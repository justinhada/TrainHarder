package de.justinharder.dietharder.domain.model.attribute.umfaenge;

import de.justinharder.base.domain.model.attribute.WertObjekt;
import de.justinharder.dietharder.domain.model.exceptions.umfaenge.RechterOberarmUmfangException;
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
public class RechterOberarmUmfang extends WertObjekt<BigDecimal>
{
	@Serial
	private static final long serialVersionUID = 8186760359201518533L;

	@NonNull
	@Column(name = "RechterOberarmUmfang", nullable = false)
	private BigDecimal wert;

	public RechterOberarmUmfang(@NonNull BigDecimal wert)
	{
		if (wert.compareTo(BigDecimal.ZERO) < 0)
		{
			throw new RechterOberarmUmfangException("Der RechterOberarmUmfang darf nicht negativ sein!");
		}
		this.wert = wert;
	}

	public RechterOberarmUmfang(@NonNull String wert)
	{
		this(new BigDecimal(wert));
	}
}
