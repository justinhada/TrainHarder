package de.justinharder.trainharder.model.services.berechnung.koerpermasse;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.math.BigDecimal;
import java.math.RoundingMode;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FettfreiesKoerpergewicht
{
	public static BigDecimal aus(@NonNull BigDecimal koerpergewicht, @NonNull BigDecimal koerperfettAnteil)
	{
		return koerpergewicht.subtract(koerpergewicht.multiply(Berechnung.dividiereBasis(koerperfettAnteil))).setScale(2, RoundingMode.HALF_EVEN);
	}
}