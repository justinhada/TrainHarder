package de.justinharder.trainharder.domain.services.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static de.justinharder.trainharder.setup.Testdaten.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("KraftwertDtoMapper sollte")
class KraftwertDtoMapperTest
{
	private KraftwertDtoMapper sut;

	@BeforeEach
	void setup()
	{
		sut = new KraftwertDtoMapper();
	}

	@Test
	@DisplayName("alle  mappen")
	void test01()
	{
		assertThat(sut.mappeAlle(
			List.of(KRAFTWERT_LOWBAR_KNIEBEUGE, KRAFTWERT_WETTKAMPFBANKDRUECKEN, KRAFTWERT_KONVENTIONELLES_KREUZHEBEN)))
			.isEqualTo(List.of(KRAFTWERT_DTO_LOWBAR_KNIEBEUGE, KRAFTWERT_DTO_WETTKAMPFBANKDRUECKEN,
				KRAFTWERT_DTO_KONVENTIONELLES_KREUZHEBEN));
	}

	@Test
	@DisplayName("null validieren")
	void test02()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> sut.mappeAlle(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.mappe(null)));
	}
}
