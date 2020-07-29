package de.justinharder.trainharder.view.dto;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class KoerpermessungDto implements Serializable
{
	private static final long serialVersionUID = 46915464001880978L;

	private String primaerschluessel;
	private LocalDate datum;
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

	public KoerpermessungDto(
		final String primaerschluessel, 
		final LocalDate datum, 
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
		this.fettfreiesKoerpergewicht = berechneFettfreiesKoerpergewicht(koerpergewicht, koerperfettAnteil);
		this.bodyMassIndex = berechneBmi(koerpergroesse, koerpergewicht);
		this.fatFreeMassIndex = berechneFfmi(koerpergewicht, koerperfettAnteil, koerpergroesse);
		this.kalorieneinnahme = kalorieneinnahme;
		this.kalorienverbrauch = kalorienverbrauch;
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

	public KoerpermessungDto setDatum(final LocalDate datum)
	{
		this.datum = datum;
		return this;
	}

	public KoerpermessungDto setKoerpergroesse(final int koerpergroesse)
	{
		this.koerpergroesse = koerpergroesse;
		this.fettfreiesKoerpergewicht = berechneFettfreiesKoerpergewicht(koerpergewicht, koerperfettAnteil);
		this.bodyMassIndex = berechneBmi(koerpergroesse, koerpergewicht);
		this.fatFreeMassIndex = berechneFfmi(koerpergewicht, koerperfettAnteil, koerpergroesse);
		return this;
	}

	public KoerpermessungDto setKoerpergewicht(final double koerpergewicht)
	{
		this.koerpergewicht = koerpergewicht;
		this.fettfreiesKoerpergewicht = berechneFettfreiesKoerpergewicht(koerpergewicht, koerperfettAnteil);
		this.bodyMassIndex = berechneBmi(koerpergroesse, koerpergewicht);
		this.fatFreeMassIndex = berechneFfmi(koerpergewicht, koerperfettAnteil, koerpergroesse);
		return this;
	}

	public KoerpermessungDto setKoerperfettAnteil(final double koerperfettAnteil)
	{
		this.koerperfettAnteil = koerperfettAnteil;
		this.fettfreiesKoerpergewicht = berechneFettfreiesKoerpergewicht(koerpergewicht, koerperfettAnteil);
		this.bodyMassIndex = berechneBmi(koerpergroesse, koerpergewicht);
		this.fatFreeMassIndex = berechneFfmi(koerpergewicht, koerperfettAnteil, koerpergroesse);
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
