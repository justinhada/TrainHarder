package de.justinharder.powerlifting.model.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import de.justinharder.powerlifting.model.domain.Benutzer;
import de.justinharder.powerlifting.model.domain.dto.BenutzerEintrag;
import de.justinharder.powerlifting.model.domain.enums.Doping;
import de.justinharder.powerlifting.model.domain.enums.Erfahrung;
import de.justinharder.powerlifting.model.domain.enums.Ernaehrung;
import de.justinharder.powerlifting.model.domain.enums.Geschlecht;
import de.justinharder.powerlifting.model.domain.enums.Kraftlevel;
import de.justinharder.powerlifting.model.domain.enums.Regenerationsfaehigkeit;
import de.justinharder.powerlifting.model.domain.enums.Schlafqualitaet;
import de.justinharder.powerlifting.model.domain.enums.Stress;
import de.justinharder.powerlifting.model.repository.BenutzerRepository;

public class BenutzerService
{
	private final BenutzerRepository benutzerRepository;

	@Inject
	public BenutzerService(final BenutzerRepository benutzerRepository)
	{
		this.benutzerRepository = benutzerRepository;
	}

	public BenutzerEintrag erstelleBenutzer(final String vorname, final String nachname, final int koerpergewicht,
		final int koerpergroesse, final int lebensalter, final String geschlecht, final String erfahrung,
		final String ernaehrung, final String schlafqualitaet, final String stress, final String doping,
		final String regenerationsfaehigkeit)
	{
		final var benutzer = new Benutzer(
			vorname,
			nachname,
			koerpergewicht,
			koerpergroesse,
			lebensalter,
			Kraftlevel.CLASS_5,
			Geschlecht.fromGeschlechtOption(geschlecht),
			Erfahrung.fromErfahrungOption(erfahrung),
			Ernaehrung.fromErnaehrungOption(ernaehrung),
			Schlafqualitaet.fromSchlafqualitaetOption(schlafqualitaet),
			Stress.fromStressOption(stress),
			Doping.fromDopingOption(doping),
			Regenerationsfaehigkeit.fromRegenerationsfaehigkeitOption(regenerationsfaehigkeit));
		benutzerRepository.erstelleBenutzer(benutzer);

		return konvertiere(benutzer);
	}

	public List<BenutzerEintrag> ermittleAlle()
	{
		return konvertiereAlle(benutzerRepository.ermittleAlle());
	}

	private List<BenutzerEintrag> konvertiereAlle(final List<Benutzer> alleBenutzer)
	{
		return alleBenutzer.stream()
			.map(benutzer -> konvertiere(benutzer))
			.collect(Collectors.toList());
	}

	private BenutzerEintrag konvertiere(final Benutzer benutzer)
	{
		return new BenutzerEintrag(
			benutzer.getVorname(),
			benutzer.getNachname(),
			benutzer.getKoerpergewicht(),
			benutzer.getKoerpergroesse(),
			benutzer.getLebensalter(),
			benutzer.getKraftlevel(),
			benutzer.getGeschlecht(),
			benutzer.getErfahrung(),
			benutzer.getErnaehrung(),
			benutzer.getSchlafqualitaet(),
			benutzer.getStress(),
			benutzer.getDoping(),
			benutzer.getRegenerationsfaehigkeit());
	}
}
