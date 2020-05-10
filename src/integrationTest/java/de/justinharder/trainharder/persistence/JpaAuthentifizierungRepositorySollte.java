package de.justinharder.trainharder.persistence;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.justinharder.trainharder.model.domain.Authentifizierung;
import de.justinharder.trainharder.model.domain.Benutzer;
import de.justinharder.trainharder.model.domain.enums.Doping;
import de.justinharder.trainharder.model.domain.enums.Erfahrung;
import de.justinharder.trainharder.model.domain.enums.Ernaehrung;
import de.justinharder.trainharder.model.domain.enums.Geschlecht;
import de.justinharder.trainharder.model.domain.enums.Regenerationsfaehigkeit;
import de.justinharder.trainharder.model.domain.enums.Schlafqualitaet;
import de.justinharder.trainharder.model.domain.enums.Stress;
import de.justinharder.trainharder.model.domain.exceptions.AuthentifizierungNichtGefundenException;
import de.justinharder.trainharder.model.domain.exceptions.LoginException;
import de.justinharder.trainharder.setup.Testdaten;

public class JpaAuthentifizierungRepositorySollte
{
	private JpaAuthentifizierungRepository sut;

	@BeforeClass
	public static void setupClass()
	{
		JpaRepositorySollte.erzeugeTestdaten();
	}

	@AfterClass
	public static void resetClass()
	{
		JpaRepositorySollte.schliesseEntityMananger();
	}

	@Before
	public void setup()
	{
		sut = new JpaAuthentifizierungRepository(JpaRepositorySollte.erzeugeEntityManager());
	}

	@Test
	public void alleAuthentifizierungenErmitteln()
	{
		final var erwartet = List.of(Testdaten.AUTHENTIFIZIERUNG_JUSTIN, Testdaten.AUTHENTIFIZIERUNG_EDUARD);

		final var ergebnis = sut.ermittleAlle();

		assertThat(ergebnis).containsAll(erwartet);
	}

	@Test
	public void authentifizierungZuIdErmitteln()
	{
		assertAll(() ->
		{
			final var erwartet = Testdaten.AUTHENTIFIZIERUNG_JUSTIN;

			final var ergebnis = sut.ermittleZuId(1);

			assertThat(ergebnis).isEqualTo(erwartet);
		}, () ->
		{
			final var erwartet = Testdaten.AUTHENTIFIZIERUNG_EDUARD;

			final var ergebnis = sut.ermittleZuId(2);

			assertThat(ergebnis).isEqualTo(erwartet);
		});
	}

	@Test
	public void authentifizierungNichtGefundenExceptionWerfenWennBenutzerNichtExistiert()
	{
		final var erwartet = "Die Authentifizierung zur BenutzerID \"100\" existiert nicht!";

		final var exception =
			assertThrows(AuthentifizierungNichtGefundenException.class, () -> sut.ermittleZuBenutzer(100));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	public void authentifizierungZuBenutzerErmitteln()
	{
		assertAll(() ->
		{
			final var erwartet = Testdaten.AUTHENTIFIZIERUNG_JUSTIN;

			final var ergebnis = sut.ermittleZuBenutzer(Testdaten.BENUTZER_JUSTIN.getId());

			assertThat(ergebnis).isEqualTo(erwartet);
		}, () ->
		{
			final var erwartet = Testdaten.AUTHENTIFIZIERUNG_EDUARD;

			final var ergebnis = sut.ermittleZuBenutzer(Testdaten.BENUTZER_EDUARD.getId());

			assertThat(ergebnis).isEqualTo(erwartet);
		});
	}

	@Test
	public void authentifizierungNichtGefundenExceptionWerfenWennMailNichtExistiert()
	{
		final var erwartet = "Die Authentifizierung zur Mail \"nicht@existent.de\" existiert nicht!";

		final var exception =
			assertThrows(AuthentifizierungNichtGefundenException.class, () -> sut.ermittleZuMail("nicht@existent.de"));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	public void authentifizierungZuMailErmitteln()
	{
		assertAll(() ->
		{
			final var erwartet = Testdaten.AUTHENTIFIZIERUNG_JUSTIN;

			final var ergebnis = sut.ermittleZuMail(Testdaten.AUTHENTIFIZIERUNG_JUSTIN.getMail());

			assertThat(ergebnis).isEqualTo(erwartet);
		}, () ->
		{
			final var erwartet = Testdaten.AUTHENTIFIZIERUNG_EDUARD;

			final var ergebnis = sut.ermittleZuMail(Testdaten.AUTHENTIFIZIERUNG_EDUARD.getMail());

			assertThat(ergebnis).isEqualTo(erwartet);
		});
	}

	@Test
	public void authentifizierungErstellen() throws AuthentifizierungNichtGefundenException
	{
		final var erwartet = new Authentifizierung("justinharder@t-online.de", "lololol", "DerBossDerBosse#123");
		erwartet.setId(1000);
		erwartet.setBenutzer(new Benutzer("Justin", "Harder", 21, Geschlecht.MAENNLICH, Erfahrung.BEGINNER,
			Ernaehrung.GUT, Schlafqualitaet.GUT, Stress.MITTELMAESSIG, Doping.NEIN,
			Regenerationsfaehigkeit.DURCHSCHNITTLICH, erwartet));

		sut.erstelleAuthentifizierung(erwartet);
		final var ergebnis = sut.ermittleZuId(1000);

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	public void loginExceptionWerfen()
	{
		final var erwartet = "Benutzername oder Passwort falsch!";

		final var exception = assertThrows(LoginException.class, () -> sut.checkLogin(
			Testdaten.AUTHENTIFIZIERUNG_JUSTIN.getBenutzername(),
			Testdaten.AUTHENTIFIZIERUNG_EDUARD.getPasswort()));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	public void loginChecken() throws LoginException
	{
		final var erwartet = Testdaten.AUTHENTIFIZIERUNG_JUSTIN;

		final var ergebnis = sut.checkLogin(erwartet.getBenutzername(), erwartet.getPasswort());

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	public void mailCheckenWennDieseExistiert()
	{
		final var erwartet = true;

		final var ergebnis = sut.checkMail(Testdaten.AUTHENTIFIZIERUNG_EDUARD.getMail());

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	public void mailCheckenWennDieseNichtExistiert()
	{
		final var erwartet = false;

		final var ergebnis = sut.checkMail("nicht@existent.de");

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	public void benutzernameCheckenWennDieserExistiert()
	{
		final var erwartet = true;

		final var ergebnis = sut.checkBenutzername(Testdaten.AUTHENTIFIZIERUNG_JUSTIN.getBenutzername());

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	public void benutzernameCheckenWennDieserNichtExistiert()
	{
		final var erwartet = false;

		final var ergebnis = sut.checkBenutzername("nichtexistent");

		assertThat(ergebnis).isEqualTo(erwartet);
	}
}
