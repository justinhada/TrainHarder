package de.justinharder.dietharder.domain.model.attribute.umfaenge;

import de.justinharder.base.domain.model.attribute.WertObjekt;
import de.justinharder.dietharder.domain.model.exceptions.umfaenge.RechterUnterschenkelUmfangException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serial;
import java.math.BigDecimal;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RechterUnterschenkelUmfang extends WertObjekt<BigDecimal>
{
	@Serial
	private static final long serialVersionUID = 4005082914642502133L;

	@NonNull
	@Column(name = "RechterUnterschenkelUmfang", nullable = false)
	private BigDecimal wert;

	public RechterUnterschenkelUmfang(@NonNull BigDecimal wert)
	{
		if (wert.compareTo(BigDecimal.ZERO) < 0)
		{
			throw new RechterUnterschenkelUmfangException("Der RechterUnterschenkelUmfang darf nicht negativ sein!");
		}
		this.wert = wert;
	}

	public RechterUnterschenkelUmfang(@NonNull String wert)
	{
		this(new BigDecimal(wert));
	}
}
