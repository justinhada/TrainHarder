package de.justinharder.trainharder.model.domain.embeddables;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Embeddable
public class Koerpermasse implements Serializable
{
	private static final long serialVersionUID = 3836150830695827628L;

	@Column(name = "Koerpergroesse")
	private int koerpergroesse;
	@Column(name = "Koerpergewicht")
	private double koerpergewicht;
	@Column(name = "KoerperfettAnteil")
	private double koerperfettAnteil;
	@Column(name = "FettfreiesKoerpergewicht")
	@Setter(value = AccessLevel.NONE)
	private double fettfreiesKoerpergewicht;
	@Column(name = "BodyMassIndex")
	@Setter(value = AccessLevel.NONE)
	private double bodyMassIndex;
	@Column(name = "FatFreeMassIndex")
	@Setter(value = AccessLevel.NONE)
	private double fatFreeMassIndex;

	public Koerpermasse(final int koerpergroesse, final double koerpergewicht, final double koerperfettAnteil)
	{
		this.koerpergroesse = koerpergroesse;
		this.koerpergewicht = koerpergewicht;
		this.koerperfettAnteil = koerperfettAnteil;

		this.fettfreiesKoerpergewicht = berechneFettfreiesKoerpergewicht(koerpergewicht, koerperfettAnteil);
		this.bodyMassIndex = berechneBmi(koerpergroesse, koerpergewicht);
		this.fatFreeMassIndex = berechneFfmi(koerpergewicht, koerperfettAnteil, koerpergroesse);
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

	public Koerpermasse setKoerpergroesse(final int koerpergroesse)
	{
		this.koerpergroesse = koerpergroesse;
		this.bodyMassIndex = berechneBmi(koerpergroesse, koerpergewicht);
		this.fettfreiesKoerpergewicht = berechneFettfreiesKoerpergewicht(koerpergewicht, koerperfettAnteil);
		this.fatFreeMassIndex = berechneFfmi(koerpergewicht, koerperfettAnteil, koerpergroesse);
		return this;
	}

	public Koerpermasse setKoerpergewicht(final double koerpergewicht)
	{
		this.koerpergewicht = koerpergewicht;
		this.fettfreiesKoerpergewicht = berechneFettfreiesKoerpergewicht(koerpergewicht, koerperfettAnteil);
		this.bodyMassIndex = berechneBmi(koerpergroesse, koerpergewicht);
		this.fatFreeMassIndex = berechneFfmi(koerpergewicht, koerperfettAnteil, koerpergroesse);
		return this;
	}

	public Koerpermasse setKoerperfettAnteil(final double koerperfettAnteil)
	{
		this.koerperfettAnteil = koerperfettAnteil;
		this.fettfreiesKoerpergewicht = berechneFettfreiesKoerpergewicht(koerpergewicht, koerperfettAnteil);
		this.bodyMassIndex = berechneBmi(koerpergroesse, koerpergewicht);
		this.fatFreeMassIndex = berechneFfmi(koerpergewicht, koerperfettAnteil, koerpergroesse);
		return this;
	}
}
