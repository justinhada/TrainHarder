package de.justinharder.trainharder.domain.services;

import de.justinharder.base.domain.model.attribute.ID;
import de.justinharder.base.domain.services.Service;
import de.justinharder.base.domain.services.dto.pagination.PaginationRequest;
import de.justinharder.base.domain.services.dto.pagination.PaginationResponse;
import de.justinharder.trainharder.domain.model.Registrierung;
import de.justinharder.trainharder.domain.model.attribute.EMailAdresse;
import de.justinharder.trainharder.domain.model.attribute.Passwort;
import de.justinharder.trainharder.domain.model.attribute.Salt;
import de.justinharder.trainharder.domain.model.exceptions.BenutzerException;
import de.justinharder.trainharder.domain.model.exceptions.RegistrierungException;
import de.justinharder.trainharder.domain.repository.RegistrierungRepository;
import de.justinharder.trainharder.domain.services.dto.benutzer.NeuerBenutzer;
import de.justinharder.trainharder.domain.services.dto.login.NeuerLogin;
import de.justinharder.trainharder.domain.services.dto.registrierung.AktualisierteRegistrierung;
import de.justinharder.trainharder.domain.services.dto.registrierung.GeloeschteRegistrierung;
import de.justinharder.trainharder.domain.services.dto.registrierung.GespeicherteRegistrierung;
import de.justinharder.trainharder.domain.services.dto.registrierung.NeueRegistrierung;
import de.justinharder.trainharder.domain.services.mapping.RegistrierungMapping;
import jakarta.enterprise.context.Dependent;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Dependent
@RequiredArgsConstructor
public class RegistrierungService implements Service<
	GespeicherteRegistrierung,
	NeueRegistrierung,
	AktualisierteRegistrierung,
	GeloeschteRegistrierung>
{
	private static final String ENDPUNKT = "registrierungen";

	@NonNull
	private final RegistrierungRepository registrierungRepository;

	@NonNull
	private final RegistrierungMapping registrierungMapping;

	@NonNull
	private final BenutzerService benutzerService;

	@NonNull
	private final LoginService loginService;

	@NonNull
	private final MailService mailService;

	@Override
	public PaginationResponse<GespeicherteRegistrierung> findeAlle(
		@NonNull PaginationRequest<GespeicherteRegistrierung> paginationRequest)
	{
		return registrierungMapping.mappe(
			paginationRequest,
			registrierungRepository.findeAlle(paginationRequest),
			registrierungRepository.zaehleAlle(),
			ENDPUNKT);
	}

	@Override
	public GespeicherteRegistrierung finde(@NonNull String id) throws RegistrierungException
	{
		return registrierungRepository.finde(new ID(id))
			.map(registrierungMapping::mappe)
			.orElseThrow(
				() -> new RegistrierungException("Die Registrierung mit der ID %s existiert nicht!".formatted(id)));
	}

	@Override
	@Transactional
	public NeueRegistrierung erstelle(@NonNull NeueRegistrierung neueRegistrierung)
	{
		var salt = Salt.random();
		var registrierung = new Registrierung(
			new ID(),
			new EMailAdresse(neueRegistrierung.getEMailAdresse()),
			salt,
			Passwort.aus(salt, neueRegistrierung.getPasswort()));

		registrierungRepository.speichere(registrierung);

		mailService.sendeNachErsterRegistrierung(registrierung);

		return neueRegistrierung;
	}

	@Override
	@Transactional
	public AktualisierteRegistrierung aktualisiere(@NonNull AktualisierteRegistrierung aktualisierteRegistrierung)
		throws RegistrierungException, BenutzerException
	{
		var registrierung = registrierungRepository.finde(new ID(aktualisierteRegistrierung.getId()))
			.orElseThrow(() -> new RegistrierungException(
				"Die Registrierung mit der ID %s existiert nicht!".formatted(aktualisierteRegistrierung.getId())));

		var neuerBenutzer = benutzerService.erstelle(NeuerBenutzer.aus(aktualisierteRegistrierung));
		var gespeicherterLogin = loginService.erstelle(
			NeuerLogin.aus(registrierung, aktualisierteRegistrierung, neuerBenutzer));

		registrierungRepository.loesche(registrierung);

		return aktualisierteRegistrierung;
	}

	@Override
	public GeloeschteRegistrierung loesche(@NonNull GeloeschteRegistrierung geloeschteRegistrierung)
		throws RegistrierungException
	{
		registrierungRepository.loesche(registrierungRepository.finde(new ID(geloeschteRegistrierung.getId()))
			.orElseThrow(() -> new RegistrierungException(
				"Die Registrierung mit der ID %s existiert nicht!".formatted(geloeschteRegistrierung.getId()))));

		return geloeschteRegistrierung;
	}

	public boolean isEMailAdresseVergeben(@NonNull String eMailAdresse)
	{
		return registrierungRepository.findeMit(new EMailAdresse(eMailAdresse)).isPresent();
	}
}
