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
		throws BenutzerNichtGefundenException
	{
		when(benutzerRepository.ermittleAlleZuNachname(anyString())).thenReturn(alleBenutzer);
	}

	private void angenommenDasBenutzerRepositoryGibtNullZuNachnamenZurueck() throws BenutzerNichtGefundenException
	{
		angenommenDasBenutzerRepositoryGibtAlleBenutzerZuNachnameZurueck(null);
	}

	@Test
	@DisplayName("alle Benutzer ermitteln")
	public void test01()
	{
		final var erwartet = List.of(Testdaten.BENUTZEREINTRAG_JUSTIN, Testdaten.BENUTZEREINTRAG_ANETTE);
		final var alleBenutzer = List.of(Testdaten.BENUTZER_JUSTIN, Testdaten.BENUTZER_ANETTE);
		angenommenDasBenutzerRepositoryGibtAlleBenutzerZurueck(alleBenutzer);

		final var ergebnis = sut.ermittleAlle();

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("einen Benutzer erstellen")
	public void test02()
	{
		final var benutzerEintrag = Testdaten.BENUTZEREINTRAG_JUSTIN;
		final var authentifizierungEintrag = Testdaten.AUTHENTIFIZIERUNGEINTRAG_JUSTIN;
		final var benutzer = Testdaten.BENUTZER_JUSTIN;
		benutzer.setId(0);

		sut.erstelleBenutzer(benutzerEintrag, authentifizierungEintrag);

		verify(benutzerRepository).erstelleBenutzer(benutzer);
	}

	@Test
	@DisplayName("BenutzerNichtGefundenException werfen, wenn Benutzer zu ID ermittelt wird, aber nicht existiert")
	public void test03()
	{
		angenommenDasBenutzerRepositoryGibtNullZurueck();

		final var exception = assertThrows(BenutzerNichtGefundenException.class, () -> sut.ermittleZuId("10000"));

		assertThat(exception.getMessage()).isEqualTo("Der Benutzer mit der ID \"10000\" existiert nicht!");
	}

	@Test
	@DisplayName("einen Benutzer zu ID ermitteln")
	public void test04() throws BenutzerNichtGefundenException
	{
		final var erwartet = Testdaten.BENUTZEREINTRAG_JUSTIN;
		final var benutzer = Testdaten.BENUTZER_JUSTIN;
		erwartet.setId(0);
		angenommenDasBenutzerRepositoryGibtEinenBenutzerZurueck(benutzer);

		final var ergebnis = sut.ermittleZuId("0");

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("BenutzerNichtGefundenException werfen, wenn List von Benutzern zu Nachname ermittelt wird, aber keine existieren")
	public void test05() throws BenutzerNichtGefundenException
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
			Testdaten.BENUTZEREINTRAG_JUSTIN,
			Testdaten.BENUTZEREINTRAG_GOTT);
		final var alleBenutzer = List.of(
			Testdaten.BENUTZER_JUSTIN,
			Testdaten.BENUTZER_GOTT);
		alleBenutzer.stream().forEach(benutzerEintrag -> benutzerEintrag.setId(0));
		erwartet.stream().forEach(benutzerEintrag -> benutzerEintrag.setId(0));
		angenommenDasBenutzerRepositoryGibtAlleBenutzerZuNachnameZurueck(alleBenutzer);

		final var ergebnis = sut.ermittleZuNachname("Harder");

		assertThat(ergebnis).isEqualTo(erwartet);
	}
}
