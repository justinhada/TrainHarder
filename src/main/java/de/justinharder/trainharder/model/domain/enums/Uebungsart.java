package de.justinharder.trainharder.model.domain.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Uebungsart
{
	GRUNDUEBUNG("GRUNDUEBUNG"),
	GRUNDUEBUNG_VARIATION("GRUNDUEBUNG_VARIATION"),
	ASSISTENZ("ASSISTENZ");

	private final String wert;

	public static Uebungsart zuWert(String wert)
	{
		return Enums.zuWert(Stream.of(values())
			.collect(Collectors.toMap(Function.identity(), eintrag -> eintrag.wert)), wert);
	}
}
