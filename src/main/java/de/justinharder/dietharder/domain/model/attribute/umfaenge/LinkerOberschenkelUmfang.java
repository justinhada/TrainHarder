package de.justinharder.dietharder.domain.model.attribute.umfaenge;

import de.justinharder.base.domain.model.attribute.WertObjekt;
import de.justinharder.dietharder.domain.model.exceptions.umfaenge.LinkerOberschenkelUmfangException;
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
public class LinkerOberschenkelUmfang extends WertObjekt<BigDecimal>
{
	@Serial
	private static final long serialVersionUID = -6863125038420379939L;

	@NonNull
	@Column(name = "LinkerOberschenkelUmfang", nullable = false)
	private BigDecimal wert;

	public LinkerOberschenkelUmfang(@NonNull BigDecimal wert)
	{
		if (wert.compareTo(BigDecimal.ZERO) < 0)
		{
			throw new LinkerOberschenkelUmfangException("Der LinkerOberschenkelUmfang darf nicht negativ sein!");
		}
		this.wert = wert;
	}

	public LinkerOberschenkelUmfang(@NonNull String wert)
	{
		this(new BigDecimal(wert));
	}
}
