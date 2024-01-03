package de.justinharder.trainharder.domain.model.enums;

import de.justinharder.trainharder.domain.model.exceptions.EnumException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static de.justinharder.trainharder.domain.model.enums.Uebungsart.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Uebungsart sollte")
class UebungsartTest
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
			() -> assertThat(Uebungsart.zuWert("GRUNDUEBUNG")).isEqualTo(GRUNDUEBUNG),
			() -> assertThat(Uebungsart.zuWert("GRUNDUEBUNG_VARIATION")).isEqualTo(GRUNDUEBUNG_VARIATION),
			() -> assertThat(Uebungsart.zuWert("ASSISTENZ")).isEqualTo(ASSISTENZ));
	}

	@Test
	@DisplayName("null validieren")
	void test03()
	{
		assertThrows(NullPointerException.class, () -> Uebungsart.zuWert(null));
	}
}
