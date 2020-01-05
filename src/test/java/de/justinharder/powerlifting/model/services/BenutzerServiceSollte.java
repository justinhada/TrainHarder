package de.justinharder.powerlifting.model.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.powerlifting.model.domain.Benutzer;
import de.justinharder.powerlifting.model.domain.exceptions.BenutzerNichtGefundenException;
import de.justinharder.powerlifting.model.repository.BenutzerRepository;
import de.justinharder.powerlifting.setup.Testdaten;

public class BenutzerServiceSollte
{
	private BenutzerService sut;
	private BenutzerRepository benutzerRepository;

	@BeforeEach
	public void setup()
	{
		benutzerRepository = mock(BenutzerRepository.class);
		sut = new BenutzerService(benutzerRepository);
	}

	private void angenommenDasBenutzerRepositoryGibtAlleBenutzerZurueck(final List<Benutzer> alleBenutzer)
	{
		when(benutzerRepository.ermittleAlle()).thenReturn(alleBenutzer);
	}

	private void angenommenDasBenutzerRepositoryGibtEinenBenutzerZurueck(final Benutzer benutzer)
	{
		when(benutzerRepository.ermittleZuId(anyInt())).thenReturn(benutzer);
	}

	private void angenommenDasBenutzerRepositoryGibtNullZurueck()
	{
		angenommenDasBenutzerRepositoryGibtEinenBenutzerZurueck(null);
	}

	private void angenommenDasBenutzerRepositoryGibtAlleBenutzerZuNachnameZurueck(final List<Benutzer> alleBenutzer)
	{
		when(benutzerRepository.ermittleAlleZuNachname(anyString())).thenReturn(alleBenutzer);
	}

	private void angenommenDasBenutzerRepositoryGibtNullZuNachnamenZurueck()
	{
		angenommenDasBenutzerRepositoryGibtAlleBenutzerZuNachnameZurueck(null);
	}

	@Test
	@DisplayName("alle Benutzer ermitteln")
	public void test01()
	{
		final var erwartet = List.of(Testdaten.JUSTIN_BENUTZEREINTRAG, Testdaten.ANETTE_BENUTZEREINTRAG);
		final var alleBenutzer = List.of(Testdaten.JUSTIN_BENUTZER, Testdaten.ANETTE_BENUTZER);
		angenommenDasBenutzerRepositoryGibtAlleBenutzerZurueck(alleBenutzer);

		final var ergebnis = sut.ermittleAlle();

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("einen Benutzer erstellen")
	public void test02()
	{
		final var erwartet = Testdaten.JUSTIN_BENUTZEREINTRAG;
		final var eingabe = Testdaten.JUSTIN_BENUTZER;

		final var ergebnis = sut.erstelleBenutzer("Justin", "Harder", 90, 178, 21, "MAENNLICH", "BEGINNER", "GUT",
			"GUT", "MITTELMAESSIG", "NEIN", "GUT");

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(benutzerRepository).erstelleBenutzer(eingabe);
	}

	@Test
	@DisplayName("BenutzerNichtGefundenException werfen, wenn Benutzer zu ID ermittelt wird, aber nicht existiert")
	public void test03()
	{
		angenommenDasBenutzerRepositoryGibtNullZurueck();

		final var exception = assertThrows(BenutzerNichtGefundenException.class, () -> sut.ermittleZuId(20));

		assertThat(exception.getMessage()).isEqualTo("Der Benutzer mit der ID 20 existiert nicht!");
	}

	@Test
	@DisplayName("einen Benutzer zu ID ermitteln")
	public void test04() throws BenutzerNichtGefundenException
	{
		final var erwartet = Testdaten.JUSTIN_BENUTZEREINTRAG;
		final var benutzer = Testdaten.JUSTIN_BENUTZER;
		angenommenDasBenutzerRepositoryGibtEinenBenutzerZurueck(benutzer);

		final var ergebnis = sut.ermittleZuId(0);

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("BenutzerNichtGefundenException werfen, wenn List von Benutzern zu Nachname ermittelt wird, aber keine existieren")
	public void test05()
	{
		angenommenDasBenutzerRepositoryGibtNullZuNachnamenZurueck();

		final var exception =
			assertThrows(BenutzerNichtGefundenException.class, () -> sut.ermittleZuNachname("NichtGefunden"));

		assertThat(exception.getMessage())
			.isEqualTo("Es wurde kein Benutzer mit dem Nachnamen \"NichtGefunden\" gefunden!");
	}

	@Test
	@DisplayName("eine List von Benutzern zu Nachnamen ermitteln")
	public void test06() throws BenutzerNichtGefundenException
	{
		final var erwartet = List.of(
			Testdaten.JUSTIN_BENUTZEREINTRAG,
			Testdaten.GOTT_BENUTZEREINTRAG);
		final var alleBenutzer = List.of(
			Testdaten.JUSTIN_BENUTZER,
			Testdaten.GOTT_BENUTZER);
		angenommenDasBenutzerRepositoryGibtAlleBenutzerZuNachnameZurueck(alleBenutzer);

		final var ergebnis = sut.ermittleZuNachname("Harder");

		assertThat(ergebnis).isEqualTo(erwartet);
	}
}
