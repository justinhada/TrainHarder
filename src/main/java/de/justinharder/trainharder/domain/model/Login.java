package de.justinharder.trainharder.domain.model;

import com.google.common.base.MoreObjects;
import de.justinharder.base.domain.model.Entitaet;
import de.justinharder.base.domain.model.attribute.ID;
import de.justinharder.trainharder.domain.model.attribute.Benutzername;
import de.justinharder.trainharder.domain.model.attribute.EMailAdresse;
import de.justinharder.trainharder.domain.model.attribute.Passwort;
import de.justinharder.trainharder.domain.model.attribute.Salt;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.io.Serial;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Login extends Entitaet
{
	@Serial
	private static final long serialVersionUID = -2791077622025263428L;

	@Setter
	@NonNull
	@Embedded
	private EMailAdresse eMailAdresse;

	@Setter
	@NonNull
	@Embedded
	private Benutzername benutzername;

	@NonNull
	@Embedded
	private Salt salt;

	@Setter
	@NonNull
	@Embedded
	private Passwort passwort;

	@NonNull
	@OneToOne(optional = false)
	@JoinColumn(name = "BenutzerID", nullable = false)
	private Benutzer benutzer;

	public Login(
		ID id,
		@NonNull EMailAdresse eMailAdresse,
		@NonNull Benutzername benutzername,
		@NonNull Salt salt,
		@NonNull Passwort passwort,
		@NonNull Benutzer benutzer)
	{
		super(id);
		this.eMailAdresse = eMailAdresse;
		this.benutzername = benutzername;
		this.salt = salt;
		this.passwort = passwort;
		this.benutzer = benutzer;
	}

	@Override
	public String toString()
	{
		return MoreObjects.toStringHelper(this)
			.add("ID", id)
			.add("E-Mail-Adresse", eMailAdresse)
			.add("Benutzername", benutzername)
			.add("Salt", salt)
			.add("Passwort", passwort)
			.add("Benutzer", benutzer)
			.toString();
	}
}
