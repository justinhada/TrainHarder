package de.justinharder.trainharder.domain.model.enums;

import de.justinharder.trainharder.domain.model.exceptions.EnumException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ErfahrungSollte
{
	@Test
	@DisplayName("EnumException werfen, wenn der Wert nicht existiert")
	void test01()
	{
		assertThrows(EnumException.class, () -> Erfahrung.zuWert("UNGUELTIG"));
	}

	@Test
	@DisplayName("die Erfahrung zu Wert ermitteln")
	void test02()
	{
		assertAll(
			() -> assertThat(Erfahrung.zuWert("BEGINNER")).isEqualTo(Erfahrung.BEGINNER),
			() -> assertThat(Erfahrung.zuWert("FORTGESCHRITTEN")).isEqualTo(Erfahrung.FORTGESCHRITTEN),
			() -> assertThat(Erfahrung.zuWert("SEHR_FORTGESCHRITTEN")).isEqualTo(Erfahrung.SEHR_FORTGESCHRITTEN),
			() -> assertThat(Erfahrung.zuWert("EXPERTE")).isEqualTo(Erfahrung.EXPERTE));
	}

	@Test
	@DisplayName("null validieren")
	void test03()
	{
		assertThrows(NullPointerException.class, () -> Erfahrung.zuWert(null));
	}
}
