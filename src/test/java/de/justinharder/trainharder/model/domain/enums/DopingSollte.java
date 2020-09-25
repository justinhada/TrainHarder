package de.justinharder.trainharder.model.domain.enums;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DopingSollte
{
	@Test
	@DisplayName("IllegalArgumentException werfen, wenn der String nicht existiert")
	void test01()
	{
		final var exception = assertThrows(IllegalArgumentException.class, () -> Doping.fromString("UNGUELTIG"));

		assertThat(exception.getMessage()).isEqualTo("Der Wert \"UNGUELTIG\" für Doping existiert nicht!");
	}

	@Test
	@DisplayName("das Doping aus dem String zurückgeben")
	void test02()
	{
		final var erwartet = Doping.JA;

		final var ergebnis = Doping.fromString("JA");

		assertThat(ergebnis).isEqualTo(erwartet);
	}
}
