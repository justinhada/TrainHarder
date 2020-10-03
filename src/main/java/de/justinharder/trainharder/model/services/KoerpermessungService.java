package de.justinharder.trainharder.model.services;

import com.google.common.base.Preconditions;
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

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class KoerpermessungService
{
	public static final String ID = "der ID";
	private final KoerpermessungRepository koerpermessungRepository;
	private final BenutzerRepository benutzerRepository;
	private final KoerpermessungDtoMapper koerpermessungDtoMapper;

	@Inject
	public KoerpermessungService(
		final KoerpermessungRepository koerpermessungRepository,
		final BenutzerRepository benutzerRepository,
		final KoerpermessungDtoMapper koerpermessungDtoMapper)
	{
		this.koerpermessungRepository = koerpermessungRepository;
		this.benutzerRepository = benutzerRepository;
		this.koerpermessungDtoMapper = koerpermessungDtoMapper;
	}

	public List<KoerpermessungDto> ermittleAlleZuBenutzer(final String benutzerId)
	{
		Preconditions.checkNotNull(benutzerId, "Die Ermittlung der Koerpermessungen benötigt eine gültige BenutzerID!");

		return koerpermessungDtoMapper
			.konvertiereAlle(koerpermessungRepository.ermittleAlleZuBenutzer(new Primaerschluessel(benutzerId)));
	}

	public KoerpermessungDto ermittleZuId(final String id) throws KoerpermessungNichtGefundenException
	{
		Preconditions.checkNotNull(id, "Die Ermittlung der Koerpermessung benötigt eine gültige ID!");

		return koerpermessungRepository.ermittleZuId(new Primaerschluessel(id))
			.map(koerpermessungDtoMapper::konvertiere)
			.orElseThrow(FehlermeldungService.wirfKoerpermessungNichtGefundenException(ID, id));
	}

	public KoerpermessungDto erstelleKoerpermessung(final Koerpermessdaten koerpermessdaten, final String benutzerId)
		throws BenutzerNichtGefundenException
	{
		Preconditions.checkNotNull(koerpermessdaten,
			"Die Erstellung der Koerpermessung benötigt gültige Koerpermessdaten!");
		Preconditions.checkNotNull(benutzerId, "Die Erstellung der Koerpermessungen benötigt eine gültige BenutzerID!");

		final var benutzer = benutzerRepository.ermittleZuId(new Primaerschluessel(benutzerId))
			.orElseThrow(FehlermeldungService.wirfBenutzerNichtGefundenException(ID, benutzerId));

		return koerpermessungDtoMapper.konvertiere(koerpermessungRepository.speichereKoerpermessung(new Koerpermessung(
			new Primaerschluessel(),
			LocalDate.parse(koerpermessdaten.getDatum(), DateTimeFormatter.ISO_DATE),
			new Koerpermasse(
				koerpermessdaten.getKoerpergroesse(),
				koerpermessdaten.getKoerpergewicht(),
				koerpermessdaten.getKoerperfettAnteil()),
			koerpermessdaten.getKalorieneinnahme(),
			koerpermessdaten.getKalorienverbrauch(),
			benutzer)));
	}

	public KoerpermessungDto aktualisiereKoerpermessung(final String id, final Koerpermessdaten koerpermessdaten)
		throws KoerpermessungNichtGefundenException
	{
		Preconditions.checkNotNull(id, "Die Aktualisierung der Koerpermessungen benötigt eine gültige ID!");
		Preconditions.checkNotNull(koerpermessdaten,
			"Die Aktualisierung der Koerpermessung benötigt gültige Koerpermessdaten!");

		final var koerpermessung = koerpermessungRepository.ermittleZuId(new Primaerschluessel(id))
			.orElseThrow(FehlermeldungService.wirfKoerpermessungNichtGefundenException(ID, id));

		return koerpermessungDtoMapper.konvertiere(koerpermessungRepository.speichereKoerpermessung(koerpermessung
			.setDatum(LocalDate.parse(koerpermessdaten.getDatum(), DateTimeFormatter.ISO_DATE))
			.setKoerpermasse(new Koerpermasse(
				koerpermessdaten.getKoerpergroesse(),
				koerpermessdaten.getKoerpergewicht(),
				koerpermessdaten.getKoerperfettAnteil()))
			.setKalorieneinnahme(koerpermessdaten.getKalorieneinnahme())
			.setKalorienverbrauch(koerpermessdaten.getKalorienverbrauch())));
	}
}
