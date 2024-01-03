package de.justinharder.trainharder.domain.services;

import de.justinharder.trainharder.domain.model.Benutzer;
import de.justinharder.trainharder.domain.model.embeddables.Benutzerangabe;
import de.justinharder.trainharder.domain.model.embeddables.Name;
import de.justinharder.trainharder.domain.model.embeddables.Primaerschluessel;
import de.justinharder.trainharder.domain.model.enums.*;
import de.justinharder.trainharder.domain.model.exceptions.AuthentifizierungNichtGefundenException;
import de.justinharder.trainharder.domain.model.exceptions.BenutzerNichtGefundenException;
import de.justinharder.trainharder.domain.repository.AuthentifizierungRepository;
import de.justinharder.trainharder.domain.repository.BenutzerRepository;
import de.justinharder.trainharder.domain.services.mapper.BenutzerDtoMapper;
import de.justinharder.trainharder.domain.services.dto.BenutzerDto;
import de.justinharder.trainharder.domain.services.dto.Benutzerdaten;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import lombok.NonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Dependent
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
