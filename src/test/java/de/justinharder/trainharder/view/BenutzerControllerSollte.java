package de.justinharder.trainharder.view;

import de.justinharder.trainharder.model.domain.exceptions.AuthentifizierungNichtGefundenException;
import de.justinharder.trainharder.model.domain.exceptions.BenutzerNichtGefundenException;
import de.justinharder.trainharder.setup.Testdaten;
import de.justinharder.trainharder.view.dto.Benutzerdaten;
import jakarta.security.enterprise.CallerPrincipal;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
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

	private void angenommenDerBenutzerServiceWirftBenutzerNichtGefundenException(String authentifizierungId) throws BenutzerNichtGefundenException
	{
		when(benutzerService.ermittleZuAuthentifizierung(authentifizierungId))
			.thenThrow(new BenutzerNichtGefundenException("Die Authentifizierung mit der ID \"" + authentifizierungId + "\" existiert nicht!"));
	}

	@Test
	@DisplayName("zur Login-Seite weiterleiten, wenn Benutzer angemeldet ist")
	void test01()
	{
		assertThat(super.zurSeiteNavigierenOhneAngemeldetenBenutzer(sut::index)).isEqualTo("redirect:login");
	}

	@Test
	@DisplayName("zur Benutzer-Seite per GET navigieren mit Servicefehler")
	void test02() throws AuthentifizierungNichtGefundenException
	{
		var authentifizierungDto = Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN;

		assertThat(super.zurSeiteNavigierenMitServicefehler(sut::index, authentifizierungDto)).isEqualTo("/benutzer/index.xhtml");
		verify(models).put("fehler", "Die Authentifizierung mit dem Benutzernamen \"" + authentifizierungDto.getBenutzername() + "\" existiert nicht!");
	}

	@Test
	@DisplayName("zur Benutzer-Seite per GET navigieren mit angemeldeten Benutzer")
	void test03() throws AuthentifizierungNichtGefundenException, BenutzerNichtGefundenException
	{
		var authentifizierungDto = Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN;
		var benutzerDto = Testdaten.BENUTZER_DTO_JUSTIN;

		assertThat(super.zurSeiteNavigierenMitAngemeldetenBenutzer(sut::index, authentifizierungDto, benutzerDto)).isEqualTo("/benutzer/index.xhtml");
		verify(models).put("authentifizierung", authentifizierungDto);
		verify(models).put("benutzer", benutzerDto);
	}

	@Test
	@DisplayName("zur Login-Seite weiterleiten, wenn kein Benutzer angemeldet ist")
	void test04()
	{
		assertThat(super.zurSeiteNavigierenOhneAngemeldetenBenutzer(() -> sut.benutzerdaten("harder"))).isEqualTo("redirect:login");
	}

	@Test
	@DisplayName("zur Benutzerdaten-Seite per GET navigieren mit Servicefehler")
	void test05() throws AuthentifizierungNichtGefundenException
	{
		var authentifizierungDto = Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN;

		assertThat(super.zurSeiteNavigierenMitServicefehler(() -> sut.benutzerdaten(authentifizierungDto.getBenutzername()), authentifizierungDto)).isEqualTo("/benutzer/benutzerdaten.xhtml");
		verify(models).put("fehler", "Die Authentifizierung mit dem Benutzernamen \"" + authentifizierungDto.getBenutzername() + "\" existiert nicht!");
	}

	@Test
	@DisplayName("zur Benutzerdaten-Seite per GET navigieren mit angemeldeten Benutzer")
	void test06() throws AuthentifizierungNichtGefundenException, BenutzerNichtGefundenException
	{
		var authentifizierungDto = Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN;
		var benutzerDto = Testdaten.BENUTZER_DTO_JUSTIN;

		assertThat(super.zurSeiteNavigierenMitAngemeldetenBenutzer(() -> sut.benutzerdaten(authentifizierungDto.getBenutzername()), authentifizierungDto, benutzerDto))
			.isEqualTo("/benutzer/benutzerdaten.xhtml");
		verify(models).put("authentifizierung", authentifizierungDto);
		verify(models).put("benutzer", benutzerDto);
	}

	@Test
	@DisplayName("null validieren")
	void test07()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> sut.setModels(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setSecurityContext(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setAuthentifizierungService(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setBenutzerService(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setRequest(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.benutzerdaten(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.aendereBenutzerdaten(null)));
	}

	@Test
	@DisplayName("zur Login-Seite weiterleiten, wenn kein Benutzer angemeldet ist")
	void test08() throws AuthentifizierungNichtGefundenException
	{
		var benutzername = "harder";
		angenommenDerSecurityContextGibtCallerPrincipalZurueck(new CallerPrincipal(benutzername));
		angenommenDerAuthentifizierungServiceWirftAuthentifizierungNichtGefundenException(benutzername);

		var benutzerdaten = new Benutzerdaten("Justin", "Harder", "06.12.1998", "MAENNLICH", "BEGINNER", "GUT", "GUT", "MITTELMAESSIG", "NEIN", "GUT");

		assertThat(sut.aendereBenutzerdaten(benutzerdaten)).isEqualTo("redirect:login");
		verify(models).put("fehler", "Die Authentifizierung mit dem Benutzernamen \"" + benutzername + "\" existiert nicht!");
	}

	@Test
	@DisplayName("einen neuen Benutzer erstellen")
	void test09() throws AuthentifizierungNichtGefundenException, BenutzerNichtGefundenException
	{
		var benutzername = "harder";
		var authentifizierungId = Testdaten.AUTHENTIFIZIERUNG_JUSTIN_ID.getId().toString();
		angenommenDerSecurityContextGibtCallerPrincipalZurueck(new CallerPrincipal(benutzername));
		angenommenDerAuthentifizierungServiceErmitteltAuthentifizierungDtoZuBenutzername(benutzername, Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN);
		angenommenDerBenutzerServiceWirftBenutzerNichtGefundenException(authentifizierungId);

		var benutzerdaten = new Benutzerdaten("Justin", "Harder", "06.12.1998", "MAENNLICH", "BEGINNER", "GUT", "GUT", "MITTELMAESSIG", "NEIN", "GUT");

		assertThat(sut.aendereBenutzerdaten(benutzerdaten)).isEqualTo("/benutzer/benutzerdaten.xhtml");
		verify(authentifizierungService, times(2)).ermittleZuBenutzername(benutzername);
		verify(benutzerService, times(2)).ermittleZuAuthentifizierung(authentifizierungId);
		verify(benutzerService).erstelleBenutzer(benutzerdaten, authentifizierungId);
	}

	@Test
	@DisplayName("einen Benutzer aktualisieren")
	void test10() throws AuthentifizierungNichtGefundenException, BenutzerNichtGefundenException
	{
		var benutzername = "harder";
		var authentifizierungId = Testdaten.AUTHENTIFIZIERUNG_JUSTIN_ID.getId().toString();
		angenommenDerSecurityContextGibtCallerPrincipalZurueck(new CallerPrincipal(benutzername));
		angenommenDerAuthentifizierungServiceErmitteltAuthentifizierungDtoZuBenutzername(benutzername, Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN);
		angenommenDerBenutzerServiceErmitteltBenutzerDtoZuAuthentifizierung(authentifizierungId, Testdaten.BENUTZER_DTO_JUSTIN);

		var benutzerdaten = new Benutzerdaten("Justin", "Harder", "06.12.1998", "MAENNLICH", "BEGINNER", "GUT", "GUT", "MITTELMAESSIG", "NEIN", "GUT");

		assertThat(sut.aendereBenutzerdaten(benutzerdaten)).isEqualTo("/benutzer/benutzerdaten.xhtml");
		verify(authentifizierungService, times(2)).ermittleZuBenutzername(benutzername);
		verify(benutzerService, times(2)).ermittleZuAuthentifizierung(authentifizierungId);
		verify(benutzerService).aktualisiereBenutzer(Testdaten.BENUTZER_JUSTIN_ID.getId().toString(), benutzerdaten);
	}

	@Test
	@DisplayName("zur Login-Seite weiterleiten, wenn kein Benutzer angemeldet ist")
	void test11()
	{
		angenommenDerSecurityContextGibtKeinCallerPrincipalZurueck();

		assertThat(sut.logout()).isEqualTo("redirect:login");
	}

	@Test
	@DisplayName("zur Fehler-Seite weiterleiten, wenn der Logout fehlerhaft ist")
	void test12() throws ServletException
	{
		angenommenDerSecurityContextGibtCallerPrincipalZurueck(new CallerPrincipal("harder"));
		angenommenDerHttpServletRequestWirftServletException();

		assertThat(sut.logout()).isEqualTo("redirect:error");
	}

	@Test
	@DisplayName("zur Start-Seite weiterleiten, wenn der Logout erfolgreich ist")
	void test13() throws ServletException
	{
		angenommenDerSecurityContextGibtCallerPrincipalZurueck(new CallerPrincipal("harder"));

		assertThat(sut.logout()).isEqualTo("redirect:start");
		verify(request).logout();
	}
}
