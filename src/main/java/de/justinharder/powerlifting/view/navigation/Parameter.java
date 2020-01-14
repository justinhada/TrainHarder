package de.justinharder.powerlifting.view.navigation;

public class Parameter
{
	private static final String PARAMETER_WERTZUWEISUNG = "=";

	private final String name;
	private final String wert;

	private Parameter(final String name, final String wert)
	{
		this.name = name;
		this.wert = wert;
	}

	public static Parameter fromFehlermeldung(final Fehlermeldung fehlermeldung)
	{
		return new Parameter("fehlerId", String.valueOf(fehlermeldung.getId()));
	}

	public static Parameter fromBenutzer(final String id)
	{
		return new Parameter("benutzerId", id);
	}

	@Override
	public String toString()
	{
		return name + PARAMETER_WERTZUWEISUNG + wert;
	}
}
