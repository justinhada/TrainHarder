package de.justinharder.dietharder.domain.services;

import de.justinharder.base.domain.model.attribute.ID;
import de.justinharder.base.domain.services.Service;
import de.justinharder.dietharder.domain.model.Ziel;
import de.justinharder.dietharder.domain.model.attribute.KoerperfettAnteil;
import de.justinharder.dietharder.domain.model.attribute.Koerpergewicht;
import de.justinharder.dietharder.domain.model.exceptions.ZielException;
import de.justinharder.dietharder.domain.repository.ZielRepository;
import de.justinharder.dietharder.domain.services.dto.ziel.AktualisiertesZiel;
import de.justinharder.dietharder.domain.services.dto.ziel.GespeichertesZiel;
import de.justinharder.dietharder.domain.services.dto.ziel.NeuesZiel;
import de.justinharder.dietharder.domain.services.mapping.ZielMapping;
import de.justinharder.trainharder.domain.model.attribute.Datum;
import de.justinharder.trainharder.domain.model.exceptions.BenutzerException;
import de.justinharder.trainharder.domain.repository.BenutzerRepository;
import jakarta.enterprise.context.Dependent;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Dependent
@RequiredArgsConstructor
public class ZielService implements Service<Ziel, GespeichertesZiel, NeuesZiel, AktualisiertesZiel>
{
	@NonNull
	private final ZielRepository zielRepository;

	@NonNull
	private final BenutzerRepository benutzerRepository;

	@NonNull
	private final ZielMapping zielMapping;

	@Override
	public List<GespeichertesZiel> findeAlle()
	{
		return zielRepository.findeAlle().stream()
			.map(zielMapping::mappe)
			.toList();
	}

	@Override
	public GespeichertesZiel finde(@NonNull String id)
	{
		return zielRepository.finde(new ID(id))
			.map(zielMapping::mappe)
			.orElseThrow(() -> new ZielException("Das Ziel mit der ID %s existiert nicht!".formatted(id)));
	}

	@Override
	public GespeichertesZiel erstelle(@NonNull NeuesZiel neuesZiel)
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

		return zielMapping.mappe(ziel);
	}

	@Override
	public GespeichertesZiel aktualisiere(@NonNull String id, @NonNull AktualisiertesZiel aktualisiertesZiel)
	{
		var ziel = zielRepository.finde(new ID(id))
			.orElseThrow(() -> new ZielException("Das Ziel mit der ID %s existiert nicht!".formatted(id)))
			.setDatum(new Datum(aktualisiertesZiel.getDatum()))
			.setKoerpergewicht(new Koerpergewicht(aktualisiertesZiel.getKoerpergewicht()))
			.setKoerperfettAnteil(new KoerperfettAnteil(aktualisiertesZiel.getKoerperfettAnteil()));

		zielRepository.speichere(ziel);

		return zielMapping.mappe(ziel);
	}

	@Override
	public void loesche(@NonNull String id)
	{
		zielRepository.loesche(zielRepository.finde(new ID(id))
			.orElseThrow(() -> new ZielException("Das Ziel mit der ID %s existiert nicht!".formatted(id))));
	}

	public List<GespeichertesZiel> findeAlle(String benutzerId)
	{
		return zielRepository.findeAlle(new ID(benutzerId)).stream()
			.map(zielMapping::mappe)
			.toList();
	}
}
