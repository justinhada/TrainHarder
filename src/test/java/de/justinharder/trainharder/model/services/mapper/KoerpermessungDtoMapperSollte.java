package de.justinharder.trainharder.model.services.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.trainharder.setup.Testdaten;

class KoerpermessungDtoMapperSollte
{
	private KoerpermessungDtoMapper sut;

	@BeforeEach
	void setup()
	{
		sut = new KoerpermessungDtoMapper();
	}

	@Test
	@DisplayName("alle Koerpermessungen zu KoerpermessungDtos konvertieren")
	void test01()
	{
		final var erwartet = List.of(Testdaten.KOERPERMESSUNG_DTO_JUSTIN, Testdaten.KOERPERMESSUNG_DTO_EDUARD);
		final var koerpermessungen = List.of(Testdaten.KOERPERMESSUNG_JUSTIN, Testdaten.KOERPERMESSUNG_EDUARD);

		final var ergebnis = sut.konvertiereAlle(koerpermessungen);

		assertThat(ergebnis).isEqualTo(erwartet);
	}
}
