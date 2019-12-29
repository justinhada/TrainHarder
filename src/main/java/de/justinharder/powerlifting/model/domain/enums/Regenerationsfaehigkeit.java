package de.justinharder.powerlifting.model.domain.enums;

import java.util.Arrays;

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
		for (final Regenerationsfaehigkeit r : Regenerationsfaehigkeit.values())
		{
			if (r.regenerationsfaehigkeit.equalsIgnoreCase(regenerationsfaehigkeitOption))
			{
				return r;
			}
		}

		throw new IllegalArgumentException(
			"Die Regenerationsfaehigkeit-Option \"" + regenerationsfaehigkeitOption + "\" existiert nicht!");
	}

	public static Regenerationsfaehigkeit fromName(final String name)
	{
		return Arrays.asList(Regenerationsfaehigkeit.values())
			.stream()
			.filter(r -> r.name().equals(name))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException("Der Name \"" + name + "\" existiert nicht!"));
	}
}
