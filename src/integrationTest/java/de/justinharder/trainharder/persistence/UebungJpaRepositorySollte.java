package de.justinharder.trainharder.persistence;

import de.justinharder.trainharder.model.domain.Belastungsfaktor;
import de.justinharder.trainharder.model.domain.Uebung;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.model.domain.enums.Uebungsart;
import de.justinharder.trainharder.model.domain.enums.Uebungskategorie;
import de.justinharder.trainharder.setup.Testdaten;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class UebungJpaRepositorySollte extends JpaRepositorySollte
{
	private UebungJpaRepository sut;

	@Before
	public void setup()
	{
		sut = new UebungJpaRepository(erzeugeEntityManager());
	}

	@Test
	public void alleUebungenErmitteln()
	{
		var erwartet = List.of(
			Testdaten.UEBUNG_WETTKAMPFBANKDRUECKEN,
			Testdaten.UEBUNG_LOWBAR_KNIEBEUGE,
			Testdaten.UEBUNG_KONVENTIONELLES_KREUZHEBEN);

		var ergebnis = sut.ermittleAlle();

		assertThat(ergebnis).containsAll(erwartet);
	}

	@Test
	public void alleUebungenZuUebungsartErmitteln()
	{
		var erwartet = List.of(
			Testdaten.UEBUNG_WETTKAMPFBANKDRUECKEN,
			Testdaten.UEBUNG_LOWBAR_KNIEBEUGE,
			Testdaten.UEBUNG_KONVENTIONELLES_KREUZHEBEN);

		var ergebnis = sut.ermittleAlleZuUebungsart(Uebungsart.GRUNDUEBUNG);

		assertThat(ergebnis).containsAll(erwartet);
	}

	@Test
	public void alleUebungenZuUebungskategorieErmitteln()
	{
		assertAll(
			() -> {
				var erwartet = List.of(Testdaten.UEBUNG_WETTKAMPFBANKDRUECKEN);

				var ergebnis = sut.ermittleAlleZuUebungskategorie(Uebungskategorie.WETTKAMPF_BANKDRUECKEN);

				assertThat(ergebnis).containsAll(erwartet);
			},
			() -> {
				var erwartet = List.of(Testdaten.UEBUNG_LOWBAR_KNIEBEUGE);

				var ergebnis = sut.ermittleAlleZuUebungskategorie(Uebungskategorie.WETTKAMPF_KNIEBEUGE);

				assertThat(ergebnis).containsAll(erwartet);
			},
			() -> {
				var erwartet = List.of(Testdaten.UEBUNG_KONVENTIONELLES_KREUZHEBEN);

				var ergebnis = sut.ermittleAlleZuUebungskategorie(Uebungskategorie.WETTKAMPF_KREUZHEBEN);

				assertThat(ergebnis).containsAll(erwartet);
			}
		);
	}

	@Test
	public void keineUebungZuIdErmitteln()
	{
		var erwartet = Optional.empty();

		var ergebnis = sut.ermittleZuId(new Primaerschluessel());

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	public void uebungZuIdErmitteln()
	{
		assertAll(
			() -> {
				var erwartet = Optional.of(Testdaten.UEBUNG_WETTKAMPFBANKDRUECKEN);

				var ergebnis = sut.ermittleZuId(Testdaten.UEBUNG_WETTKAMPFBANKDRUECKEN_ID);

				assertThat(ergebnis).isEqualTo(erwartet);
			},
			() -> {
				var erwartet = Optional.of(Testdaten.UEBUNG_LOWBAR_KNIEBEUGE);

				var ergebnis = sut.ermittleZuId(Testdaten.UEBUNG_LOWBAR_KNIEBEUGE_ID);

				assertThat(ergebnis).isEqualTo(erwartet);
			},
			() -> {
				var erwartet = Optional.of(Testdaten.UEBUNG_KONVENTIONELLES_KREUZHEBEN);

				var ergebnis = sut.ermittleZuId(Testdaten.UEBUNG_KONVENTIONELLES_KREUZHEBEN_ID);

				assertThat(ergebnis).isEqualTo(erwartet);
			}
		);
	}

	@Test
	public void uebungErstellen()
	{
		var erwartet = new Uebung(
			new Primaerschluessel(),
			"Spoto Bankdrücken",
			Uebungsart.GRUNDUEBUNG,
			Uebungskategorie.WETTKAMPF_BANKDRUECKEN,
			new Belastungsfaktor(
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
				0.7));

		var ergebnis = sut.speichereUebung(erwartet);

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	public void uebungAktualisieren()
	{
		var erwartet = Testdaten.UEBUNG_WETTKAMPFBANKDRUECKEN;
		erwartet.setName("Wettkampfbankdrücken (2s pausiert)");

		var ergebnis = sut.speichereUebung(erwartet);

		assertAll(
			() -> assertThat(ergebnis.getPrimaerschluessel()).isEqualTo(erwartet.getPrimaerschluessel()),
			() -> assertThat(ergebnis.getName()).isEqualTo(erwartet.getName()),
			() -> assertThat(ergebnis.getUebungsart()).isEqualTo(erwartet.getUebungsart()),
			() -> assertThat(ergebnis.getUebungskategorie()).isEqualTo(erwartet.getUebungskategorie()),
			() -> assertThat(ergebnis.getBelastungsfaktor()).isEqualTo(erwartet.getBelastungsfaktor())
		);
	}
}
