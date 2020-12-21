package de.justinharder.trainharder.model.services.berechnung;

import de.justinharder.trainharder.model.domain.Konstanten;
import de.justinharder.trainharder.model.domain.exceptions.RepsInReserveException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EstimatedOneRepMax
{
	private static final int MINIMUM = 0;
	private static final int MAXIMUM_RIR = 4;
	private static final int MAXIMUM_WIEDERHOLUNGEN = 12;

	private final int gewicht;
	private final int wiederholungen;
	private final int repsInReserve;
	private final int estimatedOneRepMax;

	public static EstimatedOneRepMax aus(int gewicht, int wiederholungen, int repsInReserve)
	{
		if (gewicht <= MINIMUM)
		{
			throw new RepsInReserveException("Du bist leider zu schwach, um überhaupt mit dem Training zu beginnen!");
		}
		if (wiederholungen <= MINIMUM || wiederholungen > MAXIMUM_WIEDERHOLUNGEN)
		{
			throw new RepsInReserveException("Die Wiederholungszahl (" + wiederholungen + ") ist leider ungültig!");
		}
		if (repsInReserve < MINIMUM || repsInReserve > MAXIMUM_RIR)
		{
			throw new RepsInReserveException("Die RIR-Zahl (" + repsInReserve + ") ist ungültig!");
		}

		var repsInReserve = (int) Math.round(Konstanten.PROZENTE.get(repsInReserve).get(wiederholungen - 1) * gewicht);
		return new EstimatedOneRepMax(repsInReserve);
	}

	public int werteA()
	{
		return anzahl;
	}
}
