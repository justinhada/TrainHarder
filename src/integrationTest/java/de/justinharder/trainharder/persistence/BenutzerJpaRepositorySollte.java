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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BenutzerJpaRepositorySollte extends JpaRepositorySollte
{
	private BenutzerJpaRepository sut;

	@BeforeEach
	void setup()
	{
		sut = new BenutzerJpaRepository();

		sut.setEntityManager(getEntityManager());
	}

	@Test
	@DisplayName("alle Benutzer ermitteln")
	void test01()
	{
		assertThat(sut.ermittleAlle()).containsExactlyInAnyOrder(Testdaten.BENUTZER_JUSTIN, Testdaten.BENUTZER_EDUARD);
	}

	@Test
	@DisplayName("keinen Benutzer zu ID ermitteln")
	void test02()
	{
		assertThat(sut.ermittleZuId(new Primaerschluessel())).isEmpty();
	}

	@Test
	@DisplayName("Benutzer zu ID ermitteln")
	void test03()
	{
		assertAll(
			() -> assertThat(sut.ermittleZuId(Testdaten.BENUTZER_JUSTIN_ID)).hasValue(Testdaten.BENUTZER_JUSTIN),
			() -> assertThat(sut.ermittleZuId(Testdaten.BENUTZER_EDUARD_ID)).hasValue(Testdaten.BENUTZER_EDUARD));
	}

	@Test
	@DisplayName("keinen Benutzer zu Authentifizierung ermitteln")
	void test04()
	{
		assertThat(sut.ermittleZuAuthentifizierung(new Primaerschluessel())).isEmpty();
	}

	@Test
	@DisplayName("Benutzer zu Authentifizierung ermitteln")
	void test05()
	{
		assertAll(
			() -> assertThat(sut.ermittleZuAuthentifizierung(Testdaten.AUTHENTIFIZIERUNG_JUSTIN_ID)).hasValue(Testdaten.BENUTZER_JUSTIN),
			() -> assertThat(sut.ermittleZuAuthentifizierung(Testdaten.AUTHENTIFIZIERUNG_EDUARD_ID)).hasValue(Testdaten.BENUTZER_EDUARD));
	}

	@Test
	@DisplayName("Benutzer erstellen")
	void test06()
	{
		var benutzer = new Benutzer(
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

		assertThat(sut.speichereBenutzer(benutzer)).isEqualTo(benutzer);
	}

	@Test
	@DisplayName("Benutzer aktualisieren")
	void test07()
	{
		var benutzer = Testdaten.BENUTZER_EDUARD
			.setGeburtsdatum(LocalDate.of(1997, 12, 6));

		var ergebnis = sut.speichereBenutzer(benutzer);

		assertAll(
			() -> assertThat(ergebnis.getPrimaerschluessel()).isEqualTo(benutzer.getPrimaerschluessel()),
			() -> assertThat(ergebnis.getName()).isEqualTo(benutzer.getName()),
			() -> assertThat(ergebnis.getGeburtsdatum()).isEqualTo(benutzer.getGeburtsdatum()),
			() -> assertThat(ergebnis.getBenutzerangabe()).isEqualTo(benutzer.getBenutzerangabe()),
			() -> assertThat(ergebnis.getAuthentifizierung()).isEqualTo(benutzer.getAuthentifizierung()));
	}

	@Test
	@DisplayName("null validieren")
	void test08()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> sut.setEntityManager(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.ermittleZuId(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.ermittleZuAuthentifizierung(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.speichereBenutzer(null)));
	}
}
