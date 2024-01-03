package de.justinharder.trainharder.domain.services.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static de.justinharder.trainharder.setup.Testdaten.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("KoerpermessungDtoMapper sollte")
class KoerpermessungDtoMapperTest
{
	private KoerpermessungDtoMapper sut;

	private KoerpermasseDtoMapper koerpermasseDtoMapper;

	@BeforeEach
	void setup()
	{
		koerpermasseDtoMapper = mock(KoerpermasseDtoMapper.class);

		sut = new KoerpermessungDtoMapper(koerpermasseDtoMapper);
	}

	@Test
	@DisplayName("alle Koerpermessungen zu KoerpermessungDtos mappen")
	void test01()
	{
		when(koerpermasseDtoMapper.mappe(KOERPERMASSE_JUSTIN)).thenReturn(KOERPERMASSE_DTO_JUSTIN);
		when(koerpermasseDtoMapper.mappe(KOERPERMASSE_EDUARD)).thenReturn(KOERPERMASSE_DTO_EDUARD);

		assertThat(sut.mappeAlle(List.of(KOERPERMESSUNG_JUSTIN, KOERPERMESSUNG_EDUARD))).isEqualTo(
			List.of(KOERPERMESSUNG_DTO_JUSTIN, KOERPERMESSUNG_DTO_EDUARD));
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
