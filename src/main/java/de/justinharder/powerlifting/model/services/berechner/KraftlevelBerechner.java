package de.justinharder.powerlifting.model.services.berechner;

import java.util.List;

import de.justinharder.powerlifting.model.domain.Benutzer;
import de.justinharder.powerlifting.model.domain.Konstanten;
import de.justinharder.powerlifting.model.domain.Kraftwert;
import de.justinharder.powerlifting.model.domain.enums.Geschlecht;

public class KraftlevelBerechner
{
	private final Benutzer benutzer;
	private final Geschlecht geschlecht;
	private final double koerpergewicht;
	private final List<Kraftwert> kraftwerte;

	public KraftlevelBerechner(final Benutzer benutzer)
	{
		this.benutzer = benutzer;
		geschlecht = benutzer.getGeschlecht();
		koerpergewicht = benutzer.getAktuellesKoerpergewicht();
		kraftwerte = benutzer.getKraftwerte();
	}

	public void setzeKraftlevel()
	{
		final var klassifikationen =
			geschlecht.equals(Geschlecht.WEIBLICH) ? Konstanten.KLASSIFIKATION_FRAUEN
				: Konstanten.KLASSIFIKATION_MAENNER;

		var total = 0;
		for (final var kraftwert : kraftwerte)
		{
			total += kraftwert.getMaximum();
		}

		var gewichtIndex = 0;
		for (var i = 0; i < klassifikationen[0].length; i++)
		{
			if (koerpergewicht > klassifikationen[0][i])
			{
				if (i + 1 == klassifikationen[0].length)
				{
					gewichtIndex = i;
				}
				else
				{
					gewichtIndex = i + 1;
				}
			}
		}

		var totalIndex = 0;
		for (var i = klassifikationen.length - 1; i > 0; i--)
		{
			if (total >= klassifikationen[i][gewichtIndex])
			{
				totalIndex = i;
			}
		}

		final var kraftlevel = Konstanten.KRAFTLEVEL_EINTEILUNG[totalIndex - 1];
		benutzer.setKraftlevel(kraftlevel);
	}
}
