package de.justinharder.old.domain.services.authentifizierung;

import de.justinharder.old.domain.model.Authentifizierung;
import de.justinharder.old.domain.model.attribute.Passwort;
import de.justinharder.old.domain.model.exceptions.AuthentifizierungException;
import de.justinharder.old.domain.model.exceptions.BenutzernameVergebenException;
import de.justinharder.old.domain.model.exceptions.MailVergebenException;
import de.justinharder.old.domain.model.exceptions.PasswortUnsicherException;
import de.justinharder.old.domain.repository.AuthentifizierungRepository;
import de.justinharder.old.domain.services.authentifizierung.passwort.PasswortCheck;
import de.justinharder.old.domain.services.authentifizierung.passwort.PasswortHasher;
import de.justinharder.old.domain.services.dto.AuthentifizierungDto;
import de.justinharder.old.domain.services.dto.Registrierung;
import de.justinharder.old.domain.services.mail.Mail;
import de.justinharder.old.domain.services.mail.MailAdresse;
import de.justinharder.old.domain.services.mapper.AuthentifizierungDtoMapper;
import de.justinharder.base.domain.model.attribute.ID;
import de.justinharder.old.domain.services.mail.MailServer;
import jakarta.enterprise.context.Dependent;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Slf4j
@Dependent
@RequiredArgsConstructor
public class RegistrierungService
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

	public AuthentifizierungDto registriere(@NonNull Registrierung registrierung)
		throws MailVergebenException, BenutzernameVergebenException, PasswortUnsicherException, InvalidKeySpecException,
		NoSuchAlgorithmException
	{
		if (authentifizierungRepository.findeMitMail(registrierung.getMail()).isPresent())
		{
			throw new MailVergebenException("Die Mail %s ist bereits vergeben!".formatted(registrierung.getMail()));
		}

		if (authentifizierungRepository.findeMitBenutzername(registrierung.getBenutzername()).isPresent())
		{
			throw new BenutzernameVergebenException(
				"Der Benutzername %s ist bereits vergeben!".formatted(registrierung.getBenutzername()));
		}

		if (passwortCheck.isUnsicher(registrierung.getPasswort()))
		{
			throw new PasswortUnsicherException("Das Passwort ist unsicher!");
		}

		var salt = passwortHasher.generiereSalt(new byte[16]);
		var authentifizierung = new Authentifizierung(
			new ID(),
			registrierung.getMail(),
			registrierung.getBenutzername(),
			new Passwort(salt, passwortHasher.hash(registrierung.getPasswort(), salt)));

		authentifizierungRepository.speichere(authentifizierung);

		var mail = new Mail(
			new MailAdresse("trainharder2021@gmail.com", "TrainHarder-Team"),
			"Willkommen bei TrainHarder!",
			"Hallo " + authentifizierung.getBenutzername() + ",\n"
				+ "wir heißen dich herzlich Willkommen bei TrainHarder!\n"
				+ "Über folgenden Link kannst du deine E-Mail-Adresse bestätigen: \n"
				+ "\thttps://www.trainharder.de/join/" + authentifizierung.getId().getWert()
				+ "\n\n"
				+ "Mit den besten Grüßen!\n"
				+ "das TrainHarder-Team")
			.fuegeEmpfaengerHinzu(new MailAdresse(authentifizierung.getMail()));

		log.info("{}", mail);
		mailServer.sende(mail);

		return authentifizierungDtoMapper.mappe(authentifizierung);
	}

	public void aktiviere(@NonNull String id) throws AuthentifizierungException
	{
		authentifizierungRepository.speichere(authentifizierungRepository.finde(new ID(id))
			.orElseThrow(() -> new AuthentifizierungException(
				"Die Authentifizierung mit " + "der ID" + " \"" + id + "\" existiert nicht!"))
			.setAktiv(true));
	}
}
