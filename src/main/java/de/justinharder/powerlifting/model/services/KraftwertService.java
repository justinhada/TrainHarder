package de.justinharder.powerlifting.model.services;

import java.util.List;

import javax.inject.Inject;

import de.justinharder.powerlifting.model.domain.Benutzer;
import de.justinharder.powerlifting.model.domain.Kraftwert;
import de.justinharder.powerlifting.model.domain.dto.KraftwertEintrag;
import de.justinharder.powerlifting.model.domain.enums.Wiederholungen;
import de.justinharder.powerlifting.model.domain.exceptions.KraftwertNichtGefundenException;
import de.justinharder.powerlifting.model.repository.BenutzerRepository;
import de.justinharder.powerlifting.model.repository.KraftwertRepository;
import de.justinharder.powerlifting.model.repository.UebungRepository;

public class KraftwertService
{
	private final KraftwertRepository kraftwertRepository;
	private final BenutzerRepository benutzerRepository;
	private final UebungRepository uebungRepository;

	@Inject
	public KraftwertService(final KraftwertRepository kraftwertRepository, final BenutzerRepository benutzerRepository,
		final UebungRepository uebungRepository)
	{
		this.kraftwertRepository = kraftwertRepository;
		this.benutzerRepository = benutzerRepository;
		this.uebungRepository = uebungRepository;
	}

	public List<KraftwertEintrag> ermittleAlle()
	{
		return Konvertierer.konvertiereAlleZuKraftwertEintrag(kraftwertRepository.ermittleAlle());
	}

	public List<KraftwertEintrag> ermittleAlleZuBenutzer(final Benutzer benutzer)
	{
		return Konvertierer.konvertiereAlleZuKraftwertEintrag(kraftwertRepository.ermittleAlleZuBenutzer(benutzer));
	}

	public KraftwertEintrag ermittleZuId(final String id) throws KraftwertNichtGefundenException
	{
		final var kraftwert = kraftwertRepository.ermittleZuId(Integer.valueOf(id));
		if (kraftwert == null)
		{
			throw new KraftwertNichtGefundenException("Der Kraftwert mit der ID \"" + id + "\" existiert nicht!");
		}
		return Konvertierer.konvertiereZuKraftwertEintrag(kraftwert);
	}

	public void erstelleKraftwert(
		final KraftwertEintrag kraftwertEintrag,
		final String benutzerId,
		final String uebungId)
	{
		final var kraftwert = new Kraftwert(
			uebungRepository.ermittleZuId(Integer.valueOf(uebungId)),
			benutzerRepository.ermittleZuId(Integer.valueOf(benutzerId)),
			kraftwertEintrag.getMaximum(),
			kraftwertEintrag.getKoerpergewicht(),
			kraftwertEintrag.getDatum(),
			Wiederholungen.fromName(kraftwertEintrag.getWiederholungen()));
		kraftwertRepository.erstelleKraftwert(kraftwert);
	}
}
