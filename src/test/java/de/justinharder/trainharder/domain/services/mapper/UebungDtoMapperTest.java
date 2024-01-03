package de.justinharder.trainharder.domain.services.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static de.justinharder.trainharder.setup.Testdaten.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@DisplayName("UebungDtoMapper sollte")
class UebungDtoMapperTest
{
	private UebungDtoMapper sut;

	private BelastungDtoMapper belastungDtoMapper;

	@BeforeEach
	void setup()
	{
		belastungDtoMapper = mock(BelastungDtoMapper.class);

		sut = new UebungDtoMapper(belastungDtoMapper);
	}

	@Test
	@DisplayName("alle mappen")
	void test01()
	{
		when(belastungDtoMapper.mappe(BELASTUNG_LOWBAR_KNIEBEUGE)).thenReturn(BELASTUNG_DTO_LOWBAR_KNIEBEUGE);
		when(belastungDtoMapper.mappe(BELASTUNG_WETTKAMPFBANKDRUECKEN)).thenReturn(BELASTUNG_DTO_WETTKAMPFBANKDRUECKEN);
		when(belastungDtoMapper.mappe(BELASTUNG_KONVENTIONELLES_KREUZHEBEN)).thenReturn(
			BELASTUNG_DTO_KONVENTIONELLES_KREUZHEBEN);

		assertThat(sut.mappeAlle(
			List.of(UEBUNG_LOWBAR_KNIEBEUGE, UEBUNG_WETTKAMPFBANKDRUECKEN, UEBUNG_KONVENTIONELLES_KREUZHEBEN)))
			.isEqualTo(List.of(UEBUNG_DTO_LOWBAR_KNIEBEUGE, UEBUNG_DTO_WETTKAMPFBANKDRUECKEN,
				UEBUNG_DTO_KONVENTIONELLES_KREUZHEBEN));
		verify(belastungDtoMapper).mappe(BELASTUNG_LOWBAR_KNIEBEUGE);
		verify(belastungDtoMapper).mappe(BELASTUNG_WETTKAMPFBANKDRUECKEN);
		verify(belastungDtoMapper).mappe(BELASTUNG_KONVENTIONELLES_KREUZHEBEN);
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
