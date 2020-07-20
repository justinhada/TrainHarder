package de.justinharder.trainharder.model.services;

import java.nio.charset.StandardCharsets;

import javax.inject.Inject;

import com.google.common.base.Preconditions;

import de.justinharder.trainharder.model.services.mail.Mail;
import de.justinharder.trainharder.model.services.mail.MailAdresse;
import de.justinharder.trainharder.model.services.mail.MailServer;
import de.justinharder.trainharder.view.dto.Kontaktformular;

public class KontaktService
{
	private final MailServer mailServer;

	@Inject
	public KontaktService(final MailServer mailServer)
	{
		this.mailServer = mailServer;
	}

	public void kontaktiere(final Kontaktformular kontaktformular)
	{
		Preconditions.checkNotNull(kontaktformular, "Zum Kontaktieren wird ein gültiges Kontaktformular benötigt!");

		mailServer.sendeMail(
			new Mail(
				new MailAdresse("mail@justinharder.de", "TrainHarder-Team"),
				"Support-Anfrage von " + kontaktformular.getBenutzername(),
				"Eine Support-Anfrage von " + kontaktformular.getBenutzername()
					+ "Benutzer:\n"
					+ "\tBenutzername: " + kontaktformular.getBenutzername() + "\n"
					+ "\tE-Mail-Adresse: " + kontaktformular.getMail() + "\n"
					+ "\tName: " + kontaktformular.getVorname() + " " + kontaktformular.getNachname() + "\n"
					+ "Nachricht:\n"
					+ "\t" + kontaktformular.getNachricht())
						.fuegeEmpfaengerHinzu(new MailAdresse("justinharder@t-online.de", "Justin Harder")),
			StandardCharsets.UTF_8);
	}
}
