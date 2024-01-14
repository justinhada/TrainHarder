package de.justinharder.trainharder.domain.model.attribute;

import de.justinharder.trainharder.domain.model.exceptions.GeschlechtException;
import lombok.NonNull;

import java.util.stream.Stream;

public enum Geschlecht
{
	MAENNLICH,
	WEIBLICH,
	DIVERS;

	public static Geschlecht aus(@NonNull String wert)
	{
		return Stream.of(values())
			.filter(geschlecht -> geschlecht.name().equals(wert))
			.findAny()
			.orElseThrow(() -> new GeschlechtException("Das Geschlecht %s existiert nicht!".formatted(wert)));
	}
}
