package de.justinharder.trainharder.model.domain.enums;

import java.util.stream.Stream;

public enum Regenerationsfaehigkeit
{
	SCHLECHT("SCHLECHT"),
	UNTERDURCHSCHNITTLICH("UNTERDURCHSCHNITTLICH"),
	DURCHSCHNITTLICH("DURCHSCHNITTLICH"),
	GUT("GUT"),
	PERFEKT("PERFEKT");

	private String regenerationsfaehigkeit;

	private Regenerationsfaehigkeit(final String regenerationsfaehigkeit)
	{
		this.regenerationsfaehigkeit = regenerationsfaehigkeit;
	}

	public static Regenerationsfaehigkeit fromRegenerationsfaehigkeitOption(final String regenerationsfaehigkeitOption)
	{
		return Stream.of(Regenerationsfaehigkeit.values())
			.filter(r -> r.regenerationsfaehigkeit.equalsIgnoreCase(regenerationsfaehigkeitOption))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException(
				"Die Regenerationsfaehigkeit-Option \"" + regenerationsfaehigkeitOption + "\" existiert nicht!"));
	}

	public static Regenerationsfaehigkeit fromName(final String name)
	{
		return Stream.of(Regenerationsfaehigkeit.values())
			.filter(r -> r.name().equals(name))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException("Der Name \"" + name + "\" existiert nicht!"));
	}
}
