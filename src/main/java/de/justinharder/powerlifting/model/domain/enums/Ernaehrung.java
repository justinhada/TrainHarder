package de.justinharder.powerlifting.model.domain.enums;

import java.util.Arrays;

public enum Ernaehrung
{
	SCHLECHT("SCHLECHT"),
	DURCHSCHNITT("DURCHSCHNITT"),
	GUT("GUT");

	private String ernaehrung;

	private Ernaehrung(final String ernaehrung)
	{
		this.ernaehrung = ernaehrung;
	}

	public static Ernaehrung fromErnaehrungOption(final String ernaehrungOption)
	{
		for (final Ernaehrung e : Ernaehrung.values())
		{
			if (e.ernaehrung.equalsIgnoreCase(ernaehrungOption))
			{
				return e;
			}
		}

		throw new IllegalArgumentException("Die Ernaehrung-Option \"" + ernaehrungOption + "\" existiert nicht!");
	}

	public static Ernaehrung fromName(final String name)
	{
		return Arrays.asList(Ernaehrung.values())
			.stream()
			.filter(e -> e.name().equals(name))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException("Der Name \"" + name + "\" existiert nicht!"));
	}
}
