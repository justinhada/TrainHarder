package de.justinharder.dietharder.domain.model.attribute.hautfaltendicke;

import de.justinharder.base.domain.model.attribute.WertObjekt;
import de.justinharder.dietharder.domain.model.exceptions.hautfaltendicke.AchselfalteException;
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
public class Achselfalte extends WertObjekt<Integer>
{
	@Serial
	private static final long serialVersionUID = 5041501362992281659L;

	@NonNull
	@Column(name = "Achselfalte", nullable = false)
	private Integer wert;

	public Achselfalte(@NonNull Integer wert)
	{
		if (wert <= 0)
		{
			throw new AchselfalteException("Die Achselfalte kann nicht %s mm betragen!".formatted(wert));
		}
		this.wert = wert;
	}
}
