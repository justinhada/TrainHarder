package de.justinharder.trainharder.model.services.mail;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.trainharder.model.domain.exceptions.MailServerException;

class MailServerSollte
{
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
		final var erwartet = "Beim Versenden der Mail ist ein Fehler aufgetreten!";

		final var exception = assertThrows(MailServerException.class, () -> sut.sendeMail(new Mail(
			new MailAdresse("mail@justinharder.de", "TrainHarder-Team"),
			"Betreff",
			"Inhalt")
				.fuegeEmpfaengerHinzu(new MailAdresse("justinharder@t-online.de", "Justin Harder")),
			StandardCharsets.UTF_8));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}
}
