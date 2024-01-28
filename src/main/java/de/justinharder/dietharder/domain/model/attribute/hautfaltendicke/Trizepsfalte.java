package de.justinharder.dietharder.domain.model.attribute.hautfaltendicke;

import de.justinharder.base.domain.model.attribute.WertObjekt;
import de.justinharder.dietharder.domain.model.exceptions.hautfaltendicke.TrizepsfalteException;
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
public class Trizepsfalte extends WertObjekt<Integer>
{
	@Serial
	private static final long serialVersionUID = -4207488188639497007L;

	@NonNull
	@Column(name = "Trizepsfalte", nullable = false)
	private Integer wert;

	public Trizepsfalte(@NonNull Integer wert)
	{
		if (wert <= 0)
		{
			throw new TrizepsfalteException("Die Trizepsfalte kann nicht %s mm betragen!".formatted(wert));
		}
		this.wert = wert;
	}
}
