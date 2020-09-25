package de.justinharder.trainharder.persistence;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import de.justinharder.trainharder.model.domain.Authentifizierung;
import de.justinharder.trainharder.model.domain.Benutzer;
import de.justinharder.trainharder.model.domain.embeddables.Benutzerangabe;
import de.justinharder.trainharder.model.domain.embeddables.Name;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.model.domain.enums.Doping;
import de.justinharder.trainharder.model.domain.enums.Erfahrung;
import de.justinharder.trainharder.model.domain.enums.Ernaehrung;
import de.justinharder.trainharder.model.domain.enums.Geschlecht;
import de.justinharder.trainharder.model.domain.enums.Regenerationsfaehigkeit;
import de.justinharder.trainharder.model.domain.enums.Schlafqualitaet;
import de.justinharder.trainharder.model.domain.enums.Stress;
import de.justinharder.trainharder.setup.Testdaten;

public class AuthentifizierungJpaRepositorySollte extends JpaRepositorySollte
{
	private AuthentifizierungJpaRepository sut;

	@Before
	public void setup()
	{
		sut = new AuthentifizierungJpaRepository(erzeugeEntityManager());
	}

	@Test
	public void keineAuthentifizierungZuIdErmitteln()
	{
		final var erwartet = Optional.empty();

		final var ergebnis = sut.ermittleZuId(new Primaerschluessel());

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	public void authentifizierungZuIdErmitteln()
	{
		assertAll(() ->
		{
			final var erwartet = Optional.of(Testdaten.AUTHENTIFIZIERUNG_JUSTIN);

			final var ergebnis = sut.ermittleZuId(Testdaten.AUTHENTIFIZIERUNG_JUSTIN_ID);

			assertThat(ergebnis).isEqualTo(erwartet);
		}, () ->
		{
			final var erwartet = Optional.of(Testdaten.AUTHENTIFIZIERUNG_EDUARD);

			final var ergebnis = sut.ermittleZuId(Testdaten.AUTHENTIFIZIERUNG_EDUARD_ID);

			assertThat(ergebnis).isEqualTo(erwartet);
		});
	}

	@Test
	public void keineAuthentifizierungZuBenutzerErmitteln()
	{
		final var erwartet = Optional.empty();

		final var ergebnis = sut.ermittleZuBenutzer(new Primaerschluessel());

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	public void authentifizierungZuBenutzerErmitteln()
	{
		assertAll(() ->
		{
			final var erwartet = Optional.of(Testdaten.AUTHENTIFIZIERUNG_JUSTIN);

			final var ergebnis = sut.ermittleZuBenutzer(Testdaten.BENUTZER_JUSTIN_ID);

			assertThat(ergebnis).isEqualTo(erwartet);
		}, () ->
		{
			final var erwartet = Optional.of(Testdaten.AUTHENTIFIZIERUNG_EDUARD);

			final var ergebnis = sut.ermittleZuBenutzer(Testdaten.BENUTZER_EDUARD_ID);

			assertThat(ergebnis).isEqualTo(erwartet);
		});
	}

	@Test
	public void keineAuthentifizierungZuMailErmitteln()
	{
		final var erwartet = Optional.empty();

		final var ergebnis = sut.ermittleZuMail("nicht@existent.de");

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	public void authentifizierungZuMailErmitteln()
	{
		assertAll(() ->
		{
			final var erwartet = Optional.of(Testdaten.AUTHENTIFIZIERUNG_JUSTIN);

			final var ergebnis = sut.ermittleZuMail(Testdaten.AUTHENTIFIZIERUNG_JUSTIN.getMail());

			assertThat(ergebnis).isEqualTo(erwartet);
		}, () ->
		{
			final var erwartet = Optional.of(Testdaten.AUTHENTIFIZIERUNG_EDUARD);

			final var ergebnis = sut.ermittleZuMail(Testdaten.AUTHENTIFIZIERUNG_EDUARD.getMail());

			assertThat(ergebnis).isEqualTo(erwartet);
		});
	}

	@Test
	public void keineAuthentifizierungZuBenutzernameErmitteln()
	{
		final var erwartet = Optional.empty();

		final var ergebnis = sut.ermittleZuBenutzername("nichtexistent");

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	public void authentifizierungZuBenutzernameErmitteln()
	{
		assertAll(() ->
		{
			final var erwartet = Optional.of(Testdaten.AUTHENTIFIZIERUNG_JUSTIN);

			final var ergebnis = sut.ermittleZuBenutzername(Testdaten.AUTHENTIFIZIERUNG_JUSTIN.getBenutzername());

			assertThat(ergebnis).isEqualTo(erwartet);
		}, () ->
		{
			final var erwartet = Optional.of(Testdaten.AUTHENTIFIZIERUNG_EDUARD);

			final var ergebnis = sut.ermittleZuBenutzername(Testdaten.AUTHENTIFIZIERUNG_EDUARD.getBenutzername());

			assertThat(ergebnis).isEqualTo(erwartet);
		});
	}

	@Test
	public void keineAuthentifizierungZuResetUuidErmitteln()
	{
		final var erwartet = Optional.empty();

		final var ergebnis = sut.ermittleZuResetUuid(UUID.randomUUID());

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	public void authentifizierungZuResetUuidErmitteln()
	{
		final var erwartet = Optional.of(Testdaten.AUTHENTIFIZIERUNG_JUSTIN);

		final var ergebnis = sut.ermittleZuResetUuid(Testdaten.AUTHENTIFIZIERUNG_JUSTIN.getResetUuid());

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	public void authentifizierungErstellen()
	{
		final var erwartet = new Authentifizierung(
			new Primaerschluessel(),
			"justinharder@t-online.de",
			"lololol",
			Testdaten.PASSWORT);
		erwartet.setBenutzer(new Benutzer(
			new Primaerschluessel(),
			new Name("Justin", "Harder"),
			LocalDate.of(1998, 12, 6),
			new Benutzerangabe(
				Geschlecht.MAENNLICH,
				Erfahrung.BEGINNER,
				Ernaehrung.GUT,
				Schlafqualitaet.GUT,
				Stress.MITTELMAESSIG,
				Doping.NEIN,
				Regenerationsfaehigkeit.DURCHSCHNITTLICH),
			erwartet));

		final var ergebnis = sut.speichereAuthentifizierung(erwartet);

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	public void authentifizierungAktualisieren()
	{
		final var erwartet = Testdaten.AUTHENTIFIZIERUNG_JUSTIN;
		erwartet.setAktiv(true);

		final var ergebnis = sut.speichereAuthentifizierung(erwartet);

		assertAll(
			() -> assertThat(ergebnis.getPrimaerschluessel()).isEqualTo(erwartet.getPrimaerschluessel()),
			() -> assertThat(ergebnis.getMail()).isEqualTo(erwartet.getMail()),
			() -> assertThat(ergebnis.getBenutzername()).isEqualTo(erwartet.getBenutzername()),
			() -> assertThat(ergebnis.getPasswort()).isEqualTo(erwartet.getPasswort()),
			() -> assertThat(ergebnis.isAktiv()).isEqualTo(erwartet.isAktiv()),
			() -> assertThat(ergebnis.getBenutzer()).isEqualTo(erwartet.getBenutzer()));
	}
}
