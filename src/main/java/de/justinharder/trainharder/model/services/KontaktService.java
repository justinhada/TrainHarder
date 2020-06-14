package de.justinharder.trainharder.model.services;

import java.nio.charset.StandardCharsets;

import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import de.justinharder.trainharder.view.dto.Kontaktformular;

public class KontaktService
{
	private static final String UTF8 = StandardCharsets.UTF_8.toString();
	private static final String E_MAIL_ADRESSE = "mail@justinharder.de";
	private static final String MAIL_PROPERTY = "mail.smtp.host";
	private static final String LOCALHOST = "localhost";

	private final MimeMessage nachricht;

	@Inject
	public KontaktService()
	{
		final var properties = System.getProperties();
		properties.setProperty(MAIL_PROPERTY, LOCALHOST);
		final var session = Session.getDefaultInstance(properties);
		nachricht = new MimeMessage(session);
	}

	public void kontaktiere(final Kontaktformular kontaktformular) throws MessagingException
	{
		nachricht.setFrom(new InternetAddress(E_MAIL_ADRESSE));
		nachricht.addRecipient(Message.RecipientType.TO, new InternetAddress(E_MAIL_ADRESSE));
		nachricht.setSubject("Support-Anfrage von " + kontaktformular.getBenutzername(), UTF8);
		nachricht.setText("Eine Support-Anfrage von " + kontaktformular.getBenutzername()
			+ "Benutzer:\n"
			+ "\tBenutzername: " + kontaktformular.getBenutzername()
			+ "\tE-Mail-Adresse: " + kontaktformular.getMail()
			+ "\tName: " + kontaktformular.getVorname() + " " + kontaktformular.getNachname() + "\n"
			+ "Nachricht:\n"
			+ "\t" + kontaktformular.getNachricht(), UTF8);

		Transport.send(nachricht);
	}
}
