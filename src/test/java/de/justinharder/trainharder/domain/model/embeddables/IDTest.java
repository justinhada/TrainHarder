package de.justinharder.trainharder.domain.model.embeddables;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("ID sollte")
class IDTest
{
	private static final UUID WERT = UUID.randomUUID();

	private ID sut;

	@BeforeEach
	void setup()
	{
		sut = new ID(WERT);
	}

	@Test
	@DisplayName("Getter besitzen")
	void test01()
	{
		assertThat(sut.getWert()).isEqualTo(WERT);
	}

	@Test
	@DisplayName("sich vergleichen")
	void test02()
	{
		EqualsVerifier.forClass(ID.class)
			.usingGetClass()
			.verify();
	}

	@Test
	@DisplayName("sich drucken")
	void test03()
	{
		assertThat(sut).hasToString(sut.getWert().toString());
	}

	@Test
	@DisplayName("null validieren")
	void test04()
	{
		assertThrows(NullPointerException.class, () -> new ID((UUID) null));
	}
}
