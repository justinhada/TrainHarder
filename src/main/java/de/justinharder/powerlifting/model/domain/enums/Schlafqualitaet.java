package de.justinharder.powerlifting.model.domain.enums;

import java.util.stream.Stream;

public enum Schlafqualitaet
{
	SCHLECHT("SCHLECHT"),
	DURCHSCHNITT("DURCHSCHNITT"),
	GUT("GUT");

	private String schlafqualitaet;

	private Schlafqualitaet(final String schlafqualitaet)
	{
		this.schlafqualitaet = schlafqualitaet;
	}

	public static Schlafqualitaet fromSchlafqualitaetOption(final String schlafqualitaetOption)
	{
		return Stream.of(Schlafqualitaet.values())
			.filter(s -> s.schlafqualitaet.equalsIgnoreCase(schlafqualitaetOption))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException(
				"Die Schlafqualitaet-Option \"" + schlafqualitaetOption + "\" existiert nicht!"));
	}

	public static Schlafqualitaet fromName(final String name)
	{
		return Stream.of(Schlafqualitaet.values())
			.filter(s -> s.name().equals(name))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException("Der Name \"" + name + "\" existiert nicht!"));
	}
}
