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
		for (var i = 0; i < klassifikationen.get(0).size(); i++)
		{
			if (koerpergewicht > klassifikationen.get(0).get(i))
			{
				if (i + 1 == klassifikationen.get(0).size())
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
		for (var i = klassifikationen.size() - 1; i > 0; i--)
		{
			if (total >= klassifikationen.get(i).get(gewichtIndex))
			{
				totalIndex = i;
			}
		}

		final var kraftlevel = Konstanten.KRAFTLEVEL_EINTEILUNG.get(totalIndex - 1);
		benutzer.getBenutzerangabe().setKraftlevel(kraftlevel);
	}
}
