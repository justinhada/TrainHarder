package de.justinharder.trainharder.model.services.berechnung;

import de.justinharder.trainharder.setup.Testdaten;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AnpassungsfaktorBerechnerSollte
{
	private AnpassungsfaktorBerechner sut;

	@BeforeEach
	void setup()
	{
		sut = new AnpassungsfaktorBerechner();
	}

	@Test
	@DisplayName("NullPointerException werfen, wenn Benutzer null ist")
	void test01()
	{
		var exception = assertThrows(NullPointerException.class, () -> sut.berechneAnpassungsfaktor(null));

		assertThat(exception.getMessage()).isEqualTo("Benutzer ist null!");
	}

	@Test
	@DisplayName("den richtigen Anpassungsfaktor für Justin berechnen")
	void test02()
	{
		assertThat(sut.berechneAnpassungsfaktor(Testdaten.BENUTZER_JUSTIN)).isEqualTo(9);
	}

	@Test
	@DisplayName("den richtigen Anpassungsfaktor für Eduard berechnen")
	void test03()
	{
		assertThat(sut.berechneAnpassungsfaktor(Testdaten.BENUTZER_EDUARD)).isEqualTo(7);
	}
}
