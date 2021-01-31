package de.justinharder.trainharder.view.dto;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BenutzerangabeDtoSollte
{
	private static final String KRAFTLEVEL = "CLASS_5";
	private static final String GESCHLECHT = "MAENNLICH";
	private static final String ERFAHRUNG = "BEGINNER";
	private static final String ERNAEHRUNG = "GUT";
	private static final String SCHLAFQUALITAET = "GUT";
	private static final String STRESS = "MITTELMAESSIG";
	private static final String DOPING = "NEIN";
	private static final String REGENERATIONSFAEHIGKEIT = "GUT";

	private BenutzerangabeDto sut;

	@BeforeEach
	void setup()
	{
		sut = new BenutzerangabeDto(GESCHLECHT, ERFAHRUNG, ERNAEHRUNG, SCHLAFQUALITAET, STRESS, DOPING, REGENERATIONSFAEHIGKEIT);
	}

	@Test
	@DisplayName("einen NoArgsConstructor und Setter besitzen")
	void test01()
	{
		sut = new BenutzerangabeDto()
			.setKraftlevel(KRAFTLEVEL)
			.setGeschlecht(GESCHLECHT)
			.setErfahrung(ERFAHRUNG)
			.setErnaehrung(ERNAEHRUNG)
			.setSchlafqualitaet(SCHLAFQUALITAET)
			.setStress(STRESS)
			.setDoping(DOPING)
			.setRegenerationsfaehigkeit(REGENERATIONSFAEHIGKEIT);

		assertAll(
			() -> assertThat(sut.getKraftlevel()).isEqualTo(KRAFTLEVEL),
			() -> assertThat(sut.getGeschlecht()).isEqualTo(GESCHLECHT),
			() -> assertThat(sut.getErfahrung()).isEqualTo(ERFAHRUNG),
			() -> assertThat(sut.getErnaehrung()).isEqualTo(ERNAEHRUNG),
			() -> assertThat(sut.getSchlafqualitaet()).isEqualTo(SCHLAFQUALITAET),
			() -> assertThat(sut.getStress()).isEqualTo(STRESS),
			() -> assertThat(sut.getDoping()).isEqualTo(DOPING),
			() -> assertThat(sut.getRegenerationsfaehigkeit()).isEqualTo(REGENERATIONSFAEHIGKEIT));
	}

	@Test
	@DisplayName("sich vergleichen")
	void test02()
	{
		EqualsVerifier.forClass(BenutzerangabeDto.class)
			.suppress(Warning.STRICT_INHERITANCE)
			.suppress(Warning.NONFINAL_FIELDS)
			.verify();
	}

	@Test
	@DisplayName("eine toString()-Methode haben")
	void test03()
	{
		assertThat(sut).hasToString(
			"BenutzerangabeDto(kraftlevel=CLASS_5, geschlecht=MAENNLICH, erfahrung=BEGINNER, ernaehrung=GUT, schlafqualitaet=GUT, stress=MITTELMAESSIG, doping=NEIN, regenerationsfaehigkeit=GUT)");
	}

	@Test
	@DisplayName("null validieren")
	void test04()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> new BenutzerangabeDto(null, ERFAHRUNG, ERNAEHRUNG, SCHLAFQUALITAET, STRESS, DOPING, REGENERATIONSFAEHIGKEIT)),
			() -> assertThrows(NullPointerException.class, () -> new BenutzerangabeDto(GESCHLECHT, null, ERNAEHRUNG, SCHLAFQUALITAET, STRESS, DOPING, REGENERATIONSFAEHIGKEIT)),
			() -> assertThrows(NullPointerException.class, () -> new BenutzerangabeDto(GESCHLECHT, ERFAHRUNG, null, SCHLAFQUALITAET, STRESS, DOPING, REGENERATIONSFAEHIGKEIT)),
			() -> assertThrows(NullPointerException.class, () -> new BenutzerangabeDto(GESCHLECHT, ERFAHRUNG, ERNAEHRUNG, null, STRESS, DOPING, REGENERATIONSFAEHIGKEIT)),
			() -> assertThrows(NullPointerException.class, () -> new BenutzerangabeDto(GESCHLECHT, ERFAHRUNG, ERNAEHRUNG, SCHLAFQUALITAET, null, DOPING, REGENERATIONSFAEHIGKEIT)),
			() -> assertThrows(NullPointerException.class, () -> new BenutzerangabeDto(GESCHLECHT, ERFAHRUNG, ERNAEHRUNG, SCHLAFQUALITAET, STRESS, null, REGENERATIONSFAEHIGKEIT)),
			() -> assertThrows(NullPointerException.class, () -> new BenutzerangabeDto(GESCHLECHT, ERFAHRUNG, ERNAEHRUNG, SCHLAFQUALITAET, STRESS, DOPING, null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setKraftlevel(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setGeschlecht(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setErfahrung(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setErnaehrung(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setSchlafqualitaet(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setStress(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setDoping(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setRegenerationsfaehigkeit(null)));
	}
}