package de.justinharder.old.domain.model.attribute;

import de.justinharder.old.domain.model.attribute.UnterkoerperBelastung;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("UnterkoerperBelastung sollte")
class UnterkoerperBelastungTest
{
	private static final double GLUTES = 1.0;
	private static final double QUADS = 1.0;
	private static final double HAMSTRINGS = 0.5;

	private UnterkoerperBelastung sut;

	@BeforeEach
	void setup()
	{
		sut = new UnterkoerperBelastung(GLUTES, QUADS, HAMSTRINGS);
	}

	@Test
	@DisplayName("einen NoArgsConstructor und Setter besitzen")
	void test01()
	{
		sut = new UnterkoerperBelastung()
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
		EqualsVerifier.forClass(UnterkoerperBelastung.class).verify();
	}

	@Test
	@DisplayName("eine toString()-Methode haben")
	void test03()
	{
		assertThat(sut).hasToString("UnterkoerperBelastung(glutes=1.0, quads=1.0, hamstrings=0.5)");
	}
}
