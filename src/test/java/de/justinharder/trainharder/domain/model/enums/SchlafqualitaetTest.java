package de.justinharder.trainharder.domain.model.enums;

import de.justinharder.trainharder.domain.model.exceptions.EnumException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static de.justinharder.trainharder.domain.model.enums.Schlafqualitaet.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Schlafqualitaet sollte")
class SchlafqualitaetTest
{
	@Test
	@DisplayName("EnumException werfen, wenn der Wert nicht existiert")
	void test01()
	{
		assertThrows(EnumException.class, () -> Schlafqualitaet.zuWert("UNGUELTIG"));
	}

	@Test
	@DisplayName("die Schlafqualitaet zu Wert ermitteln")
	void test02()
	{
		assertAll(
			() -> assertThat(Schlafqualitaet.zuWert("SCHLECHT")).isEqualTo(SCHLECHT),
			() -> assertThat(Schlafqualitaet.zuWert("DURCHSCHNITT")).isEqualTo(DURCHSCHNITT),
			() -> assertThat(Schlafqualitaet.zuWert("GUT")).isEqualTo(GUT));
	}

	@Test
	@DisplayName("null validieren")
	void test03()
	{
		assertThrows(NullPointerException.class, () -> Schlafqualitaet.zuWert(null));
	}
}
