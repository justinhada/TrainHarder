package de.justinharder.trainharder.domain.services.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static de.justinharder.trainharder.setup.Testdaten.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("BelastungDtoMapper sollte")
class BelastungDtoMapperTest
{
	private BelastungDtoMapper sut;

	@BeforeEach
	void setup()
	{
		sut = new BelastungDtoMapper();
	}

	@Test
	@DisplayName("alle mappen")
	void test01()
	{
		assertThat(sut.mappeAlle(
			List.of(BELASTUNG_WETTKAMPFBANKDRUECKEN, BELASTUNG_LOWBAR_KNIEBEUGE, BELASTUNG_KONVENTIONELLES_KREUZHEBEN)))
			.isEqualTo(List.of(BELASTUNG_DTO_WETTKAMPFBANKDRUECKEN, BELASTUNG_DTO_LOWBAR_KNIEBEUGE,
				BELASTUNG_DTO_KONVENTIONELLES_KREUZHEBEN));
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
