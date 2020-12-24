package de.justinharder.trainharder.model.domain;

import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.model.domain.enums.Uebungsart;
import de.justinharder.trainharder.model.domain.enums.Uebungskategorie;
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

class UebungSollte
{
	private static final Primaerschluessel PRIMAERSCHLUESSEL = new Primaerschluessel();
	private static final String NAME = "Wettkampfbankdrücken (pausiert)";

	private Uebung sut;

	@BeforeEach
	void setup()
	{
		sut = new Uebung(
			PRIMAERSCHLUESSEL,
			NAME,
			Uebungsart.GRUNDUEBUNG,
			Uebungskategorie.WETTKAMPF_BANKDRUECKEN,
			Testdaten.BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN);
	}

	@Test
	@DisplayName("einen NoArgsConstructor und Setter besitzen")
	void test01()
	{
		var uebung = new Uebung()
			.setPrimaerschluessel(PRIMAERSCHLUESSEL)
			.setName(NAME)
			.setUebungsart(Uebungsart.GRUNDUEBUNG)
			.setUebungskategorie(Uebungskategorie.WETTKAMPF_BANKDRUECKEN)
			.setBelastungsfaktor(Testdaten.BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN);

		assertAll(
			() -> assertThat(uebung.getPrimaerschluessel()).isEqualTo(PRIMAERSCHLUESSEL),
			() -> assertThat(uebung.getName()).isEqualTo(NAME),
			() -> assertThat(uebung.getUebungsart()).isEqualTo(Uebungsart.GRUNDUEBUNG),
			() -> assertThat(uebung.getUebungskategorie()).isEqualTo(Uebungskategorie.WETTKAMPF_BANKDRUECKEN),
			() -> assertThat(uebung.getBelastungsfaktor()).isEqualTo(Testdaten.BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN));
	}

	@Test
	@DisplayName("sich vergleichen")
	void test02()
	{
		EqualsVerifier.forClass(Uebung.class)
			.withPrefabValues(Belastungsfaktor.class, Testdaten.BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN,
				Testdaten.BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE)
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
		assertThat(sut).hasToString("Uebung{ID=" + sut.getPrimaerschluessel().getId().toString() + "}");
	}

	@Test
	@DisplayName("einen Kraftwert hinzufügen können")
	void test04()
	{
		var kraftwert = new Kraftwert(
			new Primaerschluessel(),
			100.0,
			Testdaten.BENUTZER_JUSTIN.getKoerpergewicht(),
			LocalDate.now(),
			Wiederholungen.ONE_REP_MAX,
			sut,
			Testdaten.BENUTZER_JUSTIN);
		sut.fuegeKraftwertHinzu(kraftwert);

		assertThat(sut.getKraftwerte()).contains(kraftwert);
	}

	@Test
	@DisplayName("null validieren")
	void test05()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> new Uebung(null, NAME, Uebungsart.GRUNDUEBUNG,
				Uebungskategorie.WETTKAMPF_BANKDRUECKEN, Testdaten.BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN)),
			() -> assertThrows(NullPointerException.class, () -> new Uebung(PRIMAERSCHLUESSEL, null,
				Uebungsart.GRUNDUEBUNG, Uebungskategorie.WETTKAMPF_BANKDRUECKEN,
				Testdaten.BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN)),
			() -> assertThrows(NullPointerException.class, () -> new Uebung(PRIMAERSCHLUESSEL, NAME, null,
				Uebungskategorie.WETTKAMPF_BANKDRUECKEN, Testdaten.BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN)),
			() -> assertThrows(NullPointerException.class, () -> new Uebung(PRIMAERSCHLUESSEL, NAME,
				Uebungsart.GRUNDUEBUNG, null, Testdaten.BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN)),
			() -> assertThrows(NullPointerException.class, () -> new Uebung(PRIMAERSCHLUESSEL, NAME,
				Uebungsart.GRUNDUEBUNG, Uebungskategorie.WETTKAMPF_BANKDRUECKEN, null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setPrimaerschluessel(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setName(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setUebungsart(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setUebungskategorie(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setBelastungsfaktor(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.fuegeKraftwertHinzu(null)));
	}
}
