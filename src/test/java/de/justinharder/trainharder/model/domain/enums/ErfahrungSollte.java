package de.justinharder.trainharder.model.domain.enums;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ErfahrungSollte
{
	@Test
	@DisplayName("IllegalArgumentException werfen, wenn der String nicht existiert")
	void test01()
	{
		final var exception =
			assertThrows(IllegalArgumentException.class, () -> Erfahrung.fromString("UNGUELTIG"));

		assertThat(exception.getMessage()).isEqualTo("Der Wert \"UNGUELTIG\" für Erfahrung existiert nicht!");
	}

	@Test
	@DisplayName("die Erfahrung aus dem String zurückgeben")
	void test02()
	{
		final var erwartet = Erfahrung.EXPERTE;

		final var ergebnis = Erfahrung.fromString("EXPERTE");

		assertThat(ergebnis).isEqualTo(erwartet);
	}
}
