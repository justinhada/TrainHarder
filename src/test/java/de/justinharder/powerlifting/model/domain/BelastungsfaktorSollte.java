package de.justinharder.powerlifting.model.domain;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.allOf;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.powerlifting.setup.Testdaten;

public class BelastungsfaktorSollte
{
	private Belastungsfaktor sut;

	@BeforeEach
	public void setup()
	{
		sut = Testdaten.BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN;
	}

	@Test
	@DisplayName("einen NoArgsConstructor haben")
	public void test01()
	{
		org.hamcrest.MatcherAssert.assertThat(Belastungsfaktor.class, allOf(hasValidBeanConstructor()));
	}

	@Test
	@DisplayName("einen RequiredArgsConstructor haben")
	public void test02()
	{
		final var belastungsfaktor = new Belastungsfaktor(0, 1, 0, 0.7, 1, 0, 0, 0, 0, 0, 0, 0.1);

		assertAll(
			() -> assertThat(belastungsfaktor.getId()).isEqualTo(0),
			() -> assertThat(belastungsfaktor.getSquat()).isEqualTo(0),
			() -> assertThat(belastungsfaktor.getBenchpress()).isEqualTo(1),
			() -> assertThat(belastungsfaktor.getDeadlift()).isEqualTo(0),
			() -> assertThat(belastungsfaktor.getTriceps()).isEqualTo(0.7),
			() -> assertThat(belastungsfaktor.getChest()).isEqualTo(1),
			() -> assertThat(belastungsfaktor.getCore()).isEqualTo(0),
			() -> assertThat(belastungsfaktor.getBack()).isEqualTo(0),
			() -> assertThat(belastungsfaktor.getBiceps()).isEqualTo(0),
			() -> assertThat(belastungsfaktor.getGlutes()).isEqualTo(0),
			() -> assertThat(belastungsfaktor.getQuads()).isEqualTo(0),
			() -> assertThat(belastungsfaktor.getHamstrings()).isEqualTo(0),
			() -> assertThat(belastungsfaktor.getShoulder()).isEqualTo(0.1));
	}

	@Test
	@DisplayName("Getter besitzen")
	public void test03()
	{
		assertAll(
			() -> assertThat(sut.getId()).isEqualTo(0),
			() -> assertThat(sut.getSquat()).isEqualTo(0),
			() -> assertThat(sut.getBenchpress()).isEqualTo(1),
			() -> assertThat(sut.getDeadlift()).isEqualTo(0),
			() -> assertThat(sut.getTriceps()).isEqualTo(0.7),
			() -> assertThat(sut.getChest()).isEqualTo(1),
			() -> assertThat(sut.getCore()).isEqualTo(0),
			() -> assertThat(sut.getBack()).isEqualTo(0),
			() -> assertThat(sut.getBiceps()).isEqualTo(0),
			() -> assertThat(sut.getGlutes()).isEqualTo(0),
			() -> assertThat(sut.getQuads()).isEqualTo(0),
			() -> assertThat(sut.getHamstrings()).isEqualTo(0),
			() -> assertThat(sut.getShoulder()).isEqualTo(0.1));
	}

	@Test
	@DisplayName("Setter besitzen")
	public void test04()
	{
		final var belastungsfaktor = new Belastungsfaktor();
		belastungsfaktor.setId(0);
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
		belastungsfaktor.setUebung(Testdaten.WETTKAMPFBANKDRUECKEN);

		assertAll(
			() -> assertThat(belastungsfaktor.getId()).isEqualTo(0),
			() -> assertThat(belastungsfaktor.getSquat()).isEqualTo(0),
			() -> assertThat(belastungsfaktor.getBenchpress()).isEqualTo(1),
			() -> assertThat(belastungsfaktor.getDeadlift()).isEqualTo(0),
			() -> assertThat(belastungsfaktor.getTriceps()).isEqualTo(0.7),
			() -> assertThat(belastungsfaktor.getChest()).isEqualTo(1),
			() -> assertThat(belastungsfaktor.getCore()).isEqualTo(0),
			() -> assertThat(belastungsfaktor.getBack()).isEqualTo(0),
			() -> assertThat(belastungsfaktor.getBiceps()).isEqualTo(0),
			() -> assertThat(belastungsfaktor.getGlutes()).isEqualTo(0),
			() -> assertThat(belastungsfaktor.getQuads()).isEqualTo(0),
			() -> assertThat(belastungsfaktor.getHamstrings()).isEqualTo(0),
			() -> assertThat(belastungsfaktor.getShoulder()).isEqualTo(0.1),
			() -> assertThat(belastungsfaktor.getUebung()).isEqualTo(Testdaten.WETTKAMPFBANKDRUECKEN));
	}

	@Test
	@DisplayName("sich vergleichen")
	@SuppressWarnings("unlikely-arg-type")
	public void test05()
	{
		final var andererBelastungsfaktor = new Belastungsfaktor();
		andererBelastungsfaktor.setId(1);

		final var belastungsfaktorMitGleicherId = new Belastungsfaktor();
		belastungsfaktorMitGleicherId.setId(0);

		assertAll(
			() -> assertThat(sut.equals(sut)).isEqualTo(true),
			() -> assertThat(sut.equals(null)).isEqualTo(false),
			() -> assertThat(sut.equals(Testdaten.ANMELDEDATEN_JUSTIN)).isEqualTo(false),
			() -> assertThat(sut.equals(andererBelastungsfaktor)).isEqualTo(false),
			() -> assertThat(sut.equals(belastungsfaktorMitGleicherId)).isEqualTo(true),
			() -> assertThat(sut.hashCode()).isNotEqualTo(andererBelastungsfaktor));
	}

	@Test
	@DisplayName("eine toString()-Methode haben")
	public void test06()
	{
		assertThat(sut.toString()).isEqualTo("Belastungsfaktor{ID=0}");
	}
}
