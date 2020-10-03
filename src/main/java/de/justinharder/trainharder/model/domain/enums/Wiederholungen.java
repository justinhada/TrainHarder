package de.justinharder.trainharder.model.domain.enums;

import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum Wiederholungen
{
	ONE_REP_MAX("1RM"),
	THREE_REP_MAX("3RM"),
	FIVE_REP_MAX("5RM"),
	EIGHT_REP_MAX("8RM"),
	TEN_REP_MAX("10RM");

	private final String wert;

	Wiederholungen(final String wert)
	{
		this.wert = wert;
	}

	public static Wiederholungen fromString(final String wert)
	{
		return Stream.of(Wiederholungen.values())
			.filter(w -> w.wert.equalsIgnoreCase(wert))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException(
				"Der Wert \"" + wert + "\" für Wiederholungen existiert nicht!"));
	}
}
