package de.justinharder.trainharder.domain.services.mapper;

import de.justinharder.trainharder.setup.Testdaten;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BelastungDtoMapperSollte
{
	private BelastungDtoMapper sut;

	@BeforeEach
	void setup()
	{
		sut = new BelastungDtoMapper();
	}

	@Test
	@DisplayName("alle Belastungsfaktoren zu BelastungsfaktorDtos mappen")
	void test01()
	{
		assertThat(sut.mappeAlle(List.of(Testdaten.BELASTUNG_WETTKAMPFBANKDRUECKEN, Testdaten.BELASTUNG_LOWBAR_KNIEBEUGE, Testdaten.BELASTUNG_KONVENTIONELLES_KREUZHEBEN)))
			.isEqualTo(List.of(Testdaten.BELASTUNG_DTO_WETTKAMPFBANKDRUECKEN, Testdaten.BELASTUNG_DTO_LOWBAR_KNIEBEUGE, Testdaten.BELASTUNG_DTO_KONVENTIONELLES_KREUZHEBEN));
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
