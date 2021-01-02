package de.justinharder.trainharder.persistence;

import de.justinharder.trainharder.model.domain.Belastung;
import de.justinharder.trainharder.model.domain.Uebung;
import de.justinharder.trainharder.model.domain.embeddables.GrunduebungBelastung;
import de.justinharder.trainharder.model.domain.embeddables.OberkoerperBelastung;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.model.domain.embeddables.UnterkoerperBelastung;
import de.justinharder.trainharder.model.domain.enums.Uebungsart;
import de.justinharder.trainharder.model.domain.enums.Uebungskategorie;
import de.justinharder.trainharder.setup.Testdaten;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BelastungJpaRepositorySollte extends JpaRepositorySollte
{
	private BelastungsfaktorJpaRepository sut;

	@BeforeEach
	void setup()
	{
		sut = new BelastungsfaktorJpaRepository();

		sut.setEntityManager(getEntityManager());
	}

	@Test
	@DisplayName("keinen Belastungsfaktor zu ID ermitteln")
	void test01()
	{
		assertThat(sut.ermittleZuId(new Primaerschluessel())).isEmpty();
	}

	@Test
	@DisplayName("Belastungsfaktor zu ID ermitteln")
	void test02()
	{
		assertAll(
			() -> assertThat(sut.ermittleZuId(Testdaten.BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN_ID))
				.hasValue(Testdaten.BELASTUNG_WETTKAMPFBANKDRUECKEN),
			() -> assertThat(sut.ermittleZuId(Testdaten.BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE_ID))
				.hasValue(Testdaten.BELASTUNG_LOWBAR_KNIEBEUGE),
			() -> assertThat(sut.ermittleZuId(Testdaten.BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN_ID))
				.hasValue(Testdaten.BELASTUNG_KONVENTIONELLES_KREUZHEBEN));
	}

	@Test
	@DisplayName("Belastungsfaktor erstellen")
	void test03()
	{
		var belastungsfaktor = new Belastung(
			new Primaerschluessel(),
			new GrunduebungBelastung(0.0, 1.0, 0.0),
			new OberkoerperBelastung(0.7, 1.0, 0.0, 0.0, 0.0, 0.1),
			new UnterkoerperBelastung(0.0, 0.0, 0.0));
		belastungsfaktor.setUebung(new Uebung(
			new Primaerschluessel(),
			"Spoto Bankdrücken",
			Uebungsart.GRUNDUEBUNG,
			Uebungskategorie.BANKDRUECKEN_VARIATION,
			belastungsfaktor));

		assertThat(sut.speichereBelastungsfaktor(belastungsfaktor)).isEqualTo(belastungsfaktor);
	}

	@Test
	@DisplayName("Belastungsfaktor aktualisieren")
	void test04()
	{
		var belastungsfaktor = Testdaten.BELASTUNG_WETTKAMPFBANKDRUECKEN;
		belastungsfaktor.getOberkoerperBelastung().setTriceps(0.9);

		var ergebnis = sut.speichereBelastungsfaktor(belastungsfaktor);

		assertAll(
			() -> assertThat(ergebnis.getPrimaerschluessel()).isEqualTo(belastungsfaktor.getPrimaerschluessel()),
			() -> assertThat(ergebnis.getGrunduebungBelastung()).isEqualTo(belastungsfaktor.getGrunduebungBelastung()),
			() -> assertThat(ergebnis.getOberkoerperBelastung()).isEqualTo(belastungsfaktor.getOberkoerperBelastung()),
			() -> assertThat(ergebnis.getUnterkoerperBelastung()).isEqualTo(belastungsfaktor.getUnterkoerperBelastung()),
			() -> assertThat(ergebnis.getUebung()).isEqualTo(Testdaten.UEBUNG_WETTKAMPFBANKDRUECKEN));
	}

	@Test
	@DisplayName("null validieren")
	void test05()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> sut.setEntityManager(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.ermittleZuId(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.speichereBelastungsfaktor(null)));
	}
}
