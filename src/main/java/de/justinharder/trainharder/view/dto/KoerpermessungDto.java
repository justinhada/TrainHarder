package de.justinharder.trainharder.view.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@ToString(callSuper = true)
public class KoerpermessungDto extends Dto
{
	private static final long serialVersionUID = 46915464001880978L;

	private String datum;
	private int koerpergroesse;
	private double koerpergewicht;
	private double koerperfettAnteil;
	@Setter(value = AccessLevel.NONE)
	private double fettfreiesKoerpergewicht;
	@Setter(value = AccessLevel.NONE)
	private double bodyMassIndex;
	@Setter(value = AccessLevel.NONE)
	private double fatFreeMassIndex;
	private int kalorieneinnahme;
	private int kalorienverbrauch;

	public KoerpermessungDto() {}

	public KoerpermessungDto(
		String primaerschluessel,
		String datum,
		int koerpergroesse,
		double koerpergewicht,
		double koerperfettAnteil,
		int kalorieneinnahme,
		int kalorienverbrauch)
	{
		super(primaerschluessel);
		this.datum = datum;
		this.koerpergroesse = koerpergroesse;
		this.koerpergewicht = koerpergewicht;
		this.koerperfettAnteil = koerperfettAnteil;
		this.kalorieneinnahme = kalorieneinnahme;
		this.kalorienverbrauch = kalorienverbrauch;

		fettfreiesKoerpergewicht = berechneFettfreiesKoerpergewicht(koerpergewicht, koerperfettAnteil);
		bodyMassIndex = berechneBmi(koerpergroesse, koerpergewicht);
		fatFreeMassIndex = berechneFfmi(koerpergewicht, koerperfettAnteil, koerpergroesse);
	}

	private double berechneFettfreiesKoerpergewicht(double koerpergewicht, double koerperfettAnteil)
	{
		return Math.round((koerpergewicht - koerpergewicht * (koerperfettAnteil / 100)) * 100) / 100.0;
	}

	private double berechneBmi(int koerpergroesse, double koerpergewicht)
	{
		var bmi = koerpergewicht / Math.pow(koerpergroesse / 100.0, 2);
		return Math.round(bmi * 100) / 100.0;
	}

	private double berechneFfmi(double koerpergewicht, double koerperfettAnteil, int koerpergroesse)
	{
		var magermasse = koerpergewicht * (1 - koerperfettAnteil / 100.0);
		var ffmi = magermasse / Math.pow(koerpergroesse / 100.0, 2) + 6.1 * (1.8 - koerpergroesse / 100.0);
		return Math.round(ffmi * 100) / 100.0;
	}

	@Override
	public KoerpermessungDto setPrimaerschluessel(String primaerschluessel)
	{
		this.primaerschluessel = primaerschluessel;
		return this;
	}

	public KoerpermessungDto setDatum(String datum)
	{
		this.datum = datum;
		return this;
	}

	public KoerpermessungDto setKoerpergroesse(int koerpergroesse)
	{
		this.koerpergroesse = koerpergroesse;
		fettfreiesKoerpergewicht = berechneFettfreiesKoerpergewicht(koerpergewicht, koerperfettAnteil);
		bodyMassIndex = berechneBmi(koerpergroesse, koerpergewicht);
		fatFreeMassIndex = berechneFfmi(koerpergewicht, koerperfettAnteil, koerpergroesse);
		return this;
	}

	public KoerpermessungDto setKoerpergewicht(double koerpergewicht)
	{
		this.koerpergewicht = koerpergewicht;
		fettfreiesKoerpergewicht = berechneFettfreiesKoerpergewicht(koerpergewicht, koerperfettAnteil);
		bodyMassIndex = berechneBmi(koerpergroesse, koerpergewicht);
		fatFreeMassIndex = berechneFfmi(koerpergewicht, koerperfettAnteil, koerpergroesse);
		return this;
	}

	public KoerpermessungDto setKoerperfettAnteil(double koerperfettAnteil)
	{
		this.koerperfettAnteil = koerperfettAnteil;
		fettfreiesKoerpergewicht = berechneFettfreiesKoerpergewicht(koerpergewicht, koerperfettAnteil);
		bodyMassIndex = berechneBmi(koerpergroesse, koerpergewicht);
		fatFreeMassIndex = berechneFfmi(koerpergewicht, koerperfettAnteil, koerpergroesse);
		return this;
	}

	public KoerpermessungDto setKalorieneinnahme(int kalorieneinnahme)
	{
		this.kalorieneinnahme = kalorieneinnahme;
		return this;
	}

	public KoerpermessungDto setKalorienverbrauch(int kalorienverbrauch)
	{
		this.kalorienverbrauch = kalorienverbrauch;
		return this;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (!(o instanceof KoerpermessungDto))
		{
			return false;
		}
		var that = (KoerpermessungDto) o;
		return koerpergroesse == that.koerpergroesse &&
			Double.compare(that.koerpergewicht, koerpergewicht) == 0 &&
			Double.compare(that.koerperfettAnteil, koerperfettAnteil) == 0 &&
			Double.compare(that.fettfreiesKoerpergewicht, fettfreiesKoerpergewicht) == 0 &&
			Double.compare(that.bodyMassIndex, bodyMassIndex) == 0 &&
			Double.compare(that.fatFreeMassIndex, fatFreeMassIndex) == 0 &&
			kalorieneinnahme == that.kalorieneinnahme &&
			kalorienverbrauch == that.kalorienverbrauch &&
			primaerschluessel.equals(that.primaerschluessel) &&
			datum.equals(that.datum);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(primaerschluessel, datum, koerpergroesse, koerpergewicht, koerperfettAnteil,
			fettfreiesKoerpergewicht, bodyMassIndex, fatFreeMassIndex, kalorieneinnahme, kalorienverbrauch);
	}
}
