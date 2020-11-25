package de.justinharder.trainharder.model.services.berechnung.anpassungsfaktor;

import com.google.common.base.Preconditions;
import de.justinharder.trainharder.model.domain.Benutzer;
import de.justinharder.trainharder.model.domain.enums.*;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AnpassungsfaktorErmittlung
{
	public Anpassungsfaktor berechneAnpassungsfaktor(Benutzer benutzer)
	{
		Preconditions.checkNotNull(benutzer, "Benutzer ist null!");

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

	private int koerpergroesse(int koerpergroesse, Geschlecht geschlecht)
	{
		if (geschlecht.equals(Geschlecht.WEIBLICH) && koerpergroesse < 160 ||
			geschlecht.equals(Geschlecht.MAENNLICH) && koerpergroesse < 170)
		{
			return 2;
		}

		if (geschlecht.equals(Geschlecht.WEIBLICH) && koerpergroesse < 167 ||
			geschlecht.equals(Geschlecht.MAENNLICH) && koerpergroesse < 183)
		{
			return 1;
		}

		if (geschlecht.equals(Geschlecht.WEIBLICH) && koerpergroesse < 175 ||
			geschlecht.equals(Geschlecht.MAENNLICH) && koerpergroesse < 195)
		{
			return -1;
		}

		return -2;
	}

	private int koerpergewicht(double koerpergewicht, Geschlecht geschlecht)
	{
		if (geschlecht.equals(Geschlecht.WEIBLICH) && koerpergewicht < 57 ||
			geschlecht.equals(Geschlecht.MAENNLICH) && koerpergewicht < 74)
		{
			return 4;
		}

		if (geschlecht.equals(Geschlecht.WEIBLICH) && koerpergewicht < 74 ||
			geschlecht.equals(Geschlecht.MAENNLICH) && koerpergewicht < 105)
		{
			return 2;
		}

		if (geschlecht.equals(Geschlecht.WEIBLICH) && koerpergewicht < 84 ||
			geschlecht.equals(Geschlecht.MAENNLICH) && koerpergewicht < 120)
		{
			return -2;
		}

		return -4;
	}
}
