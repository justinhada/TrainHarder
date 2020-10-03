package de.justinharder.trainharder.model.domain.enums;

import java.util.stream.Stream;

public enum Doping
{
	NEIN("NEIN"),
	JA("JA");

	private final String wert;

	Doping(final String wert)
	{
		this.wert = wert;
	}

		public static Doping fromString(final String wert)
	{
		return Stream.of(Doping.values())
			.filter(d -> d.wert.equalsIgnoreCase(wert))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException(
					"Der Wert \"" + wert + "\" für Doping existiert nicht!"));
	}
}
