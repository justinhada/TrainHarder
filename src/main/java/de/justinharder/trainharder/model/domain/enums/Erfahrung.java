package de.justinharder.trainharder.model.domain.enums;

import java.util.stream.Stream;

public enum Erfahrung
{
	BEGINNER("BEGINNER"), // < 4 Jahre
	FORTGESCHRITTEN("FORTGESCHRITTEN"), // 4-8 Jahre
	SEHR_FORTGESCHRITTEN("SEHR_FORTGESCHRITTEN"), // 8-12 Jahre
	EXPERTE("EXPERTE"); // > 12 Jahre

	private String erfahrung;

	private Erfahrung(final String erfahrung)
	{
		this.erfahrung = erfahrung;
	}

	public static Erfahrung fromErfahrungOption(final String erfahrungOption)
	{
		return Stream.of(Erfahrung.values())
			.filter(e -> e.erfahrung.equalsIgnoreCase(erfahrungOption))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException(
				"Die Erfahrung-Option \"" + erfahrungOption + "\" existiert nicht!"));
	}

	public static Erfahrung fromName(final String name)
	{
		return Stream.of(Erfahrung.values())
			.filter(e -> e.name().equals(name))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException("Der Name \"" + name + "\" existiert nicht!"));
	}
}