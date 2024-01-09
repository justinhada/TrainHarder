package de.justinharder.old.domain.model.attribute;

import de.justinharder.old.domain.model.attribute.Benutzerangabe;
import de.justinharder.old.domain.model.enums.*;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Benutzerangabe sollte")
class BenutzerangabeTest
{
	private Benutzerangabe sut;

	@BeforeEach
	void setup()
	{
		sut = new Benutzerangabe(Geschlecht.MAENNLICH, Erfahrung.BEGINNER, Ernaehrung.GUT, Schlafqualitaet.GUT,
			Stress.MITTELMAESSIG, Doping.NEIN, Regenerationsfaehigkeit.GUT);
	}

	@Test
	@DisplayName("einen NoArgsConstructor und Setter besitzen")
	void test01()
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
	void test02()
	{
		EqualsVerifier.forClass(Benutzerangabe.class).verify();
	}

	@Test
	@DisplayName("eine toString()-Methode haben")
	void test03()
	{
		assertThat(sut).hasToString(
			"Benutzerangabe(kraftlevel=CLASS_5, geschlecht=MAENNLICH, erfahrung=BEGINNER, ernaehrung=GUT, schlafqualitaet=GUT, stress=MITTELMAESSIG, doping=NEIN, regenerationsfaehigkeit=GUT)");
	}

	@Test
	@DisplayName("null validieren")
	void test04()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class,
				() -> new Benutzerangabe(null, Erfahrung.BEGINNER, Ernaehrung.DURCHSCHNITT,
					Schlafqualitaet.DURCHSCHNITT, Stress.MITTELMAESSIG, Doping.NEIN,
					Regenerationsfaehigkeit.DURCHSCHNITTLICH)),
			() -> assertThrows(NullPointerException.class,
				() -> new Benutzerangabe(Geschlecht.MAENNLICH, null, Ernaehrung.DURCHSCHNITT,
					Schlafqualitaet.DURCHSCHNITT, Stress.MITTELMAESSIG, Doping.NEIN,
					Regenerationsfaehigkeit.DURCHSCHNITTLICH)),
			() -> assertThrows(NullPointerException.class,
				() -> new Benutzerangabe(Geschlecht.MAENNLICH, Erfahrung.BEGINNER, null, Schlafqualitaet.DURCHSCHNITT,
					Stress.MITTELMAESSIG, Doping.NEIN, Regenerationsfaehigkeit.DURCHSCHNITTLICH)),
			() -> assertThrows(NullPointerException.class,
				() -> new Benutzerangabe(Geschlecht.MAENNLICH, Erfahrung.BEGINNER, Ernaehrung.DURCHSCHNITT, null,
					Stress.MITTELMAESSIG, Doping.NEIN, Regenerationsfaehigkeit.DURCHSCHNITTLICH)),
			() -> assertThrows(NullPointerException.class,
				() -> new Benutzerangabe(Geschlecht.MAENNLICH, Erfahrung.BEGINNER, Ernaehrung.DURCHSCHNITT,
					Schlafqualitaet.DURCHSCHNITT, null, Doping.NEIN, Regenerationsfaehigkeit.DURCHSCHNITTLICH)),
			() -> assertThrows(NullPointerException.class,
				() -> new Benutzerangabe(Geschlecht.MAENNLICH, Erfahrung.BEGINNER, Ernaehrung.DURCHSCHNITT,
					Schlafqualitaet.DURCHSCHNITT, Stress.MITTELMAESSIG, null,
					Regenerationsfaehigkeit.DURCHSCHNITTLICH)),
			() -> assertThrows(NullPointerException.class,
				() -> new Benutzerangabe(Geschlecht.MAENNLICH, Erfahrung.BEGINNER, Ernaehrung.DURCHSCHNITT,
					Schlafqualitaet.DURCHSCHNITT, Stress.MITTELMAESSIG, Doping.NEIN, null)),
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
