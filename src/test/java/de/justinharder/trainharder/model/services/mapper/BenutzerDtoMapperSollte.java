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
import de.justinharder.trainharder.model.domain.Koerpermessung;
import de.justinharder.trainharder.setup.Testdaten;
import de.justinharder.trainharder.view.dto.AuthentifizierungDto;
import de.justinharder.trainharder.view.dto.KoerpermessungDto;

public class BenutzerDtoMapperSollte
{
	private BenutzerDtoMapper sut;

	private AuthentifizierungDtoMapper authentifizierungDtoMapper;
	private KoerpermessungDtoMapper koerpermessungDtoMapper;

	@BeforeEach
	public void setup()
	{
		authentifizierungDtoMapper = mock(AuthentifizierungDtoMapper.class);
		koerpermessungDtoMapper = mock(KoerpermessungDtoMapper.class);

		sut = new BenutzerDtoMapper(authentifizierungDtoMapper, koerpermessungDtoMapper);
	}

	private void angenommenDerAuthentifizierungDtoMapperKonvertiertZuAuthentifizierungDto(
		final Authentifizierung authentifizierung,
		final AuthentifizierungDto authentifizierungDto)
	{
		when(authentifizierungDtoMapper.konvertiere(authentifizierung)).thenReturn(authentifizierungDto);
	}

	private void angenommenDerKoerpermessungDtoMapperKonvertiertAlleZuKoerpermessungDto(
		final List<Koerpermessung> koerpermessungen,
		final List<KoerpermessungDto> koerpermessungDtos)
	{
		when(koerpermessungDtoMapper.konvertiereAlle(koerpermessungen)).thenReturn(koerpermessungDtos);
	}

	@Test
	@DisplayName("alle Benutzer zu BenutzerDtos konvertieren")
	public void test01()
	{
		final var erwartet = List.of(
			Testdaten.BENUTZER_DTO_JUSTIN,
			Testdaten.BENUTZER_DTO_EDUARD);
		final var benutzer = List.of(
			Testdaten.BENUTZER_JUSTIN,
			Testdaten.BENUTZER_EDUARD);
		angenommenDerAuthentifizierungDtoMapperKonvertiertZuAuthentifizierungDto(
			Testdaten.AUTHENTIFIZIERUNG_JUSTIN,
			Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN);
		angenommenDerAuthentifizierungDtoMapperKonvertiertZuAuthentifizierungDto(
			Testdaten.AUTHENTIFIZIERUNG_EDUARD,
			Testdaten.AUTHENTIFIZIERUNG_DTO_EDUARD);
		angenommenDerKoerpermessungDtoMapperKonvertiertAlleZuKoerpermessungDto(
			List.of(Testdaten.KOERPERMESSUNG_JUSTIN),
			List.of(Testdaten.KOERPERMESSUNG_DTO_JUSTIN));
		angenommenDerKoerpermessungDtoMapperKonvertiertAlleZuKoerpermessungDto(
			List.of(Testdaten.KOERPERMESSUNG_EDUARD),
			List.of(Testdaten.KOERPERMESSUNG_DTO_EDUARD));

		final var ergebnis = sut.konvertiereAlle(benutzer);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(authentifizierungDtoMapper).konvertiere(Testdaten.AUTHENTIFIZIERUNG_JUSTIN);
		verify(authentifizierungDtoMapper).konvertiere(Testdaten.AUTHENTIFIZIERUNG_EDUARD);
		verify(koerpermessungDtoMapper).konvertiereAlle(List.of(Testdaten.KOERPERMESSUNG_JUSTIN));
		verify(koerpermessungDtoMapper).konvertiereAlle(List.of(Testdaten.KOERPERMESSUNG_EDUARD));
	}
}
