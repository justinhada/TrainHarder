package de.justinharder.trainharder.model.domain;

import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.model.domain.enums.Wiederholungen;
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

class KraftwertSollte
{
	private static final Primaerschluessel PRIMAERSCHLUESSEL = new Primaerschluessel();
	private static final int GEWICHT = 100;
	private static final LocalDate DATUM = LocalDate.now();

	private Kraftwert sut;

	@BeforeEach
	void setup()
	{
		sut = new Kraftwert(
			PRIMAERSCHLUESSEL,
			GEWICHT,
			Testdaten.BENUTZER_JUSTIN.getKoerpergewicht(),
			DATUM,
			Wiederholungen.ONE_REP_MAX,
			Testdaten.UEBUNG_WETTKAMPFBANKDRUECKEN,
			Testdaten.BENUTZER_JUSTIN);
	}

	@Test
	@DisplayName("einen NoArgsConstructor und Setter besitzen")
	void test01()
	{
		var kraftwert = new Kraftwert()
			.setPrimaerschluessel(PRIMAERSCHLUESSEL)
			.setGewicht(GEWICHT)
			.setKoerpergewicht(Testdaten.BENUTZER_JUSTIN.getKoerpergewicht())
			.setDatum(DATUM)
			.setWiederholungen(Wiederholungen.ONE_REP_MAX)
			.setUebung(Testdaten.UEBUNG_WETTKAMPFBANKDRUECKEN)
			.setBenutzer(Testdaten.BENUTZER_JUSTIN);

		assertAll(
			() -> assertThat(kraftwert.getPrimaerschluessel()).isEqualTo(PRIMAERSCHLUESSEL),
			() -> assertThat(kraftwert.getGewicht()).isEqualTo(GEWICHT),
			() -> assertThat(kraftwert.getKoerpergewicht()).isEqualTo(Testdaten.BENUTZER_JUSTIN.getKoerpergewicht()),
			() -> assertThat(kraftwert.getDatum()).isEqualTo(DATUM),
			() -> assertThat(kraftwert.getWiederholungen()).isEqualTo(Wiederholungen.ONE_REP_MAX),
			() -> assertThat(kraftwert.getUebung()).isEqualTo(Testdaten.UEBUNG_WETTKAMPFBANKDRUECKEN),
			() -> assertThat(kraftwert.getBenutzer()).isEqualTo(Testdaten.BENUTZER_JUSTIN));
	}

	@Test
	@DisplayName("sich vergleichen")
	void test02()
	{
		EqualsVerifier.forClass(Kraftwert.class)
			.withPrefabValues(Uebung.class, Testdaten.UEBUNG_WETTKAMPFBANKDRUECKEN, Testdaten.UEBUNG_LOWBAR_KNIEBEUGE)
			.withPrefabValues(Benutzer.class, Testdaten.BENUTZER_JUSTIN, Testdaten.BENUTZER_EDUARD)
			.suppress(Warning.STRICT_INHERITANCE)
			.suppress(Warning.SURROGATE_KEY)
			.suppress(Warning.NULL_FIELDS)
			.verify();
	}

	@Test
	@DisplayName("eine toString()-Methode haben")
	void test03()
	{
		assertThat(sut).hasToString("Kraftwert{ID=" + sut.getPrimaerschluessel().getId().toString() + "}");
	}

	@Test
	@DisplayName("null validieren")
	void test04()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> new Kraftwert(null, 0, 0,
				DATUM, Wiederholungen.ONE_REP_MAX, Testdaten.UEBUNG_WETTKAMPFBANKDRUECKEN, Testdaten.BENUTZER_JUSTIN)),
			() -> assertThrows(NullPointerException.class, () -> new Kraftwert(PRIMAERSCHLUESSEL, GEWICHT, 0,
				null, Wiederholungen.ONE_REP_MAX, Testdaten.UEBUNG_WETTKAMPFBANKDRUECKEN, Testdaten.BENUTZER_JUSTIN)),
			() -> assertThrows(NullPointerException.class, () -> new Kraftwert(PRIMAERSCHLUESSEL, GEWICHT, 0,
				DATUM, null, Testdaten.UEBUNG_WETTKAMPFBANKDRUECKEN, Testdaten.BENUTZER_JUSTIN)),
			() -> assertThrows(NullPointerException.class, () -> new Kraftwert(PRIMAERSCHLUESSEL, GEWICHT, 0,
				DATUM, Wiederholungen.ONE_REP_MAX, null, Testdaten.BENUTZER_JUSTIN)),
			() -> assertThrows(NullPointerException.class, () -> new Kraftwert(PRIMAERSCHLUESSEL, GEWICHT, 0,
				DATUM, Wiederholungen.ONE_REP_MAX, Testdaten.UEBUNG_WETTKAMPFBANKDRUECKEN, null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setPrimaerschluessel(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setDatum(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setWiederholungen(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setUebung(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setBenutzer(null)));
	}
}
