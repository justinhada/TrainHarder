package de.justinharder.trainharder.model.domain.enums;

import java.util.stream.Stream;

public enum Uebungsart
{
	GRUNDUEBUNG("GRUNDUEBUNG"),
	GRUNDUEBUNG_VARIATION("GRUNDUEBUNG_VARIATION"),
	ASSISTENZ("ASSISTENZ");

	private final String wert;

	private Uebungsart(final String wert)
	{
		this.wert = wert;
	}

	public static Uebungsart fromString(final String wert)
	{
		return Stream.of(Uebungsart.values())
			.filter(u -> u.wert.equalsIgnoreCase(wert))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException(
				"Der Wert \"" + wert + "\" für Übungsart existiert nicht!"));
	}
}
