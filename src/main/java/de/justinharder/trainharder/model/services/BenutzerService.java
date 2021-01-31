package de.justinharder.trainharder.model.services;

import de.justinharder.trainharder.model.domain.Benutzer;
import de.justinharder.trainharder.model.domain.embeddables.Benutzerangabe;
import de.justinharder.trainharder.model.domain.embeddables.Name;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.model.domain.enums.*;
import de.justinharder.trainharder.model.domain.exceptions.AuthentifizierungNichtGefundenException;
import de.justinharder.trainharder.model.domain.exceptions.BenutzerNichtGefundenException;
import de.justinharder.trainharder.model.repository.AuthentifizierungRepository;
import de.justinharder.trainharder.model.repository.BenutzerRepository;
import de.justinharder.trainharder.model.services.mapper.BenutzerDtoMapper;
import de.justinharder.trainharder.view.dto.BenutzerDto;
import de.justinharder.trainharder.view.dto.Benutzerdaten;
import lombok.NonNull;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BenutzerService
{
	private static final String ID = "der ID";

	private final BenutzerRepository benutzerRepository;
	private final AuthentifizierungRepository authentifizierungRepository;
	private final BenutzerDtoMapper benutzerDtoMapper;

	@Inject
	public BenutzerService(BenutzerRepository benutzerRepository, AuthentifizierungRepository authentifizierungRepository, BenutzerDtoMapper benutzerDtoMapper)
	{
		this.benutzerRepository = benutzerRepository;
		this.authentifizierungRepository = authentifizierungRepository;
		this.benutzerDtoMapper = benutzerDtoMapper;
	}

	public List<BenutzerDto> ermittleAlle()
	{
		return benutzerDtoMapper.mappeAlle(benutzerRepository.ermittleAlle());
	}

	public BenutzerDto ermittleZuId(@NonNull String id) throws BenutzerNichtGefundenException
	{
		return benutzerRepository.ermittleZuId(new Primaerschluessel(id))
			.map(benutzerDtoMapper::mappe)
			.orElseThrow(FehlermeldungService.wirfBenutzerNichtGefundenException(ID, id));
	}

	public BenutzerDto ermittleZuAuthentifizierung(@NonNull String authentifizierungId) throws BenutzerNichtGefundenException
	{
		return benutzerRepository.ermittleZuAuthentifizierung(new Primaerschluessel(authentifizierungId))
			.map(benutzerDtoMapper::mappe)
			.orElseThrow(FehlermeldungService
				.wirfBenutzerNichtGefundenException("der AuthentifizierungID", authentifizierungId));
	}

	public BenutzerDto erstelleBenutzer(@NonNull Benutzerdaten benutzerdaten, @NonNull String authentifizierungId) throws AuthentifizierungNichtGefundenException
	{
		var authentifizierung = authentifizierungRepository.ermittleZuId(new Primaerschluessel(authentifizierungId))
			.orElseThrow(FehlermeldungService.wirfAuthentifizierungNichtGefundenException(ID, authentifizierungId));

		var benutzer = benutzerRepository.speichereBenutzer(new Benutzer(
			new Primaerschluessel(),
			new Name(benutzerdaten.getVorname(), benutzerdaten.getNachname()),
			LocalDate.parse(benutzerdaten.getGeburtsdatum(), DateTimeFormatter.ISO_DATE),
			new Benutzerangabe(
				Geschlecht.zuWert(benutzerdaten.getGeschlecht()),
				Erfahrung.zuWert(benutzerdaten.getErfahrung()),
				Ernaehrung.zuWert(benutzerdaten.getErnaehrung()),
				Schlafqualitaet.zuWert(benutzerdaten.getSchlafqualitaet()),
				Stress.zuWert(benutzerdaten.getStress()),
				Doping.zuWert(benutzerdaten.getDoping()),
				Regenerationsfaehigkeit.zuWert(benutzerdaten.getRegenerationsfaehigkeit())),
			authentifizierung));

		authentifizierungRepository.speichereAuthentifizierung(authentifizierung);

		return benutzerDtoMapper.mappe(benutzer);
	}

	public BenutzerDto aktualisiereBenutzer(@NonNull String id, @NonNull Benutzerdaten benutzerdaten) throws BenutzerNichtGefundenException
	{
		var benutzer = benutzerRepository.ermittleZuId(new Primaerschluessel(id))
			.orElseThrow(FehlermeldungService.wirfBenutzerNichtGefundenException(ID, id));

		return benutzerDtoMapper.mappe(benutzerRepository.speichereBenutzer(benutzer
			.setName(new Name(benutzerdaten.getVorname(), benutzerdaten.getNachname()))
			.setGeburtsdatum(LocalDate.parse(benutzerdaten.getGeburtsdatum(), DateTimeFormatter.ISO_DATE))
			.setBenutzerangabe(new Benutzerangabe(
				Geschlecht.zuWert(benutzerdaten.getGeschlecht()),
				Erfahrung.zuWert(benutzerdaten.getErfahrung()),
				Ernaehrung.zuWert(benutzerdaten.getErnaehrung()),
				Schlafqualitaet.zuWert(benutzerdaten.getSchlafqualitaet()),
				Stress.zuWert(benutzerdaten.getStress()),
				Doping.zuWert(benutzerdaten.getDoping()),
				Regenerationsfaehigkeit.zuWert(benutzerdaten.getRegenerationsfaehigkeit())))));
	}
}