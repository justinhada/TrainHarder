package de.justinharder.trainharder.domain.services;

import de.justinharder.trainharder.domain.model.Kraftwert;
import de.justinharder.trainharder.domain.model.embeddables.Primaerschluessel;
import de.justinharder.trainharder.domain.model.enums.Wiederholungen;
import de.justinharder.trainharder.domain.model.exceptions.BenutzerNichtGefundenException;
import de.justinharder.trainharder.domain.model.exceptions.KraftwertNichtGefundenException;
import de.justinharder.trainharder.domain.model.exceptions.UebungNichtGefundenException;
import de.justinharder.trainharder.domain.repository.BenutzerRepository;
import de.justinharder.trainharder.domain.repository.KraftwertRepository;
import de.justinharder.trainharder.domain.repository.UebungRepository;
import de.justinharder.trainharder.domain.services.mapper.KraftwertDtoMapper;
import de.justinharder.trainharder.domain.services.dto.KraftwertDto;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import lombok.NonNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Dependent
public class KraftwertService
{
	private static final String ID = "der ID";
	private static final DateTimeFormatter DATUMSFORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

	private final KraftwertRepository kraftwertRepository;
	private final BenutzerRepository benutzerRepository;
	private final UebungRepository uebungRepository;
	private final KraftwertDtoMapper kraftwertDtoMapper;

	@Inject
	public KraftwertService(KraftwertRepository kraftwertRepository, BenutzerRepository benutzerRepository, UebungRepository uebungRepository, KraftwertDtoMapper kraftwertDtoMapper)
	{
		this.kraftwertRepository = kraftwertRepository;
		this.benutzerRepository = benutzerRepository;
		this.uebungRepository = uebungRepository;
		this.kraftwertDtoMapper = kraftwertDtoMapper;
	}

	public List<KraftwertDto> ermittleAlleZuBenutzer(@NonNull String benutzerId)
	{
		return kraftwertDtoMapper.mappeAlle(kraftwertRepository.ermittleAlleZuBenutzer(new Primaerschluessel(benutzerId)));
	}

	public KraftwertDto ermittleZuId(@NonNull String id) throws KraftwertNichtGefundenException
	{
		return kraftwertRepository.ermittleZuId(new Primaerschluessel(id))
			.map(kraftwertDtoMapper::mappe)
			.orElseThrow(FehlermeldungService.wirfKraftwertNichtGefundenException(ID, id));
	}

	public KraftwertDto speichereKraftwert(@NonNull KraftwertDto kraftwertDto, @NonNull String uebungId, @NonNull String benutzerId) throws UebungNichtGefundenException, BenutzerNichtGefundenException
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
