package de.justinharder.trainharder.model.services.berechnung;

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
		var erwartet = 16;

		var ergebnis = sut.berechneDiaetInWochen(90.0, 22.5, 10.0);

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("die Diätzeit in Tagen berechnen")
	void test02()
	{
		var erwartet = 112;

		var ergebnis = sut.berechneDiaetInTagen(90.0, 22.5, 10.0);

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("den erwarteten Körperfettanteil berechnen")
	void test03()
	{
		var erwartet = 15;

		var ergebnis = sut.berechneGeschaetztenKoerperfettAnteil(90.0, 22.5, 12);

		assertThat(ergebnis).isEqualTo(erwartet);
	}
}
