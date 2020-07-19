package de.justinharder.trainharder.view.navigation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.trainharder.model.domain.exceptions.AuthentifizierungNichtGefundenException;
import de.justinharder.trainharder.model.domain.exceptions.BenutzerNichtGefundenException;
import de.justinharder.trainharder.setup.Testdaten;
import de.justinharder.trainharder.view.AbstractControllerSollte;

public class DatenschutzControllerSollte extends AbstractControllerSollte
{
	private DatenschutzController sut;

	@BeforeEach
	public void setup()
	{
		sut = new DatenschutzController();
		super.setup(sut);
	}

	@Test
	@DisplayName("zur Datenschutz-Seite per GET navigieren ohne angemeldeten Benutzer")
	public void test01()
	{
		final var erwartet = "/datenschutz.xhtml";

		final var ergebnis = super.zurSeiteNavigierenOhneAngemeldetenBenutzer(sut);

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("zur Datenschutz-Seite per GET navigieren mit Servicefehler")
	public void test02() throws AuthentifizierungNichtGefundenException
	{
		final var erwartet = "/datenschutz.xhtml";
		final var authentifizierungDto = Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN;

		final var ergebnis = super.zurSeiteNavigierenMitServicefehler(sut, authentifizierungDto);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(models).put("fehler", "Die Authentifizierung mit dem Benutzernamen \""
			+ authentifizierungDto.getBenutzername() + "\" existiert nicht!");
	}

	@Test
	@DisplayName("zur Datenschutz-Seite per GET navigieren mit angemeldeten Benutzer")
	public void test03() throws AuthentifizierungNichtGefundenException, BenutzerNichtGefundenException
	{
		final var erwartet = "/datenschutz.xhtml";
		final var authentifizierungDto = Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN;
		final var benutzerDto = Testdaten.BENUTZER_DTO_JUSTIN;

		final var ergebnis = super.zurSeiteNavigierenMitAngemeldetenBenutzer(sut, authentifizierungDto, benutzerDto);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(models).put("authentifizierung", authentifizierungDto);
		verify(models).put("benutzer", benutzerDto);
	}
}
