package de.justinharder.trainharder.domain.services;

import de.justinharder.base.domain.model.attribute.ID;
import de.justinharder.base.domain.services.Service;
import de.justinharder.trainharder.domain.model.Benutzer;
import de.justinharder.trainharder.domain.model.attribute.*;
import de.justinharder.trainharder.domain.model.exceptions.BenutzerException;
import de.justinharder.trainharder.domain.repository.BenutzerRepository;
import de.justinharder.trainharder.domain.services.dto.benutzer.AktualisierterBenutzer;
import de.justinharder.trainharder.domain.services.dto.benutzer.GeloeschterBenutzer;
import de.justinharder.trainharder.domain.services.dto.benutzer.GespeicherterBenutzer;
import de.justinharder.trainharder.domain.services.dto.benutzer.NeuerBenutzer;
import de.justinharder.trainharder.domain.services.dto.benutzer.pagination.BenutzerPaginationRequest;
import de.justinharder.trainharder.domain.services.dto.benutzer.pagination.BenutzerPaginationResponse;
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
	GeloeschterBenutzer,
	BenutzerPaginationRequest,
	BenutzerPaginationResponse>
{
	@NonNull
	private final BenutzerRepository benutzerRepository;

	@NonNull
	private final BenutzerMapping benutzerMapping;

	@Override
	public BenutzerPaginationResponse findeAlle(@NonNull BenutzerPaginationRequest benutzerPaginationRequest)
	{
		return benutzerMapping.mappe(benutzerRepository.findeAlle(
			benutzerPaginationRequest.getPage(),
			benutzerPaginationRequest.getPageSize()));
	}

	@Override
	public GespeicherterBenutzer finde(@NonNull String id) throws BenutzerException
	{
		return benutzerRepository.finde(new ID(id))
			.map(benutzerMapping::mappe)
			.orElseThrow(() -> new BenutzerException("Der Benutzer mit der ID %s existiert nicht!".formatted(id)));
	}

	@Override
	public GespeicherterBenutzer erstelle(@NonNull NeuerBenutzer neuerBenutzer)
	{
		var benutzer = new Benutzer(
			new ID(),
			new Nachname(neuerBenutzer.getNachname()),
			new Vorname(neuerBenutzer.getVorname()),
			Geschlecht.aus(neuerBenutzer.getGeschlecht()),
			new Geburtsdatum(neuerBenutzer.getGeburtsdatum().substring(0, 10)));

		benutzerRepository.speichere(benutzer);

		return benutzerMapping.mappe(benutzer);
	}

	@Override
	public GespeicherterBenutzer aktualisiere(
		@NonNull String id,
		@NonNull AktualisierterBenutzer aktualisierterBenutzer) throws BenutzerException
	{
		var benutzer = benutzerRepository.finde(new ID(id))
			.orElseThrow(() -> new BenutzerException("Der Benutzer mit der ID %s existiert nicht!".formatted(id)))
			.setNachname(new Nachname(aktualisierterBenutzer.getNachname()))
			.setVorname(new Vorname(aktualisierterBenutzer.getVorname()))
			.setGeschlecht(Geschlecht.aus(aktualisierterBenutzer.getGeschlecht()))
			.setGeburtsdatum(new Geburtsdatum(aktualisierterBenutzer.getGeburtsdatum()));

		benutzerRepository.speichere(benutzer);

		return benutzerMapping.mappe(benutzer);
	}

	@Override
	public GeloeschterBenutzer loesche(@NonNull String id) throws BenutzerException
	{
		benutzerRepository.loesche(benutzerRepository.finde(new ID(id))
			.orElseThrow(() -> new BenutzerException("Der Benutzer mit der ID %s existiert nicht!".formatted(id))));

		return new GeloeschterBenutzer(id);
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
