package de.justinharder.powerlifting.model.domain.enums;

import java.util.Arrays;

public enum Erfahrung
{
	BEGINNER("BEGINNER"), // < 4 Jahre
	FORTGESCHRITTEN("FORTGESCHRITTEN"), // 4-8 Jahre
	SEHR_FORTGESCHRITTEN("SEHR_FORTGESCHRITTEN"), // 8-12 Jahre
	EXPERTE("EXPERTE"); // > 12 Jahre

	private String erfahrung;

	private Erfahrung(final String erfahrung)
	{
		this.erfahrung = erfahrung;
	}

	public static Erfahrung fromErfahrungOption(final String erfahrungOption)
	{
		for (final Erfahrung e : Erfahrung.values())
		{
			if (e.erfahrung.equalsIgnoreCase(erfahrungOption))
			{
				return e;
			}
		}

		throw new IllegalArgumentException("Die Erfahrung-Option \"" + erfahrungOption + "\" existiert nicht!");
	}

	public static Erfahrung fromName(final String name)
	{
		return Arrays.asList(Erfahrung.values())
			.stream()
			.filter(e -> e.name().equals(name))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException("Der Name \"" + name + "\" existiert nicht!"));
	}
}
