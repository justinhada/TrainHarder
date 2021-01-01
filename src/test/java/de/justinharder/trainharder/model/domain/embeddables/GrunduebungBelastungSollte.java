package de.justinharder.trainharder.model.domain.embeddables;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class GrunduebungBelastungSollte
{
	private static final double SQUAT = 1.0;
	private static final double BENCHPRESS = 0.0;
	private static final double DEADLIFT = 0.0;

	private GrunduebungBelastung sut;

	@BeforeEach
	void setup()
	{
		sut = new GrunduebungBelastung(SQUAT, BENCHPRESS, DEADLIFT);
	}

	@Test
	@DisplayName("einen NoArgsConstructor und Setter besitzen")
	void test01()
	{
		sut = new GrunduebungBelastung()
			.setSquat(SQUAT)
			.setBenchpress(BENCHPRESS)
			.setDeadlift(DEADLIFT);

		assertAll(
			() -> assertThat(sut.getSquat()).isEqualTo(SQUAT),
			() -> assertThat(sut.getBenchpress()).isEqualTo(BENCHPRESS),
			() -> assertThat(sut.getDeadlift()).isEqualTo(DEADLIFT));
	}

	@Test
	@DisplayName("sich vergleichen")
	void test02()
	{
		EqualsVerifier.forClass(GrunduebungBelastung.class).verify();
	}

	@Test
	@DisplayName("eine toString()-Methode haben")
	void test03()
	{
		assertThat(sut).hasToString("GrunduebungBelastung(squat=1.0, benchpress=0.0, deadlift=0.0)");
	}
}
