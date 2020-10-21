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

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class BenutzerSollte
{
	private Benutzer sut;

	@BeforeEach
	void setup()
	{
		sut = Testdaten.BENUTZER_JUSTIN;
	}

	@Test
	@DisplayName("einen NoArgsConstructor haben")
	void test01()
	{
		assertThat(Benutzer.class, allOf(hasValidBeanConstructor()));
	}

	@Test
	@DisplayName("einen RequiredArgsConstructor haben")
	void test02()
	{
		final var benutzerId = new Primaerschluessel();
		final var authentifizierungId = new Primaerschluessel();
		final var benutzer = new Benutzer(
			benutzerId,
			new Name("Justin", "Harder"),
			LocalDate.of(1998, 12, 6),
			new Benutzerangabe(
				Geschlecht.MAENNLICH,
				Erfahrung.BEGINNER,
				Ernaehrung.GUT,
				Schlafqualitaet.GUT,
				Stress.MITTELMAESSIG,
				Doping.NEIN,
				Regenerationsfaehigkeit.GUT),
			new Authentifizierung(
				authentifizierungId,
				"mail@justinharder.de",
				"harder",
				Testdaten.PASSWORT));

		assertAll(
			() -> assertThat(benutzer.getPrimaerschluessel()).isEqualTo(benutzerId),
			() -> assertThat(benutzer.getName().getVorname()).isEqualTo("Justin"),
			() -> assertThat(benutzer.getName().getNachname()).isEqualTo("Harder"),
			() -> assertThat(benutzer.getGeburtsdatum()).isEqualTo(LocalDate.of(1998, 12, 6)),
			() -> assertThat(benutzer.getBenutzerangabe().getKraftlevel()).isEqualTo(Kraftlevel.CLASS_5),
			() -> assertThat(benutzer.getBenutzerangabe().getGeschlecht()).isEqualTo(Geschlecht.MAENNLICH),
			() -> assertThat(benutzer.getBenutzerangabe().getErfahrung()).isEqualTo(Erfahrung.BEGINNER),
			() -> assertThat(benutzer.getBenutzerangabe().getErnaehrung()).isEqualTo(Ernaehrung.GUT),
			() -> assertThat(benutzer.getBenutzerangabe().getSchlafqualitaet()).isEqualTo(Schlafqualitaet.GUT),
			() -> assertThat(benutzer.getBenutzerangabe().getStress()).isEqualTo(Stress.MITTELMAESSIG),
			() -> assertThat(benutzer.getBenutzerangabe().getDoping()).isEqualTo(Doping.NEIN),
			() -> assertThat(benutzer.getBenutzerangabe().getRegenerationsfaehigkeit())
				.isEqualTo(Regenerationsfaehigkeit.GUT),
			() -> assertThat(benutzer.getAuthentifizierung().getPrimaerschluessel()).isEqualTo(authentifizierungId),
			() -> assertThat(benutzer.getAuthentifizierung().getMail()).isEqualTo("mail@justinharder.de"),
			() -> assertThat(benutzer.getAuthentifizierung().getBenutzername()).isEqualTo("harder"),
			() -> assertThat(benutzer.getAuthentifizierung().getPasswort()).isEqualTo(Testdaten.PASSWORT),
			() -> assertThat(benutzer.getAuthentifizierung().getBenutzer()).isEqualTo(benutzer));
	}

	@Test
	@DisplayName("Getter besitzen")
	void test03()
	{
		assertAll(
			() -> assertThat(sut.getPrimaerschluessel()).isEqualTo(Testdaten.BENUTZER_JUSTIN_ID),
			() -> assertThat(sut.getName().getVorname()).isEqualTo("Justin"),
			() -> assertThat(sut.getName().getNachname()).isEqualTo("Harder"),
			() -> assertThat(sut.getGeburtsdatum()).isEqualTo(LocalDate.of(1998, 12, 6)),
			() -> assertThat(sut.getBenutzerangabe().getKraftlevel()).isEqualTo(Kraftlevel.CLASS_5),
			() -> assertThat(sut.getBenutzerangabe().getGeschlecht()).isEqualTo(Geschlecht.MAENNLICH),
			() -> assertThat(sut.getBenutzerangabe().getErfahrung()).isEqualTo(Erfahrung.BEGINNER),
			() -> assertThat(sut.getBenutzerangabe().getErnaehrung()).isEqualTo(Ernaehrung.GUT),
			() -> assertThat(sut.getBenutzerangabe().getSchlafqualitaet()).isEqualTo(Schlafqualitaet.GUT),
			() -> assertThat(sut.getBenutzerangabe().getStress()).isEqualTo(Stress.MITTELMAESSIG),
			() -> assertThat(sut.getBenutzerangabe().getDoping()).isEqualTo(Doping.NEIN),
			() -> assertThat(sut.getBenutzerangabe().getRegenerationsfaehigkeit())
				.isEqualTo(Regenerationsfaehigkeit.GUT),
			() -> assertThat(sut.getKoerpergroesse()).isEqualTo(178),
			() -> assertThat(sut.getKoerpergewicht()).isEqualTo(90.0),
			() -> assertThat(sut.getKoerpermessungen()).contains(Testdaten.KOERPERMESSUNG_JUSTIN),
			() -> assertThat(sut.getAuthentifizierung()).isEqualTo(Testdaten.AUTHENTIFIZIERUNG_JUSTIN));
	}

	@Test
	@DisplayName("Setter besitzen")
	void test04()
	{
		final var benutzerId = new Primaerschluessel();
		final var authentifizierungId = new Primaerschluessel();
		final var authentifizierung = new Authentifizierung(
			authentifizierungId,
			"mail@justinharder.de",
			"harder",
			Testdaten.PASSWORT);
		final var benutzer = new Benutzer();
		benutzer.setPrimaerschluessel(benutzerId);
		benutzer.setName(new Name("Justin", "Harder"));
		benutzer.setGeburtsdatum(LocalDate.of(1998, 12, 6));
		benutzer.setBenutzerangabe(new Benutzerangabe(
			Geschlecht.MAENNLICH,
			Erfahrung.BEGINNER,
			Ernaehrung.GUT,
			Schlafqualitaet.GUT,
			Stress.MITTELMAESSIG,
			Doping.NEIN,
			Regenerationsfaehigkeit.GUT));
		benutzer.getBenutzerangabe().setKraftlevel(Kraftlevel.CLASS_5);
		benutzer.setAuthentifizierung(authentifizierung);
		authentifizierung.setBenutzer(benutzer);

		assertAll(
			() -> assertThat(benutzer.getPrimaerschluessel()).isEqualTo(benutzerId),
			() -> assertThat(benutzer.getName().getVorname()).isEqualTo("Justin"),
			() -> assertThat(benutzer.getName().getNachname()).isEqualTo("Harder"),
			() -> assertThat(benutzer.getGeburtsdatum()).isEqualTo(LocalDate.of(1998, 12, 6)),
			() -> assertThat(benutzer.getBenutzerangabe().getKraftlevel()).isEqualTo(Kraftlevel.CLASS_5),
			() -> assertThat(benutzer.getBenutzerangabe().getGeschlecht()).isEqualTo(Geschlecht.MAENNLICH),
			() -> assertThat(benutzer.getBenutzerangabe().getErfahrung()).isEqualTo(Erfahrung.BEGINNER),
			() -> assertThat(benutzer.getBenutzerangabe().getErnaehrung()).isEqualTo(Ernaehrung.GUT),
			() -> assertThat(benutzer.getBenutzerangabe().getSchlafqualitaet()).isEqualTo(Schlafqualitaet.GUT),
			() -> assertThat(benutzer.getBenutzerangabe().getStress()).isEqualTo(Stress.MITTELMAESSIG),
			() -> assertThat(benutzer.getBenutzerangabe().getDoping()).isEqualTo(Doping.NEIN),
			() -> assertThat(benutzer.getBenutzerangabe().getRegenerationsfaehigkeit())
				.isEqualTo(Regenerationsfaehigkeit.GUT),
			() -> assertThat(benutzer.getAuthentifizierung().getPrimaerschluessel()).isEqualTo(authentifizierungId),
			() -> assertThat(benutzer.getAuthentifizierung().getMail()).isEqualTo("mail@justinharder.de"),
			() -> assertThat(benutzer.getAuthentifizierung().getBenutzername()).isEqualTo("harder"),
			() -> assertThat(benutzer.getAuthentifizierung().getPasswort()).isEqualTo(Testdaten.PASSWORT),
			() -> assertThat(benutzer.getAuthentifizierung().getBenutzer()).isEqualTo(benutzer));
	}

	@Test
	@DisplayName("sich vergleichen")
	void test05()
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
	void test06()
	{
		final var erwartet = "Benutzer{ID=" + sut.getPrimaerschluessel().getId().toString() + "}";

		assertThat(sut).hasToString(erwartet);
	}

	@Test
	@DisplayName("einen Kraftwert hinzufügen können")
	void test07()
	{
		var kraftwert = new Kraftwert(
			new Primaerschluessel(),
			100.0,
			sut.getKoerpergewicht(),
			LocalDate.now(),
			Wiederholungen.ONE_REP_MAX,
			Testdaten.UEBUNG_WETTKAMPFBANKDRUECKEN,
			sut);
		sut.fuegeKraftwertHinzu(kraftwert);

		assertThat(sut.getKraftwerte()).contains(kraftwert);
	}
}
