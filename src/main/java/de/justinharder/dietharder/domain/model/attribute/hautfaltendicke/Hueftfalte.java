package de.justinharder.dietharder.domain.model.attribute.hautfaltendicke;

import de.justinharder.base.domain.model.attribute.WertObjekt;
import de.justinharder.dietharder.domain.model.exceptions.hautfaltendicke.HueftfalteException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serial;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Hueftfalte extends WertObjekt<Integer>
{
	@Serial
	private static final long serialVersionUID = 5158941426379887658L;

	@NonNull
	@Column(name = "Hueftfalte", nullable = false)
	private Integer wert;

	public Hueftfalte(@NonNull Integer wert)
	{
		if (wert <= 0)
		{
			throw new HueftfalteException("Die Hüftfalte kann nicht %s mm betragen!".formatted(wert));
		}
		this.wert = wert;
	}
}
