package de.justinharder.trainharder.model.services.berechnung.koerpermasse;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Berechnung
{
	protected static BigDecimal dividiere(BigDecimal divident, BigDecimal divisor)
	{
		return divident.divide(divisor, 2, RoundingMode.HALF_UP);
	}

	protected static BigDecimal dividiereBasis(BigDecimal divident)
	{
		return dividiere(divident, new BigDecimal(100));
	}
}
