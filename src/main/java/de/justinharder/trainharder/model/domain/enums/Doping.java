package de.justinharder.trainharder.model.domain.enums;

import java.util.stream.Stream;

public enum Doping
{
	NEIN("NEIN"),
	JA("JA");

	private String dopingOption;

	private Doping(final String dopingOption)
	{
		this.dopingOption = dopingOption;
	}

	public static Doping fromDopingOption(final String dopingOption)
	{
		return Stream.of(Doping.values())
			.filter(d -> d.dopingOption.equalsIgnoreCase(dopingOption))
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
