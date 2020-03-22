package de.justinharder.powerlifting.model.services.berechner;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class VolumenrechnerSollte
{
	private Volumenrechner sut;

	@BeforeEach
	public void setup()
	{
		sut = new Volumenrechner(9);
	}

	@Test
	@DisplayName("die richtigen Mittelwerte für alle drei Grundübungen in der Hypertrophie-Phase berechnen")
	public void test01()
	{
		final var erwartet = new int[]
		{ 20, 22, 17 };

		assertThat(sut.getVolumenHypertrophiePhase()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("die richtigen Mittelwerte für alle drei Grundübungen in der Kraft-Phase berechnen")
	public void test02()
	{
		final var erwartet = new int[]
		{ 16, 19, 15 };

		assertThat(sut.getVolumenKraftPhase()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("die richtigen Mittelwerte für alle drei Grundübungen in der Peaking-Phase berechnen")
	public void test03()
	{
		final var erwartet = new int[]
		{ 14, 17, 13 };

		assertThat(sut.getVolumenPeakingPhase()).isEqualTo(erwartet);
	}
}
