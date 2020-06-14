package de.justinharder.trainharder.model.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.trainharder.model.services.mail.MailServer;

public class KontaktServiceSollte
{
	private KontaktService sut;

	private MailServer mailServer;

	@BeforeEach
	public void setup()
	{
		mailServer = mock(MailServer.class);
		sut = new KontaktService(mailServer);
	}

	@Test
	@DisplayName("NullPointerException werfen, wenn das Kontaktformular null ist")
	public void test01()
	{
		final var erwartet = "Zum Kontaktieren wird ein gültiges Kontaktformular benötigt!";

		final var exception = assertThrows(NullPointerException.class, () -> sut.kontaktiere(null));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}
}
