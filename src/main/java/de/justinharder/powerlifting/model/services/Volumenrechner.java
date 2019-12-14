package de.justinharder.powerlifting.model.services;

import de.justinharder.powerlifting.model.domain.Benutzer;
import de.justinharder.powerlifting.model.domain.enums.Geschlecht;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Volumenrechner
{
	private int anpassungsfaktor;

	public void berechneAnpassungsfaktor(final Benutzer benutzer)
	{
		anpassungsfaktor = 0;

		if (benutzer.getGeschlecht().equals(Geschlecht.WEIBLICH))
		{
			anpassungsfaktor += 5;
		}
	}
}
