package de.justinharder.powerlifting.model.services;

import de.justinharder.powerlifting.model.domain.Konstanten;
import de.justinharder.powerlifting.model.domain.exceptions.UngueltigeRepsInReserveException;
import de.justinharder.powerlifting.model.domain.exceptions.UngueltigeWiederholungenException;
import de.justinharder.powerlifting.model.domain.exceptions.UngueltigesMaximumException;

public class RepsInReserveRechner
{
	private static final int MINIMUM = 0;
	private static final int MAXIMUM_RIR = 4;
	private static final int MAXIMUM_WIEDERHOLUNGEN = 12;

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
			throw new UngueltigeRepsInReserveException("Die RIR-Zahl (" + rir + ") ist ungültig!");
		}

		return (int) Math.round(Konstanten.PROZENTE[rir][wiederholungen - 1] * maximum);
	}

	public double[][] getProzente()
	{
		return Konstanten.PROZENTE;
	}
}
