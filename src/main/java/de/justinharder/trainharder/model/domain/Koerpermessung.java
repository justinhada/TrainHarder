package de.justinharder.trainharder.model.domain;

import de.justinharder.trainharder.model.domain.embeddables.Koerpermasse;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Entity
@Table(name = "Koerpermessung")
public class Koerpermessung extends Entitaet
{
	private static final long serialVersionUID = -6355583837778945437L;

	@EmbeddedId
	@Column(name = "ID")
	private Primaerschluessel primaerschluessel;
	@Column(name = "Datum")
	private LocalDate datum;
	@Embedded
	private Koerpermasse koerpermasse;
	@Column(name = "Kalorieneinnahme")
	private int kalorieneinnahme;
	@Column(name = "Kalorienverbrauch")
	private int kalorienverbrauch;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "BenutzerID", nullable = false)
	private Benutzer benutzer;

	public Koerpermessung()
	{}

	public Koerpermessung(
		Primaerschluessel primaerschluessel,
		LocalDate datum,
		Koerpermasse koerpermasse,
		int kalorieneinnahme,
		int kalorienverbrauch,
		Benutzer benutzer)
	{
		this.primaerschluessel = primaerschluessel;
		this.datum = datum;
		this.koerpermasse = koerpermasse;
		this.kalorieneinnahme = kalorieneinnahme;
		this.kalorienverbrauch = kalorienverbrauch;
		this.benutzer = benutzer;

		benutzer.fuegeKoerpermessungHinzu(this);
	}

	public Koerpermessung setPrimaerschluessel(Primaerschluessel primaerschluessel)
	{
		this.primaerschluessel = primaerschluessel;
		return this;
	}

	public Koerpermessung setDatum(LocalDate datum)
	{
		this.datum = datum;
		return this;
	}

	public Koerpermessung setKoerpermasse(Koerpermasse koerpermasse)
	{
		this.koerpermasse = koerpermasse;
		return this;
	}

	public Koerpermessung setKalorieneinnahme(int kalorieneinnahme)
	{
		this.kalorieneinnahme = kalorieneinnahme;
		return this;
	}

	public Koerpermessung setKalorienverbrauch(int kalorienverbrauch)
	{
		this.kalorienverbrauch = kalorienverbrauch;
		return this;
	}

	public Koerpermessung setBenutzer(Benutzer benutzer)
	{
		this.benutzer = benutzer;
		return this;
	}

	@Override
	public boolean equals(Object obj)
	{
		return super.equals(obj);
	}

	@Override
	public int hashCode()
	{
		return super.hashCode();
	}
}
