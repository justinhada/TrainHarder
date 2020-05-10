package de.justinharder.trainharder.model.services.berechner;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.trainharder.model.services.berechner.Diaetrechner;

public class DiaetrechnerSollte
{
	private Diaetrechner sut;

	@BeforeEach
	public void setup()
	{
		sut = new Diaetrechner();
	}

	@Test
	@DisplayName("die Diätzeit in Wochen berechnen")
	public void test01()
	{
		final var erwartet = 16;

		final var ergebnis = sut.berechneDiaetInWochen(90.0, 22.5, 10.0);

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("die Diätzeit in Tagen berechnen")
	public void test02()
	{
		final var erwartet = 112;

		final var ergebnis = sut.berechneDiaetInTagen(90.0, 22.5, 10.0);

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("den erwarteten Körperfettanteil berechnen")
	public void test03()
	{
		final var erwartet = 15;

		final var ergebnis = sut.berechneGeschaetztenKoerperfettAnteil(90.0, 22.5, 12);

		assertThat(ergebnis).isEqualTo(erwartet);
	}
}
