package de.justinharder.trainharder.model.services.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.trainharder.setup.Testdaten;

class AuthentifizierungDtoMapperSollte
{
	private AuthentifizierungDtoMapper sut;

	@BeforeEach
	void setup()
	{
		sut = new AuthentifizierungDtoMapper();
	}

	@Test
	@DisplayName("alle Authentifizierungen zu AuthentifizierungDtos konvertieren")
	void test01()
	{
		final var erwartet = List.of(
			Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN,
			Testdaten.AUTHENTIFIZIERUNG_DTO_EDUARD);
		final var authentifizierungen = List.of(
			Testdaten.AUTHENTIFIZIERUNG_JUSTIN,
			Testdaten.AUTHENTIFIZIERUNG_EDUARD);

		final var ergebnis = sut.konvertiereAlle(authentifizierungen);

		assertThat(ergebnis).isEqualTo(erwartet);
	}
}
