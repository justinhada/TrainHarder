package de.justinharder.trainharder.model.domain.enums;

import java.util.stream.Stream;

public enum Schlafqualitaet
{
	SCHLECHT("SCHLECHT"),
	DURCHSCHNITT("DURCHSCHNITT"),
	GUT("GUT");

	private final String wert;

	private Schlafqualitaet(final String wert)
	{
		this.wert = wert;
	}

	public static Schlafqualitaet fromString(final String wert)
	{
		return Stream.of(Schlafqualitaet.values())
			.filter(s -> s.wert.equalsIgnoreCase(wert))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException(
				"Der Wert \"" + wert + "\" für Schlafqualität existiert nicht!"));
	}
}
