package de.justinharder.dietharder.domain.model.attribute.umfaenge;

import de.justinharder.base.domain.model.attribute.WertObjekt;
import de.justinharder.dietharder.domain.model.exceptions.umfaenge.RechterUnterarmUmfangException;
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
public class RechterUnterarmUmfang extends WertObjekt<BigDecimal>
{
	@Serial
	private static final long serialVersionUID = -528223287931192323L;

	@NonNull
	@Column(name = "RechterUnterarmUmfang", nullable = false)
	private BigDecimal wert;

	public RechterUnterarmUmfang(@NonNull BigDecimal wert)
	{
		if (wert.compareTo(BigDecimal.ZERO) < 0)
		{
			throw new RechterUnterarmUmfangException("Der RechterUnterarmUmfang darf nicht negativ sein!");
		}
		this.wert = wert;
	}

	public RechterUnterarmUmfang(@NonNull String wert)
	{
		this(new BigDecimal(wert));
	}
}
