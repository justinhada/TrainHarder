package de.justinharder.trainharder.model.domain.embeddables;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.trainharder.setup.Testdaten;

class PrimaerschluesselSollte
{
	private Primaerschluessel sut;

	@BeforeEach
	void setup()
	{
		sut = new Primaerschluessel();
	}

	@Test
	@DisplayName("einen NoArgsConstructor besitzen")
	void test01()
	{
		assertThat(Primaerschluessel.class, allOf(hasValidBeanConstructor()));
	}

	@Test
	@DisplayName("einen RequiredArgsConstructor und Getter besitzen")
	void test02()
	{
		final var primaerschluessel = new Primaerschluessel(sut.getId());

		assertThat(primaerschluessel.getId()).isEqualTo(sut.getId());
	}

	@Test
	@DisplayName("Setter besitzen")
	void test03()
	{
		final var primaerschluessel = new Primaerschluessel();
		final var uuid = UUID.randomUUID();

		primaerschluessel.setId(uuid);

		assertThat(primaerschluessel.getId()).isEqualTo(uuid);
	}

	@Test
	@DisplayName("sich vergleichen")
	@SuppressWarnings("unlikely-arg-type")
	void test05()
	{
		final var andererPrimaerschluessel = new Primaerschluessel();

		final var primaerschluesselMitGleicherId = new Primaerschluessel(sut.getId());

		assertAll(
			() -> assertThat(sut).isEqualTo(sut),
			() -> assertThat(sut).isNotEqualTo(null),
			() -> assertThat(sut).isNotEqualTo(Testdaten.AUTHENTIFIZIERUNG_JUSTIN),
			() -> assertThat(sut).isNotEqualTo(andererPrimaerschluessel),
			() -> assertThat(sut).isEqualTo(primaerschluesselMitGleicherId),
			() -> assertThat(sut.hashCode()).isNotEqualTo(andererPrimaerschluessel.hashCode()));
	}

	@Test
	@DisplayName("eine toString()-Methode haben")
	void test06()
	{
		final var erwartet = "Primaerschluessel{ID=" + sut.getId().toString() + "}";

		assertThat(sut).hasToString(erwartet);
	}
}
