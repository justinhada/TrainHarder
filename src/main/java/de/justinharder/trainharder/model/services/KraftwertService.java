package de.justinharder.trainharder.model.services;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.inject.Inject;

import de.justinharder.trainharder.model.domain.Kraftwert;
import de.justinharder.trainharder.model.domain.dto.KraftwertEintrag;
import de.justinharder.trainharder.model.domain.enums.Wiederholungen;
import de.justinharder.trainharder.model.domain.exceptions.KraftwertNichtGefundenException;
import de.justinharder.trainharder.model.repository.BenutzerRepository;
import de.justinharder.trainharder.model.repository.KraftwertRepository;
import de.justinharder.trainharder.model.repository.UebungRepository;

public class KraftwertService implements Serializable
{
	private static final long serialVersionUID = -5443953675613638545L;

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

	public List<KraftwertEintrag> ermittleAlleZuBenutzer(final String benutzerId)
	{
		return Konvertierer
			.konvertiereAlleZuKraftwertEintrag(kraftwertRepository.ermittleAlleZuBenutzer(Integer.valueOf(benutzerId)));
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
		final String uebungId,
		final String benutzerId)
	{
		final var kraftwert = new Kraftwert(
			kraftwertEintrag.getMaximum(),
			kraftwertEintrag.getKoerpergewicht(),
			LocalDate.parse(kraftwertEintrag.getDatum(), DateTimeFormatter.ofPattern("dd.MM.yyyy")),
			Wiederholungen.fromName(kraftwertEintrag.getWiederholungen()),
			uebungRepository.ermittleZuId(Integer.valueOf(uebungId)),
			benutzerRepository.ermittleZuId(Integer.valueOf(benutzerId)));
		kraftwertRepository.erstelleKraftwert(kraftwert);
	}
}
