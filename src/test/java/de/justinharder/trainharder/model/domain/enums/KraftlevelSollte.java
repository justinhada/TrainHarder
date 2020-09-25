package de.justinharder.trainharder.model.domain.enums;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KraftlevelSollte
{
	@Test
	@DisplayName("IllegalArgumentException werfen, wenn der String nicht existiert")
	void test01()
	{
		final var exception =
			assertThrows(IllegalArgumentException.class, () -> Kraftlevel.fromString("UNGUELTIG"));

		assertThat(exception.getMessage()).isEqualTo("Der Wert \"UNGUELTIG\" für Kraftlevel existiert nicht!");
	}

	@Test
	@DisplayName("das Kraftlevel aus dem String zurückgeben")
	void test02()
	{
		final var erwartet = Kraftlevel.ELITE;

		final var ergebnis = Kraftlevel.fromString("ELITE");

		assertThat(ergebnis).isEqualTo(erwartet);
	}
}
