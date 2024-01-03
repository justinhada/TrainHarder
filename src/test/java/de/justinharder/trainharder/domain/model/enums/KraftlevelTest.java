package de.justinharder.trainharder.domain.model.enums;

import de.justinharder.trainharder.domain.model.exceptions.EnumException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static de.justinharder.trainharder.domain.model.enums.Kraftlevel.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Kraftlevel sollte")
class KraftlevelTest
{
	@Test
	@DisplayName("EnumException werfen, wenn der Wert nicht existiert")
	void test01()
	{
		assertThrows(EnumException.class, () -> Kraftlevel.zuWert("UNGUELTIG"));
	}

	@Test
	@DisplayName("das Kraftlevel zu Wert ermitteln")
	void test02()
	{
		assertAll(
			() -> assertThat(Kraftlevel.zuWert("CLASS_5")).isEqualTo(CLASS_5),
			() -> assertThat(Kraftlevel.zuWert("CLASS_4")).isEqualTo(CLASS_4),
			() -> assertThat(Kraftlevel.zuWert("CLASS_3")).isEqualTo(CLASS_3),
			() -> assertThat(Kraftlevel.zuWert("CLASS_2")).isEqualTo(CLASS_2),
			() -> assertThat(Kraftlevel.zuWert("CLASS_1")).isEqualTo(CLASS_1),
			() -> assertThat(Kraftlevel.zuWert("MASTER")).isEqualTo(MASTER),
			() -> assertThat(Kraftlevel.zuWert("ELITE")).isEqualTo(ELITE));
	}

	@Test
	@DisplayName("null validieren")
	void test03()
	{
		assertThrows(NullPointerException.class, () -> Kraftlevel.zuWert(null));
	}
}
