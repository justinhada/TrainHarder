package de.justinharder.trainharder.model.domain;

import de.justinharder.trainharder.model.domain.embeddables.Benutzerangabe;
import de.justinharder.trainharder.model.domain.embeddables.Name;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.model.domain.enums.*;
import de.justinharder.trainharder.setup.Testdaten;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BenutzerSollte
{
	private static final Primaerschluessel PRIMAERSCHLUESSEL = new Primaerschluessel();
	private static final Name NAME = new Name("Justin", "Harder");
	private static final LocalDate GEBURTSDATUM = LocalDate.of(1998, 12, 6);
	private static final Benutzerangabe BENUTZERANGABE = new Benutzerangabe(
		Geschlecht.MAENNLICH,
		Erfahrung.BEGINNER,
		Ernaehrung.GUT,
		Schlafqualitaet.GUT,
		Stress.MITTELMAESSIG,
		Doping.NEIN,
		Regenerationsfaehigkeit.GUT)
		.setKraftlevel(Kraftlevel.CLASS_5);
	private static final Primaerschluessel PRIMAERSCHLUESSEL_AUTHENTIFIZIERUNG = new Primaerschluessel();
	private static final Authentifizierung AUTHENTIFIZIERUNG = new Authentifizierung(
		PRIMAERSCHLUESSEL_AUTHENTIFIZIERUNG,
		"mail@justinharder.de",
		"harder",
		Testdaten.PASSWORT);

	private Benutzer sut;

	@BeforeEach
	void setup()
	{
		sut = new Benutzer(PRIMAERSCHLUESSEL, NAME, GEBURTSDATUM, BENUTZERANGABE, AUTHENTIFIZIERUNG);
	}

	@Test
	@DisplayName("einen NoArgsConstructor und Setter besitzen")
	void test01()
	{
		var benutzer = new Benutzer()
			.setPrimaerschluessel(PRIMAERSCHLUESSEL)
			.setName(NAME)
			.setGeburtsdatum(GEBURTSDATUM)
			.setBenutzerangabe(BENUTZERANGABE)
			.setAuthentifizierung(AUTHENTIFIZIERUNG);
		AUTHENTIFIZIERUNG.setBenutzer(benutzer);

		assertAll(
			() -> assertThat(benutzer.getPrimaerschluessel()).isEqualTo(PRIMAERSCHLUESSEL),
			() -> assertThat(benutzer.getName()).isEqualTo(NAME),
			() -> assertThat(benutzer.getGeburtsdatum()).isEqualTo(GEBURTSDATUM),
			() -> assertThat(benutzer.getBenutzerangabe()).isEqualTo(BENUTZERANGABE),
			() -> assertThat(benutzer.getAuthentifizierung()).isEqualTo(AUTHENTIFIZIERUNG));
	}

	@Test
	@DisplayName("sich vergleichen")
	void test02()
	{
		EqualsVerifier.forClass(Benutzer.class)
			.withPrefabValues(Authentifizierung.class, Testdaten.AUTHENTIFIZIERUNG_JUSTIN,
				Testdaten.AUTHENTIFIZIERUNG_EDUARD)
			.withPrefabValues(Koerpermessung.class, Testdaten.KOERPERMESSUNG_JUSTIN, Testdaten.KOERPERMESSUNG_EDUARD)
			.withPrefabValues(Kraftwert.class, Testdaten.KRAFTWERT_WETTKAMPFBANKDRUECKEN,
				Testdaten.KRAFTWERT_LOWBAR_KNIEBEUGE)
			.suppress(Warning.STRICT_INHERITANCE)
			.suppress(Warning.SURROGATE_KEY)
			.suppress(Warning.NULL_FIELDS)
			.verify();
	}

	@Test
	@DisplayName("eine toString()-Methode haben")
	void test03()
	{
		assertThat(sut).hasToString("Benutzer{ID=" + sut.getPrimaerschluessel().getId().toString() + "}");
	}

	@Test
	@DisplayName("einen Kraftwert hinzufügen können")
	void test04()
	{
		var kraftwert = new Kraftwert(
			new Primaerschluessel(),
			100.0,
			80.0,
			LocalDate.now(),
			Wiederholungen.ONE_REP_MAX,
			Testdaten.UEBUNG_WETTKAMPFBANKDRUECKEN,
			sut);
		sut.fuegeKraftwertHinzu(kraftwert);

		assertThat(sut.getKraftwerte()).contains(kraftwert);
	}

	@Test
	@DisplayName("null validieren")
	void test05()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class,
				() -> new Benutzer(null, NAME, GEBURTSDATUM, BENUTZERANGABE, AUTHENTIFIZIERUNG)),
			() -> assertThrows(NullPointerException.class,
				() -> new Benutzer(PRIMAERSCHLUESSEL, null, GEBURTSDATUM, BENUTZERANGABE, AUTHENTIFIZIERUNG)),
			() -> assertThrows(NullPointerException.class,
				() -> new Benutzer(PRIMAERSCHLUESSEL, NAME, null, BENUTZERANGABE, AUTHENTIFIZIERUNG)),
			() -> assertThrows(NullPointerException.class,
				() -> new Benutzer(PRIMAERSCHLUESSEL, NAME, GEBURTSDATUM, null, AUTHENTIFIZIERUNG)),
			() -> assertThrows(NullPointerException.class,
				() -> new Benutzer(PRIMAERSCHLUESSEL, NAME, GEBURTSDATUM, BENUTZERANGABE, null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setPrimaerschluessel(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setName(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setGeburtsdatum(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setBenutzerangabe(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setAuthentifizierung(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.fuegeKraftwertHinzu(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.fuegeKoerpermessungHinzu(null)));
	}
}
