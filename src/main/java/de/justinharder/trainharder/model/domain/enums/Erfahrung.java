package de.justinharder.trainharder.model.domain.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Erfahrung
{
	BEGINNER("BEGINNER"), // < 4 Jahre
	FORTGESCHRITTEN("FORTGESCHRITTEN"), // 4-8 Jahre
	SEHR_FORTGESCHRITTEN("SEHR_FORTGESCHRITTEN"), // 8-12 Jahre
	EXPERTE("EXPERTE"); // > 12 Jahre

	private final String wert;

	public static Erfahrung zuWert(@NonNull String wert)
	{
		return Enums.zuWert(Stream.of(values())
			.collect(Collectors.toMap(Function.identity(), eintrag -> eintrag.wert)), wert);
	}
}
