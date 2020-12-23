package de.justinharder.trainharder.persistence;

import de.justinharder.trainharder.model.domain.Kraftwert;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.model.domain.enums.Wiederholungen;
import de.justinharder.trainharder.setup.Testdaten;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class KraftwertJpaRepositorySollte extends JpaRepositorySollte
{
	private KraftwertJpaRepository sut;

	@BeforeEach
	void setup()
	{
		sut = new KraftwertJpaRepository();
		sut.setEntityManager(getEntityManager());
	}

	@Test
	@DisplayName("alle Kraftwerte zu Benutzer ermitteln")
	void test01()
	{
		assertAll(
			() -> assertThat(sut.ermittleAlleZuBenutzer(Testdaten.BENUTZER_JUSTIN_ID))
				.containsExactlyInAnyOrder(Testdaten.KRAFTWERT_WETTKAMPFBANKDRUECKEN,
					Testdaten.KRAFTWERT_LOWBAR_KNIEBEUGE,
					Testdaten.KRAFTWERT_KONVENTIONELLES_KREUZHEBEN),
			() -> assertThat(sut.ermittleAlleZuBenutzer(Testdaten.BENUTZER_EDUARD_ID)).isEmpty());
	}

	@Test
	@DisplayName("keinen Kraftwert zu ID ermitteln")
	void test02()
	{
		assertThat(sut.ermittleZuId(new Primaerschluessel())).isEmpty();
	}

	@Test
	@DisplayName("Kraftwert zu ID ermitteln")
	void test03()
	{
		assertAll(
			() -> assertThat(sut.ermittleZuId(Testdaten.KRAFTWERT_WETTKAMPFBANKDRUECKEN_ID))
				.hasValue(Testdaten.KRAFTWERT_WETTKAMPFBANKDRUECKEN),
			() -> assertThat(sut.ermittleZuId(Testdaten.KRAFTWERT_LOWBAR_KNIEBEUGE_ID))
				.hasValue(Testdaten.KRAFTWERT_LOWBAR_KNIEBEUGE),
			() -> assertThat(sut.ermittleZuId(Testdaten.KRAFTWERT_KONVENTIONELLES_KREUZHEBEN_ID))
				.hasValue(Testdaten.KRAFTWERT_KONVENTIONELLES_KREUZHEBEN));
	}

	@Test
	@DisplayName("Kraftwert erstellen")
	void test04()
	{
		var kraftwert = new Kraftwert(
			new Primaerschluessel(),
			100,
			76.5,
			LocalDate.now(),
			Wiederholungen.ONE_REP_MAX,
			Testdaten.UEBUNG_WETTKAMPFBANKDRUECKEN,
			Testdaten.BENUTZER_JUSTIN);

		assertThat(sut.speichereKraftwert(kraftwert)).isEqualTo(kraftwert);
	}

	@Test
	@DisplayName("Kraftwert aktualisieren")
	void test05()
	{
		var kraftwert = Testdaten.KRAFTWERT_WETTKAMPFBANKDRUECKEN
			.setGewicht(105);

		var ergebnis = sut.speichereKraftwert(kraftwert);

		assertAll(
			() -> assertThat(ergebnis.getPrimaerschluessel()).isEqualTo(kraftwert.getPrimaerschluessel()),
			() -> assertThat(ergebnis.getGewicht()).isEqualTo(kraftwert.getGewicht()),
			() -> assertThat(ergebnis.getKoerpergewicht()).isEqualTo(kraftwert.getKoerpergewicht()),
			() -> assertThat(ergebnis.getDatum()).isEqualTo(kraftwert.getDatum()),
			() -> assertThat(ergebnis.getWiederholungen()).isEqualTo(kraftwert.getWiederholungen()),
			() -> assertThat(ergebnis.getUebung()).isEqualTo(kraftwert.getUebung()),
			() -> assertThat(ergebnis.getBenutzer()).isEqualTo(kraftwert.getBenutzer()));
	}

	@Test
	@DisplayName("null validieren")
	void test06()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> sut.setEntityManager(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.ermittleAlleZuBenutzer(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.ermittleZuId(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.speichereKraftwert(null)));
	}
}
