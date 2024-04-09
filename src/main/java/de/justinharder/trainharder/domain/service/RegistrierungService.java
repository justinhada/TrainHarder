package de.justinharder.trainharder.domain.service;

import de.justinharder.base.domain.model.attribute.ID;
import de.justinharder.base.domain.services.Service;
import de.justinharder.trainharder.domain.model.Registrierung;
import de.justinharder.trainharder.domain.model.attribute.EMailAdresse;
import de.justinharder.trainharder.domain.model.attribute.Passwort;
import de.justinharder.trainharder.domain.model.attribute.Salt;
import de.justinharder.trainharder.domain.model.exceptions.BenutzerException;
import de.justinharder.trainharder.domain.model.exceptions.RegistrierungException;
import de.justinharder.trainharder.domain.repository.RegistrierungRepository;
import de.justinharder.trainharder.domain.service.dto.benutzer.NeuerBenutzer;
import de.justinharder.trainharder.domain.service.dto.login.NeuerLogin;
import de.justinharder.trainharder.domain.service.dto.registrierung.AktualisierteRegistrierung;
import de.justinharder.trainharder.domain.service.dto.registrierung.GeloeschteRegistrierung;
import de.justinharder.trainharder.domain.service.dto.registrierung.GespeicherteRegistrierung;
import de.justinharder.trainharder.domain.service.dto.registrierung.NeueRegistrierung;
import de.justinharder.trainharder.domain.service.dto.registrierung.pagination.RegistrierungPaginationRequest;
import de.justinharder.trainharder.domain.service.dto.registrierung.pagination.RegistrierungPaginationResponse;
import de.justinharder.trainharder.domain.service.mapping.RegistrierungMapping;
import jakarta.enterprise.context.Dependent;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Dependent
@RequiredArgsConstructor
public class RegistrierungService implements
	Service<GespeicherteRegistrierung, NeueRegistrierung, AktualisierteRegistrierung, GeloeschteRegistrierung, RegistrierungPaginationRequest, RegistrierungPaginationResponse>
{
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
	public List<GespeicherteRegistrierung> findeAlle()
	{
		return registrierungRepository.findeAlle().stream()
			.map(registrierungMapping::mappe)
			.toList();
	}

	@Override
	public RegistrierungPaginationResponse findeAlle(
		@NonNull RegistrierungPaginationRequest registrierungPaginationRequest)
	{
		return registrierungMapping.mappe(registrierungRepository.findeAlle(
			registrierungPaginationRequest.getPage(),
			registrierungPaginationRequest.getPageSize()));
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
	public GespeicherteRegistrierung erstelle(@NonNull NeueRegistrierung neueRegistrierung)
	{
		var salt = Salt.random();
		var registrierung = new Registrierung(
			new ID(),
			new EMailAdresse(neueRegistrierung.getEMailAdresse()),
			salt,
			Passwort.aus(salt, neueRegistrierung.getPasswort()));

		registrierungRepository.speichere(registrierung);

		mailService.sendeNachErsterRegistrierung(registrierung);

		return registrierungMapping.mappe(registrierung);
	}

	// TODO: Auch AktualisierteRegistrierung zurückgeben (allgemein immer rein und raus gleich halten)
	@Override
	@Transactional
	public GespeicherteRegistrierung aktualisiere(
		@NonNull String id,
		@NonNull AktualisierteRegistrierung aktualisierteRegistrierung) throws RegistrierungException, BenutzerException
	{
		var registrierung = registrierungRepository.finde(new ID(id))
			.orElseThrow(
				() -> new RegistrierungException("Die Registrierung mit der ID %s existiert nicht!".formatted(id)));

		var gespeicherterBenutzer = benutzerService.erstelle(NeuerBenutzer.aus(aktualisierteRegistrierung));
		var gespeicherterLogin = loginService.erstelle(
			NeuerLogin.aus(registrierung, aktualisierteRegistrierung, gespeicherterBenutzer));

		registrierungRepository.loesche(registrierung);

		return registrierungMapping.mappe(registrierung);
	}

	@Override
	public GeloeschteRegistrierung loesche(@NonNull String id) throws RegistrierungException
	{
		registrierungRepository.loesche(registrierungRepository.finde(new ID(id))
			.orElseThrow(
				() -> new RegistrierungException("Die Registrierung mit der ID %s existiert nicht!".formatted(id))));

		return new GeloeschteRegistrierung(id);
	}

	public boolean isEMailAdresseVergeben(@NonNull String eMailAdresse)
	{
		return registrierungRepository.findeMit(new EMailAdresse(eMailAdresse)).isPresent();
	}
}
