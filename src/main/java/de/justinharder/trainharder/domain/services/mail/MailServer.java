package de.justinharder.trainharder.domain.services.mail;

import de.justinharder.trainharder.domain.model.exceptions.MailServerException;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.NonNull;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;

@Dependent
public class MailServer
{
	private static final String CHARSET = "UTF-8";

	private final Session session;

	public MailServer(Properties properties, String benutzername, String passwort)
	{
		session = initialisiere(properties, MailAuthentifizierer.aus(benutzername, passwort));
	}

	@Inject
	public MailServer()
	{
		var properties = new Properties();
		properties.setProperty("mail.smtp.host", "smtp.gmail.com");
		properties.setProperty("mail.smtp.port", "587");
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.starttls.enable", "true");

		session = initialisiere(properties, MailAuthentifizierer.aus("", ""));
	}

	private Session initialisiere(Properties properties, Authenticator authenticator)
	{
		return Session.getInstance(properties, authenticator);
	}

	public void sende(@NonNull Mail mail)
	{
		try
		{
			var mimeMessage = new MimeMessage(session);

			mimeMessage.setFrom(new InternetAddress(mail.getSender().getAdresse(), mail.getSender().getName()));
			empfaengerTyp(mail.getAlleEmpfaenger(), MimeMessage.RecipientType.TO, mimeMessage);
			empfaengerTyp(mail.getAlleInKopie(), MimeMessage.RecipientType.CC, mimeMessage);
			empfaengerTyp(mail.getAlleInBlindkopie(),  MimeMessage.RecipientType.BCC, mimeMessage);
			mimeMessage.setSubject(mail.getBetreff(), CHARSET);
			mimeMessage.setText(mail.getInhalt(), CHARSET);

			Transport.send(mimeMessage);
		}
		catch (MessagingException | UnsupportedEncodingException e)
		{
			throw new MailServerException("Beim Versenden der Mail ist ein Fehler aufgetreten!", e);
		}
	}

	private void empfaengerTyp(List<MailAdresse> mailAdressen, Message.RecipientType recipientType, MimeMessage mimeMessage) throws MessagingException
	{
		for (var mailAdresse : mailAdressen)
		{
			mimeMessage.addRecipient(recipientType, new InternetAddress(mailAdresse.getAdresse()));
		}
	}
}
