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

public class PrimaerschluesselSollte
{
	private Primaerschluessel sut;

	@BeforeEach
	public void setup()
	{
		sut = new Primaerschluessel();
	}

	@Test
	@DisplayName("einen NoArgsConstructor besitzen")
	public void test01()
	{
		assertThat(Primaerschluessel.class, allOf(hasValidBeanConstructor()));
	}

	@Test
	@DisplayName("einen RequiredArgsConstructor und Getter besitzen")
	public void test02()
	{
		final var primaerschluessel = new Primaerschluessel(sut.getId());

		assertThat(primaerschluessel.getId()).isEqualTo(sut.getId());
	}

	@Test
	@DisplayName("Setter besitzen")
	public void test03()
	{
		final var primaerschluessel = new Primaerschluessel();
		final var uuid = UUID.randomUUID();

		primaerschluessel.setId(uuid);

		assertThat(primaerschluessel.getId()).isEqualTo(uuid);
	}

	@Test
	@DisplayName("sich vergleichen")
	@SuppressWarnings("unlikely-arg-type")
	public void test05()
	{
		final var andererPrimaerschluessel = new Primaerschluessel();

		final var primaerschluesselMitGleicherId = new Primaerschluessel(sut.getId());

		assertAll(
			() -> assertThat(sut.equals(sut)).isEqualTo(true),
			() -> assertThat(sut.equals(null)).isEqualTo(false),
			() -> assertThat(sut.equals(Testdaten.AUTHENTIFIZIERUNG_JUSTIN)).isEqualTo(false),
			() -> assertThat(sut.equals(andererPrimaerschluessel)).isEqualTo(false),
			() -> assertThat(sut.equals(primaerschluesselMitGleicherId)).isEqualTo(true),
			() -> assertThat(sut.hashCode()).isNotEqualTo(andererPrimaerschluessel.hashCode()));
	}

	@Test
	@DisplayName("eine toString()-Methode haben")
	public void test06()
	{
		final var erwartet = "Primaerschluessel{ID=" + sut.getId().toString() + "}";

		assertThat(sut.toString()).isEqualTo(erwartet);
	}
}
