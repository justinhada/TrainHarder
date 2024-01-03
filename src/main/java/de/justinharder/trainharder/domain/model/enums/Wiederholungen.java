package de.justinharder.trainharder.domain.model.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Wiederholungen
{
	ONE_REP_MAX("1RM"),
	THREE_REP_MAX("3RM"),
	FIVE_REP_MAX("5RM"),
	EIGHT_REP_MAX("8RM"),
	TEN_REP_MAX("10RM");

	private final String wert;

	public static Wiederholungen zuWert(@NonNull String wert)
	{
		return Enums.zuWert(Stream.of(values())
			.collect(Collectors.toMap(Function.identity(), eintrag -> eintrag.wert)), wert);
	}
}
