package de.justinharder.trainharder.domain.model.embeddables;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class IDSollte
{
	private ID sut;

	@BeforeEach
	void setup()
	{
		sut = new ID();
	}

	@Test
	@DisplayName("einen RequiredArgsConstructor und Getter besitzen")
	void test02()
	{
		assertThat(new ID(sut.getId()).getId()).isEqualTo(sut.getId());
	}

	@Test
	@DisplayName("Setter besitzen")
	void test03()
	{
		var primaerschluessel = new ID();
		var uuid = UUID.randomUUID();

		primaerschluessel.setId(uuid);

		assertThat(primaerschluessel.getId()).isEqualTo(uuid);
	}

	@Test
	@DisplayName("sich vergleichen")
	void test04()
	{
		EqualsVerifier.forClass(ID.class)
			.suppress(Warning.STRICT_INHERITANCE)
			.suppress(Warning.NONFINAL_FIELDS)
			.verify();
	}

	@Test
	@DisplayName("eine toString()-Methode haben")
	void test05()
	{
		assertThat(sut).hasToString("Primaerschluessel{ID=" + sut.getId().toString() + "}");
	}
}
