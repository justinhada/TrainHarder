package de.justinharder.trainharder.model.services.berechnung;

import com.google.common.base.Preconditions;
import de.justinharder.trainharder.model.domain.Konstanten;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

@Getter
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Backoff
{
	private final int oneRepMax;
	private final int wiederholungen;
	private final int repsInReserve;
	private final int richtwert;

	public static Backoff aus(int oneRepMax, int wiederholungen, int repsInReserve)
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

	private static int berechneRichtwert(int oneRepMax, int wiederholungen, int repsInReserve)
	{
		var prozentsatz = Konstanten.PROZENTE.get(repsInReserve).get(wiederholungen - 1);
		return (int) Math.round(prozentsatz * oneRepMax);
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (!(o instanceof Backoff))
		{
			return false;
		}
		Backoff backoff = (Backoff) o;
		return oneRepMax == backoff.oneRepMax && wiederholungen == backoff.wiederholungen && repsInReserve == backoff.repsInReserve && richtwert == backoff.richtwert;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(oneRepMax, wiederholungen, repsInReserve, richtwert);
	}
}
