package de.justinharder.trainharder.domain.services.authentifizierung;

import de.justinharder.trainharder.domain.model.embeddables.Passwort;
import de.justinharder.trainharder.domain.model.exceptions.AuthentifizierungNichtGefundenException;
import de.justinharder.trainharder.domain.model.exceptions.LoginException;
import de.justinharder.trainharder.domain.model.exceptions.PasswortUnsicherException;
import de.justinharder.trainharder.domain.repository.AuthentifizierungRepository;
import de.justinharder.trainharder.domain.services.FehlermeldungService;
import de.justinharder.trainharder.domain.services.authentifizierung.passwort.PasswortCheck;
import de.justinharder.trainharder.domain.services.authentifizierung.passwort.PasswortHasher;
import de.justinharder.trainharder.domain.services.mail.Mail;
import de.justinharder.trainharder.domain.services.mail.MailAdresse;
import de.justinharder.trainharder.domain.services.mail.MailServer;
import de.justinharder.trainharder.domain.services.mapper.AuthentifizierungDtoMapper;
import de.justinharder.trainharder.domain.services.dto.AuthentifizierungDto;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.UUID;

@Slf4j
@Dependent
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
		AuthentifizierungRepository authentifizierungRepository,
		AuthentifizierungDtoMapper authentifizierungDtoMapper,
		PasswortHasher passwortHasher,
		PasswortCheck passwortCheck,
		MailServer mailServer)
	{
		this.authentifizierungRepository = authentifizierungRepository;
		this.authentifizierungDtoMapper = authentifizierungDtoMapper;
		this.passwortHasher = passwortHasher;
		this.passwortCheck = passwortCheck;
		this.mailServer = mailServer;
	}

	public AuthentifizierungDto login(@NonNull String benutzername, @NonNull String passwort) throws InvalidKeySpecException, NoSuchAlgorithmException, LoginException
	{
		var authentifizierung = authentifizierungRepository.ermittleZuBenutzername(benutzername)
			.orElseThrow(() -> new LoginException(LOGIN_EXCEPTION));

		if (!passwortHasher.check(authentifizierung.getPasswort(), passwort))
		{
			throw new LoginException(LOGIN_EXCEPTION);
		}

		return authentifizierungDtoMapper.mappe(authentifizierung);
	}

	public void sendeResetMail(@NonNull String mail, @NonNull UUID resetUuid) throws AuthentifizierungNichtGefundenException
	{
		var authentifizierung = authentifizierungRepository.ermittleZuMail(mail)
			.orElseThrow(FehlermeldungService.wirfAuthentifizierungNichtGefundenException("der Mail", mail));
		authentifizierungRepository.speichereAuthentifizierung(authentifizierung.setResetUuid(resetUuid));

		var mail1 = new Mail(
			new MailAdresse("trainharder2021@gmail.com", "TrainHarder-Team"),
			"Anfrage der Passwort-Zurücksetzung",
			"Hallo " + authentifizierung.getBenutzername() + ",\n"
				+ "wir haben deine Anfrage der Passwort-Zurücksetzung erhalten.\n"
				+ "Über folgenden Link kannst du dein Passwort zurücksetzen: \n"
				+ "\thttps://www.trainharder.de/login/reset/" + resetUuid + "\n\n"
				+ "Mit den besten Grüßen!\n"
				+ "das TrainHarder-Team")
			.fuegeEmpfaengerHinzu(new MailAdresse(authentifizierung.getMail()));

		log.info("{}", mail1);
		mailServer.sende(mail1);
	}

	public void resetPassword(@NonNull UUID resetUuid, @NonNull String passwort)
		throws PasswortUnsicherException, AuthentifizierungNichtGefundenException, InvalidKeySpecException, NoSuchAlgorithmException
	{
		if (passwortCheck.isUnsicher(passwort))
		{
			throw new PasswortUnsicherException("Das Passwort ist unsicher!");
		}

		var authentifizierung = authentifizierungRepository.ermittleZuResetUuid(resetUuid)
			.orElseThrow(FehlermeldungService.wirfAuthentifizierungNichtGefundenException("der ResetUUID", resetUuid.toString()));

		var salt = authentifizierung.getPasswort().getSalt();
		authentifizierungRepository.speichereAuthentifizierung(authentifizierung
			.setPasswort(new Passwort(salt, passwortHasher.hash(passwort, salt)))
			.setResetUuid(null));
	}
}
