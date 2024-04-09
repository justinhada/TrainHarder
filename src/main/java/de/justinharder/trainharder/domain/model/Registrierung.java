package de.justinharder.trainharder.domain.model;

import com.google.common.base.MoreObjects;
import de.justinharder.base.domain.model.Entitaet;
import de.justinharder.base.domain.model.attribute.ID;
import de.justinharder.trainharder.domain.model.attribute.EMailAdresse;
import de.justinharder.trainharder.domain.model.attribute.Passwort;
import de.justinharder.trainharder.domain.model.attribute.Salt;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serial;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Registrierung extends Entitaet
{
	@Serial
	private static final long serialVersionUID = 6108543866037307371L;

	@NonNull
	@Embedded
	private EMailAdresse eMailAdresse;

	@NonNull
	@Embedded
	private Salt salt;

	@NonNull
	@Embedded
	private Passwort passwort;

	public Registrierung(
		@NonNull ID id,
		@NonNull EMailAdresse eMailAdresse,
		@NonNull Salt salt,
		@NonNull Passwort passwort)
	{
		super(id);
		this.eMailAdresse = eMailAdresse;
		this.salt = salt;
		this.passwort = passwort;
	}

	@Override
	public String toString()
	{
		return MoreObjects.toStringHelper(this)
			.add("ID", id)
			.add("E-Mail-Adresse", eMailAdresse)
			.add("Salt", salt)
			.add("Passwort", passwort)
			.toString();
	}
}
