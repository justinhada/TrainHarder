package de.justinharder.trainharder.model.services.authentifizierung;

import javax.inject.Inject;

import de.justinharder.trainharder.model.domain.Authentifizierung;
import de.justinharder.trainharder.model.domain.Primaerschluessel;
import de.justinharder.trainharder.model.domain.exceptions.BenutzernameVergebenException;
import de.justinharder.trainharder.model.domain.exceptions.MailBereitsRegistriertException;
import de.justinharder.trainharder.model.domain.exceptions.PasswortNichtSicherException;
import de.justinharder.trainharder.model.repository.AuthentifizierungRepository;
import de.justinharder.trainharder.model.services.mapper.AuthentifizierungDtoMapper;
import de.justinharder.trainharder.view.dto.AuthentifizierungDto;
import de.justinharder.trainharder.view.dto.Registrierung;

public class RegistrierungService
{
	private final AuthentifizierungRepository authentifizierungRepository;
	private final AuthentifizierungDtoMapper authentifizierungDtoMapper;

	@Inject
	public RegistrierungService(
		final AuthentifizierungRepository authentifizierungRepository,
		final AuthentifizierungDtoMapper authentifizierungDtoMapper)
	{
		this.authentifizierungRepository = authentifizierungRepository;
		this.authentifizierungDtoMapper = authentifizierungDtoMapper;
	}

	public AuthentifizierungDto registriere(final Registrierung registrierung)
		throws MailBereitsRegistriertException, BenutzernameVergebenException, PasswortNichtSicherException
	{
		if (authentifizierungRepository.checkMail(registrierung.getMail()))
		{
			throw new MailBereitsRegistriertException("Es existiert bereits ein Benutzer mit dieser E-Mail-Adresse!");
		}

		if (authentifizierungRepository.checkBenutzername(registrierung.getBenutzername()))
		{
			throw new BenutzernameVergebenException("Dieser Benutzername ist leider schon vergeben!");
		}

		if (!new PasswortChecker().check(registrierung.getPasswort()))
		{
			throw new PasswortNichtSicherException("Das Passwort ist nicht sicher genug!");
		}

		return authentifizierungDtoMapper.konvertiere(authentifizierungRepository
			.speichereAuthentifizierung(new Authentifizierung(
				new Primaerschluessel(),
				registrierung.getMail(),
				registrierung.getBenutzername(),
				registrierung.getPasswort())));
	}
}
