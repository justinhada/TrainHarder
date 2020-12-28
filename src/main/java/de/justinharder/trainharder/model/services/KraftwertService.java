package de.justinharder.trainharder.model.services;

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
import lombok.NonNull;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class KraftwertService
{
	private static final String ID = "der ID";
	private static final DateTimeFormatter DATUMSFORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

	private final KraftwertRepository kraftwertRepository;
	private final BenutzerRepository benutzerRepository;
	private final UebungRepository uebungRepository;
	private final KraftwertDtoMapper kraftwertDtoMapper;

	@Inject
	public KraftwertService(
		KraftwertRepository kraftwertRepository,
		BenutzerRepository benutzerRepository,
		UebungRepository uebungRepository,
		KraftwertDtoMapper kraftwertDtoMapper)
	{
		this.kraftwertRepository = kraftwertRepository;
		this.benutzerRepository = benutzerRepository;
		this.uebungRepository = uebungRepository;
		this.kraftwertDtoMapper = kraftwertDtoMapper;
	}

	public List<KraftwertDto> ermittleAlleZuBenutzer(@NonNull String benutzerId)
	{
		return kraftwertDtoMapper
			.mappeAlle(kraftwertRepository.ermittleAlleZuBenutzer(new Primaerschluessel(benutzerId)));
	}

	public KraftwertDto ermittleZuId(@NonNull String id) throws KraftwertNichtGefundenException
	{
		return kraftwertRepository.ermittleZuId(new Primaerschluessel(id))
			.map(kraftwertDtoMapper::mappe)
			.orElseThrow(FehlermeldungService.wirfKraftwertNichtGefundenException(ID, id));
	}

	public KraftwertDto speichereKraftwert(@NonNull KraftwertDto kraftwertDto, @NonNull String uebungId,
		@NonNull String benutzerId) throws UebungNichtGefundenException, BenutzerNichtGefundenException
	{
		var uebung = uebungRepository.ermittleZuId(new Primaerschluessel(uebungId))
			.orElseThrow(FehlermeldungService.wirfUebungNichtGefundenException(ID, uebungId));
		var benutzer = benutzerRepository.ermittleZuId(new Primaerschluessel(benutzerId))
			.orElseThrow(FehlermeldungService.wirfBenutzerNichtGefundenException(ID, benutzerId));

		return kraftwertDtoMapper.mappe(kraftwertRepository.speichereKraftwert(new Kraftwert(
			new Primaerschluessel(),
			new BigDecimal(kraftwertDto.getGewicht()),
			new BigDecimal(kraftwertDto.getKoerpergewicht()),
			LocalDate.parse(kraftwertDto.getDatum(), DATUMSFORMAT),
			Wiederholungen.zuWert(kraftwertDto.getWiederholungen()),
			uebung,
			benutzer)));
	}
}
