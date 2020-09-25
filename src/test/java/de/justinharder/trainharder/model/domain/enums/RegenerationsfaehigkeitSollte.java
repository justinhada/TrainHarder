package de.justinharder.trainharder.model.domain.enums;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RegenerationsfaehigkeitSollte
{
	@Test
	@DisplayName("IllegalArgumentException werfen, wenn der String nicht existiert")
	void test01()
	{
		final var exception =
			assertThrows(IllegalArgumentException.class,
				() -> Regenerationsfaehigkeit.fromString("UNGUELTIG"));

		assertThat(exception.getMessage())
			.isEqualTo("Der Wert \"UNGUELTIG\" für Regenerationsfähigkeit existiert nicht!");
	}

	@Test
	@DisplayName("die Regenerationsfaehigkeit aus dem String zurückgeben")
	void test02()
	{
		final var erwartet = Regenerationsfaehigkeit.PERFEKT;

		final var ergebnis = Regenerationsfaehigkeit.fromString("PERFEKT");

		assertThat(ergebnis).isEqualTo(erwartet);
	}
}
