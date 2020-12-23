package de.justinharder.trainharder.model.domain.enums;

import de.justinharder.trainharder.model.domain.exceptions.EnumException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StressSollte
{
	@Test
	@DisplayName("EnumException werfen, wenn der Wert nicht existiert")
	void test01()
	{
		var erwartet = "Der Wert \"UNGUELTIG\" existiert nicht!";

		var exception = assertThrows(EnumException.class, () -> Stress.zuWert("UNGUELTIG"));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("den Stress zu Wert ermitteln")
	void test02()
	{
		assertAll(
			() -> assertThat(Stress.zuWert("NIEDRIG")).isEqualTo(Stress.NIEDRIG),
			() -> assertThat(Stress.zuWert("MITTELMAESSIG")).isEqualTo(Stress.MITTELMAESSIG),
			() -> assertThat(Stress.zuWert("HOCH")).isEqualTo(Stress.HOCH));
	}

	@Test
	@DisplayName("null validieren")
	void test03()
	{
		assertThrows(NullPointerException.class, () -> Stress.zuWert(null));
	}
}
