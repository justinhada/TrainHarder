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
import de.justinharder.powerlifting.view.navigation.ExternerWebContext;
import de.justinharder.powerlifting.view.navigation.Navigator;

public class BenutzerControllerSollte
{
	private BenutzerController sut;
	private ExternerWebContext externerWebContext;
	private Navigator navigator;
	private BenutzerService benutzerService;

	@BeforeEach
	public void setup()
	{
		externerWebContext = mock(ExternerWebContext.class);
		navigator = mock(Navigator.class);
		benutzerService = mock(BenutzerService.class);
		sut = new BenutzerController(externerWebContext, navigator, benutzerService);
	}

	private void angenommenDerBenutzerServiceGibtAlleBenutzerEintraegeZurueck(final List<BenutzerEintrag> erwartet)
	{
		when(benutzerService.ermittleAlle()).thenReturn(erwartet);
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
		final var benutzerEintrag = Testdaten.JUSTIN_BENUTZEREINTRAG;

		sut.getBenutzerEintrag().setId(0);
		sut.getBenutzerEintrag().setVorname("Justin");
		sut.getBenutzerEintrag().setNachname("Harder");
		sut.getBenutzerEintrag().setKoerpergewicht(90);
		sut.getBenutzerEintrag().setKoerpergroesse(178);
		sut.getBenutzerEintrag().setLebensalter(21);
		sut.getBenutzerEintrag().setKraftlevel("CLASS_5");
		sut.getBenutzerEintrag().setGeschlecht("MAENNLICH");
		sut.getBenutzerEintrag().setErfahrung("BEGINNER");
		sut.getBenutzerEintrag().setErnaehrung("GUT");
		sut.getBenutzerEintrag().setSchlafqualitaet("GUT");
		sut.getBenutzerEintrag().setStress("MITTELMAESSIG");
		sut.getBenutzerEintrag().setDoping("NEIN");
		sut.getBenutzerEintrag().setRegenerationsfaehigkeit("GUT");
		sut.erstelleBenutzer();

		verify(benutzerService).erstelleBenutzer(benutzerEintrag);
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

		sut.getBenutzerZuNachname("Harder");
		final var ergebnis = sut.getBenutzerEintraege();

		assertThat(ergebnis).isEqualTo(erwartet);
	}
}
