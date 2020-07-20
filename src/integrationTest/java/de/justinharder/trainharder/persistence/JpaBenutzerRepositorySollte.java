package de.justinharder.trainharder.persistence;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.justinharder.trainharder.model.domain.Authentifizierung;
import de.justinharder.trainharder.model.domain.Benutzer;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.model.domain.enums.Doping;
import de.justinharder.trainharder.model.domain.enums.Erfahrung;
import de.justinharder.trainharder.model.domain.enums.Ernaehrung;
import de.justinharder.trainharder.model.domain.enums.Geschlecht;
import de.justinharder.trainharder.model.domain.enums.Regenerationsfaehigkeit;
import de.justinharder.trainharder.model.domain.enums.Schlafqualitaet;
import de.justinharder.trainharder.model.domain.enums.Stress;
import de.justinharder.trainharder.model.domain.exceptions.BenutzerNichtGefundenException;
import de.justinharder.trainharder.setup.Testdaten;

public class JpaBenutzerRepositorySollte
{
	private JpaBenutzerRepository sut;

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
		sut = new JpaBenutzerRepository(JpaRepositorySollte.erzeugeEntityManager());
	}

	@Test
	public void alleBenutzerErmitteln()
	{
		final var erwartet = List.of(Testdaten.BENUTZER_JUSTIN, Testdaten.BENUTZER_EDUARD);

		final var ergebnis = sut.ermittleAlle();

		assertThat(ergebnis).containsAll(erwartet);
	}

	@Test
	public void benutzerZuIdErmitteln()
	{
		assertAll(() ->
		{
			final var erwartet = Testdaten.BENUTZER_JUSTIN;

			final var ergebnis = sut.ermittleZuId(erwartet.getPrimaerschluessel());

			assertThat(ergebnis).isEqualTo(Optional.ofNullable(erwartet));
		}, () ->
		{
			final var erwartet = Testdaten.BENUTZER_EDUARD;

			final var ergebnis = sut.ermittleZuId(erwartet.getPrimaerschluessel());

			assertThat(ergebnis).isEqualTo(Optional.ofNullable(erwartet));
		});
	}

	@Test
	public void benutzerErstellen()
	{
		final var erwartet = new Benutzer(
			new Primaerschluessel(),
			"Nicole",
			"Harder",
			13,
			Geschlecht.WEIBLICH,
			Erfahrung.BEGINNER,
			Ernaehrung.SCHLECHT,
			Schlafqualitaet.GUT,
			Stress.MITTELMAESSIG,
			Doping.NEIN,
			Regenerationsfaehigkeit.SCHLECHT,
			new Authentifizierung(
				new Primaerschluessel(),
				"nicoleharder@mail.de",
				"nicoleee",
				"NicoleHarder#2007"));

		final var ergebnis = sut.speichereBenutzer(erwartet);

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	public void leereListZurueckgebenWennKeinBenutzerZuNachnameErmitteltWurde()
	{
		final var erwartet = new ArrayList<Benutzer>();

		final var ergebnis = sut.ermittleAlleZuNachname("lol");

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	public void alleBenutzerZuNachnameErmitteln() throws BenutzerNichtGefundenException
	{
		final var erwartet = List.of(Testdaten.BENUTZER_EDUARD);

		final var ergebnis = sut.ermittleAlleZuNachname("Stremel");

		assertThat(ergebnis).isEqualTo(erwartet);
	}
}
