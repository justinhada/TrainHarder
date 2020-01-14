package de.justinharder.powerlifting.model.domain.enums;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UebungskategorieSollte
{

	@Test
	@DisplayName("IllegalArgumentException werfen, wenn die Uebungskategorie-Option nicht existiert")
	public void test01()
	{
		final var exception =
			assertThrows(IllegalArgumentException.class,
				() -> Uebungskategorie.fromUebungskategorieOption("UNGUELTIG"));

		assertThat(exception.getMessage()).isEqualTo("Die Uebungskategorie-Option \"UNGUELTIG\" existiert nicht!");
	}

	@Test
	@DisplayName("die Uebungskategorie aus der Uebungskategorie-Option zurückgeben")
	public void test02()
	{
		final var erwartet = Uebungskategorie.UEBERKOPFDRUECKEN;

		final var ergebnis = Uebungskategorie.fromUebungskategorieOption("UEBERKOPFDRUECKEN");

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("IllegalArgumentException werfen, wenn der Name nicht existiert")
	public void test03()
	{
		final var exception =
			assertThrows(IllegalArgumentException.class, () -> Uebungskategorie.fromName("UNGUELTIG"));

		assertThat(exception.getMessage()).isEqualTo("Der Name \"UNGUELTIG\" existiert nicht!");
	}

	@Test
	@DisplayName("die Uebungskategorie aus dem Namen zurückgeben")
	public void test04()
	{
		final var erwartet = Uebungskategorie.ASSISTENZ_CORE;

		final var ergebnis = Uebungskategorie.fromName("ASSISTENZ_CORE");

		assertThat(ergebnis).isEqualTo(erwartet);
	}
}
