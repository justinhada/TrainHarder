package de.justinharder.trainharder.view.navigation;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DatenschutzControllerSollte
{
	private DatenschutzController sut;

	@BeforeEach
	public void setup()
	{
		sut = new DatenschutzController();
	}

	@Test
	@DisplayName("zur Datenschutz-Seite per GET navigieren")
	public void test01()
	{
		final var erwartet = "/datenschutz.xhtml";

		final var ergebnis = sut.index();

		assertThat(ergebnis).isEqualTo(erwartet);
	}
}
