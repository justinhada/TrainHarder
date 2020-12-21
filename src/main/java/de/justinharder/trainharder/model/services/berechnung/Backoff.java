package de.justinharder.trainharder.model.services.berechnung;

import com.google.common.base.Preconditions;
import de.justinharder.trainharder.model.domain.Konstanten;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Backoff
{
	private final double oneRepMax;
	private final int wiederholungen;
	private final int repsInReserve;
	private final int richtwert;

	public static Backoff aus(double oneRepMax, int wiederholungen, int repsInReserve)
	{
		Preconditions.checkArgument(oneRepMax > 0, "Ungültiger OneRepMax!");
		Preconditions.checkArgument(wiederholungen > 0, "Ungültige Wiederholungen!");
		Preconditions.checkArgument(repsInReserve >= 0, "Ungültige RepsInReserve!");

		return new Backoff(
			oneRepMax,
			wiederholungen,
			repsInReserve,
			berechneRichtwert(oneRepMax, wiederholungen, repsInReserve));
	}

	private static int berechneRichtwert(double oneRepMax, int wiederholungen, int repsInReserve)
	{
		var prozentsatz = Konstanten.PROZENTE.get(repsInReserve).get(wiederholungen - 1);
		return (int) Math.round(prozentsatz * oneRepMax);
	}
}
