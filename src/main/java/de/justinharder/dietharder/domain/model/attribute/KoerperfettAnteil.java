package de.justinharder.dietharder.domain.model.attribute;

import de.justinharder.base.domain.model.attribute.WertObjekt;
import de.justinharder.dietharder.domain.model.exceptions.KoerperfettAnteilException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serial;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class KoerperfettAnteil extends WertObjekt<BigDecimal>
{
	@Serial
	private static final long serialVersionUID = -5614633155913876897L;

	@NonNull
	@Column(name = "KoerperfettAnteil", nullable = false)
	private BigDecimal wert;

	public KoerperfettAnteil(@NonNull BigDecimal wert)
	{
		if (wert.compareTo(BigDecimal.ZERO) < 0)
		{
			throw new KoerperfettAnteilException("Der KörperfettAnteil darf nicht negativ sein!");
		}
		this.wert = wert;
	}

	public KoerperfettAnteil(@NonNull String wert)
	{
		this(new BigDecimal(wert));
	}

	@Override
	public String toString()
	{
		var decimalFormatSymbols = new DecimalFormatSymbols(Locale.GERMAN);
		decimalFormatSymbols.setDecimalSeparator(',');
		decimalFormatSymbols.setGroupingSeparator('.');
		return new DecimalFormat("###,##0.00", decimalFormatSymbols)
			.format(getWert().setScale(2, RoundingMode.HALF_UP));
	}
}
