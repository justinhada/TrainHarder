package de.justinharder.trainharder.model.domain.enums;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.trainharder.model.domain.enums.Doping;

public class DopingSollte
{
	@Test
	@DisplayName("IllegalArgumentException werfen, wenn die Doping-Option nicht existiert")
	public void test01()
	{
		final var exception = assertThrows(IllegalArgumentException.class, () -> Doping.fromDopingOption("UNGUELTIG"));

		assertThat(exception.getMessage()).isEqualTo("Die Doping-Option \"UNGUELTIG\" existiert nicht!");
	}

	@Test
	@DisplayName("das Doping aus der Doping-Option zurückgeben")
	public void test02()
	{
		final var erwartet = Doping.JA;

		final var ergebnis = Doping.fromDopingOption("JA");

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("IllegalArgumentException werfen, wenn der Name nicht existiert")
	public void test03()
	{
		final var exception = assertThrows(IllegalArgumentException.class, () -> Doping.fromName("UNGUELTIG"));

		assertThat(exception.getMessage()).isEqualTo("Der Name \"UNGUELTIG\" existiert nicht!");
	}

	@Test
	@DisplayName("das Doping aus dem Namen zurückgeben")
	public void test04()
	{
		final var erwartet = Doping.NEIN;

		final var ergebnis = Doping.fromName("NEIN");

		assertThat(ergebnis).isEqualTo(erwartet);
	}
}
