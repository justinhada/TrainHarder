package de.justinharder.trainharder.persistence;

import de.justinharder.trainharder.model.domain.Authentifizierung;
import de.justinharder.trainharder.model.domain.Benutzer;
import de.justinharder.trainharder.model.domain.embeddables.Benutzerangabe;
import de.justinharder.trainharder.model.domain.embeddables.Name;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.model.domain.enums.*;
import de.justinharder.trainharder.setup.Testdaten;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class AuthentifizierungJpaRepositorySollte extends JpaRepositorySollte
{
	private AuthentifizierungJpaRepository sut;

	@Before
	public void setup()
	{
		sut = new AuthentifizierungJpaRepository();

		sut.setEntityManager(erzeugeEntityManager());
	}

	@Test
	public void keineAuthentifizierungZuIdErmitteln()
	{
		var ergebnis = sut.ermittleZuId(new Primaerschluessel());

		assertThat(ergebnis).isEmpty();
	}

	@Test
	public void authentifizierungZuIdErmitteln()
	{
		assertAll(() ->
		{
			var erwartet = Testdaten.AUTHENTIFIZIERUNG_JUSTIN;

			var ergebnis = sut.ermittleZuId(Testdaten.AUTHENTIFIZIERUNG_JUSTIN_ID);

			assertThat(ergebnis).hasValue(erwartet);
		}, () ->
		{
			var erwartet = Testdaten.AUTHENTIFIZIERUNG_EDUARD;

			var ergebnis = sut.ermittleZuId(Testdaten.AUTHENTIFIZIERUNG_EDUARD_ID);

			assertThat(ergebnis).hasValue(erwartet);
		});
	}

	@Test
	public void keineAuthentifizierungZuBenutzerErmitteln()
	{
		var ergebnis = sut.ermittleZuBenutzer(new Primaerschluessel());

		assertThat(ergebnis).isEmpty();
	}

	@Test
	public void authentifizierungZuBenutzerErmitteln()
	{
		assertAll(() ->
		{
			var erwartet = Testdaten.AUTHENTIFIZIERUNG_JUSTIN;

			var ergebnis = sut.ermittleZuBenutzer(Testdaten.BENUTZER_JUSTIN_ID);

			assertThat(ergebnis).hasValue(erwartet);
		}, () ->
		{
			var erwartet = Testdaten.AUTHENTIFIZIERUNG_EDUARD;

			var ergebnis = sut.ermittleZuBenutzer(Testdaten.BENUTZER_EDUARD_ID);

			assertThat(ergebnis).hasValue(erwartet);
		});
	}

	@Test
	public void keineAuthentifizierungZuMailErmitteln()
	{
		var ergebnis = sut.ermittleZuMail("nicht@existent.de");

		assertThat(ergebnis).isEmpty();
	}

	@Test
	public void authentifizierungZuMailErmitteln()
	{
		assertAll(() ->
		{
			var erwartet = Testdaten.AUTHENTIFIZIERUNG_JUSTIN;

			var ergebnis = sut.ermittleZuMail(Testdaten.AUTHENTIFIZIERUNG_JUSTIN.getMail());

			assertThat(ergebnis).hasValue(erwartet);
		}, () ->
		{
			var erwartet = Testdaten.AUTHENTIFIZIERUNG_EDUARD;

			var ergebnis = sut.ermittleZuMail(Testdaten.AUTHENTIFIZIERUNG_EDUARD.getMail());

			assertThat(ergebnis).hasValue(erwartet);
		});
	}

	@Test
	public void keineAuthentifizierungZuBenutzernameErmitteln()
	{
		var ergebnis = sut.ermittleZuBenutzername("nichtexistent");

		assertThat(ergebnis).isEmpty();
	}

	@Test
	public void authentifizierungZuBenutzernameErmitteln()
	{
		assertAll(() ->
		{
			var erwartet = Testdaten.AUTHENTIFIZIERUNG_JUSTIN;

			var ergebnis = sut.ermittleZuBenutzername(Testdaten.AUTHENTIFIZIERUNG_JUSTIN.getBenutzername());

			assertThat(ergebnis).hasValue(erwartet);
		}, () ->
		{
			var erwartet = Testdaten.AUTHENTIFIZIERUNG_EDUARD;

			var ergebnis = sut.ermittleZuBenutzername(Testdaten.AUTHENTIFIZIERUNG_EDUARD.getBenutzername());

			assertThat(ergebnis).hasValue(erwartet);
		});
	}

	@Test
	public void keineAuthentifizierungZuResetUuidErmitteln()
	{
		var ergebnis = sut.ermittleZuResetUuid(UUID.randomUUID());

		assertThat(ergebnis).isEmpty();
	}

	@Test
	public void authentifizierungZuResetUuidErmitteln()
	{
		var erwartet = Testdaten.AUTHENTIFIZIERUNG_JUSTIN;

		var ergebnis = sut.ermittleZuResetUuid(Testdaten.AUTHENTIFIZIERUNG_JUSTIN.getResetUuid());

		assertThat(ergebnis).hasValue(erwartet);
	}

	@Test
	public void authentifizierungErstellen()
	{
		var erwartet = new Authentifizierung(
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

		var ergebnis = sut.speichereAuthentifizierung(erwartet);

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@Ignore
	public void authentifizierungAktualisieren()
	{
		var erwartet = Testdaten.AUTHENTIFIZIERUNG_JUSTIN;
		erwartet.setAktiv(true);

		var ergebnis = sut.speichereAuthentifizierung(erwartet);

		assertAll(
			() -> assertThat(ergebnis.getPrimaerschluessel()).isEqualTo(erwartet.getPrimaerschluessel()),
			() -> assertThat(ergebnis.getMail()).isEqualTo(erwartet.getMail()),
			() -> assertThat(ergebnis.getBenutzername()).isEqualTo(erwartet.getBenutzername()),
			() -> assertThat(ergebnis.getPasswort()).isEqualTo(erwartet.getPasswort()),
			() -> assertThat(ergebnis.isAktiv()).isEqualTo(erwartet.isAktiv()),
			() -> assertThat(ergebnis.getBenutzer()).isEqualTo(erwartet.getBenutzer()));
	}
}
