package de.justinharder.trainharder.view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.trainharder.model.domain.dto.BenutzerEintrag;
import de.justinharder.trainharder.model.domain.exceptions.BenutzerNichtGefundenException;
import de.justinharder.trainharder.model.services.BenutzerService;
import de.justinharder.trainharder.setup.Testdaten;

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

	private void angenommenDerBenutzerServiceGibtEinenBenutzerEintragMithilfeDerIdZurueck(
		final BenutzerEintrag erwartet) throws BenutzerNichtGefundenException
	{
		when(benutzerService.ermittleZuId(anyString())).thenReturn(erwartet);
	}

	private void angenommenDerBenutzerServiceGibtAlleBenutzerEintraegeMithilfeDesNachnamensZurueck(
		final List<BenutzerEintrag> erwartet) throws BenutzerNichtGefundenException
	{
		when(benutzerService.ermittleAlleZuNachname(anyString())).thenReturn(erwartet);
	}

	@Test
	@DisplayName("eine Liste aller BenutzerEinträge zurückgeben")
	public void test01()
	{
		final var erwartet = List.of(Testdaten.BENUTZEREINTRAG_JUSTIN, Testdaten.BENUTZEREINTRAG_ANETTE);
		angenommenDerBenutzerServiceGibtAlleBenutzerEintraegeZurueck(erwartet);

		final var ergebnis = sut.getBenutzer();

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("einen Benutzer mit der übergebenen ID zurückgeben")
	public void test02() throws BenutzerNichtGefundenException
	{
		final var erwartet = Testdaten.BENUTZEREINTRAG_JUSTIN;
		angenommenDerBenutzerServiceGibtEinenBenutzerEintragMithilfeDerIdZurueck(erwartet);

		final var ergebnis = sut.getBenutzerZuId(erwartet.getId());

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("eine Liste aller BenutzerEinträge mit dem übergebenem Nachnamen zurückgeben")
	public void test03() throws BenutzerNichtGefundenException
	{
		final var erwartet = List.of(
			Testdaten.BENUTZEREINTRAG_JUSTIN,
			Testdaten.BENUTZEREINTRAG_GOTT);
		angenommenDerBenutzerServiceGibtAlleBenutzerEintraegeMithilfeDesNachnamensZurueck(erwartet);

		final var ergebnis = sut.getBenutzerZuNachname("Harder");

		assertThat(ergebnis).isEqualTo(erwartet);
	}
}
