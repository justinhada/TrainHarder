package de.justinharder.powerlifting.model.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "Mail", unique = true, nullable = false)
	private String mail;
	@Column(name = "Benutzername", unique = true, nullable = false)
	private String benutzername;
	@Column(name = "Passwort", nullable = false)
	private String passwort;
	@Column(name = "Aktiv", nullable = false)
	private boolean aktiv;
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "BenutzerID", nullable = true)
	private Benutzer benutzer;

	public Authentifizierung(
		final String mail,
		final String benutzername,
		final String passwort)
	{
		this.mail = mail;
		this.benutzername = benutzername;
		this.passwort = passwort;
		this.aktiv = false;
	}

	public void aktiviere()
	{
		this.aktiv = true;
	}

//	public void setBenutzer(final Benutzer benutzer)
//	{
//		this.benutzer = benutzer;
//		benutzer.setAuthentifizierung(this);
//	}
}
