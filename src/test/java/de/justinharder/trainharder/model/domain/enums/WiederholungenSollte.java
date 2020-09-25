package de.justinharder.trainharder.model.domain.enums;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WiederholungenSollte
{
	@Test
	@DisplayName("IllegalArgumentException werfen, wenn der String nicht existiert")
	void test01()
	{
		final var exception =
			assertThrows(IllegalArgumentException.class, () -> Wiederholungen.fromString("UNGUELTIG"));

		assertThat(exception.getMessage()).isEqualTo("Der Wert \"UNGUELTIG\" für Wiederholungen existiert nicht!");
	}

	@Test
	@DisplayName("die Wiederholungen aus dem String zurückgeben")
	void test02()
	{
		final var erwartet = Wiederholungen.ONE_REP_MAX;

		final var ergebnis = Wiederholungen.fromString("1RM");

		assertThat(ergebnis).isEqualTo(erwartet);
	}
}
