package de.justinharder.powerlifting.model.services;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import de.justinharder.powerlifting.model.domain.Authentifizierung;
import de.justinharder.powerlifting.model.domain.dto.AuthentifizierungEintrag;
import de.justinharder.powerlifting.model.domain.exceptions.AuthentifizierungNichtGefundenException;
import de.justinharder.powerlifting.model.domain.exceptions.BenutzernameVergebenException;
import de.justinharder.powerlifting.model.domain.exceptions.LoginException;
import de.justinharder.powerlifting.model.domain.exceptions.MailBereitsRegistriertException;
import de.justinharder.powerlifting.model.domain.exceptions.PasswortNichtSicherException;
import de.justinharder.powerlifting.model.repository.AuthentifizierungRepository;
import de.justinharder.powerlifting.model.services.registrierung.MailSender;
import de.justinharder.powerlifting.model.services.registrierung.PasswortChecker;

public class AuthentifizierungService implements Serializable
{
	private static final long serialVersionUID = -1065462481382412629L;

	private final AuthentifizierungRepository authentifizierungRepository;

	@Inject
	public AuthentifizierungService(final AuthentifizierungRepository authentifizierungRepository)
	{
		this.authentifizierungRepository = authentifizierungRepository;
	}

	public List<AuthentifizierungEintrag> ermittleAlle()
	{
		return konvertiereAlle(authentifizierungRepository.ermittleAlle());
	}

	public AuthentifizierungEintrag ermittleZuId(final String id) throws AuthentifizierungNichtGefundenException
	{
		final var authentifizierung = authentifizierungRepository.ermittleZuId(Integer.valueOf(id));
		if (authentifizierung == null)
		{
			throw new AuthentifizierungNichtGefundenException(
				"Die Authentifizierung mit der ID \"" + id + "\" existiert nicht!");
		}
		return konvertiere(authentifizierung);
	}

	public AuthentifizierungEintrag ermittleZuBenutzer(final String benutzerId)
		throws AuthentifizierungNichtGefundenException
	{
		final var authentifizierung = authentifizierungRepository.ermittleZuBenutzer(Integer.valueOf(benutzerId));
		if (authentifizierung == null)
		{
			throw new AuthentifizierungNichtGefundenException(
				"Die Authentifizierung mit der BenutzerID \"" + benutzerId + "\" existiert nicht!");
		}
		return konvertiere(authentifizierung);
	}

	public void erstelleAuthentifizierung(final AuthentifizierungEintrag authentifizierungEintrag)
	{
		final var authentifizierung = new Authentifizierung(
			authentifizierungEintrag.getMail(),
			authentifizierungEintrag.getBenutzername(),
			authentifizierungEintrag.getPasswort());
		authentifizierungRepository.erstelleAuthentifizierung(authentifizierung);
	}

	public void registriereBenutzer(final AuthentifizierungEintrag authentifizierungEintrag)
		throws MailBereitsRegistriertException, BenutzernameVergebenException, PasswortNichtSicherException
	{
		if (authentifizierungRepository.checkMail(authentifizierungEintrag.getMail()))
		{
			throw new MailBereitsRegistriertException("Es existiert bereits ein Benutzer mit dieser E-Mail-Adresse!");
		}

		if (authentifizierungRepository.checkBenutzername(authentifizierungEintrag.getBenutzername()))
		{
			throw new BenutzernameVergebenException("Dieser Benutzername ist leider schon vergeben!");
		}

		if (!new PasswortChecker().check(authentifizierungEintrag.getPasswort()))
		{
			throw new PasswortNichtSicherException("Das Passwort ist nicht sicher genug!");
		}

		new MailSender().sendeRegistrierungMail(authentifizierungEintrag);

		erstelleAuthentifizierung(authentifizierungEintrag);
	}

	public AuthentifizierungEintrag checkLogin(final String mail, final String passwort) throws LoginException
	{
		return konvertiere(authentifizierungRepository.checkLogin(mail, passwort));
	}

	private List<AuthentifizierungEintrag> konvertiereAlle(final List<Authentifizierung> authentifizierungen)
	{
		return authentifizierungen
			.stream()
			.map(this::konvertiere)
			.collect(Collectors.toList());
	}

	private AuthentifizierungEintrag konvertiere(final Authentifizierung authentifizierung)
	{
		return new AuthentifizierungEintrag(
			authentifizierung.getId(),
			authentifizierung.getMail(),
			authentifizierung.getBenutzername(),
			authentifizierung.getPasswort(),
			authentifizierung.getPasswort());
	}
}
