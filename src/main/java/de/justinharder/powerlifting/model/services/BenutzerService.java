package de.justinharder.powerlifting.model.services;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import de.justinharder.powerlifting.model.domain.Anmeldedaten;
import de.justinharder.powerlifting.model.domain.Benutzer;
import de.justinharder.powerlifting.model.domain.Koerpermessung;
import de.justinharder.powerlifting.model.domain.dto.AnmeldedatenEintrag;
import de.justinharder.powerlifting.model.domain.dto.BenutzerEintrag;
import de.justinharder.powerlifting.model.domain.enums.Doping;
import de.justinharder.powerlifting.model.domain.enums.Erfahrung;
import de.justinharder.powerlifting.model.domain.enums.Ernaehrung;
import de.justinharder.powerlifting.model.domain.enums.Geschlecht;
import de.justinharder.powerlifting.model.domain.enums.Regenerationsfaehigkeit;
import de.justinharder.powerlifting.model.domain.enums.Schlafqualitaet;
import de.justinharder.powerlifting.model.domain.enums.Stress;
import de.justinharder.powerlifting.model.domain.exceptions.BenutzerNichtGefundenException;
import de.justinharder.powerlifting.model.repository.BenutzerRepository;

public class BenutzerService implements Serializable
{
	private static final long serialVersionUID = 4793689097189495259L;

	private final BenutzerRepository benutzerRepository;

	@Inject
	public BenutzerService(final BenutzerRepository benutzerRepository)
	{
		this.benutzerRepository = benutzerRepository;
	}

	public List<BenutzerEintrag> ermittleAlle()
	{
		return konvertiereAlle(benutzerRepository.ermittleAlle());
	}

	public BenutzerEintrag ermittleZuId(final String id) throws BenutzerNichtGefundenException
	{
		final var benutzer = benutzerRepository.ermittleZuId(Integer.valueOf(id));
		if (benutzer == null)
		{
			throw new BenutzerNichtGefundenException("Der Benutzer mit der ID \"" + id + "\" existiert nicht!");
		}
		return konvertiere(benutzer);
	}

	public List<BenutzerEintrag> ermittleZuNachname(final String nachname) throws BenutzerNichtGefundenException
	{
		final var alleBenutzer = benutzerRepository.ermittleAlleZuNachname(nachname);
		if (alleBenutzer == null)
		{
			throw new BenutzerNichtGefundenException(
				"Es wurde kein Benutzer mit dem Nachnamen \"" + nachname + "\" gefunden!");
		}
		return konvertiereAlle(alleBenutzer);
	}

	public void erstelleBenutzer(final BenutzerEintrag benutzerEintrag, final AnmeldedatenEintrag anmeldedatenEintrag)
	{
		final var benutzer = new Benutzer(
			benutzerEintrag.getVorname(),
			benutzerEintrag.getNachname(),
			benutzerEintrag.getLebensalter(),
			Geschlecht.fromGeschlechtOption(benutzerEintrag.getGeschlecht()),
			Erfahrung.fromErfahrungOption(benutzerEintrag.getErfahrung()),
			Ernaehrung.fromErnaehrungOption(benutzerEintrag.getErnaehrung()),
			Schlafqualitaet.fromSchlafqualitaetOption(benutzerEintrag.getSchlafqualitaet()),
			Stress.fromStressOption(benutzerEintrag.getStress()),
			Doping.fromDopingOption(benutzerEintrag.getDoping()),
			Regenerationsfaehigkeit.fromRegenerationsfaehigkeitOption(benutzerEintrag.getRegenerationsfaehigkeit()),
			new Anmeldedaten(
				anmeldedatenEintrag.getMail(),
				anmeldedatenEintrag.getBenutzername(),
				anmeldedatenEintrag.getPasswort()));
		final var koerpermessung = new Koerpermessung();
		koerpermessung.setKoerpergewicht(benutzerEintrag.getKoerpergewicht());
		benutzer.fuegeKoerpermessungHinzu(koerpermessung);
		benutzerRepository.erstelleBenutzer(benutzer);
	}

	private List<BenutzerEintrag> konvertiereAlle(final List<Benutzer> alleBenutzer)
	{
		return alleBenutzer.stream()
			.map(this::konvertiere)
			.collect(Collectors.toList());
	}

	private BenutzerEintrag konvertiere(final Benutzer benutzer)
	{
		return new BenutzerEintrag(
			benutzer.getId(),
			benutzer.getVorname(),
			benutzer.getNachname(),
			benutzer.getAktuelleKoerpergroesse(),
			benutzer.getAktuellesKoerpergewicht(),
			benutzer.getLebensalter(),
			benutzer.getKraftlevel().name(),
			benutzer.getGeschlecht().name(),
			benutzer.getErfahrung().name(),
			benutzer.getErnaehrung().name(),
			benutzer.getSchlafqualitaet().name(),
			benutzer.getStress().name(),
			benutzer.getDoping().name(),
			benutzer.getRegenerationsfaehigkeit().name());
	}
}
