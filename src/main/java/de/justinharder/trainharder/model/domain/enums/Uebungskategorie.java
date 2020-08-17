package de.justinharder.trainharder.model.domain.enums;

import java.util.stream.Stream;

public enum Uebungskategorie
{
	ASSISTENZ_BRUST("ASSISTENZ_BRUST"),
	ASSISTENZ_CORE("ASSITENZ_CORE"),
	ASSISTENZ_GLUTEUS("ASSITENZ_GLUTEUS"),
	ASSISTENZ_RUECKEN("ASSISTENZ_RUECKEN"),
	ASSISTENZ_SCHULTER("ASSISTENZ_SCHULTER"),
	ASSISTENZ_TRIZEPS("ASSISTENZ_TRIZEPS"),
	ASSISTENZ_UNTERARM("ASSISTENZ_UNTERARM"),
	BANKDRUECKEN_VARIATION("BANKDRUECKEN_VARIATION"),
	KNIEBEUGE_VARIATION("KNIEBEUGE_VARIATION"),
	KREUZHEBEN_VARIATION("KREUZHEBEN_VARIATION"),
	UEBERKOPFDRUECKEN("UEBERKOPFDRUECKEN"),
	WETTKAMPF_BANKDRUECKEN("WETTKAMPF_BANKDRUECKEN"),
	WETTKAMPF_KNIEBEUGE("WETTKAMPF_KNIEBEUGE"),
	WETTKAMPF_KREUZHEBEN("WETTKAMPF_KREUZHEBEN");

	private String uebungskategorieOption;

	private Uebungskategorie(final String uebungskategorieOption)
	{
		this.uebungskategorieOption = uebungskategorieOption;
	}

	public static Uebungskategorie fromUebungskategorieOption(final String uebungskategorieOption)
	{
		return Stream.of(Uebungskategorie.values())
			.filter(u -> u.uebungskategorieOption.equalsIgnoreCase(uebungskategorieOption))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException(
				"Die Uebungskategorie-Option \"" + uebungskategorieOption + "\" existiert nicht!"));
	}

	public static Uebungskategorie fromName(final String name)
	{
		return Stream.of(Uebungskategorie.values())
			.filter(u -> u.name().equals(name))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException("Der Name \"" + name + "\" existiert nicht!"));
	}
}
