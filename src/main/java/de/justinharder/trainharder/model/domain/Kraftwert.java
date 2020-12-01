package de.justinharder.trainharder.model.domain;

import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.model.domain.enums.Wiederholungen;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Entity
@Table(name = "Kraftwert")
public class Kraftwert extends Entitaet
{
	private static final long serialVersionUID = -1203157961547955006L;

	@EmbeddedId
	@Column(name = "ID")
	private Primaerschluessel primaerschluessel;
	@Column(name = "Gewicht")
	private double gewicht;
	@Column(name = "Koerpergewicht")
	private double koerpergewicht;
	@Column(name = "Datum")
	private LocalDate datum;
	@Column(name = "Wiederholungen")
	@Enumerated(EnumType.STRING)
	private Wiederholungen wiederholungen;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UebungID", nullable = false)
	private Uebung uebung;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BenutzerID", nullable = false)
	private Benutzer benutzer;

	public Kraftwert()
	{}

	public Kraftwert(
		Primaerschluessel primaerschluessel,
		double gewicht,
		double koerpergewicht,
		LocalDate datum,
		Wiederholungen wiederholungen,
		Uebung uebung,
		Benutzer benutzer)
	{
		this.primaerschluessel = primaerschluessel;
		this.gewicht = gewicht;
		this.koerpergewicht = koerpergewicht;
		this.datum = datum;
		this.wiederholungen = wiederholungen;
		this.uebung = uebung;
		this.benutzer = benutzer;
	}

	public Kraftwert setPrimaerschluessel(Primaerschluessel primaerschluessel)
	{
		this.primaerschluessel = primaerschluessel;
		return this;
	}

	public Kraftwert setGewicht(double gewicht)
	{
		this.gewicht = gewicht;
		return this;
	}

	public Kraftwert setKoerpergewicht(double koerpergewicht)
	{
		this.koerpergewicht = koerpergewicht;
		return this;
	}

	public Kraftwert setDatum(LocalDate datum)
	{
		this.datum = datum;
		return this;
	}

	public Kraftwert setWiederholungen(Wiederholungen wiederholungen)
	{
		this.wiederholungen = wiederholungen;
		return this;
	}

	public Kraftwert setUebung(Uebung uebung)
	{
		this.uebung = uebung;
		return this;
	}

	public Kraftwert setBenutzer(Benutzer benutzer)
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
