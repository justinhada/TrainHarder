package de.justinharder.powerlifting.model.domain.enums;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ErfahrungSollte
{
	@Test
	@DisplayName("IllegalArgumentException werfen, wenn die Erfahrung-Option nicht existiert")
	public void test01()
	{
		final var exception =
			assertThrows(IllegalArgumentException.class, () -> Erfahrung.fromErfahrungOption("UNGUELTIG"));

		assertThat(exception.getMessage()).isEqualTo("Die Erfahrung-Option \"UNGUELTIG\" existiert nicht!");
	}

	@Test
	@DisplayName("die Erfahrung aus der Erfahrung-Option zurückgeben")
	public void test02()
	{
		final var erwartet = Erfahrung.EXPERTE;

		final var ergebnis = Erfahrung.fromErfahrungOption("EXPERTE");

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("IllegalArgumentException werfen, wenn der Name nicht existiert")
	public void test03()
	{
		final var exception = assertThrows(IllegalArgumentException.class, () -> Erfahrung.fromName("UNGUELTIG"));

		assertThat(exception.getMessage()).isEqualTo("Der Name \"UNGUELTIG\" existiert nicht!");
	}

	@Test
	@DisplayName("die Erfahrung aus dem Namen zurückgeben")
	public void test04()
	{
		final var erwartet = Erfahrung.BEGINNER;

		final var ergebnis = Erfahrung.fromName("BEGINNER");

		assertThat(ergebnis).isEqualTo(erwartet);
	}
}
