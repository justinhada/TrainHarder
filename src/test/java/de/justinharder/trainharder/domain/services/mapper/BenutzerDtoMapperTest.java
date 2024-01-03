package de.justinharder.trainharder.domain.services.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static de.justinharder.trainharder.setup.Testdaten.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@DisplayName("BenutzerDtoMapper sollte")
class BenutzerDtoMapperTest
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

	@Test
	@DisplayName("alle mappen")
	void test01()
	{
		when(authentifizierungDtoMapper.mappe(AUTHENTIFIZIERUNG_JUSTIN)).thenReturn(AUTHENTIFIZIERUNG_DTO_JUSTIN);
		when(authentifizierungDtoMapper.mappe(AUTHENTIFIZIERUNG_EDUARD)).thenReturn(AUTHENTIFIZIERUNG_DTO_EDUARD);
		when(koerpermessungDtoMapper.mappeAlle(List.of(KOERPERMESSUNG_JUSTIN))).thenReturn(
			List.of(KOERPERMESSUNG_DTO_JUSTIN));
		when(koerpermessungDtoMapper.mappeAlle(List.of(KOERPERMESSUNG_EDUARD))).thenReturn(
			List.of(KOERPERMESSUNG_DTO_EDUARD));

		var ergebnis = sut.mappeAlle(List.of(BENUTZER_JUSTIN, BENUTZER_EDUARD));

		assertThat(ergebnis).containsAll(List.of(BENUTZER_DTO_EDUARD));
		verify(authentifizierungDtoMapper).mappe(AUTHENTIFIZIERUNG_EDUARD);
		verify(koerpermessungDtoMapper).mappeAlle(List.of(KOERPERMESSUNG_EDUARD));
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
