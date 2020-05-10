package de.justinharder.trainharder.model.domain.enums;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.trainharder.model.domain.enums.Regenerationsfaehigkeit;

public class RegenerationsfaehigkeitSollte
{
	@Test
	@DisplayName("IllegalArgumentException werfen, wenn die Regenerationsfaehigkeit-Option nicht existiert")
	public void test01()
	{
		final var exception =
			assertThrows(IllegalArgumentException.class,
				() -> Regenerationsfaehigkeit.fromRegenerationsfaehigkeitOption("UNGUELTIG"));

		assertThat(exception.getMessage())
			.isEqualTo("Die Regenerationsfaehigkeit-Option \"UNGUELTIG\" existiert nicht!");
	}

	@Test
	@DisplayName("die Regenerationsfaehigkeit aus der Regenerationsfaehigkeit-Option zurückgeben")
	public void test02()
	{
		final var erwartet = Regenerationsfaehigkeit.PERFEKT;

		final var ergebnis = Regenerationsfaehigkeit.fromRegenerationsfaehigkeitOption("PERFEKT");

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("IllegalArgumentException werfen, wenn der Name nicht existiert")
	public void test03()
	{
		final var exception =
			assertThrows(IllegalArgumentException.class, () -> Regenerationsfaehigkeit.fromName("UNGUELTIG"));

		assertThat(exception.getMessage()).isEqualTo("Der Name \"UNGUELTIG\" existiert nicht!");
	}

	@Test
	@DisplayName("die Regenerationsfaehigkeit aus dem Namen zurückgeben")
	public void test04()
	{
		final var erwartet = Regenerationsfaehigkeit.SCHLECHT;

		final var ergebnis = Regenerationsfaehigkeit.fromName("SCHLECHT");

		assertThat(ergebnis).isEqualTo(erwartet);
	}
}
