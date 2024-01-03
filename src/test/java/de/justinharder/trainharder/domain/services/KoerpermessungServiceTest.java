package de.justinharder.trainharder.domain.services;

import de.justinharder.trainharder.domain.model.Authentifizierung;
import de.justinharder.trainharder.domain.model.Benutzer;
import de.justinharder.trainharder.domain.model.Koerpermessung;
import de.justinharder.trainharder.domain.model.embeddables.Benutzerangabe;
import de.justinharder.trainharder.domain.model.embeddables.ID;
import de.justinharder.trainharder.domain.model.embeddables.Koerpermasse;
import de.justinharder.trainharder.domain.model.embeddables.Name;
import de.justinharder.trainharder.domain.model.enums.*;
import de.justinharder.trainharder.domain.model.exceptions.BenutzerException;
import de.justinharder.trainharder.domain.model.exceptions.KoerpermessungException;
import de.justinharder.trainharder.domain.repository.BenutzerRepository;
import de.justinharder.trainharder.domain.repository.KoerpermessungRepository;
import de.justinharder.trainharder.domain.services.dto.KoerpermasseDto;
import de.justinharder.trainharder.domain.services.dto.Koerpermessdaten;
import de.justinharder.trainharder.domain.services.dto.KoerpermessungDto;
import de.justinharder.trainharder.domain.services.mapper.KoerpermessungDtoMapper;
import de.justinharder.trainharder.setup.Testdaten;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static de.justinharder.trainharder.setup.Testdaten.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("KoerpermessungService sollte")
class KoerpermessungServiceTest
{
	private KoerpermessungService sut;

	private KoerpermessungRepository koerpermessungRepository;
	private BenutzerRepository benutzerRepository;
	private KoerpermessungDtoMapper koerpermessungDtoMapper;

	@BeforeEach
	void setup()
	{
		koerpermessungRepository = mock(KoerpermessungRepository.class);
		benutzerRepository = mock(BenutzerRepository.class);
		koerpermessungDtoMapper = mock(KoerpermessungDtoMapper.class);

		sut = new KoerpermessungService(koerpermessungRepository, benutzerRepository, koerpermessungDtoMapper);
	}

	@Test
	@DisplayName("null validieren")
	void test01()
	{
		var koerpermessdaten = new Koerpermessdaten();
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> sut.findeAlleMitBenutzer(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.finde(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.erstelle(null,
				BENUTZER_DTO_JUSTIN.getId())),
			() -> assertThrows(NullPointerException.class, () -> sut.erstelle(koerpermessdaten, null)),
			() -> assertThrows(NullPointerException.class,
				() -> sut.aktualisiere(null, koerpermessdaten)),
			() -> assertThrows(NullPointerException.class, () -> sut.aktualisiere(
				KOERPERMESSUNG_DTO_JUSTIN.getId(), null)));
	}

	@Test
	@DisplayName("alle Koerpermessungen zu Benutzer ermitteln")
	void test02()
	{
		var erwartet = List.of(KOERPERMESSUNG_DTO_JUSTIN);
		var benutzerId = new ID();
		var koerpermessungen = List.of(KOERPERMESSUNG_JUSTIN);
		when(koerpermessungRepository.findeAlleMitBenutzer(new ID(benutzerId.getWert().toString()))).thenReturn(
			koerpermessungen);
		when(koerpermessungDtoMapper.mappeAlle(koerpermessungen)).thenReturn(erwartet);

		assertThat(sut.findeAlleMitBenutzer(benutzerId.getWert().toString())).isEqualTo(erwartet);
		verify(koerpermessungRepository).findeAlleMitBenutzer(benutzerId);
		verify(koerpermessungDtoMapper).mappeAlle(koerpermessungen);
	}

	@Test
	@DisplayName("KoerpermessungException werfen, wenn die KoerpermessungID nicht existiert")
	void test03()
	{
		var id = new ID();
		when(koerpermessungRepository.finde(new ID(id.getWert().toString()))).thenReturn(Optional.empty());

		assertThrows(KoerpermessungException.class, () -> sut.finde(id.getWert().toString()));
		verify(koerpermessungRepository).finde(id);
	}

	@Test
	@DisplayName("Koerpermessung zu KoerpermessungID ermitteln")
	void test04() throws KoerpermessungException
	{
		var erwartet = KOERPERMESSUNG_DTO_JUSTIN;
		var id = new ID();
		var koerpermessung = KOERPERMESSUNG_JUSTIN;
		when(koerpermessungRepository.finde(new ID(id.getWert().toString()))).thenReturn(Optional.of(koerpermessung));
		when(koerpermessungDtoMapper.mappe(any(Koerpermessung.class))).thenReturn(erwartet);

		assertThat(sut.finde(id.getWert().toString())).isEqualTo(erwartet);
		verify(koerpermessungRepository).finde(id);
		verify(koerpermessungDtoMapper).mappe(koerpermessung);
	}

	@Test
	@DisplayName("BenutzerException werfen, wenn die BenutzerID nicht existiert")
	void test05()
	{
		var benutzerId = new ID();
		when(benutzerRepository.finde(new ID(benutzerId.getWert().toString()))).thenReturn(Optional.empty());

		assertThrows(BenutzerException.class,
			() -> sut.erstelle(new Koerpermessdaten(), benutzerId.getWert().toString()));
		verify(benutzerRepository).finde(benutzerId);
	}

	@Test
	@DisplayName("eine Koerpermessung erstellen")
	void test06() throws BenutzerException
	{
		var koerpermessung = new Koerpermessung(
			new ID(),
			LocalDate.of(2020, 6, 29),
			new Koerpermasse(new BigDecimal(178), new BigDecimal(90), new BigDecimal(25)),
			2500,
			2900);
		var benutzer = new Benutzer(
			new ID(),
			new Name("Justin", "Harder"),
			LocalDate.of(2020, 12, 6),
			new Benutzerangabe(
				Geschlecht.MAENNLICH,
				Erfahrung.BEGINNER,
				Ernaehrung.GUT,
				Schlafqualitaet.GUT,
				Stress.MITTELMAESSIG,
				Doping.NEIN,
				Regenerationsfaehigkeit.PERFEKT),
			new Authentifizierung(new ID(), "mail@justinharder.de", "harder", Testdaten.PASSWORT));
		var erwartet = new KoerpermessungDto(
			koerpermessung.getId().getWert().toString(),
			LocalDate.of(2020, 7, 29).format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
			new KoerpermasseDto("178", "90.00", "25.0", "67.50", "28.4", "21.8"),
			2500,
			2900);
		when(benutzerRepository.finde(new ID(benutzer.getId().getWert().toString()))).thenReturn(Optional.of(benutzer));
		when(koerpermessungDtoMapper.mappe(any(Koerpermessung.class))).thenReturn(erwartet);

		assertThat(sut.erstelle(
			new Koerpermessdaten("2020-07-29", 178, 90, 25, 2500, 2900),
			benutzer.getId().getWert().toString()))
			.isEqualTo(erwartet);
		verify(benutzerRepository).finde(benutzer.getId());
		verify(koerpermessungRepository).speichere(any(Koerpermessung.class));
		verify(koerpermessungDtoMapper).mappe(any(Koerpermessung.class));
	}

	@Test
	@DisplayName("KoerpermessungException werfen, wenn die KoerpermessungID nicht existiert")
	void test07()
	{
		var id = new ID();

		assertThrows(KoerpermessungException.class,
			() -> sut.aktualisiere(id.getWert().toString(), new Koerpermessdaten()));
		verify(koerpermessungRepository).finde(id);
	}

	@Test
	@DisplayName("eine Koerpermessung aktualisieren")
	void test08() throws KoerpermessungException
	{
		var id = new ID();
		var erwartet = new KoerpermessungDto(
			id.getWert().toString(),
			LocalDate.of(2020, 7, 29).format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
			new KoerpermasseDto("178", "90.00", "25.0", "67.50", "28.4", "21.8"),
			2500,
			2900);
		erwartet.setKalorienverbrauch(3500);
		var koerpermessung = new Koerpermessung(
			id,
			LocalDate.of(2020, 6, 29),
			new Koerpermasse(new BigDecimal(178), new BigDecimal(90), new BigDecimal(25)),
			2500,
			2900);
		when(koerpermessungRepository.finde(new ID(id.getWert().toString()))).thenReturn(Optional.of(koerpermessung));
		when(koerpermessungDtoMapper.mappe(any(Koerpermessung.class))).thenReturn(erwartet);

		assertThat(sut.aktualisiere(id.getWert().toString(),
			new Koerpermessdaten("2020-07-29", 178, 90, 25, 2500, 3500))).isEqualTo(erwartet);
		verify(koerpermessungRepository).speichere(koerpermessung);
		verify(koerpermessungDtoMapper).mappe(koerpermessung);
	}
}
