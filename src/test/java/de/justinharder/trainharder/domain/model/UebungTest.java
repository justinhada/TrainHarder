package de.justinharder.trainharder.domain.model;

import de.justinharder.trainharder.domain.model.embeddables.ID;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static de.justinharder.trainharder.domain.model.enums.Uebungsart.GRUNDUEBUNG;
import static de.justinharder.trainharder.domain.model.enums.Uebungskategorie.WETTKAMPF_BANKDRUECKEN;
import static de.justinharder.trainharder.setup.Testdaten.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Uebung sollte")
class UebungTest
{
	private static final ID ID = new ID();
	private static final String BEZEICHNUNG = "Wettkampfbankdrücken (pausiert)";

	private Uebung sut;

	@BeforeEach
	void setup()
	{
		sut = new Uebung(ID, BEZEICHNUNG, GRUNDUEBUNG, WETTKAMPF_BANKDRUECKEN, BELASTUNG_WETTKAMPFBANKDRUECKEN);
	}

	@Test
	@DisplayName("einen NoArgsConstructor und Setter besitzen")
	void test01()
	{
		var uebung = new Uebung()
			.setBezeichnung(BEZEICHNUNG)
			.setUebungsart(GRUNDUEBUNG)
			.setUebungskategorie(WETTKAMPF_BANKDRUECKEN)
			.setBelastung(BELASTUNG_WETTKAMPFBANKDRUECKEN);

		assertAll(
			() -> assertThat(uebung.getBezeichnung()).isEqualTo(BEZEICHNUNG),
			() -> assertThat(uebung.getUebungsart()).isEqualTo(GRUNDUEBUNG),
			() -> assertThat(uebung.getUebungskategorie()).isEqualTo(WETTKAMPF_BANKDRUECKEN),
			() -> assertThat(uebung.getBelastung()).isEqualTo(BELASTUNG_WETTKAMPFBANKDRUECKEN));
	}

	@Test
	@DisplayName("sich vergleichen")
	void test02()
	{
		EqualsVerifier.forClass(Uebung.class)
			.withPrefabValues(Belastung.class, BELASTUNG_WETTKAMPFBANKDRUECKEN, BELASTUNG_LOWBAR_KNIEBEUGE)
			.withPrefabValues(Kraftwert.class, KRAFTWERT_WETTKAMPFBANKDRUECKEN, KRAFTWERT_LOWBAR_KNIEBEUGE)
			.suppress(Warning.STRICT_INHERITANCE)
			.suppress(Warning.SURROGATE_KEY)
			.suppress(Warning.NULL_FIELDS)
			.verify();
	}

	@Test
	@DisplayName("eine toString()-Methode haben")
	void test03()
	{
		assertThat(sut).hasToString("Uebung{ID=" + sut.getId().getWert() + "}");
	}

	@Test
	@DisplayName("null validieren")
	void test05()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class,
				() -> new Uebung(null, BEZEICHNUNG, GRUNDUEBUNG, WETTKAMPF_BANKDRUECKEN,
					BELASTUNG_WETTKAMPFBANKDRUECKEN)),
			() -> assertThrows(NullPointerException.class,
				() -> new Uebung(ID, null, GRUNDUEBUNG, WETTKAMPF_BANKDRUECKEN, BELASTUNG_WETTKAMPFBANKDRUECKEN)),
			() -> assertThrows(NullPointerException.class,
				() -> new Uebung(ID, BEZEICHNUNG, null, WETTKAMPF_BANKDRUECKEN, BELASTUNG_WETTKAMPFBANKDRUECKEN)),
			() -> assertThrows(NullPointerException.class,
				() -> new Uebung(ID, BEZEICHNUNG, GRUNDUEBUNG, null, BELASTUNG_WETTKAMPFBANKDRUECKEN)),
			() -> assertThrows(NullPointerException.class,
				() -> new Uebung(ID, BEZEICHNUNG, GRUNDUEBUNG, WETTKAMPF_BANKDRUECKEN, null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setBezeichnung(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setUebungsart(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setUebungskategorie(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setBelastung(null)));
	}
}
