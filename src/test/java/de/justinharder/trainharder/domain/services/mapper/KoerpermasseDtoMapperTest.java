package de.justinharder.trainharder.domain.services.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static de.justinharder.trainharder.setup.Testdaten.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("KoerpermasseDtoMapper sollte")
class KoerpermasseDtoMapperTest
{
	private KoerpermasseDtoMapper sut;

	@BeforeEach
	void setup()
	{
		sut = new KoerpermasseDtoMapper();
	}

	@Test
	@DisplayName("mappen")
	void test01()
	{
		assertAll(
			() -> assertThat(sut.mappe(KOERPERMASSE_JUSTIN)).isEqualTo(KOERPERMASSE_DTO_JUSTIN),
			() -> assertThat(sut.mappe(KOERPERMASSE_EDUARD)).isEqualTo(KOERPERMASSE_DTO_EDUARD));
	}

	@Test
	@DisplayName("null validieren")
	void test02()
	{
		assertThrows(NullPointerException.class, () -> sut.mappe(null));
	}
}
