package de.justinharder.dietharder.domain.model.attribute.umfaenge;

import de.justinharder.base.domain.model.attribute.WertObjekt;
import de.justinharder.dietharder.domain.model.exceptions.umfaenge.LinkerOberarmUmfangException;
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
public class LinkerOberarmUmfang extends WertObjekt<BigDecimal>
{
	@Serial
	private static final long serialVersionUID = 7014986062195321138L;

	@NonNull
	@Column(name = "LinkerOberarmUmfang", nullable = false)
	private BigDecimal wert;

	public LinkerOberarmUmfang(@NonNull BigDecimal wert)
	{
		if (wert.compareTo(BigDecimal.ZERO) < 0)
		{
			throw new LinkerOberarmUmfangException("Der LinkerOberarmUmfang darf nicht negativ sein!");
		}
		this.wert = wert;
	}

	public LinkerOberarmUmfang(@NonNull String wert)
	{
		this(new BigDecimal(wert));
	}
}
