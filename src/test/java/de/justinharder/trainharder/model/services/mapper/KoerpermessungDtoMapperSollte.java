package de.justinharder.trainharder.model.services.mapper;

import de.justinharder.trainharder.model.domain.embeddables.Koerpermasse;
import de.justinharder.trainharder.setup.Testdaten;
import de.justinharder.trainharder.view.dto.KoerpermasseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class KoerpermessungDtoMapperSollte
{
	private KoerpermessungDtoMapper sut;

	private KoerpermasseDtoMapper koerpermasseDtoMapper;

	@BeforeEach
	void setup()
	{
		koerpermasseDtoMapper = mock(KoerpermasseDtoMapper.class);

		sut = new KoerpermessungDtoMapper(koerpermasseDtoMapper);
	}

	private void angenommenKoerpermasseDtoMapperMappt(Koerpermasse koerpermasse, KoerpermasseDto koerpermasseDto)
	{
		when(koerpermasseDtoMapper.mappe(koerpermasse)).thenReturn(koerpermasseDto);
	}

	@Test
	@DisplayName("alle Koerpermessungen zu KoerpermessungDtos mappen")
	void test01()
	{
		angenommenKoerpermasseDtoMapperMappt(Testdaten.KOERPERMASSE_JUSTIN, Testdaten.KOERPERMASSE_DTO_JUSTIN);
		angenommenKoerpermasseDtoMapperMappt(Testdaten.KOERPERMASSE_EDUARD, Testdaten.KOERPERMASSE_DTO_EDUARD);

		assertThat(sut.mappeAlle(List.of(Testdaten.KOERPERMESSUNG_JUSTIN, Testdaten.KOERPERMESSUNG_EDUARD)))
			.isEqualTo(List.of(Testdaten.KOERPERMESSUNG_DTO_JUSTIN, Testdaten.KOERPERMESSUNG_DTO_EDUARD));
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