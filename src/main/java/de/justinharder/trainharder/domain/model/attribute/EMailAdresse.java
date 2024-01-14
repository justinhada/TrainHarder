package de.justinharder.trainharder.domain.model.attribute;

import de.justinharder.base.domain.model.attribute.WertObjekt;
import de.justinharder.trainharder.domain.model.exceptions.EMailAdresseException;
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
public class EMailAdresse extends WertObjekt<String>
{
	@Serial
	private static final long serialVersionUID = 5901061035646273500L;

	@NonNull
	@Column(name = "EMailAdresse", nullable = false, unique = true)
	private String wert;

	public EMailAdresse(@NonNull String wert)
	{
		if (wert.isBlank())
		{
			throw new EMailAdresseException("Die E-Mail-Adresse darf nicht leer sein!");
		}
		this.wert = wert;
	}
}
