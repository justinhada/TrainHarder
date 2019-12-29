package de.justinharder.powerlifting.model.domain.enums;

import java.util.Arrays;

public enum Stress
{
	NIEDRIG("NIEDRIG"),
	MITTELMAESSIG("MITTELMAESSIG"),
	HOCH("HOCH");

	private String stress;

	private Stress(final String stress)
	{
		this.stress = stress;
	}

	public static Stress fromStressOption(final String stressOption)
	{
		for (final Stress s : Stress.values())
		{
			if (s.stress.equalsIgnoreCase(stressOption))
			{
				return s;
			}
		}

		throw new IllegalArgumentException(
			"Die Stress-Option \"" + stressOption + "\" existiert nicht!");
	}

	public static Stress fromName(final String name)
	{
		return Arrays.asList(Stress.values())
			.stream()
			.filter(s -> s.name().equals(name))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException("Der Name \"" + name + "\" existiert nicht!"));
	}
}
