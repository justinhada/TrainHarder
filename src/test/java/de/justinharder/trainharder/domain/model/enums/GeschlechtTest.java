package de.justinharder.trainharder.domain.model.enums;

import de.justinharder.trainharder.domain.model.exceptions.EnumException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static de.justinharder.trainharder.domain.model.enums.Geschlecht.MAENNLICH;
import static de.justinharder.trainharder.domain.model.enums.Geschlecht.WEIBLICH;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Geschlecht sollte")
class GeschlechtTest
{
	@Test
	@DisplayName("EnumException werfen, wenn der Wert nicht existiert")
	void test01()
	{
		assertThrows(EnumException.class, () -> Geschlecht.zuWert("UNGUELTIG"));
	}

	@Test
	@DisplayName("das Geschlecht zu Wert ermitteln")
	void test02()
	{
		assertAll(
			() -> assertThat(Geschlecht.zuWert("MAENNLICH")).isEqualTo(MAENNLICH),
			() -> assertThat(Geschlecht.zuWert("WEIBLICH")).isEqualTo(WEIBLICH));
	}

	@Test
	@DisplayName("null validieren")
	void test03()
	{
		assertThrows(NullPointerException.class, () -> Geschlecht.zuWert(null));
	}
}
