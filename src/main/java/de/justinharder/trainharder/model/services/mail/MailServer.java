package de.justinharder.trainharder.model.services.mail;

import de.justinharder.trainharder.model.domain.exceptions.MailServerException;
import jakarta.inject.Inject;
import lombok.NonNull;

import javax.mail.*;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;

public class MailServer
{
	private static final String CHARSET = "UTF-8";

	private final Session session;

	public MailServer(Properties properties, String benutzername, String passwort)
	{
		session = initialisiere(properties, MailAuthentifizierer.aus(benutzername, passwort));
	}

	@Inject
	public MailServer() throws NamingException
	{
		var properties = new Properties();
		properties.setProperty("mail.smtp.host", "smtp.gmail.com");
		properties.setProperty("mail.smtp.port", "587");
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.starttls.enable", "true");

		var initialContext = new InitialContext();
		session = initialisiere(properties, MailAuthentifizierer.aus((String) initialContext.lookup("java:global/gmail-mail"), (String) initialContext.lookup("java:global/gmail-passwort")));
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
			empfaengerTyp(mail.getAlleEmpfaenger(), RecipientType.TO, mimeMessage);
			empfaengerTyp(mail.getAlleInKopie(), RecipientType.CC, mimeMessage);
			empfaengerTyp(mail.getAlleInBlindkopie(), RecipientType.BCC, mimeMessage);
			mimeMessage.setSubject(mail.getBetreff(), CHARSET);
			mimeMessage.setText(mail.getInhalt(), CHARSET);

			Transport.send(mimeMessage);
		}
		catch (MessagingException | UnsupportedEncodingException e)
		{
			throw new MailServerException("Beim Versenden der Mail ist ein Fehler aufgetreten!", e);
		}
	}

	private void empfaengerTyp(List<MailAdresse> mailAdressen, RecipientType recipientType, MimeMessage mimeMessage) throws MessagingException
	{
		for (var mailAdresse : mailAdressen)
		{
			mimeMessage.addRecipient(recipientType, new InternetAddress(mailAdresse.getAdresse()));
		}
	}
}
