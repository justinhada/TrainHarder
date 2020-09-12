package de.justinharder.trainharder.model.domain.enums;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.trainharder.model.domain.enums.Geschlecht;

class GeschlechtSollte
{
	@Test
	@DisplayName("IllegalArgumentException werfen, wenn die Geschlecht-Option nicht existiert")
	void test01()
	{
		final var exception =
			assertThrows(IllegalArgumentException.class, () -> Geschlecht.fromGeschlechtOption("UNGUELTIG"));

		assertThat(exception.getMessage()).isEqualTo("Die Geschlecht-Option \"UNGUELTIG\" existiert nicht!");
	}

	@Test
	@DisplayName("die Geschlecht aus der Geschlecht-Option zurückgeben")
	void test02()
	{
		final var erwartet = Geschlecht.MAENNLICH;

		final var ergebnis = Geschlecht.fromGeschlechtOption("MAENNLICH");

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("IllegalArgumentException werfen, wenn der Name nicht existiert")
	void test03()
	{
		final var exception = assertThrows(IllegalArgumentException.class, () -> Geschlecht.fromName("UNGUELTIG"));

		assertThat(exception.getMessage()).isEqualTo("Der Name \"UNGUELTIG\" existiert nicht!");
	}

	@Test
	@DisplayName("die Geschlecht aus dem Namen zurückgeben")
	void test04()
	{
		final var erwartet = Geschlecht.WEIBLICH;

		final var ergebnis = Geschlecht.fromName("WEIBLICH");

		assertThat(ergebnis).isEqualTo(erwartet);
	}
}
