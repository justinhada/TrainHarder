package de.justinharder.trainharder.view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.trainharder.model.services.berechner.Diaetrechner;

public class DiaetControllerSollte extends ControllerSollte
{
	private DiaetController sut;
	private Diaetrechner diaetrechner;

	@BeforeEach
	public void setup()
	{
		diaetrechner = mock(Diaetrechner.class);
		sut = new DiaetController(externerWebContext, navigator, diaetrechner);
	}

	private void angenommenDerDiaetrechnerGibtDieDiaetInWochenZurueck(final int erwartet)
	{
		when(diaetrechner.berechneDiaetInWochen(anyDouble(), anyDouble(), anyDouble())).thenReturn(erwartet);
	}

	private void angenommenDerDiaetrechnerGibtDieDiaetInTagenZurueck(final int erwartet)
	{
		when(diaetrechner.berechneDiaetInTagen(anyDouble(), anyDouble(), anyDouble())).thenReturn(erwartet);
	}

	private void angenommenDerDiaetrechnerGibtDenGeschaetztenKoerperfettAnteilZurueck(final int erwartet)
	{
		when(diaetrechner.berechneGeschaetztenKoerperfettAnteil(anyDouble(), anyDouble(), anyInt()))
			.thenReturn(erwartet);
	}

	@Test
	@DisplayName("die Diätzeit in Wochen zurückgeben")
	public void test01()
	{
		final var erwartet = 16;
		angenommenDerDiaetrechnerGibtDieDiaetInWochenZurueck(erwartet);

		sut.setKoerpergewichtIst(90.0);
		sut.setKoerperfettAnteilIst(22.5);
		sut.setKoerperfettAnteilSoll(10.0);
		final var ergebnis = sut.getDiaetInWochen();

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("die Diätzeit in Tagen zurückgeben")
	public void test02()
	{
		final var erwartet = 112;
		angenommenDerDiaetrechnerGibtDieDiaetInTagenZurueck(erwartet);

		sut.setKoerpergewichtIst(90.0);
		sut.setKoerperfettAnteilIst(22.5);
		sut.setKoerperfettAnteilSoll(10.0);
		final var ergebnis = sut.getDiaetInTagen();

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("den geschätzten Körperfettanteil zurückgeben")
	public void test03()
	{
		final var erwartet = 15;
		angenommenDerDiaetrechnerGibtDenGeschaetztenKoerperfettAnteilZurueck(erwartet);

		sut.setKoerpergewichtIst(90.0);
		sut.setKoerperfettAnteilIst(22.5);
		sut.setDiaetWochen(12);
		final var ergebnis = sut.getGeschaetztenKoerperfettAnteil();

		assertThat(ergebnis).isEqualTo(erwartet);
	}
}
