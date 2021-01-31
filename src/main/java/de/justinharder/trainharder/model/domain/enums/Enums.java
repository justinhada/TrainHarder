package de.justinharder.trainharder.model.domain.enums;

import de.justinharder.trainharder.model.domain.exceptions.EnumException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Enums
{
	public static <T extends Enum<T>> T zuWert(Map<T, String> werte, String wert)
	{
		return werte.entrySet().stream()
			.filter(eintrag -> eintrag.getValue().equalsIgnoreCase(wert))
			.findAny()
			.orElseThrow(() -> new EnumException("Der Wert \"" + wert + "\" existiert nicht!"))
			.getKey();
	}
}