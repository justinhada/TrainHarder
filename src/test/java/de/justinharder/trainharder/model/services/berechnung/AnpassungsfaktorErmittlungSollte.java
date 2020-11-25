package de.justinharder.trainharder.model.services.berechnung;

import de.justinharder.trainharder.model.services.berechnung.anpassungsfaktor.AnpassungsfaktorErmittlung;
import de.justinharder.trainharder.setup.Testdaten;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AnpassungsfaktorErmittlungSollte
{
	private AnpassungsfaktorErmittlung sut;

	@BeforeEach
	void setup()
	{
		sut = new AnpassungsfaktorErmittlung();
	}

	@Test
	@DisplayName("NullPointerException werfen, wenn Benutzer null ist")
	void test01()
	{
		var erwartet = "Benutzer ist null!";

		var exception = assertThrows(NullPointerException.class, () -> sut.berechneAnpassungsfaktor(null));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("den richtigen Anpassungsfaktor für Justin berechnen")
	void test02()
	{
		assertAll(
			() -> assertThat(sut.berechneAnpassungsfaktor(Testdaten.BENUTZER_JUSTIN).werteAus()).isEqualTo(9),
			() -> assertThat(sut.berechneAnpassungsfaktor(Testdaten.BENUTZER_EDUARD).werteAus()).isEqualTo(7)
		);
	}
}
