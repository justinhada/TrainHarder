package de.justinharder.trainharder.domain.model;

import de.justinharder.trainharder.domain.UUIDMapping;
import de.justinharder.trainharder.domain.model.embeddables.ID;
import de.justinharder.trainharder.domain.model.embeddables.Passwort;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.*;

import java.io.Serial;
import java.util.UUID;

@Entity
@Getter
@UserDefinition
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Authentifizierung extends Entitaet
{
	@Serial
	private static final long serialVersionUID = 1607570632256351984L;

	@Setter
	@NonNull
	@Column(name = "Mail", unique = true, nullable = false)
	private String mail;

	@Setter
	@NonNull
	@Username
	@Column(name = "Benutzername", unique = true, nullable = false)
	private String benutzername;

	@Setter
	@NonNull
	@Embedded
	private Passwort passwort;

	@Setter
	@Column(name = "Aktiv", nullable = false)
	private boolean aktiv = false;

	@Setter
	@Convert(converter = UUIDMapping.class)
	@Column(name = "ResetUUID", columnDefinition = "VARCHAR(36)")
	private UUID resetUuid;

	@Roles
	private final String rolle = "";

	public Authentifizierung(
		ID id,
		@NonNull String mail,
		@NonNull String benutzername,
		@NonNull Passwort passwort)
	{
		super(id);
		this.mail = mail;
		this.benutzername = benutzername;
		this.passwort = passwort;
	}
}
