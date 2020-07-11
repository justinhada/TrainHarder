package de.justinharder.trainharder.model.services;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.google.common.base.Preconditions;

import de.justinharder.trainharder.model.domain.Benutzer;
import de.justinharder.trainharder.model.domain.Primaerschluessel;
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
import de.justinharder.trainharder.view.dto.BenutzerDto;

public class BenutzerService implements Serializable
{
	private static final long serialVersionUID = 4793689097189495259L;

	private final BenutzerRepository benutzerRepository;
	private final AuthentifizierungRepository authentfizierungRepository;

	@Inject
	public BenutzerService(
		final BenutzerRepository benutzerRepository,
		final AuthentifizierungRepository authentifizierungRepository)
	{
		this.benutzerRepository = benutzerRepository;
		authentfizierungRepository = authentifizierungRepository;
	}

	public List<BenutzerDto> ermittleAlle()
	{
		return Konvertierer.konvertiereAlleZuBenutzerEintrag(benutzerRepository.ermittleAlle());
	}

	public BenutzerDto ermittleZuId(final String id) throws BenutzerNichtGefundenException
	{
		Preconditions.checkNotNull(id, "Ermittlung des Benutzers benötigt eine gültige BenutzerID!");

		return Konvertierer.konvertiereZuBenutzerEintrag(benutzerRepository
			.ermittleZuId(new Primaerschluessel(id))
			.orElseThrow(
				() -> new BenutzerNichtGefundenException("Der Benutzer mit der ID \"" + id + "\" existiert nicht!")));
	}

	public BenutzerDto ermittleZuAuthentifizierung(final String authentifizierungId)
		throws BenutzerNichtGefundenException
	{
		return Konvertierer.konvertiereZuBenutzerEintrag(benutzerRepository
			.ermittleZuAuthentifizierung(new Primaerschluessel(authentifizierungId))
			.orElseThrow(() -> new BenutzerNichtGefundenException(
				"Der Benutzer mit der AuthentifizierungID \"" + authentifizierungId + "\" existiert nicht!")));
	}

	public List<BenutzerDto> ermittleAlleZuNachname(final String nachname) throws BenutzerNichtGefundenException
	{
		final var alleBenutzer = benutzerRepository.ermittleAlleZuNachname(nachname);
		if (alleBenutzer == null)
		{
			throw new BenutzerNichtGefundenException(
				"Es wurde kein Benutzer mit dem Nachnamen \"" + nachname + "\" gefunden!");
		}
		return Konvertierer.konvertiereAlleZuBenutzerEintrag(alleBenutzer);
	}

	public BenutzerDto speichereBenutzer(final BenutzerDto benutzerEintrag, final String authentifizierungId)
		throws AuthentifizierungNichtGefundenException
	{
		final var authentifizierung = authentfizierungRepository
			.ermittleZuId(new Primaerschluessel(authentifizierungId))
			.orElseThrow(() -> new AuthentifizierungNichtGefundenException(
				"Die Authentifizierung mit der ID \"" + authentifizierungId + "\" existiert nicht!"));

		return Konvertierer.konvertiereZuBenutzerEintrag(benutzerRepository.speichereBenutzer(new Benutzer(
			new Primaerschluessel(),
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
			authentifizierung)));
	}
}
