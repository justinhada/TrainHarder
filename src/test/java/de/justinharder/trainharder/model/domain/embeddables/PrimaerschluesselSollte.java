package de.justinharder.trainharder.model.domain.embeddables;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

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
		var primaerschluessel = new Primaerschluessel(sut.getId());

		assertThat(primaerschluessel.getId()).isEqualTo(sut.getId());
	}

	@Test
	@DisplayName("Setter besitzen")
	void test03()
	{
		var primaerschluessel = new Primaerschluessel();
		var uuid = UUID.randomUUID();

		primaerschluessel.setId(uuid);

		assertThat(primaerschluessel.getId()).isEqualTo(uuid);
	}

	@Test
	@DisplayName("sich vergleichen")
	void test05()
	{
		EqualsVerifier.forClass(Primaerschluessel.class)
			.suppress(Warning.STRICT_INHERITANCE)
			.suppress(Warning.NONFINAL_FIELDS)
			.verify();
	}

	@Test
	@DisplayName("eine toString()-Methode haben")
	void test06()
	{
		var erwartet = "Primaerschluessel{ID=" + sut.getId().toString() + "}";

		assertThat(sut).hasToString(erwartet);
	}
}
