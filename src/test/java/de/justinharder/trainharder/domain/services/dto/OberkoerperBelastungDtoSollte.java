package de.justinharder.trainharder.domain.services.dto;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OberkoerperBelastungDtoSollte
{
	private static final String TRICEPS = "0.7";
	private static final String CHEST = "1.0";
	private static final String CORE = "0.0";
	private static final String BACK = "0.0";
	private static final String BICEPS = "0.0";
	private static final String SHOULDER = "0.1";

	private OberkoerperBelastungDto sut;

	@BeforeEach
	void setup()
	{
		sut = new OberkoerperBelastungDto(TRICEPS, CHEST, CORE, BACK, BICEPS, SHOULDER);
	}

	@Test
	@DisplayName("einen NoArgsConstructor und Setter besitzen")
	void test01()
	{
		sut = new OberkoerperBelastungDto()
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
		EqualsVerifier.forClass(OberkoerperBelastungDto.class)
			.suppress(Warning.STRICT_INHERITANCE)
			.suppress(Warning.NONFINAL_FIELDS)
			.verify();
	}

	@Test
	@DisplayName("eine toString()-Methode haben")
	void test03()
	{
		assertThat(sut).hasToString("OberkoerperBelastungDto(triceps=0.7, chest=1.0, core=0.0, back=0.0, biceps=0.0, shoulder=0.1)");
	}

	@Test
	@DisplayName("null validieren")
	void test04()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> new OberkoerperBelastungDto(null, CHEST, CORE, BACK, BICEPS, SHOULDER)),
			() -> assertThrows(NullPointerException.class, () -> new OberkoerperBelastungDto(TRICEPS, null, CORE, BACK, BICEPS, SHOULDER)),
			() -> assertThrows(NullPointerException.class, () -> new OberkoerperBelastungDto(TRICEPS, CHEST, null, BACK, BICEPS, SHOULDER)),
			() -> assertThrows(NullPointerException.class, () -> new OberkoerperBelastungDto(TRICEPS, CHEST, CORE, null, BICEPS, SHOULDER)),
			() -> assertThrows(NullPointerException.class, () -> new OberkoerperBelastungDto(TRICEPS, CHEST, CORE, BACK, null, SHOULDER)),
			() -> assertThrows(NullPointerException.class, () -> new OberkoerperBelastungDto(TRICEPS, CHEST, CORE, BACK, BICEPS, null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setTriceps(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setChest(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setCore(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setBack(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setBiceps(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setShoulder(null)));
	}
}
