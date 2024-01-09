package de.justinharder.old.domain.model.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;

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

	public static Uebungsart zuWert(@NonNull String wert)
	{
		return Enums.zuWert(Stream.of(values())
			.collect(Collectors.toMap(Function.identity(), eintrag -> eintrag.wert)), wert);
	}
}
