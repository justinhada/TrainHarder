package de.justinharder.old.domain.services.berechnung;

import de.justinharder.old.domain.services.berechnung.Volumenrechner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Volumenrechner sollte")
class VolumenrechnerTest
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
		assertThat(sut.getVolumenHypertrophiePhase()).isEqualTo(new int[] { 20, 22, 17 });
	}

	@Test
	@DisplayName("die richtigen Mittelwerte für alle drei Grundübungen in der Kraft-Phase berechnen")
	void test02()
	{
		assertThat(sut.getVolumenKraftPhase()).isEqualTo(new int[] { 16, 19, 15 });
	}

	@Test
	@DisplayName("die richtigen Mittelwerte für alle drei Grundübungen in der Peaking-Phase berechnen")
	void test03()
	{
		assertThat(sut.getVolumenPeakingPhase()).isEqualTo(new int[] { 14, 17, 13 });
	}
}
