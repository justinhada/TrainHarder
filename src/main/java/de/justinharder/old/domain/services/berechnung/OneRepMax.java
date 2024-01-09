package de.justinharder.old.domain.services.berechnung;

import com.google.common.base.Preconditions;
import de.justinharder.old.domain.model.Konstanten;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

@Getter
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OneRepMax
{
	private final int gewicht;
	private final int wiederholungen;
	private final int repsInReserve;
	private final int richtwert;

	public static OneRepMax aus(int gewicht, int wiederholungen, int repsInReserve)
	{
		Preconditions.checkArgument(gewicht > 0, "Ungültiges Gewicht!");
		Preconditions.checkArgument(wiederholungen > 0 && wiederholungen <= 8, "Ungültige Wiederholungen!");
		Preconditions.checkArgument(repsInReserve >= 0 && repsInReserve <= 4, "Ungültige RepsInReserve!");

		return new OneRepMax(gewicht, wiederholungen, repsInReserve,
			berechneRichtwert(gewicht, wiederholungen + repsInReserve));
	}

	private static int berechneRichtwert(int gewicht, int index)
	{
		var prozentsatz = Konstanten.ONE_REP_MAX_UMRECHNUNG.get(index - 1);
		return new BigDecimal(gewicht).divide(BigDecimal.valueOf(prozentsatz), RoundingMode.HALF_UP).intValue();
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (!(o instanceof OneRepMax oneRepMax))
		{
			return false;
		}
		return gewicht == oneRepMax.gewicht
			&& wiederholungen == oneRepMax.wiederholungen
			&& repsInReserve == oneRepMax.repsInReserve
			&& richtwert == oneRepMax.richtwert;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(gewicht, wiederholungen, repsInReserve, richtwert);
	}
}
