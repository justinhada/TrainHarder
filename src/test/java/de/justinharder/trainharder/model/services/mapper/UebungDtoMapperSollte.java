package de.justinharder.trainharder.model.services.mapper;

import de.justinharder.trainharder.model.domain.Belastungsfaktor;
import de.justinharder.trainharder.setup.Testdaten;
import de.justinharder.trainharder.view.dto.BelastungsfaktorDto;
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

	private void angenommenDerBelastungsfaktorDtoMapperMapptZuBelastungsfaktorDto(
		Belastungsfaktor belastungsfaktor, BelastungsfaktorDto belastungsfaktorDto)
	{
		when(belastungsfaktorDtoMapper.mappe(belastungsfaktor)).thenReturn(belastungsfaktorDto);
	}

	@Test
	@DisplayName("alle Uebungen zu UebungDtos mappen")
	void test01()
	{
		var erwartet = List.of(
			Testdaten.UEBUNG_DTO_LOWBAR_KNIEBEUGE,
			Testdaten.UEBUNG_DTO_WETTKAMPFBANKDRUECKEN,
			Testdaten.UEBUNG_DTO_KONVENTIONELLES_KREUZHEBEN);
		var uebungen = List.of(
			Testdaten.UEBUNG_LOWBAR_KNIEBEUGE,
			Testdaten.UEBUNG_WETTKAMPFBANKDRUECKEN,
			Testdaten.UEBUNG_KONVENTIONELLES_KREUZHEBEN);
		angenommenDerBelastungsfaktorDtoMapperMapptZuBelastungsfaktorDto(
			Testdaten.BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE,
			Testdaten.BELASTUNGSFAKTOR_DTO_LOWBAR_KNIEBEUGE);
		angenommenDerBelastungsfaktorDtoMapperMapptZuBelastungsfaktorDto(
			Testdaten.BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN,
			Testdaten.BELASTUNGSFAKTOR_DTO_WETTKAMPFBANKDRUECKEN);
		angenommenDerBelastungsfaktorDtoMapperMapptZuBelastungsfaktorDto(
			Testdaten.BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN,
			Testdaten.BELASTUNGSFAKTOR_DTO_KONVENTIONELLES_KREUZHEBEN);

		var ergebnis = sut.mappeAlle(uebungen);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(belastungsfaktorDtoMapper).mappe(Testdaten.BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE);
		verify(belastungsfaktorDtoMapper).mappe(Testdaten.BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN);
		verify(belastungsfaktorDtoMapper).mappe(Testdaten.BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN);
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
