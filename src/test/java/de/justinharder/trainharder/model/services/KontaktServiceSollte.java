package de.justinharder.trainharder.model.services;

import de.justinharder.trainharder.model.services.mail.Mail;
import de.justinharder.trainharder.model.services.mail.MailAdresse;
import de.justinharder.trainharder.model.services.mail.MailServer;
import de.justinharder.trainharder.view.dto.Kontaktformular;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class KontaktServiceSollte
{
	private KontaktService sut;

	private MailServer mailServer;

	@BeforeEach
	void setup()
	{
		mailServer = mock(MailServer.class);

		sut = new KontaktService(mailServer);
	}

	@Test
	@DisplayName("NullPointerException werfen, wenn das Kontaktformular null ist")
	void test01()
	{
		var erwartet = "Zum Kontaktieren wird ein gültiges Kontaktformular benötigt!";

		var exception = assertThrows(NullPointerException.class, () -> sut.kontaktiere(null));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("erfolgreich kontaktieren und Mail an MailServer weitergeben")
	void test02()
	{
		var kontaktformular = new Kontaktformular(
			"mail@justinharder.de",
			"harder",
			"Justin",
			"Harder",
			"Ich habe ein Problem.");

		sut.kontaktiere(kontaktformular);

		verify(mailServer).sendeMail(new Mail(
				new MailAdresse("mail@justinharder.de", "TrainHarder-Team"),
				"Support-Anfrage von " + kontaktformular.getBenutzername(),
				"Eine Support-Anfrage von " + kontaktformular.getBenutzername() + "\n"
					+ "Benutzer:\n"
					+ "\tBenutzername: " + kontaktformular.getBenutzername() + "\n"
					+ "\tE-Mail-Adresse: " + kontaktformular.getMail() + "\n"
					+ "\tName: " + kontaktformular.getVorname() + " " + kontaktformular.getNachname() + "\n"
					+ "Nachricht:\n"
					+ "\t" + kontaktformular.getNachricht())
				.fuegeEmpfaengerHinzu(new MailAdresse("justinharder@t-online.de", "Justin Harder")),
			StandardCharsets.UTF_8);
	}
}
