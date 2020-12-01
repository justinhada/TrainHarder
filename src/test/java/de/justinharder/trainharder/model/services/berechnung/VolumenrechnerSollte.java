package de.justinharder.trainharder.model.services.berechnung;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class VolumenrechnerSollte
{
	private Volumenrechner sut;

	@BeforeEach
	void setup()
	{
		sut = new Volumenrechner(9);
	}

	@Test
	@DisplayName("die richtigen Mittelwerte für alle drei Grundübungen in der Hypertrophie-Phase berechnen")
	void test01()
	{
		var erwartet = new int[]
		{ 20, 22, 17 };

		assertThat(sut.getVolumenHypertrophiePhase()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("die richtigen Mittelwerte für alle drei Grundübungen in der Kraft-Phase berechnen")
	void test02()
	{
		var erwartet = new int[]
		{ 16, 19, 15 };

		assertThat(sut.getVolumenKraftPhase()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("die richtigen Mittelwerte für alle drei Grundübungen in der Peaking-Phase berechnen")
	void test03()
	{
		var erwartet = new int[]
		{ 14, 17, 13 };

		assertThat(sut.getVolumenPeakingPhase()).isEqualTo(erwartet);
	}
}
