package de.justinharder.trainharder.model.services.berechnung;

import com.google.common.base.Preconditions;

import de.justinharder.trainharder.model.domain.Benutzer;
import de.justinharder.trainharder.model.domain.enums.Doping;
import de.justinharder.trainharder.model.domain.enums.Erfahrung;
import de.justinharder.trainharder.model.domain.enums.Ernaehrung;
import de.justinharder.trainharder.model.domain.enums.Geschlecht;
import de.justinharder.trainharder.model.domain.enums.Kraftlevel;
import de.justinharder.trainharder.model.domain.enums.Regenerationsfaehigkeit;
import de.justinharder.trainharder.model.domain.enums.Schlafqualitaet;
import de.justinharder.trainharder.model.domain.enums.Stress;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AnpassungsfaktorBerechner
{
	private int anpassungsfaktor;

	public int berechneAnpassungsfaktor(final Benutzer benutzer)
	{
		Preconditions.checkNotNull(benutzer,
			"Der Benutzer, für den der Anpassungsfaktor berechnet werden soll, existiert nicht!");
		anpassungsfaktor = 0;

		var geschlecht = Geschlecht.MAENNLICH;
		if (benutzer.getBenutzerangabe().getGeschlecht().equals(Geschlecht.WEIBLICH))
		{
			geschlecht = Geschlecht.WEIBLICH;
			anpassungsfaktor += 5;
		}

		berechneKoerpergewichtAnpassungsfaktor(benutzer.getKoerpergewicht(), geschlecht);
		berechneKoerpergroesseAnpassungsfaktor(benutzer.getKoerpergroesse(), geschlecht);
		berechneErfahrungAnpassungsfaktor(benutzer.getBenutzerangabe().getErfahrung());
		berechneLebensalterAnpassungsfaktor(benutzer.getAlter());
		berechneErnaehrungAnpassungsfaktor(benutzer.getBenutzerangabe().getErnaehrung());
		berechneSchlafqualitaetAnpassungsfaktor(benutzer.getBenutzerangabe().getSchlafqualitaet());
		berechneStressAnpassungsfaktor(benutzer.getBenutzerangabe().getStress());
		berechneDopingAnpassungsfaktor(benutzer.getBenutzerangabe().getDoping());
		berechneRegenerationsfaehigkeitAnpassungsfaktor(benutzer.getBenutzerangabe().getRegenerationsfaehigkeit());
		berechneKraftlevelAnpassungsfaktor(benutzer.getBenutzerangabe().getKraftlevel());

		return anpassungsfaktor;
	}

	private void berechneKraftlevelAnpassungsfaktor(final Kraftlevel kraftlevel)
	{
		if (kraftlevel.equals(Kraftlevel.CLASS_5) || kraftlevel.equals(Kraftlevel.CLASS_4))
		{
			anpassungsfaktor += 1;
		}

		if (kraftlevel.equals(Kraftlevel.CLASS_1))
		{
			anpassungsfaktor -= 1;
		}

		if (kraftlevel.equals(Kraftlevel.MASTER) || kraftlevel.equals(Kraftlevel.ELITE))
		{
			anpassungsfaktor -= 3;
		}
	}

	private void berechneRegenerationsfaehigkeitAnpassungsfaktor(final Regenerationsfaehigkeit regenerationsfaehigkeit)
	{
		if (regenerationsfaehigkeit.equals(Regenerationsfaehigkeit.SCHLECHT))
		{
			anpassungsfaktor -= 2;
		}

		if (regenerationsfaehigkeit.equals(Regenerationsfaehigkeit.UNTERDURCHSCHNITTLICH))
		{
			anpassungsfaktor -= 1;
		}

		if (regenerationsfaehigkeit.equals(Regenerationsfaehigkeit.GUT))
		{
			anpassungsfaktor += 1;
		}

		if (regenerationsfaehigkeit.equals(Regenerationsfaehigkeit.PERFEKT))
		{
			anpassungsfaktor += 2;
		}
	}

	private void berechneDopingAnpassungsfaktor(final Doping doping)
	{
		if (doping.equals(Doping.JA))
		{
			anpassungsfaktor += 3;
		}
	}

	private void berechneStressAnpassungsfaktor(final Stress stress)
	{
		if (stress.equals(Stress.NIEDRIG))
		{
			anpassungsfaktor += 1;
		}

		if (stress.equals(Stress.HOCH))
		{
			anpassungsfaktor -= 3;
		}
	}

	private void berechneSchlafqualitaetAnpassungsfaktor(final Schlafqualitaet schlafqualitaet)
	{
		if (schlafqualitaet.equals(Schlafqualitaet.SCHLECHT))
		{
			anpassungsfaktor -= 3;
		}

		if (schlafqualitaet.equals(Schlafqualitaet.GUT))
		{
			anpassungsfaktor += 1;
		}
	}

	private void berechneErnaehrungAnpassungsfaktor(final Ernaehrung ernaehrung)
	{
		if (ernaehrung.equals(Ernaehrung.SCHLECHT))
		{
			anpassungsfaktor -= 3;
		}

		if (ernaehrung.equals(Ernaehrung.GUT))
		{
			anpassungsfaktor += 1;
		}
	}

	private void berechneLebensalterAnpassungsfaktor(final int lebensalter)
	{
		if (lebensalter < 15)
		{
			anpassungsfaktor += 2;
		}

		if (lebensalter >= 15 && lebensalter < 25)
		{
			anpassungsfaktor += 1;
		}

		if (lebensalter >= 35 && lebensalter < 45)
		{
			anpassungsfaktor -= 2;
		}

		if (lebensalter >= 45)
		{
			anpassungsfaktor -= 4;
		}
	}

	private void berechneErfahrungAnpassungsfaktor(final Erfahrung erfahrung)
	{
		if (erfahrung.equals(Erfahrung.BEGINNER))
		{
			anpassungsfaktor += 1;
		}

		if (erfahrung.equals(Erfahrung.SEHR_FORTGESCHRITTEN))
		{
			anpassungsfaktor -= 1;
		}

		if (erfahrung.equals(Erfahrung.EXPERTE))
		{
			anpassungsfaktor -= 3;
		}
	}

	private void berechneKoerpergroesseAnpassungsfaktor(final int koerpergroesse, final Geschlecht geschlecht)
	{
		if (geschlecht.equals(Geschlecht.WEIBLICH) && koerpergroesse < 160 ||
			geschlecht.equals(Geschlecht.MAENNLICH) && koerpergroesse < 170)
		{
			anpassungsfaktor += 2;
		}

		if (geschlecht.equals(Geschlecht.WEIBLICH) && koerpergroesse >= 160 && koerpergroesse < 167 ||
			geschlecht.equals(Geschlecht.MAENNLICH) && koerpergroesse >= 170 && koerpergroesse < 183)
		{
			anpassungsfaktor += 1;
		}

		if (geschlecht.equals(Geschlecht.WEIBLICH) && koerpergroesse >= 167 && koerpergroesse < 175 ||
			geschlecht.equals(Geschlecht.MAENNLICH) && koerpergroesse >= 183 && koerpergroesse < 195)
		{
			anpassungsfaktor -= 1;
		}

		if (geschlecht.equals(Geschlecht.WEIBLICH) && koerpergroesse >= 175 ||
			geschlecht.equals(Geschlecht.MAENNLICH) && koerpergroesse >= 195)
		{
			anpassungsfaktor -= 2;
		}
	}

	private void berechneKoerpergewichtAnpassungsfaktor(final double koerpergewicht, final Geschlecht geschlecht)
	{
		if (geschlecht.equals(Geschlecht.WEIBLICH) && koerpergewicht < 57 ||
			geschlecht.equals(Geschlecht.MAENNLICH) && koerpergewicht < 74)
		{
			anpassungsfaktor += 4;
		}

		if (geschlecht.equals(Geschlecht.WEIBLICH) && koerpergewicht >= 57 && koerpergewicht < 74 ||
			geschlecht.equals(Geschlecht.MAENNLICH) && koerpergewicht >= 74 && koerpergewicht < 105)
		{
			anpassungsfaktor += 2;
		}

		if (geschlecht.equals(Geschlecht.WEIBLICH) && koerpergewicht >= 74 && koerpergewicht < 84 ||
			geschlecht.equals(Geschlecht.MAENNLICH) && koerpergewicht >= 105 && koerpergewicht < 120)
		{
			anpassungsfaktor -= 2;
		}

		if (geschlecht.equals(Geschlecht.WEIBLICH) && koerpergewicht >= 84 ||
			geschlecht.equals(Geschlecht.MAENNLICH) && koerpergewicht >= 120)
		{
			anpassungsfaktor -= 4;
		}
	}
}
