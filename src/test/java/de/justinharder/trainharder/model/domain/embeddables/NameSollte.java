package de.justinharder.trainharder.model.domain.embeddables;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.trainharder.setup.Testdaten;

class NameSollte
{
	private Name sut;

	@BeforeEach
	void setup()
	{
		sut = new Name("Justin", "Harder");
	}

	@Test
	@DisplayName("einen AllArgsConstructor und Getter besitzen")
	void test01()
	{
		assertAll(
			() -> assertThat(sut.getVorname()).isEqualTo("Justin"),
			() -> assertThat(sut.getNachname()).isEqualTo("Harder"));
	}

	@Test
	@DisplayName("einen NoArgsConstructor und Setter besitzen")
	void test02()
	{
		sut = new Name()
			.setVorname("Justin")
			.setNachname("Harder");

		assertAll(
			() -> assertThat(sut.getVorname()).isEqualTo("Justin"),
			() -> assertThat(sut.getNachname()).isEqualTo("Harder"));
	}

	@Test
	@DisplayName("sich vergleichen")
	@SuppressWarnings("unlikely-arg-type")
	void test05()
	{
//		final var andererName = new Name("Nicole", "Harder");
//
//		assertAll(
//			() -> assertThat(sut).isNotNull(),
//			() -> assertThat(sut).isNotEqualTo(andererName),
//			() -> assertThat(sut.hashCode()).isNotEqualTo(andererName.hashCode()));

		EqualsVerifier.forClass(Name.class).verify();
	}

	@Test
	@DisplayName("eine toString()-Methode haben")
	void test06()
	{
		final var erwartet = "Name(vorname=Justin, nachname=Harder)";

		assertThat(sut).hasToString(erwartet);
	}
}
