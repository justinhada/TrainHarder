package de.justinharder.dietharder.domain.model.attribute.umfaenge;

import de.justinharder.base.domain.model.attribute.WertObjekt;
import de.justinharder.dietharder.domain.model.exceptions.umfaenge.LinkerUnterschenkelUmfangException;
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
public class LinkerUnterschenkelUmfang extends WertObjekt<BigDecimal>
{
	@Serial
	private static final long serialVersionUID = 7536035876202740614L;

	@NonNull
	@Column(name = "LinkerUnterschenkelUmfang", nullable = false)
	private BigDecimal wert;

	public LinkerUnterschenkelUmfang(@NonNull BigDecimal wert)
	{
		if (wert.compareTo(BigDecimal.ZERO) < 0)
		{
			throw new LinkerUnterschenkelUmfangException("Der LinkerUnterschenkelUmfang darf nicht negativ sein!");
		}
		this.wert = wert;
	}

	public LinkerUnterschenkelUmfang(@NonNull String wert)
	{
		this(new BigDecimal(wert));
	}
}
