package de.justinharder.old.domain.model.enums;

import de.justinharder.old.domain.model.enums.Regenerationsfaehigkeit;
import de.justinharder.old.domain.model.exceptions.EnumException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static de.justinharder.old.domain.model.enums.Regenerationsfaehigkeit.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Regenerationsfaehigkeit sollte")
class RegenerationsfaehigkeitTest
{
	@Test
	@DisplayName("EnumException werfen, wenn der Wert nicht existiert")
	void test01()
	{
		assertThrows(EnumException.class, () -> Regenerationsfaehigkeit.zuWert("UNGUELTIG"));
	}

	@Test
	@DisplayName("die Regenerationsfaehigkeit zu Wert ermitteln")
	void test02()
	{
		assertAll(
			() -> assertThat(Regenerationsfaehigkeit.zuWert("SCHLECHT")).isEqualTo(SCHLECHT),
			() -> assertThat(Regenerationsfaehigkeit.zuWert("UNTERDURCHSCHNITTLICH")).isEqualTo(UNTERDURCHSCHNITTLICH),
			() -> assertThat(Regenerationsfaehigkeit.zuWert("DURCHSCHNITTLICH")).isEqualTo(DURCHSCHNITTLICH),
			() -> assertThat(Regenerationsfaehigkeit.zuWert("GUT")).isEqualTo(GUT),
			() -> assertThat(Regenerationsfaehigkeit.zuWert("PERFEKT")).isEqualTo(PERFEKT));
	}

	@Test
	@DisplayName("null validieren")
	void test03()
	{
		assertThrows(NullPointerException.class, () -> Regenerationsfaehigkeit.zuWert(null));
	}
}
