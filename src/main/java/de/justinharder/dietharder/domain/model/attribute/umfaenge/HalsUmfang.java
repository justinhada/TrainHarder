package de.justinharder.dietharder.domain.model.attribute.umfaenge;

import de.justinharder.base.domain.model.attribute.WertObjekt;
import de.justinharder.dietharder.domain.model.exceptions.umfaenge.HalsUmfangException;
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
public class HalsUmfang extends WertObjekt<BigDecimal>
{
	@Serial
	private static final long serialVersionUID = -6110033102166447224L;

	@NonNull
	@Column(name = "HalsUmfang", nullable = false)
	private BigDecimal wert;

	public HalsUmfang(@NonNull BigDecimal wert)
	{
		if (wert.compareTo(BigDecimal.ZERO) < 0)
		{
			throw new HalsUmfangException("Der HalsUmfang darf nicht negativ sein!");
		}
		this.wert = wert;
	}

	public HalsUmfang(@NonNull String wert)
	{
		this(new BigDecimal(wert));
	}
}
