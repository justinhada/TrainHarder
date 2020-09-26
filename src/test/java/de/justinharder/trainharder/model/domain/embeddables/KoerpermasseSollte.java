package de.justinharder.trainharder.model.domain.embeddables;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.trainharder.setup.Testdaten;

class KoerpermasseSollte
{
	private Koerpermasse sut;

	@BeforeEach
	void setup()
	{
		sut = new Koerpermasse(178, 90, 25);
	}

	@Test
	@DisplayName("einen AllArgsConstructor und Getter besitzen")
	void test01()
	{
		assertAll(
			() -> assertThat(sut.getKoerpergroesse()).isEqualTo(178),
			() -> assertThat(sut.getKoerpergewicht()).isEqualTo(90),
			() -> assertThat(sut.getKoerperfettAnteil()).isEqualTo(25),
			() -> assertThat(sut.getFettfreiesKoerpergewicht()).isEqualTo(67.5),
			() -> assertThat(sut.getBodyMassIndex()).isEqualTo(28.41),
			() -> assertThat(sut.getFatFreeMassIndex()).isEqualTo(21.43));
	}

	@Test
	@DisplayName("einen NoArgsConstructor und Setter besitzen")
	void test02()
	{
		sut = new Koerpermasse()
			.setKoerpergroesse(178)
			.setKoerpergewicht(90)
			.setKoerperfettAnteil(25);

		assertAll(
			() -> assertThat(sut.getKoerpergroesse()).isEqualTo(178),
			() -> assertThat(sut.getKoerpergewicht()).isEqualTo(90),
			() -> assertThat(sut.getKoerperfettAnteil()).isEqualTo(25),
			() -> assertThat(sut.getFettfreiesKoerpergewicht()).isEqualTo(67.5),
			() -> assertThat(sut.getBodyMassIndex()).isEqualTo(28.41),
			() -> assertThat(sut.getFatFreeMassIndex()).isEqualTo(21.43));
	}

	@Test
	@DisplayName("sich vergleichen")
	void test03()
	{
		EqualsVerifier.forClass(Koerpermasse.class).verify();
	}

	@Test
	@DisplayName("eine toString()-Methode haben")
	void test04()
	{
		final var erwartet =
			"Koerpermasse(koerpergroesse=178, koerpergewicht=90.0, koerperfettAnteil=25.0, fettfreiesKoerpergewicht=67.5, bodyMassIndex=28.41, fatFreeMassIndex=21.43)";

		assertThat(sut).hasToString(erwartet);
	}
}
