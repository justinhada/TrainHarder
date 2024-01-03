package de.justinharder.trainharder.domain.model;

import de.justinharder.trainharder.domain.UuidMapper;
import de.justinharder.trainharder.domain.model.embeddables.Passwort;
import de.justinharder.trainharder.domain.model.embeddables.Primaerschluessel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;

@Getter
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
	@Embedded
	private Passwort passwort;
	@Column(name = "Aktiv", nullable = false)
	private boolean aktiv;
	@Convert(converter = UuidMapper.class)
	@Column(name = "ResetUuid", columnDefinition = "VARCHAR(36)")
	private UUID resetUuid;
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "BenutzerID")
	private Benutzer benutzer;

	public Authentifizierung()
	{}

	public Authentifizierung(@NonNull Primaerschluessel primaerschluessel, @NonNull String mail, @NonNull String benutzername, @NonNull Passwort passwort)
	{
		this.primaerschluessel = primaerschluessel;
		this.mail = mail;
		this.benutzername = benutzername;
		this.passwort = passwort;
		aktiv = false;
	}

	public Authentifizierung setPrimaerschluessel(@NonNull Primaerschluessel primaerschluessel)
	{
		this.primaerschluessel = primaerschluessel;
		return this;
	}

	public Authentifizierung setMail(@NonNull String mail)
	{
		this.mail = mail;
		return this;
	}

	public Authentifizierung setBenutzername(@NonNull String benutzername)
	{
		this.benutzername = benutzername;
		return this;
	}

	public Authentifizierung setPasswort(@NonNull Passwort passwort)
	{
		this.passwort = passwort;
		return this;
	}

	public Authentifizierung setAktiv(boolean aktiv)
	{
		this.aktiv = aktiv;
		return this;
	}

	public Authentifizierung setResetUuid(UUID resetUuid)
	{
		this.resetUuid = resetUuid;
		return this;
	}

	public Authentifizierung setBenutzer(@NonNull Benutzer benutzer)
	{
		this.benutzer = benutzer;
		return this;
	}

	@Override
	public boolean equals(Object o)
	{
		return super.equals(o);
	}

	@Override
	public int hashCode()
	{
		return super.hashCode();
	}
}
