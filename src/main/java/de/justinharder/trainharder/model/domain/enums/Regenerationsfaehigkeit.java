package de.justinharder.trainharder.model.domain.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Regenerationsfaehigkeit
{
	SCHLECHT("SCHLECHT"),
	UNTERDURCHSCHNITTLICH("UNTERDURCHSCHNITTLICH"),
	DURCHSCHNITTLICH("DURCHSCHNITTLICH"),
	GUT("GUT"),
	PERFEKT("PERFEKT");

	private final String wert;

	public static Regenerationsfaehigkeit zuWert(String wert)
	{
		return Enums.zuWert(Stream.of(values())
			.collect(Collectors.toMap(Function.identity(), eintrag -> eintrag.wert)), wert);
	}
}
