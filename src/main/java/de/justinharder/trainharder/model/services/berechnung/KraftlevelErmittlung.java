package de.justinharder.trainharder.model.services.berechnung;

import de.justinharder.trainharder.model.domain.Benutzer;
import de.justinharder.trainharder.model.domain.Konstanten;
import de.justinharder.trainharder.model.domain.Kraftwert;
import de.justinharder.trainharder.model.domain.enums.Geschlecht;
import de.justinharder.trainharder.model.domain.enums.Kraftlevel;

public class KraftlevelErmittlung
{
	private static final int KOERPERGEWICHT_ZEILE = 0;

	public Kraftlevel ermittle(Benutzer benutzer)
	{
		var klassifikation = benutzer.getBenutzerangabe().getGeschlecht().equals(Geschlecht.WEIBLICH)
			? Konstanten.KLASSIFIKATION_FRAUEN : Konstanten.KLASSIFIKATION_MAENNER;

		var gewichtIndex = 0;
		var koerpergewichte = klassifikation.get(KOERPERGEWICHT_ZEILE);
		for (var i = 0; i < koerpergewichte.size(); i++)
		{
			if (benutzer.getKoerpergewicht() > koerpergewichte.get(i))
			{
				if (i + 1 == koerpergewichte.size())
				{
					gewichtIndex = i;
				}
				else
				{
					gewichtIndex = i + 1;
				}
			}
		}

		var total = benutzer.getKraftwerte().stream()
			.mapToDouble(Kraftwert::getGewicht)
			.sum();

		var totalIndex = 0;
		for (var i = 0; i < klassifikation.size(); i++)
		{
			if (Double.compare(total, klassifikation.get(i).get(gewichtIndex)) < 0)
			{
				totalIndex = i;
			}
		}

		return Konstanten.KRAFTLEVEL_EINTEILUNG.get(totalIndex);
	}
}
