package de.justinharder.trainharder.model.services.berechnung.koerpermasse;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FettfreiesKoerpergewicht extends Berechnung
{
	public static BigDecimal aus(@NonNull BigDecimal koerpergewicht, @NonNull BigDecimal koerperfettAnteil)
	{
		return koerpergewicht.subtract(koerpergewicht.multiply(dividiereBasis(koerperfettAnteil)));
	}
}
