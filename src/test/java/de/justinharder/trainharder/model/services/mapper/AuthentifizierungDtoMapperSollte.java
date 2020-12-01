package de.justinharder.trainharder.model.services.mapper;

import de.justinharder.trainharder.setup.Testdaten;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AuthentifizierungDtoMapperSollte
{
	private AuthentifizierungDtoMapper sut;

	@BeforeEach
	void setup()
	{
		sut = new AuthentifizierungDtoMapper();
	}

	@Test
	@DisplayName("alle Authentifizierungen zu AuthentifizierungDtos mappen")
	void test01()
	{
		var erwartet = List.of(Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN, Testdaten.AUTHENTIFIZIERUNG_DTO_EDUARD);
		var authentifizierungen = List.of(Testdaten.AUTHENTIFIZIERUNG_JUSTIN, Testdaten.AUTHENTIFIZIERUNG_EDUARD);

		var ergebnis = sut.mappeAlle(authentifizierungen);

		assertThat(ergebnis).isEqualTo(erwartet);
	}
}
