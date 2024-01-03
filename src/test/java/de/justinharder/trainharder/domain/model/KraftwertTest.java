package de.justinharder.trainharder.domain.model;

import de.justinharder.trainharder.domain.model.embeddables.ID;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static de.justinharder.trainharder.domain.model.enums.Wiederholungen.ONE_REP_MAX;
import static de.justinharder.trainharder.setup.Testdaten.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Kraftwert sollte")
class KraftwertTest
{
	private static final ID ID = new ID();
	private static final BigDecimal GEWICHT = new BigDecimal(100);
	private static final LocalDate DATUM = LocalDate.now();

	private Kraftwert sut;

	@BeforeEach
	void setup()
	{
		sut = new Kraftwert(
			ID,
			DATUM,
			GEWICHT,
			ONE_REP_MAX,
			UEBUNG_WETTKAMPFBANKDRUECKEN,
			BENUTZER_JUSTIN);
	}

	@Test
	@DisplayName("einen NoArgsConstructor und Setter besitzen")
	void test01()
	{
		var kraftwert = new Kraftwert()
			.setDatum(DATUM)
			.setGewicht(GEWICHT)
			.setWiederholungen(ONE_REP_MAX)
			.setUebung(UEBUNG_WETTKAMPFBANKDRUECKEN)
			.setBenutzer(BENUTZER_JUSTIN);

		assertAll(
			() -> assertThat(kraftwert.getDatum()).isEqualTo(DATUM),
			() -> assertThat(kraftwert.getGewicht()).isEqualTo(GEWICHT),
			() -> assertThat(kraftwert.getWiederholungen()).isEqualTo(ONE_REP_MAX),
			() -> assertThat(kraftwert.getUebung()).isEqualTo(UEBUNG_WETTKAMPFBANKDRUECKEN),
			() -> assertThat(kraftwert.getBenutzer()).isEqualTo(BENUTZER_JUSTIN));
	}

	@Test
	@DisplayName("sich vergleichen")
	void test02()
	{
		EqualsVerifier.forClass(Kraftwert.class)
			.withPrefabValues(Uebung.class, UEBUNG_WETTKAMPFBANKDRUECKEN, UEBUNG_LOWBAR_KNIEBEUGE)
			.withPrefabValues(Benutzer.class, BENUTZER_JUSTIN, BENUTZER_EDUARD)
			.suppress(Warning.STRICT_INHERITANCE)
			.suppress(Warning.SURROGATE_KEY)
			.suppress(Warning.NULL_FIELDS)
			.verify();
	}

	@Test
	@DisplayName("eine toString()-Methode haben")
	void test03()
	{
		assertThat(sut).hasToString("Kraftwert{ID=" + sut.getId().getWert() + "}");
	}

	@Test
	@DisplayName("null validieren")
	void test04()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class,
				() -> new Kraftwert(null, DATUM, GEWICHT, ONE_REP_MAX, UEBUNG_WETTKAMPFBANKDRUECKEN, BENUTZER_JUSTIN)),
			() -> assertThrows(NullPointerException.class,
				() -> new Kraftwert(ID, null, GEWICHT, ONE_REP_MAX, UEBUNG_WETTKAMPFBANKDRUECKEN, BENUTZER_JUSTIN)),
			() -> assertThrows(NullPointerException.class,
				() -> new Kraftwert(ID, DATUM, null, ONE_REP_MAX, UEBUNG_WETTKAMPFBANKDRUECKEN, BENUTZER_JUSTIN)),
			() -> assertThrows(NullPointerException.class,
				() -> new Kraftwert(ID, DATUM, GEWICHT, null, UEBUNG_WETTKAMPFBANKDRUECKEN, BENUTZER_JUSTIN)),
			() -> assertThrows(NullPointerException.class,
				() -> new Kraftwert(ID, DATUM, GEWICHT, ONE_REP_MAX, null, BENUTZER_JUSTIN)),
			() -> assertThrows(NullPointerException.class,
				() -> new Kraftwert(ID, DATUM, GEWICHT, ONE_REP_MAX, UEBUNG_WETTKAMPFBANKDRUECKEN, null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setGewicht(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setDatum(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setWiederholungen(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setUebung(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setBenutzer(null)));
	}
}
