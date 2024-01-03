package de.justinharder.trainharder.domain.services.berechnung;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Diaetrechner sollte")
class DiaetrechnerTest
{
	private static final double KOERPERGEWICHT_IST = 90.0;
	private static final double KOERPERFETT_ANTEIL_IST = 22.5;
	private static final double KOERPERFETT_ANTEIL_SOLL = 10.0;

	private Diaetrechner sut;

	@BeforeEach
	void setup()
	{
		sut = new Diaetrechner();
	}

	@Test
	@DisplayName("die Diätzeit in Wochen berechnen")
	void test01()
	{
		assertThat(sut.berechneDiaetInWochen(KOERPERGEWICHT_IST, KOERPERFETT_ANTEIL_IST, KOERPERFETT_ANTEIL_SOLL))
			.isEqualTo(16);
	}

	@Test
	@DisplayName("die Diätzeit in Tagen berechnen")
	void test02()
	{
		assertThat(sut.berechneDiaetInTagen(KOERPERGEWICHT_IST, KOERPERFETT_ANTEIL_IST, KOERPERFETT_ANTEIL_SOLL))
			.isEqualTo(112);
	}

	@Test
	@DisplayName("den erwarteten Körperfettanteil berechnen")
	void test03()
	{
		assertThat(sut.berechneGeschaetztenKoerperfettAnteil(KOERPERGEWICHT_IST, KOERPERFETT_ANTEIL_IST, 12))
			.isEqualTo(15);
	}
}
