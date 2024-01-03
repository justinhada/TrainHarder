package de.justinharder.trainharder.domain.services.mapper;

import de.justinharder.trainharder.setup.Testdaten;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class KoerpermasseDtoMapperSollte
{
	private KoerpermasseDtoMapper sut;

	@BeforeEach
	void setup()
	{
		sut = new KoerpermasseDtoMapper();
	}

	@Test
	@DisplayName("Koerpermasse zu KoerpermasseDto mappen")
	void test01()
	{
		assertAll(
			() -> assertThat(sut.mappe(Testdaten.KOERPERMASSE_JUSTIN)).isEqualTo(Testdaten.KOERPERMASSE_DTO_JUSTIN),
			() -> assertThat(sut.mappe(Testdaten.KOERPERMASSE_EDUARD)).isEqualTo(Testdaten.KOERPERMASSE_DTO_EDUARD));
	}

	@Test
	@DisplayName("null validieren")
	void test02()
	{
		assertThrows(NullPointerException.class, () -> sut.mappe(null));
	}
}
