package de.justinharder.trainharder.model.services.mapper;

import de.justinharder.trainharder.model.domain.Belastung;
import de.justinharder.trainharder.setup.Testdaten;
import de.justinharder.trainharder.view.dto.BelastungDto;
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

	private BelastungsfaktorDtoMapper belastungsfaktorDtoMapper;

	@BeforeEach
	void setup()
	{
		belastungsfaktorDtoMapper = mock(BelastungsfaktorDtoMapper.class);

		sut = new UebungDtoMapper(belastungsfaktorDtoMapper);
	}

	private void angenommenDerBelastungsfaktorDtoMapperMapptZuBelastungsfaktorDto(Belastung belastung, BelastungDto belastungDto)
	{
		when(belastungsfaktorDtoMapper.mappe(belastung)).thenReturn(belastungDto);
	}

	@Test
	@DisplayName("alle Uebungen zu UebungDtos mappen")
	void test01()
	{
		angenommenDerBelastungsfaktorDtoMapperMapptZuBelastungsfaktorDto(Testdaten.BELASTUNG_LOWBAR_KNIEBEUGE, Testdaten.BELASTUNGSFAKTOR_DTO_LOWBAR_KNIEBEUGE);
		angenommenDerBelastungsfaktorDtoMapperMapptZuBelastungsfaktorDto(Testdaten.BELASTUNG_WETTKAMPFBANKDRUECKEN, Testdaten.BELASTUNGSFAKTOR_DTO_WETTKAMPFBANKDRUECKEN);
		angenommenDerBelastungsfaktorDtoMapperMapptZuBelastungsfaktorDto(Testdaten.BELASTUNG_KONVENTIONELLES_KREUZHEBEN, Testdaten.BELASTUNGSFAKTOR_DTO_KONVENTIONELLES_KREUZHEBEN);

		assertThat(sut.mappeAlle(List.of(Testdaten.UEBUNG_LOWBAR_KNIEBEUGE, Testdaten.UEBUNG_WETTKAMPFBANKDRUECKEN, Testdaten.UEBUNG_KONVENTIONELLES_KREUZHEBEN)))
			.isEqualTo(List.of(Testdaten.UEBUNG_DTO_LOWBAR_KNIEBEUGE, Testdaten.UEBUNG_DTO_WETTKAMPFBANKDRUECKEN, Testdaten.UEBUNG_DTO_KONVENTIONELLES_KREUZHEBEN));
		verify(belastungsfaktorDtoMapper).mappe(Testdaten.BELASTUNG_LOWBAR_KNIEBEUGE);
		verify(belastungsfaktorDtoMapper).mappe(Testdaten.BELASTUNG_WETTKAMPFBANKDRUECKEN);
		verify(belastungsfaktorDtoMapper).mappe(Testdaten.BELASTUNG_KONVENTIONELLES_KREUZHEBEN);
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