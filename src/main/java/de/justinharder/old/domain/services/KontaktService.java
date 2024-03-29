package de.justinharder.old.domain.services;

import de.justinharder.old.domain.services.dto.Kontaktformular;
import de.justinharder.old.domain.services.mail.Mail;
import de.justinharder.old.domain.services.mail.MailAdresse;
import de.justinharder.old.domain.services.mail.MailServer;
import jakarta.enterprise.context.Dependent;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Dependent
@RequiredArgsConstructor
public class KontaktService
{
	@NonNull
	private final MailServer mailServer;

	public void kontaktiere(@NonNull Kontaktformular kontaktformular)
	{
		var name = kontaktformular.getVorname() + " " + kontaktformular.getNachname();
		var mail = new Mail(
			new MailAdresse("trainharder2021@gmail.com", "TrainHarder-Team"),
			"Support-Anfrage von " + name,
			"Eine Support-Anfrage von " + name + "\n"
				+ "Benutzer:\n"
				+ "\tBenutzername: " + kontaktformular.getBenutzername() + "\n"
				+ "\tE-Mail-Adresse: " + kontaktformular.getMail() + "\n"
				+ "\tName: " + name + "\n"
				+ "Nachricht:\n"
				+ "\t" + kontaktformular.getNachricht())
			.fuegeEmpfaengerHinzu(new MailAdresse("justinharder@t-online.de", "Justin Harder"));

		log.info("{}", mail);
		mailServer.sende(mail);
	}
}
