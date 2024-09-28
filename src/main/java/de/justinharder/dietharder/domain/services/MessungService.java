package de.justinharder.dietharder.domain.services;

import de.justinharder.base.domain.model.attribute.ID;
import de.justinharder.base.domain.services.Service;
import de.justinharder.base.domain.services.dto.pagination.PaginationRequest;
import de.justinharder.base.domain.services.dto.pagination.PaginationResponse;
import de.justinharder.dietharder.domain.model.Messung;
import de.justinharder.dietharder.domain.model.attribute.KoerperfettAnteil;
import de.justinharder.dietharder.domain.model.attribute.Koerpergewicht;
import de.justinharder.dietharder.domain.model.exceptions.MessungException;
import de.justinharder.dietharder.domain.repository.MessungRepository;
import de.justinharder.dietharder.domain.services.dto.messung.AktualisierteMessung;
import de.justinharder.dietharder.domain.services.dto.messung.GeloeschteMessung;
import de.justinharder.dietharder.domain.services.dto.messung.GespeicherteMessung;
import de.justinharder.dietharder.domain.services.dto.messung.NeueMessung;
import de.justinharder.dietharder.domain.services.mapping.MessungMapping;
import de.justinharder.trainharder.domain.model.attribute.Datum;
import de.justinharder.trainharder.domain.model.exceptions.BenutzerException;
import de.justinharder.trainharder.domain.repository.BenutzerRepository;
import jakarta.enterprise.context.Dependent;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Dependent
@RequiredArgsConstructor
public class MessungService implements Service<
	GespeicherteMessung,
	NeueMessung,
	AktualisierteMessung,
	GeloeschteMessung>
{
	@NonNull
	private final MessungRepository messungRepository;

	@NonNull
	private final BenutzerRepository benutzerRepository;

	@NonNull
	private final MessungMapping messungMapping;

	@Override
	public PaginationResponse<GespeicherteMessung> findeAlle(
		@NonNull PaginationRequest<GespeicherteMessung> paginationRequest)
	{
		return messungMapping.mappe(messungRepository.findeAlle(
			messungPaginationRequest.getPage(),
			messungPaginationRequest.getPageSize()));
	}

	@Override
	public GespeicherteMessung finde(@NonNull String id) throws MessungException
	{
		return messungRepository.finde(new ID(id))
			.map(messungMapping::mappe)
			.orElseThrow(() -> new MessungException("Die Messung mit der ID %s existiert nicht!".formatted(id)));
	}

	@Override
	public GespeicherteMessung erstelle(@NonNull NeueMessung neueMessung) throws BenutzerException
	{
		var messung = new Messung(
			new ID(),
			new Datum(neueMessung.getDatum()),
			new Koerpergewicht(neueMessung.getKoerpergewicht()),
			new KoerperfettAnteil(neueMessung.getKoerperfettAnteil()),
			benutzerRepository.finde(new ID(neueMessung.getBenutzerId()))
				.orElseThrow(() -> new BenutzerException(
					"Der Benutzer mit der ID %s existiert nicht!".formatted(neueMessung.getBenutzerId()))));

		messungRepository.speichere(messung);

		return messungMapping.mappe(messung);
	}

	@Override
	public GespeicherteMessung aktualisiere(@NonNull String id, @NonNull AktualisierteMessung aktualisierteMessung)
		throws MessungException
	{
		var messung = messungRepository.finde(new ID(id))
			.orElseThrow(() -> new MessungException("Die Messung mit der ID %s existiert nicht!".formatted(id)))
			.setDatum(new Datum(aktualisierteMessung.getDatum()))
			.setKoerpergewicht(new Koerpergewicht(aktualisierteMessung.getKoerpergewicht()))
			.setKoerperfettAnteil(new KoerperfettAnteil(aktualisierteMessung.getKoerperfettAnteil()));

		messungRepository.speichere(messung);

		return messungMapping.mappe(messung);
	}

	@Override
	public GeloeschteMessung loesche(@NonNull String id) throws MessungException
	{
		messungRepository.loesche(messungRepository.finde(new ID(id))
			.orElseThrow(() -> new MessungException("Die Messung mit der ID %s existiert nicht!".formatted(id))));

		return new GeloeschteMessung(id);
	}

	public PaginationResponse<GespeicherteMessung> findeAlle(
		@NonNull String benutzerId,
		@NonNull PaginationRequest<GespeicherteMessung> paginationRequest)
	{
		return messungRepository.findeAlle(
				new ID(benutzerId),
				messungPaginationRequest.getPage(),
				messungPaginationRequest.getPageSize()).stream()
			.map(messungMapping::mappe)
			.toList();
	}
}
