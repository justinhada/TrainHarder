package de.justinharder.trainharder.model.domain.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Stress
{
	NIEDRIG("NIEDRIG"),
	MITTELMAESSIG("MITTELMAESSIG"),
	HOCH("HOCH");

	private final String wert;

	public static Stress zuWert(@NonNull String wert)
	{
		return Enums.zuWert(Stream.of(values())
			.collect(Collectors.toMap(Function.identity(), eintrag -> eintrag.wert)), wert);
	}
}