package de.justinharder.dietharder.domain.services;

import de.justinharder.base.domain.model.attribute.ID;
import de.justinharder.base.domain.services.Service;
import de.justinharder.dietharder.domain.model.Messung;
import de.justinharder.dietharder.domain.model.attribute.KoerperfettAnteil;
import de.justinharder.dietharder.domain.model.attribute.Koerpergewicht;
import de.justinharder.dietharder.domain.model.exceptions.MessungException;
import de.justinharder.dietharder.domain.repository.MessungRepository;
import de.justinharder.dietharder.domain.services.dto.messung.AktualisierteMessung;
import de.justinharder.dietharder.domain.services.dto.messung.GespeicherteMessung;
import de.justinharder.dietharder.domain.services.dto.messung.NeueMessung;
import de.justinharder.dietharder.domain.services.mapping.MessungMapping;
import de.justinharder.trainharder.domain.model.attribute.Datum;
import de.justinharder.trainharder.domain.model.exceptions.BenutzerException;
import de.justinharder.trainharder.domain.repository.BenutzerRepository;
import jakarta.enterprise.context.Dependent;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Dependent
@RequiredArgsConstructor
public class MessungService implements Service<Messung, GespeicherteMessung, NeueMessung, AktualisierteMessung>
{
	@NonNull
	private final MessungRepository messungRepository;

	@NonNull
	private final BenutzerRepository benutzerRepository;

	@NonNull
	private final MessungMapping messungMapping;

	@Override
	public List<GespeicherteMessung> findeAlle()
	{
		return messungRepository.findeAlle().stream()
			.map(messungMapping::mappe)
			.toList();
	}

	@Override
	public GespeicherteMessung finde(@NonNull String id)
	{
		return messungRepository.finde(new ID(id))
			.map(messungMapping::mappe)
			.orElseThrow(() -> new MessungException("Die Messung mit der ID %s existiert nicht!".formatted(id)));
	}

	@Override
	public GespeicherteMessung erstelle(@NonNull NeueMessung neueMessung)
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
	public void loesche(@NonNull String id)
	{
		messungRepository.loesche(messungRepository.finde(new ID(id))
			.orElseThrow(() -> new MessungException("Die Messung mit der ID %s existiert nicht!".formatted(id))));
	}

	public List<GespeicherteMessung> findeAlle(String benutzerId)
	{
		return messungRepository.findeAlle(new ID(benutzerId)).stream()
			.map(messungMapping::mappe)
			.toList();
	}
}
