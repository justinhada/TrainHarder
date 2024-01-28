package de.justinharder.dietharder.domain.model.attribute.umfaenge;

import de.justinharder.base.domain.model.attribute.WertObjekt;
import de.justinharder.dietharder.domain.model.exceptions.umfaenge.HueftUmfangException;
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
public class HueftUmfang extends WertObjekt<BigDecimal>
{
	@Serial
	private static final long serialVersionUID = 3851517382369671166L;

	@NonNull
	@Column(name = "HueftUmfang", nullable = false)
	private BigDecimal wert;

	public HueftUmfang(@NonNull BigDecimal wert)
	{
		if (wert.compareTo(BigDecimal.ZERO) < 0)
		{
			throw new HueftUmfangException("Der HueftUmfang darf nicht negativ sein!");
		}
		this.wert = wert;
	}

	public HueftUmfang(@NonNull String wert)
	{
		this(new BigDecimal(wert));
	}
}
