package de.justinharder.trainharder.model.services.berechnung.koerpermasse;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.math.BigDecimal;
import java.math.RoundingMode;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FatFreeMassIndex
{
	public static BigDecimal aus(@NonNull BigDecimal koerpergewicht, @NonNull BigDecimal koerperfettAnteil, @NonNull BigDecimal koerpergroesse)
	{
		var magermasse = koerpergewicht.multiply(new BigDecimal(1).subtract(Berechnung.dividiereBasis(koerperfettAnteil)));
		return Berechnung.dividiere(magermasse, Berechnung.dividiereBasis(koerpergroesse).pow(2))
			.add(BigDecimal.valueOf(6.1).multiply(BigDecimal.valueOf(1.8).subtract(Berechnung.dividiereBasis(koerpergroesse))))
			.setScale(2, RoundingMode.HALF_EVEN);
	}
}