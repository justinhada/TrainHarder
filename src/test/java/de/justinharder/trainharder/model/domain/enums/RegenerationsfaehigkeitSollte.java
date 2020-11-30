package de.justinharder.trainharder.model.domain.enums;

import de.justinharder.trainharder.model.domain.exceptions.EnumException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RegenerationsfaehigkeitSollte
{
	@Test
	@DisplayName("EnumException werfen, wenn der Wert nicht existiert")
	void test01()
	{
		var erwartet = "Der Wert \"UNGUELTIG\" existiert nicht!";

		var exception = assertThrows(EnumException.class, () -> Regenerationsfaehigkeit.zuWert("UNGUELTIG"));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("die Regenerationsfaehigkeit zu Wert ermitteln")
	void test02()
	{
		assertAll(
			() -> assertThat(Regenerationsfaehigkeit.zuWert("SCHLECHT")).isEqualTo(Regenerationsfaehigkeit.SCHLECHT),
			() -> assertThat(Regenerationsfaehigkeit.zuWert("UNTERDURCHSCHNITTLICH"))
				.isEqualTo(Regenerationsfaehigkeit.UNTERDURCHSCHNITTLICH),
			() -> assertThat(Regenerationsfaehigkeit.zuWert("DURCHSCHNITTLICH"))
				.isEqualTo(Regenerationsfaehigkeit.DURCHSCHNITTLICH),
			() -> assertThat(Regenerationsfaehigkeit.zuWert("GUT")).isEqualTo(Regenerationsfaehigkeit.GUT),
			() -> assertThat(Regenerationsfaehigkeit.zuWert("PERFEKT")).isEqualTo(Regenerationsfaehigkeit.PERFEKT));
	}
}
