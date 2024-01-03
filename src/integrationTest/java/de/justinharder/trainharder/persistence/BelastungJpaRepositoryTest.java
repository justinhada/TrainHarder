package de.justinharder.trainharder.persistence;

import de.justinharder.trainharder.domain.model.Belastung;
import de.justinharder.trainharder.domain.model.Uebung;
import de.justinharder.trainharder.domain.model.embeddables.GrunduebungBelastung;
import de.justinharder.trainharder.domain.model.embeddables.OberkoerperBelastung;
import de.justinharder.trainharder.domain.model.embeddables.Primaerschluessel;
import de.justinharder.trainharder.domain.model.embeddables.UnterkoerperBelastung;
import de.justinharder.trainharder.domain.model.enums.Uebungsart;
import de.justinharder.trainharder.domain.model.enums.Uebungskategorie;
import de.justinharder.trainharder.setup.Testdaten;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@QuarkusTest
class BelastungJpaRepositorySollte
{
	@Inject
	BelastungsfaktorJpaRepository sut;

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
			() -> assertThat(sut.ermittleZuId(new Primaerschluessel())).hasValue(
				Testdaten.BELASTUNG_WETTKAMPFBANKDRUECKEN),
			() -> assertThat(sut.ermittleZuId(new Primaerschluessel())).hasValue(
				Testdaten.BELASTUNG_LOWBAR_KNIEBEUGE),
			() -> assertThat(sut.ermittleZuId(new Primaerschluessel())).hasValue(
				Testdaten.BELASTUNG_KONVENTIONELLES_KREUZHEBEN));
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
		belastungsfaktor.setUebung(new Uebung(new Primaerschluessel(), "Spoto Bankdrücken", Uebungsart.GRUNDUEBUNG,
			Uebungskategorie.BANKDRUECKEN_VARIATION, belastungsfaktor));

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
			() -> assertThat(ergebnis.getUnterkoerperBelastung()).isEqualTo(
				belastungsfaktor.getUnterkoerperBelastung()),
			() -> assertThat(ergebnis.getUebung()).isEqualTo(Testdaten.UEBUNG_WETTKAMPFBANKDRUECKEN));
	}

	@Test
	@DisplayName("null validieren")
	void test05()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> sut.ermittleZuId(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.speichereBelastungsfaktor(null)));
	}
}
