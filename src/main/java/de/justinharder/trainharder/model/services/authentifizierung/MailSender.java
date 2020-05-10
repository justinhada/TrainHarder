package de.justinharder.trainharder.model.services.authentifizierung;

import java.nio.charset.StandardCharsets;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.google.common.base.Preconditions;

import de.justinharder.trainharder.model.domain.dto.AuthentifizierungEintrag;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MailSender
{
	private static final String E_MAIL_ADRESSE = "mail@justinharder.de";
	private static final String MAIL_PROPERTY = "mail.smtp.host";
	private static final String LOCALHOST = "localhost";

	public boolean sendeRegistrierungMail(final AuthentifizierungEintrag authentifizierungEintrag)
	{
		Preconditions.checkNotNull(authentifizierungEintrag,
			"Es konnte keine Registierung-Mail gesendet werden, weil die Authentifizierung nicht gültig sind!");

		try
		{
			final var properties = System.getProperties();
			properties.setProperty(MAIL_PROPERTY, LOCALHOST);

			final var session = Session.getDefaultInstance(properties);

			final var message = new MimeMessage(session);
			message.setFrom(new InternetAddress(E_MAIL_ADRESSE));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(authentifizierungEintrag.getMail()));
			message.setSubject("Ihre Registrierung bei TrainHarder", StandardCharsets.UTF_8.name());
			message.setText(erstelleMailInhalt(authentifizierungEintrag));
			Transport.send(message);

			System.out.println("Mail wurde erfolgreich an " + authentifizierungEintrag.getMail() + " gesendet!");

			return true;
		}
		catch (final MessagingException e)
		{
			return false;
		}
	}

	private String erstelleMailInhalt(final AuthentifizierungEintrag authentifizierungEintrag)
	{
		final var link = "<a href=\"localhost:8080/TrainHarder/create.xhtml?id=" + authentifizierungEintrag.getId()
			+ "\">Bestätige E-Mail-Adresse und erstelle Benutzer</a>";

		return new StringBuilder("Hallo " + authentifizierungEintrag.getBenutzername() + ",\n")
			.append("deine Registrierung bei TrainHarder war erfolgreich! ")
			.append("Wir freuen uns sehr, dass du für uns entschieden hast.\n")
			.append(
				"Klicke auf untenstehenden Link, um deine E-Mail-Adresse zu verifizieren und so mit der Erstellung deines Benutzers fortzufahren.\n")
			.append(link + "\n\n")
			.append("Mit freundlichen Grüßen\n")
			.append("dein TrainHarder-Team!")
			.toString();
	}
}
