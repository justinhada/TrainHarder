package de.justinharder.dietharder.domain.model.attribute.hautfaltendicke;

import de.justinharder.base.domain.model.attribute.WertObjekt;
import de.justinharder.dietharder.domain.model.exceptions.hautfaltendicke.BeinfalteException;
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
public class Beinfalte extends WertObjekt<Integer>
{
	@Serial
	private static final long serialVersionUID = 4839632105916472824L;

	@NonNull
	@Column(name = "Beinfalte", nullable = false)
	private Integer wert;

	public Beinfalte(@NonNull Integer wert)
	{
		if (wert <= 0)
		{
			throw new BeinfalteException("Die Beinfalte kann nicht %s mm betragen!".formatted(wert));
		}
		this.wert = wert;
	}
}
