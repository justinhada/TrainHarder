package de.justinharder.trainharder.model.services;

import de.justinharder.trainharder.model.services.mail.Mail;
import de.justinharder.trainharder.model.services.mail.MailAdresse;
import de.justinharder.trainharder.model.services.mail.MailServer;
import de.justinharder.trainharder.view.dto.Kontaktformular;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;

@Slf4j
public class KontaktService
{
	private final MailServer mailServer;

	@Inject
	public KontaktService(MailServer mailServer)
	{
		this.mailServer = mailServer;
	}

	public void kontaktiere(@NonNull Kontaktformular kontaktformular)
	{
		var name = kontaktformular.getVorname() + " " + kontaktformular.getNachname();
		var mail = new Mail(
			new MailAdresse("trainharder2021@gmail.com", "TrainHarder-Team"),
			"Support-Anfrage von " + name,
			"Eine Support-Anfrage von " + name + "\n"
				+ "Benutzer:\n"
				+ "\tBenutzername: " + kontaktformular.getBenutzername() + "\n"
				+ "\tE-Mail-Adresse: " + kontaktformular.getMail() + "\n"
				+ "\tName: " + name + "\n"
				+ "Nachricht:\n"
				+ "\t" + kontaktformular.getNachricht())
			.fuegeEmpfaengerHinzu(new MailAdresse("justinharder@t-online.de", "Justin Harder"));

		log.info("{}", mail);
		mailServer.sende(mail);
	}
}