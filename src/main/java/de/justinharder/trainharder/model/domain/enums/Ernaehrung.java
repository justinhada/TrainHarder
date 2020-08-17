package de.justinharder.trainharder.model.domain.enums;

import java.util.stream.Stream;

public enum Ernaehrung
{
	SCHLECHT("SCHLECHT"),
	DURCHSCHNITT("DURCHSCHNITT"),
	GUT("GUT");

	private String ernaehrungOption;

	private Ernaehrung(final String ernaehrungOption)
	{
		this.ernaehrungOption = ernaehrungOption;
	}

	public static Ernaehrung fromErnaehrungOption(final String ernaehrungOption)
	{
		return Stream.of(Ernaehrung.values())
			.filter(e -> e.ernaehrungOption.equalsIgnoreCase(ernaehrungOption))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException(
				"Die Ernaehrung-Option \"" + ernaehrungOption + "\" existiert nicht!"));
	}

	public static Ernaehrung fromName(final String name)
	{
		return Stream.of(Ernaehrung.values())
			.filter(e -> e.name().equals(name))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException("Der Name \"" + name + "\" existiert nicht!"));
	}
}
