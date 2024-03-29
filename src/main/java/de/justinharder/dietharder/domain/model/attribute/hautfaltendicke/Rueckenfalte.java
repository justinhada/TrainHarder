package de.justinharder.dietharder.domain.model.attribute.hautfaltendicke;

import de.justinharder.base.domain.model.attribute.WertObjekt;
import de.justinharder.dietharder.domain.model.exceptions.hautfaltendicke.RueckenfalteException;
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
public class Rueckenfalte extends WertObjekt<Integer>
{
	@Serial
	private static final long serialVersionUID = -8292153689968847707L;

	@NonNull
	@Column(name = "Rueckenfalte", nullable = false)
	private Integer wert;

	public Rueckenfalte(@NonNull Integer wert)
	{
		if (wert <= 0)
		{
			throw new RueckenfalteException("Die Rückenfalte kann nicht %s mm betragen!".formatted(wert));
		}
		this.wert = wert;
	}
}
