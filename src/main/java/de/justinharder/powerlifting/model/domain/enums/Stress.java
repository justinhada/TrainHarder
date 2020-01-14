package de.justinharder.powerlifting.model.domain.enums;

import java.util.stream.Stream;

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
		return Stream.of(Stress.values())
			.filter(s -> s.stress.equalsIgnoreCase(stressOption))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException(
				"Die Stress-Option \"" + stressOption + "\" existiert nicht!"));
	}

	public static Stress fromName(final String name)
	{
		return Stream.of(Stress.values())
			.filter(s -> s.name().equals(name))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException("Der Name \"" + name + "\" existiert nicht!"));
	}
}
