package de.justinharder.trainharder.domain.model.embeddables;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class KoerpermasseSollte
{
	private static final BigDecimal KOERPERGROESSE = new BigDecimal(178);
	private static final BigDecimal KOERPERGEWICHT = new BigDecimal(90);
	private static final BigDecimal KOERPERFETT_ANTEIL = new BigDecimal(25);
	private static final BigDecimal FETTFREIES_KOERPERGEWICHT = new BigDecimal("67.50");
	private static final BigDecimal BODY_MASS_INDEX = new BigDecimal("28.41");
	private static final BigDecimal FAT_FREE_MASS_INDEX = new BigDecimal("21.42");

	private Koerpermasse sut;

	@BeforeEach
	void setup()
	{
		sut = new Koerpermasse(KOERPERGROESSE, KOERPERGEWICHT, KOERPERFETT_ANTEIL);
	}

	@Test
	@DisplayName("einen NoArgsConstructor und Setter besitzen")
	void test01()
	{
		sut.setKoerpergroesse(KOERPERGROESSE)
			.setKoerpergewicht(KOERPERGEWICHT)
			.setKoerperfettAnteil(KOERPERFETT_ANTEIL);

		assertAll(
			() -> assertThat(sut.getKoerpergroesse()).isEqualTo(KOERPERGROESSE),
			() -> assertThat(sut.getKoerpergewicht()).isEqualTo(KOERPERGEWICHT),
			() -> assertThat(sut.getKoerperfettAnteil()).isEqualTo(KOERPERFETT_ANTEIL),
			() -> assertThat(sut.getFettfreiesKoerpergewicht()).isEqualTo(FETTFREIES_KOERPERGEWICHT),
			() -> assertThat(sut.getBodyMassIndex()).isEqualTo(BODY_MASS_INDEX),
			() -> assertThat(sut.getFatFreeMassIndex()).isEqualTo(FAT_FREE_MASS_INDEX));
	}

	@Test
	@DisplayName("sich vergleichen")
	void test02()
	{
		EqualsVerifier.forClass(Koerpermasse.class)
			.suppress(Warning.BIGDECIMAL_EQUALITY)
			.suppress(Warning.NULL_FIELDS)
			.verify();
	}

	@Test
	@DisplayName("eine toString()-Methode haben")
	void test03()
	{
		assertThat(sut).hasToString("Koerpermasse(koerpergroesse=178, koerpergewicht=90, koerperfettAnteil=25, fettfreiesKoerpergewicht=67.50, bodyMassIndex=28.41, fatFreeMassIndex=21.42)");
	}

	@Test
	@DisplayName("null validieren")
	void test04()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> new Koerpermasse(null, KOERPERGEWICHT, KOERPERFETT_ANTEIL)),
			() -> assertThrows(NullPointerException.class, () -> new Koerpermasse(KOERPERGROESSE, null, KOERPERFETT_ANTEIL)),
			() -> assertThrows(NullPointerException.class, () -> new Koerpermasse(KOERPERGROESSE, KOERPERGEWICHT, null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setKoerpergroesse(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setKoerpergewicht(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setKoerperfettAnteil(null)));
	}
}
