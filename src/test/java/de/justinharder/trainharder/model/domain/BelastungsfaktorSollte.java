package de.justinharder.trainharder.model.domain;

import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.setup.Testdaten;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BelastungsfaktorSollte
{
	private static final Primaerschluessel PRIMAERSCHLUESSEL = new Primaerschluessel();

	private Belastungsfaktor sut;

	@BeforeEach
	void setup()
	{
		sut = new Belastungsfaktor(PRIMAERSCHLUESSEL, 0, 1, 0, 0.7, 1, 0, 0, 0, 0, 0, 0, 0.1);
	}

	@Test
	@DisplayName("einen NoArgsConstructor und Setter besitzen")
	void test01()
	{
		var id = new Primaerschluessel();
		var belastungsfaktor = new Belastungsfaktor()
			.setPrimaerschluessel(PRIMAERSCHLUESSEL)
			.setSquat(0)
			.setBenchpress(1)
			.setDeadlift(0)
			.setTriceps(0.7)
			.setChest(1)
			.setCore(0)
			.setBack(0)
			.setBiceps(0)
			.setGlutes(0)
			.setQuads(0)
			.setHamstrings(0)
			.setShoulder(0.1)
			.setUebung(Testdaten.UEBUNG_WETTKAMPFBANKDRUECKEN);

		assertAll(
			() -> assertThat(belastungsfaktor.getPrimaerschluessel()).isEqualTo(PRIMAERSCHLUESSEL),
			() -> assertThat(belastungsfaktor.getSquat()).isZero(),
			() -> assertThat(belastungsfaktor.getBenchpress()).isEqualTo(1),
			() -> assertThat(belastungsfaktor.getDeadlift()).isZero(),
			() -> assertThat(belastungsfaktor.getTriceps()).isEqualTo(0.7),
			() -> assertThat(belastungsfaktor.getChest()).isEqualTo(1),
			() -> assertThat(belastungsfaktor.getCore()).isZero(),
			() -> assertThat(belastungsfaktor.getBack()).isZero(),
			() -> assertThat(belastungsfaktor.getBiceps()).isZero(),
			() -> assertThat(belastungsfaktor.getGlutes()).isZero(),
			() -> assertThat(belastungsfaktor.getQuads()).isZero(),
			() -> assertThat(belastungsfaktor.getHamstrings()).isZero(),
			() -> assertThat(belastungsfaktor.getShoulder()).isEqualTo(0.1),
			() -> assertThat(belastungsfaktor.getUebung()).isEqualTo(Testdaten.UEBUNG_WETTKAMPFBANKDRUECKEN));
	}

	@Test
	@DisplayName("sich vergleichen")
	void test02()
	{
		EqualsVerifier.forClass(Belastungsfaktor.class)
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
		assertThat(sut).hasToString("Belastungsfaktor{ID=" + sut.getPrimaerschluessel().getId().toString() + "}");
	}

	@Test
	@DisplayName("null validieren")
	void test04()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> new Belastungsfaktor(null, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0)),
			() -> assertThrows(NullPointerException.class, () -> sut.setPrimaerschluessel(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setUebung(null)));
	}
}
