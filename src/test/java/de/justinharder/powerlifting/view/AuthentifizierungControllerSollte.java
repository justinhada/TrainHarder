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

import de.justinharder.powerlifting.model.domain.dto.AuthentifizierungEintrag;
import de.justinharder.powerlifting.model.domain.exceptions.AuthentifizierungNichtGefundenException;
import de.justinharder.powerlifting.model.services.AuthentifizierungService;
import de.justinharder.powerlifting.setup.Testdaten;

public class AuthentifizierungControllerSollte extends ControllerSollte
{
	private AuthentifizierungController sut;
	private AuthentifizierungService authentifizierungService;

	@BeforeEach
	public void setup()
	{
		authentifizierungService = mock(AuthentifizierungService.class);
		sut = new AuthentifizierungController(externerWebContext, navigator, authentifizierungService);
	}

	private void angenommenDerAuthentifizierungServiceGibtAlleAuthentifizierungEintraegeZurueck(
		final List<AuthentifizierungEintrag> erwartet)
	{
		when(authentifizierungService.ermittleAlle()).thenReturn(erwartet);
	}

	private void angenommenDerAuthentifizierungServiceGibtAuthentifizierungMithilfeDerIdZurueck(
		final AuthentifizierungEintrag erwartet) throws AuthentifizierungNichtGefundenException
	{
		when(authentifizierungService.ermittleZuId(anyString())).thenReturn(erwartet);
	}

	private void angenommenDerAuthentifizierungServiceGibtAuthentifizierungMithilfeDerBenutzerIdZurueck(
		final AuthentifizierungEintrag erwartet) throws AuthentifizierungNichtGefundenException
	{
		when(authentifizierungService.ermittleZuBenutzer(anyString())).thenReturn(erwartet);
	}

	@Test
	@DisplayName("eine Liste aller AuthentifizierungEinträge zurückgeben")
	public void test01()
	{
		final var erwartet = List.of(
			Testdaten.AUTHENTIFIZIERUNGEINTRAG_JUSTIN,
			Testdaten.AUTHENTIFIZIERUNGEINTRAG_EDUARD);
		angenommenDerAuthentifizierungServiceGibtAlleAuthentifizierungEintraegeZurueck(erwartet);

		final var ergebnis = sut.getAuthentifizierung();

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("Authentifizierung mit der übergebenen ID ermitteln")
	public void test02() throws AuthentifizierungNichtGefundenException
	{
		final var erwartet = Testdaten.AUTHENTIFIZIERUNGEINTRAG_EDUARD;
		angenommenDerAuthentifizierungServiceGibtAuthentifizierungMithilfeDerIdZurueck(erwartet);
		angenommenExternerWebContextEnthaeltParameter(Maps.immutableEntry("authentifizierungId", "0"));

		final var ergebnis = sut.getAuthentifizierungZuId();

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("Authentifizierung mit der übergebenen BenutzerID ermitteln")
	public void test03() throws AuthentifizierungNichtGefundenException
	{
		final var erwartet = Testdaten.AUTHENTIFIZIERUNGEINTRAG_JUSTIN;
		angenommenDerAuthentifizierungServiceGibtAuthentifizierungMithilfeDerBenutzerIdZurueck(erwartet);
		angenommenExternerWebContextEnthaeltParameter(Maps.immutableEntry("benutzerId", "0"));

		final var ergebnis = sut.getAuthentifierungZuBenutzer();

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("Authentifizierung weiter an den AuthentifizierungService geben")
	public void test04()
	{
		final var authentifizierungEintrag = Testdaten.AUTHENTIFIZIERUNGEINTRAG_EDUARD;

		sut.getAuthentifizierungEintrag().setMail("mail@eduard.de");
		sut.getAuthentifizierungEintrag().setBenutzername("eduard");
		sut.getAuthentifizierungEintrag().setPasswort("EduardEduardEduard_98");
		sut.getAuthentifizierungEintrag().setPasswortWiederholen("EduardEduardEduard_98");
		sut.erstelleAuthentifizierung();

		verify(authentifizierungService).erstelleAuthentifizierung(authentifizierungEintrag);
	}
}
