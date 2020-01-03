package de.justinharder.powerlifting.model.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.powerlifting.model.domain.Benutzer;
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

	@Test
	@DisplayName("alle Benutzer ermitteln")
	public void test01()
	{
		final var erwartet = List.of(Testdaten.JUSTIN_BENUTZEREINTRAG, Testdaten.ANETTE_BENUTZEREINTRAG);
		final var alleBenutzer = List.of(Testdaten.JUSTIN_BENUTZER, Testdaten.ANETTE);
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
}
