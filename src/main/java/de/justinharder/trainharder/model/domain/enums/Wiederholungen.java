package de.justinharder.trainharder.model.domain.enums;

import java.util.stream.Stream;

public enum Wiederholungen
{
	ONE_REP_MAX("1RM"),
	THREE_REP_MAX("3RM"),
	FIVE_REP_MAX("5RM"),
	EIGHT_REP_MAX("8RM"),
	TEN_REP_MAX("10RM");

	private String wiederholungenOption;

	private Wiederholungen(final String wiederholungenOption)
	{
		this.wiederholungenOption = wiederholungenOption;
	}

	public static Wiederholungen fromWiederholungenOption(final String wiederholungenOption)
	{
		return Stream.of(Wiederholungen.values())
			.filter(w -> w.wiederholungenOption.equalsIgnoreCase(wiederholungenOption))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException(
				"Die Wiederholungen-Option \"" + wiederholungenOption + "\" existiert nicht!"));
	}

	public static Wiederholungen fromName(final String name)
	{
		return Stream.of(Wiederholungen.values())
			.filter(w -> w.name().equals(name))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException("Der Name \"" + name + "\" existiert nicht!"));
	}
}
