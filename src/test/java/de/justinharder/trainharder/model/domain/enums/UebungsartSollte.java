package de.justinharder.trainharder.model.domain.enums;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.trainharder.model.domain.enums.Uebungsart;

class UebungsartSollte
{
	@Test
	@DisplayName("IllegalArgumentException werfen, wenn die Uebungsart-Option nicht existiert")
	void test01()
	{
		final var exception =
			assertThrows(IllegalArgumentException.class, () -> Uebungsart.fromUebungsartOption("UNGUELTIG"));

		assertThat(exception.getMessage()).isEqualTo("Die Uebungsart-Option \"UNGUELTIG\" existiert nicht!");
	}

	@Test
	@DisplayName("die Uebungsart aus der Uebungsart-Option zurückgeben")
	void test02()
	{
		final var erwartet = Uebungsart.GRUNDUEBUNG;

		final var ergebnis = Uebungsart.fromUebungsartOption("GRUNDUEBUNG");

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("IllegalArgumentException werfen, wenn der Name nicht existiert")
	void test03()
	{
		final var exception = assertThrows(IllegalArgumentException.class, () -> Uebungsart.fromName("UNGUELTIG"));

		assertThat(exception.getMessage()).isEqualTo("Der Name \"UNGUELTIG\" existiert nicht!");
	}

	@Test
	@DisplayName("die Uebungsart aus dem Namen zurückgeben")
	void test04()
	{
		final var erwartet = Uebungsart.ASSISTENZ;

		final var ergebnis = Uebungsart.fromName("ASSISTENZ");

		assertThat(ergebnis).isEqualTo(erwartet);
	}
}
