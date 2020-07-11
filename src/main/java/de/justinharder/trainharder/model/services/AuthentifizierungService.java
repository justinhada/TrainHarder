package de.justinharder.trainharder.model.services;

import java.io.Serializable;

import javax.inject.Inject;

import com.google.common.base.Preconditions;

import de.justinharder.trainharder.model.domain.Authentifizierung;
import de.justinharder.trainharder.model.domain.Primaerschluessel;
import de.justinharder.trainharder.model.domain.exceptions.AuthentifizierungNichtGefundenException;
import de.justinharder.trainharder.model.domain.exceptions.BenutzernameVergebenException;
import de.justinharder.trainharder.model.domain.exceptions.LoginException;
import de.justinharder.trainharder.model.domain.exceptions.MailBereitsRegistriertException;
import de.justinharder.trainharder.model.domain.exceptions.PasswortNichtSicherException;
import de.justinharder.trainharder.model.repository.AuthentifizierungRepository;
import de.justinharder.trainharder.model.services.authentifizierung.PasswortChecker;
import de.justinharder.trainharder.view.dto.AuthentifizierungDto;
import de.justinharder.trainharder.view.dto.Registrierung;

public class AuthentifizierungService implements Serializable
{
	private static final long serialVersionUID = -1065462481382412629L;

	private final AuthentifizierungRepository authentifizierungRepository;

	@Inject
	public AuthentifizierungService(final AuthentifizierungRepository authentifizierungRepository)
	{
		this.authentifizierungRepository = authentifizierungRepository;
	}

	public AuthentifizierungDto ermittleZuId(final String id) throws AuthentifizierungNichtGefundenException
	{
		Preconditions.checkNotNull(id, "Ermittlung der Authentifizierung benötigt eine gültige AuthentifizierungID!");

		return Konvertierer.konvertiereZuAuthentifizierungEintrag(authentifizierungRepository
			.ermittleZuId(new Primaerschluessel(id))
			.orElseThrow(() -> new AuthentifizierungNichtGefundenException(
				"Die Authentifizierung mit der ID \"" + id + "\" existiert nicht!")));
	}

	public AuthentifizierungDto ermittleZuBenutzername(final String benutzername)
		throws AuthentifizierungNichtGefundenException
	{
		Preconditions.checkNotNull(benutzername,
			"Ermittlung der Authentifizierung benötigt einen gültigen Benutzernamen!");

		return Konvertierer.konvertiereZuAuthentifizierungEintrag(authentifizierungRepository
			.ermittleZuBenutzername(benutzername)
			.orElseThrow(() -> new AuthentifizierungNichtGefundenException(
				"Die Authentifizierung mit dem Benutzernamen \"" + benutzername + "\" existiert nicht!")));
	}

	public AuthentifizierungDto ermittleZuBenutzer(final String benutzerId)
		throws AuthentifizierungNichtGefundenException
	{
		Preconditions.checkNotNull(benutzerId, "Ermittlung der Authentifizierung benötigt eine gültige BenutzerID!");

		return Konvertierer.konvertiereZuAuthentifizierungEintrag(authentifizierungRepository
			.ermittleZuBenutzer(new Primaerschluessel(benutzerId))
			.orElseThrow(() -> new AuthentifizierungNichtGefundenException(
				"Die Authentifizierung mit der BenutzerID \"" + benutzerId + "\" existiert nicht!")));
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

		return Konvertierer.konvertiereZuAuthentifizierungEintrag(authentifizierungRepository
			.speichereAuthentifizierung(new Authentifizierung(
				new Primaerschluessel(),
				registrierung.getMail(),
				registrierung.getBenutzername(),
				registrierung.getPasswort())));
	}

	public AuthentifizierungDto login(final String benutzername, final String passwort) throws LoginException
	{
		return Konvertierer.konvertiereZuAuthentifizierungEintrag(authentifizierungRepository
			.login(benutzername, passwort)
			.orElseThrow(() -> new LoginException("Der Benutzername oder das Passwort ist leider falsch!")));
	}
}
