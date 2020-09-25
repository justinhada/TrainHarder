package de.justinharder.trainharder.model.domain.enums;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UebungsartSollte
{
	@Test
	@DisplayName("IllegalArgumentException werfen, wenn der String nicht existiert")
	void test01()
	{
		final var exception =
			assertThrows(IllegalArgumentException.class, () -> Uebungsart.fromString("UNGUELTIG"));

		assertThat(exception.getMessage()).isEqualTo("Der Wert \"UNGUELTIG\" für Übungsart existiert nicht!");
	}

	@Test
	@DisplayName("die Uebungsart aus dem String zurückgeben")
	void test02()
	{
		final var erwartet = Uebungsart.GRUNDUEBUNG;

		final var ergebnis = Uebungsart.fromString("GRUNDUEBUNG");

		assertThat(ergebnis).isEqualTo(erwartet);
	}
}
