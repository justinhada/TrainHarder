package de.justinharder.trainharder.model.services.berechnung.koerpermasse;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BodyMassIndex extends Berechnung
{
	public static BigDecimal aus(@NonNull BigDecimal koerpergewicht, @NonNull BigDecimal koerpergroesse)
	{
		return dividiere(koerpergewicht, dividiereBasis(koerpergroesse).pow(2));
	}
}
