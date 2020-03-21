package de.justinharder.powerlifting.model.domain;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Koerpermessung extends Entitaet
{
	private static final long serialVersionUID = -6355583837778945437L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int koerpergroesse;
	private double koerpergewicht;
	private double bodyMassIndex;
	private double fatFreeMassIndex;
	private double koerperfettAnteil;
	private double fettfreiesKoerpergewicht;
	private double subkutanesFett;
	private double viszeralfett;
	private double koerperwasser;
	private double skelettmuskel;
	private double muskelmasse;
	private double knochenmasse;
	private double protein;
	private int grundumsatz;
	private int eingenommeneKalorien;
	private int verbrannteKalorien;
	private int biologischesAlter;
	private LocalDate datum;
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(nullable = false)
	private Benutzer benutzer;

	public Koerpermessung(
		final int koerpergroesse,
		final double koerpergewicht,
		final double koerperfettAnteil,
		final double fettfreiesKoerpergewicht,
		final double subkutanesFett,
		final double viszeralfett,
		final double koerperwasser,
		final double skelettmuskel,
		final double muskelmasse,
		final double knochenmasse,
		final double protein,
		final int grundumsatz,
		final int eingenommeneKalorien,
		final int verbrannteKalorien,
		final int biologischesAlter,
		final LocalDate datum,
		final Benutzer benutzer)
	{
		this.koerpergroesse = koerpergroesse;
		this.koerpergewicht = koerpergewicht;
		this.bodyMassIndex = berechneBmi(koerpergroesse, koerpergewicht);
		this.fatFreeMassIndex = berechneFfmi(koerpergewicht, koerperfettAnteil, koerpergroesse);
		this.koerperfettAnteil = koerperfettAnteil;
		this.fettfreiesKoerpergewicht = fettfreiesKoerpergewicht;
		this.subkutanesFett = subkutanesFett;
		this.viszeralfett = viszeralfett;
		this.koerperwasser = koerperwasser;
		this.skelettmuskel = skelettmuskel;
		this.muskelmasse = muskelmasse;
		this.knochenmasse = knochenmasse;
		this.protein = protein;
		this.grundumsatz = grundumsatz;
		this.eingenommeneKalorien = eingenommeneKalorien;
		this.verbrannteKalorien = verbrannteKalorien;
		this.biologischesAlter = biologischesAlter;
		this.datum = datum;
		this.benutzer = benutzer;

		benutzer.fuegeKoerpermessungHinzu(this);
	}

	private double berechneBmi(int koerpergroesse, double koerpergewicht)
	{
		var bmi = koerpergewicht / Math.pow(koerpergroesse / 100.0, 2);
		return Math.round(bmi * 100) / 100.0;
	}

	private double berechneFfmi(double koerpergewicht, double koerperfettAnteil, int koerpergroesse)
	{
		var magermasse = koerpergewicht * (1 - koerperfettAnteil / 100.0);
		var ffmi = (magermasse / Math.pow(koerpergroesse / 100.0, 2)) + 6.1 * (1.8 - koerpergroesse / 100.0);
		return Math.round(ffmi * 100) / 100.0;
	}

	public void setKoerpergroesse(int koerpergroesse)
	{
		this.koerpergroesse = koerpergroesse;
		this.bodyMassIndex = berechneBmi(koerpergroesse, koerpergewicht);
		this.fatFreeMassIndex = berechneFfmi(koerpergewicht, koerperfettAnteil, koerpergroesse);
	}

	public void setKoerpergewicht(double koerpergewicht)
	{
		this.koerpergewicht = koerpergewicht;
		this.bodyMassIndex = berechneBmi(koerpergroesse, koerpergewicht);
		this.fatFreeMassIndex = berechneFfmi(koerpergewicht, koerperfettAnteil, koerpergroesse);
	}

	public void setKoerperfettAnteil(double koerperfettAnteil)
	{
		this.koerperfettAnteil = koerperfettAnteil;
		this.bodyMassIndex = berechneBmi(koerpergroesse, koerpergewicht);
		this.fatFreeMassIndex = berechneFfmi(koerpergewicht, koerperfettAnteil, koerpergroesse);

	}
}
