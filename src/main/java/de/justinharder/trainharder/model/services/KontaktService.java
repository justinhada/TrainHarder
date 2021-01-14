package de.justinharder.trainharder.model.services;

import de.justinharder.trainharder.model.services.mail.Mail;
import de.justinharder.trainharder.model.services.mail.MailAdresse;
import de.justinharder.trainharder.model.services.mail.MailServer;
import de.justinharder.trainharder.view.dto.Kontaktformular;
import lombok.NonNull;

import javax.inject.Inject;

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
		mailServer.sende(new Mail(
			new MailAdresse("mail@justinharder.de", "TrainHarder-Team"),
			"Support-Anfrage von " + kontaktformular.getBenutzername(),
			"Eine Support-Anfrage von " + kontaktformular.getBenutzername() + "\n"
				+ "Benutzer:\n"
				+ "\tBenutzername: " + kontaktformular.getBenutzername() + "\n"
				+ "\tE-Mail-Adresse: " + kontaktformular.getMail() + "\n"
				+ "\tName: " + kontaktformular.getVorname() + " " + kontaktformular.getNachname() + "\n"
				+ "Nachricht:\n"
				+ "\t" + kontaktformular.getNachricht())
			.fuegeEmpfaengerHinzu(new MailAdresse("justinharder@t-online.de", "Justin Harder")));
	}
}
