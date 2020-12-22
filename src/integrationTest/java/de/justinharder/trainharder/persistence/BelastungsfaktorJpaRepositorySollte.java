package de.justinharder.trainharder.persistence;

import de.justinharder.trainharder.model.domain.Belastungsfaktor;
import de.justinharder.trainharder.model.domain.Uebung;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.model.domain.enums.Uebungsart;
import de.justinharder.trainharder.model.domain.enums.Uebungskategorie;
import de.justinharder.trainharder.setup.Testdaten;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class BelastungsfaktorJpaRepositorySollte extends JpaRepositorySollte
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
				.hasValue(Testdaten.BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN),
			() -> assertThat(sut.ermittleZuId(Testdaten.BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE_ID))
				.hasValue(Testdaten.BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE),
			() -> assertThat(sut.ermittleZuId(Testdaten.BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN_ID))
				.hasValue(Testdaten.BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN));
	}

	@Test
	@DisplayName("Belastungsfaktor erstellen")
	void test03()
	{
		var belastungsfaktor = new Belastungsfaktor(
			new Primaerschluessel(),
			0.0,
			1.0,
			0.0,
			1.0,
			0.0,
			0.0,
			0.0,
			0.0,
			0.0,
			0.1,
			0.0,
			0.7);
		belastungsfaktor.setUebung(new Uebung(
			new Primaerschluessel(),
			"Spoto Bankdrücken",
			Uebungsart.GRUNDUEBUNG,
			Uebungskategorie.WETTKAMPF_BANKDRUECKEN,
			belastungsfaktor));

		assertThat(sut.speichereBelastungsfaktor(belastungsfaktor)).isEqualTo(belastungsfaktor);
	}

	@Test
	@DisplayName("Belastungsfaktor aktualisieren")
	void test04()
	{
		var belastungsfaktor = Testdaten.BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN;
		belastungsfaktor.setTriceps(0.9);

		var ergebnis = sut.speichereBelastungsfaktor(belastungsfaktor);

		assertAll(
			() -> assertThat(ergebnis.getPrimaerschluessel()).isEqualTo(belastungsfaktor.getPrimaerschluessel()),
			() -> assertThat(ergebnis.getSquat()).isEqualTo(belastungsfaktor.getSquat()),
			() -> assertThat(ergebnis.getBenchpress()).isEqualTo(belastungsfaktor.getBenchpress()),
			() -> assertThat(ergebnis.getDeadlift()).isEqualTo(belastungsfaktor.getDeadlift()),
			() -> assertThat(ergebnis.getTriceps()).isEqualTo(belastungsfaktor.getTriceps()),
			() -> assertThat(ergebnis.getChest()).isEqualTo(belastungsfaktor.getChest()),
			() -> assertThat(ergebnis.getCore()).isEqualTo(belastungsfaktor.getCore()),
			() -> assertThat(ergebnis.getBack()).isEqualTo(belastungsfaktor.getBack()),
			() -> assertThat(ergebnis.getBiceps()).isEqualTo(belastungsfaktor.getBiceps()),
			() -> assertThat(ergebnis.getGlutes()).isEqualTo(belastungsfaktor.getGlutes()),
			() -> assertThat(ergebnis.getQuads()).isEqualTo(belastungsfaktor.getQuads()),
			() -> assertThat(ergebnis.getHamstrings()).isEqualTo(belastungsfaktor.getHamstrings()),
			() -> assertThat(ergebnis.getShoulder()).isEqualTo(belastungsfaktor.getShoulder()));
	}
}
