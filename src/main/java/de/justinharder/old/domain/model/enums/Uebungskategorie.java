package de.justinharder.old.domain.model.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Uebungskategorie
{
	ASSISTENZ_BRUST("ASSISTENZ_BRUST"),
	ASSISTENZ_CORE("ASSISTENZ_CORE"),
	ASSISTENZ_GLUTEUS("ASSISTENZ_GLUTEUS"),
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

	public static Uebungskategorie zuWert(@NonNull String wert)
	{
		return Enums.zuWert(Stream.of(values())
			.collect(Collectors.toMap(Function.identity(), eintrag -> eintrag.wert)), wert);
	}
}
