package de.justinharder.old.domain.model.enums;

import de.justinharder.old.domain.model.exceptions.EnumException;
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
