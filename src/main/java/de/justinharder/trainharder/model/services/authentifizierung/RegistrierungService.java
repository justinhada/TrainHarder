package de.justinharder.trainharder.model.services.authentifizierung;

import javax.inject.Inject;

import com.google.common.base.Preconditions;

import de.justinharder.trainharder.model.domain.Authentifizierung;
import de.justinharder.trainharder.model.domain.Primaerschluessel;
import de.justinharder.trainharder.model.domain.exceptions.BenutzernameVergebenException;
import de.justinharder.trainharder.model.domain.exceptions.MailVergebenException;
import de.justinharder.trainharder.model.domain.exceptions.PasswortUnsicherException;
import de.justinharder.trainharder.model.repository.AuthentifizierungRepository;
import de.justinharder.trainharder.model.services.mapper.AuthentifizierungDtoMapper;
import de.justinharder.trainharder.view.dto.AuthentifizierungDto;
import de.justinharder.trainharder.view.dto.Registrierung;

public class RegistrierungService
{
	private final AuthentifizierungRepository authentifizierungRepository;
	private final AuthentifizierungDtoMapper authentifizierungDtoMapper;
	private final PasswortCheck passwortCheck;

	@Inject
	public RegistrierungService(
		final AuthentifizierungRepository authentifizierungRepository,
		final AuthentifizierungDtoMapper authentifizierungDtoMapper,
		final PasswortCheck passwortCheck)
	{
		this.authentifizierungRepository = authentifizierungRepository;
		this.authentifizierungDtoMapper = authentifizierungDtoMapper;
		this.passwortCheck = passwortCheck;
	}

	public AuthentifizierungDto registriere(final Registrierung registrierung)
		throws MailVergebenException, BenutzernameVergebenException, PasswortUnsicherException
	{
		Preconditions.checkNotNull(registrierung, "Es wird eine gültige Registrierung benötigt!");

		if (authentifizierungRepository.checkMail(registrierung.getMail()))
		{
			throw new MailVergebenException(
				"Die Mail \"" + registrierung.getMail() + "\" ist bereits vergeben!");
		}

		if (authentifizierungRepository.checkBenutzername(registrierung.getBenutzername()))
		{
			throw new BenutzernameVergebenException(
				"Der Benutzername \"" + registrierung.getBenutzername() + "\" ist bereits vergeben!");
		}

		if (passwortCheck.isUnsicher(registrierung.getPasswort()))
		{
			throw new PasswortUnsicherException("Das Passwort ist unsicher!");
		}

		return authentifizierungDtoMapper.konvertiere(authentifizierungRepository
			.speichereAuthentifizierung(new Authentifizierung(
				new Primaerschluessel(),
				registrierung.getMail(),
				registrierung.getBenutzername(),
				registrierung.getPasswort())));
	}
}