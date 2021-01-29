package de.justinharder.trainharder.model.services.mail;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import de.justinharder.trainharder.model.domain.exceptions.MailServerException;
import org.junit.jupiter.api.*;

import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MailServerSollte
{
	private static final GreenMail GREEN_MAIL = new GreenMail(new ServerSetup(1530, "localhost", "smtp"));
	private static final Mail MAIL = new Mail(
		new MailAdresse("mail@justinharder.de", "TrainHarder-Team"),
		"Betreff",
		"Inhalt.")
		.fuegeEmpfaengerHinzu(new MailAdresse("justinharder@t-online.de", "Justin Harder"));

	private MailServer sut;

	@BeforeEach
	void setup()
	{
		var properties = new Properties();
		properties.setProperty("mail.smtp.host", "localhost");
		properties.setProperty("mail.smtp.port", "1530");
		properties.setProperty("mail.smtp.auth", "true");

		sut = new MailServer(properties, "trainharder", "Justinharder#98");
	}

	@BeforeAll
	static void setupClass()
	{
		GREEN_MAIL.setUser("mail@justinharder.de", "trainharder", "Justinharder#98");
		GREEN_MAIL.start();
	}

	@AfterAll
	static void resetClass()
	{
		GREEN_MAIL.stop();
	}

	@Test
	@DisplayName("MailServerException werfen, wenn die Mail nicht gesendet werden kann")
	void test01()
	{
		assertThrows(MailServerException.class, () -> new MailServer().sende(MAIL));
	}

	@Test
	@DisplayName("null validieren")
	void test02()
	{
		assertThrows(NullPointerException.class, () -> new MailServer().sende(null));
	}

	@Test
	@DisplayName("Mail senden")
	void test03()
	{
		sut.sende(MAIL);

		assertAll(
			() -> assertThat(GREEN_MAIL.waitForIncomingEmail(5000, 1)).isTrue(),
			() -> assertThat(GREEN_MAIL.getReceivedMessages()).hasSize(1),
			() -> assertThat(GREEN_MAIL.getReceivedMessages()[0].getContentType())
				.isEqualTo("text/plain; charset=utf-8"),
			() -> assertThat(GREEN_MAIL.getReceivedMessages()[0].getHeader("From")[0])
				.isEqualTo("TrainHarder-Team <mail@justinharder.de>"),
			() -> assertThat(GREEN_MAIL.getReceivedMessages()[0].getHeader("To")[0])
				.isEqualTo("justinharder@t-online.de"),
			() -> assertThat(GREEN_MAIL.getReceivedMessages()[0].getSubject()).isEqualTo("Betreff"),
			() -> assertThat(((String) GREEN_MAIL.getReceivedMessages()[0].getContent()).trim()).isEqualTo("Inhalt."));
	}
}
