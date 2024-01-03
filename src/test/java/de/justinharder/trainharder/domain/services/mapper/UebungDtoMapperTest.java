package de.justinharder.trainharder.domain.services.mapper;

import de.justinharder.trainharder.domain.model.Belastung;
import de.justinharder.trainharder.setup.Testdaten;
import de.justinharder.trainharder.domain.services.dto.BelastungDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class UebungDtoMapperSollte
{
	private UebungDtoMapper sut;

	private BelastungDtoMapper belastungDtoMapper;

	@BeforeEach
	void setup()
	{
		belastungDtoMapper = mock(BelastungDtoMapper.class);

		sut = new UebungDtoMapper(belastungDtoMapper);
	}

	private void angenommenDerBelastungsfaktorDtoMapperMapptZuBelastungsfaktorDto(Belastung belastung, BelastungDto belastungDto)
	{
		when(belastungDtoMapper.mappe(belastung)).thenReturn(belastungDto);
	}

	@Test
	@DisplayName("alle Uebungen zu UebungDtos mappen")
	void test01()
	{
		angenommenDerBelastungsfaktorDtoMapperMapptZuBelastungsfaktorDto(Testdaten.BELASTUNG_LOWBAR_KNIEBEUGE, Testdaten.BELASTUNG_DTO_LOWBAR_KNIEBEUGE);
		angenommenDerBelastungsfaktorDtoMapperMapptZuBelastungsfaktorDto(Testdaten.BELASTUNG_WETTKAMPFBANKDRUECKEN, Testdaten.BELASTUNG_DTO_WETTKAMPFBANKDRUECKEN);
		angenommenDerBelastungsfaktorDtoMapperMapptZuBelastungsfaktorDto(Testdaten.BELASTUNG_KONVENTIONELLES_KREUZHEBEN, Testdaten.BELASTUNG_DTO_KONVENTIONELLES_KREUZHEBEN);

		assertThat(sut.mappeAlle(List.of(Testdaten.UEBUNG_LOWBAR_KNIEBEUGE, Testdaten.UEBUNG_WETTKAMPFBANKDRUECKEN, Testdaten.UEBUNG_KONVENTIONELLES_KREUZHEBEN)))
			.isEqualTo(List.of(Testdaten.UEBUNG_DTO_LOWBAR_KNIEBEUGE, Testdaten.UEBUNG_DTO_WETTKAMPFBANKDRUECKEN, Testdaten.UEBUNG_DTO_KONVENTIONELLES_KREUZHEBEN));
		verify(belastungDtoMapper).mappe(Testdaten.BELASTUNG_LOWBAR_KNIEBEUGE);
		verify(belastungDtoMapper).mappe(Testdaten.BELASTUNG_WETTKAMPFBANKDRUECKEN);
		verify(belastungDtoMapper).mappe(Testdaten.BELASTUNG_KONVENTIONELLES_KREUZHEBEN);
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
