package de.justinharder.trainharder.model.services;

import com.google.common.base.Preconditions;
import de.justinharder.trainharder.model.domain.Kraftwert;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.model.domain.enums.Wiederholungen;
import de.justinharder.trainharder.model.domain.exceptions.BenutzerNichtGefundenException;
import de.justinharder.trainharder.model.domain.exceptions.KraftwertNichtGefundenException;
import de.justinharder.trainharder.model.domain.exceptions.UebungNichtGefundenException;
import de.justinharder.trainharder.model.repository.BenutzerRepository;
import de.justinharder.trainharder.model.repository.KraftwertRepository;
import de.justinharder.trainharder.model.repository.UebungRepository;
import de.justinharder.trainharder.model.services.mapper.KraftwertDtoMapper;
import de.justinharder.trainharder.view.dto.KraftwertDto;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class KraftwertService
{
	private static final String ID = "der ID";
	private static final String DATUMSFORMAT = "dd.MM.yyyy";

	private final KraftwertRepository kraftwertRepository;
	private final BenutzerRepository benutzerRepository;
	private final UebungRepository uebungRepository;
	private final KraftwertDtoMapper kraftwertDtoMapper;

	@Inject
	public KraftwertService(
		final KraftwertRepository kraftwertRepository,
		final BenutzerRepository benutzerRepository,
		final UebungRepository uebungRepository,
		final KraftwertDtoMapper kraftwertDtoMapper)
	{
		this.kraftwertRepository = kraftwertRepository;
		this.benutzerRepository = benutzerRepository;
		this.uebungRepository = uebungRepository;
		this.kraftwertDtoMapper = kraftwertDtoMapper;
	}

	public List<KraftwertDto> ermittleAlleZuBenutzer(final String benutzerId)
	{
		Preconditions.checkNotNull(benutzerId, "Die Ermittlung der Kraftwerte benötigt eine gültige BenutzerID!");

		return kraftwertDtoMapper.konvertiereAlle(
			kraftwertRepository.ermittleAlleZuBenutzer(new Primaerschluessel(benutzerId)));
	}

	public KraftwertDto ermittleZuId(final String id) throws KraftwertNichtGefundenException
	{
		Preconditions.checkNotNull(id, "Die Ermittlung des Kraftwerts benötigt eine gültige KraftwertID!");

		return kraftwertRepository.ermittleZuId(new Primaerschluessel(id))
			.map(kraftwertDtoMapper::konvertiere)
			.orElseThrow(FehlermeldungService.wirfKraftwertNichtGefundenException(ID, id));
	}

	public KraftwertDto speichereKraftwert(
		final KraftwertDto kraftwertDto,
		final String uebungId,
		final String benutzerId) throws UebungNichtGefundenException, BenutzerNichtGefundenException
	{
		Preconditions.checkNotNull(kraftwertDto,
			"Zur Erstellung des Kraftwerts wird ein gültiges KraftwertDto benötigt!");
		Preconditions.checkNotNull(uebungId, "Zur Erstellung des Kraftwerts wird eine gültige UebungID benötigt!");
		Preconditions.checkNotNull(benutzerId, "Zur Erstellung des Kraftwerts wird eine gültige BenutzerID benötigt!");

		final var uebung = uebungRepository.ermittleZuId(new Primaerschluessel(uebungId))
			.orElseThrow(FehlermeldungService.wirfUebungNichtGefundenException(ID, uebungId));
		final var benutzer = benutzerRepository.ermittleZuId(new Primaerschluessel(benutzerId))
			.orElseThrow(FehlermeldungService.wirfBenutzerNichtGefundenException(ID, benutzerId));

		return kraftwertDtoMapper.konvertiere(kraftwertRepository.speichereKraftwert(new Kraftwert(
			new Primaerschluessel(),
			kraftwertDto.getMaximum(),
			kraftwertDto.getKoerpergewicht(),
			LocalDate.parse(kraftwertDto.getDatum(), DateTimeFormatter.ofPattern(DATUMSFORMAT)),
			Wiederholungen.fromName(kraftwertDto.getWiederholungen()),
			uebung,
			benutzer)));
	}
}
