package de.justinharder.trainharder.model.services.mail;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import de.justinharder.trainharder.model.domain.exceptions.MailServerException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MailServerSollte
{
	private static final GreenMail GREEN_MAIL = new GreenMail(new ServerSetup(530, "localhost", "smtp"));
	private static final Mail MAIL = new Mail(
		new MailAdresse("mail@justinharder.de", "TrainHarder-Team"),
		"Betreff",
		"Inhalt.")
		.fuegeEmpfaengerHinzu(new MailAdresse("justinharder@t-online.de", "Justin Harder"));

	private MailServer sut;

	@BeforeEach
	void setup()
	{
		sut = new MailServer();
	}

	@Test
	@DisplayName("MailServerException werfen, wenn die Mail nicht gesendet werden kann")
	void test01()
	{
		var exception = assertThrows(MailServerException.class, () -> sut.sende(MAIL));

		assertThat(exception.getMessage()).isEqualTo("Beim Versenden der Mail ist ein Fehler aufgetreten!");
	}

	@Test
	@DisplayName("null validieren")
	void test02()
	{
		assertThrows(NullPointerException.class, () -> sut.sende(null));
	}

	@Test
	@DisplayName("Mail senden")
	void test03()
	{
		GREEN_MAIL.setUser("mail@justinharder.de", "trainharder", "Justinharder#98");
		GREEN_MAIL.start();
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
		GREEN_MAIL.stop();
	}
}
