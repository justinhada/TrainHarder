package de.justinharder.trainharder.model.domain;

import de.justinharder.trainharder.model.domain.embeddables.Koerpermasse;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import lombok.Getter;
import lombok.NonNull;

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
		@NonNull Primaerschluessel primaerschluessel,
		@NonNull LocalDate datum,
		@NonNull Koerpermasse koerpermasse,
		int kalorieneinnahme,
		int kalorienverbrauch,
		@NonNull Benutzer benutzer)
	{
		this.primaerschluessel = primaerschluessel;
		this.datum = datum;
		this.koerpermasse = koerpermasse;
		this.kalorieneinnahme = kalorieneinnahme;
		this.kalorienverbrauch = kalorienverbrauch;
		this.benutzer = benutzer;

		benutzer.fuegeKoerpermessungHinzu(this);
	}

	public Koerpermessung setPrimaerschluessel(@NonNull Primaerschluessel primaerschluessel)
	{
		this.primaerschluessel = primaerschluessel;
		return this;
	}

	public Koerpermessung setDatum(@NonNull LocalDate datum)
	{
		this.datum = datum;
		return this;
	}

	public Koerpermessung setKoerpermasse(@NonNull Koerpermasse koerpermasse)
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

	public Koerpermessung setBenutzer(@NonNull Benutzer benutzer)
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