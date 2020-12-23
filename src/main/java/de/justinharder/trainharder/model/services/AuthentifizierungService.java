package de.justinharder.trainharder.model.services;

import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.model.domain.exceptions.AuthentifizierungNichtGefundenException;
import de.justinharder.trainharder.model.repository.AuthentifizierungRepository;
import de.justinharder.trainharder.model.services.mapper.AuthentifizierungDtoMapper;
import de.justinharder.trainharder.view.dto.AuthentifizierungDto;
import lombok.NonNull;

import javax.inject.Inject;

public class AuthentifizierungService
{
	private final AuthentifizierungRepository authentifizierungRepository;
	private final AuthentifizierungDtoMapper authentifizierungDtoMapper;

	@Inject
	public AuthentifizierungService(
		AuthentifizierungRepository authentifizierungRepository,
		AuthentifizierungDtoMapper authentifizierungDtoMapper)
	{
		this.authentifizierungRepository = authentifizierungRepository;
		this.authentifizierungDtoMapper = authentifizierungDtoMapper;
	}

	public AuthentifizierungDto ermittleZuId(@NonNull String id) throws AuthentifizierungNichtGefundenException
	{
		return authentifizierungRepository.ermittleZuId(new Primaerschluessel(id))
			.map(authentifizierungDtoMapper::mappe)
			.orElseThrow(FehlermeldungService.wirfAuthentifizierungNichtGefundenException("der ID", id));
	}

	public AuthentifizierungDto ermittleZuBenutzer(@NonNull String benutzerId)
		throws AuthentifizierungNichtGefundenException
	{
		return authentifizierungRepository.ermittleZuBenutzer(new Primaerschluessel(benutzerId))
			.map(authentifizierungDtoMapper::mappe)
			.orElseThrow(
				FehlermeldungService.wirfAuthentifizierungNichtGefundenException("der BenutzerID", benutzerId));
	}

	public AuthentifizierungDto ermittleZuBenutzername(@NonNull String benutzername)
		throws AuthentifizierungNichtGefundenException
	{
		return authentifizierungRepository.ermittleZuBenutzername(benutzername)
			.map(authentifizierungDtoMapper::mappe)
			.orElseThrow(
				FehlermeldungService.wirfAuthentifizierungNichtGefundenException("dem Benutzernamen", benutzername));
	}
}
