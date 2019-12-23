package de.justinharder.powerlifting.model.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import de.justinharder.powerlifting.model.domain.Benutzer;
import de.justinharder.powerlifting.model.domain.dto.BenutzerEintrag;
import de.justinharder.powerlifting.model.repository.BenutzerRepository;

public class BenutzerService
{
	private final BenutzerRepository benutzerRepository;

	@Inject
	public BenutzerService(final BenutzerRepository benutzerRepository)
	{
		this.benutzerRepository = benutzerRepository;
	}

	public List<BenutzerEintrag> ermittleAlle()
	{
		return konvertiere(benutzerRepository.ermittleAlle());
	}

	private List<BenutzerEintrag> konvertiere(final List<Benutzer> alleBenutzer)
	{
		return alleBenutzer.stream()
			.map(benutzer -> new BenutzerEintrag(
				benutzer.getId(),
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
				benutzer.getRegenerationsfaehigkeit()))
			.collect(Collectors.toList());
	}
}
