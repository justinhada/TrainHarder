package de.justinharder.trainharder.model.services;

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
			.orElseThrow(
				() -> new BenutzerNichtGefundenException("Der Benutzer mit der ID \"" + id + "\" existiert nicht!"));
	}

	public BenutzerDto ermittleZuAuthentifizierung(final String authentifizierungId)
		throws BenutzerNichtGefundenException
	{
		return benutzerRepository.ermittleZuAuthentifizierung(new Primaerschluessel(authentifizierungId))
			.map(benutzerDtoMapper::konvertiere)
			.orElseThrow(() -> new BenutzerNichtGefundenException(
				"Der Benutzer mit der AuthentifizierungID \"" + authentifizierungId + "\" existiert nicht!"));
	}

	public BenutzerDto speichereBenutzer(final BenutzerDto benutzerDto, final String authentifizierungId)
		throws AuthentifizierungNichtGefundenException
	{
		final var authentifizierung = authentifizierungRepository
			.ermittleZuId(new Primaerschluessel(authentifizierungId))
			.orElseThrow(() -> new AuthentifizierungNichtGefundenException(
				"Die Authentifizierung mit der ID \"" + authentifizierungId + "\" existiert nicht!"));

		return benutzerDtoMapper.konvertiere(benutzerRepository.speichereBenutzer(new Benutzer(
			new Primaerschluessel(),
			new Name(benutzerDto.getVorname(), benutzerDto.getNachname()),
			benutzerDto.getLebensalter(),
			new Benutzerangabe(
				Geschlecht.fromGeschlechtOption(benutzerDto.getGeschlecht()),
				Erfahrung.fromErfahrungOption(benutzerDto.getErfahrung()),
				Ernaehrung.fromErnaehrungOption(benutzerDto.getErnaehrung()),
				Schlafqualitaet.fromSchlafqualitaetOption(benutzerDto.getSchlafqualitaet()),
				Stress.fromStressOption(benutzerDto.getStress()),
				Doping.fromDopingOption(benutzerDto.getDoping()),
				Regenerationsfaehigkeit.fromRegenerationsfaehigkeitOption(benutzerDto.getRegenerationsfaehigkeit())),
			authentifizierung)));
	}
}
