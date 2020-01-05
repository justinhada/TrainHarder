package de.justinharder.powerlifting.model.domain.enums;

import java.util.Arrays;

public enum Kraftlevel
{
	CLASS_5("CLASS_5"),
	CLASS_4("CLASS_4"),
	CLASS_3("CLASS_3"),
	CLASS_2("CLASS_2"),
	CLASS_1("CLASS_1"),
	MASTER("MASTER"),
	ELITE("ELITE");

	private String kraftlevel;

	private Kraftlevel(final String kraftlevel)
	{
		this.kraftlevel = kraftlevel;
	}

	public static Kraftlevel fromKraftlevelOption(final String kraftlevelOption)
	{
		for (final Kraftlevel k : Kraftlevel.values())
		{
			if (k.kraftlevel.equalsIgnoreCase(kraftlevelOption))
			{
				return k;
			}
		}

		throw new IllegalArgumentException("Die Kraftlevel-Option \"" + kraftlevelOption + "\" existiert nicht!");
	}

	public static Kraftlevel fromName(final String name)
	{
		return Arrays.asList(Kraftlevel.values())
			.stream()
			.filter(k -> k.name().equals(name))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException("Der Name \"" + name + "\" existiert nicht!"));
	}

}
