package de.justinharder.trainharder.model.services.mail;

import javax.mail.internet.MimeMessage;

@FunctionalInterface
public interface MailSender
{
	void sende(MimeMessage mimeMessage);
}
