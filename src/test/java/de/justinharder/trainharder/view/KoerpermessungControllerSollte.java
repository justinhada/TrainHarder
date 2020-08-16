package de.justinharder.trainharder.view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;

import javax.security.enterprise.CallerPrincipal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.trainharder.model.domain.exceptions.AuthentifizierungNichtGefundenException;
import de.justinharder.trainharder.model.domain.exceptions.BenutzerNichtGefundenException;
import de.justinharder.trainharder.model.services.KoerpermessungService;
import de.justinharder.trainharder.setup.Testdaten;
import de.justinharder.trainharder.view.dto.Koerpermessdaten;

public class KoerpermessungControllerSollte extends AbstractControllerSollte
{
	private KoerpermessungController sut;

	private KoerpermessungService koerpermessungService;

	@BeforeEach
	public void setup()
	{
		sut = new KoerpermessungController();
		super.setup(sut);

		koerpermessungService = mock(KoerpermessungService.class);

		sut.setKoerpermessungService(koerpermessungService);
	}

	@Test
	@DisplayName("zur Login-Seite weiterleiten, wenn kein Benutzer angemeldet ist")
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
		final var erwartet = "/koerpermessungen/index.xhtml";
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
		final var erwartet = "/koerpermessungen/index.xhtml";
		final var authentifizierungDto = Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN;
		final var benutzerDto = Testdaten.BENUTZER_DTO_JUSTIN;

		final var ergebnis =
			super.zurSeiteNavigierenMitAngemeldetenBenutzer(sut::index, authentifizierungDto, benutzerDto);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(models).put("authentifizierung", authentifizierungDto);
		verify(models).put("benutzer", benutzerDto);
	}

	@Test
	@DisplayName("zur Login-Seite weiterleiten, wenn kein Benutzer angemeldet ist")
	public void test04()
	{
		final var erwartet = "redirect:login";

		final var ergebnis = super.zurSeiteNavigierenOhneAngemeldetenBenutzer(() -> sut.koerpermessdaten("harder"));

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(securityContext).getCallerPrincipal();
	}

	@Test
	@DisplayName("zur Login-Seite weiterleiten, wenn der Benutzer nicht existiert")
	public void test05() throws AuthentifizierungNichtGefundenException
	{
		final var erwartet = "redirect:login";
		final var authentifizierungDto = Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN;

		final var ergebnis = super.zurSeiteNavigierenMitServicefehler(
			() -> sut.koerpermessdaten(authentifizierungDto.getBenutzername()), authentifizierungDto);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(models).put("fehler", "Die Authentifizierung mit dem Benutzernamen \""
			+ authentifizierungDto.getBenutzername() + "\" existiert nicht!");
	}

	@Test
	@DisplayName("zur Login-Seite weiterleiten, wenn die Authentifizierung nicht existiert")
	public void test06() throws AuthentifizierungNichtGefundenException
	{
		final var erwartet = "redirect:login";
		final var authentifizierungDto = Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN;

		final var ergebnis = super.zurSeiteNavigierenMitServicefehler(
			() -> sut.koerpermessdaten(authentifizierungDto.getBenutzername()), authentifizierungDto);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(models).put("fehler", "Die Authentifizierung mit dem Benutzernamen \""
			+ authentifizierungDto.getBenutzername() + "\" existiert nicht!");
	}

	@Test
	@DisplayName("zur Benutzerdaten-Seite per GET navigieren mit angemeldeten Benutzer")
	public void test07() throws AuthentifizierungNichtGefundenException, BenutzerNichtGefundenException
	{
		final var erwartet = "/koerpermessungen/benutzerdaten.xhtml";
		final var authentifizierungDto = Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN;
		final var benutzerDto = Testdaten.BENUTZER_DTO_JUSTIN;
		final var koerpermessungDtos = List.of(Testdaten.KOERPERMESSUNG_DTO_JUSTIN);

		final var ergebnis = super.zurSeiteNavigierenMitAngemeldetenBenutzer(
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
	public void test08()
	{
		final var erwartet = "Die Erstellung der Koerpermessung benötigt gültige Koerpermessdaten!";

		final var exception = assertThrows(NullPointerException.class, () -> sut.addKoerpermessung(null));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("zur Login-Seite weiterleiten, wenn die Authentifizierung nicht existiert")
	public void test09() throws AuthentifizierungNichtGefundenException
	{
		final var erwartet = "redirect:login";
		final var benutzername = "harder";
		angenommenDerSecurityContextGibtCallerPrincipalZurueck(new CallerPrincipal(benutzername));
		angenommenDerAuthentifizierungServiceWirftAuthentifizierungNichtGefundenException(benutzername);

		final var koerpermessdaten = new Koerpermessdaten("2020-06-29", 178, 90, 25, 2500, 2900);
		final var ergebnis = sut.addKoerpermessung(koerpermessdaten);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(models).put(
			"fehler",
			"Die Authentifizierung mit dem Benutzernamen \"" + benutzername + "\" existiert nicht!");
	}

	@Test
	@DisplayName("eine neue Koerpermessung erstellen")
	public void test10() throws AuthentifizierungNichtGefundenException, BenutzerNichtGefundenException
	{
		final var erwartet = "/koerpermessungen/benutzerdaten.xhtml";
		final var benutzername = "harder";
		final var authentifizierungDto = Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN;
		final var benutzerDto = Testdaten.BENUTZER_DTO_JUSTIN;
		angenommenDerSecurityContextGibtCallerPrincipalZurueck(new CallerPrincipal(benutzername));
		angenommenDerAuthentifizierungServiceErmitteltAuthentifizierungDtoZuBenutzername(benutzername,
			authentifizierungDto);
		angenommenDerBenutzerServiceErmitteltBenutzerDtoZuAuthentifizierung(authentifizierungDto.getPrimaerschluessel(),
			benutzerDto);

		final var koerpermessdaten = new Koerpermessdaten("2020-06-29", 178, 90, 25, 2500, 2900);
		final var ergebnis = sut.addKoerpermessung(koerpermessdaten);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(authentifizierungService, times(2)).ermittleZuBenutzername(benutzername);
		verify(benutzerService, times(2)).ermittleZuAuthentifizierung(authentifizierungDto.getPrimaerschluessel());
		verify(koerpermessungService).erstelleKoerpermessung(koerpermessdaten, benutzerDto.getPrimaerschluessel());
	}
}
