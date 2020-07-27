package de.justinharder.trainharder.model.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.inject.Inject;

import com.google.common.base.Preconditions;

import de.justinharder.trainharder.model.domain.Benutzer;
import de.justinharder.trainharder.model.domain.embeddables.Benutzerangabe;
import de.justinharder.trainharder.model.domain.embeddables.Name;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.model.domain.enums.Doping;
import de.justinharder.trainharder.model.domain.enums.Erfahrung;
import de.justinharder.trainharder.model.domain.enums.Ernaehrung;
import de.justinharder.trainharder.model.domain.enums.Geschlecht;
import de.justinharder.trainharder.model.domain.enums.Regenerationsfaehigkeit;
import de.justinharder.trainharder.model.domain.enums.Schlafqualitaet;
import de.justinharder.trainharder.model.domain.enums.Stress;
import de.justinharder.trainharder.model.domain.exceptions.AuthentifizierungNichtGefundenException;
import de.justinharder.trainharder.model.domain.exceptions.BenutzerNichtGefundenException;
import de.justinharder.trainharder.model.repository.AuthentifizierungRepository;
import de.justinharder.trainharder.model.repository.BenutzerRepository;
import de.justinharder.trainharder.model.services.mapper.BenutzerDtoMapper;
import de.justinharder.trainharder.view.dto.BenutzerDto;
import de.justinharder.trainharder.view.dto.Benutzerdaten;

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

	public BenutzerDto ermittleZuId(final String id) throws BenutzerNichtGefundenException
	{
		Preconditions.checkNotNull(id, "Ermittlung des Benutzers benötigt eine gültige BenutzerID!");

		return benutzerRepository.ermittleZuId(new Primaerschluessel(id))
			.map(benutzerDtoMapper::konvertiere)
			.orElseThrow(() -> new BenutzerNichtGefundenException(
				"Der Benutzer mit der ID \"" + id + "\" existiert nicht!"));
	}

	public BenutzerDto ermittleZuAuthentifizierung(final String authentifizierungId)
		throws BenutzerNichtGefundenException
	{
		Preconditions.checkNotNull(authentifizierungId,
			"Ermittlung des Benutzers benötigt eine gültige AuthentifizierungID!");

		return benutzerRepository.ermittleZuAuthentifizierung(new Primaerschluessel(authentifizierungId))
			.map(benutzerDtoMapper::konvertiere)
			.orElseThrow(() -> new BenutzerNichtGefundenException(
				"Der Benutzer mit der AuthentifizierungID \"" + authentifizierungId + "\" existiert nicht!"));
	}

	public BenutzerDto erstelleBenutzer(final Benutzerdaten benutzerdaten, final String authentifizierungId)
		throws AuthentifizierungNichtGefundenException
	{
		Preconditions.checkNotNull(benutzerdaten, "Erstellung des Benutzers benötigt gültige Benutzerdaten!");
		Preconditions.checkNotNull(authentifizierungId,
			"Erstellung des Benutzers benötigt eine gültige AuthentifizierungID!");

		final var authentifizierung = authentifizierungRepository
			.ermittleZuId(new Primaerschluessel(authentifizierungId))
			.orElseThrow(() -> new AuthentifizierungNichtGefundenException(
				"Die Authentifizierung mit der ID \"" + authentifizierungId + "\" existiert nicht!"));

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
		Preconditions.checkNotNull(id, "Aktualisierung des Benutzers benötigt eine gültige ID!");
		Preconditions.checkNotNull(benutzerdaten, "Aktualisierung des Benutzers benötigt gültige Benutzerdaten!");

		final var benutzer = benutzerRepository
			.ermittleZuId(new Primaerschluessel(id))
			.orElseThrow(() -> new BenutzerNichtGefundenException(
				"Der Benutzer mit der ID \"" + id + "\" existiert nicht!"));

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
