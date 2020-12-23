package de.justinharder.trainharder.model.services.mail;

import de.justinharder.trainharder.model.domain.exceptions.MailServerException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MailServerSollte
{
	private static final Mail MAIL = new Mail(
		new MailAdresse("mail@justinharder.de", "TrainHarder-Team"),
		"Betreff",
		"Inhalt")
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
		var exception = assertThrows(MailServerException.class, () -> sut.sendeMail(MAIL, StandardCharsets.UTF_8));

		assertThat(exception.getMessage()).isEqualTo("Beim Versenden der Mail ist ein Fehler aufgetreten!");
	}

	@Test
	@DisplayName("null validieren")
	void test02()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> sut.sendeMail(null, StandardCharsets.UTF_8)),
			() -> assertThrows(NullPointerException.class, () -> sut.sendeMail(MAIL, null)));
	}
}
