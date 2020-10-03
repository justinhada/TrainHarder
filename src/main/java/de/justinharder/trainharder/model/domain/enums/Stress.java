package de.justinharder.trainharder.model.domain.enums;

import java.util.stream.Stream;

public enum Stress
{
	NIEDRIG("NIEDRIG"),
	MITTELMAESSIG("MITTELMAESSIG"),
	HOCH("HOCH");

	private final String wert;

	Stress(final String wert)
	{
		this.wert = wert;
	}

	public static Stress fromString(final String wert)
	{
		return Stream.of(Stress.values())
			.filter(s -> s.wert.equalsIgnoreCase(wert))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException(
				"Der Wert \"" + wert + "\" für Stress existiert nicht!"));
	}
}
