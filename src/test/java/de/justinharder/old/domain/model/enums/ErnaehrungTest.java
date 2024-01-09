package de.justinharder.old.domain.model.enums;

import de.justinharder.old.domain.model.enums.Ernaehrung;
import de.justinharder.old.domain.model.exceptions.EnumException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static de.justinharder.old.domain.model.enums.Ernaehrung.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Ernaehrung sollte")
class ErnaehrungTest
{
	@Test
	@DisplayName("EnumException werfen, wenn der Wert nicht existiert")
	void test01()
	{
		assertThrows(EnumException.class, () -> Ernaehrung.zuWert("UNGUELTIG"));
	}

	@Test
	@DisplayName("die Ernaehrung zu Wert ermitteln")
	void test02()
	{
		assertAll(
			() -> assertThat(Ernaehrung.zuWert("SCHLECHT")).isEqualTo(SCHLECHT),
			() -> assertThat(Ernaehrung.zuWert("DURCHSCHNITT")).isEqualTo(DURCHSCHNITT),
			() -> assertThat(Ernaehrung.zuWert("GUT")).isEqualTo(GUT));
	}

	@Test
	@DisplayName("null validieren")
	void test03()
	{
		assertThrows(NullPointerException.class, () -> Ernaehrung.zuWert(null));
	}
}
