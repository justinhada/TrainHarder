package de.justinharder.dietharder.domain.model.attribute.hautfaltendicke;

import de.justinharder.base.domain.model.attribute.WertObjekt;
import de.justinharder.dietharder.domain.model.exceptions.hautfaltendicke.BauchfalteException;
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
public class Bauchfalte extends WertObjekt<Integer>
{
	@Serial
	private static final long serialVersionUID = -7750433390256334939L;

	@NonNull
	@Column(name = "Bauchfalte", nullable = false)
	private Integer wert;

	public Bauchfalte(@NonNull Integer wert)
	{
		if (wert <= 0)
		{
			throw new BauchfalteException("Die Bauchfalte kann nicht %s mm betragen!".formatted(wert));
		}
		this.wert = wert;
	}
}
