package de.justinharder.trainharder.view;

import de.justinharder.trainharder.model.domain.exceptions.AuthentifizierungNichtGefundenException;
import de.justinharder.trainharder.model.domain.exceptions.BenutzerNichtGefundenException;
import de.justinharder.trainharder.setup.Testdaten;
import de.justinharder.trainharder.view.dto.Benutzerdaten;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.security.enterprise.CallerPrincipal;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class BenutzerControllerSollte extends AbstractControllerSollte
{
	private BenutzerController sut;

	private HttpServletRequest request;

	@BeforeEach
	void setup()
	{
		request = mock(HttpServletRequest.class);

		sut = new BenutzerController();

		sut.setRequest(request);
		super.setup(sut);
	}

	private void angenommenDerHttpServletRequestWirftServletException() throws ServletException
	{
		doThrow(ServletException.class).when(request).logout();
	}

	private void angenommenDerBenutzerServiceWirftBenutzerNichtGefundenException(String authentifizierungId)
		throws BenutzerNichtGefundenException
	{
		when(benutzerService.ermittleZuAuthentifizierung(authentifizierungId))
			.thenThrow(new BenutzerNichtGefundenException(
				"Die Authentifizierung mit der ID \"" + authentifizierungId + "\" existiert nicht!"));
	}

	@Test
	@DisplayName("zur Login-Seite weiterleiten, wenn Benutzer angemeldet ist")
	void test01()
	{
		var erwartet = "redirect:login";

		var ergebnis = super.zurSeiteNavigierenOhneAngemeldetenBenutzer(sut::index);

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("zur Benutzer-Seite per GET navigieren mit Servicefehler")
	void test02() throws AuthentifizierungNichtGefundenException
	{
		var erwartet = "/benutzer/index.xhtml";
		var authentifizierungDto = Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN;

		var ergebnis = super.zurSeiteNavigierenMitServicefehler(sut::index, authentifizierungDto);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(models).put(
			"fehler",
			"Die Authentifizierung mit dem Benutzernamen \"" + authentifizierungDto.getBenutzername()
				+ "\" existiert nicht!");
	}

	@Test
	@DisplayName("zur Benutzer-Seite per GET navigieren mit angemeldeten Benutzer")
	void test03() throws AuthentifizierungNichtGefundenException, BenutzerNichtGefundenException
	{
		var erwartet = "/benutzer/index.xhtml";
		var authentifizierungDto = Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN;
		var benutzerDto = Testdaten.BENUTZER_DTO_JUSTIN;

		var ergebnis =
			super.zurSeiteNavigierenMitAngemeldetenBenutzer(sut::index, authentifizierungDto, benutzerDto);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(models).put("authentifizierung", authentifizierungDto);
		verify(models).put("benutzer", benutzerDto);
	}

	@Test
	@DisplayName("zur Login-Seite weiterleiten, wenn kein Benutzer angemeldet ist")
	void test04()
	{
		var erwartet = "redirect:login";

		var ergebnis = super.zurSeiteNavigierenOhneAngemeldetenBenutzer(() -> sut.benutzerdaten("harder"));

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("zur Benutzerdaten-Seite per GET navigieren mit Servicefehler")
	void test05() throws AuthentifizierungNichtGefundenException
	{
		var erwartet = "/benutzer/benutzerdaten.xhtml";
		var authentifizierungDto = Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN;

		var ergebnis = super.zurSeiteNavigierenMitServicefehler(
			() -> sut.benutzerdaten(authentifizierungDto.getBenutzername()), authentifizierungDto);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(models).put("fehler", "Die Authentifizierung mit dem Benutzernamen \""
			+ authentifizierungDto.getBenutzername() + "\" existiert nicht!");
	}

	@Test
	@DisplayName("zur Benutzerdaten-Seite per GET navigieren mit angemeldeten Benutzer")
	void test06() throws AuthentifizierungNichtGefundenException, BenutzerNichtGefundenException
	{
		var erwartet = "/benutzer/benutzerdaten.xhtml";
		var authentifizierungDto = Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN;
		var benutzerDto = Testdaten.BENUTZER_DTO_JUSTIN;

		var ergebnis = super.zurSeiteNavigierenMitAngemeldetenBenutzer(
			() -> sut.benutzerdaten(authentifizierungDto.getBenutzername()),
			authentifizierungDto,
			benutzerDto);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(models).put("authentifizierung", authentifizierungDto);
		verify(models).put("benutzer", benutzerDto);
	}

	@Test
	@DisplayName("NullPointerException werfen, wenn die Benutzerdaten null sind")
	void test07()
	{
		var erwartet = "Zum Ändern des Benutzers werden gültige Benutzerdaten benötigt!";

		var exception = assertThrows(NullPointerException.class, () -> sut.aendereBenutzerdaten(null));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("zur Login-Seite weiterleiten, wenn kein Benutzer angemeldet ist")
	void test08() throws AuthentifizierungNichtGefundenException
	{
		var erwartet = "redirect:login";
		var benutzername = "harder";
		angenommenDerSecurityContextGibtCallerPrincipalZurueck(new CallerPrincipal(benutzername));
		angenommenDerAuthentifizierungServiceWirftAuthentifizierungNichtGefundenException(benutzername);

		var benutzerdaten = new Benutzerdaten(
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
		var ergebnis = sut.aendereBenutzerdaten(benutzerdaten);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(models).put(
			"fehler",
			"Die Authentifizierung mit dem Benutzernamen \"" + benutzername + "\" existiert nicht!");
	}

	@Test
	@DisplayName("einen neuen Benutzer erstellen")
	void test09() throws AuthentifizierungNichtGefundenException, BenutzerNichtGefundenException
	{
		var erwartet = "/benutzer/benutzerdaten.xhtml";
		var benutzername = "harder";
		var authentifizierungId = Testdaten.AUTHENTIFIZIERUNG_JUSTIN_ID.getId().toString();
		angenommenDerSecurityContextGibtCallerPrincipalZurueck(new CallerPrincipal(benutzername));
		angenommenDerAuthentifizierungServiceErmitteltAuthentifizierungDtoZuBenutzername(benutzername,
			Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN);
		angenommenDerBenutzerServiceWirftBenutzerNichtGefundenException(authentifizierungId);

		var benutzerdaten = new Benutzerdaten(
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
		var ergebnis = sut.aendereBenutzerdaten(benutzerdaten);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(authentifizierungService, times(2)).ermittleZuBenutzername(benutzername);
		verify(benutzerService, times(2)).ermittleZuAuthentifizierung(authentifizierungId);
		verify(benutzerService).erstelleBenutzer(benutzerdaten, authentifizierungId);
	}

	@Test
	@DisplayName("einen Benutzer aktualisieren")
	void test10() throws AuthentifizierungNichtGefundenException, BenutzerNichtGefundenException
	{
		var erwartet = "/benutzer/benutzerdaten.xhtml";
		var benutzername = "harder";
		var authentifizierungId = Testdaten.AUTHENTIFIZIERUNG_JUSTIN_ID.getId().toString();
		angenommenDerSecurityContextGibtCallerPrincipalZurueck(new CallerPrincipal(benutzername));
		angenommenDerAuthentifizierungServiceErmitteltAuthentifizierungDtoZuBenutzername(benutzername,
			Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN);
		angenommenDerBenutzerServiceErmitteltBenutzerDtoZuAuthentifizierung(authentifizierungId,
			Testdaten.BENUTZER_DTO_JUSTIN);

		var benutzerdaten = new Benutzerdaten(
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
		var ergebnis = sut.aendereBenutzerdaten(benutzerdaten);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(authentifizierungService, times(2)).ermittleZuBenutzername(benutzername);
		verify(benutzerService, times(2)).ermittleZuAuthentifizierung(authentifizierungId);
		verify(benutzerService).aktualisiereBenutzer(Testdaten.BENUTZER_JUSTIN_ID.getId().toString(), benutzerdaten);
	}

	@Test
	@DisplayName("zur Login-Seite weiterleiten, wenn kein Benutzer angemeldet ist")
	void test11()
	{
		var erwartet = "redirect:login";
		angenommenDerSecurityContextGibtKeinCallerPrincipalZurueck();

		var ergebnis = sut.logout();

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("zur Fehler-Seite weiterleiten, wenn der Logout fehlerhaft ist")
	void test12() throws ServletException
	{
		var erwartet = "redirect:error";
		angenommenDerSecurityContextGibtCallerPrincipalZurueck(new CallerPrincipal("harder"));
		angenommenDerHttpServletRequestWirftServletException();

		var ergebnis = sut.logout();

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("zur Start-Seite weiterleiten, wenn der Logout erfolgreich ist")
	void test13() throws ServletException
	{
		var erwartet = "redirect:start";
		angenommenDerSecurityContextGibtCallerPrincipalZurueck(new CallerPrincipal("harder"));

		var ergebnis = sut.logout();

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(request).logout();
	}
}
