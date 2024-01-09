package de.justinharder.old.domain.services.mail;

import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

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
