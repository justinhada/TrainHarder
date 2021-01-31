package de.justinharder.trainharder.model.domain.enums;

import de.justinharder.trainharder.model.domain.exceptions.EnumException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class KraftlevelSollte
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
			() -> assertThat(Kraftlevel.zuWert("CLASS_5")).isEqualTo(Kraftlevel.CLASS_5),
			() -> assertThat(Kraftlevel.zuWert("CLASS_4")).isEqualTo(Kraftlevel.CLASS_4),
			() -> assertThat(Kraftlevel.zuWert("CLASS_3")).isEqualTo(Kraftlevel.CLASS_3),
			() -> assertThat(Kraftlevel.zuWert("CLASS_2")).isEqualTo(Kraftlevel.CLASS_2),
			() -> assertThat(Kraftlevel.zuWert("CLASS_1")).isEqualTo(Kraftlevel.CLASS_1),
			() -> assertThat(Kraftlevel.zuWert("MASTER")).isEqualTo(Kraftlevel.MASTER),
			() -> assertThat(Kraftlevel.zuWert("ELITE")).isEqualTo(Kraftlevel.ELITE));
	}

	@Test
	@DisplayName("null validieren")
	void test03()
	{
		assertThrows(NullPointerException.class, () -> Kraftlevel.zuWert(null));
	}
}