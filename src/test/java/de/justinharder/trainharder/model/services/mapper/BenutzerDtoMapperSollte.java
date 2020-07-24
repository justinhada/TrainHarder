package de.justinharder.trainharder.model.services.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.trainharder.model.domain.Authentifizierung;
import de.justinharder.trainharder.setup.Testdaten;
import de.justinharder.trainharder.view.dto.AuthentifizierungDto;

public class BenutzerDtoMapperSollte
{
	private BenutzerDtoMapper sut;

	private AuthentifizierungDtoMapper authentifizierungDtoMapper;

	@BeforeEach
	public void setup()
	{
		authentifizierungDtoMapper = mock(AuthentifizierungDtoMapper.class);

		sut = new BenutzerDtoMapper(authentifizierungDtoMapper);
	}

	private void angenommenDerAuthentifizierungDtoMapperKonvertiertZuAuthentifizierungDto(
		final Authentifizierung authentifizierung,
		final AuthentifizierungDto authentifizierungDto)
	{
		when(authentifizierungDtoMapper.konvertiere(authentifizierung)).thenReturn(authentifizierungDto);
	}

	@Test
	@DisplayName("alle Benutzer zu BenutzerDtos konvertieren")
	public void test01()
	{
		final var erwartet = List.of(
			Testdaten.BENUTZER_DTO_JUSTIN);
		final var benutzer = List.of(
			Testdaten.BENUTZER_JUSTIN);
		angenommenDerAuthentifizierungDtoMapperKonvertiertZuAuthentifizierungDto(Testdaten.AUTHENTIFIZIERUNG_JUSTIN,
			Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN);

		final var ergebnis = sut.konvertiereAlle(benutzer);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(authentifizierungDtoMapper).konvertiere(Testdaten.AUTHENTIFIZIERUNG_JUSTIN);
	}
}
