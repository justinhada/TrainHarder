package de.justinharder.trainharder.domain.model.attribute;

import de.justinharder.base.domain.model.attribute.WertObjekt;
import de.justinharder.trainharder.domain.model.exceptions.VornameException;
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
public class Vorname extends WertObjekt<String>
{
	@Serial
	private static final long serialVersionUID = -2470030962247254659L;

	@NonNull
	@Column(name = "Vorname", nullable = false)
	private String wert;

	public Vorname(@NonNull String wert)
	{
		if (wert.isBlank())
		{
			throw new VornameException("Der Vorname darf nicht leer sein!");
		}
		this.wert = wert;
	}
}
