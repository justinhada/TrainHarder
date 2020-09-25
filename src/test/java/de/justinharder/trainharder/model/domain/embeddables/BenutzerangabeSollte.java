package de.justinharder.trainharder.model.domain.embeddables;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.trainharder.model.domain.enums.Doping;
import de.justinharder.trainharder.model.domain.enums.Erfahrung;
import de.justinharder.trainharder.model.domain.enums.Ernaehrung;
import de.justinharder.trainharder.model.domain.enums.Geschlecht;
import de.justinharder.trainharder.model.domain.enums.Regenerationsfaehigkeit;
import de.justinharder.trainharder.model.domain.enums.Schlafqualitaet;
import de.justinharder.trainharder.model.domain.enums.Stress;
import de.justinharder.trainharder.setup.Testdaten;

class BenutzerangabeSollte
{
	private Benutzerangabe sut;

	@BeforeEach
	void setup()
	{
		sut = new Benutzerangabe(
			Geschlecht.MAENNLICH,
			Erfahrung.BEGINNER,
			Ernaehrung.GUT,
			Schlafqualitaet.GUT,
			Stress.MITTELMAESSIG,
			Doping.NEIN,
			Regenerationsfaehigkeit.GUT);
	}

	@Test
	@DisplayName("einen AllArgsConstructor und Getter besitzen")
	void test01()
	{
		assertAll(
			() -> assertThat(sut.getGeschlecht()).isEqualTo(Geschlecht.MAENNLICH),
			() -> assertThat(sut.getErfahrung()).isEqualTo(Erfahrung.BEGINNER),
			() -> assertThat(sut.getErnaehrung()).isEqualTo(Ernaehrung.GUT),
			() -> assertThat(sut.getSchlafqualitaet()).isEqualTo(Schlafqualitaet.GUT),
			() -> assertThat(sut.getStress()).isEqualTo(Stress.MITTELMAESSIG),
			() -> assertThat(sut.getDoping()).isEqualTo(Doping.NEIN),
			() -> assertThat(sut.getRegenerationsfaehigkeit()).isEqualTo(Regenerationsfaehigkeit.GUT));
	}

	@Test
	@DisplayName("einen NoArgsConstructor und Setter besitzen")
	void test02()
	{
		sut = new Benutzerangabe()
			.setGeschlecht(Geschlecht.MAENNLICH)
			.setErfahrung(Erfahrung.BEGINNER)
			.setErnaehrung(Ernaehrung.GUT)
			.setSchlafqualitaet(Schlafqualitaet.GUT)
			.setStress(Stress.MITTELMAESSIG)
			.setDoping(Doping.NEIN)
			.setRegenerationsfaehigkeit(Regenerationsfaehigkeit.GUT);

		assertAll(
			() -> assertThat(sut.getGeschlecht()).isEqualTo(Geschlecht.MAENNLICH),
			() -> assertThat(sut.getErfahrung()).isEqualTo(Erfahrung.BEGINNER),
			() -> assertThat(sut.getErnaehrung()).isEqualTo(Ernaehrung.GUT),
			() -> assertThat(sut.getSchlafqualitaet()).isEqualTo(Schlafqualitaet.GUT),
			() -> assertThat(sut.getStress()).isEqualTo(Stress.MITTELMAESSIG),
			() -> assertThat(sut.getDoping()).isEqualTo(Doping.NEIN),
			() -> assertThat(sut.getRegenerationsfaehigkeit()).isEqualTo(Regenerationsfaehigkeit.GUT));
	}

	@Test
	@DisplayName("sich vergleichen")
	@SuppressWarnings("unlikely-arg-type")
	void test05()
	{
		EqualsVerifier.forClass(Benutzerangabe.class).verify();
	}

	@Test
	@DisplayName("eine toString()-Methode haben")
	void test06()
	{
		final var erwartet =
			"Benutzerangabe(kraftlevel=CLASS_5, geschlecht=MAENNLICH, erfahrung=BEGINNER, ernaehrung=GUT, schlafqualitaet=GUT, stress=MITTELMAESSIG, doping=NEIN, regenerationsfaehigkeit=GUT)";

		assertThat(sut).hasToString(erwartet);
	}
}
