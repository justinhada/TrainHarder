package de.justinharder.trainharder.model.services.berechnung.koerpermasse;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.math.BigDecimal;
import java.math.RoundingMode;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BodyMassIndex
{
	public static BigDecimal aus(@NonNull BigDecimal koerpergewicht, @NonNull BigDecimal koerpergroesse)
	{
		return Berechnung.dividiere(koerpergewicht, Berechnung.dividiereBasis(koerpergroesse).pow(2)).setScale(2, RoundingMode.HALF_EVEN);
	}
}