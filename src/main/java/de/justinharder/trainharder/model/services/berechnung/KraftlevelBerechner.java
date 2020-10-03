package de.justinharder.trainharder.model.services.berechnung;

import de.justinharder.trainharder.model.domain.Benutzer;
import de.justinharder.trainharder.model.domain.Konstanten;
import de.justinharder.trainharder.model.domain.Kraftwert;
import de.justinharder.trainharder.model.domain.enums.Geschlecht;

import java.util.List;

public class KraftlevelBerechner
{
	private final Benutzer benutzer;
	private final Geschlecht geschlecht;
	private final double koerpergewicht;
	private final List<Kraftwert> kraftwerte;

	public KraftlevelBerechner(final Benutzer benutzer)
	{
		this.benutzer = benutzer;
		geschlecht = benutzer.getBenutzerangabe().getGeschlecht();
		koerpergewicht = benutzer.getKoerpergewicht();
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
		benutzer.getBenutzerangabe().setKraftlevel(kraftlevel);
	}
}
