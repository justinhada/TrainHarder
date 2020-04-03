package de.justinharder.powerlifting.view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.google.common.collect.Maps;

import de.justinharder.powerlifting.model.domain.dto.AnmeldedatenEintrag;
import de.justinharder.powerlifting.model.domain.exceptions.AnmeldedatenNichtGefundenException;
import de.justinharder.powerlifting.model.services.AnmeldedatenService;
import de.justinharder.powerlifting.setup.Testdaten;

public class AnmeldedatenControllerSollte extends ControllerSollte
{
	private AnmeldedatenController sut;
	private AnmeldedatenService anmeldedatenService;

	@BeforeEach
	public void setup()
	{
		anmeldedatenService = mock(AnmeldedatenService.class);
		sut = new AnmeldedatenController(externerWebContext, navigator, anmeldedatenService);
	}

	private void angenommenDerAnmeldedatenServiceGibtAlleAnmeldedatenEintraegeZurueck(
		final List<AnmeldedatenEintrag> erwartet)
	{
		when(anmeldedatenService.ermittleAlle()).thenReturn(erwartet);
	}

	private void angenommenDerAnmeldedatenServiceGibtAnmeldedatenMithilfeDerIdZurueck(
		final AnmeldedatenEintrag erwartet) throws AnmeldedatenNichtGefundenException
	{
		when(anmeldedatenService.ermittleZuId(anyString())).thenReturn(erwartet);
	}

	private void angenommenDerAnmeldedatenServiceGibtAnmeldedatenMithilfeDerBenutzerIdZurueck(
		final AnmeldedatenEintrag erwartet) throws AnmeldedatenNichtGefundenException
	{
		when(anmeldedatenService.ermittleZuBenutzer(anyString())).thenReturn(erwartet);
	}

	@Test
	@DisplayName("eine Liste aller AnmeldedatenEinträge zurückgeben")
	public void test01()
	{
		final var erwartet = List.of(
			Testdaten.ANMELDEDATENEINTRAG_JUSTIN,
			Testdaten.ANMELDEDATENEINTRAG_EDUARD);
		angenommenDerAnmeldedatenServiceGibtAlleAnmeldedatenEintraegeZurueck(erwartet);

		final var ergebnis = sut.getAnmeldedaten();

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("Anmeldedaten mit der übergebenen ID ermitteln")
	public void test02() throws AnmeldedatenNichtGefundenException
	{
		final var erwartet = Testdaten.ANMELDEDATENEINTRAG_EDUARD;
		angenommenDerAnmeldedatenServiceGibtAnmeldedatenMithilfeDerIdZurueck(erwartet);
		angenommenExternerWebContextEnthaeltParameter(Maps.immutableEntry("anmeldedatenId", "0"));

		final var ergebnis = sut.getAnmeldedatenZuId();

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("Anmeldedaten mit der übergebenen BenutzerID ermitteln")
	public void test03() throws AnmeldedatenNichtGefundenException
	{
		final var erwartet = Testdaten.ANMELDEDATENEINTRAG_JUSTIN;
		angenommenDerAnmeldedatenServiceGibtAnmeldedatenMithilfeDerBenutzerIdZurueck(erwartet);
		angenommenExternerWebContextEnthaeltParameter(Maps.immutableEntry("benutzerId", "0"));

		final var ergebnis = sut.getAnmeldedatenZuBenutzer();

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("Anmeldedaten weiter an den AnmeldedatenService geben")
	public void test04()
	{
		final var anmeldedatenEintrag = Testdaten.ANMELDEDATENEINTRAG_EDUARD;

		sut.getAnmeldedatenEintrag().setMail("mail@eduard.de");
		sut.getAnmeldedatenEintrag().setBenutzername("eduard");
		sut.getAnmeldedatenEintrag().setPasswort("EduardEduardEduard_98");
		sut.erstelleAnmeldedaten();

		verify(anmeldedatenService).erstelleAnmeldedaten(anmeldedatenEintrag);
	}
}
