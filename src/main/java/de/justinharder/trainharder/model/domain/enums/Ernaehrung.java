package de.justinharder.trainharder.model.domain.enums;

import java.util.stream.Stream;

public enum Ernaehrung
{
	SCHLECHT("SCHLECHT"),
	DURCHSCHNITT("DURCHSCHNITT"),
	GUT("GUT");

	private final String wert;

	private Ernaehrung(final String wert)
	{
		this.wert = wert;
	}

	public static Ernaehrung fromString(final String wert)
	{
		return Stream.of(Ernaehrung.values())
			.filter(e -> e.wert.equalsIgnoreCase(wert))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException(
				"Der Wert \"" + wert + "\" für Ernährung existiert nicht!"));
	}
}
