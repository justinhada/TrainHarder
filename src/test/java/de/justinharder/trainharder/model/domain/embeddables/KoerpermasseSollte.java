package de.justinharder.trainharder.model.domain.embeddables;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.trainharder.setup.Testdaten;

public class KoerpermasseSollte
{
	private Koerpermasse sut;

	@BeforeEach
	public void setup()
	{
		sut = new Koerpermasse(178, 90, 25);
	}

	@Test
	@DisplayName("einen AllArgsConstructor und Getter besitzen")
	public void test01()
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
	public void test02()
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
	@SuppressWarnings("unlikely-arg-type")
	public void test03()
	{
		final var andereKoerpermasse = new Koerpermasse(190, 100, 6);

		assertAll(
			() -> assertThat(sut.equals(sut)).isEqualTo(true),
			() -> assertThat(sut.equals(null)).isEqualTo(false),
			() -> assertThat(sut.equals(Testdaten.AUTHENTIFIZIERUNG_JUSTIN)).isEqualTo(false),
			() -> assertThat(sut.equals(andereKoerpermasse)).isEqualTo(false),
			() -> assertThat(sut.hashCode()).isNotEqualTo(andereKoerpermasse.hashCode()));
	}

	@Test
	@DisplayName("eine toString()-Methode haben")
	public void test04()
	{
		final var erwartet =
			"Koerpermasse(koerpergroesse=178, koerpergewicht=90.0, koerperfettAnteil=25.0, fettfreiesKoerpergewicht=67.5, bodyMassIndex=28.41, fatFreeMassIndex=21.43)";

		assertThat(sut.toString()).isEqualTo(erwartet);
	}
}
