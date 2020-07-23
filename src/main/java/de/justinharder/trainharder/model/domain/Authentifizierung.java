package de.justinharder.trainharder.model.domain;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Authentifizierung")
public class Authentifizierung extends Entitaet
{
	private static final long serialVersionUID = 1607570632256351984L;

	@EmbeddedId
	@Column(name = "ID")
	private Primaerschluessel primaerschluessel;
	@Column(name = "Mail", unique = true, nullable = false)
	private String mail;
	@Column(name = "Benutzername", unique = true, nullable = false)
	private String benutzername;
	@Column(name = "Passwort", nullable = false)
	private String passwort;
	@Column(name = "Aktiv", nullable = false)
	private boolean aktiv;
	@Column(name = "ResetUuid", columnDefinition = "VARCHAR(36)", nullable = true)
	private UUID resetUuid;
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "BenutzerID", nullable = true)
	private Benutzer benutzer;

	public Authentifizierung(
		final Primaerschluessel primaerschluessel,
		final String mail,
		final String benutzername,
		final String passwort)
	{
		this.primaerschluessel = primaerschluessel;
		this.mail = mail;
		this.benutzername = benutzername;
		this.passwort = passwort;
		this.aktiv = false;
	}

	public Authentifizierung setAktiv(boolean aktiv)
	{
		this.aktiv = aktiv;
		return this;
	}

	public Authentifizierung setPasswort(String passwort)
	{
		this.passwort = passwort;
		return this;
	}

	public Authentifizierung setResetUuid(UUID resetUuid)
	{
		this.resetUuid = resetUuid;
		return this;
	}
}
