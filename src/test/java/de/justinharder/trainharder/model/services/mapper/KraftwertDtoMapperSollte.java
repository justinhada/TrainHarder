package de.justinharder.trainharder.model.services.mapper;

import de.justinharder.trainharder.setup.Testdaten;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class KraftwertDtoMapperSollte
{
	private KraftwertDtoMapper sut;

	@BeforeEach
	void setup()
	{
		sut = new KraftwertDtoMapper();
	}

	@Test
	@DisplayName("alle Kraftwerte zu KraftwertDtos mappen")
	void test01()
	{
		var erwartet = List.of(
			Testdaten.KRAFTWERT_DTO_LOWBAR_KNIEBEUGE,
			Testdaten.KRAFTWERT_DTO_WETTKAMPFBANKDRUECKEN,
			Testdaten.KRAFTWERT_DTO_KONVENTIONELLES_KREUZHEBEN);
		var kraftwerte = List.of(
			Testdaten.KRAFTWERT_LOWBAR_KNIEBEUGE,
			Testdaten.KRAFTWERT_WETTKAMPFBANKDRUECKEN,
			Testdaten.KRAFTWERT_KONVENTIONELLES_KREUZHEBEN);

		var ergebnis = sut.mappeAlle(kraftwerte);

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
