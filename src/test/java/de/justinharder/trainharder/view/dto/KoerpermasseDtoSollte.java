package de.justinharder.trainharder.view.dto;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class KoerpermasseDtoSollte
{
	private static final String KOERPERGROESSE = "178";
	private static final String KOERPERGEWICHT = "90.00";
	private static final String KOERPERFETT_ANTEIL = "20.0";
	private static final String FETTFREIES_KOERPERGEWICHT = "72.00";
	private static final String BODY_MASS_INDEX = "28.4";
	private static final String FAT_FREE_MASS_INDEX = "22.8";

	private KoerpermasseDto sut;

	@BeforeEach
	void setup()
	{
		sut = new KoerpermasseDto(KOERPERGROESSE, KOERPERGEWICHT, KOERPERFETT_ANTEIL, FETTFREIES_KOERPERGEWICHT, BODY_MASS_INDEX, FAT_FREE_MASS_INDEX);
	}

	@Test
	@DisplayName("einen NoArgsConstructor und Setter besitzen")
	void test01()
	{
		sut = new KoerpermasseDto()
			.setKoerpergroesse(KOERPERGROESSE)
			.setKoerpergewicht(KOERPERGEWICHT)
			.setKoerperfettAnteil(KOERPERFETT_ANTEIL)
			.setFettfreiesKoerpergewicht(FETTFREIES_KOERPERGEWICHT)
			.setBodyMassIndex(BODY_MASS_INDEX)
			.setFatFreeMassIndex(FAT_FREE_MASS_INDEX);

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
		EqualsVerifier.forClass(KoerpermasseDto.class)
			.suppress(Warning.STRICT_INHERITANCE)
			.suppress(Warning.NONFINAL_FIELDS)
			.suppress(Warning.NULL_FIELDS)
			.verify();
	}

	@Test
	@DisplayName("eine toString()-Methode haben")
	void test03()
	{
		assertThat(sut).hasToString("KoerpermasseDto(koerpergroesse=178, koerpergewicht=90.00, koerperfettAnteil=20.0, fettfreiesKoerpergewicht=72.00, bodyMassIndex=28.4, fatFreeMassIndex=22.8)");
	}

	@Test
	@DisplayName("null validieren")
	void test04()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> new KoerpermasseDto(null, KOERPERGEWICHT, KOERPERFETT_ANTEIL, FETTFREIES_KOERPERGEWICHT, BODY_MASS_INDEX, FAT_FREE_MASS_INDEX)),
			() -> assertThrows(NullPointerException.class, () -> new KoerpermasseDto(KOERPERGROESSE, null, KOERPERFETT_ANTEIL, FETTFREIES_KOERPERGEWICHT, BODY_MASS_INDEX, FAT_FREE_MASS_INDEX)),
			() -> assertThrows(NullPointerException.class, () -> new KoerpermasseDto(KOERPERGROESSE, KOERPERGEWICHT, null, FETTFREIES_KOERPERGEWICHT, BODY_MASS_INDEX, FAT_FREE_MASS_INDEX)),
			() -> assertThrows(NullPointerException.class, () -> new KoerpermasseDto(KOERPERGROESSE, KOERPERGEWICHT, KOERPERFETT_ANTEIL, null, BODY_MASS_INDEX, FAT_FREE_MASS_INDEX)),
			() -> assertThrows(NullPointerException.class, () -> new KoerpermasseDto(KOERPERGROESSE, KOERPERGEWICHT, KOERPERFETT_ANTEIL, FETTFREIES_KOERPERGEWICHT, null, FAT_FREE_MASS_INDEX)),
			() -> assertThrows(NullPointerException.class, () -> new KoerpermasseDto(KOERPERGROESSE, KOERPERGEWICHT, KOERPERFETT_ANTEIL, FETTFREIES_KOERPERGEWICHT, BODY_MASS_INDEX, null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setKoerpergroesse(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setKoerpergewicht(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setKoerperfettAnteil(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setFettfreiesKoerpergewicht(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setBodyMassIndex(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setFatFreeMassIndex(null)));
	}
}