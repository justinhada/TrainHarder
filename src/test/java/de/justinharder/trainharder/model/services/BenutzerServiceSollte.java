package de.justinharder.trainharder.model.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

public class BenutzerServiceSollte
{
	private BenutzerService sut;

	private BenutzerRepository benutzerRepository;
	private AuthentifizierungRepository authentifizierungRepository;
	private BenutzerDtoMapper benutzerDtoMapper;

	@BeforeEach
	public void setup()
	{
		benutzerRepository = mock(BenutzerRepository.class);
		authentifizierungRepository = mock(AuthentifizierungRepository.class);
		benutzerDtoMapper = mock(BenutzerDtoMapper.class);
		sut = new BenutzerService(benutzerRepository, authentifizierungRepository, benutzerDtoMapper);
	}

	@AfterEach
	public void reset()
	{
		Testdaten.BENUTZER_JUSTIN.setBenutzerangabe(Testdaten.BENUTZERANGABE_JUSTIN);
	}

	private void angenommenDasBenutzerRepositoryErmitteltAlle(final List<Benutzer> benutzer)
	{
		when(benutzerRepository.ermittleAlle()).thenReturn(benutzer);
	}

	private void angenommenDasBenutzerRepositoryErmitteltBenutzerZuId(final String id,
		final Optional<Benutzer> benutzer)
	{
		when(benutzerRepository.ermittleZuId(new Primaerschluessel(id))).thenReturn(benutzer);
	}

	private void angenommenDasBenutzerRepositoryErmitteltKeinenBenutzerZuId(final String id)
	{
		angenommenDasBenutzerRepositoryErmitteltBenutzerZuId(id, Optional.empty());
	}

	private void angenommenDasAuthentifizierungRepositoryErmitteltAuthentifizierungZuId(
		final String authentifizierungId, final Optional<Authentifizierung> authentifizierung)
	{
		when(authentifizierungRepository.ermittleZuId(new Primaerschluessel(authentifizierungId)))
			.thenReturn(authentifizierung);
	}

	private void angenommenDasAuthentifizierungRepositoryErmitteltKeineAuthentifizierung(
		final String authentifizierungId)
	{
		angenommenDasAuthentifizierungRepositoryErmitteltAuthentifizierungZuId(authentifizierungId, Optional.empty());
	}

	private void angenommenDasBenutzerRepositorySpeichertBenutzer(final Benutzer benutzer)
	{
		when(benutzerRepository.speichereBenutzer(any(Benutzer.class))).thenReturn(benutzer);
	}

	private void angenommenDerBenutzerDtoMapperKonvertiertZuBenutzerDto(final Benutzer benutzer,
		final BenutzerDto benutzerDto)
	{
		when(benutzerDtoMapper.konvertiere(benutzer)).thenReturn(benutzerDto);
	}

	private void angenommenDasBenutzerRepositoryGibtBenutzerZuAuthentifizierungZurueck(final String authentifizierungId,
		final Optional<Benutzer> benutzer)
	{
		when(benutzerRepository.ermittleZuAuthentifizierung(new Primaerschluessel(authentifizierungId)))
			.thenReturn(benutzer);
	}

	private void angenommenDasBenutzerRepositoryGibtKeinenBenutzerZurAuthentifizierungZurueck(
		final String authentifizierungId)
	{
		angenommenDasBenutzerRepositoryGibtBenutzerZuAuthentifizierungZurueck(authentifizierungId, Optional.empty());
	}

	private void angenommenDerBenutzerDtoMapperKonvertiertAlleZuBenutzerDtos(final List<Benutzer> benutzer,
		final List<BenutzerDto> benutzerDtos)
	{
		when(benutzerDtoMapper.konvertiereAlle(benutzer)).thenReturn(benutzerDtos);
	}

	@Test
	@DisplayName("alle Benutzer ermitteln")
	public void test01()
	{
		final var erwartet = List.of(Testdaten.BENUTZER_DTO_JUSTIN, Testdaten.BENUTZER_DTO_EDUARD);
		final var benutzer = List.of(Testdaten.BENUTZER_JUSTIN, Testdaten.BENUTZER_EDUARD);
		angenommenDasBenutzerRepositoryErmitteltAlle(benutzer);
		angenommenDerBenutzerDtoMapperKonvertiertAlleZuBenutzerDtos(benutzer, erwartet);

		final var ergebnis = sut.ermittleAlle();

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("NullPointerException werfen, wenn die BenutzerID null ist")
	public void test02()
	{
		final var erwartet = "Die Ermittlung des Benutzers benötigt eine gültige BenutzerID!";

		final var exception = assertThrows(NullPointerException.class, () -> sut.ermittleZuId(null));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("BenutzerNichtGefundenException werfen, wenn kein Benutzer zu ID ermittelt wird")
	public void test03()
	{
		final var id = new Primaerschluessel().getId().toString();
		angenommenDasBenutzerRepositoryErmitteltKeinenBenutzerZuId(id);

		final var exception = assertThrows(BenutzerNichtGefundenException.class, () -> sut.ermittleZuId(id));

		assertThat(exception.getMessage()).isEqualTo("Der Benutzer mit der ID \"" + id + "\" existiert nicht!");
		verify(benutzerRepository).ermittleZuId(new Primaerschluessel(id));
	}

	@Test
	@DisplayName("einen Benutzer zu ID ermitteln")
	public void test04() throws BenutzerNichtGefundenException
	{
		final var erwartet = Testdaten.BENUTZER_DTO_JUSTIN;
		final var id = Testdaten.BENUTZER_JUSTIN_ID.getId().toString();
		final var benutzer = Testdaten.BENUTZER_JUSTIN;
		angenommenDasBenutzerRepositoryErmitteltBenutzerZuId(id, Optional.of(benutzer));
		angenommenDerBenutzerDtoMapperKonvertiertZuBenutzerDto(benutzer, erwartet);

		final var ergebnis = sut.ermittleZuId(id);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(benutzerRepository).ermittleZuId(new Primaerschluessel(id));
		verify(benutzerDtoMapper).konvertiere(benutzer);
	}

	@Test
	@DisplayName("NullPointerException werfen, wenn die AuthentifizierungID null ist")
	public void test05()
	{
		final var erwartet = "Die Ermittlung des Benutzers benötigt eine gültige AuthentifizierungID!";

		final var exception = assertThrows(NullPointerException.class, () -> sut.ermittleZuAuthentifizierung(null));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("BenutzerNichtGefundenException werfen, wenn kein Benutzer zur Authentifizierung ermittelt werden kann")
	public void test06() throws BenutzerNichtGefundenException
	{
		final var authentifizierungId = new Primaerschluessel().getId().toString();
		final var erwartet =
			"Der Benutzer mit der AuthentifizierungID \"" + authentifizierungId + "\" existiert nicht!";
		angenommenDasBenutzerRepositoryGibtKeinenBenutzerZurAuthentifizierungZurueck(authentifizierungId);

		final var exception = assertThrows(BenutzerNichtGefundenException.class,
			() -> sut.ermittleZuAuthentifizierung(authentifizierungId));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
		verify(benutzerRepository).ermittleZuAuthentifizierung(new Primaerschluessel(authentifizierungId));
	}

	@Test
	@DisplayName("einen Benutzer zur AuthentifizierungID ermitteln")
	public void test07() throws BenutzerNichtGefundenException
	{
		final var erwartet = Testdaten.BENUTZER_DTO_JUSTIN;
		final var authentifizierungId = Testdaten.AUTHENTIFIZIERUNG_JUSTIN_ID.getId().toString();
		final var benutzer = Testdaten.BENUTZER_JUSTIN;
		angenommenDasBenutzerRepositoryGibtBenutzerZuAuthentifizierungZurueck(authentifizierungId,
			Optional.of(benutzer));
		angenommenDerBenutzerDtoMapperKonvertiertZuBenutzerDto(benutzer, erwartet);

		final var ergebnis = sut.ermittleZuAuthentifizierung(authentifizierungId);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(benutzerRepository).ermittleZuAuthentifizierung(new Primaerschluessel(authentifizierungId));
		verify(benutzerDtoMapper).konvertiere(benutzer);
	}

	@Test
	@DisplayName("NullPointerException werfen, wenn die Benutzerdaten null sind")
	public void test08()
	{
		final var erwartet = "Die Erstellung des Benutzers benötigt gültige Benutzerdaten!";

		final var exception = assertThrows(NullPointerException.class, () -> sut.erstelleBenutzer(null, null));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("NullPointerException werfen, wenn die AuthentifizierungID null sind")
	public void test09()
	{
		final var erwartet = "Die Erstellung des Benutzers benötigt eine gültige AuthentifizierungID!";

		final var exception =
			assertThrows(NullPointerException.class, () -> sut.erstelleBenutzer(new Benutzerdaten(), null));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("AuthentifizierungNichtGefundenException werfen, wenn die AuthentifizierungID nicht existiert")
	public void test10()
	{
		final var authentifizierungId = Testdaten.AUTHENTIFIZIERUNG_JUSTIN_ID.getId().toString();
		final var erwartet = "Die Authentifizierung mit der ID \"" + authentifizierungId + "\" existiert nicht!";
		angenommenDasAuthentifizierungRepositoryErmitteltKeineAuthentifizierung(authentifizierungId);

		final var exception = assertThrows(AuthentifizierungNichtGefundenException.class,
			() -> sut.erstelleBenutzer(new Benutzerdaten(), authentifizierungId));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
		verify(authentifizierungRepository).ermittleZuId(new Primaerschluessel(authentifizierungId));
	}

	@Test
	@DisplayName("einen neuen Benutzer erstellen")
	public void test11() throws AuthentifizierungNichtGefundenException
	{
		final var erwartet = Testdaten.BENUTZER_DTO_JUSTIN;
		final var benutzer = Testdaten.BENUTZER_JUSTIN;
		final var benutzerdaten = new Benutzerdaten(
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
		final var authentifizierungId = Testdaten.AUTHENTIFIZIERUNG_JUSTIN_ID.getId().toString();
		final var authentifizierung = Testdaten.AUTHENTIFIZIERUNG_JUSTIN;
		angenommenDasAuthentifizierungRepositoryErmitteltAuthentifizierungZuId(authentifizierungId,
			Optional.of(authentifizierung));
		angenommenDasBenutzerRepositorySpeichertBenutzer(benutzer);
		angenommenDerBenutzerDtoMapperKonvertiertZuBenutzerDto(benutzer, erwartet);

		final var ergebnis = sut.erstelleBenutzer(benutzerdaten, authentifizierungId);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(authentifizierungRepository).ermittleZuId(new Primaerschluessel(authentifizierungId));
		verify(authentifizierungRepository).speichereAuthentifizierung(authentifizierung);
		verify(benutzerDtoMapper).konvertiere(benutzer);
	}

	@Test
	@DisplayName("NullPointerException werfen, wenn die BenutzerID null ist")
	public void test12()
	{
		final var erwartet = "Die Aktualisierung des Benutzers benötigt eine gültige ID!";

		final var exception =
			assertThrows(NullPointerException.class, () -> sut.aktualisiereBenutzer(null, null));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("NullPointerException werfen, wenn die Benutzerdaten null sind")
	public void test13()
	{
		final var erwartet = "Die Aktualisierung des Benutzers benötigt gültige Benutzerdaten!";

		final var exception = assertThrows(NullPointerException.class,
			() -> sut.aktualisiereBenutzer(Testdaten.BENUTZER_JUSTIN_ID.getId().toString(), null));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("BenutzerNichtGefundenException werfen, wenn die BenutzerID nicht existiert")
	public void test14()
	{
		final var id = Testdaten.BENUTZER_JUSTIN_ID.getId().toString();
		final var erwartet = "Der Benutzer mit der ID \"" + id + "\" existiert nicht!";
		angenommenDasBenutzerRepositoryErmitteltKeinenBenutzerZuId(id);

		final var exception = assertThrows(BenutzerNichtGefundenException.class,
			() -> sut.aktualisiereBenutzer(id, new Benutzerdaten()));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
		verify(benutzerRepository).ermittleZuId(new Primaerschluessel(id));
	}

	@Test
	@DisplayName("einen Benutzer aktualisieren")
	public void test15() throws BenutzerNichtGefundenException
	{
		final var benutzer = Testdaten.BENUTZER_JUSTIN;
		final var id = benutzer.getPrimaerschluessel().getId().toString();
		final var erwartet = new BenutzerDto(
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
		final var benutzerdaten = new Benutzerdaten(
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
		angenommenDerBenutzerDtoMapperKonvertiertZuBenutzerDto(benutzer, erwartet);

		final var ergebnis = sut.aktualisiereBenutzer(id, benutzerdaten);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(benutzerRepository).ermittleZuId(new Primaerschluessel(id));
		verify(benutzerRepository).speichereBenutzer(benutzer);
		verify(benutzerDtoMapper).konvertiere(benutzer);
	}
}
