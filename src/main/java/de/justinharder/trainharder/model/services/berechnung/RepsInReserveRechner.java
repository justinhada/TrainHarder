package de.justinharder.trainharder.model.services.berechnung;

import de.justinharder.trainharder.model.domain.Konstanten;
import de.justinharder.trainharder.model.domain.exceptions.UngueltigeRepsInReserveException;
import de.justinharder.trainharder.model.domain.exceptions.UngueltigeWiederholungenException;
import de.justinharder.trainharder.model.domain.exceptions.UngueltigesMaximumException;

public class RepsInReserveRechner
{
	private static final int MINIMUM = 0;
	private static final int MAXIMUM_RIR = 4;
	private static final int MAXIMUM_WIEDERHOLUNGEN = 12;

	public int berechneRichtwert(int maximum, int wiederholungen, int rir)
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

		return (int) Math.round(Konstanten.PROZENTE.get(rir).get(wiederholungen - 1) * maximum);
	}
}
