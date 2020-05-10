package de.justinharder.trainharder.model.domain.enums;

import java.util.stream.Stream;

public enum Ernaehrung
{
	SCHLECHT("SCHLECHT"),
	DURCHSCHNITT("DURCHSCHNITT"),
	GUT("GUT");

	private String ernaehrung;

	private Ernaehrung(final String ernaehrung)
	{
		this.ernaehrung = ernaehrung;
	}

	public static Ernaehrung fromErnaehrungOption(final String ernaehrungOption)
	{
		return Stream.of(Ernaehrung.values())
			.filter(e -> e.ernaehrung.equalsIgnoreCase(ernaehrungOption))
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
