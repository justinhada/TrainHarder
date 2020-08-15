package de.justinharder.trainharder.model.services.authentifizierung;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.UUID;

import javax.inject.Inject;

import com.google.common.base.Preconditions;

import de.justinharder.trainharder.model.domain.embeddables.Passwort;
import de.justinharder.trainharder.model.domain.exceptions.AuthentifizierungNichtGefundenException;
import de.justinharder.trainharder.model.domain.exceptions.LoginException;
import de.justinharder.trainharder.model.domain.exceptions.PasswortUnsicherException;
import de.justinharder.trainharder.model.repository.AuthentifizierungRepository;
import de.justinharder.trainharder.model.services.authentifizierung.passwort.PasswortCheck;
import de.justinharder.trainharder.model.services.authentifizierung.passwort.PasswortHasher;
import de.justinharder.trainharder.model.services.mail.Mail;
import de.justinharder.trainharder.model.services.mail.MailAdresse;
import de.justinharder.trainharder.model.services.mail.MailServer;
import de.justinharder.trainharder.model.services.mapper.AuthentifizierungDtoMapper;
import de.justinharder.trainharder.view.dto.AuthentifizierungDto;

public class LoginService
{
	private static final String LOGIN_EXCEPTION = "Der Benutzername oder das Passwort ist leider falsch!";

	private final AuthentifizierungRepository authentifizierungRepository;
	private final AuthentifizierungDtoMapper authentifizierungDtoMapper;
	private final PasswortHasher passwortHasher;
	private final PasswortCheck passwortCheck;
	private final MailServer mailServer;

	@Inject
	public LoginService(
		final AuthentifizierungRepository authentifizierungRepository,
		final AuthentifizierungDtoMapper authentifizierungDtoMapper,
		final PasswortHasher passwortHasher,
		final PasswortCheck passwortCheck,
		final MailServer mailServer)
	{
		this.authentifizierungRepository = authentifizierungRepository;
		this.authentifizierungDtoMapper = authentifizierungDtoMapper;
		this.passwortHasher = passwortHasher;
		this.passwortCheck = passwortCheck;
		this.mailServer = mailServer;
	}

	public AuthentifizierungDto login(final String benutzername, final String passwort)
		throws InvalidKeySpecException, NoSuchAlgorithmException, LoginException
	{
		Preconditions.checkNotNull(benutzername, "Zum Login wird ein gültiger Benutzername benötigt!");
		Preconditions.checkNotNull(passwort, "Zum Login wird ein gültiges Passwort benötigt!");

		final var authentifizierung = authentifizierungRepository.ermittleZuBenutzername(benutzername)
			.orElseThrow(() -> new LoginException(LOGIN_EXCEPTION));

		if (!passwortHasher.check(authentifizierung.getPasswort(), passwort))
		{
			throw new LoginException(LOGIN_EXCEPTION);
		}

		return authentifizierungDtoMapper.konvertiere(authentifizierung);
	}

	public void sendeResetMail(final String mail, final UUID resetUuid) throws AuthentifizierungNichtGefundenException
	{
		Preconditions.checkNotNull(mail, "Zum Senden der Reset-Mail wird eine gültige Mail benötigt!");
		Preconditions.checkNotNull(resetUuid, "Zum Senden der Reset-Mail wird eine gültige ResetUUID benötigt!");

		final var authentifizierung = authentifizierungRepository.ermittleZuMail(mail)
			.orElseThrow(() -> new AuthentifizierungNichtGefundenException(
				"Die Authentifizierung mit der Mail \"" + mail + "\" existiert nicht!"));
		authentifizierungRepository.speichereAuthentifizierung(
			authentifizierung.setResetUuid(resetUuid));

		final var mail1 = new Mail(
			new MailAdresse("mail@justinharder.de", "TrainHarder-Team"),
			"Anfrage der Passwort-Zurücksetzung",
			"Hallo " + authentifizierung.getBenutzername() + ",\n"
				+ "wir haben deine Anfrage der Passwort-Zurücksetzung erhalten.\n"
				+ "Über folgenden Link kannst du dein Passwort zurücksetzen: \n"
				+ "\thttps://www.trainharder.de/login/reset/" + resetUuid.toString() + "\n\n"
				+ "Mit den besten Grüßen!\n"
				+ "das TrainHarder-Team")
					.fuegeEmpfaengerHinzu(new MailAdresse(authentifizierung.getMail()));
		System.out.println(mail1);

		//		mailServer.sendeMail(
		//			mail1,
		//			StandardCharsets.UTF_8);
	}

	public void resetPassword(final UUID resetUuid, final String passwort) throws PasswortUnsicherException,
		AuthentifizierungNichtGefundenException, InvalidKeySpecException, NoSuchAlgorithmException
	{
		Preconditions.checkNotNull(resetUuid, "Zum Zurücksetzen des Passworts wird eine gültige ResetUUID benötigt!");
		Preconditions.checkNotNull(passwort, "Zum Zurücksetzen des Passworts wird ein gültiges Passwort benötigt!");

		if (passwortCheck.isUnsicher(passwort))
		{
			throw new PasswortUnsicherException("Das Passwort ist unsicher!");
		}

		final var authentifizierung = authentifizierungRepository.ermittleZuResetUuid(resetUuid)
			.orElseThrow(() -> new AuthentifizierungNichtGefundenException(
				"Die Authentifizierung mit der ResetUUID \"" + resetUuid.toString() + "\" existiert nicht!"));

		final var salt = authentifizierung.getPasswort().getSalt();
		authentifizierungRepository.speichereAuthentifizierung(authentifizierung
			.setPasswort(new Passwort(salt, passwortHasher.hash(passwort, salt)))
			.setResetUuid(null));
	}
}
