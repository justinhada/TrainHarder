package de.justinharder.trainharder.domain.services;

import de.justinharder.trainharder.domain.model.Benutzer;
import de.justinharder.trainharder.domain.model.embeddables.ID;
import de.justinharder.trainharder.domain.model.exceptions.AuthentifizierungException;
import de.justinharder.trainharder.domain.model.exceptions.BenutzerException;
import de.justinharder.trainharder.domain.repository.AuthentifizierungRepository;
import de.justinharder.trainharder.domain.repository.BenutzerRepository;
import de.justinharder.trainharder.domain.services.dto.BenutzerDto;
import de.justinharder.trainharder.domain.services.dto.BenutzerangabeDto;
import de.justinharder.trainharder.domain.services.dto.Benutzerdaten;
import de.justinharder.trainharder.domain.services.dto.NameDto;
import de.justinharder.trainharder.domain.services.mapper.BenutzerDtoMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static de.justinharder.trainharder.setup.Testdaten.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@DisplayName("BenutzerService sollte")
class BenutzerServiceTest
{
	private BenutzerService sut;

	private BenutzerRepository benutzerRepository;
	private AuthentifizierungRepository authentifizierungRepository;
	private BenutzerDtoMapper benutzerDtoMapper;

	@BeforeEach
	void setup()
	{
		benutzerRepository = mock(BenutzerRepository.class);
		authentifizierungRepository = mock(AuthentifizierungRepository.class);
		benutzerDtoMapper = mock(BenutzerDtoMapper.class);

		sut = new BenutzerService(benutzerRepository, authentifizierungRepository, benutzerDtoMapper);
	}

	@AfterEach
	void reset()
	{
		BENUTZER_JUSTIN.setBenutzerangabe(BENUTZERANGABE_JUSTIN);
	}

	@Test
	@DisplayName("alle finden")
	void test01()
	{
		var erwartet = List.of(BENUTZER_DTO_JUSTIN, BENUTZER_DTO_EDUARD);
		var benutzer = List.of(BENUTZER_JUSTIN, BENUTZER_EDUARD);
		when(benutzerRepository.findeAlle()).thenReturn(benutzer);
		when(benutzerDtoMapper.mappeAlle(benutzer)).thenReturn(erwartet);

		assertThat(sut.findeAlle()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("null validieren")
	void test02()
	{
		var benutzerdaten = new Benutzerdaten();
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> sut.finde(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.findeMitAuthentifizierung(null)),
			() -> assertThrows(NullPointerException.class,
				() -> sut.erstelle(null, AUTHENTIFIZIERUNG_DTO_JUSTIN.getId())),
			() -> assertThrows(NullPointerException.class, () -> sut.erstelle(benutzerdaten, null)),
			() -> assertThrows(NullPointerException.class, () -> sut.aktualisiere(null, benutzerdaten)),
			() -> assertThrows(NullPointerException.class, () -> sut.aktualisiere(BENUTZER_DTO_JUSTIN.getId(), null)));
	}

	@Test
	@DisplayName("BenutzerException werfen, wenn kein Benutzer zu ID ermittelt wird")
	void test03()
	{
		var id = new ID().getWert().toString();
		when(benutzerRepository.finde(new ID(id))).thenReturn(Optional.empty());

		assertThrows(BenutzerException.class, () -> sut.finde(id));
		verify(benutzerRepository).finde(new ID(id));
	}

	@Test
	@DisplayName("finden")
	void test04() throws BenutzerException
	{
		when(benutzerRepository.finde(BENUTZER_JUSTIN.getId())).thenReturn(Optional.of(BENUTZER_JUSTIN));
		when(benutzerDtoMapper.mappe(BENUTZER_JUSTIN)).thenReturn(BENUTZER_DTO_JUSTIN);

		assertThat(sut.finde(BENUTZER_DTO_JUSTIN.getId())).isEqualTo(BENUTZER_DTO_JUSTIN);
		verify(benutzerRepository).finde(BENUTZER_JUSTIN.getId());
		verify(benutzerDtoMapper).mappe(BENUTZER_JUSTIN);
	}

	@Test
	@DisplayName("BenutzerException werfen, wenn kein Benutzer zur Authentifizierung ermittelt werden kann")
	void test05()
	{
		var authentifizierungId = new ID().getWert().toString();
		when(benutzerRepository.findeMitAuthentifizierung(new ID(authentifizierungId))).thenReturn(Optional.empty());

		assertThrows(BenutzerException.class, () -> sut.findeMitAuthentifizierung(authentifizierungId));
		verify(benutzerRepository).findeMitAuthentifizierung(new ID(authentifizierungId));
	}

	@Test
	@DisplayName("einen Benutzer zur AuthentifizierungID ermitteln")
	void test06() throws BenutzerException
	{
		var authentifizierungId = new ID().getWert().toString();
		when(benutzerRepository.findeMitAuthentifizierung(new ID(authentifizierungId))).thenReturn(
			Optional.of(BENUTZER_JUSTIN));
		when(benutzerDtoMapper.mappe(BENUTZER_JUSTIN)).thenReturn(BENUTZER_DTO_JUSTIN);

		assertThat(sut.findeMitAuthentifizierung(authentifizierungId)).isEqualTo(BENUTZER_DTO_JUSTIN);
		verify(benutzerRepository).findeMitAuthentifizierung(new ID(authentifizierungId));
		verify(benutzerDtoMapper).mappe(BENUTZER_JUSTIN);
	}

	@Test
	@DisplayName("AuthentifizierungException werfen, wenn die AuthentifizierungID nicht existiert")
	void test07()
	{
		when(authentifizierungRepository.finde(AUTHENTIFIZIERUNG_JUSTIN.getId())).thenReturn(Optional.empty());

		assertThrows(AuthentifizierungException.class, () -> sut.erstelle(
			new Benutzerdaten(
				"Justin",
				"Harder",
				"1998-12-06",
				"MAENNLICH",
				"BEGINNER",
				"GUT",
				"GUT",
				"MITTELMAESSIG",
				"NEIN",
				"GUT"),
			AUTHENTIFIZIERUNG_DTO_JUSTIN.getId()));
		verify(authentifizierungRepository).finde(AUTHENTIFIZIERUNG_JUSTIN.getId());
	}

	@Test
	@DisplayName("einen neuen Benutzer erstellen")
	void test08() throws AuthentifizierungException
	{
		var benutzerdaten = new Benutzerdaten(
			"Justin",
			"Harder",
			"1998-12-06",
			"MAENNLICH",
			"BEGINNER",
			"GUT",
			"GUT",
			"MITTELMAESSIG",
			"NEIN",
			"GUT");
		when(authentifizierungRepository.finde(AUTHENTIFIZIERUNG_JUSTIN.getId())).thenReturn(
			Optional.of(AUTHENTIFIZIERUNG_JUSTIN));
		when(benutzerDtoMapper.mappe(any(Benutzer.class))).thenReturn(BENUTZER_DTO_JUSTIN);

		assertThat(sut.erstelle(benutzerdaten, AUTHENTIFIZIERUNG_DTO_JUSTIN.getId())).isEqualTo(BENUTZER_DTO_JUSTIN);
		verify(authentifizierungRepository).finde(AUTHENTIFIZIERUNG_JUSTIN.getId());
		verify(benutzerRepository).speichere(any(Benutzer.class));
		verify(benutzerDtoMapper).mappe(any(Benutzer.class));
	}

	@Test
	@DisplayName("BenutzerException werfen, wenn die BenutzerID nicht existiert")
	void test09()
	{
		var id = new ID().getWert().toString();
		when(benutzerRepository.finde(new ID(id))).thenReturn(Optional.empty());

		assertThrows(BenutzerException.class, () -> sut.aktualisiere(id, new Benutzerdaten()));
		verify(benutzerRepository).finde(new ID(id));
	}

	@Test
	@DisplayName("einen Benutzer aktualisieren")
	void test10() throws BenutzerException
	{
		var benutzer = BENUTZER_JUSTIN;
		var id = benutzer.getId().getWert().toString();
		var erwartet = new BenutzerDto(
			id,
			new NameDto("Justin", "Harder"),
			LocalDate.of(1998, 12, 6),
			new BenutzerangabeDto("MAENNLICH", "FORTGESCHRITTEN", "GUT", "GUT", "MITTELMAESSIG", "NEIN", "GUT")
				.setKraftlevel("CLASS_5"),
			AUTHENTIFIZIERUNG_DTO_JUSTIN,
			List.of(KOERPERMESSUNG_DTO_JUSTIN));
		var benutzerdaten = new Benutzerdaten(
			"Justin",
			"Harder",
			"1998-12-06",
			"MAENNLICH",
			"FORTGESCHRITTEN",
			"GUT",
			"GUT",
			"MITTELMAESSIG",
			"NEIN",
			"GUT");
		when(benutzerRepository.finde(new ID(id))).thenReturn(Optional.of(benutzer));
		when(benutzerDtoMapper.mappe(benutzer)).thenReturn(erwartet);

		assertThat(sut.aktualisiere(id, benutzerdaten)).isEqualTo(erwartet);
		verify(benutzerRepository).finde(new ID(id));
		verify(benutzerRepository).speichere(benutzer);
		verify(benutzerDtoMapper).mappe(benutzer);
	}
}
