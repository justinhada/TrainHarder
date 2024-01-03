package de.justinharder.trainharder.domain.services.authentifizierung;

import de.justinharder.trainharder.domain.model.embeddables.Passwort;
import de.justinharder.trainharder.domain.model.exceptions.AuthentifizierungException;
import de.justinharder.trainharder.domain.model.exceptions.LoginException;
import de.justinharder.trainharder.domain.model.exceptions.PasswortUnsicherException;
import de.justinharder.trainharder.domain.repository.AuthentifizierungRepository;
import de.justinharder.trainharder.domain.services.authentifizierung.passwort.PasswortCheck;
import de.justinharder.trainharder.domain.services.authentifizierung.passwort.PasswortHasher;
import de.justinharder.trainharder.domain.services.dto.AuthentifizierungDto;
import de.justinharder.trainharder.domain.services.mail.Mail;
import de.justinharder.trainharder.domain.services.mail.MailAdresse;
import de.justinharder.trainharder.domain.services.mail.MailServer;
import de.justinharder.trainharder.domain.services.mapper.AuthentifizierungDtoMapper;
import jakarta.enterprise.context.Dependent;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.UUID;

@Slf4j
@Dependent
@RequiredArgsConstructor
public class LoginService
{
	@NonNull
	private final AuthentifizierungRepository authentifizierungRepository;

	@NonNull
	private final AuthentifizierungDtoMapper authentifizierungDtoMapper;

	@NonNull
	private final PasswortHasher passwortHasher;

	@NonNull
	private final PasswortCheck passwortCheck;

	@NonNull
	private final MailServer mailServer;

	public AuthentifizierungDto login(@NonNull String benutzername, @NonNull String passwort)
		throws InvalidKeySpecException, NoSuchAlgorithmException, LoginException
	{
		var authentifizierung = authentifizierungRepository.findeMitBenutzername(benutzername)
			.orElseThrow(() -> new LoginException("Der Benutzername oder das Passwort ist leider falsch!"));

		if (!passwortHasher.check(authentifizierung.getPasswort(), passwort))
		{
			throw new LoginException("Der Benutzername oder das Passwort ist leider falsch!");
		}

		return authentifizierungDtoMapper.mappe(authentifizierung);
	}

	public void sendeResetMail(@NonNull String mail, @NonNull UUID resetUuid) throws AuthentifizierungException
	{
		var authentifizierung = authentifizierungRepository.findeMitMail(mail)
			.orElseThrow(() -> new AuthentifizierungException(
				"Die Authentifizierung mit der Mail %s existiert nicht!".formatted(mail)));
		authentifizierungRepository.speichere(authentifizierung.setResetUuid(resetUuid));

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
		throws PasswortUnsicherException, AuthentifizierungException, InvalidKeySpecException, NoSuchAlgorithmException
	{
		if (passwortCheck.isUnsicher(passwort))
		{
			throw new PasswortUnsicherException("Das Passwort ist unsicher!");
		}

		var authentifizierung = authentifizierungRepository.findeMitResetUuid(resetUuid)
			.orElseThrow(() -> new AuthentifizierungException(
				"Die Authentifizierung mit der ResetUUID %s existiert nicht!".formatted(resetUuid)));

		var salt = authentifizierung.getPasswort().getSalt();
		authentifizierungRepository.speichere(authentifizierung
			.setPasswort(new Passwort(salt, passwortHasher.hash(passwort, salt)))
			.setResetUuid(null));
	}
}
