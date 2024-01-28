package de.justinharder.dietharder.domain.model.attribute.umfaenge;

import de.justinharder.base.domain.model.attribute.WertObjekt;
import de.justinharder.dietharder.domain.model.exceptions.umfaenge.SchulterUmfangException;
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
public class SchulterUmfang extends WertObjekt<BigDecimal>
{
	@Serial
	private static final long serialVersionUID = 9126795183667239032L;

	@NonNull
	@Column(name = "SchulterUmfang", nullable = false)
	private BigDecimal wert;

	public SchulterUmfang(@NonNull BigDecimal wert)
	{
		if (wert.compareTo(BigDecimal.ZERO) < 0)
		{
			throw new SchulterUmfangException("Der SchulterUmfang darf nicht negativ sein!");
		}
		this.wert = wert;
	}

	public SchulterUmfang(@NonNull String wert)
	{
		this(new BigDecimal(wert));
	}
}
