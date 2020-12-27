package de.justinharder.trainharder.model.services.berechnung.koerpermasse;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FatFreeMassIndex extends Berechnung
{
	public static BigDecimal aus(
		@NonNull BigDecimal koerpergewicht,
		@NonNull BigDecimal koerperfettAnteil,
		@NonNull BigDecimal koerpergroesse)
	{
		var magermasse = koerpergewicht.multiply(new BigDecimal(1).subtract(dividiereBasis(koerperfettAnteil)));
		return dividiere(magermasse, dividiereBasis(koerpergroesse).pow(2)).add(BigDecimal.valueOf(6.1)
			.multiply(BigDecimal.valueOf(1.8).subtract(dividiereBasis(koerpergroesse))));
	}
}
