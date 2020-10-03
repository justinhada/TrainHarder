package de.justinharder.trainharder.view.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.io.Serializable;

@Data
public class KoerpermessungDto implements Serializable
{
	private static final long serialVersionUID = 46915464001880978L;

	private String primaerschluessel;
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

	public KoerpermessungDto()
	{}

	public KoerpermessungDto(
		final String primaerschluessel,
		final String datum,
		final int koerpergroesse,
		final double koerpergewicht,
		final double koerperfettAnteil,
		final int kalorieneinnahme,
		final int kalorienverbrauch)
	{
		this.primaerschluessel = primaerschluessel;
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

	private double berechneFettfreiesKoerpergewicht(final double koerpergewicht, final double koerperfettAnteil)
	{
		return Math.round((koerpergewicht - koerpergewicht * (koerperfettAnteil / 100)) * 100) / 100.0;
	}

	private double berechneBmi(final int koerpergroesse, final double koerpergewicht)
	{
		final var bmi = koerpergewicht / Math.pow(koerpergroesse / 100.0, 2);
		return Math.round(bmi * 100) / 100.0;
	}

	private double berechneFfmi(final double koerpergewicht, final double koerperfettAnteil, final int koerpergroesse)
	{
		final var magermasse = koerpergewicht * (1 - koerperfettAnteil / 100.0);
		final var ffmi = magermasse / Math.pow(koerpergroesse / 100.0, 2) + 6.1 * (1.8 - koerpergroesse / 100.0);
		return Math.round(ffmi * 100) / 100.0;
	}

	public KoerpermessungDto setPrimaerschluessel(final String primaerschluessel)
	{
		this.primaerschluessel = primaerschluessel;
		return this;
	}

	public KoerpermessungDto setDatum(final String datum)
	{
		this.datum = datum;
		return this;
	}

	public KoerpermessungDto setKoerpergroesse(final int koerpergroesse)
	{
		this.koerpergroesse = koerpergroesse;
		fettfreiesKoerpergewicht = berechneFettfreiesKoerpergewicht(koerpergewicht, koerperfettAnteil);
		bodyMassIndex = berechneBmi(koerpergroesse, koerpergewicht);
		fatFreeMassIndex = berechneFfmi(koerpergewicht, koerperfettAnteil, koerpergroesse);
		return this;
	}

	public KoerpermessungDto setKoerpergewicht(final double koerpergewicht)
	{
		this.koerpergewicht = koerpergewicht;
		fettfreiesKoerpergewicht = berechneFettfreiesKoerpergewicht(koerpergewicht, koerperfettAnteil);
		bodyMassIndex = berechneBmi(koerpergroesse, koerpergewicht);
		fatFreeMassIndex = berechneFfmi(koerpergewicht, koerperfettAnteil, koerpergroesse);
		return this;
	}

	public KoerpermessungDto setKoerperfettAnteil(final double koerperfettAnteil)
	{
		this.koerperfettAnteil = koerperfettAnteil;
		fettfreiesKoerpergewicht = berechneFettfreiesKoerpergewicht(koerpergewicht, koerperfettAnteil);
		bodyMassIndex = berechneBmi(koerpergroesse, koerpergewicht);
		fatFreeMassIndex = berechneFfmi(koerpergewicht, koerperfettAnteil, koerpergroesse);
		return this;
	}

	public KoerpermessungDto setKalorieneinnahme(final int kalorieneinnahme)
	{
		this.kalorieneinnahme = kalorieneinnahme;
		return this;
	}

	public KoerpermessungDto setKalorienverbrauch(final int kalorienverbrauch)
	{
		this.kalorienverbrauch = kalorienverbrauch;
		return this;
	}
}
