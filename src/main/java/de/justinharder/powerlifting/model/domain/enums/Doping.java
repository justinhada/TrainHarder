package de.justinharder.powerlifting.model.domain.enums;

import java.util.Arrays;

public enum Doping
{
	NEIN("NEIN"),
	JA("JA");

	private String doping;

	private Doping(final String doping)
	{
		this.doping = doping;
	}

	public static Doping fromDopingOption(final String dopingOption)
	{
		for (final Doping d : Doping.values())
		{
			if (d.doping.equalsIgnoreCase(dopingOption))
			{
				return d;
			}
		}

		throw new IllegalArgumentException("Die Doping-Option \"" + dopingOption + "\" existiert nicht!");
	}

	public static Doping fromName(final String name)
	{
		return Arrays.asList(Doping.values())
			.stream()
			.filter(d -> d.name().equals(name))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException("Der Name \"" + name + "\" existiert nicht!"));
	}
}
