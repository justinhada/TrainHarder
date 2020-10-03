package de.justinharder.trainharder.model.domain.enums;

import java.util.stream.Stream;

public enum Erfahrung
{
	BEGINNER("BEGINNER"), // < 4 Jahre
	FORTGESCHRITTEN("FORTGESCHRITTEN"), // 4-8 Jahre
	SEHR_FORTGESCHRITTEN("SEHR_FORTGESCHRITTEN"), // 8-12 Jahre
	EXPERTE("EXPERTE"); // > 12 Jahre

	private final String wert;

	Erfahrung(final String wert)
	{
		this.wert = wert;
	}

	public static Erfahrung fromString(final String wert)
	{
		return Stream.of(Erfahrung.values())
			.filter(e -> e.wert.equalsIgnoreCase(wert))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException(
				"Der Wert \"" + wert + "\" für Erfahrung existiert nicht!"));
	}
}
