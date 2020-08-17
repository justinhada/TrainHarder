package de.justinharder.trainharder.model.domain.enums;

import java.util.stream.Stream;

public enum Geschlecht
{
	MAENNLICH("MAENNLICH"),
	WEIBLICH("WEIBLICH");

	private String geschlechtOption;

	private Geschlecht(final String geschlechtOption)
	{
		this.geschlechtOption = geschlechtOption;
	}

	public static Geschlecht fromGeschlechtOption(final String geschlechtOption)
	{
		return Stream.of(Geschlecht.values())
			.filter(g -> g.geschlechtOption.equalsIgnoreCase(geschlechtOption))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException(
				"Die Geschlecht-Option \"" + geschlechtOption + "\" existiert nicht!"));
	}

	public static Geschlecht fromName(final String name)
	{
		return Stream.of(Geschlecht.values())
			.filter(g -> g.name().equals(name))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException("Der Name \"" + name + "\" existiert nicht!"));
	}
}
