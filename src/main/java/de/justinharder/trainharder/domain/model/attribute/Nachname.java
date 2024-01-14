package de.justinharder.trainharder.domain.model.attribute;

import de.justinharder.base.domain.model.attribute.WertObjekt;
import de.justinharder.trainharder.domain.model.exceptions.NachnameException;
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
public class Nachname extends WertObjekt<String>
{
	@Serial
	private static final long serialVersionUID = -3614269240391364735L;

	@NonNull
	@Column(name = "Nachname", nullable = false)
	private String wert;

	public Nachname(@NonNull String wert)
	{
		if (wert.isBlank())
		{
			throw new NachnameException("Der Nachname darf nicht leer sein!");
		}
		this.wert = wert;
	}
}
