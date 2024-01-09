package de.justinharder.old.domain.services.berechnung.koerpermasse;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Berechnung
{
	protected static BigDecimal dividiere(BigDecimal divident, BigDecimal divisor)
	{
		return divident.divide(divisor, 2, RoundingMode.HALF_EVEN);
	}

	protected static BigDecimal dividiereBasis(BigDecimal divident)
	{
		return dividiere(divident, new BigDecimal(100));
	}
}
