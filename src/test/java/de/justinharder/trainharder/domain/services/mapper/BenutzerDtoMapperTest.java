package de.justinharder.trainharder.domain.services.mapper;

import de.justinharder.trainharder.domain.model.Authentifizierung;
import de.justinharder.trainharder.domain.model.Koerpermessung;
import de.justinharder.trainharder.setup.Testdaten;
import de.justinharder.trainharder.domain.services.dto.AuthentifizierungDto;
import de.justinharder.trainharder.domain.services.dto.KoerpermessungDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class BenutzerDtoMapperSollte
{
	private BenutzerDtoMapper sut;

	private AuthentifizierungDtoMapper authentifizierungDtoMapper;
	private KoerpermessungDtoMapper koerpermessungDtoMapper;

	@BeforeEach
	void setup()
	{
		authentifizierungDtoMapper = mock(AuthentifizierungDtoMapper.class);
		koerpermessungDtoMapper = mock(KoerpermessungDtoMapper.class);

		sut = new BenutzerDtoMapper(authentifizierungDtoMapper, koerpermessungDtoMapper);
	}

	private void angenommenDerAuthentifizierungDtoMapperMapptZuAuthentifizierungDto(Authentifizierung authentifizierung, AuthentifizierungDto authentifizierungDto)
	{
		when(authentifizierungDtoMapper.mappe(authentifizierung)).thenReturn(authentifizierungDto);
	}

	private void angenommenDerKoerpermessungDtoMapperMapptAlleZuKoerpermessungDto(List<Koerpermessung> koerpermessungen, List<KoerpermessungDto> koerpermessungDtos)
	{
		when(koerpermessungDtoMapper.mappeAlle(koerpermessungen)).thenReturn(koerpermessungDtos);
	}

	@Test
	@DisplayName("alle Benutzer zu BenutzerDtos mappen")
	void test01()
	{
		angenommenDerAuthentifizierungDtoMapperMapptZuAuthentifizierungDto(Testdaten.AUTHENTIFIZIERUNG_JUSTIN, Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN);
		angenommenDerAuthentifizierungDtoMapperMapptZuAuthentifizierungDto(Testdaten.AUTHENTIFIZIERUNG_EDUARD, Testdaten.AUTHENTIFIZIERUNG_DTO_EDUARD);
		angenommenDerKoerpermessungDtoMapperMapptAlleZuKoerpermessungDto(List.of(Testdaten.KOERPERMESSUNG_JUSTIN), List.of(Testdaten.KOERPERMESSUNG_DTO_JUSTIN));
		angenommenDerKoerpermessungDtoMapperMapptAlleZuKoerpermessungDto(List.of(Testdaten.KOERPERMESSUNG_EDUARD), List.of(Testdaten.KOERPERMESSUNG_DTO_EDUARD));

		var ergebnis = sut.mappeAlle(List.of(Testdaten.BENUTZER_JUSTIN, Testdaten.BENUTZER_EDUARD));

		assertThat(ergebnis).containsAll(List.of(Testdaten.BENUTZER_DTO_EDUARD));
		verify(authentifizierungDtoMapper).mappe(Testdaten.AUTHENTIFIZIERUNG_EDUARD);
		verify(koerpermessungDtoMapper).mappeAlle(List.of(Testdaten.KOERPERMESSUNG_EDUARD));
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
