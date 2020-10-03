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

	private final String wert;

	Uebungskategorie(final String wert)
	{
		this.wert = wert;
	}

	public static Uebungskategorie fromString(final String wert)
	{
		return Stream.of(Uebungskategorie.values())
			.filter(u -> u.wert.equalsIgnoreCase(wert))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException(
				"Der Wert \"" + wert + "\" für Übungskategorie existiert nicht!"));
	}
}
