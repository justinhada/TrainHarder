package de.justinharder.trainharder.model.domain.enums;

import de.justinharder.trainharder.model.domain.exceptions.EnumException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GeschlechtSollte
{
	@Test
	@DisplayName("EnumException werfen, wenn der Wert nicht existiert")
	void test01()
	{
		var erwartet = "Der Wert \"UNGUELTIG\" existiert nicht!";

		var exception = assertThrows(EnumException.class, () -> Geschlecht.zuWert("UNGUELTIG"));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("das Geschlecht zu Wert ermitteln")
	void test02()
	{
		assertAll(
			() -> assertThat(Geschlecht.zuWert("MAENNLICH")).isEqualTo(Geschlecht.MAENNLICH),
			() -> assertThat(Geschlecht.zuWert("WEIBLICH")).isEqualTo(Geschlecht.WEIBLICH));
	}

	@Test
	@DisplayName("null validieren")
	void test03()
	{
		assertThrows(NullPointerException.class, () -> Geschlecht.zuWert(null));
	}
}
