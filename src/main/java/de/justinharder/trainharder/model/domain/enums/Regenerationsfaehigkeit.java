package de.justinharder.trainharder.model.domain.enums;

import java.util.stream.Stream;

public enum Regenerationsfaehigkeit
{
	SCHLECHT("SCHLECHT"),
	UNTERDURCHSCHNITTLICH("UNTERDURCHSCHNITTLICH"),
	DURCHSCHNITTLICH("DURCHSCHNITTLICH"),
	GUT("GUT"),
	PERFEKT("PERFEKT");

	private final String wert;

	private Regenerationsfaehigkeit(final String wert)
	{
		this.wert = wert;
	}

	public static Regenerationsfaehigkeit fromString(final String wert)
	{
		return Stream.of(Regenerationsfaehigkeit.values())
			.filter(r -> r.wert.equalsIgnoreCase(wert))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException(
				"Der Wert \"" + wert + "\" für Regenerationsfähigkeit existiert nicht!"));
	}
}
