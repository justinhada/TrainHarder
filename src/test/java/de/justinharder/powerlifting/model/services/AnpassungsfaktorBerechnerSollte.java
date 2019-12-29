package de.justinharder.powerlifting.model.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.powerlifting.setup.Testdaten;

public class AnpassungsfaktorBerechnerSollte
{
	private AnpassungsfaktorBerechner sut;

	@BeforeEach
	public void setup()
	{
		sut = new AnpassungsfaktorBerechner();
	}

	@Test
	@DisplayName("NullPointerException werfen, wenn Benutzer null ist")
	public void test01()
	{
		final var e = assertThrows(NullPointerException.class, () -> sut.berechneAnpassungsfaktor(null));

		assertThat(e.getMessage())
			.isEqualTo("Der Benutzer, für den der Anpassungsfaktor berechnet werden soll, existiert nicht!");
	}

	@Test
	@DisplayName("den richtigen Anpassungsfaktor für Justin berechnen")
	public void test02()
	{
		assertThat(sut.berechneAnpassungsfaktor(Testdaten.JUSTIN)).isEqualTo(9);
	}

	@Test
	@DisplayName("den richtigen Anpassungsfaktor für Eduard berechnen")
	public void test03()
	{
		assertThat(sut.berechneAnpassungsfaktor(Testdaten.EDUARD)).isEqualTo(8);
	}

	@Test
	@DisplayName("den richtigen Anpassungsfaktor für Anna berechnen")
	public void test04()
	{
		assertThat(sut.berechneAnpassungsfaktor(Testdaten.ANNA)).isEqualTo(7);
	}

	@Test
	@DisplayName("den richtigen Anpassungsfaktor für Anette berechnen")
	public void test05()
	{
		assertThat(sut.berechneAnpassungsfaktor(Testdaten.ANETTE)).isEqualTo(-9);
	}

	@Test
	@DisplayName("den richtigen Anpassungsfaktor für Gott berechnen")
	public void test06()
	{
		assertThat(sut.berechneAnpassungsfaktor(Testdaten.GOTT)).isEqualTo(-3);
	}

	@Test
	@DisplayName("den richtigen Anpassungsfaktor für Baba berechnen")
	public void test07()
	{
		assertThat(sut.berechneAnpassungsfaktor(Testdaten.BABA)).isEqualTo(-2);
	}
}
