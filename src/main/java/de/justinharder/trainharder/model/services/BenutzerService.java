package de.justinharder.trainharder.model.services;

import com.google.common.base.Preconditions;
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

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BenutzerService
{
	private final BenutzerRepository benutzerRepository;
	private final AuthentifizierungRepository authentifizierungRepository;
	private final BenutzerDtoMapper benutzerDtoMapper;

	@Inject
	public BenutzerService(
		final BenutzerRepository benutzerRepository,
		final AuthentifizierungRepository authentifizierungRepository,
		final BenutzerDtoMapper benutzerDtoMapper)
	{
		this.benutzerRepository = benutzerRepository;
		this.authentifizierungRepository = authentifizierungRepository;
		this.benutzerDtoMapper = benutzerDtoMapper;
	}

	public List<BenutzerDto> ermittleAlle()
	{
		return benutzerDtoMapper.konvertiereAlle(benutzerRepository.ermittleAlle());
	}

	public BenutzerDto ermittleZuId(final String id) throws BenutzerNichtGefundenException
	{
		Preconditions.checkNotNull(id, "Die Ermittlung des Benutzers benötigt eine gültige BenutzerID!");

		return benutzerRepository.ermittleZuId(new Primaerschluessel(id))
			.map(benutzerDtoMapper::konvertiere)
			.orElseThrow(FehlermeldungService.wirfBenutzerNichtGefundenException("der ID", id));
	}

	public BenutzerDto ermittleZuAuthentifizierung(final String authentifizierungId)
		throws BenutzerNichtGefundenException
	{
		Preconditions.checkNotNull(authentifizierungId,
			"Die Ermittlung des Benutzers benötigt eine gültige AuthentifizierungID!");

		return benutzerRepository.ermittleZuAuthentifizierung(new Primaerschluessel(authentifizierungId))
			.map(benutzerDtoMapper::konvertiere)
			.orElseThrow(FehlermeldungService
				.wirfBenutzerNichtGefundenException("der AuthentifizierungID", authentifizierungId));
	}

	public BenutzerDto erstelleBenutzer(final Benutzerdaten benutzerdaten, final String authentifizierungId)
		throws AuthentifizierungNichtGefundenException
	{
		Preconditions.checkNotNull(benutzerdaten, "Die Erstellung des Benutzers benötigt gültige Benutzerdaten!");
		Preconditions.checkNotNull(authentifizierungId,
			"Die Erstellung des Benutzers benötigt eine gültige AuthentifizierungID!");

		final var authentifizierung = authentifizierungRepository
			.ermittleZuId(new Primaerschluessel(authentifizierungId))
			.orElseThrow(
				FehlermeldungService.wirfAuthentifizierungNichtGefundenException("der ID", authentifizierungId));

		final var benutzer = benutzerRepository.speichereBenutzer(new Benutzer(
			new Primaerschluessel(),
			new Name(benutzerdaten.getVorname(), benutzerdaten.getNachname()),
			LocalDate.parse(benutzerdaten.getGeburtsdatum(), DateTimeFormatter.ISO_DATE),
			new Benutzerangabe(
				Geschlecht.fromGeschlechtOption(benutzerdaten.getGeschlecht()),
				Erfahrung.fromErfahrungOption(benutzerdaten.getErfahrung()),
				Ernaehrung.fromErnaehrungOption(benutzerdaten.getErnaehrung()),
				Schlafqualitaet.fromSchlafqualitaetOption(benutzerdaten.getSchlafqualitaet()),
				Stress.fromStressOption(benutzerdaten.getStress()),
				Doping.fromDopingOption(benutzerdaten.getDoping()),
				Regenerationsfaehigkeit.fromRegenerationsfaehigkeitOption(benutzerdaten.getRegenerationsfaehigkeit())),
			authentifizierung));

		authentifizierungRepository.speichereAuthentifizierung(authentifizierung);

		return benutzerDtoMapper.konvertiere(benutzer);
	}

	public BenutzerDto aktualisiereBenutzer(final String id, final Benutzerdaten benutzerdaten)
		throws BenutzerNichtGefundenException
	{
		Preconditions.checkNotNull(id, "Die Aktualisierung des Benutzers benötigt eine gültige ID!");
		Preconditions.checkNotNull(benutzerdaten, "Die Aktualisierung des Benutzers benötigt gültige Benutzerdaten!");

		final var benutzer = benutzerRepository
			.ermittleZuId(new Primaerschluessel(id))
			.orElseThrow(FehlermeldungService.wirfBenutzerNichtGefundenException("der ID", id));

		return benutzerDtoMapper.konvertiere(benutzerRepository.speichereBenutzer(benutzer
			.setName(new Name(benutzerdaten.getVorname(), benutzerdaten.getNachname()))
			.setGeburtsdatum(LocalDate.parse(benutzerdaten.getGeburtsdatum(), DateTimeFormatter.ISO_DATE))
			.setBenutzerangabe(new Benutzerangabe(
				Geschlecht.fromGeschlechtOption(benutzerdaten.getGeschlecht()),
				Erfahrung.fromErfahrungOption(benutzerdaten.getErfahrung()),
				Ernaehrung.fromErnaehrungOption(benutzerdaten.getErnaehrung()),
				Schlafqualitaet.fromSchlafqualitaetOption(benutzerdaten.getSchlafqualitaet()),
				Stress.fromStressOption(benutzerdaten.getStress()),
				Doping.fromDopingOption(benutzerdaten.getDoping()),
				Regenerationsfaehigkeit
					.fromRegenerationsfaehigkeitOption(benutzerdaten.getRegenerationsfaehigkeit())))));
	}
}
