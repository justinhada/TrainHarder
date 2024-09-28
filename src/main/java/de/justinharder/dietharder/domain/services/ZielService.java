package de.justinharder.dietharder.domain.services;

import de.justinharder.base.domain.model.attribute.ID;
import de.justinharder.base.domain.services.Service;
import de.justinharder.base.domain.services.dto.pagination.PaginationRequest;
import de.justinharder.base.domain.services.dto.pagination.PaginationResponse;
import de.justinharder.dietharder.domain.model.Ziel;
import de.justinharder.dietharder.domain.model.attribute.KoerperfettAnteil;
import de.justinharder.dietharder.domain.model.attribute.Koerpergewicht;
import de.justinharder.dietharder.domain.model.exceptions.ZielException;
import de.justinharder.dietharder.domain.repository.ZielRepository;
import de.justinharder.dietharder.domain.services.dto.ziel.AktualisiertesZiel;
import de.justinharder.dietharder.domain.services.dto.ziel.GeloeschtesZiel;
import de.justinharder.dietharder.domain.services.dto.ziel.GespeichertesZiel;
import de.justinharder.dietharder.domain.services.dto.ziel.NeuesZiel;
import de.justinharder.dietharder.domain.services.mapping.ZielMapping;
import de.justinharder.trainharder.domain.model.attribute.Datum;
import de.justinharder.trainharder.domain.model.exceptions.BenutzerException;
import de.justinharder.trainharder.domain.repository.BenutzerRepository;
import jakarta.enterprise.context.Dependent;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Dependent
@RequiredArgsConstructor
public class ZielService implements Service<
	GespeichertesZiel,
	NeuesZiel,
	AktualisiertesZiel,
	GeloeschtesZiel>
{
	private static final String ENDPUNKT = "ziele";

	@NonNull
	private final ZielRepository zielRepository;

	@NonNull
	private final BenutzerRepository benutzerRepository;

	@NonNull
	private final ZielMapping zielMapping;

	@Override
	public PaginationResponse<GespeichertesZiel> findeAlle(
		@NonNull PaginationRequest<GespeichertesZiel> paginationRequest)
	{
		return zielMapping.mappe(
			ENDPUNKT,
			zielRepository.findeAlle(paginationRequest),
			zielRepository.zaehleAlle(),
			paginationRequest);
	}

	@Override
	public GespeichertesZiel finde(@NonNull String id) throws ZielException
	{
		return zielRepository.finde(new ID(id))
			.map(zielMapping::mappe)
			.orElseThrow(() -> new ZielException("Das Ziel mit der ID %s existiert nicht!".formatted(id)));
	}

	@Override
	public NeuesZiel erstelle(@NonNull NeuesZiel neuesZiel) throws BenutzerException
	{
		var ziel = new Ziel(
			new ID(),
			new Datum(neuesZiel.getDatum()),
			new Koerpergewicht(neuesZiel.getKoerpergewicht()),
			new KoerperfettAnteil(neuesZiel.getKoerperfettAnteil()),
			benutzerRepository.finde(new ID(neuesZiel.getBenutzerId()))
				.orElseThrow(() -> new BenutzerException(
					"Der Benutzer mit der ID %s existiert nicht!".formatted(neuesZiel.getBenutzerId()))));

		zielRepository.speichere(ziel);

		return neuesZiel;
	}

	@Override
	public AktualisiertesZiel aktualisiere(@NonNull AktualisiertesZiel aktualisiertesZiel) throws ZielException
	{
		var ziel = zielRepository.finde(new ID(aktualisiertesZiel.getId()))
			.orElseThrow(() -> new ZielException(
				"Das Ziel mit der ID %s existiert nicht!".formatted(aktualisiertesZiel.getId())))
			.setDatum(new Datum(aktualisiertesZiel.getDatum()))
			.setKoerpergewicht(new Koerpergewicht(aktualisiertesZiel.getKoerpergewicht()))
			.setKoerperfettAnteil(new KoerperfettAnteil(aktualisiertesZiel.getKoerperfettAnteil()));

		zielRepository.speichere(ziel);

		return aktualisiertesZiel;
	}

	@Override
	public GeloeschtesZiel loesche(@NonNull GeloeschtesZiel geloeschtesZiel) throws ZielException
	{
		zielRepository.loesche(zielRepository.finde(new ID(geloeschtesZiel.getId()))
			.orElseThrow(() -> new ZielException(
				"Das Ziel mit der ID %s existiert nicht!".formatted(geloeschtesZiel.getId()))));

		return geloeschtesZiel;
	}

	public PaginationResponse<GespeichertesZiel> findeAlle(
		@NonNull String benutzerId,
		@NonNull PaginationRequest<GespeichertesZiel> paginationRequest)
	{
		return zielMapping.mappe(
			ENDPUNKT, // TODO: erweitere Endpunkt mit BenutzerID
			zielRepository.findeAlle(new ID(benutzerId), paginationRequest),
			zielRepository.zaehleAlle(), // TODO: Zähle nur die relevanten
			paginationRequest);
	}
}
