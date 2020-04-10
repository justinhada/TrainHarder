package de.justinharder.powerlifting.model.services.registrierung;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MailSenderSollte
{
	private MailSender sut;

	@BeforeEach
	public void setup()
	{
		sut = new MailSender();
	}

	@Test
	@DisplayName("NullPointerException werfen, wenn die Authentifizierung null ist")
	public void test01()
	{
		final var erwartet =
			"Es konnte keine Registierung-Mail gesendet werden, weil die Authentifizierung nicht gültig sind!";

		final var exception = assertThrows(NullPointerException.class, () -> sut.sendeRegistrierungMail(null));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}
}
