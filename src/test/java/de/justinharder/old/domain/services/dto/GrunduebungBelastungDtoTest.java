package de.justinharder.old.domain.services.dto;

import de.justinharder.old.domain.services.dto.GrunduebungBelastungDto;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("GrunduebungBelastungDto sollte")
class GrunduebungBelastungDtoTest
{
	private static final String SQUAT = "1.0";
	private static final String BENCHPRESS = "0.0";
	private static final String DEADLIFT = "0.0";

	private GrunduebungBelastungDto sut;

	@BeforeEach
	void setup()
	{
		sut = new GrunduebungBelastungDto(SQUAT, BENCHPRESS, DEADLIFT);
	}

	@Test
	@DisplayName("einen NoArgsConstructor und Setter besitzen")
	void test01()
	{
		sut = new GrunduebungBelastungDto()
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
		EqualsVerifier.forClass(GrunduebungBelastungDto.class)
			.suppress(Warning.STRICT_INHERITANCE)
			.suppress(Warning.NONFINAL_FIELDS)
			.verify();
	}

	@Test
	@DisplayName("eine toString()-Methode haben")
	void test03()
	{
		assertThat(sut).hasToString("GrunduebungBelastungDto(squat=1.0, benchpress=0.0, deadlift=0.0)");
	}

	@Test
	@DisplayName("null validieren")
	void test04()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> new GrunduebungBelastungDto(null, BENCHPRESS, DEADLIFT)),
			() -> assertThrows(NullPointerException.class, () -> new GrunduebungBelastungDto(SQUAT, null, DEADLIFT)),
			() -> assertThrows(NullPointerException.class, () -> new GrunduebungBelastungDto(SQUAT, BENCHPRESS, null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setSquat(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setBenchpress(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setDeadlift(null)));
	}
}
