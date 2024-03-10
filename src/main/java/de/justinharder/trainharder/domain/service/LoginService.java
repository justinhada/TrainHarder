package de.justinharder.trainharder.domain.service;

import de.justinharder.base.domain.model.attribute.ID;
import de.justinharder.base.domain.services.Service;
import de.justinharder.trainharder.domain.model.Login;
import de.justinharder.trainharder.domain.model.attribute.Benutzername;
import de.justinharder.trainharder.domain.model.attribute.EMailAdresse;
import de.justinharder.trainharder.domain.model.attribute.Passwort;
import de.justinharder.trainharder.domain.model.attribute.Salt;
import de.justinharder.trainharder.domain.model.exceptions.BenutzerException;
import de.justinharder.trainharder.domain.model.exceptions.LoginException;
import de.justinharder.trainharder.domain.repository.BenutzerRepository;
import de.justinharder.trainharder.domain.repository.LoginRepository;
import de.justinharder.trainharder.domain.service.dto.login.AktualisierterLogin;
import de.justinharder.trainharder.domain.service.dto.login.GeloeschterLogin;
import de.justinharder.trainharder.domain.service.dto.login.GespeicherterLogin;
import de.justinharder.trainharder.domain.service.dto.login.NeuerLogin;
import de.justinharder.trainharder.domain.service.dto.login.pagination.LoginPaginationRequest;
import de.justinharder.trainharder.domain.service.dto.login.pagination.LoginPaginationResponse;
import de.justinharder.trainharder.domain.service.mapping.LoginMapping;
import jakarta.enterprise.context.Dependent;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Dependent
@RequiredArgsConstructor
public class LoginService implements
	Service<GespeicherterLogin, NeuerLogin, AktualisierterLogin, GeloeschterLogin, LoginPaginationRequest, LoginPaginationResponse>
{
	@NonNull
	private final LoginRepository loginRepository;

	@NonNull
	private final BenutzerRepository benutzerRepository;

	@NonNull
	private final LoginMapping loginMapping;

	@Override
	public List<GespeicherterLogin> findeAlle()
	{
		return loginRepository.findeAlle().stream()
			.map(loginMapping::mappe)
			.toList();
	}

	@Override
	public LoginPaginationResponse findeAlle(@NonNull LoginPaginationRequest loginPaginationRequest)
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
	public GespeicherterLogin erstelle(@NonNull NeuerLogin neuerLogin) throws BenutzerException
	{
		var salt = Salt.random();
		var login = new Login(
			new ID(),
			new EMailAdresse(neuerLogin.getEMailAdresse()),
			new Benutzername(neuerLogin.getBenutzername()),
			salt,
			Passwort.aus(salt, neuerLogin.getPasswort()),
			benutzerRepository.finde(new ID(neuerLogin.getBenutzerId()))
				.orElseThrow(() -> new BenutzerException(
					"Der Benutzer mit der ID %s existiert nicht!".formatted(neuerLogin.getBenutzerId()))));

		loginRepository.speichere(login);

		return loginMapping.mappe(login);
	}

	@Override
	public GespeicherterLogin aktualisiere(@NonNull String id, @NonNull AktualisierterLogin aktualisierterLogin)
		throws LoginException
	{
		var login = loginRepository.finde(new ID(id))
			.orElseThrow(() -> new LoginException("Der Login mit der ID %s existiert nicht!".formatted(id)));

		login.setEMailAdresse(new EMailAdresse(aktualisierterLogin.getEMailAdresse()))
			.setBenutzername(new Benutzername(aktualisierterLogin.getBenutzername()))
			.setPasswort(Passwort.aus(login.getSalt(), aktualisierterLogin.getPasswort()));

		loginRepository.speichere(login);

		return loginMapping.mappe(login);
	}

	@Override
	public GeloeschterLogin loesche(@NonNull String id) throws LoginException
	{
		loginRepository.loesche(loginRepository.finde(new ID(id))
			.orElseThrow(() -> new LoginException("Der Login mit der ID %s existiert nicht!".formatted(id))));

		return new GeloeschterLogin(id);
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
}
