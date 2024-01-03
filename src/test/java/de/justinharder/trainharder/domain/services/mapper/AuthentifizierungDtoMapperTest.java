package de.justinharder.trainharder.domain.services.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static de.justinharder.trainharder.setup.Testdaten.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("AuthentifizierungDtoMapper sollte")
class AuthentifizierungDtoMapperTest
{
	private AuthentifizierungDtoMapper sut;

	@BeforeEach
	void setup()
	{
		sut = new AuthentifizierungDtoMapper();
	}

	@Test
	@DisplayName("alle mappen")
	void test01()
	{
		assertThat(sut.mappeAlle(List.of(AUTHENTIFIZIERUNG_JUSTIN, AUTHENTIFIZIERUNG_EDUARD))).isEqualTo(
			List.of(AUTHENTIFIZIERUNG_DTO_JUSTIN, AUTHENTIFIZIERUNG_DTO_EDUARD));
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
