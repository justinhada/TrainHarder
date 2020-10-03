package de.justinharder.trainharder.model.services.mail;

import de.justinharder.trainharder.model.domain.exceptions.MailServerException;

import javax.inject.Inject;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.List;

public class MailServer
{
	private static final String MAIL_PROPERTY = "mail.smtp.host";
	private static final String LOCALHOST = "localhost";

	private final Session session;
	private final MailSender mailSender;

	@Inject
	public MailServer()
	{
		final var properties = System.getProperties();
		properties.setProperty(MAIL_PROPERTY, LOCALHOST);
		session = Session.getDefaultInstance(properties);
		mailSender = mimeMessage ->
		{
			try
			{
				Transport.send(mimeMessage);
			}
			catch (final MessagingException e)
			{
				throw new MailServerException("Beim Versenden der Mail ist ein Fehler aufgetreten!", e);
			}
		};
	}

	public void sendeMail(final Mail mail, final Charset charset)
	{
		try
		{
			final var mimeMessage = new MimeMessage(session);

			mimeMessage.setFrom(new InternetAddress(mail.getSender().getAdresse(), mail.getSender().getName()));
			verarbeiteEmpfaengerMithilfeDesTypen(mail.getAlleEmpfaenger(), RecipientType.TO, mimeMessage);
			verarbeiteEmpfaengerMithilfeDesTypen(mail.getAlleInKopie(), RecipientType.CC, mimeMessage);
			verarbeiteEmpfaengerMithilfeDesTypen(mail.getAlleInBlindkopie(), RecipientType.BCC, mimeMessage);
			mimeMessage.setSubject(mail.getBetreff(), charset.name());
			mimeMessage.setText(mail.getInhalt(), charset.name());

			mailSender.sende(mimeMessage);
		}
		catch (final MessagingException | UnsupportedEncodingException e)
		{
			throw new MailServerException("Beim Versenden der Mail ist ein Fehler aufgetreten!", e);
		}
	}

	private void verarbeiteEmpfaengerMithilfeDesTypen(
		final List<MailAdresse> mailAdressen,
		final RecipientType recipientType,
		final MimeMessage mimeMessage)
	{
		mailAdressen
			.forEach(mailAdresse ->
			{
				try
				{
					mimeMessage.addRecipient(recipientType, new InternetAddress(mailAdresse.getAdresse()));
				}
				catch (final MessagingException e)
				{
					throw new MailServerException("Beim Hinzufügen eines Empfängers ist ein Fehler aufgetreten!", e);
				}
			});
	}
}
