package de.justinharder.old.domain.services;

import de.justinharder.old.domain.services.KontaktService;
import de.justinharder.old.domain.services.mail.Mail;
import de.justinharder.old.domain.services.mail.MailAdresse;
import de.justinharder.old.domain.services.mail.MailServer;
import de.justinharder.old.domain.services.dto.Kontaktformular;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@DisplayName("KontaktService sollte")
class KontaktServiceTest
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
	@DisplayName("null validieren")
	void test01()
	{
		assertThrows(NullPointerException.class, () -> sut.kontaktiere(null));
	}

	@Test
	@DisplayName("erfolgreich kontaktieren und Mail an MailServer weitergeben")
	void test02()
	{
		var vorname = "Justin";
		var nachname = "Harder";
		var kontaktformular = new Kontaktformular("mail@justinharder.de", "harder", vorname, nachname, "Ich habe ein Problem.");

		sut.kontaktiere(kontaktformular);

		var name = vorname + " " + nachname;
		verify(mailServer).sende(new Mail(
			new MailAdresse("trainharder2021@gmail.com", "TrainHarder-Team"),
			"Support-Anfrage von " + name,
			"Eine Support-Anfrage von " + name + "\n"
				+ "Benutzer:\n"
				+ "\tBenutzername: " + kontaktformular.getBenutzername() + "\n"
				+ "\tE-Mail-Adresse: " + kontaktformular.getMail() + "\n"
				+ "\tName: " + name + "\n"
				+ "Nachricht:\n"
				+ "\t" + kontaktformular.getNachricht())
			.fuegeEmpfaengerHinzu(new MailAdresse("justinharder@t-online.de", "Justin Harder")));
	}
}
