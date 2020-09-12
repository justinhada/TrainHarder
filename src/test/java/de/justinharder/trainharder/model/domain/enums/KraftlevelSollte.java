package de.justinharder.trainharder.model.domain.enums;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.trainharder.model.domain.enums.Kraftlevel;

class KraftlevelSollte
{
	@Test
	@DisplayName("IllegalArgumentException werfen, wenn die Kraftlevel-Option nicht existiert")
	void test01()
	{
		final var exception =
			assertThrows(IllegalArgumentException.class, () -> Kraftlevel.fromKraftlevelOption("UNGUELTIG"));

		assertThat(exception.getMessage()).isEqualTo("Die Kraftlevel-Option \"UNGUELTIG\" existiert nicht!");
	}

	@Test
	@DisplayName("die Kraftlevel aus der Kraftlevel-Option zurückgeben")
	void test02()
	{
		final var erwartet = Kraftlevel.ELITE;

		final var ergebnis = Kraftlevel.fromKraftlevelOption("ELITE");

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("IllegalArgumentException werfen, wenn der Name nicht existiert")
	void test03()
	{
		final var exception = assertThrows(IllegalArgumentException.class, () -> Kraftlevel.fromName("UNGUELTIG"));

		assertThat(exception.getMessage()).isEqualTo("Der Name \"UNGUELTIG\" existiert nicht!");
	}

	@Test
	@DisplayName("die Kraftlevel aus dem Namen zurückgeben")
	void test04()
	{
		final var erwartet = Kraftlevel.MASTER;

		final var ergebnis = Kraftlevel.fromName("MASTER");

		assertThat(ergebnis).isEqualTo(erwartet);
	}
}
