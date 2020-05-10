package de.justinharder.trainharder.model.domain.enums;

import java.util.stream.Stream;

public enum Doping
{
	NEIN("NEIN"),
	JA("JA");

	private String doping;

	private Doping(final String doping)
	{
		this.doping = doping;
	}

	public static Doping fromDopingOption(final String dopingOption)
	{
		return Stream.of(Doping.values())
			.filter(d -> d.doping.equalsIgnoreCase(dopingOption))
			.findAny()
			.orElseThrow(
				() -> new IllegalArgumentException("Die Doping-Option \"" + dopingOption + "\" existiert nicht!"));
	}

	public static Doping fromName(final String name)
	{
		return Stream.of(Doping.values())
			.filter(d -> d.name().equals(name))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException("Der Name \"" + name + "\" existiert nicht!"));
	}
}
