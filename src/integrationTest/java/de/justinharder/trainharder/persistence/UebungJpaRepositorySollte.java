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

class UebungJpaRepositorySollte extends JpaRepositorySollte
{
	private UebungJpaRepository sut;

	@BeforeEach
	void setup()
	{
		sut = new UebungJpaRepository();

		sut.setEntityManager(getEntityManager());
	}

	@Test
	@DisplayName("alle Uebungen ermitteln")
	void test01()
	{
		assertThat(sut.ermittleAlle()).containsExactlyInAnyOrder(Testdaten.UEBUNG_WETTKAMPFBANKDRUECKEN, Testdaten.UEBUNG_LOWBAR_KNIEBEUGE, Testdaten.UEBUNG_KONVENTIONELLES_KREUZHEBEN);
	}

	@Test
	@DisplayName("alle Uebungen zu Uebungsart ermitteln")
	void test02()
	{
		assertThat(sut.ermittleAlleZuUebungsart(Uebungsart.GRUNDUEBUNG))
			.containsExactlyInAnyOrder(Testdaten.UEBUNG_WETTKAMPFBANKDRUECKEN, Testdaten.UEBUNG_LOWBAR_KNIEBEUGE, Testdaten.UEBUNG_KONVENTIONELLES_KREUZHEBEN);
	}

	@Test
	@DisplayName("alle Uebungen zu Uebungskategorie ermitteln")
	void test03()
	{
		assertAll(
			() -> assertThat(sut.ermittleAlleZuUebungskategorie(Uebungskategorie.WETTKAMPF_BANKDRUECKEN)).containsExactlyInAnyOrder(Testdaten.UEBUNG_WETTKAMPFBANKDRUECKEN),
			() -> assertThat(sut.ermittleAlleZuUebungskategorie(Uebungskategorie.WETTKAMPF_KNIEBEUGE)).containsExactlyInAnyOrder(Testdaten.UEBUNG_LOWBAR_KNIEBEUGE),
			() -> assertThat(sut.ermittleAlleZuUebungskategorie(Uebungskategorie.WETTKAMPF_KREUZHEBEN)).containsExactlyInAnyOrder(Testdaten.UEBUNG_KONVENTIONELLES_KREUZHEBEN));
	}

	@Test
	@DisplayName("keine Uebung zu ID ermitteln")
	void test04()
	{
		assertThat(sut.ermittleZuId(new Primaerschluessel())).isEmpty();
	}

	@Test
	@DisplayName("Uebung zu ID ermitteln")
	void test05()
	{
		assertAll(
			() -> assertThat(sut.ermittleZuId(Testdaten.UEBUNG_WETTKAMPFBANKDRUECKEN_ID)).hasValue(Testdaten.UEBUNG_WETTKAMPFBANKDRUECKEN),
			() -> assertThat(sut.ermittleZuId(Testdaten.UEBUNG_LOWBAR_KNIEBEUGE_ID)).hasValue(Testdaten.UEBUNG_LOWBAR_KNIEBEUGE),
			() -> assertThat(sut.ermittleZuId(Testdaten.UEBUNG_KONVENTIONELLES_KREUZHEBEN_ID)).hasValue(Testdaten.UEBUNG_KONVENTIONELLES_KREUZHEBEN));
	}

	@Test
	@DisplayName("Uebung erstellen")
	void test06()
	{
		var uebung = new Uebung(
			new Primaerschluessel(),
			"Spoto Bankdrücken",
			Uebungsart.GRUNDUEBUNG,
			Uebungskategorie.WETTKAMPF_BANKDRUECKEN,
			new Belastung(
				new Primaerschluessel(),
				new GrunduebungBelastung(0.0, 1.0, 0.0),
				new OberkoerperBelastung(0.7, 1.0, 0.0, 0.0, 0.0, 0.1),
				new UnterkoerperBelastung(0.0, 0.0, 0.0)));

		assertThat(sut.speichereUebung(uebung)).isEqualTo(uebung);
	}

	@Test
	@DisplayName("Uebung aktualisieren")
	void test07()
	{
		var uebung = Testdaten.UEBUNG_WETTKAMPFBANKDRUECKEN
			.setName("Wettkampfbankdrücken (2s pausiert)");

		var ergebnis = sut.speichereUebung(uebung);

		assertAll(
			() -> assertThat(ergebnis.getPrimaerschluessel()).isEqualTo(uebung.getPrimaerschluessel()),
			() -> assertThat(ergebnis.getName()).isEqualTo(uebung.getName()),
			() -> assertThat(ergebnis.getUebungsart()).isEqualTo(uebung.getUebungsart()),
			() -> assertThat(ergebnis.getUebungskategorie()).isEqualTo(uebung.getUebungskategorie()),
			() -> assertThat(ergebnis.getBelastung()).isEqualTo(uebung.getBelastung()));
	}

	@Test
	@DisplayName("null validieren")
	void test08()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> sut.setEntityManager(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.ermittleAlleZuUebungsart(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.ermittleAlleZuUebungskategorie(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.ermittleZuId(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.speichereUebung(null)));
	}
}