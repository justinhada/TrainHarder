package de.justinharder.trainharder.model.domain.enums;

import de.justinharder.trainharder.model.domain.exceptions.EnumException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WiederholungenSollte
{
	@Test
	@DisplayName("EnumException werfen, wenn der Wert nicht existiert")
	void test01()
	{
		var erwartet = "Der Wert \"UNGUELTIG\" existiert nicht!";

		var exception = assertThrows(EnumException.class, () -> Wiederholungen.zuWert("UNGUELTIG"));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("die Wiederholungen zu Wert ermitteln")
	void test02()
	{
		assertAll(
			() -> assertThat(Wiederholungen.zuWert("1RM")).isEqualTo(Wiederholungen.ONE_REP_MAX),
			() -> assertThat(Wiederholungen.zuWert("3RM")).isEqualTo(Wiederholungen.THREE_REP_MAX),
			() -> assertThat(Wiederholungen.zuWert("5RM")).isEqualTo(Wiederholungen.FIVE_REP_MAX),
			() -> assertThat(Wiederholungen.zuWert("8RM")).isEqualTo(Wiederholungen.EIGHT_REP_MAX),
			() -> assertThat(Wiederholungen.zuWert("10RM")).isEqualTo(Wiederholungen.TEN_REP_MAX));
	}
}
