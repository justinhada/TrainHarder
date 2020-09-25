package de.justinharder.trainharder.model.domain.enums;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ErnaehrungSollte
{
	@Test
	@DisplayName("IllegalArgumentException werfen, wenn der String nicht existiert")
	void test01()
	{
		final var exception =
			assertThrows(IllegalArgumentException.class, () -> Ernaehrung.fromString("UNGUELTIG"));

		assertThat(exception.getMessage()).isEqualTo("Der Wert \"UNGUELTIG\" für Ernährung existiert nicht!");
	}

	@Test
	@DisplayName("die Ernaehrung aus dem String zurückgeben")
	void test02()
	{
		final var erwartet = Ernaehrung.DURCHSCHNITT;

		final var ergebnis = Ernaehrung.fromString("DURCHSCHNITT");

		assertThat(ergebnis).isEqualTo(erwartet);
	}
}
