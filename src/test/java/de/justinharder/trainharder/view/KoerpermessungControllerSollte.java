package de.justinharder.trainharder.view;

import de.justinharder.trainharder.model.domain.exceptions.AuthentifizierungNichtGefundenException;
import de.justinharder.trainharder.model.domain.exceptions.BenutzerNichtGefundenException;
import de.justinharder.trainharder.model.services.KoerpermessungService;
import de.justinharder.trainharder.setup.Testdaten;
import de.justinharder.trainharder.view.dto.Koerpermessdaten;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.security.enterprise.CallerPrincipal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
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
		var erwartet = "redirect:login";

		var ergebnis = super.zurSeiteNavigierenOhneAngemeldetenBenutzer(sut::index);

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("zur Benutzer-Seite per GET navigieren mit Servicefehler")
	void test02() throws AuthentifizierungNichtGefundenException
	{
		var erwartet = "/koerpermessungen/index.xhtml";
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
		var erwartet = "/koerpermessungen/index.xhtml";
		var authentifizierungDto = Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN;
		var benutzerDto = Testdaten.BENUTZER_DTO_JUSTIN;

		var ergebnis = super.zurSeiteNavigierenMitAngemeldetenBenutzer(sut::index, authentifizierungDto, benutzerDto);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(models).put("authentifizierung", authentifizierungDto);
		verify(models).put("benutzer", benutzerDto);
	}

	@Test
	@DisplayName("zur Login-Seite weiterleiten, wenn kein Benutzer angemeldet ist")
	void test04()
	{
		var erwartet = "redirect:login";

		var ergebnis = super.zurSeiteNavigierenOhneAngemeldetenBenutzer(() -> sut.koerpermessdaten("harder"));

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(securityContext).getCallerPrincipal();
	}

	@Test
	@DisplayName("zur Login-Seite weiterleiten, wenn der Benutzer nicht existiert")
	void test05() throws AuthentifizierungNichtGefundenException
	{
		var erwartet = "redirect:login";
		var authentifizierungDto = Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN;

		var ergebnis = super.zurSeiteNavigierenMitServicefehler(
			() -> sut.koerpermessdaten(authentifizierungDto.getBenutzername()), authentifizierungDto);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(models).put("fehler", "Die Authentifizierung mit dem Benutzernamen \""
			+ authentifizierungDto.getBenutzername() + "\" existiert nicht!");
	}

	@Test
	@DisplayName("zur Login-Seite weiterleiten, wenn die Authentifizierung nicht existiert")
	void test06() throws AuthentifizierungNichtGefundenException
	{
		var erwartet = "redirect:login";
		var authentifizierungDto = Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN;

		var ergebnis = super.zurSeiteNavigierenMitServicefehler(
			() -> sut.koerpermessdaten(authentifizierungDto.getBenutzername()), authentifizierungDto);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(models).put("fehler", "Die Authentifizierung mit dem Benutzernamen \""
			+ authentifizierungDto.getBenutzername() + "\" existiert nicht!");
	}

	@Test
	@DisplayName("zur Benutzerdaten-Seite per GET navigieren mit angemeldeten Benutzer")
	void test07() throws AuthentifizierungNichtGefundenException, BenutzerNichtGefundenException
	{
		var erwartet = "/koerpermessungen/benutzerdaten.xhtml";
		var authentifizierungDto = Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN;
		var benutzerDto = Testdaten.BENUTZER_DTO_JUSTIN;
		var koerpermessungDtos = List.of(Testdaten.KOERPERMESSUNG_DTO_JUSTIN);

		var ergebnis = super.zurSeiteNavigierenMitAngemeldetenBenutzer(
			() -> sut.koerpermessdaten(authentifizierungDto.getBenutzername()),
			authentifizierungDto,
			benutzerDto);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(models).put("authentifizierung", authentifizierungDto);
		verify(models).put("benutzer", benutzerDto);
		verify(models).put("koerpermessungen", koerpermessungDtos);
	}

	@Test
	@DisplayName("NullPointerException werfen, wenn die Koerpermessdaten null sind")
	void test08()
	{
		var erwartet = "Die Erstellung der Koerpermessung benötigt gültige Koerpermessdaten!";

		var exception = assertThrows(NullPointerException.class, () -> sut.addKoerpermessung(null));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("zur Login-Seite weiterleiten, wenn die Authentifizierung nicht existiert")
	void test09() throws AuthentifizierungNichtGefundenException
	{
		var erwartet = "redirect:login";
		var benutzername = "harder";
		angenommenDerSecurityContextGibtCallerPrincipalZurueck(new CallerPrincipal(benutzername));
		angenommenDerAuthentifizierungServiceWirftAuthentifizierungNichtGefundenException(benutzername);

		var koerpermessdaten = new Koerpermessdaten("2020-06-29", 178, 90, 25, 2500, 2900);
		var ergebnis = sut.addKoerpermessung(koerpermessdaten);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(models).put(
			"fehler",
			"Die Authentifizierung mit dem Benutzernamen \"" + benutzername + "\" existiert nicht!");
	}

	@Test
	@DisplayName("eine neue Koerpermessung erstellen")
	void test10() throws AuthentifizierungNichtGefundenException, BenutzerNichtGefundenException
	{
		var erwartet = "/koerpermessungen/benutzerdaten.xhtml";
		var benutzername = "harder";
		var authentifizierungDto = Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN;
		var benutzerDto = Testdaten.BENUTZER_DTO_JUSTIN;
		angenommenDerSecurityContextGibtCallerPrincipalZurueck(new CallerPrincipal(benutzername));
		angenommenDerAuthentifizierungServiceErmitteltAuthentifizierungDtoZuBenutzername(benutzername,
			authentifizierungDto);
		angenommenDerBenutzerServiceErmitteltBenutzerDtoZuAuthentifizierung(authentifizierungDto.getPrimaerschluessel(),
			benutzerDto);

		var koerpermessdaten = new Koerpermessdaten("2020-06-29", 178, 90, 25, 2500, 2900);
		var ergebnis = sut.addKoerpermessung(koerpermessdaten);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(authentifizierungService, times(2)).ermittleZuBenutzername(benutzername);
		verify(benutzerService, times(2)).ermittleZuAuthentifizierung(authentifizierungDto.getPrimaerschluessel());
		verify(koerpermessungService).erstelleKoerpermessung(koerpermessdaten, benutzerDto.getPrimaerschluessel());
	}
}
