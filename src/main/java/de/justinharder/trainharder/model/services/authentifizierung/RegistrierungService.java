package de.justinharder.trainharder.model.services.authentifizierung;

import javax.inject.Inject;

import com.google.common.base.Preconditions;

import de.justinharder.trainharder.model.domain.Authentifizierung;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.model.domain.exceptions.AuthentifizierungNichtGefundenException;
import de.justinharder.trainharder.model.domain.exceptions.BenutzernameVergebenException;
import de.justinharder.trainharder.model.domain.exceptions.MailVergebenException;
import de.justinharder.trainharder.model.domain.exceptions.PasswortUnsicherException;
import de.justinharder.trainharder.model.repository.AuthentifizierungRepository;
import de.justinharder.trainharder.model.services.mail.Mail;
import de.justinharder.trainharder.model.services.mail.MailAdresse;
import de.justinharder.trainharder.model.services.mail.MailServer;
import de.justinharder.trainharder.model.services.mapper.AuthentifizierungDtoMapper;
import de.justinharder.trainharder.view.dto.AuthentifizierungDto;
import de.justinharder.trainharder.view.dto.Registrierung;

public class RegistrierungService
{
	private final AuthentifizierungRepository authentifizierungRepository;
	private final AuthentifizierungDtoMapper authentifizierungDtoMapper;
	private final PasswortCheck passwortCheck;
	private final MailServer mailServer;

	@Inject
	public RegistrierungService(
		final AuthentifizierungRepository authentifizierungRepository,
		final AuthentifizierungDtoMapper authentifizierungDtoMapper,
		final PasswortCheck passwortCheck,
		final MailServer mailServer)
	{
		this.authentifizierungRepository = authentifizierungRepository;
		this.authentifizierungDtoMapper = authentifizierungDtoMapper;
		this.passwortCheck = passwortCheck;
		this.mailServer = mailServer;
	}

	public AuthentifizierungDto registriere(final Registrierung registrierung)
		throws MailVergebenException, BenutzernameVergebenException, PasswortUnsicherException
	{
		Preconditions.checkNotNull(registrierung, "Zum Beitreten wird eine gültige Registrierung benötigt!");

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

		final var authentifizierung = authentifizierungRepository
			.speichereAuthentifizierung(new Authentifizierung(
				new Primaerschluessel(),
				registrierung.getMail(),
				registrierung.getBenutzername(),
				registrierung.getPasswort()));

		final var mail = new Mail(
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
		System.out.println(mail);

		//		mailServer.sendeMail(
		//			mail,
		//			StandardCharsets.UTF_8);

		return authentifizierungDtoMapper.konvertiere(authentifizierung);
	}

	public void aktiviere(final String id) throws AuthentifizierungNichtGefundenException
	{
		Preconditions.checkNotNull(id, "Zum Aktivieren wird eine gültige ID benötigt!");

		final var authentifizierung = authentifizierungRepository.ermittleZuId(new Primaerschluessel(id))
			.orElseThrow(() -> new AuthentifizierungNichtGefundenException(
				"Die Authentifizierung mit der ID \"" + id + "\" existiert nicht!"));

		authentifizierungRepository.speichereAuthentifizierung(authentifizierung.setAktiv(true));
	}
}
