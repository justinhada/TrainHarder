package de.justinharder.trainharder.model.domain.enums;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.trainharder.model.domain.enums.Wiederholungen;

class WiederholungenSollte
{
	@Test
	@DisplayName("IllegalArgumentException werfen, wenn die Wiederholungen-Option nicht existiert")
	void test01()
	{
		final var exception =
			assertThrows(IllegalArgumentException.class, () -> Wiederholungen.fromWiederholungenOption("UNGUELTIG"));

		assertThat(exception.getMessage()).isEqualTo("Die Wiederholungen-Option \"UNGUELTIG\" existiert nicht!");
	}

	@Test
	@DisplayName("die Wiederholungen aus der Wiederholungen-Option zurückgeben")
	void test02()
	{
		final var erwartet = Wiederholungen.ONE_REP_MAX;

		final var ergebnis = Wiederholungen.fromWiederholungenOption("1RM");

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("IllegalArgumentException werfen, wenn der Name nicht existiert")
	void test03()
	{
		final var exception = assertThrows(IllegalArgumentException.class, () -> Wiederholungen.fromName("UNGUELTIG"));

		assertThat(exception.getMessage()).isEqualTo("Der Name \"UNGUELTIG\" existiert nicht!");
	}

	@Test
	@DisplayName("die Wiederholungen aus dem Namen zurückgeben")
	void test04()
	{
		final var erwartet = Wiederholungen.FIVE_REP_MAX;

		final var ergebnis = Wiederholungen.fromName("FIVE_REP_MAX");

		assertThat(ergebnis).isEqualTo(erwartet);
	}
}
