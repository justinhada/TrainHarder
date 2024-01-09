package de.justinharder.old.domain.model.enums;

import de.justinharder.old.domain.model.enums.Wiederholungen;
import de.justinharder.old.domain.model.exceptions.EnumException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static de.justinharder.old.domain.model.enums.Wiederholungen.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Wiederholungen sollte")
class WiederholungenTest
{
	@Test
	@DisplayName("EnumException werfen, wenn der Wert nicht existiert")
	void test01()
	{
		assertThrows(EnumException.class, () -> Wiederholungen.zuWert("UNGUELTIG"));
	}

	@Test
	@DisplayName("die Wiederholungen zu Wert ermitteln")
	void test02()
	{
		assertAll(
			() -> assertThat(Wiederholungen.zuWert("1RM")).isEqualTo(ONE_REP_MAX),
			() -> assertThat(Wiederholungen.zuWert("3RM")).isEqualTo(THREE_REP_MAX),
			() -> assertThat(Wiederholungen.zuWert("5RM")).isEqualTo(FIVE_REP_MAX),
			() -> assertThat(Wiederholungen.zuWert("8RM")).isEqualTo(EIGHT_REP_MAX),
			() -> assertThat(Wiederholungen.zuWert("10RM")).isEqualTo(TEN_REP_MAX));
	}

	@Test
	@DisplayName("null validieren")
	void test03()
	{
		assertThrows(NullPointerException.class, () -> Wiederholungen.zuWert(null));
	}
}
