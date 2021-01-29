package de.justinharder.trainharder.model.services.mail;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MailAuthentifizierer
{
	public static Authenticator aus(String benutzername, String passwort)
	{
		return new Authenticator()
		{
			@Override
			protected PasswordAuthentication getPasswordAuthentication()
			{
				return new PasswordAuthentication(benutzername, passwort);
			}
		};
	}
}
