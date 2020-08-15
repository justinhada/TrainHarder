package de.justinharder.trainharder.view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.security.enterprise.CallerPrincipal;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.trainharder.model.domain.exceptions.AuthentifizierungNichtGefundenException;
import de.justinharder.trainharder.model.domain.exceptions.BenutzerNichtGefundenException;
import de.justinharder.trainharder.setup.Testdaten;
import de.justinharder.trainharder.view.dto.Benutzerdaten;

public class BenutzerControllerSollte extends AbstractControllerSollte
{
	private BenutzerController sut;

	private HttpServletRequest request;

	@BeforeEach
	public void setup()
	{
		sut = new BenutzerController();
		super.setup(sut);

		request = mock(HttpServletRequest.class);

		sut.setRequest(request);
	}

	private void angenommenDerHttpServletRequestWirftServletException() throws ServletException
	{
		doThrow(ServletException.class).when(request).logout();
	}

	private void angenommenDerBenutzerServiceWirftBenutzerNichtGefundenException(final String authentifizierungId)
		throws BenutzerNichtGefundenException
	{
		when(benutzerService.ermittleZuAuthentifizierung(authentifizierungId))
			.thenThrow(new BenutzerNichtGefundenException(
				"Die Authentifizierung mit der ID \"" + authentifizierungId + "\" existiert nicht!"));
	}

	@Test
	@DisplayName("zur Login-Seite per GET navigieren ohne angemeldeten Benutzer")
	public void test01()
	{
		final var erwartet = "redirect:login";

		final var ergebnis = super.zurSeiteNavigierenOhneAngemeldetenBenutzer(sut::index);

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("zur Benutzer-Seite per GET navigieren mit Servicefehler")
	public void test02() throws AuthentifizierungNichtGefundenException
	{
		final var erwartet = "/benutzer/index.xhtml";
		final var authentifizierungDto = Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN;

		final var ergebnis = super.zurSeiteNavigierenMitServicefehler(sut::index, authentifizierungDto);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(models).put(
			"fehler",
			"Die Authentifizierung mit dem Benutzernamen \"" + authentifizierungDto.getBenutzername()
				+ "\" existiert nicht!");
	}

	@Test
	@DisplayName("zur Benutzer-Seite per GET navigieren mit angemeldeten Benutzer")
	public void test03() throws AuthentifizierungNichtGefundenException, BenutzerNichtGefundenException
	{
		final var erwartet = "/benutzer/index.xhtml";
		final var authentifizierungDto = Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN;
		final var benutzerDto = Testdaten.BENUTZER_DTO_JUSTIN;

		final var ergebnis =
			super.zurSeiteNavigierenMitAngemeldetenBenutzer(sut::index, authentifizierungDto, benutzerDto);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(models).put("authentifizierung", authentifizierungDto);
		verify(models).put("benutzer", benutzerDto);
	}

	@Test
	@DisplayName("zur Login-Seite per GET navigieren ohne angemeldeten Benutzer")
	public void test04()
	{
		final var erwartet = "redirect:login";

		final var ergebnis = super.zurSeiteNavigierenOhneAngemeldetenBenutzer(() -> sut.benutzerdaten("harder"));

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("zur Benutzerdaten-Seite per GET navigieren mit Servicefehler")
	public void test05() throws AuthentifizierungNichtGefundenException
	{
		final var erwartet = "/benutzer/benutzerdaten.xhtml";
		final var authentifizierungDto = Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN;

		final var ergebnis = super.zurSeiteNavigierenMitServicefehler(
			() -> sut.benutzerdaten(authentifizierungDto.getBenutzername()), authentifizierungDto);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(models).put("fehler", "Die Authentifizierung mit dem Benutzernamen \""
			+ authentifizierungDto.getBenutzername() + "\" existiert nicht!");
	}

	@Test
	@DisplayName("zur Benutzerdaten-Seite per GET navigieren mit angemeldeten Benutzer")
	public void test06() throws AuthentifizierungNichtGefundenException, BenutzerNichtGefundenException
	{
		final var erwartet = "/benutzer/benutzerdaten.xhtml";
		final var authentifizierungDto = Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN;
		final var benutzerDto = Testdaten.BENUTZER_DTO_JUSTIN;

		final var ergebnis = super.zurSeiteNavigierenMitAngemeldetenBenutzer(
			() -> sut.benutzerdaten(authentifizierungDto.getBenutzername()),
			authentifizierungDto,
			benutzerDto);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(models).put("authentifizierung", authentifizierungDto);
		verify(models).put("benutzer", benutzerDto);
	}

	@Test
	@DisplayName("NullPointerException werfen, wenn die Benutzerdaten null sind")
	public void test07()
	{
		final var erwartet = "Zum Ändern des Benutzers werden gültige Benutzerdaten benötigt!";

		final var exception = assertThrows(NullPointerException.class, () -> sut.aendereBenutzerdaten(null));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("bei fehlerhafter Authentifizierung zur Fehler-Seite per GET navigieren")
	public void test08() throws AuthentifizierungNichtGefundenException
	{
		final var erwartet = "/error";
		final var benutzername = "harder";
		angenommenDerSecurityContextGibtCallerPrincipalZurueck(new CallerPrincipal(benutzername));
		angenommenDerAuthentifizierungServiceWirftAuthentifizierungNichtGefundenException(benutzername);

		final var benutzerdaten = new Benutzerdaten(
			"Justin",
			"Harder",
			"06.12.1998",
			"MAENNLICH",
			"BEGINNER",
			"GUT",
			"GUT",
			"MITTELMAESSIG",
			"NEIN",
			"GUT");
		final var ergebnis = sut.aendereBenutzerdaten(benutzerdaten);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(models).put(
			"fehler",
			"Die Authentifizierung mit dem Benutzernamen \"" + benutzername + "\" existiert nicht!");
	}

	@Test
	@DisplayName("einen neuen Benutzer erstellen")
	public void test09() throws AuthentifizierungNichtGefundenException, BenutzerNichtGefundenException
	{
		final var erwartet = "/benutzer/benutzerdaten.xhtml";
		final var benutzername = "harder";
		final var authentifizierungId = Testdaten.AUTHENTIFIZIERUNG_JUSTIN_ID.getId().toString();
		angenommenDerSecurityContextGibtCallerPrincipalZurueck(new CallerPrincipal(benutzername));
		angenommenDerAuthentifizierungServiceErmitteltAuthentifizierungDtoZuBenutzername(benutzername,
			Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN);
		angenommenDerBenutzerServiceWirftBenutzerNichtGefundenException(authentifizierungId);

		final var benutzerdaten = new Benutzerdaten(
			"Justin",
			"Harder",
			"06.12.1998",
			"MAENNLICH",
			"BEGINNER",
			"GUT",
			"GUT",
			"MITTELMAESSIG",
			"NEIN",
			"GUT");
		final var ergebnis = sut.aendereBenutzerdaten(benutzerdaten);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(authentifizierungService, times(2)).ermittleZuBenutzername(benutzername);
		verify(benutzerService, times(2)).ermittleZuAuthentifizierung(authentifizierungId);
		verify(benutzerService).erstelleBenutzer(benutzerdaten, authentifizierungId);
	}

	@Test
	@DisplayName("einen Benutzer aktualisieren")
	public void test10() throws AuthentifizierungNichtGefundenException, BenutzerNichtGefundenException
	{
		final var erwartet = "/benutzer/benutzerdaten.xhtml";
		final var benutzername = "harder";
		final var authentifizierungId = Testdaten.AUTHENTIFIZIERUNG_JUSTIN_ID.getId().toString();
		angenommenDerSecurityContextGibtCallerPrincipalZurueck(new CallerPrincipal(benutzername));
		angenommenDerAuthentifizierungServiceErmitteltAuthentifizierungDtoZuBenutzername(benutzername,
			Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN);
		angenommenDerBenutzerServiceErmitteltBenutzerDtoZuAuthentifizierung(authentifizierungId,
			Testdaten.BENUTZER_DTO_JUSTIN);

		final var benutzerdaten = new Benutzerdaten(
			"Justin",
			"Harder",
			"06.12.1998",
			"MAENNLICH",
			"BEGINNER",
			"GUT",
			"GUT",
			"MITTELMAESSIG",
			"NEIN",
			"GUT");
		final var ergebnis = sut.aendereBenutzerdaten(benutzerdaten);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(authentifizierungService, times(2)).ermittleZuBenutzername(benutzername);
		verify(benutzerService, times(2)).ermittleZuAuthentifizierung(authentifizierungId);
		verify(benutzerService).aktualisiereBenutzer(Testdaten.BENUTZER_JUSTIN_ID.getId().toString(), benutzerdaten);
	}

	@Test
	@DisplayName("bei Logout ohne angemeldeten Benutzer zur Login-Seite per GET navigieren")
	public void test11() throws ServletException
	{
		final var erwartet = "redirect:login";
		angenommenDerHttpServletRequestWirftServletException();

		final var ergebnis = sut.logout();

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("bei erfolgreichem Logout zur Start-Seite per GET navigieren")
	public void test12() throws ServletException
	{
		final var erwartet = "redirect:start";
		angenommenDerSecurityContextGibtCallerPrincipalZurueck(new CallerPrincipal("harder"));

		final var ergebnis = sut.logout();

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(request).logout();
	}
}
