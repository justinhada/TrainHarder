package de.justinharder.powerlifting.view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.powerlifting.model.domain.dto.BenutzerEintrag;
import de.justinharder.powerlifting.model.domain.exceptions.BenutzerNichtGefundenException;
import de.justinharder.powerlifting.model.services.BenutzerService;
import de.justinharder.powerlifting.setup.Testdaten;

public class BenutzerControllerSollte
{
	private BenutzerController sut;
	private BenutzerService benutzerService;

	@BeforeEach
	public void setup()
	{
		benutzerService = mock(BenutzerService.class);
		sut = new BenutzerController(benutzerService);
	}

	private void angenommenDerBenutzerServiceGibtAlleBenutzerEintraegeZurueck(final List<BenutzerEintrag> erwartet)
	{
		when(benutzerService.ermittleAlle()).thenReturn(erwartet);
	}

	private void angenommenDerBenutzerServiceGibtEinenBenutzerEintragZurueck(final BenutzerEintrag erwartet)
	{
		when(benutzerService.erstelleBenutzer(anyString(), anyString(), anyInt(), anyInt(), anyInt(), anyString(),
			anyString(), anyString(), anyString(), anyString(), anyString(), anyString())).thenReturn(erwartet);
	}

	private void angenommenDerBenutzerServiceGibtEinenBenutzerEintragMithilfeDerIdZurueck(
		final BenutzerEintrag erwartet) throws BenutzerNichtGefundenException
	{
		when(benutzerService.ermittleZuId(anyInt())).thenReturn(erwartet);
	}

	private void angenommenDerBenutzerServiceGibtAlleBenutzerEintraegeMithilfeDesNachnamensZurueck(
		final List<BenutzerEintrag> erwartet) throws BenutzerNichtGefundenException
	{
		when(benutzerService.ermittleZuNachname(anyString())).thenReturn(erwartet);
	}

	@Test
	@DisplayName("eine Liste aller BenutzerEinträge zurückgeben")
	public void test01()
	{
		final var erwartet = List.of(Testdaten.JUSTIN_BENUTZEREINTRAG, Testdaten.ANETTE_BENUTZEREINTRAG);
		angenommenDerBenutzerServiceGibtAlleBenutzerEintraegeZurueck(erwartet);

		final var ergebnis = sut.getBenutzer();

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("einen Benutzer weiter an den BenutzerService geben")
	public void test02()
	{
		final var erwartet = Testdaten.JUSTIN_BENUTZEREINTRAG;
		angenommenDerBenutzerServiceGibtEinenBenutzerEintragZurueck(erwartet);

		sut.setId(0);
		sut.setVorname("Justin");
		sut.setNachname("Harder");
		sut.setKoerpergewicht(90);
		sut.setKoerpergroesse(178);
		sut.setLebensalter(21);
		sut.setGeschlecht("MAENNLICH");
		sut.setErfahrung("BEGINNER");
		sut.setErnaehrung("GUT");
		sut.setSchlafqualitaet("GUT");
		sut.setStress("MITTELMAESSIG");
		sut.setDoping("NEIN");
		sut.setRegenerationsfaehigkeit("GUT");
		final var ergebnis = sut.erstelleBenutzer();

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(benutzerService).erstelleBenutzer("Justin", "Harder", 90, 178, 21, "MAENNLICH", "BEGINNER", "GUT", "GUT",
			"MITTELMAESSIG", "NEIN", "GUT");
	}

	@Test
	@DisplayName("einen Benutzer mit der übergebenen ID zurückgeben")
	public void test03() throws BenutzerNichtGefundenException
	{
		final var erwartet = Testdaten.JUSTIN_BENUTZEREINTRAG;
		angenommenDerBenutzerServiceGibtEinenBenutzerEintragMithilfeDerIdZurueck(erwartet);

		final var ergebnis = sut.getBenutzerZuId();

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("eine Liste aller BenutzerEinträge mit dem übergebenem Nachnamen zurückgeben")
	public void test04() throws BenutzerNichtGefundenException
	{
		final var erwartet = List.of(
			Testdaten.JUSTIN_BENUTZEREINTRAG,
			Testdaten.GOTT_BENUTZEREINTRAG);
		angenommenDerBenutzerServiceGibtAlleBenutzerEintraegeMithilfeDesNachnamensZurueck(erwartet);

		sut.setNachname("Harder");
		final var ergebnis = sut.getBenutzerZuNachname();

		assertThat(ergebnis).isEqualTo(erwartet);
	}
}
