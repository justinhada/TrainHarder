package de.justinharder.dietharder.domain.model.attribute.hautfaltendicke;

import de.justinharder.base.domain.model.attribute.WertObjekt;
import de.justinharder.dietharder.domain.model.exceptions.hautfaltendicke.BrustfalteException;
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
public class Brustfalte extends WertObjekt<Integer>
{
	@Serial
	private static final long serialVersionUID = 195070276521997819L;

	@NonNull
	@Column(name = "Brustfalte", nullable = false)
	private Integer wert;

	public Brustfalte(@NonNull Integer wert)
	{
		if (wert <= 0)
		{
			throw new BrustfalteException("Die Brustfalte kann nicht %s mm betragen!".formatted(wert));
		}
		this.wert = wert;
	}
}
