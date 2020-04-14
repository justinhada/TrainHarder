package de.justinharder.powerlifting.view.navigation;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Parameter
{
	private static final String PARAMETER_WERTZUWEISUNG = "=";

	private final String name;
	private final String wert;

	public static Parameter fromFehlermeldung(final Fehlermeldung fehlermeldung)
	{
		return new Parameter("fehlerId", String.valueOf(fehlermeldung.getId()));
	}

	public static Parameter fromBenutzer(final String id)
	{
		return new Parameter("benutzerId", id);
	}

	public static Parameter fromUebung(final String id)
	{
		return new Parameter("uebungId", id);
	}

	public static Parameter fromAuthentifizierung(final String id)
	{
		return new Parameter("AuthentifizierungID", id);
	}

	@Override
	public String toString()
	{
		return name + PARAMETER_WERTZUWEISUNG + wert;
	}
}
