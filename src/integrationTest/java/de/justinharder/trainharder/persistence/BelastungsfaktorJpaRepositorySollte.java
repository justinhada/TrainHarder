package de.justinharder.trainharder.persistence;

import de.justinharder.trainharder.model.domain.Belastungsfaktor;
import de.justinharder.trainharder.model.domain.Uebung;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.model.domain.enums.Uebungsart;
import de.justinharder.trainharder.model.domain.enums.Uebungskategorie;
import de.justinharder.trainharder.setup.Testdaten;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class BelastungsfaktorJpaRepositorySollte extends JpaRepositorySollte
{
	private BelastungsfaktorJpaRepository sut;

	@Before
	public void setup()
	{
		sut = new BelastungsfaktorJpaRepository();

		sut.setEntityManager(erzeugeEntityManager());
	}

	@Test
	public void keinenBelastungsfaktorZuIdErmitteln()
	{
		var ergebnis = sut.ermittleZuId(new Primaerschluessel());

		assertThat(ergebnis).isEmpty();
	}

	@Test
	public void belastungsfaktorZuIdErmitteln()
	{
		assertAll(() ->
			{
				var erwartet = Testdaten.BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN;

				var ergebnis = sut.ermittleZuId(Testdaten.BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN_ID);

				assertThat(ergebnis).hasValue(erwartet);
			}, () ->
			{
				var erwartet = Testdaten.BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE;

				var ergebnis = sut.ermittleZuId(Testdaten.BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE_ID);

				assertThat(ergebnis).hasValue(erwartet);
			}, () ->
			{
				var erwartet = Testdaten.BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN;

				var ergebnis = sut.ermittleZuId(Testdaten.BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN_ID);

				assertThat(ergebnis).hasValue(erwartet);
			}
		);
	}

	@Test
	public void belastungsfaktorErstellen()
	{
		var erwartet = new Belastungsfaktor(
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
		erwartet.setUebung(new Uebung(
			new Primaerschluessel(),
			"Spoto Bankdrücken",
			Uebungsart.GRUNDUEBUNG,
			Uebungskategorie.WETTKAMPF_BANKDRUECKEN,
			erwartet));

		var ergebnis = sut.speichereBelastungsfaktor(erwartet);

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	public void belastungsfaktorAktualisieren()
	{
		var erwartet = Testdaten.BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN;
		erwartet.setTriceps(0.9);

		var ergebnis = sut.speichereBelastungsfaktor(erwartet);

		assertAll(
			() -> assertThat(ergebnis.getPrimaerschluessel()).isEqualTo(erwartet.getPrimaerschluessel()),
			() -> assertThat(ergebnis.getSquat()).isEqualTo(erwartet.getSquat()),
			() -> assertThat(ergebnis.getBenchpress()).isEqualTo(erwartet.getBenchpress()),
			() -> assertThat(ergebnis.getDeadlift()).isEqualTo(erwartet.getDeadlift()),
			() -> assertThat(ergebnis.getTriceps()).isEqualTo(erwartet.getTriceps()),
			() -> assertThat(ergebnis.getChest()).isEqualTo(erwartet.getChest()),
			() -> assertThat(ergebnis.getCore()).isEqualTo(erwartet.getCore()),
			() -> assertThat(ergebnis.getBack()).isEqualTo(erwartet.getBack()),
			() -> assertThat(ergebnis.getBiceps()).isEqualTo(erwartet.getBiceps()),
			() -> assertThat(ergebnis.getGlutes()).isEqualTo(erwartet.getGlutes()),
			() -> assertThat(ergebnis.getQuads()).isEqualTo(erwartet.getQuads()),
			() -> assertThat(ergebnis.getHamstrings()).isEqualTo(erwartet.getHamstrings()),
			() -> assertThat(ergebnis.getShoulder()).isEqualTo(erwartet.getShoulder()));
	}
}
