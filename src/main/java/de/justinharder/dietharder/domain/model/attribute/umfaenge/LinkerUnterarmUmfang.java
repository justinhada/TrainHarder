package de.justinharder.dietharder.domain.model.attribute.umfaenge;

import de.justinharder.base.domain.model.attribute.WertObjekt;
import de.justinharder.dietharder.domain.model.exceptions.umfaenge.LinkerUnterarmUmfangException;
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
public class LinkerUnterarmUmfang extends WertObjekt<BigDecimal>
{
	@Serial
	private static final long serialVersionUID = -2728390184461691791L;

	@NonNull
	@Column(name = "LinkerUnterarmUmfang", nullable = false)
	private BigDecimal wert;

	public LinkerUnterarmUmfang(@NonNull BigDecimal wert)
	{
		if (wert.compareTo(BigDecimal.ZERO) < 0)
		{
			throw new LinkerUnterarmUmfangException("Der LinkerUnterarmUmfang darf nicht negativ sein!");
		}
		this.wert = wert;
	}

	public LinkerUnterarmUmfang(@NonNull String wert)
	{
		this(new BigDecimal(wert));
	}
}
