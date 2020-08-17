package de.justinharder.trainharder.model.domain.enums;

import java.util.stream.Stream;

public enum Stress
{
	NIEDRIG("NIEDRIG"),
	MITTELMAESSIG("MITTELMAESSIG"),
	HOCH("HOCH");

	private String stressOption;

	private Stress(final String stressOption)
	{
		this.stressOption = stressOption;
	}

	public static Stress fromStressOption(final String stressOption)
	{
		return Stream.of(Stress.values())
			.filter(s -> s.stressOption.equalsIgnoreCase(stressOption))
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
