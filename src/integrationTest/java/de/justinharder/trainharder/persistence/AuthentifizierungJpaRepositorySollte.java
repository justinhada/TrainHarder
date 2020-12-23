package de.justinharder.trainharder.persistence;

import de.justinharder.trainharder.model.domain.Authentifizierung;
import de.justinharder.trainharder.model.domain.Benutzer;
import de.justinharder.trainharder.model.domain.embeddables.Benutzerangabe;
import de.justinharder.trainharder.model.domain.embeddables.Name;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.model.domain.enums.*;
import de.justinharder.trainharder.setup.Testdaten;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AuthentifizierungJpaRepositorySollte extends JpaRepositorySollte
{
	private AuthentifizierungJpaRepository sut;

	@BeforeEach
	void setup()
	{
		sut = new AuthentifizierungJpaRepository();

		sut.setEntityManager(getEntityManager());
	}

	@Test
	@DisplayName("keine Authentifizierung zu ID ermitteln")
	void test01()
	{
		assertThat(sut.ermittleZuId(new Primaerschluessel())).isEmpty();
	}

	@Test
	@DisplayName("Authentifizierung zu ID ermitteln")
	void test02()
	{
		assertAll(
			() -> assertThat(sut.ermittleZuId(Testdaten.AUTHENTIFIZIERUNG_JUSTIN_ID))
				.hasValue(Testdaten.AUTHENTIFIZIERUNG_JUSTIN),
			() -> assertThat(sut.ermittleZuId(Testdaten.AUTHENTIFIZIERUNG_EDUARD_ID))
				.hasValue(Testdaten.AUTHENTIFIZIERUNG_EDUARD));
	}

	@Test
	@DisplayName("keine Authentifizierung zu Benutzer ermitteln")
	void test03()
	{
		assertThat(sut.ermittleZuBenutzer(new Primaerschluessel())).isEmpty();
	}

	@Test
	@DisplayName("Authentifizierung zu Benutzer ermitteln")
	void test04()
	{
		assertAll(
			() -> assertThat(sut.ermittleZuBenutzer(Testdaten.BENUTZER_JUSTIN_ID))
				.hasValue(Testdaten.AUTHENTIFIZIERUNG_JUSTIN),
			() -> assertThat(sut.ermittleZuBenutzer(Testdaten.BENUTZER_EDUARD_ID))
				.hasValue(Testdaten.AUTHENTIFIZIERUNG_EDUARD));
	}

	@Test
	@DisplayName("keine Authentifizierung zu Mail ermitteln")
	void test05()
	{
		assertThat(sut.ermittleZuMail("nicht@existent.de")).isEmpty();
	}

	@Test
	@DisplayName("Authentifizierung zu Mail ermitteln")
	void test06()
	{
		assertAll(
			() -> assertThat(sut.ermittleZuMail(Testdaten.AUTHENTIFIZIERUNG_JUSTIN.getMail()))
				.hasValue(Testdaten.AUTHENTIFIZIERUNG_JUSTIN),
			() -> assertThat(sut.ermittleZuMail(Testdaten.AUTHENTIFIZIERUNG_EDUARD.getMail()))
				.hasValue(Testdaten.AUTHENTIFIZIERUNG_EDUARD));
	}

	@Test
	@DisplayName("keine Authentifizierung zu Benutzername ermitteln")
	void test07()
	{
		assertThat(sut.ermittleZuBenutzername("nichtexistent")).isEmpty();
	}

	@Test
	@DisplayName("Authentifizierung zu Benutzername ermitteln")
	void test08()
	{
		assertAll(
			() -> assertThat(sut.ermittleZuBenutzername(Testdaten.AUTHENTIFIZIERUNG_JUSTIN.getBenutzername()))
				.hasValue(Testdaten.AUTHENTIFIZIERUNG_JUSTIN),
			() -> assertThat(sut.ermittleZuBenutzername(Testdaten.AUTHENTIFIZIERUNG_EDUARD.getBenutzername()))
				.hasValue(Testdaten.AUTHENTIFIZIERUNG_EDUARD));
	}

	@Test
	@DisplayName("keine Authentifizierung zu ResetUUID ermitteln")
	void test09()
	{
		assertThat(sut.ermittleZuResetUuid(UUID.randomUUID())).isEmpty();
	}

	@Test
	@DisplayName("Authentifizierung zu ResetUUID ermitteln")
	void test10()
	{
		assertThat(sut.ermittleZuResetUuid(Testdaten.AUTHENTIFIZIERUNG_JUSTIN.getResetUuid()))
			.hasValue(Testdaten.AUTHENTIFIZIERUNG_JUSTIN);
	}

	@Test
	@DisplayName("Authentifizierung erstellen")
	void test11()
	{
		var authentifizierung = new Authentifizierung(
			new Primaerschluessel(),
			"justinharder@t-online.de",
			"lololol",
			Testdaten.PASSWORT);
		authentifizierung.setBenutzer(new Benutzer(
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
			authentifizierung));

		assertThat(sut.speichereAuthentifizierung(authentifizierung)).isEqualTo(authentifizierung);
	}

	@Test
	@DisplayName("Authentifizierung aktualisieren")
	void test12()
	{
		var authentifizierung = Testdaten.AUTHENTIFIZIERUNG_JUSTIN;
		authentifizierung.setAktiv(true);

		var ergebnis = sut.speichereAuthentifizierung(authentifizierung);

		assertAll(
			() -> assertThat(ergebnis.getPrimaerschluessel()).isEqualTo(authentifizierung.getPrimaerschluessel()),
			() -> assertThat(ergebnis.getMail()).isEqualTo(authentifizierung.getMail()),
			() -> assertThat(ergebnis.getBenutzername()).isEqualTo(authentifizierung.getBenutzername()),
			() -> assertThat(ergebnis.getPasswort()).isEqualTo(authentifizierung.getPasswort()),
			() -> assertThat(ergebnis.isAktiv()).isEqualTo(authentifizierung.isAktiv()),
			() -> assertThat(ergebnis.getBenutzer()).isEqualTo(authentifizierung.getBenutzer()));
	}

	@Test
	@DisplayName("null validieren")
	void test13()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> sut.setEntityManager(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.ermittleZuId(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.ermittleZuBenutzer(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.ermittleZuMail(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.ermittleZuBenutzername(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.ermittleZuResetUuid(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.speichereAuthentifizierung(null)));
	}
}
