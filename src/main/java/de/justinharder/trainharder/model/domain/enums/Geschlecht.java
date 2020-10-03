package de.justinharder.trainharder.model.domain.enums;

import java.util.stream.Stream;

public enum Geschlecht
{
	MAENNLICH("MAENNLICH"),
	WEIBLICH("WEIBLICH");

	private final String wert;

	Geschlecht(final String wert)
	{
		this.wert = wert;
	}

	public static Geschlecht fromString(final String wert)
	{
		return Stream.of(Geschlecht.values())
			.filter(g -> g.wert.equalsIgnoreCase(wert))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException(
				"Der Wert \"" + wert + "\" für Geschlecht existiert nicht!"));
	}
}
