package de.justinharder.powerlifting.model.domain.enums;

import java.util.stream.Stream;

public enum Uebungsart
{
	GRUNDUEBUNG("GRUNDUEBUNG"),
	GRUNDUEBUNG_VARIATION("GRUNDUEBUNG_VARIATION"),
	ASSISTENZ("ASSISTENZ");

	private String uebungsart;

	private Uebungsart(final String uebungsart)
	{
		this.uebungsart = uebungsart;
	}

	public static Uebungsart fromUebungsartOption(final String uebungsartOption)
	{
		return Stream.of(Uebungsart.values())
			.filter(u -> u.uebungsart.equalsIgnoreCase(uebungsartOption))
			.findAny()
			.orElseThrow(
				() -> new IllegalArgumentException(
					"Die Uebungsart-Option \"" + uebungsartOption + "\" existiert nicht!"));
	}

	public static Uebungsart fromName(final String name)
	{
		return Stream.of(Uebungsart.values())
			.filter(u -> u.name().equals(name))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException("Der Name \"" + name + "\" existiert nicht!"));
	}
}
