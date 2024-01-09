package de.justinharder.old.domain.services.dto;

import de.justinharder.old.domain.services.dto.UnterkoerperBelastungDto;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("UnterkoerperBelastungDto sollte")
class UnterkoerperBelastungDtoTest
{
	private static final String GLUTES = "1.0";
	private static final String QUADS = "1.0";
	private static final String HAMSTRINGS = "0.5";

	private UnterkoerperBelastungDto sut;

	@BeforeEach
	void setup()
	{
		sut = new UnterkoerperBelastungDto(GLUTES, QUADS, HAMSTRINGS);
	}

	@Test
	@DisplayName("einen NoArgsConstructor und Setter besitzen")
	void test01()
	{
		sut = new UnterkoerperBelastungDto()
			.setGlutes(GLUTES)
			.setQuads(QUADS)
			.setHamstrings(HAMSTRINGS);

		assertAll(
			() -> assertThat(sut.getGlutes()).isEqualTo(GLUTES),
			() -> assertThat(sut.getQuads()).isEqualTo(QUADS),
			() -> assertThat(sut.getHamstrings()).isEqualTo(HAMSTRINGS));
	}

	@Test
	@DisplayName("sich vergleichen")
	void test02()
	{
		EqualsVerifier.forClass(UnterkoerperBelastungDto.class)
			.suppress(Warning.STRICT_INHERITANCE)
			.suppress(Warning.NONFINAL_FIELDS)
			.verify();
	}

	@Test
	@DisplayName("eine toString()-Methode haben")
	void test03()
	{
		assertThat(sut).hasToString("UnterkoerperBelastungDto(glutes=1.0, quads=1.0, hamstrings=0.5)");
	}

	@Test
	@DisplayName("null validieren")
	void test04()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> new UnterkoerperBelastungDto(null, QUADS, HAMSTRINGS)),
			() -> assertThrows(NullPointerException.class, () -> new UnterkoerperBelastungDto(GLUTES, null, HAMSTRINGS)),
			() -> assertThrows(NullPointerException.class, () -> new UnterkoerperBelastungDto(GLUTES, QUADS, null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setGlutes(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setQuads(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setHamstrings(null)));
	}
}
