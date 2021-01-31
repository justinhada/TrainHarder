package de.justinharder.trainharder.model.services.mapper;

import de.justinharder.trainharder.setup.Testdaten;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
		assertThat(sut.mappeAlle(List.of(Testdaten.AUTHENTIFIZIERUNG_JUSTIN, Testdaten.AUTHENTIFIZIERUNG_EDUARD)))
			.isEqualTo(List.of(Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN, Testdaten.AUTHENTIFIZIERUNG_DTO_EDUARD));
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