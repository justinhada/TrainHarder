package de.justinharder.trainharder.model.services.berechnung.anpassungsfaktor;

import de.justinharder.trainharder.model.domain.Benutzer;
import de.justinharder.trainharder.model.domain.enums.*;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.math.BigDecimal;

@NoArgsConstructor
public class AnpassungsfaktorErmittlung
{
	public Anpassungsfaktor berechneAnpassungsfaktor(@NonNull Benutzer benutzer)
	{
		var benutzerangabe = benutzer.getBenutzerangabe();
		var geschlecht = benutzerangabe.getGeschlecht();

		return new Anpassungsfaktor()
			.mitAlter(alter(benutzer.getAlter()))
			.mitKoerpergewicht(koerpergewicht(benutzer.getKoerpergewicht(), geschlecht))
			.mitKoerpergroesse(koerpergroesse(benutzer.getKoerpergroesse(), geschlecht))
			.mitKraftlevel(kraftlevel(benutzerangabe.getKraftlevel()))
			.mitGeschlecht(geschlecht(geschlecht))
			.mitErfahrung(erfahrung(benutzerangabe.getErfahrung()))
			.mitErnaehrung(ernaehrung(benutzerangabe.getErnaehrung()))
			.mitSchlafqualitaet(schlafqualitaet(benutzerangabe.getSchlafqualitaet()))
			.mitStress(stress(benutzerangabe.getStress()))
			.mitDoping(doping(benutzerangabe.getDoping()))
			.mitRegenerationsfaehigkeit(regenerationsfaehigkeit(benutzerangabe.getRegenerationsfaehigkeit()));
	}

	private int geschlecht(Geschlecht geschlecht)
	{
		return geschlecht.equals(Geschlecht.WEIBLICH) ? 5 : 0;
	}

	private int kraftlevel(Kraftlevel kraftlevel)
	{
		if (kraftlevel.equals(Kraftlevel.CLASS_5) || kraftlevel.equals(Kraftlevel.CLASS_4))
		{
			return 1;
		}

		if (kraftlevel.equals(Kraftlevel.CLASS_1))
		{
			return -1;
		}

		if (kraftlevel.equals(Kraftlevel.MASTER) || kraftlevel.equals(Kraftlevel.ELITE))
		{
			return -3;
		}

		return 0;
	}

	private int regenerationsfaehigkeit(Regenerationsfaehigkeit regenerationsfaehigkeit)
	{
		if (regenerationsfaehigkeit.equals(Regenerationsfaehigkeit.SCHLECHT))
		{
			return -2;
		}

		if (regenerationsfaehigkeit.equals(Regenerationsfaehigkeit.UNTERDURCHSCHNITTLICH))
		{
			return -1;
		}

		if (regenerationsfaehigkeit.equals(Regenerationsfaehigkeit.GUT))
		{
			return 1;
		}

		if (regenerationsfaehigkeit.equals(Regenerationsfaehigkeit.PERFEKT))
		{
			return 2;
		}

		return 0;
	}

	private int doping(Doping doping)
	{
		return doping.equals(Doping.JA) ? 3 : 0;
	}

	private int stress(Stress stress)
	{
		if (stress.equals(Stress.NIEDRIG))
		{
			return +1;
		}

		if (stress.equals(Stress.HOCH))
		{
			return -3;
		}

		return 0;
	}

	private int schlafqualitaet(Schlafqualitaet schlafqualitaet)
	{
		if (schlafqualitaet.equals(Schlafqualitaet.SCHLECHT))
		{
			return -3;
		}

		if (schlafqualitaet.equals(Schlafqualitaet.GUT))
		{
			return 1;
		}

		return 0;
	}

	private int ernaehrung(Ernaehrung ernaehrung)
	{
		if (ernaehrung.equals(Ernaehrung.SCHLECHT))
		{
			return -3;
		}

		if (ernaehrung.equals(Ernaehrung.GUT))
		{
			return 1;
		}

		return 0;
	}

	private int alter(int lebensalter)
	{
		if (lebensalter < 15)
		{
			return 2;
		}

		if (lebensalter < 25)
		{
			return 1;
		}

		if (lebensalter < 35)
		{
			return 0;
		}

		if (lebensalter < 45)
		{
			return -2;
		}

		return -4;
	}

	private int erfahrung(Erfahrung erfahrung)
	{
		if (erfahrung.equals(Erfahrung.BEGINNER))
		{
			return 1;
		}

		if (erfahrung.equals(Erfahrung.SEHR_FORTGESCHRITTEN))
		{
			return -1;
		}

		if (erfahrung.equals(Erfahrung.EXPERTE))
		{
			return -3;
		}

		return 0;
	}

	private int koerpergroesse(BigDecimal koerpergroesse, Geschlecht geschlecht)
	{
		if (geschlecht.equals(Geschlecht.WEIBLICH) && koerpergroesse.intValueExact() < 160 || geschlecht.equals(Geschlecht.MAENNLICH) && koerpergroesse.intValueExact() < 170)
		{
			return 2;
		}

		if (geschlecht.equals(Geschlecht.WEIBLICH) && koerpergroesse.intValueExact() < 167 || geschlecht.equals(Geschlecht.MAENNLICH) && koerpergroesse.intValueExact() < 183)
		{
			return 1;
		}

		if (geschlecht.equals(Geschlecht.WEIBLICH) && koerpergroesse.intValueExact() < 175 || geschlecht.equals(Geschlecht.MAENNLICH) && koerpergroesse.intValueExact() < 195)
		{
			return -1;
		}

		return -2;
	}

	private int koerpergewicht(BigDecimal koerpergewicht, Geschlecht geschlecht)
	{
		if (geschlecht.equals(Geschlecht.WEIBLICH) && koerpergewicht.doubleValue() < 57 || geschlecht.equals(Geschlecht.MAENNLICH) && koerpergewicht.doubleValue() < 74)
		{
			return 4;
		}

		if (geschlecht.equals(Geschlecht.WEIBLICH) && koerpergewicht.doubleValue() < 74 || geschlecht.equals(Geschlecht.MAENNLICH) && koerpergewicht.doubleValue() < 105)
		{
			return 2;
		}

		if (geschlecht.equals(Geschlecht.WEIBLICH) && koerpergewicht.doubleValue() < 84 || geschlecht.equals(Geschlecht.MAENNLICH) && koerpergewicht.doubleValue() < 120)
		{
			return -2;
		}

		return -4;
	}
}