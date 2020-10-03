package de.justinharder.trainharder.persistence;

import de.justinharder.trainharder.model.domain.Authentifizierung;
import de.justinharder.trainharder.model.domain.Benutzer;
import de.justinharder.trainharder.model.domain.embeddables.Benutzerangabe;
import de.justinharder.trainharder.model.domain.embeddables.Name;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.model.domain.enums.*;
import de.justinharder.trainharder.setup.Testdaten;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class BenutzerJpaRepositorySollte extends JpaRepositorySollte
{
	private BenutzerJpaRepository sut;

	@Before
	public void setup()
	{
		sut = new BenutzerJpaRepository();
		sut.setEntityManager(erzeugeEntityManager());
	}

	@Test
	public void alleBenutzerErmitteln()
	{
		final var erwartet = List.of(Testdaten.BENUTZER_JUSTIN, Testdaten.BENUTZER_EDUARD);

		final var ergebnis = sut.ermittleAlle();

		assertThat(ergebnis).containsAll(erwartet);
	}

	@Test
	public void keinenBenutzerZuIdErmitteln()
	{
		final var erwartet = Optional.empty();

		final var ergebnis = sut.ermittleZuId(new Primaerschluessel());

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	public void benutzerZuIdErmitteln()
	{
		assertAll(() ->
		{
			final var erwartet = Optional.of(Testdaten.BENUTZER_JUSTIN);

			final var ergebnis = sut.ermittleZuId(Testdaten.BENUTZER_JUSTIN_ID);

			assertThat(ergebnis).isEqualTo(erwartet);
		}, () ->
		{
			final var erwartet = Optional.of(Testdaten.BENUTZER_EDUARD);

			final var ergebnis = sut.ermittleZuId(Testdaten.BENUTZER_EDUARD_ID);

			assertThat(ergebnis).isEqualTo(erwartet);
		});
	}

	@Test
	public void keinenBenutzerZuAuthentifizierungErmitteln()
	{
		final var erwartet = Optional.empty();

		final var ergebnis = sut.ermittleZuAuthentifizierung(new Primaerschluessel());

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	public void benutzerZuAuthentifizierungErmitteln()
	{
		assertAll(() ->
		{
			final var erwartet = Optional.of(Testdaten.BENUTZER_JUSTIN);

			final var ergebnis = sut.ermittleZuAuthentifizierung(Testdaten.AUTHENTIFIZIERUNG_JUSTIN_ID);

			assertThat(ergebnis).isEqualTo(erwartet);
		}, () ->
		{
			final var erwartet = Optional.of(Testdaten.BENUTZER_EDUARD);

			final var ergebnis = sut.ermittleZuAuthentifizierung(Testdaten.AUTHENTIFIZIERUNG_EDUARD_ID);

			assertThat(ergebnis).isEqualTo(erwartet);
		});
	}

	@Test
	public void benutzerErstellen()
	{
		final var erwartet = new Benutzer(
			new Primaerschluessel(),
			new Name("Nicole", "Harder"),
			LocalDate.of(2007, 2, 26),
			new Benutzerangabe(
				Geschlecht.WEIBLICH,
				Erfahrung.BEGINNER,
				Ernaehrung.SCHLECHT,
				Schlafqualitaet.GUT,
				Stress.MITTELMAESSIG,
				Doping.NEIN,
				Regenerationsfaehigkeit.SCHLECHT),
			new Authentifizierung(
				new Primaerschluessel(),
				"nicoleharder@mail.de",
				"nicoleee",
				Testdaten.PASSWORT));

		final var ergebnis = sut.speichereBenutzer(erwartet);

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	public void benutzerAktualisieren()
	{
		final var erwartet = Testdaten.BENUTZER_EDUARD;
		erwartet.setGeburtsdatum(LocalDate.of(1997, 12, 6));

		final var ergebnis = sut.speichereBenutzer(erwartet);

		assertThat(ergebnis).isEqualTo(erwartet);
	}
}
