package de.justinharder.trainharder.model.services.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.trainharder.setup.Testdaten;

class BelastungsfaktorDtoMapperSollte
{
	private BelastungsfaktorDtoMapper sut;

	@BeforeEach
	void setup()
	{
		sut = new BelastungsfaktorDtoMapper();
	}

	@Test
	@DisplayName("alle Belastungsfaktoren zu BelastungsfaktorDtos konvertieren")
	void test01()
	{
		final var erwartet = List.of(
			Testdaten.BELASTUNGSFAKTOR_DTO_WETTKAMPFBANKDRUECKEN,
			Testdaten.BELASTUNGSFAKTOR_DTO_LOWBAR_KNIEBEUGE,
			Testdaten.BELASTUNGSFAKTOR_DTO_KONVENTIONELLES_KREUZHEBEN);
		final var belastungsfaktoren = List.of(
			Testdaten.BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN,
			Testdaten.BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE,
			Testdaten.BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN);

		final var ergebnis = sut.konvertiereAlle(belastungsfaktoren);

		assertThat(ergebnis).isEqualTo(erwartet);
	}
}
