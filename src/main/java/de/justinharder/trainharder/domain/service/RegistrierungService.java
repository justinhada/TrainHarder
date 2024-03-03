package de.justinharder.trainharder.domain.service;

import de.justinharder.base.domain.model.attribute.ID;
import de.justinharder.base.domain.services.Service;
import de.justinharder.trainharder.domain.model.Registrierung;
import de.justinharder.trainharder.domain.model.attribute.EMailAdresse;
import de.justinharder.trainharder.domain.model.attribute.Passwort;
import de.justinharder.trainharder.domain.model.attribute.Salt;
import de.justinharder.trainharder.domain.model.exceptions.RegistrierungException;
import de.justinharder.trainharder.domain.repository.RegistrierungRepository;
import de.justinharder.trainharder.domain.service.dto.registrierung.AktualisierteRegistrierung;
import de.justinharder.trainharder.domain.service.dto.registrierung.GeloeschteRegistrierung;
import de.justinharder.trainharder.domain.service.dto.registrierung.GespeicherteRegistrierung;
import de.justinharder.trainharder.domain.service.dto.registrierung.NeueRegistrierung;
import de.justinharder.trainharder.domain.service.dto.registrierung.pagination.RegistrierungPaginationRequest;
import de.justinharder.trainharder.domain.service.dto.registrierung.pagination.RegistrierungPaginationResponse;
import de.justinharder.trainharder.domain.service.mapping.RegistrierungMapping;
import jakarta.enterprise.context.Dependent;
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
	public GespeicherteRegistrierung erstelle(@NonNull NeueRegistrierung neueRegistrierung)
	{
		var salt = Salt.random();
		var registrierung = new Registrierung(
			new ID(),
			new EMailAdresse(neueRegistrierung.getEMailAdresse()),
			salt,
			Passwort.aus(salt, neueRegistrierung.getPasswort()));

		registrierungRepository.speichere(registrierung);

		return registrierungMapping.mappe(registrierung);
	}

	@Override
	public GespeicherteRegistrierung aktualisiere(
		@NonNull String id,
		@NonNull AktualisierteRegistrierung aktualisierteRegistrierung) throws RegistrierungException
	{
		var registrierung = registrierungRepository.finde(new ID(id))
			.orElseThrow(
				() -> new RegistrierungException("Die Registrierung mit der ID %s existiert nicht!".formatted(id)));

		registrierung.setEMailAdresse(new EMailAdresse(aktualisierteRegistrierung.getEMailAdresse()))
			.setPasswort(Passwort.aus(registrierung.getSalt(), aktualisierteRegistrierung.getPasswort()));

		registrierungRepository.speichere(registrierung);

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
}
