package de.justinharder.powerlifting.model.domain.enums;

import java.util.Arrays;

public enum Geschlecht
{
	MAENNLICH("MAENNLICH"),
	WEIBLICH("WEIBLICH");

	private String geschlecht;

	private Geschlecht(final String geschlecht)
	{
		this.geschlecht = geschlecht;
	}

	public static Geschlecht fromGeschlechtOption(final String geschlechtOption)
	{
		for (final Geschlecht g : Geschlecht.values())
		{
			if (g.geschlecht.equalsIgnoreCase(geschlechtOption))
			{
				return g;
			}
		}

		throw new IllegalArgumentException("Die Geschlecht-Option \"" + geschlechtOption + "\" existiert nicht!");
	}

	public static Geschlecht fromName(final String name)
	{
		return Arrays.asList(Geschlecht.values())
			.stream()
			.filter(g -> g.name().equals(name))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException("Der Name \"" + name + "\" existiert nicht!"));
	}
}
