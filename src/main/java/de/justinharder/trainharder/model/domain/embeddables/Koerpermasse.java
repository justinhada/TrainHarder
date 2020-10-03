package de.justinharder.trainharder.model.domain.embeddables;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Getter
@ToString
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
	@Setter(value = AccessLevel.NONE)
	@Column(name = "FettfreiesKoerpergewicht")
	private double fettfreiesKoerpergewicht;
	@Setter(value = AccessLevel.NONE)
	@Column(name = "BodyMassIndex")
	private double bodyMassIndex;
	@Setter(value = AccessLevel.NONE)
	@Column(name = "FatFreeMassIndex")
	private double fatFreeMassIndex;

	public Koerpermasse()
	{}

	public Koerpermasse(final int koerpergroesse, final double koerpergewicht, final double koerperfettAnteil)
	{
		this.koerpergroesse = koerpergroesse;
		this.koerpergewicht = koerpergewicht;
		this.koerperfettAnteil = koerperfettAnteil;

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

	public Koerpermasse setKoerpergroesse(final int koerpergroesse)
	{
		this.koerpergroesse = koerpergroesse;
		bodyMassIndex = berechneBmi(koerpergroesse, koerpergewicht);
		fettfreiesKoerpergewicht = berechneFettfreiesKoerpergewicht(koerpergewicht, koerperfettAnteil);
		fatFreeMassIndex = berechneFfmi(koerpergewicht, koerperfettAnteil, koerpergroesse);
		return this;
	}

	public Koerpermasse setKoerpergewicht(final double koerpergewicht)
	{
		this.koerpergewicht = koerpergewicht;
		fettfreiesKoerpergewicht = berechneFettfreiesKoerpergewicht(koerpergewicht, koerperfettAnteil);
		bodyMassIndex = berechneBmi(koerpergroesse, koerpergewicht);
		fatFreeMassIndex = berechneFfmi(koerpergewicht, koerperfettAnteil, koerpergroesse);
		return this;
	}

	public Koerpermasse setKoerperfettAnteil(final double koerperfettAnteil)
	{
		this.koerperfettAnteil = koerperfettAnteil;
		fettfreiesKoerpergewicht = berechneFettfreiesKoerpergewicht(koerpergewicht, koerperfettAnteil);
		bodyMassIndex = berechneBmi(koerpergroesse, koerpergewicht);
		fatFreeMassIndex = berechneFfmi(koerpergewicht, koerperfettAnteil, koerpergroesse);
		return this;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (o == null || getClass() != o.getClass())
		{
			return false;
		}
		Koerpermasse that = (Koerpermasse) o;
		return koerpergroesse == that.koerpergroesse &&
			Double.compare(that.koerpergewicht, koerpergewicht) == 0 &&
			Double.compare(that.koerperfettAnteil, koerperfettAnteil) == 0 &&
			Double.compare(that.fettfreiesKoerpergewicht, fettfreiesKoerpergewicht) == 0 &&
			Double.compare(that.bodyMassIndex, bodyMassIndex) == 0 &&
			Double.compare(that.fatFreeMassIndex, fatFreeMassIndex) == 0;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(koerpergroesse, koerpergewicht, koerperfettAnteil, fettfreiesKoerpergewicht, bodyMassIndex,
			fatFreeMassIndex);
	}
}
