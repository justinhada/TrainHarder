package de.justinharder.trainharder.view.navigation;

import de.justinharder.trainharder.model.domain.exceptions.AuthentifizierungNichtGefundenException;
import de.justinharder.trainharder.model.domain.exceptions.BenutzerNichtGefundenException;
import de.justinharder.trainharder.setup.Testdaten;
import de.justinharder.trainharder.view.AbstractControllerSollte;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

class ImpressumControllerSollte extends AbstractControllerSollte
{
	private ImpressumController sut;

	@BeforeEach
	void setup()
	{
		sut = new ImpressumController();

		super.setup(sut);
	}

	@Test
	@DisplayName("zur Impressum-Seite per GET navigieren ohne angemeldeten Benutzer")
	void test01()
	{
		var erwartet = "/impressum.xhtml";

		var ergebnis = super.zurSeiteNavigierenOhneAngemeldetenBenutzer(sut::index);

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("zur Impressum-Seite per GET navigieren mit Servicefehler")
	void test02() throws AuthentifizierungNichtGefundenException
	{
		var erwartet = "/impressum.xhtml";
		var authentifizierungDto = Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN;

		var ergebnis = super.zurSeiteNavigierenMitServicefehler(sut::index, authentifizierungDto);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(models).put("fehler", "Die Authentifizierung mit dem Benutzernamen \""
			+ authentifizierungDto.getBenutzername() + "\" existiert nicht!");
	}

	@Test
	@DisplayName("zur Impressum-Seite per GET navigieren mit angemeldeten Benutzer")
	void test03() throws AuthentifizierungNichtGefundenException, BenutzerNichtGefundenException
	{
		var erwartet = "/impressum.xhtml";
		var authentifizierungDto = Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN;
		var benutzerDto = Testdaten.BENUTZER_DTO_JUSTIN;

		var ergebnis = super.zurSeiteNavigierenMitAngemeldetenBenutzer(sut::index, authentifizierungDto, benutzerDto);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(models).put("authentifizierung", authentifizierungDto);
		verify(models).put("benutzer", benutzerDto);
	}
}
