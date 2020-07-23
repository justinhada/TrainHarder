package de.justinharder.trainharder.model.services.authentifizierung;

import javax.inject.Inject;

import com.google.common.base.Preconditions;

import de.justinharder.trainharder.model.domain.exceptions.AuthentifizierungNichtGefundenException;
import de.justinharder.trainharder.model.domain.exceptions.LoginException;
import de.justinharder.trainharder.model.domain.exceptions.PasswortUnsicherException;
import de.justinharder.trainharder.model.repository.AuthentifizierungRepository;
import de.justinharder.trainharder.model.services.mail.Mail;
import de.justinharder.trainharder.model.services.mail.MailAdresse;
import de.justinharder.trainharder.model.services.mail.MailServer;
import de.justinharder.trainharder.model.services.mapper.AuthentifizierungDtoMapper;
import de.justinharder.trainharder.view.dto.AuthentifizierungDto;

public class LoginService
{
	private final AuthentifizierungRepository authentifizierungRepository;
	private final AuthentifizierungDtoMapper authentifizierungDtoMapper;
	private final MailServer mailServer;
	private final PasswortCheck passwortCheck;

	@Inject
	public LoginService(
		final AuthentifizierungRepository authentifizierungRepository,
		final AuthentifizierungDtoMapper authentifizierungDtoMapper,
		final MailServer mailServer,
		final PasswortCheck passwortCheck)
	{
		this.authentifizierungRepository = authentifizierungRepository;
		this.authentifizierungDtoMapper = authentifizierungDtoMapper;
		this.mailServer = mailServer;
		this.passwortCheck = passwortCheck;
	}

	public AuthentifizierungDto login(final String benutzername, final String passwort) throws LoginException
	{
		Preconditions.checkNotNull(benutzername, "Zum Login wird ein gültiger Benutzername benötigt!");
		Preconditions.checkNotNull(passwort, "Zum Login wird ein gültiges Passwort benötigt!");

		return authentifizierungRepository.login(benutzername, passwort)
			.map(authentifizierungDtoMapper::konvertiere)
			.orElseThrow(() -> new LoginException("Der Benutzername oder das Passwort ist leider falsch!"));
	}

	public void sendeResetMail(final String mail, final String resetUuid) throws AuthentifizierungNichtGefundenException
	{
		Preconditions.checkNotNull(mail, "Zum Senden der Reset-Mail wird eine gültige Mail benötigt!");
		Preconditions.checkNotNull(resetUuid, "Zum Senden der Reset-Mail wird eine gültige ResetUUID benötigt!");

		final var authentifizierung = authentifizierungRepository.ermittleZuMail(mail)
			.orElseThrow(() -> new AuthentifizierungNichtGefundenException(
				"Die Authentifizierung mit der Mail \"" + mail + "\" existiert nicht!"));
		authentifizierungRepository.speichereAuthentifizierung(authentifizierung.setResetUuid(resetUuid));

		final var mail1 = new Mail(
			new MailAdresse("mail@justinharder.de", "TrainHarder-Team"),
			"Anfrage der Passwort-Zurücksetzung",
			"Hallo " + authentifizierung.getBenutzername() + ",\n"
				+ "wir haben deine Anfrage der Passwort-Zurücksetzung erhalten.\n"
				+ "Über folgenden Link kannst du dein Passwort zurücksetzen: \n"
				+ "\thttps://www.trainharder.de/login/reset/" + resetUuid + "\n\n"
				+ "Mit den besten Grüßen!\n"
				+ "das TrainHarder-Team")
					.fuegeEmpfaengerHinzu(new MailAdresse(authentifizierung.getMail()));
		System.out.println(mail1);

		//		mailServer.sendeMail(
		//			mail1,
		//			StandardCharsets.UTF_8);
	}

	public void resetPassword(final String resetUuid, final String passwort)
		throws PasswortUnsicherException, AuthentifizierungNichtGefundenException
	{
		Preconditions.checkNotNull(resetUuid, "Zum Zurücksetzen des Passworts wird eine gültige ResetUUID benötigt!");
		Preconditions.checkNotNull(passwort, "Zum Zurücksetzen des Passworts wird ein gültiges Passwort benötigt!");

		if (passwortCheck.isUnsicher(passwort))
		{
			throw new PasswortUnsicherException("Das Passwort ist unsicher!");
		}

		final var authentifizierung = authentifizierungRepository.ermittleZuResetUuid(resetUuid)
			.orElseThrow(() -> new AuthentifizierungNichtGefundenException(
				"Die Authentifizierung mit der ResetUUID \"" + resetUuid + "\" existiert nicht!"));
		authentifizierungRepository.speichereAuthentifizierung(authentifizierung
			.setPasswort(passwort)
			.setResetUuid(null));
	}
}
