package de.justinharder.trainharder.domain.services.berechnung;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DiaetrechnerSollte
{
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
		assertThat(sut.berechneDiaetInWochen(90.0, 22.5, 10.0)).isEqualTo(16);
	}

	@Test
	@DisplayName("die Diätzeit in Tagen berechnen")
	void test02()
	{
		assertThat(sut.berechneDiaetInTagen(90.0, 22.5, 10.0)).isEqualTo(112);
	}

	@Test
	@DisplayName("den erwarteten Körperfettanteil berechnen")
	void test03()
	{
		assertThat(sut.berechneGeschaetztenKoerperfettAnteil(90.0, 22.5, 12)).isEqualTo(15);
	}
}
