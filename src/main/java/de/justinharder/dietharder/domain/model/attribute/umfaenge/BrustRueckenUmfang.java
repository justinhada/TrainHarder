package de.justinharder.dietharder.domain.model.attribute.umfaenge;

import de.justinharder.base.domain.model.attribute.WertObjekt;
import de.justinharder.dietharder.domain.model.exceptions.umfaenge.BrustRueckenUmfangException;
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
public class BrustRueckenUmfang extends WertObjekt<BigDecimal>
{
	@Serial
	private static final long serialVersionUID = -451163193841115695L;

	@NonNull
	@Column(name = "BrustRueckenUmfang", nullable = false)
	private BigDecimal wert;

	public BrustRueckenUmfang(@NonNull BigDecimal wert)
	{
		if (wert.compareTo(BigDecimal.ZERO) < 0)
		{
			throw new BrustRueckenUmfangException("Der BrustRueckenUmfang darf nicht negativ sein!");
		}
		this.wert = wert;
	}

	public BrustRueckenUmfang(@NonNull String wert)
	{
		this(new BigDecimal(wert));
	}
}
