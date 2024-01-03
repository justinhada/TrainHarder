package de.justinharder.trainharder.domain.model.enums;

import de.justinharder.trainharder.domain.model.exceptions.EnumException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static de.justinharder.trainharder.domain.model.enums.Stress.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Stress sollte")
class StressTest
{
	@Test
	@DisplayName("EnumException werfen, wenn der Wert nicht existiert")
	void test01()
	{
		assertThrows(EnumException.class, () -> Stress.zuWert("UNGUELTIG"));
	}

	@Test
	@DisplayName("den Stress zu Wert ermitteln")
	void test02()
	{
		assertAll(
			() -> assertThat(Stress.zuWert("NIEDRIG")).isEqualTo(NIEDRIG),
			() -> assertThat(Stress.zuWert("MITTELMAESSIG")).isEqualTo(MITTELMAESSIG),
			() -> assertThat(Stress.zuWert("HOCH")).isEqualTo(HOCH));
	}

	@Test
	@DisplayName("null validieren")
	void test03()
	{
		assertThrows(NullPointerException.class, () -> Stress.zuWert(null));
	}
}
