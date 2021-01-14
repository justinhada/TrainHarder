package de.justinharder.trainharder.model.services.mail;

import de.justinharder.trainharder.model.domain.exceptions.MailServerException;
import lombok.NonNull;

import javax.inject.Inject;
import javax.mail.*;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;

public class MailServer
{
	private static final String CHARSET = "utf-8";

	private final Session session;

	@Inject
	public MailServer()
	{
		var properties = new Properties();
		properties.setProperty("mail.smtp.host", "localhost");
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.port", "1530");
		session = Session.getInstance(properties, new Authenticator()
		{
			@Override
			protected PasswordAuthentication getPasswordAuthentication()
			{
				return new PasswordAuthentication("trainharder", "Justinharder#98");
			}
		});
	}

	public void sende(@NonNull Mail mail)
	{
		try
		{
			var mimeMessage = new MimeMessage(session);

			mimeMessage.setFrom(new InternetAddress(mail.getSender().getAdresse(), mail.getSender().getName()));
			verarbeiteEmpfaengerMithilfeDesTypen(mail.getAlleEmpfaenger(), RecipientType.TO, mimeMessage);
			verarbeiteEmpfaengerMithilfeDesTypen(mail.getAlleInKopie(), RecipientType.CC, mimeMessage);
			verarbeiteEmpfaengerMithilfeDesTypen(mail.getAlleInBlindkopie(), RecipientType.BCC, mimeMessage);
			mimeMessage.setSubject(mail.getBetreff(), CHARSET);
			mimeMessage.setText(mail.getInhalt(), CHARSET);

			Transport.send(mimeMessage);
		}
		catch (MessagingException | UnsupportedEncodingException e)
		{
			throw new MailServerException("Beim Versenden der Mail ist ein Fehler aufgetreten!", e);
		}
	}

	private void verarbeiteEmpfaengerMithilfeDesTypen(
		List<MailAdresse> mailAdressen,
		RecipientType recipientType,
		MimeMessage mimeMessage) throws MessagingException
	{
		for (var mailAdresse : mailAdressen)
		{
			mimeMessage.addRecipient(recipientType, new InternetAddress(mailAdresse.getAdresse()));
		}
	}
}
