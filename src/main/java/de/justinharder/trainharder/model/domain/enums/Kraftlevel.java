package de.justinharder.trainharder.model.domain.enums;

import java.util.stream.Stream;

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
		return Stream.of(Kraftlevel.values())
			.filter(k -> k.kraftlevel.equalsIgnoreCase(kraftlevelOption))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException(
				"Die Kraftlevel-Option \"" + kraftlevelOption + "\" existiert nicht!"));
	}

	public static Kraftlevel fromName(final String name)
	{
		return Stream.of(Kraftlevel.values())
			.filter(k -> k.name().equals(name))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException("Der Name \"" + name + "\" existiert nicht!"));
	}

}
