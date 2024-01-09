package de.justinharder.old.domain.model.attribute;

import de.justinharder.old.domain.model.attribute.OberkoerperBelastung;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("OberkoerperBelastung sollte")
class OberkoerperBelastungTest
{
	private static final double TRICEPS = 0.7;
	private static final double CHEST = 1.0;
	private static final double CORE = 0.0;
	private static final double BACK = 0.0;
	private static final double BICEPS = 0.0;
	private static final double SHOULDER = 0.1;

	private OberkoerperBelastung sut;

	@BeforeEach
	void setup()
	{
		sut = new OberkoerperBelastung(TRICEPS, CHEST, CORE, BACK, BICEPS, SHOULDER);
	}

	@Test
	@DisplayName("einen NoArgsConstructor und Setter besitzen")
	void test01()
	{
		sut = new OberkoerperBelastung()
			.setTriceps(TRICEPS)
			.setChest(CHEST)
			.setCore(CORE)
			.setBack(BACK)
			.setBiceps(BICEPS)
			.setShoulder(SHOULDER);

		assertAll(
			() -> assertThat(sut.getTriceps()).isEqualTo(TRICEPS),
			() -> assertThat(sut.getChest()).isEqualTo(CHEST),
			() -> assertThat(sut.getCore()).isEqualTo(CORE),
			() -> assertThat(sut.getBack()).isEqualTo(BACK),
			() -> assertThat(sut.getBiceps()).isEqualTo(BICEPS),
			() -> assertThat(sut.getShoulder()).isEqualTo(SHOULDER));
	}

	@Test
	@DisplayName("sich vergleichen")
	void test02()
	{
		EqualsVerifier.forClass(OberkoerperBelastung.class).verify();
	}

	@Test
	@DisplayName("eine toString()-Methode haben")
	void test03()
	{
		assertThat(sut).hasToString(
			"OberkoerperBelastung(triceps=0.7, chest=1.0, core=0.0, back=0.0, biceps=0.0, shoulder=0.1)");
	}
}
