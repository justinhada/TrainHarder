package de.justinharder.trainharder.model.services.berechnung;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.trainharder.setup.Testdaten;

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
		final var e = assertThrows(NullPointerException.class, () -> sut.berechneAnpassungsfaktor(null));

		assertThat(e.getMessage())
			.isEqualTo("Der Benutzer, für den der Anpassungsfaktor berechnet werden soll, existiert nicht!");
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
