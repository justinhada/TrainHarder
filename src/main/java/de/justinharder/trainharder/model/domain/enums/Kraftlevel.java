package de.justinharder.trainharder.model.domain.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Kraftlevel
{
	CLASS_5("CLASS_5"),
	CLASS_4("CLASS_4"),
	CLASS_3("CLASS_3"),
	CLASS_2("CLASS_2"),
	CLASS_1("CLASS_1"),
	MASTER("MASTER"),
	ELITE("ELITE");

	private final String wert;

	public static Kraftlevel zuWert(@NonNull String wert)
	{
		return Enums.zuWert(Stream.of(values())
			.collect(Collectors.toMap(Function.identity(), eintrag -> eintrag.wert)), wert);
	}

}
