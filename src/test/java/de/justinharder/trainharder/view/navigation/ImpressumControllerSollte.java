package de.justinharder.trainharder.view.navigation;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ImpressumControllerSollte
{
	private ImpressumController sut;

	@BeforeEach
	public void setup()
	{
		sut = new ImpressumController();
	}

	@Test
	@DisplayName("zur Impressum-Seite per GET navigierne")
	public void test01()
	{
		final var erwartet = "/impressum.xhtml";

		final var ergebnis = sut.index();

		assertThat(ergebnis).isEqualTo(erwartet);
	}
}
