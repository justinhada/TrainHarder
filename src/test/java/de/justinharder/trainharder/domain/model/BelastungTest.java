package de.justinharder.trainharder.domain.model;

import de.justinharder.trainharder.domain.model.embeddables.GrunduebungBelastung;
import de.justinharder.trainharder.domain.model.embeddables.OberkoerperBelastung;
import de.justinharder.trainharder.domain.model.embeddables.Primaerschluessel;
import de.justinharder.trainharder.domain.model.embeddables.UnterkoerperBelastung;
import de.justinharder.trainharder.setup.Testdaten;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BelastungSollte
{
	private static final Primaerschluessel PRIMAERSCHLUESSEL = new Primaerschluessel();
	private static final GrunduebungBelastung GRUNDUEBUNG_BELASTUNG = new GrunduebungBelastung(1.0, 0.0, 0.0);
	private static final OberkoerperBelastung OBERKOERPER_BELASTUNG = new OberkoerperBelastung(0.7, 1.0, 0.0, 0.0, 0.0, 0.1);
	private static final UnterkoerperBelastung UNTERKOERPER_BELASTUNG = new UnterkoerperBelastung(1.0, 1.0, 0.5);

	private Belastung sut;

	@BeforeEach
	void setup()
	{
		sut = new Belastung(PRIMAERSCHLUESSEL, GRUNDUEBUNG_BELASTUNG, OBERKOERPER_BELASTUNG, UNTERKOERPER_BELASTUNG);
	}

	@Test
	@DisplayName("einen NoArgsConstructor und Setter besitzen")
	void test01()
	{
		sut = new Belastung()
			.setPrimaerschluessel(PRIMAERSCHLUESSEL)
			.setGrunduebungBelastung(GRUNDUEBUNG_BELASTUNG)
			.setOberkoerperBelastung(OBERKOERPER_BELASTUNG)
			.setUnterkoerperBelastung(UNTERKOERPER_BELASTUNG)
			.setUebung(Testdaten.UEBUNG_WETTKAMPFBANKDRUECKEN);

		assertAll(
			() -> assertThat(sut.getPrimaerschluessel()).isEqualTo(PRIMAERSCHLUESSEL),
			() -> assertThat(sut.getGrunduebungBelastung()).isEqualTo(GRUNDUEBUNG_BELASTUNG),
			() -> assertThat(sut.getOberkoerperBelastung()).isEqualTo(OBERKOERPER_BELASTUNG),
			() -> assertThat(sut.getUnterkoerperBelastung()).isEqualTo(UNTERKOERPER_BELASTUNG),
			() -> assertThat(sut.getUebung()).isEqualTo(Testdaten.UEBUNG_WETTKAMPFBANKDRUECKEN));
	}

	@Test
	@DisplayName("sich vergleichen")
	void test02()
	{
		EqualsVerifier.forClass(Belastung.class)
			.withPrefabValues(Uebung.class, Testdaten.UEBUNG_WETTKAMPFBANKDRUECKEN, Testdaten.UEBUNG_LOWBAR_KNIEBEUGE)
			.suppress(Warning.STRICT_INHERITANCE)
			.suppress(Warning.SURROGATE_KEY)
			.suppress(Warning.NULL_FIELDS)
			.verify();
	}

	@Test
	@DisplayName("eine toString()-Methode haben")
	void test03()
	{
		assertThat(sut).hasToString("Belastung{ID=" + PRIMAERSCHLUESSEL.getId().toString() + "}");
	}

	@Test
	@DisplayName("null validieren")
	void test04()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> new Belastung(null, GRUNDUEBUNG_BELASTUNG, OBERKOERPER_BELASTUNG, UNTERKOERPER_BELASTUNG)),
			() -> assertThrows(NullPointerException.class, () -> new Belastung(PRIMAERSCHLUESSEL, null, OBERKOERPER_BELASTUNG, UNTERKOERPER_BELASTUNG)),
			() -> assertThrows(NullPointerException.class, () -> new Belastung(PRIMAERSCHLUESSEL, GRUNDUEBUNG_BELASTUNG, null, UNTERKOERPER_BELASTUNG)),
			() -> assertThrows(NullPointerException.class, () -> new Belastung(PRIMAERSCHLUESSEL, GRUNDUEBUNG_BELASTUNG, OBERKOERPER_BELASTUNG, null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setPrimaerschluessel(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setGrunduebungBelastung(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setOberkoerperBelastung(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setUnterkoerperBelastung(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setUebung(null)));
	}
}
