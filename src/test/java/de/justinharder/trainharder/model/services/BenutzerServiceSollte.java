package de.justinharder.trainharder.model.services;

import de.justinharder.trainharder.model.domain.Authentifizierung;
import de.justinharder.trainharder.model.domain.Benutzer;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.model.domain.exceptions.AuthentifizierungNichtGefundenException;
import de.justinharder.trainharder.model.domain.exceptions.BenutzerNichtGefundenException;
import de.justinharder.trainharder.model.repository.AuthentifizierungRepository;
import de.justinharder.trainharder.model.repository.BenutzerRepository;
import de.justinharder.trainharder.model.services.mapper.BenutzerDtoMapper;
import de.justinharder.trainharder.setup.Testdaten;
import de.justinharder.trainharder.view.dto.BenutzerDto;
import de.justinharder.trainharder.view.dto.Benutzerdaten;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BenutzerServiceSollte
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
		Testdaten.BENUTZER_JUSTIN.setBenutzerangabe(Testdaten.BENUTZERANGABE_JUSTIN);
	}

	private void angenommenDasBenutzerRepositoryErmitteltAlle(List<Benutzer> benutzer)
	{
		when(benutzerRepository.ermittleAlle()).thenReturn(benutzer);
	}

	private void angenommenDasBenutzerRepositoryErmitteltBenutzerZuId(String id, Optional<Benutzer> benutzer)
	{
		when(benutzerRepository.ermittleZuId(new Primaerschluessel(id))).thenReturn(benutzer);
	}

	private void angenommenDasBenutzerRepositoryErmitteltKeinenBenutzerZuId(String id)
	{
		angenommenDasBenutzerRepositoryErmitteltBenutzerZuId(id, Optional.empty());
	}

	private void angenommenDasAuthentifizierungRepositoryErmitteltAuthentifizierungZuId(String authentifizierungId,
		Optional<Authentifizierung> authentifizierung)
	{
		when(authentifizierungRepository.ermittleZuId(new Primaerschluessel(authentifizierungId)))
			.thenReturn(authentifizierung);
	}

	private void angenommenDasAuthentifizierungRepositoryErmitteltKeineAuthentifizierung(String authentifizierungId)
	{
		angenommenDasAuthentifizierungRepositoryErmitteltAuthentifizierungZuId(authentifizierungId, Optional.empty());
	}

	private void angenommenDasBenutzerRepositorySpeichertBenutzer(Benutzer benutzer)
	{
		when(benutzerRepository.speichereBenutzer(any(Benutzer.class))).thenReturn(benutzer);
	}

	private void angenommenDerBenutzerDtoMapperMapptZuBenutzerDto(Benutzer benutzer, BenutzerDto benutzerDto)
	{
		when(benutzerDtoMapper.mappe(benutzer)).thenReturn(benutzerDto);
	}

	private void angenommenDasBenutzerRepositoryGibtBenutzerZuAuthentifizierungZurueck(String authentifizierungId,
		Optional<Benutzer> benutzer)
	{
		when(benutzerRepository.ermittleZuAuthentifizierung(new Primaerschluessel(authentifizierungId)))
			.thenReturn(benutzer);
	}

	private void angenommenDasBenutzerRepositoryGibtKeinenBenutzerZurAuthentifizierungZurueck(
		String authentifizierungId)
	{
		angenommenDasBenutzerRepositoryGibtBenutzerZuAuthentifizierungZurueck(authentifizierungId, Optional.empty());
	}

	private void angenommenDerBenutzerDtoMapperMapptAlleZuBenutzerDtos(List<Benutzer> benutzer,
		List<BenutzerDto> benutzerDtos)
	{
		when(benutzerDtoMapper.mappeAlle(benutzer)).thenReturn(benutzerDtos);
	}

	@Test
	@DisplayName("alle Benutzer ermitteln")
	void test01()
	{
		var erwartet = List.of(Testdaten.BENUTZER_DTO_JUSTIN, Testdaten.BENUTZER_DTO_EDUARD);
		var benutzer = List.of(Testdaten.BENUTZER_JUSTIN, Testdaten.BENUTZER_EDUARD);
		angenommenDasBenutzerRepositoryErmitteltAlle(benutzer);
		angenommenDerBenutzerDtoMapperMapptAlleZuBenutzerDtos(benutzer, erwartet);

		var ergebnis = sut.ermittleAlle();

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("null validieren")
	void test02()
	{
		var benutzerdaten = new Benutzerdaten();
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> sut.ermittleZuId(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.ermittleZuAuthentifizierung(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.erstelleBenutzer(null, "authenfitizierungId")),
			() -> assertThrows(NullPointerException.class, () -> sut.erstelleBenutzer(benutzerdaten, null)),
			() -> assertThrows(NullPointerException.class, () -> sut.aktualisiereBenutzer(null, benutzerdaten)),
			() -> assertThrows(NullPointerException.class, () -> sut.aktualisiereBenutzer("id", null)));
	}

	@Test
	@DisplayName("BenutzerNichtGefundenException werfen, wenn kein Benutzer zu ID ermittelt wird")
	void test03()
	{
		var id = new Primaerschluessel().getId().toString();
		angenommenDasBenutzerRepositoryErmitteltKeinenBenutzerZuId(id);

		var exception = assertThrows(BenutzerNichtGefundenException.class, () -> sut.ermittleZuId(id));

		assertThat(exception.getMessage()).isEqualTo("Der Benutzer mit der ID \"" + id + "\" existiert nicht!");
		verify(benutzerRepository).ermittleZuId(new Primaerschluessel(id));
	}

	@Test
	@DisplayName("einen Benutzer zu ID ermitteln")
	void test04() throws BenutzerNichtGefundenException
	{
		var erwartet = Testdaten.BENUTZER_DTO_JUSTIN;
		var id = Testdaten.BENUTZER_JUSTIN_ID.getId().toString();
		var benutzer = Testdaten.BENUTZER_JUSTIN;
		angenommenDasBenutzerRepositoryErmitteltBenutzerZuId(id, Optional.of(benutzer));
		angenommenDerBenutzerDtoMapperMapptZuBenutzerDto(benutzer, erwartet);

		var ergebnis = sut.ermittleZuId(id);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(benutzerRepository).ermittleZuId(new Primaerschluessel(id));
		verify(benutzerDtoMapper).mappe(benutzer);
	}

	@Test
	@DisplayName("BenutzerNichtGefundenException werfen, wenn kein Benutzer zur Authentifizierung ermittelt werden kann")
	void test05()
	{
		var authentifizierungId = new Primaerschluessel().getId().toString();
		var erwartet = "Der Benutzer mit der AuthentifizierungID \"" + authentifizierungId + "\" existiert nicht!";
		angenommenDasBenutzerRepositoryGibtKeinenBenutzerZurAuthentifizierungZurueck(authentifizierungId);

		var exception = assertThrows(BenutzerNichtGefundenException.class,
			() -> sut.ermittleZuAuthentifizierung(authentifizierungId));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
		verify(benutzerRepository).ermittleZuAuthentifizierung(new Primaerschluessel(authentifizierungId));
	}

	@Test
	@DisplayName("einen Benutzer zur AuthentifizierungID ermitteln")
	void test06() throws BenutzerNichtGefundenException
	{
		var erwartet = Testdaten.BENUTZER_DTO_JUSTIN;
		var authentifizierungId = Testdaten.AUTHENTIFIZIERUNG_JUSTIN_ID.getId().toString();
		var benutzer = Testdaten.BENUTZER_JUSTIN;
		angenommenDasBenutzerRepositoryGibtBenutzerZuAuthentifizierungZurueck(authentifizierungId,
			Optional.of(benutzer));
		angenommenDerBenutzerDtoMapperMapptZuBenutzerDto(benutzer, erwartet);

		var ergebnis = sut.ermittleZuAuthentifizierung(authentifizierungId);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(benutzerRepository).ermittleZuAuthentifizierung(new Primaerschluessel(authentifizierungId));
		verify(benutzerDtoMapper).mappe(benutzer);
	}

	@Test
	@DisplayName("AuthentifizierungNichtGefundenException werfen, wenn die AuthentifizierungID nicht existiert")
	void test07()
	{
		var authentifizierungId = Testdaten.AUTHENTIFIZIERUNG_JUSTIN_ID.getId().toString();
		var erwartet = "Die Authentifizierung mit der ID \"" + authentifizierungId + "\" existiert nicht!";
		angenommenDasAuthentifizierungRepositoryErmitteltKeineAuthentifizierung(authentifizierungId);

		var exception = assertThrows(AuthentifizierungNichtGefundenException.class,
			() -> sut.erstelleBenutzer(new Benutzerdaten(), authentifizierungId));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
		verify(authentifizierungRepository).ermittleZuId(new Primaerschluessel(authentifizierungId));
	}

	@Test
	@DisplayName("einen neuen Benutzer erstellen")
	void test08() throws AuthentifizierungNichtGefundenException
	{
		var erwartet = Testdaten.BENUTZER_DTO_JUSTIN;
		var benutzer = Testdaten.BENUTZER_JUSTIN;
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
		var authentifizierungId = Testdaten.AUTHENTIFIZIERUNG_JUSTIN_ID.getId().toString();
		var authentifizierung = Testdaten.AUTHENTIFIZIERUNG_JUSTIN;
		angenommenDasAuthentifizierungRepositoryErmitteltAuthentifizierungZuId(authentifizierungId,
			Optional.of(authentifizierung));
		angenommenDasBenutzerRepositorySpeichertBenutzer(benutzer);
		angenommenDerBenutzerDtoMapperMapptZuBenutzerDto(benutzer, erwartet);

		var ergebnis = sut.erstelleBenutzer(benutzerdaten, authentifizierungId);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(authentifizierungRepository).ermittleZuId(new Primaerschluessel(authentifizierungId));
		verify(authentifizierungRepository).speichereAuthentifizierung(authentifizierung);
		verify(benutzerDtoMapper).mappe(benutzer);
	}

	@Test
	@DisplayName("BenutzerNichtGefundenException werfen, wenn die BenutzerID nicht existiert")
	void test09()
	{
		var id = Testdaten.BENUTZER_JUSTIN_ID.getId().toString();
		var erwartet = "Der Benutzer mit der ID \"" + id + "\" existiert nicht!";
		angenommenDasBenutzerRepositoryErmitteltKeinenBenutzerZuId(id);

		var exception = assertThrows(BenutzerNichtGefundenException.class,
			() -> sut.aktualisiereBenutzer(id, new Benutzerdaten()));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
		verify(benutzerRepository).ermittleZuId(new Primaerschluessel(id));
	}

	@Test
	@DisplayName("einen Benutzer aktualisieren")
	void test10() throws BenutzerNichtGefundenException
	{
		var benutzer = Testdaten.BENUTZER_JUSTIN;
		var id = benutzer.getPrimaerschluessel().getId().toString();
		var erwartet = new BenutzerDto(
			id,
			"Justin",
			"Harder",
			LocalDate.of(1998, 12, 6),
			"CLASS_5",
			"MAENNLICH",
			"FORTGESCHRITTEN",
			"GUT",
			"GUT",
			"MITTELMAESSIG",
			"NEIN",
			"GUT",
			Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN,
			List.of(Testdaten.KOERPERMESSUNG_DTO_JUSTIN));
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
		angenommenDasBenutzerRepositoryErmitteltBenutzerZuId(id, Optional.of(benutzer));
		angenommenDasBenutzerRepositorySpeichertBenutzer(benutzer);
		angenommenDerBenutzerDtoMapperMapptZuBenutzerDto(benutzer, erwartet);

		var ergebnis = sut.aktualisiereBenutzer(id, benutzerdaten);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(benutzerRepository).ermittleZuId(new Primaerschluessel(id));
		verify(benutzerRepository).speichereBenutzer(benutzer);
		verify(benutzerDtoMapper).mappe(benutzer);
	}
}
