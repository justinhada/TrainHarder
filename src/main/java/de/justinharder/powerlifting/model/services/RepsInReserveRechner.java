package de.justinharder.powerlifting.model.services;

import de.justinharder.powerlifting.model.domain.exceptions.UngueltigeRepsInReserveException;
import de.justinharder.powerlifting.model.domain.exceptions.UngueltigeWiederholungenException;
import de.justinharder.powerlifting.model.domain.exceptions.UngueltigesMaximumException;

public class RepsInReserveRechner
{
	private static final int MINIMUM = 0;
	private static final int MAXIMUM_RIR = 4;
	private static final int MAXIMUM_WIEDERHOLUNGEN = 12;

	// Zeilen entsprechen den RIR (0-4), Spalten den Reps (1-12)
	private static final double[][] PROZENTE = new double[][]
	{
		{ 1.0000, 0.9700, 0.9400, 0.9000, 0.8700, 0.8400, 0.8100, 0.7800, 0.7700, 0.7500, 0.7400, 0.7200 },
		{ 0.9750, 0.9450, 0.9100, 0.8800, 0.8500, 0.8200, 0.7900, 0.7750, 0.7550, 0.7450, 0.7250, 0.7150 },
		{ 0.9450, 0.9100, 0.8800, 0.8500, 0.8200, 0.7900, 0.7750, 0.7550, 0.7450, 0.7250, 0.7150, 0.6950 },
		{ 0.9100, 0.8800, 0.8500, 0.8200, 0.7900, 0.7750, 0.7550, 0.7450, 0.7250, 0.7150, 0.6950, 0.6850 },
		{ 0.8825, 0.8500, 0.8200, 0.7950, 0.7650, 0.7500, 0.7375, 0.7325, 0.7125, 0.7025, 0.6825, 0.6725 }
	};

	public int berechneRichtwert(final int maximum, final int wiederholungen, final int rir)
		throws UngueltigesMaximumException, UngueltigeWiederholungenException, UngueltigeRepsInReserveException
	{
		if (maximum <= MINIMUM)
		{
			throw new UngueltigesMaximumException(
				"Du bist leider zu schwach, um überhaupt mit dem Training zu beginnen!");
		}
		if (wiederholungen <= MINIMUM || wiederholungen > MAXIMUM_WIEDERHOLUNGEN)
		{
			throw new UngueltigeWiederholungenException(
				"Die Wiederholungszahl (" + wiederholungen + ") ist leider ungültig!");
		}
		if (rir < MINIMUM || rir > MAXIMUM_RIR)
		{
			throw new UngueltigeRepsInReserveException("Die RIR-Zahl " + rir + " ist ungültig!");
		}

		return (int) Math.round(PROZENTE[rir][wiederholungen - 1] * maximum);
	}
}
