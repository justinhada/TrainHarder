package de.justinharder.trainharder.model.domain.enums;

import java.util.stream.Stream;

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
		return Stream.of(Geschlecht.values())
			.filter(g -> g.geschlecht.equalsIgnoreCase(geschlechtOption))
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
