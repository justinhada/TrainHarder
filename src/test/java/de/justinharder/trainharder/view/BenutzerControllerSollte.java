package de.justinharder.trainharder.view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.trainharder.model.domain.exceptions.AuthentifizierungNichtGefundenException;
import de.justinharder.trainharder.model.domain.exceptions.BenutzerNichtGefundenException;
import de.justinharder.trainharder.setup.Testdaten;

public class BenutzerControllerSollte extends AbstractControllerSollte
{
	private BenutzerController sut;

	@BeforeEach
	public void setup()
	{
		sut = new BenutzerController();
		super.setup(sut);
	}

	@Test
	@DisplayName("zur Benutzer-Seite per GET navigieren ohne angemeldeten Benutzer")
	public void test01()
	{
		final var erwartet = "/benutzer/index.xhtml";

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
		verify(models).put("fehler", "Die Authentifizierung mit dem Benutzernamen \""
			+ authentifizierungDto.getBenutzername() + "\" existiert nicht!");
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
	@DisplayName("zur Benutzerdaten-Seite per GET navigieren ohne angemeldeten Benutzer")
	public void test04()
	{
		final var erwartet = "/benutzer/benutzerdaten.xhtml";

		final var ergebnis = super.zurSeiteNavigierenOhneAngemeldetenBenutzer(() -> sut.benutzer("harder"));

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("zur Benutzerdaten-Seite per GET navigieren mit Servicefehler")
	public void test05() throws AuthentifizierungNichtGefundenException
	{
		final var erwartet = "/benutzer/benutzerdaten.xhtml";
		final var authentifizierungDto = Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN;

		final var ergebnis = super.zurSeiteNavigierenMitServicefehler(
			() -> sut.benutzer(authentifizierungDto.getBenutzername()), authentifizierungDto);

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
			() -> sut.benutzer(authentifizierungDto.getBenutzername()),
			authentifizierungDto,
			benutzerDto);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(models).put("authentifizierung", authentifizierungDto);
		verify(models).put("benutzer", benutzerDto);
	}
}
