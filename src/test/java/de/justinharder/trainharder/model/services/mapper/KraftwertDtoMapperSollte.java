package de.justinharder.trainharder.model.services.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.trainharder.setup.Testdaten;

public class KraftwertDtoMapperSollte
{
	private KraftwertDtoMapper sut;

	@BeforeEach
	public void setup()
	{
		sut = new KraftwertDtoMapper();
	}

	@Test
	@DisplayName("alle Kraftwerte zu KraftwertDtos konvertieren")
	public void test01()
	{
		final var erwartet = List.of(
			Testdaten.KRAFTWERT_DTO_LOWBAR_KNIEBEUGE,
			Testdaten.KRAFTWERT_DTO_WETTKAMPFBANKDRUECKEN,
			Testdaten.KRAFTWERT_DTO_KONVENTIONELLES_KREUZHEBEN);
		final var kraftwerte = List.of(
			Testdaten.KRAFTWERT_LOWBAR_KNIEBEUGE,
			Testdaten.KRAFTWERT_WETTKAMPFBANKDRUECKEN,
			Testdaten.KRAFTWERT_KONVENTIONELLES_KREUZHEBEN);

		final var ergebnis = sut.konvertiereAlle(kraftwerte);

		assertThat(ergebnis).isEqualTo(erwartet);
	}
}
