package de.justinharder.trainharder.model.services;

import de.justinharder.trainharder.model.domain.Authentifizierung;
import de.justinharder.trainharder.model.domain.Benutzer;
import de.justinharder.trainharder.model.domain.Koerpermessung;
import de.justinharder.trainharder.model.domain.embeddables.Benutzerangabe;
import de.justinharder.trainharder.model.domain.embeddables.Koerpermasse;
import de.justinharder.trainharder.model.domain.embeddables.Name;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.model.domain.enums.*;
import de.justinharder.trainharder.model.domain.exceptions.BenutzerNichtGefundenException;
import de.justinharder.trainharder.model.domain.exceptions.KoerpermessungNichtGefundenException;
import de.justinharder.trainharder.model.repository.BenutzerRepository;
import de.justinharder.trainharder.model.repository.KoerpermessungRepository;
import de.justinharder.trainharder.model.services.mapper.KoerpermessungDtoMapper;
import de.justinharder.trainharder.setup.Testdaten;
import de.justinharder.trainharder.view.dto.Koerpermessdaten;
import de.justinharder.trainharder.view.dto.KoerpermessungDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class KoerpermessungServiceSollte
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

	private void angenommenDasKoerpermessungRepositoryErmitteltAlleKoerpermessungenZuBenutzer(String benutzerId,
		List<Koerpermessung> koerpermessungen)
	{
		when(koerpermessungRepository.ermittleAlleZuBenutzer(new Primaerschluessel(benutzerId)))
			.thenReturn(koerpermessungen);
	}

	private void angenommenDasKoerpermessungRepositoryErmitteltKoerpermessungZuId(String id,
		Optional<Koerpermessung> koerpermessung)
	{
		when(koerpermessungRepository.ermittleZuId(new Primaerschluessel(id))).thenReturn(koerpermessung);
	}

	private void angenommenDasKoerpermessungRepositoryErmitteltKeineKoerpermessungZuId(String id)
	{
		angenommenDasKoerpermessungRepositoryErmitteltKoerpermessungZuId(id, Optional.empty());
	}

	private void angenommenDasBenutzerRepositoryErmitteltBenutzerZuId(String benutzerId, Optional<Benutzer> benutzer)
	{
		when(benutzerRepository.ermittleZuId(new Primaerschluessel(benutzerId))).thenReturn(benutzer);
	}

	private void angenommenDasBenutzerRepositoryErmitteltKeinenBenutzerZuId(String benutzerId)
	{
		angenommenDasBenutzerRepositoryErmitteltBenutzerZuId(benutzerId, Optional.empty());
	}

	private void angenommenDasKoerpermessungRepositorySpeichertKoerpermessung(Koerpermessung koerpermessung)
	{
		when(koerpermessungRepository.speichereKoerpermessung(any(Koerpermessung.class))).thenReturn(koerpermessung);
	}

	private void angenommenDerKoerpermessungDtoMapperMapptAlleZuKoerpermessungDtos(
		List<Koerpermessung> koerpermessungen, List<KoerpermessungDto> koerpermessungDtos)
	{
		when(koerpermessungDtoMapper.mappeAlle(koerpermessungen)).thenReturn(koerpermessungDtos);
	}

	private void angenommenDerKoerpermessungDtoMapperMapptZuKoerpermessungDto(Koerpermessung koerpermessung,
		KoerpermessungDto koerpermessungDto)
	{
		when(koerpermessungDtoMapper.mappe(koerpermessung)).thenReturn(koerpermessungDto);
	}

	@Test
	@DisplayName("NullPointerException werfen, wenn die BenutzerID null ist")
	void test01()
	{
		var erwartet = "Die Ermittlung der Koerpermessungen benötigt eine gültige BenutzerID!";

		var exception = assertThrows(NullPointerException.class, () -> sut.ermittleAlleZuBenutzer(null));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("alle Koerpermessungen zu Benutzer ermitteln")
	void test02()
	{
		var erwartet = List.of(Testdaten.KOERPERMESSUNG_DTO_JUSTIN);
		var benutzerId = Testdaten.BENUTZER_JUSTIN_ID;
		var koerpermessungen = List.of(Testdaten.KOERPERMESSUNG_JUSTIN);
		angenommenDasKoerpermessungRepositoryErmitteltAlleKoerpermessungenZuBenutzer(benutzerId.getId().toString(),
			koerpermessungen);
		angenommenDerKoerpermessungDtoMapperMapptAlleZuKoerpermessungDtos(koerpermessungen, erwartet);

		var ergebnis = sut.ermittleAlleZuBenutzer(benutzerId.getId().toString());

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(koerpermessungRepository).ermittleAlleZuBenutzer(benutzerId);
		verify(koerpermessungDtoMapper).mappeAlle(koerpermessungen);
	}

	@Test
	@DisplayName("NullPointerException werfen, wenn die KoerpermessungID null ist")
	void test03()
	{
		var erwartet = "Die Ermittlung der Koerpermessung benötigt eine gültige ID!";

		var exception = assertThrows(NullPointerException.class, () -> sut.ermittleZuId(null));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("KoerpermessungNichtGefundenException werfen, wenn die KoerpermessungID nicht existiert")
	void test04()
	{
		var id = new Primaerschluessel();
		var erwartet = "Die Koerpermessung mit der ID \"" + id.getId().toString() + "\" existiert nicht!";
		angenommenDasKoerpermessungRepositoryErmitteltKeineKoerpermessungZuId(id.getId().toString());

		var exception =
			assertThrows(KoerpermessungNichtGefundenException.class, () -> sut.ermittleZuId(id.getId().toString()));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
		verify(koerpermessungRepository).ermittleZuId(id);
	}

	@Test
	@DisplayName("Koerpermessung zu KoerpermessungID ermitteln")
	void test05() throws KoerpermessungNichtGefundenException
	{
		var erwartet = Testdaten.KOERPERMESSUNG_DTO_JUSTIN;
		var id = Testdaten.KOERPERMESSUNG_JUSTIN_ID;
		var koerpermessung = Testdaten.KOERPERMESSUNG_JUSTIN;
		angenommenDasKoerpermessungRepositoryErmitteltKoerpermessungZuId(id.getId().toString(),
			Optional.of(koerpermessung));
		angenommenDerKoerpermessungDtoMapperMapptZuKoerpermessungDto(koerpermessung, erwartet);

		var ergebnis = sut.ermittleZuId(id.getId().toString());

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(koerpermessungRepository).ermittleZuId(id);
		verify(koerpermessungDtoMapper).mappe(koerpermessung);
	}

	@Test
	@DisplayName("NullPointerException werfen, wenn die Koerpermessdaten null sind")
	void test06()
	{
		var erwartet = "Die Erstellung der Koerpermessung benötigt gültige Koerpermessdaten!";

		var exception = assertThrows(NullPointerException.class, () -> sut.erstelleKoerpermessung(null, null));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("NullPointerException werfen, wenn die BenutzerID null ist")
	void test07()
	{
		var erwartet = "Die Erstellung der Koerpermessungen benötigt eine gültige BenutzerID!";

		var koerpermessdaten = new Koerpermessdaten();
		var exception =
			assertThrows(NullPointerException.class, () -> sut.erstelleKoerpermessung(koerpermessdaten, null));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("BenutzerNichtGefundenException werfen, wenn die BenutzerID nicht existiert")
	void test08()
	{
		var benutzerId = new Primaerschluessel();
		var erwartet = "Der Benutzer mit der ID \"" + benutzerId.getId().toString() + "\" existiert nicht!";
		angenommenDasBenutzerRepositoryErmitteltKeinenBenutzerZuId(benutzerId.getId().toString());

		var exception = assertThrows(BenutzerNichtGefundenException.class,
			() -> sut.erstelleKoerpermessung(new Koerpermessdaten(), benutzerId.getId().toString()));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
		verify(benutzerRepository).ermittleZuId(benutzerId);
	}

	@Test
	@DisplayName("eine Koerpermessung erstellen")
	void test09() throws BenutzerNichtGefundenException
	{
		var id = new Primaerschluessel();
		var erwartet = new KoerpermessungDto(
			id.getId().toString(),
			LocalDate.of(2020, 7, 29).format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
			178,
			90,
			25,
			2500,
			2900);
		var koerpermessung = new Koerpermessung(
			id,
			LocalDate.of(2020, 6, 29),
			new Koerpermasse(178, 90, 25),
			2500,
			2900,
			new Benutzer());
		var benutzerId = new Primaerschluessel();
		var benutzer = new Benutzer(
			benutzerId,
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
			new Authentifizierung(new Primaerschluessel(), "mail@justinharder.de", "harder", Testdaten.PASSWORT));
		angenommenDasBenutzerRepositoryErmitteltBenutzerZuId(benutzerId.getId().toString(), Optional.of(benutzer));
		angenommenDasKoerpermessungRepositorySpeichertKoerpermessung(koerpermessung);
		angenommenDerKoerpermessungDtoMapperMapptZuKoerpermessungDto(koerpermessung, erwartet);

		var koerpermessdaten = new Koerpermessdaten("2020-07-29", 178, 90, 25, 2500, 2900);
		var ergebnis = sut.erstelleKoerpermessung(koerpermessdaten, benutzerId.getId().toString());

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(benutzerRepository).ermittleZuId(benutzerId);
		verify(koerpermessungDtoMapper).mappe(koerpermessung);
	}

	@Test
	@DisplayName("NullPointerException werfen, wenn die KoerpermessungID null ist")
	void test10()
	{
		var erwartet = "Die Aktualisierung der Koerpermessungen benötigt eine gültige ID!";

		var exception =
			assertThrows(NullPointerException.class, () -> sut.aktualisiereKoerpermessung(null, null));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("NullPointerException werfen, wenn die Koerpermessdaten null sind")
	void test11()
	{
		var erwartet = "Die Aktualisierung der Koerpermessung benötigt gültige Koerpermessdaten!";

		var primaerschluessel = new Primaerschluessel().getId().toString();
		var exception = assertThrows(NullPointerException.class,
			() -> sut.aktualisiereKoerpermessung(primaerschluessel, null));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("KoerpermessungNichtGefundenException werfen, wenn die KoerpermessungID nicht existiert")
	void test12()
	{
		var id = new Primaerschluessel();
		var erwartet = "Die Koerpermessung mit der ID \"" + id.getId().toString() + "\" existiert nicht!";

		var exception = assertThrows(KoerpermessungNichtGefundenException.class,
			() -> sut.aktualisiereKoerpermessung(id.getId().toString(), new Koerpermessdaten()));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
		verify(koerpermessungRepository).ermittleZuId(id);
	}

	@Test
	@DisplayName("eine Koerpermessung aktualisieren")
	void test13() throws KoerpermessungNichtGefundenException
	{
		var id = new Primaerschluessel();
		var erwartet = new KoerpermessungDto(
			id.getId().toString(),
			LocalDate.of(2020, 7, 29).format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
			178,
			90,
			25,
			2500,
			2900);
		erwartet.setKalorienverbrauch(3500);
		var koerpermessung = new Koerpermessung(
			id,
			LocalDate.of(2020, 6, 29),
			new Koerpermasse(178, 90, 25),
			2500,
			2900,
			new Benutzer());
		angenommenDasKoerpermessungRepositoryErmitteltKoerpermessungZuId(
			id.getId().toString(),
			Optional.of(koerpermessung));
		angenommenDasKoerpermessungRepositorySpeichertKoerpermessung(koerpermessung);
		angenommenDerKoerpermessungDtoMapperMapptZuKoerpermessungDto(koerpermessung, erwartet);

		var koerpermessdaten = new Koerpermessdaten("2020-07-29", 178, 90, 25, 2500, 3500);
		var ergebnis = sut.aktualisiereKoerpermessung(id.getId().toString(), koerpermessdaten);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(koerpermessungRepository).speichereKoerpermessung(koerpermessung);
		verify(koerpermessungDtoMapper).mappe(koerpermessung);
	}
}
