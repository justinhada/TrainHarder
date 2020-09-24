package de.justinharder.trainharder.model.domain;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.allOf;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.setup.Testdaten;

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
		final var id = new Primaerschluessel();
		final var belastungsfaktor = new Belastungsfaktor(id, 0, 1, 0, 0.7, 1, 0, 0, 0, 0, 0, 0, 0.1);

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
		final var id = new Primaerschluessel();
		final var belastungsfaktor = new Belastungsfaktor();
		belastungsfaktor.setPrimaerschluessel(id);
		belastungsfaktor.setSquat(0);
		belastungsfaktor.setBenchpress(1);
		belastungsfaktor.setDeadlift(0);
		belastungsfaktor.setTriceps(0.7);
		belastungsfaktor.setChest(1);
		belastungsfaktor.setCore(0);
		belastungsfaktor.setBack(0);
		belastungsfaktor.setBiceps(0);
		belastungsfaktor.setGlutes(0);
		belastungsfaktor.setQuads(0);
		belastungsfaktor.setHamstrings(0);
		belastungsfaktor.setShoulder(0.1);
		belastungsfaktor.setUebung(Testdaten.UEBUNG_WETTKAMPFBANKDRUECKEN);

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
	@SuppressWarnings("unlikely-arg-type")
	void test05()
	{
		final var andererBelastungsfaktor = new Belastungsfaktor();
		andererBelastungsfaktor.setPrimaerschluessel(new Primaerschluessel());

		final var belastungsfaktorMitGleicherId = new Belastungsfaktor();
		belastungsfaktorMitGleicherId.setPrimaerschluessel(sut.getPrimaerschluessel());

		assertAll(
			() -> assertThat(sut).isNotNull(),
			() -> assertThat(sut).isNotEqualTo(andererBelastungsfaktor),
			() -> assertThat(sut).isEqualTo(belastungsfaktorMitGleicherId),
			() -> assertThat(sut.hashCode()).isNotEqualTo(andererBelastungsfaktor.hashCode()));
	}

	@Test
	@DisplayName("eine toString()-Methode haben")
	void test06()
	{
		final var erwartet = "Belastungsfaktor{ID=" + sut.getPrimaerschluessel().getId().toString() + "}";

		assertThat(sut).hasToString(erwartet);
	}
}
