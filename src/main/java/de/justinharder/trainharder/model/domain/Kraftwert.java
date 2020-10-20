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
		final Primaerschluessel primaerschluessel,
		final double gewicht,
		final double koerpergewicht,
		final LocalDate datum,
		final Wiederholungen wiederholungen,
		final Uebung uebung,
		final Benutzer benutzer)
	{
		this.primaerschluessel = primaerschluessel;
		this.gewicht = gewicht;
		this.koerpergewicht = koerpergewicht;
		this.datum = datum;
		this.wiederholungen = wiederholungen;
		this.uebung = uebung;
		this.benutzer = benutzer;
	}

	public Kraftwert setPrimaerschluessel(final Primaerschluessel primaerschluessel)
	{
		this.primaerschluessel = primaerschluessel;
		return this;
	}

	public Kraftwert setGewicht(final double gewicht)
	{
		this.gewicht = gewicht;
		return this;
	}

	public Kraftwert setKoerpergewicht(final double koerpergewicht)
	{
		this.koerpergewicht = koerpergewicht;
		return this;
	}

	public Kraftwert setDatum(final LocalDate datum)
	{
		this.datum = datum;
		return this;
	}

	public Kraftwert setWiederholungen(final Wiederholungen wiederholungen)
	{
		this.wiederholungen = wiederholungen;
		return this;
	}

	public Kraftwert setUebung(final Uebung uebung)
	{
		this.uebung = uebung;
		return this;
	}

	public Kraftwert setBenutzer(final Benutzer benutzer)
	{
		this.benutzer = benutzer;
		return this;
	}

	@Override
	public boolean equals(final Object obj)
	{
		return super.equals(obj);
	}

	@Override
	public int hashCode()
	{
		return super.hashCode();
	}
}
