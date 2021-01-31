package de.justinharder.trainharder.model.domain.enums;

import de.justinharder.trainharder.model.domain.exceptions.EnumException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UebungsartSollte
{
	@Test
	@DisplayName("EnumException werfen, wenn der Wert nicht existiert")
	void test01()
	{
		assertThrows(EnumException.class, () -> Uebungsart.zuWert("UNGUELTIG"));
	}

	@Test
	@DisplayName("die Uebungsart zu Wert ermitteln")
	void test02()
	{
		assertAll(
			() -> assertThat(Uebungsart.zuWert("GRUNDUEBUNG")).isEqualTo(Uebungsart.GRUNDUEBUNG),
			() -> assertThat(Uebungsart.zuWert("GRUNDUEBUNG_VARIATION")).isEqualTo(Uebungsart.GRUNDUEBUNG_VARIATION),
			() -> assertThat(Uebungsart.zuWert("ASSISTENZ")).isEqualTo(Uebungsart.ASSISTENZ));
	}

	@Test
	@DisplayName("null validieren")
	void test03()
	{
		assertThrows(NullPointerException.class, () -> Uebungsart.zuWert(null));
	}
}