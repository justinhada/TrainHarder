package de.justinharder.trainharder.domain.services;

import de.justinharder.base.domain.model.attribute.ID;
import de.justinharder.base.domain.services.Service;
import de.justinharder.base.domain.services.dto.pagination.PaginationRequest;
import de.justinharder.base.domain.services.dto.pagination.PaginationResponse;
import de.justinharder.trainharder.domain.model.Benutzer;
import de.justinharder.trainharder.domain.model.attribute.*;
import de.justinharder.trainharder.domain.model.exceptions.BenutzerException;
import de.justinharder.trainharder.domain.repository.BenutzerRepository;
import de.justinharder.trainharder.domain.services.dto.benutzer.AktualisierterBenutzer;
import de.justinharder.trainharder.domain.services.dto.benutzer.GeloeschterBenutzer;
import de.justinharder.trainharder.domain.services.dto.benutzer.GespeicherterBenutzer;
import de.justinharder.trainharder.domain.services.dto.benutzer.NeuerBenutzer;
import de.justinharder.trainharder.domain.services.mapping.BenutzerMapping;
import jakarta.enterprise.context.Dependent;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Dependent
@RequiredArgsConstructor
public class BenutzerService implements Service<
	GespeicherterBenutzer,
	NeuerBenutzer,
	AktualisierterBenutzer,
	GeloeschterBenutzer>
{
	@NonNull
	private final BenutzerRepository benutzerRepository;

	@NonNull
	private final BenutzerMapping benutzerMapping;

	@Override
	public PaginationResponse<GespeicherterBenutzer> findeAlle(
		@NonNull PaginationRequest<GespeicherterBenutzer> paginationRequest)
	{
		return benutzerMapping.mappe(
			"benutzer",
			benutzerRepository.findeAlle(paginationRequest),
			benutzerRepository.zaehleAlle(),
			paginationRequest);
	}

	@Override
	public GespeicherterBenutzer finde(@NonNull String id) throws BenutzerException
	{
		return benutzerRepository.finde(new ID(id))
			.map(benutzerMapping::mappe)
			.orElseThrow(() -> new BenutzerException("Der Benutzer mit der ID %s existiert nicht!".formatted(id)));
	}

	@Override
	public NeuerBenutzer erstelle(@NonNull NeuerBenutzer neuerBenutzer)
	{
		var benutzer = new Benutzer(
			new ID(),
			new Nachname(neuerBenutzer.getNachname()),
			new Vorname(neuerBenutzer.getVorname()),
			Geschlecht.aus(neuerBenutzer.getGeschlecht()),
			new Geburtsdatum(neuerBenutzer.getGeburtsdatum().substring(0, 10)));

		benutzerRepository.speichere(benutzer);

		return neuerBenutzer;
	}

	@Override
	public AktualisierterBenutzer aktualisiere(@NonNull AktualisierterBenutzer aktualisierterBenutzer)
		throws BenutzerException
	{
		var benutzer = benutzerRepository.finde(new ID(aktualisierterBenutzer.getId()))
			.orElseThrow(() -> new BenutzerException(
				"Der Benutzer mit der ID %s existiert nicht!".formatted(aktualisierterBenutzer.getId())))
			.setNachname(new Nachname(aktualisierterBenutzer.getNachname()))
			.setVorname(new Vorname(aktualisierterBenutzer.getVorname()))
			.setGeschlecht(Geschlecht.aus(aktualisierterBenutzer.getGeschlecht()))
			.setGeburtsdatum(new Geburtsdatum(aktualisierterBenutzer.getGeburtsdatum()));

		benutzerRepository.speichere(benutzer);

		return aktualisierterBenutzer;
	}

	@Override
	public GeloeschterBenutzer loesche(@NonNull GeloeschterBenutzer geloeschterBenutzer) throws BenutzerException
	{
		benutzerRepository.loesche(benutzerRepository.finde(new ID(geloeschterBenutzer.getId()))
			.orElseThrow(() -> new BenutzerException(
				"Der Benutzer mit der ID %s existiert nicht!".formatted(geloeschterBenutzer.getId()))));

		return geloeschterBenutzer;
	}

	public GespeicherterBenutzer findeMitLogin(@NonNull String loginId) throws BenutzerException
	{
		return benutzerRepository.findeMit(new ID(loginId))
			.map(benutzerMapping::mappe)
			.orElseThrow(() -> new BenutzerException(
				"Der Benutzer mit der LoginID %s existiert nicht!".formatted(loginId)));
	}

	public GespeicherterBenutzer findeMitBenutzername(@NonNull String benutzername) throws BenutzerException
	{
		return benutzerRepository.findeMit(new Benutzername(benutzername))
			.map(benutzerMapping::mappe)
			.orElseThrow(() -> new BenutzerException(
				"Der Benutzer mit dem Benutzernamen %s existiert nicht!".formatted(benutzername)));
	}
}
