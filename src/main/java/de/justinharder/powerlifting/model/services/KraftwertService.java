package de.justinharder.powerlifting.model.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import de.justinharder.powerlifting.model.domain.Benutzer;
import de.justinharder.powerlifting.model.domain.Kraftwert;
import de.justinharder.powerlifting.model.domain.Uebung;
import de.justinharder.powerlifting.model.domain.dto.KraftwertEintrag;
import de.justinharder.powerlifting.model.domain.exceptions.KraftwertNichtGefundenException;
import de.justinharder.powerlifting.model.repository.KraftwertRepository;

public class KraftwertService
{
	private final KraftwertRepository kraftwertRepository;

	@Inject
	public KraftwertService(final KraftwertRepository kraftwertRepository)
	{
		this.kraftwertRepository = kraftwertRepository;
	}

	public List<KraftwertEintrag> ermittleAlle()
	{
		return konvertiereAlle(kraftwertRepository.ermittleAlle());
	}

	public List<KraftwertEintrag> ermittleAlleZuBenutzer(final Benutzer benutzer)
	{
		return konvertiereAlle(kraftwertRepository.ermittleAlleZuBenutzer(benutzer));
	}

	public KraftwertEintrag ermittleZuId(final int id) throws KraftwertNichtGefundenException
	{
		final var kraftwert = kraftwertRepository.ermittleZuId(id);
		if (kraftwert == null)
		{
			throw new KraftwertNichtGefundenException("Der Kraftwert mit der ID " + id + " existiert nicht!");
		}
		return konvertiere(kraftwert);
	}

	public KraftwertEintrag erstelleKraftwert(final Uebung uebung, final int maximum, final Benutzer benutzer)
	{
		final var kraftwert = new Kraftwert(uebung, maximum, benutzer);
		kraftwertRepository.erstelleKraftwert(kraftwert);
		return konvertiere(kraftwert);
	}

	private List<KraftwertEintrag> konvertiereAlle(final List<Kraftwert> kraftwerte)
	{
		return kraftwerte
			.stream()
			.map(this::konvertiere)
			.collect(Collectors.toList());
	}

	private KraftwertEintrag konvertiere(final Kraftwert kraftwert)
	{
		return new KraftwertEintrag(
			kraftwert.getId(),
			kraftwert.getBenutzer().getVorname(),
			kraftwert.getBenutzer().getNachname(),
			kraftwert.getUebung().getName(),
			kraftwert.getMaximum());
	}
}
