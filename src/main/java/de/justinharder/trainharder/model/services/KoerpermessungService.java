package de.justinharder.trainharder.model.services;

import de.justinharder.trainharder.model.domain.Koerpermessung;
import de.justinharder.trainharder.model.domain.embeddables.Koerpermasse;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.model.domain.exceptions.BenutzerNichtGefundenException;
import de.justinharder.trainharder.model.domain.exceptions.KoerpermessungNichtGefundenException;
import de.justinharder.trainharder.model.repository.BenutzerRepository;
import de.justinharder.trainharder.model.repository.KoerpermessungRepository;
import de.justinharder.trainharder.model.services.mapper.KoerpermessungDtoMapper;
import de.justinharder.trainharder.view.dto.Koerpermessdaten;
import de.justinharder.trainharder.view.dto.KoerpermessungDto;
import jakarta.inject.Inject;
import lombok.NonNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class KoerpermessungService
{
	private static final String ID = "der ID";

	private final KoerpermessungRepository koerpermessungRepository;
	private final BenutzerRepository benutzerRepository;
	private final KoerpermessungDtoMapper koerpermessungDtoMapper;

	@Inject
	public KoerpermessungService(KoerpermessungRepository koerpermessungRepository, BenutzerRepository benutzerRepository, KoerpermessungDtoMapper koerpermessungDtoMapper)
	{
		this.koerpermessungRepository = koerpermessungRepository;
		this.benutzerRepository = benutzerRepository;
		this.koerpermessungDtoMapper = koerpermessungDtoMapper;
	}

	public List<KoerpermessungDto> ermittleAlleZuBenutzer(@NonNull String benutzerId)
	{
		return koerpermessungDtoMapper.mappeAlle(koerpermessungRepository.ermittleAlleZuBenutzer(new Primaerschluessel(benutzerId)));
	}

	public KoerpermessungDto ermittleZuId(@NonNull String id) throws KoerpermessungNichtGefundenException
	{
		return koerpermessungRepository.ermittleZuId(new Primaerschluessel(id))
			.map(koerpermessungDtoMapper::mappe)
			.orElseThrow(FehlermeldungService.wirfKoerpermessungNichtGefundenException(ID, id));
	}

	public KoerpermessungDto erstelleKoerpermessung(@NonNull Koerpermessdaten koerpermessdaten, @NonNull String benutzerId) throws BenutzerNichtGefundenException
	{
		var benutzer = benutzerRepository.ermittleZuId(new Primaerschluessel(benutzerId))
			.orElseThrow(FehlermeldungService.wirfBenutzerNichtGefundenException(ID, benutzerId));

		return koerpermessungDtoMapper.mappe(koerpermessungRepository.speichereKoerpermessung(new Koerpermessung(
			new Primaerschluessel(),
			LocalDate.parse(koerpermessdaten.getDatum(), DateTimeFormatter.ISO_DATE),
			new Koerpermasse(
				new BigDecimal(koerpermessdaten.getKoerpergroesse()),
				BigDecimal.valueOf(koerpermessdaten.getKoerpergewicht()),
				BigDecimal.valueOf(koerpermessdaten.getKoerperfettAnteil())),
			koerpermessdaten.getKalorieneinnahme(),
			koerpermessdaten.getKalorienverbrauch(),
			benutzer)));
	}

	public KoerpermessungDto aktualisiereKoerpermessung(@NonNull String id, @NonNull Koerpermessdaten koerpermessdaten) throws KoerpermessungNichtGefundenException
	{
		var koerpermessung = koerpermessungRepository.ermittleZuId(new Primaerschluessel(id))
			.orElseThrow(FehlermeldungService.wirfKoerpermessungNichtGefundenException(ID, id));

		return koerpermessungDtoMapper.mappe(koerpermessungRepository.speichereKoerpermessung(koerpermessung
			.setDatum(LocalDate.parse(koerpermessdaten.getDatum(), DateTimeFormatter.ISO_DATE))
			.setKoerpermasse(new Koerpermasse(
				new BigDecimal(koerpermessdaten.getKoerpergroesse()),
				BigDecimal.valueOf(koerpermessdaten.getKoerpergewicht()),
				BigDecimal.valueOf(koerpermessdaten.getKoerperfettAnteil())))
			.setKalorieneinnahme(koerpermessdaten.getKalorieneinnahme())
			.setKalorienverbrauch(koerpermessdaten.getKalorienverbrauch())));
	}
}
