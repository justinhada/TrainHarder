package de.justinharder.trainharder.model.domain.enums;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SchlafqualitaetSollte
{
	@Test
	@DisplayName("IllegalArgumentException werfen, wenn der String nicht existiert")
	void test01()
	{
		final var exception =
			assertThrows(IllegalArgumentException.class, () -> Schlafqualitaet.fromString("UNGUELTIG"));

		assertThat(exception.getMessage()).isEqualTo("Der Wert \"UNGUELTIG\" für Schlafqualität existiert nicht!");
	}

	@Test
	@DisplayName("die Schlafqualitaet aus dem String zurückgeben")
	void test02()
	{
		final var erwartet = Schlafqualitaet.DURCHSCHNITT;

		final var ergebnis = Schlafqualitaet.fromString("DURCHSCHNITT");

		assertThat(ergebnis).isEqualTo(erwartet);
	}
}
