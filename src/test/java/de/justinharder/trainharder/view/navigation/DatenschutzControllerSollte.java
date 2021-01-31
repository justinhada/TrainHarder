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

class DatenschutzControllerSollte extends AbstractControllerSollte
{
	private DatenschutzController sut;

	@BeforeEach
	void setup()
	{
		sut = new DatenschutzController();

		super.setup(sut);
	}

	@Test
	@DisplayName("zur Datenschutz-Seite per GET navigieren ohne angemeldeten Benutzer")
	void test01()
	{
		assertThat(super.zurSeiteNavigierenOhneAngemeldetenBenutzer(sut::index)).isEqualTo("/datenschutz.xhtml");
	}

	@Test
	@DisplayName("zur Datenschutz-Seite per GET navigieren mit Servicefehler")
	void test02() throws AuthentifizierungNichtGefundenException
	{
		var authentifizierungDto = Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN;

		assertThat(super.zurSeiteNavigierenMitServicefehler(sut::index, authentifizierungDto)).isEqualTo("/datenschutz.xhtml");
		verify(models).put("fehler", "Die Authentifizierung mit dem Benutzernamen \"" + authentifizierungDto.getBenutzername() + "\" existiert nicht!");
	}

	@Test
	@DisplayName("zur Datenschutz-Seite per GET navigieren mit angemeldeten Benutzer")
	void test03() throws AuthentifizierungNichtGefundenException, BenutzerNichtGefundenException
	{
		var authentifizierungDto = Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN;
		var benutzerDto = Testdaten.BENUTZER_DTO_JUSTIN;

		assertThat(super.zurSeiteNavigierenMitAngemeldetenBenutzer(sut::index, authentifizierungDto, benutzerDto)).isEqualTo("/datenschutz.xhtml");
		verify(models).put("authentifizierung", authentifizierungDto);
		verify(models).put("benutzer", benutzerDto);
	}
}