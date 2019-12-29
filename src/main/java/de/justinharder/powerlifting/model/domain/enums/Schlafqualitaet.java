package de.justinharder.powerlifting.model.domain.enums;

import java.util.Arrays;

public enum Schlafqualitaet
{
	SCHLECHT("SCHLECHT"),
	DURCHSCHNITT("DURCHSCHNITT"),
	GUT("GUT");

	private String schlafqualitaet;

	private Schlafqualitaet(final String schlafqualitaet)
	{
		this.schlafqualitaet = schlafqualitaet;
	}

	public static Schlafqualitaet fromSchlafqualitaetOption(final String schlafqualitaetOption)
	{
		for (final Schlafqualitaet s : Schlafqualitaet.values())
		{
			if (s.schlafqualitaet.equalsIgnoreCase(schlafqualitaetOption))
			{
				return s;
			}
		}

		throw new IllegalArgumentException(
			"Die Schlafqualitaet-Option \"" + schlafqualitaetOption + "\" existiert nicht!");
	}

	public static Schlafqualitaet fromName(final String name)
	{
		return Arrays.asList(Schlafqualitaet.values())
			.stream()
			.filter(s -> s.name().equals(name))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException("Der Name \"" + name + "\" existiert nicht!"));
	}
}
