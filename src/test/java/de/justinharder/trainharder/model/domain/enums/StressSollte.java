package de.justinharder.trainharder.model.domain.enums;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StressSollte
{
	@Test
	@DisplayName("IllegalArgumentException werfen, wenn der String nicht existiert")
	void test01()
	{
		final var exception =
			assertThrows(IllegalArgumentException.class, () -> Stress.fromString("UNGUELTIG"));

		assertThat(exception.getMessage()).isEqualTo("Der Wert \"UNGUELTIG\" für Stress existiert nicht!");
	}

	@Test
	@DisplayName("den Stress aus dem String zurückgeben")
	void test02()
	{
		final var erwartet = Stress.MITTELMAESSIG;

		final var ergebnis = Stress.fromString("MITTELMAESSIG");

		assertThat(ergebnis).isEqualTo(erwartet);
	}
}
