package de.justinharder.trainharder.model.domain.enums;

import de.justinharder.trainharder.model.domain.exceptions.EnumException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ErnaehrungSollte
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
			() -> assertThat(Ernaehrung.zuWert("SCHLECHT")).isEqualTo(Ernaehrung.SCHLECHT),
			() -> assertThat(Ernaehrung.zuWert("DURCHSCHNITT")).isEqualTo(Ernaehrung.DURCHSCHNITT),
			() -> assertThat(Ernaehrung.zuWert("GUT")).isEqualTo(Ernaehrung.GUT));
	}

	@Test
	@DisplayName("null validieren")
	void test03()
	{
		assertThrows(NullPointerException.class, () -> Ernaehrung.zuWert(null));
	}
}