package de.justinharder.trainharder.model.domain.enums;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.trainharder.model.domain.enums.Stress;

public class StressSollte
{
	@Test
	@DisplayName("IllegalArgumentException werfen, wenn die Stress-Option nicht existiert")
	public void test01()
	{
		final var exception =
			assertThrows(IllegalArgumentException.class, () -> Stress.fromStressOption("UNGUELTIG"));

		assertThat(exception.getMessage()).isEqualTo("Die Stress-Option \"UNGUELTIG\" existiert nicht!");
	}

	@Test
	@DisplayName("die Stress aus der Stress-Option zurückgeben")
	public void test02()
	{
		final var erwartet = Stress.MITTELMAESSIG;

		final var ergebnis = Stress.fromStressOption("MITTELMAESSIG");

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("IllegalArgumentException werfen, wenn der Name nicht existiert")
	public void test03()
	{
		final var exception = assertThrows(IllegalArgumentException.class, () -> Stress.fromName("UNGUELTIG"));

		assertThat(exception.getMessage()).isEqualTo("Der Name \"UNGUELTIG\" existiert nicht!");
	}

	@Test
	@DisplayName("die Stress aus dem Namen zurückgeben")
	public void test04()
	{
		final var erwartet = Stress.NIEDRIG;

		final var ergebnis = Stress.fromName("NIEDRIG");

		assertThat(ergebnis).isEqualTo(erwartet);
	}
}
