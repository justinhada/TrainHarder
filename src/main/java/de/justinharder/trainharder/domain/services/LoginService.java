package de.justinharder.trainharder.domain.services;

import de.justinharder.base.domain.model.attribute.ID;
import de.justinharder.base.domain.services.Service;
import de.justinharder.base.domain.services.dto.pagination.PaginationRequest;
import de.justinharder.base.domain.services.dto.pagination.PaginationResponse;
import de.justinharder.trainharder.api.LoginDaten;
import de.justinharder.trainharder.api.Token;
import de.justinharder.trainharder.domain.model.Login;
import de.justinharder.trainharder.domain.model.attribute.*;
import de.justinharder.trainharder.domain.model.exceptions.BenutzerException;
import de.justinharder.trainharder.domain.model.exceptions.LoginException;
import de.justinharder.trainharder.domain.repository.BenutzerRepository;
import de.justinharder.trainharder.domain.repository.LoginRepository;
import de.justinharder.trainharder.domain.services.dto.login.AktualisierterLogin;
import de.justinharder.trainharder.domain.services.dto.login.GeloeschterLogin;
import de.justinharder.trainharder.domain.services.dto.login.GespeicherterLogin;
import de.justinharder.trainharder.domain.services.dto.login.NeuerLogin;
import de.justinharder.trainharder.domain.services.mapping.LoginMapping;
import de.justinharder.trainharder.utils.TokenUtils;
import jakarta.enterprise.context.Dependent;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Set;

@Dependent
@RequiredArgsConstructor
public class LoginService implements Service<
	GespeicherterLogin,
	NeuerLogin,
	AktualisierterLogin,
	GeloeschterLogin>
{
	@NonNull
	private final LoginRepository loginRepository;

	@NonNull
	private final BenutzerRepository benutzerRepository;

	@NonNull
	private final LoginMapping loginMapping;

	@NonNull
	private final MailService mailService;

	@Override
	public PaginationResponse<GespeicherterLogin> findeAlle(
		@NonNull PaginationRequest<GespeicherterLogin> paginationRequest)
	{
		return loginMapping.mappe(loginRepository.findeAlle(
			loginPaginationRequest.getPage(),
			loginPaginationRequest.getPageSize()));
	}

	@Override
	public GespeicherterLogin finde(@NonNull String id) throws LoginException
	{
		return loginRepository.finde(new ID(id))
			.map(loginMapping::mappe)
			.orElseThrow(() -> new LoginException("Der Login mit der ID %s existiert nicht!".formatted(id)));
	}

	@Override
	public NeuerLogin erstelle(@NonNull NeuerLogin neuerLogin) throws BenutzerException
	{
		var salt = Salt.random();
		var login = new Login(
			new ID(),
			neuerLogin.getEMailAdresse(),
			new Benutzername(neuerLogin.getBenutzername()),
			salt,
			neuerLogin.getPasswort(),
			benutzerRepository.finde(new ID(neuerLogin.getBenutzerId()))
				.orElseThrow(() -> new BenutzerException(
					"Der Benutzer mit der ID %s existiert nicht!".formatted(neuerLogin.getBenutzerId()))));

		loginRepository.speichere(login);

		mailService.sendeNachVollstaendigerRegistrierung(login);

		return neuerLogin;
	}

	@Override
	public AktualisierterLogin aktualisiere(@NonNull AktualisierterLogin aktualisierterLogin) throws LoginException
	{
		var login = loginRepository.finde(new ID(aktualisierterLogin.getId()))
			.orElseThrow(() -> new LoginException(
				"Der Login mit der ID %s existiert nicht!".formatted(aktualisierterLogin.getId())));

		login.setEMailAdresse(new EMailAdresse(aktualisierterLogin.getEMailAdresse()))
			.setBenutzername(new Benutzername(aktualisierterLogin.getBenutzername()))
			.setPasswort(Passwort.aus(login.getSalt(), aktualisierterLogin.getPasswort()));

		loginRepository.speichere(login);

		return aktualisierterLogin;
	}

	@Override
	public GeloeschterLogin loesche(@NonNull GeloeschterLogin geloeschterLogin) throws LoginException
	{
		loginRepository.loesche(loginRepository.finde(new ID(geloeschterLogin.getId()))
			.orElseThrow(() -> new LoginException(
				"Der Login mit der ID %s existiert nicht!".formatted(geloeschterLogin.getId()))));

		return geloeschterLogin;
	}

	public GespeicherterLogin findeMitEMailAdresse(@NonNull String eMailAdresse) throws LoginException
	{
		return loginRepository.findeMit(new EMailAdresse(eMailAdresse))
			.map(loginMapping::mappe)
			.orElseThrow(() -> new LoginException(
				"Der Login mit der E-Mail-Adresse %s existiert nicht!".formatted(eMailAdresse)));
	}

	public boolean isEMailAdresseVergeben(@NonNull String eMailAdresse)
	{
		return loginRepository.findeMit(new EMailAdresse(eMailAdresse)).isPresent();
	}

	public boolean isBenutzernameVergeben(@NonNull String benutzername)
	{
		return loginRepository.findeMit(new Benutzername(benutzername)).isPresent();
	}

	public Token login(LoginDaten loginDaten)
		throws LoginException, IOException, NoSuchAlgorithmException, InvalidKeySpecException
	{
		var login = loginRepository.findeMit(new Benutzername(loginDaten.getBenutzername()))
			.orElseThrow(() -> new LoginException(
				"Der Login mit dem Benutzernamen %s existiert nicht!".formatted(loginDaten.getBenutzername())));

		if (!Passwort.aus(login.getSalt(), loginDaten.getPasswort()).equals(login.getPasswort()))
		{
			throw new LoginException("Der Benutzername oder das Passwort ist falsch!");
		}

		return new Token(TokenUtils.generateToken(login.getBenutzername(), Set.of(Rolle.BENUTZER)));
	}
}
