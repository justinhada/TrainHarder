package de.justinharder.trainharder.model.domain.enums;

import java.util.stream.Stream;

public enum Kraftlevel
{
	CLASS_5("CLASS_5"),
	CLASS_4("CLASS_4"),
	CLASS_3("CLASS_3"),
	CLASS_2("CLASS_2"),
	CLASS_1("CLASS_1"),
	MASTER("MASTER"),
	ELITE("ELITE");

	private final String wert;

	private Kraftlevel(final String wert)
	{
		this.wert = wert;
	}

	public static Kraftlevel fromString(final String wert)
	{
		return Stream.of(Kraftlevel.values())
			.filter(k -> k.wert.equalsIgnoreCase(wert))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException(
				"Der Wert \"" + wert + "\" für Kraftlevel existiert nicht!"));
	}


}
