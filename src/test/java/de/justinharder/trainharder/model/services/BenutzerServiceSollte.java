package de.justinharder.trainharder.model.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.trainharder.model.domain.Authentifizierung;
import de.justinharder.trainharder.model.domain.Benutzer;
import de.justinharder.trainharder.model.domain.Primaerschluessel;
import de.justinharder.trainharder.model.domain.exceptions.AuthentifizierungNichtGefundenException;
import de.justinharder.trainharder.model.domain.exceptions.BenutzerNichtGefundenException;
import de.justinharder.trainharder.model.repository.AuthentifizierungRepository;
import de.justinharder.trainharder.model.repository.BenutzerRepository;
import de.justinharder.trainharder.setup.Testdaten;

public class BenutzerServiceSollte
{
	private BenutzerService sut;
	private BenutzerRepository benutzerRepository;
	private AuthentifizierungRepository authentifizierungRepository;

	@BeforeEach
	public void setup()
	{
		benutzerRepository = mock(BenutzerRepository.class);
		authentifizierungRepository = mock(AuthentifizierungRepository.class);
		sut = new BenutzerService(benutzerRepository, authentifizierungRepository);
	}

	private void angenommenDasBenutzerRepositoryGibtAlleBenutzerZurueck(final List<Benutzer> alleBenutzer)
	{
		when(benutzerRepository.ermittleAlle()).thenReturn(alleBenutzer);
	}

	private void angenommenDasBenutzerRepositoryGibtEinenBenutzerZurueck(final Optional<Benutzer> benutzer)
	{
		when(benutzerRepository.ermittleZuId(any(Primaerschluessel.class))).thenReturn(benutzer);
	}

	private void angenommenDasBenutzerRepositoryGibtKeinenBenutzerZurueck()
	{
		angenommenDasBenutzerRepositoryGibtEinenBenutzerZurueck(Optional.empty());
	}

	private void angenommenDasBenutzerRepositoryGibtAlleBenutzerZuNachnameZurueck(final List<Benutzer> alleBenutzer)
		throws BenutzerNichtGefundenException
	{
		when(benutzerRepository.ermittleAlleZuNachname(anyString())).thenReturn(alleBenutzer);
	}

	private void angenommenDasBenutzerRepositoryGibtNullZuNachnamenZurueck() throws BenutzerNichtGefundenException
	{
		angenommenDasBenutzerRepositoryGibtAlleBenutzerZuNachnameZurueck(null);
	}

	private void angenommenDasAuthentifizierungRepositoryErmitteltAuthentifizierungZuId(
		final Optional<Authentifizierung> authentifizierung)
	{
		when(authentifizierungRepository.ermittleZuId(any(Primaerschluessel.class))).thenReturn(authentifizierung);
	}

	private void angenommenDasAuthentifizierungRepositoryErmitteltKeineAuthentifizierung()
	{
		angenommenDasAuthentifizierungRepositoryErmitteltAuthentifizierungZuId(Optional.empty());
	}

	private void angenommenDasBenutzerRepositorySpeichertBenutzer(final Benutzer benutzer)
	{
		when(benutzerRepository.speichereBenutzer(any(Benutzer.class))).thenReturn(benutzer);
	}

	@Test
	@DisplayName("alle Benutzer ermitteln")
	public void test01()
	{
		final var erwartet = List.of(Testdaten.BENUTZER_DTO_JUSTIN, Testdaten.BENUTZER_DTO_ANETTE);
		final var alleBenutzer = List.of(Testdaten.BENUTZER_JUSTIN, Testdaten.BENUTZER_ANETTE);
		angenommenDasBenutzerRepositoryGibtAlleBenutzerZurueck(alleBenutzer);

		final var ergebnis = sut.ermittleAlle();

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("AuthentifizierungNichtGefundenException werfen, wenn Authentifizierung nicht gefunden werden kann")
	public void test021()
	{
		final var id = new Primaerschluessel().getId().toString();
		final var erwartet = "Die Authentifizierung mit der ID \"" + id + "\" existiert nicht!";
		angenommenDasAuthentifizierungRepositoryErmitteltKeineAuthentifizierung();

		final var exception = assertThrows(AuthentifizierungNichtGefundenException.class,
			() -> sut.speichereBenutzer(Testdaten.BENUTZER_DTO_JUSTIN, id));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("einen Benutzer erstellen")
	public void test02() throws AuthentifizierungNichtGefundenException
	{
		final var benutzerDto = Testdaten.BENUTZER_DTO_JUSTIN;
		final var benutzer = Testdaten.BENUTZER_JUSTIN;
		final var authentifizierung = Testdaten.AUTHENTIFIZIERUNG_JUSTIN;
		angenommenDasAuthentifizierungRepositoryErmitteltAuthentifizierungZuId(Optional.of(authentifizierung));
		angenommenDasBenutzerRepositorySpeichertBenutzer(benutzer);

		final var authentifizierungId = Testdaten.AUTHENTIFIZIERUNG_JUSTIN_ID.getId().toString();
		final var ergebnis = sut.speichereBenutzer(benutzerDto, authentifizierungId);

		assertAll(
			() -> assertThat(ergebnis.getVorname()).isEqualTo(benutzerDto.getVorname()),
			() -> assertThat(ergebnis.getNachname()).isEqualTo(benutzerDto.getNachname()),
			() -> assertThat(ergebnis.getLebensalter()).isEqualTo(benutzerDto.getLebensalter()),
			() -> assertThat(ergebnis.getKraftlevel()).isEqualTo(benutzerDto.getKraftlevel()),
			() -> assertThat(ergebnis.getGeschlecht()).isEqualTo(benutzerDto.getGeschlecht()),
			() -> assertThat(ergebnis.getErfahrung()).isEqualTo(benutzerDto.getErfahrung()),
			() -> assertThat(ergebnis.getErnaehrung()).isEqualTo(benutzerDto.getErnaehrung()),
			() -> assertThat(ergebnis.getSchlafqualitaet()).isEqualTo(benutzerDto.getSchlafqualitaet()),
			() -> assertThat(ergebnis.getStress()).isEqualTo(benutzerDto.getStress()),
			() -> assertThat(ergebnis.getDoping()).isEqualTo(benutzerDto.getDoping()),
			() -> assertThat(ergebnis.getRegenerationsfaehigkeit())
				.isEqualTo(benutzerDto.getRegenerationsfaehigkeit()),
			() -> assertThat(ergebnis.getAuthentifizierung().getMail())
				.isEqualTo(benutzerDto.getAuthentifizierung().getMail()),
			() -> assertThat(ergebnis.getAuthentifizierung().getBenutzername())
				.isEqualTo(benutzerDto.getAuthentifizierung().getBenutzername()),
			() -> assertThat(ergebnis.getAuthentifizierung().getPasswort())
				.isEqualTo(benutzerDto.getAuthentifizierung().getPasswort()));
	}

	@Test
	@DisplayName("BenutzerNichtGefundenException werfen, wenn Benutzer zu ID ermittelt wird, aber nicht existiert")
	public void test03()
	{
		angenommenDasBenutzerRepositoryGibtKeinenBenutzerZurueck();

		final var id = new Primaerschluessel().getId().toString();
		final var exception = assertThrows(BenutzerNichtGefundenException.class, () -> sut.ermittleZuId(id));

		assertThat(exception.getMessage()).isEqualTo("Der Benutzer mit der ID \"" + id + "\" existiert nicht!");
	}

	@Test
	@DisplayName("einen Benutzer zu ID ermitteln")
	public void test04() throws BenutzerNichtGefundenException
	{
		final var erwartet = Testdaten.BENUTZER_DTO_JUSTIN;
		final var benutzer = Testdaten.BENUTZER_JUSTIN;
		angenommenDasBenutzerRepositoryGibtEinenBenutzerZurueck(Optional.of(benutzer));

		final var id = new Primaerschluessel().getId().toString();
		final var ergebnis = sut.ermittleZuId(id);

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("BenutzerNichtGefundenException werfen, wenn List von Benutzern zu Nachname ermittelt wird, aber keine existieren")
	public void test05() throws BenutzerNichtGefundenException
	{
		angenommenDasBenutzerRepositoryGibtNullZuNachnamenZurueck();

		final var exception =
			assertThrows(BenutzerNichtGefundenException.class, () -> sut.ermittleAlleZuNachname("NichtGefunden"));

		assertThat(exception.getMessage())
			.isEqualTo("Es wurde kein Benutzer mit dem Nachnamen \"NichtGefunden\" gefunden!");
	}

	@Test
	@DisplayName("eine List von Benutzern zu Nachnamen ermitteln")
	public void test06() throws BenutzerNichtGefundenException
	{
		final var erwartet = List.of(
			Testdaten.BENUTZER_DTO_JUSTIN,
			Testdaten.BENUTZER_DTO_GOTT);
		final var alleBenutzer = List.of(
			Testdaten.BENUTZER_JUSTIN,
			Testdaten.BENUTZER_GOTT);
		angenommenDasBenutzerRepositoryGibtAlleBenutzerZuNachnameZurueck(alleBenutzer);

		final var ergebnis = sut.ermittleAlleZuNachname("Harder");

		assertThat(ergebnis).isEqualTo(erwartet);
	}
}
