package de.justinharder.trainharder.model.services.mapper;

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
	private BelastungsfaktorDtoMapper sut;

	@BeforeEach
	void setup()
	{
		sut = new BelastungsfaktorDtoMapper();
	}

	@Test
	@DisplayName("alle Belastungsfaktoren zu BelastungsfaktorDtos mappen")
	void test01()
	{
		var erwartet = List.of(
			Testdaten.BELASTUNGSFAKTOR_DTO_WETTKAMPFBANKDRUECKEN,
			Testdaten.BELASTUNGSFAKTOR_DTO_LOWBAR_KNIEBEUGE,
			Testdaten.BELASTUNGSFAKTOR_DTO_KONVENTIONELLES_KREUZHEBEN);
		var belastungsfaktoren = List.of(
			Testdaten.BELASTUNG_WETTKAMPFBANKDRUECKEN,
			Testdaten.BELASTUNG_LOWBAR_KNIEBEUGE,
			Testdaten.BELASTUNG_KONVENTIONELLES_KREUZHEBEN);

		var ergebnis = sut.mappeAlle(belastungsfaktoren);

		assertThat(ergebnis).isEqualTo(erwartet);
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
