package de.justinharder.powerlifting.view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.powerlifting.model.domain.exceptions.UngueltigeRepsInReserveException;
import de.justinharder.powerlifting.model.domain.exceptions.UngueltigeWiederholungenException;
import de.justinharder.powerlifting.model.domain.exceptions.UngueltigesMaximumException;
import de.justinharder.powerlifting.model.services.RepsInReserveRechner;

public class RepsInReserveControllerSollte
{
	private static final int MAXIMUM = 100;
	private static final int WIEDERHOLUNGEN = 5;
	private static final int RIR = 2;

	private RepsInReserveController sut;
	private RepsInReserveRechner repsInReserveRechner;

	@BeforeEach
	public void setup()
	{
		repsInReserveRechner = mock(RepsInReserveRechner.class);
		sut = new RepsInReserveController(repsInReserveRechner);
	}

	private void angenommenDerRepsInReserveRechnerGibtRichtwertZurueck(final int maximum, final int wiederholungen,
		final int rir, final int erwartet)
		throws UngueltigesMaximumException, UngueltigeWiederholungenException, UngueltigeRepsInReserveException
	{
		when(repsInReserveRechner.berechneRichtwert(maximum, wiederholungen, rir)).thenReturn(erwartet);
	}

	private void angenommenDerRepsInReserveRechnerGibtProzenteTabelleZurueck(final double[][] erwartet)
	{
		when(repsInReserveRechner.getProzente()).thenReturn(erwartet);
	}

	@Test
	@DisplayName("den richtigen Richtwert aus eingegebenen Werten zurückgeben")
	public void test01()
		throws UngueltigesMaximumException, UngueltigeWiederholungenException, UngueltigeRepsInReserveException
	{
		final var erwartet = 82;
		angenommenDerRepsInReserveRechnerGibtRichtwertZurueck(MAXIMUM, WIEDERHOLUNGEN, RIR, erwartet);
		sut.setMaximum(MAXIMUM);
		sut.setWiederholungen(WIEDERHOLUNGEN);
		sut.setRir(RIR);

		sut.ermittleRichtwert();
		final var ergebnis = sut.getRichtwert();

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("die Prozente-Tabelle aus dem RepsInReserveRechner richtig ausgeben")
	public void test02()
	{
		final var erwartet = new double[][]
		{
			{ 1.0000, 0.9700, 0.9400, 0.9000, 0.8700, 0.8400, 0.8100, 0.7800, 0.7700, 0.7500, 0.7400, 0.7200 },
			{ 0.9750, 0.9450, 0.9100, 0.8800, 0.8500, 0.8200, 0.7900, 0.7750, 0.7550, 0.7450, 0.7250, 0.7150 },
			{ 0.9450, 0.9100, 0.8800, 0.8500, 0.8200, 0.7900, 0.7750, 0.7550, 0.7450, 0.7250, 0.7150, 0.6950 },
			{ 0.9100, 0.8800, 0.8500, 0.8200, 0.7900, 0.7750, 0.7550, 0.7450, 0.7250, 0.7150, 0.6950, 0.6850 },
			{ 0.8825, 0.8500, 0.8200, 0.7950, 0.7650, 0.7500, 0.7375, 0.7325, 0.7125, 0.7025, 0.6825, 0.6725 }
		};
		angenommenDerRepsInReserveRechnerGibtProzenteTabelleZurueck(erwartet);

		final var ergebnis = sut.getProzente();

		assertThat(ergebnis).isEqualTo(erwartet);
	}
}
