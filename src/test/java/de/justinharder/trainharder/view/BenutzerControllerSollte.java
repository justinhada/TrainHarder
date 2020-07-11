package de.justinharder.trainharder.view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.trainharder.model.domain.exceptions.BenutzerNichtGefundenException;
import de.justinharder.trainharder.model.services.BenutzerService;
import de.justinharder.trainharder.setup.Testdaten;
import de.justinharder.trainharder.view.dto.BenutzerDto;

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

	private void angenommenDerBenutzerServiceGibtAlleBenutzerDtosZurueck(final List<BenutzerDto> erwartet)
	{
		when(benutzerService.ermittleAlle()).thenReturn(erwartet);
	}

	private void angenommenDerBenutzerServiceGibtEinenBenutzerDtoMithilfeDerIdZurueck(
		final BenutzerDto erwartet) throws BenutzerNichtGefundenException
	{
		when(benutzerService.ermittleZuId(anyString())).thenReturn(erwartet);
	}

	private void angenommenDerBenutzerServiceGibtAlleBenutzerDtosMithilfeDesNachnamensZurueck(
		final List<BenutzerDto> erwartet) throws BenutzerNichtGefundenException
	{
		when(benutzerService.ermittleAlleZuNachname(anyString())).thenReturn(erwartet);
	}

	@Test
	@DisplayName("eine Liste aller BenutzerEinträge zurückgeben")
	public void test01()
	{
		final var erwartet = List.of(Testdaten.BENUTZER_DTO_JUSTIN, Testdaten.BENUTZER_DTO_ANETTE);
		angenommenDerBenutzerServiceGibtAlleBenutzerDtosZurueck(erwartet);

		final var ergebnis = sut.getBenutzer();

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("einen Benutzer mit der übergebenen ID zurückgeben")
	public void test02() throws BenutzerNichtGefundenException
	{
		final var erwartet = Testdaten.BENUTZER_DTO_JUSTIN;
		angenommenDerBenutzerServiceGibtEinenBenutzerDtoMithilfeDerIdZurueck(erwartet);

		final var ergebnis = sut.getBenutzerZuId(erwartet.getPrimaerschluessel());

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("eine Liste aller BenutzerEinträge mit dem übergebenem Nachnamen zurückgeben")
	public void test03() throws BenutzerNichtGefundenException
	{
		final var erwartet = List.of(
			Testdaten.BENUTZER_DTO_JUSTIN,
			Testdaten.BENUTZER_DTO_GOTT);
		angenommenDerBenutzerServiceGibtAlleBenutzerDtosMithilfeDesNachnamensZurueck(erwartet);

		final var ergebnis = sut.getBenutzerZuNachname("Harder");

		assertThat(ergebnis).isEqualTo(erwartet);
	}
}
