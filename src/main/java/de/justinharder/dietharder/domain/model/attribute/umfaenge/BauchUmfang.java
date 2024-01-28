package de.justinharder.dietharder.domain.model.attribute.umfaenge;

import de.justinharder.base.domain.model.attribute.WertObjekt;
import de.justinharder.dietharder.domain.model.exceptions.umfaenge.BauchUmfangException;
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
public class BauchUmfang extends WertObjekt<BigDecimal>
{
	@Serial
	private static final long serialVersionUID = 7205682407089249505L;

	@NonNull
	@Column(name = "BauchUmfang", nullable = false)
	private BigDecimal wert;

	public BauchUmfang(@NonNull BigDecimal wert)
	{
		if (wert.compareTo(BigDecimal.ZERO) < 0)
		{
			throw new BauchUmfangException("Der BauchUmfang darf nicht negativ sein!");
		}
		this.wert = wert;
	}

	public BauchUmfang(@NonNull String wert)
	{
		this(new BigDecimal(wert));
	}
}
