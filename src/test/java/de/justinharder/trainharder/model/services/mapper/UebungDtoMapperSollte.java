package de.justinharder.trainharder.model.services.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.trainharder.model.domain.Belastungsfaktor;
import de.justinharder.trainharder.setup.Testdaten;
import de.justinharder.trainharder.view.dto.BelastungsfaktorDto;

public class UebungDtoMapperSollte
{
	private UebungDtoMapper sut;

	private BelastungsfaktorDtoMapper belastungsfaktorDtoMapper;

	@BeforeEach
	public void setup()
	{
		belastungsfaktorDtoMapper = mock(BelastungsfaktorDtoMapper.class);

		sut = new UebungDtoMapper(belastungsfaktorDtoMapper);
	}

	private void angenommenDerBelastungsfaktorDtoMapperKonvertiertZuBelastungsfaktorDto(
		final Belastungsfaktor belastungsfaktor, final BelastungsfaktorDto belastungsfaktorDto)
	{
		when(belastungsfaktorDtoMapper.konvertiere(belastungsfaktor)).thenReturn(belastungsfaktorDto);
	}

	@Test
	@DisplayName("alle Uebungen zu UebungDtos konvertieren")
	public void test01()
	{
		final var erwartet = List.of(
			Testdaten.UEBUNG_DTO_LOWBAR_KNIEBEUGE,
			Testdaten.UEBUNG_DTO_WETTKAMPFBANKDRUECKEN,
			Testdaten.UEBUNG_DTO_KONVENTIONELLES_KREUZHEBEN);
		final var uebungen = List.of(
			Testdaten.LOWBAR_KNIEBEUGE,
			Testdaten.WETTKAMPFBANKDRUECKEN,
			Testdaten.KONVENTIONELLES_KREUZHEBEN);
		angenommenDerBelastungsfaktorDtoMapperKonvertiertZuBelastungsfaktorDto(
			Testdaten.BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE,
			Testdaten.BELASTUNGSFAKTOR_DTO_LOWBAR_KNIEBEUGE);
		angenommenDerBelastungsfaktorDtoMapperKonvertiertZuBelastungsfaktorDto(
			Testdaten.BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN,
			Testdaten.BELASTUNGSFAKTOR_DTO_WETTKAMPFBANKDRUECKEN);
		angenommenDerBelastungsfaktorDtoMapperKonvertiertZuBelastungsfaktorDto(
			Testdaten.BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN,
			Testdaten.BELASTUNGSFAKTOR_DTO_KONVENTIONELLES_KREUZHEBEN);

		final var ergebnis = sut.konvertiereAlle(uebungen);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(belastungsfaktorDtoMapper).konvertiere(Testdaten.BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE);
		verify(belastungsfaktorDtoMapper).konvertiere(Testdaten.BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN);
		verify(belastungsfaktorDtoMapper).konvertiere(Testdaten.BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN);
	}
}
