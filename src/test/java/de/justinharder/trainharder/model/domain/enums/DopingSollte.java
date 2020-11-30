package de.justinharder.trainharder.model.domain.enums;

import de.justinharder.trainharder.model.domain.exceptions.EnumException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DopingSollte
{
	@Test
	@DisplayName("EnumException werfen, wenn der Wert nicht existiert")
	void test01()
	{
		var erwartet = "Der Wert \"UNGUELTIG\" existiert nicht!";

		var exception = assertThrows(EnumException.class, () -> Doping.zuWert("UNGUELTIG"));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("das Doping zu Wert ermitteln")
	void test02()
	{
		assertAll(
			() -> assertThat(Doping.zuWert("JA")).isEqualTo(Doping.JA),
			() -> assertThat(Doping.zuWert("NEIN")).isEqualTo(Doping.NEIN));
	}
}
