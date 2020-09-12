package de.justinharder.trainharder.model.services.berechnung;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.trainharder.model.services.berechnung.Diaetrechner;

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
		final var erwartet = 16;

		final var ergebnis = sut.berechneDiaetInWochen(90.0, 22.5, 10.0);

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("die Diätzeit in Tagen berechnen")
	void test02()
	{
		final var erwartet = 112;

		final var ergebnis = sut.berechneDiaetInTagen(90.0, 22.5, 10.0);

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("den erwarteten Körperfettanteil berechnen")
	void test03()
	{
		final var erwartet = 15;

		final var ergebnis = sut.berechneGeschaetztenKoerperfettAnteil(90.0, 22.5, 12);

		assertThat(ergebnis).isEqualTo(erwartet);
	}
}
