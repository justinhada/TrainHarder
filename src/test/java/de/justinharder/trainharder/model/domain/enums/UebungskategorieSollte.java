package de.justinharder.trainharder.model.domain.enums;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UebungskategorieSollte
{

	@Test
	@DisplayName("IllegalArgumentException werfen, wenn der String nicht existiert")
	void test01()
	{
		final var exception =
			assertThrows(IllegalArgumentException.class,
				() -> Uebungskategorie.fromString("UNGUELTIG"));

		assertThat(exception.getMessage()).isEqualTo("Der Wert \"UNGUELTIG\" für Übungskategorie existiert nicht!");
	}

	@Test
	@DisplayName("die Uebungskategorie aus dem String zurückgeben")
	void test02()
	{
		final var erwartet = Uebungskategorie.UEBERKOPFDRUECKEN;

		final var ergebnis = Uebungskategorie.fromString("UEBERKOPFDRUECKEN");

		assertThat(ergebnis).isEqualTo(erwartet);
	}
}
