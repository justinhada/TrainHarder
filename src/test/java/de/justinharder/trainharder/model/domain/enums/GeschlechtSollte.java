package de.justinharder.trainharder.model.domain.enums;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GeschlechtSollte
{
	@Test
	@DisplayName("IllegalArgumentException werfen, wenn der String nicht existiert")
	void test01()
	{
		final var exception =
			assertThrows(IllegalArgumentException.class, () -> Geschlecht.fromString("UNGUELTIG"));

		assertThat(exception.getMessage()).isEqualTo("Der Wert \"UNGUELTIG\" für Geschlecht existiert nicht!");
	}

	@Test
	@DisplayName("das Geschlecht aus dem String zurückgeben")
	void test02()
	{
		final var erwartet = Geschlecht.MAENNLICH;

		final var ergebnis = Geschlecht.fromString("MAENNLICH");

		assertThat(ergebnis).isEqualTo(erwartet);
	}
}
