package de.justinharder.trainharder.model.domain.enums;

import java.util.stream.Stream;

public enum Schlafqualitaet
{
	SCHLECHT("SCHLECHT"),
	DURCHSCHNITT("DURCHSCHNITT"),
	GUT("GUT");

	private String schlafqualitaetOption;

	private Schlafqualitaet(final String schlafqualitaetOption)
	{
		this.schlafqualitaetOption = schlafqualitaetOption;
	}

	public static Schlafqualitaet fromSchlafqualitaetOption(final String schlafqualitaetOption)
	{
		return Stream.of(Schlafqualitaet.values())
			.filter(s -> s.schlafqualitaetOption.equalsIgnoreCase(schlafqualitaetOption))
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
