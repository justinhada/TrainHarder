package de.justinharder.trainharder.model.services.berechnung.anpassungsfaktor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Objects;

@Getter
@ToString
@NoArgsConstructor
public class Anpassungsfaktor
{
	private int alter = 0;
	private int koerpergewicht = 0;
	private int koerpergroesse = 0;
	private int kraftlevel = 0;
	private int geschlecht = 0;
	private int erfahrung = 0;
	private int ernaehrung = 0;
	private int schlafqualitaet = 0;
	private int stress = 0;
	private int doping = 0;
	private int regenerationsfaehigkeit = 0;

	public int werteAus()
	{
		return alter + koerpergewicht + koerpergroesse + kraftlevel + geschlecht + erfahrung + ernaehrung + schlafqualitaet + stress + doping + regenerationsfaehigkeit;
	}

	public Anpassungsfaktor mitAlter(int alter)
	{
		this.alter = alter;
		return this;
	}

	public Anpassungsfaktor mitKoerpergewicht(int koerpergewicht)
	{
		this.koerpergewicht = koerpergewicht;
		return this;
	}

	public Anpassungsfaktor mitKoerpergroesse(int koerpergroesse)
	{
		this.koerpergroesse = koerpergroesse;
		return this;
	}

	public Anpassungsfaktor mitKraftlevel(int kraftlevel)
	{
		this.kraftlevel = kraftlevel;
		return this;
	}

	public Anpassungsfaktor mitGeschlecht(int geschlecht)
	{
		this.geschlecht = geschlecht;
		return this;
	}

	public Anpassungsfaktor mitErfahrung(int erfahrung)
	{
		this.erfahrung = erfahrung;
		return this;
	}

	public Anpassungsfaktor mitErnaehrung(int ernaehrung)
	{
		this.ernaehrung = ernaehrung;
		return this;
	}

	public Anpassungsfaktor mitSchlafqualitaet(int schlafqualitaet)
	{
		this.schlafqualitaet = schlafqualitaet;
		return this;
	}

	public Anpassungsfaktor mitStress(int stress)
	{
		this.stress = stress;
		return this;
	}

	public Anpassungsfaktor mitDoping(int doping)
	{
		this.doping = doping;
		return this;
	}

	public Anpassungsfaktor mitRegenerationsfaehigkeit(int regenerationsfaehigkeit)
	{
		this.regenerationsfaehigkeit = regenerationsfaehigkeit;
		return this;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (!(o instanceof Anpassungsfaktor))
		{
			return false;
		}
		var that = (Anpassungsfaktor) o;
		return alter == that.alter &&
			koerpergewicht == that.koerpergewicht &&
			koerpergroesse == that.koerpergroesse &&
			kraftlevel == that.kraftlevel &&
			geschlecht == that.geschlecht &&
			erfahrung == that.erfahrung &&
			ernaehrung == that.ernaehrung &&
			schlafqualitaet == that.schlafqualitaet &&
			stress == that.stress &&
			doping == that.doping &&
			regenerationsfaehigkeit == that.regenerationsfaehigkeit;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(alter, koerpergewicht, koerpergroesse, kraftlevel, geschlecht, erfahrung, ernaehrung,
			schlafqualitaet, stress, doping, regenerationsfaehigkeit);
	}
}
