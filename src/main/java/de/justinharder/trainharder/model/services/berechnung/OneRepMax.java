package de.justinharder.trainharder.model.services.berechnung;

import com.google.common.base.Preconditions;
import de.justinharder.trainharder.model.domain.Konstanten;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OneRepMax
{
	private final int gewicht;
	private final int wiederholungen;
	private final int repsInReserve;
	private final double richtwert;

	public static OneRepMax aus(int gewicht, int wiederholungen, int repsInReserve)
	{
		Preconditions.checkArgument(gewicht > 0, "Ungültiges Gewicht!");
		Preconditions.checkArgument(wiederholungen > 0, "Ungültige Wiederholungen!");
		Preconditions.checkArgument(repsInReserve >= 0, "Ungültige RepsInReserve!");

		return new OneRepMax(
			gewicht,
			wiederholungen,
			repsInReserve,
			berechneRichtwert(gewicht, wiederholungen, repsInReserve));
	}

	private static double berechneRichtwert(int gewicht, int wiederholungen, int repsInReserve)
	{
		var prozentsatz = Konstanten.PROZENTE.get(repsInReserve).get(wiederholungen - 1);
		return gewicht / prozentsatz;
	}
}
