package de.justinharder.trainharder.model.domain;

import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.setup.Testdaten;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.allOf;
import static org.junit.jupiter.api.Assertions.assertAll;

class BelastungsfaktorSollte
{
	private Belastungsfaktor sut;

	@BeforeEach
	void setup()
	{
		sut = Testdaten.BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN;
	}

	@Test
	@DisplayName("einen NoArgsConstructor haben")
	void test01()
	{
		org.hamcrest.MatcherAssert.assertThat(Belastungsfaktor.class, allOf(hasValidBeanConstructor()));
	}

	@Test
	@DisplayName("einen RequiredArgsConstructor haben")
	void test02()
	{
		var id = new Primaerschluessel();
		var belastungsfaktor = new Belastungsfaktor(id, 0, 1, 0, 0.7, 1, 0, 0, 0, 0, 0, 0, 0.1);

		assertAll(
			() -> assertThat(belastungsfaktor.getPrimaerschluessel()).isEqualTo(id),
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
			() -> assertThat(belastungsfaktor.getShoulder()).isEqualTo(0.1));
	}

	@Test
	@DisplayName("Getter besitzen")
	void test03()
	{
		assertAll(
			() -> assertThat(sut.getPrimaerschluessel()).isEqualTo(Testdaten.BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN_ID),
			() -> assertThat(sut.getSquat()).isZero(),
			() -> assertThat(sut.getBenchpress()).isEqualTo(1),
			() -> assertThat(sut.getDeadlift()).isZero(),
			() -> assertThat(sut.getTriceps()).isEqualTo(0.7),
			() -> assertThat(sut.getChest()).isEqualTo(1),
			() -> assertThat(sut.getCore()).isZero(),
			() -> assertThat(sut.getBack()).isZero(),
			() -> assertThat(sut.getBiceps()).isZero(),
			() -> assertThat(sut.getGlutes()).isZero(),
			() -> assertThat(sut.getQuads()).isZero(),
			() -> assertThat(sut.getHamstrings()).isZero(),
			() -> assertThat(sut.getShoulder()).isEqualTo(0.1));
	}

	@Test
	@DisplayName("Setter besitzen")
	void test04()
	{
		var id = new Primaerschluessel();
		var belastungsfaktor = new Belastungsfaktor()
			.setPrimaerschluessel(id)
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
			() -> assertThat(belastungsfaktor.getPrimaerschluessel()).isEqualTo(id),
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
	void test05()
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
	void test06()
	{
		var erwartet = "Belastungsfaktor{ID=" + sut.getPrimaerschluessel().getId().toString() + "}";

		assertThat(sut).hasToString(erwartet);
	}
}
