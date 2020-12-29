package de.justinharder.trainharder.model.services.authentifizierung;

import de.justinharder.trainharder.model.domain.Authentifizierung;
import de.justinharder.trainharder.model.domain.embeddables.Passwort;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.model.domain.exceptions.AuthentifizierungNichtGefundenException;
import de.justinharder.trainharder.model.domain.exceptions.BenutzernameVergebenException;
import de.justinharder.trainharder.model.domain.exceptions.MailVergebenException;
import de.justinharder.trainharder.model.domain.exceptions.PasswortUnsicherException;
import de.justinharder.trainharder.model.repository.AuthentifizierungRepository;
import de.justinharder.trainharder.model.services.FehlermeldungService;
import de.justinharder.trainharder.model.services.authentifizierung.passwort.PasswortCheck;
import de.justinharder.trainharder.model.services.authentifizierung.passwort.PasswortHasher;
import de.justinharder.trainharder.model.services.mail.Mail;
import de.justinharder.trainharder.model.services.mail.MailAdresse;
import de.justinharder.trainharder.model.services.mail.MailServer;
import de.justinharder.trainharder.model.services.mapper.AuthentifizierungDtoMapper;
import de.justinharder.trainharder.view.dto.AuthentifizierungDto;
import de.justinharder.trainharder.view.dto.Registrierung;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class RegistrierungService
{
	private static final Logger LOGGER = LoggerFactory.getLogger(RegistrierungService.class);

	private final AuthentifizierungRepository authentifizierungRepository;
	private final AuthentifizierungDtoMapper authentifizierungDtoMapper;
	private final PasswortHasher passwortHasher;
	private final PasswortCheck passwortCheck;
	private final MailServer mailServer;

	@Inject
	public RegistrierungService(
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

	public AuthentifizierungDto registriere(@NonNull Registrierung registrierung)
		throws MailVergebenException, BenutzernameVergebenException, PasswortUnsicherException, InvalidKeySpecException,
		NoSuchAlgorithmException
	{
		if (authentifizierungRepository.ermittleZuMail(registrierung.getMail()).isPresent())
		{
			throw new MailVergebenException(
				"Die Mail \"" + registrierung.getMail() + "\" ist bereits vergeben!");
		}

		if (authentifizierungRepository.ermittleZuBenutzername(registrierung.getBenutzername()).isPresent())
		{
			throw new BenutzernameVergebenException(
				"Der Benutzername \"" + registrierung.getBenutzername() + "\" ist bereits vergeben!");
		}

		if (passwortCheck.isUnsicher(registrierung.getPasswort()))
		{
			throw new PasswortUnsicherException("Das Passwort ist unsicher!");
		}

		var salt = passwortHasher.generiereSalt(new byte[16]);
		var authentifizierung = authentifizierungRepository
			.speichereAuthentifizierung(new Authentifizierung(
				new Primaerschluessel(),
				registrierung.getMail(),
				registrierung.getBenutzername(),
				new Passwort(salt, passwortHasher.hash(registrierung.getPasswort(), salt))));

		var mail = new Mail(
			new MailAdresse("mail@justinharder.de", "TrainHarder-Team"),
			"Willkommen bei TrainHarder!",
			"Hallo " + authentifizierung.getBenutzername() + ",\n"
				+ "wir heißen dich herzlich Willkommen bei TrainHarder!\n"
				+ "Über folgenden Link kannst du deine E-Mail-Adresse bestätigen: \n"
				+ "\thttps://www.trainharder.de/join/" + authentifizierung.getPrimaerschluessel().getId().toString()
				+ "\n\n"
				+ "Mit den besten Grüßen!\n"
				+ "das TrainHarder-Team")
			.fuegeEmpfaengerHinzu(new MailAdresse(authentifizierung.getMail()));

		LOGGER.info("{}", mail);
		// mailServer.sendeMail(mail, StandardCharsets.UTF_8);

		return authentifizierungDtoMapper.mappe(authentifizierung);
	}

	public void aktiviere(@NonNull String id) throws AuthentifizierungNichtGefundenException
	{
		var authentifizierung = authentifizierungRepository.ermittleZuId(new Primaerschluessel(id))
			.orElseThrow(FehlermeldungService.wirfAuthentifizierungNichtGefundenException("der ID", id));

		authentifizierungRepository.speichereAuthentifizierung(authentifizierung.setAktiv(true));
	}
}
