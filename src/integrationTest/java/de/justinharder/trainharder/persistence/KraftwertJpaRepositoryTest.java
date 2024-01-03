package de.justinharder.trainharder.persistence;

import de.justinharder.trainharder.domain.model.Kraftwert;
import de.justinharder.trainharder.domain.model.embeddables.Primaerschluessel;
import de.justinharder.trainharder.domain.model.enums.Wiederholungen;
import de.justinharder.trainharder.setup.Testdaten;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@QuarkusTest
class KraftwertJpaRepositorySollte
{
	@Inject
	KraftwertJpaRepository sut;

	@Test
	@DisplayName("alle Kraftwerte zu Benutzer ermitteln")
	void test01()
	{
		assertAll(
			() -> assertThat(sut.ermittleAlleZuBenutzer(new Primaerschluessel()))
				.containsExactlyInAnyOrder(Testdaten.KRAFTWERT_WETTKAMPFBANKDRUECKEN,
					Testdaten.KRAFTWERT_LOWBAR_KNIEBEUGE, Testdaten.KRAFTWERT_KONVENTIONELLES_KREUZHEBEN),
			() -> assertThat(sut.ermittleAlleZuBenutzer(new Primaerschluessel())).isEmpty());
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
			() -> assertThat(sut.ermittleZuId(new Primaerschluessel())).hasValue(
				Testdaten.KRAFTWERT_WETTKAMPFBANKDRUECKEN),
			() -> assertThat(sut.ermittleZuId(new Primaerschluessel())).hasValue(
				Testdaten.KRAFTWERT_LOWBAR_KNIEBEUGE),
			() -> assertThat(sut.ermittleZuId(new Primaerschluessel())).hasValue(
				Testdaten.KRAFTWERT_KONVENTIONELLES_KREUZHEBEN));
	}

	@Test
	@DisplayName("Kraftwert erstellen")
	void test04()
	{
		var kraftwert = new Kraftwert(
			new Primaerschluessel(),
			new BigDecimal(100),
			BigDecimal.valueOf(76.5),
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
			.setGewicht(new BigDecimal(105));

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
			() -> assertThrows(NullPointerException.class, () -> sut.ermittleAlleZuBenutzer(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.ermittleZuId(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.speichereKraftwert(null)));
	}
}
