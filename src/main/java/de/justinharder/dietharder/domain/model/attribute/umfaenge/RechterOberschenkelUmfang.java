package de.justinharder.dietharder.domain.model.attribute.umfaenge;

import de.justinharder.base.domain.model.attribute.WertObjekt;
import de.justinharder.dietharder.domain.model.exceptions.umfaenge.RechterOberschenkelUmfangException;
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
public class RechterOberschenkelUmfang extends WertObjekt<BigDecimal>
{
	@Serial
	private static final long serialVersionUID = -2767768797717617268L;

	@NonNull
	@Column(name = "RechterOberschenkelUmfang", nullable = false)
	private BigDecimal wert;

	public RechterOberschenkelUmfang(@NonNull BigDecimal wert)
	{
		if (wert.compareTo(BigDecimal.ZERO) < 0)
		{
			throw new RechterOberschenkelUmfangException("Der RechterOberschenkelUmfang darf nicht negativ sein!");
		}
		this.wert = wert;
	}

	public RechterOberschenkelUmfang(@NonNull String wert)
	{
		this(new BigDecimal(wert));
	}
}
