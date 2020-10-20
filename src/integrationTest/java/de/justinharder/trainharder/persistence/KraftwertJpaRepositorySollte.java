package de.justinharder.trainharder.persistence;

import de.justinharder.trainharder.model.domain.Kraftwert;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.model.domain.enums.Wiederholungen;
import de.justinharder.trainharder.setup.Testdaten;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class KraftwertJpaRepositorySollte extends JpaRepositorySollte
{
	private KraftwertJpaRepository sut;

	@Before
	public void setup()
	{
		sut = new KraftwertJpaRepository();
		sut.setEntityManager(erzeugeEntityManager());
	}

	@Test
	public void alleKraftwerteZuBenutzerErmitteln()
	{
		assertAll(
			() -> {
				var erwartet = List.of(
					Testdaten.KRAFTWERT_WETTKAMPFBANKDRUECKEN,
					Testdaten.KRAFTWERT_LOWBAR_KNIEBEUGE,
					Testdaten.KRAFTWERT_KONVENTIONELLES_KREUZHEBEN);

				var ergebnis = sut.ermittleAlleZuBenutzer(Testdaten.BENUTZER_JUSTIN_ID);

				assertThat(ergebnis).containsAll(erwartet);
			},
			() -> {
				var erwartet = new ArrayList<Kraftwert>();

				var ergebnis = sut.ermittleAlleZuBenutzer(Testdaten.BENUTZER_EDUARD_ID);

				assertThat(ergebnis).containsAll(erwartet);
			}
		);
	}

	@Test
	public void keinenKraftwertZuIdErmitteln()
	{
		var erwartet = Optional.empty();

		var ergebnis = sut.ermittleZuId(new Primaerschluessel());

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	public void kraftwertZuIdErmitteln()
	{
		assertAll(
			() -> {
				var erwartet = Optional.of(Testdaten.KRAFTWERT_WETTKAMPFBANKDRUECKEN);

				var ergebnis = sut.ermittleZuId(Testdaten.KRAFTWERT_WETTKAMPFBANKDRUECKEN_ID);

				assertThat(ergebnis).isEqualTo(erwartet);
			},
			() -> {
				var erwartet = Optional.of(Testdaten.KRAFTWERT_LOWBAR_KNIEBEUGE);

				var ergebnis = sut.ermittleZuId(Testdaten.KRAFTWERT_LOWBAR_KNIEBEUGE_ID);

				assertThat(ergebnis).isEqualTo(erwartet);
			},
			() -> {
				var erwartet = Optional.of(Testdaten.KRAFTWERT_KONVENTIONELLES_KREUZHEBEN);

				var ergebnis = sut.ermittleZuId(Testdaten.KRAFTWERT_KONVENTIONELLES_KREUZHEBEN_ID);

				assertThat(ergebnis).isEqualTo(erwartet);
			}
		);
	}

	@Test
	public void kraftwertErstellen()
	{
		var erwartet = new Kraftwert(
			new Primaerschluessel(),
			100,
			76.5,
			LocalDate.now(),
			Wiederholungen.ONE_REP_MAX,
			Testdaten.UEBUNG_WETTKAMPFBANKDRUECKEN,
			Testdaten.BENUTZER_JUSTIN);

		var ergebnis = sut.speichereKraftwert(erwartet);

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	public void kraftwertAktualisieren()
	{
		var erwartet = Testdaten.KRAFTWERT_WETTKAMPFBANKDRUECKEN
			.setGewicht(105);

		var ergebnis = sut.speichereKraftwert(erwartet);

		assertAll(
			() -> assertThat(ergebnis.getPrimaerschluessel()).isEqualTo(erwartet.getPrimaerschluessel()),
			() -> assertThat(ergebnis.getGewicht()).isEqualTo(erwartet.getGewicht()),
			() -> assertThat(ergebnis.getKoerpergewicht()).isEqualTo(erwartet.getKoerpergewicht()),
			() -> assertThat(ergebnis.getDatum()).isEqualTo(erwartet.getDatum()),
			() -> assertThat(ergebnis.getWiederholungen()).isEqualTo(erwartet.getWiederholungen()),
			() -> assertThat(ergebnis.getUebung()).isEqualTo(erwartet.getUebung()),
			() -> assertThat(ergebnis.getBenutzer()).isEqualTo(erwartet.getBenutzer())
		);
	}
}
