package de.justinharder.trainharder.domain.model.enums;

import de.justinharder.trainharder.domain.model.exceptions.EnumException;
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
		assertThrows(EnumException.class, () -> Doping.zuWert("UNGUELTIG"));
	}

	@Test
	@DisplayName("das Doping zu Wert ermitteln")
	void test02()
	{
		assertAll(
			() -> assertThat(Doping.zuWert("JA")).isEqualTo(Doping.JA),
			() -> assertThat(Doping.zuWert("NEIN")).isEqualTo(Doping.NEIN));
	}

	@Test
	@DisplayName("null validieren")
	void test03()
	{
		assertThrows(NullPointerException.class, () -> Doping.zuWert(null));
	}
}
