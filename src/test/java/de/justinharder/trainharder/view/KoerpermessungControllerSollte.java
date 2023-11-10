package de.justinharder.trainharder.view;

import de.justinharder.trainharder.model.domain.exceptions.AuthentifizierungNichtGefundenException;
import de.justinharder.trainharder.model.domain.exceptions.BenutzerNichtGefundenException;
import de.justinharder.trainharder.model.services.KoerpermessungService;
import de.justinharder.trainharder.setup.Testdaten;
import de.justinharder.trainharder.view.dto.Koerpermessdaten;
import jakarta.security.enterprise.CallerPrincipal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class KoerpermessungControllerSollte extends AbstractControllerSollte
{
	private KoerpermessungController sut;

	private KoerpermessungService koerpermessungService;

	@BeforeEach
	void setup()
	{
		koerpermessungService = mock(KoerpermessungService.class);

		sut = new KoerpermessungController();

		sut.setKoerpermessungService(koerpermessungService);
		super.setup(sut);
	}

	@Test
	@DisplayName("zur Login-Seite weiterleiten, wenn kein Benutzer angemeldet ist")
	void test01()
	{
		assertThat(super.zurSeiteNavigierenOhneAngemeldetenBenutzer(sut::index)).isEqualTo("redirect:login");
	}

	@Test
	@DisplayName("zur Benutzer-Seite per GET navigieren mit Servicefehler")
	void test02() throws AuthentifizierungNichtGefundenException
	{
		var authentifizierungDto = Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN;

		assertThat(super.zurSeiteNavigierenMitServicefehler(sut::index, authentifizierungDto)).isEqualTo("/koerpermessungen/index.xhtml");
		verify(models).put("fehler", "Die Authentifizierung mit dem Benutzernamen \"" + authentifizierungDto.getBenutzername() + "\" existiert nicht!");
	}

	@Test
	@DisplayName("zur Benutzer-Seite per GET navigieren mit angemeldeten Benutzer")
	void test03() throws AuthentifizierungNichtGefundenException, BenutzerNichtGefundenException
	{
		var authentifizierungDto = Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN;
		var benutzerDto = Testdaten.BENUTZER_DTO_JUSTIN;

		assertThat(super.zurSeiteNavigierenMitAngemeldetenBenutzer(sut::index, authentifizierungDto, benutzerDto)).isEqualTo("/koerpermessungen/index.xhtml");
		verify(models).put("authentifizierung", authentifizierungDto);
		verify(models).put("benutzer", benutzerDto);
	}

	@Test
	@DisplayName("zur Login-Seite weiterleiten, wenn kein Benutzer angemeldet ist")
	void test04()
	{
		assertThat(super.zurSeiteNavigierenOhneAngemeldetenBenutzer(() -> sut.koerpermessdaten("harder"))).isEqualTo("redirect:login");
		verify(securityContext).getCallerPrincipal();
	}

	@Test
	@DisplayName("zur Login-Seite weiterleiten, wenn der Benutzer nicht existiert")
	void test05() throws AuthentifizierungNichtGefundenException
	{
		var authentifizierungDto = Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN;

		assertThat(super.zurSeiteNavigierenMitServicefehler(() -> sut.koerpermessdaten(authentifizierungDto.getBenutzername()), authentifizierungDto)).isEqualTo("redirect:login");
		verify(models).put("fehler", "Die Authentifizierung mit dem Benutzernamen \"" + authentifizierungDto.getBenutzername() + "\" existiert nicht!");
	}

	@Test
	@DisplayName("zur Login-Seite weiterleiten, wenn die Authentifizierung nicht existiert")
	void test06() throws AuthentifizierungNichtGefundenException
	{
		var authentifizierungDto = Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN;

		assertThat(super.zurSeiteNavigierenMitServicefehler(() -> sut.koerpermessdaten(authentifizierungDto.getBenutzername()), authentifizierungDto)).isEqualTo("redirect:login");
		verify(models).put("fehler", "Die Authentifizierung mit dem Benutzernamen \"" + authentifizierungDto.getBenutzername() + "\" existiert nicht!");
	}

	@Test
	@DisplayName("zur Benutzerdaten-Seite per GET navigieren mit angemeldeten Benutzer")
	void test07() throws AuthentifizierungNichtGefundenException, BenutzerNichtGefundenException
	{
		var authentifizierungDto = Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN;
		var benutzerDto = Testdaten.BENUTZER_DTO_JUSTIN;
		var koerpermessungDtos = List.of(Testdaten.KOERPERMESSUNG_DTO_JUSTIN);

		assertThat(super.zurSeiteNavigierenMitAngemeldetenBenutzer(() -> sut.koerpermessdaten(authentifizierungDto.getBenutzername()), authentifizierungDto, benutzerDto))
			.isEqualTo("/koerpermessungen/benutzerdaten.xhtml");
		verify(models).put("authentifizierung", authentifizierungDto);
		verify(models).put("benutzer", benutzerDto);
		verify(models).put("koerpermessungen", koerpermessungDtos);
	}

	@Test
	@DisplayName("null validieren")
	void test08()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> sut.koerpermessdaten(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.addKoerpermessung(null)));
	}

	@Test
	@DisplayName("zur Login-Seite weiterleiten, wenn die Authentifizierung nicht existiert")
	void test09() throws AuthentifizierungNichtGefundenException
	{
		var benutzername = "harder";
		angenommenDerSecurityContextGibtCallerPrincipalZurueck(new CallerPrincipal(benutzername));
		angenommenDerAuthentifizierungServiceWirftAuthentifizierungNichtGefundenException(benutzername);

		assertThat(sut.addKoerpermessung(new Koerpermessdaten("2020-06-29", 178, 90, 25, 2500, 2900))).isEqualTo("redirect:login");
		verify(models).put("fehler", "Die Authentifizierung mit dem Benutzernamen \"" + benutzername + "\" existiert nicht!");
	}

	@Test
	@DisplayName("eine neue Koerpermessung erstellen")
	void test10() throws AuthentifizierungNichtGefundenException, BenutzerNichtGefundenException
	{
		var benutzername = "harder";
		var authentifizierungDto = Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN;
		var benutzerDto = Testdaten.BENUTZER_DTO_JUSTIN;
		angenommenDerSecurityContextGibtCallerPrincipalZurueck(new CallerPrincipal(benutzername));
		angenommenDerAuthentifizierungServiceErmitteltAuthentifizierungDtoZuBenutzername(benutzername, authentifizierungDto);
		angenommenDerBenutzerServiceErmitteltBenutzerDtoZuAuthentifizierung(authentifizierungDto.getPrimaerschluessel(), benutzerDto);

		var koerpermessdaten = new Koerpermessdaten("2020-06-29", 178, 90, 25, 2500, 2900);

		assertThat(sut.addKoerpermessung(koerpermessdaten)).isEqualTo("/koerpermessungen/benutzerdaten.xhtml");
		verify(authentifizierungService, times(2)).ermittleZuBenutzername(benutzername);
		verify(benutzerService, times(2)).ermittleZuAuthentifizierung(authentifizierungDto.getPrimaerschluessel());
		verify(koerpermessungService).erstelleKoerpermessung(koerpermessdaten, benutzerDto.getPrimaerschluessel());
	}

	@Test
	@DisplayName("null validieren")
	void test11()
	{
		assertAll(
			()->assertThrows(NullPointerException.class, () -> sut.setModels(null)),
			()->assertThrows(NullPointerException.class, () -> sut.setSecurityContext(null)),
			()->assertThrows(NullPointerException.class, () -> sut.setAuthentifizierungService(null)),
			()->assertThrows(NullPointerException.class, () -> sut.setBenutzerService(null)),
			()->assertThrows(NullPointerException.class, () -> sut.setKoerpermessungService(null)));
	}
}
