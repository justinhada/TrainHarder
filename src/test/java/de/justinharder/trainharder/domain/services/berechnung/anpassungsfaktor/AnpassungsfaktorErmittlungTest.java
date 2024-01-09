package de.justinharder.trainharder.domain.services.berechnung.anpassungsfaktor;

import de.justinharder.trainharder.domain.model.Authentifizierung;
import de.justinharder.trainharder.domain.model.Benutzer;
import de.justinharder.trainharder.domain.model.Koerpermessung;
import de.justinharder.trainharder.domain.model.attribute.Benutzerangabe;
import de.justinharder.base.domain.model.attribute.ID;
import de.justinharder.trainharder.domain.model.attribute.Koerpermasse;
import de.justinharder.trainharder.domain.model.attribute.Name;
import de.justinharder.trainharder.domain.model.enums.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static de.justinharder.trainharder.setup.Testdaten.BENUTZER_JUSTIN;
import static de.justinharder.trainharder.setup.Testdaten.PASSWORT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("AnpassungsfaktorErmittlung sollte")
class AnpassungsfaktorErmittlungTest
{
	private static final LocalDate DATUM = LocalDate.of(2020, 1, 1);

	private AnpassungsfaktorErmittlung sut;

	@BeforeEach
	void setup()
	{
		sut = new AnpassungsfaktorErmittlung();
	}

	private Benutzer erstelleBenutzer(LocalDate geburtsdatum, Benutzerangabe benutzerangabe, Kraftlevel kraftlevel,
		int koerpergroesse, int koerpergewicht)
	{
		var benutzer = new Benutzer(
			new ID(),
			new Name("Max", "Mustermann"),
			geburtsdatum,
			benutzerangabe.setKraftlevel(kraftlevel),
			new Authentifizierung(new ID(), "max@mustermann.de", "mustermann", PASSWORT));
		return benutzer.fuegeKoerpermessungHinzu(new Koerpermessung(
			new ID(),
			LocalDate.now(),
			new Koerpermasse(new BigDecimal(koerpergroesse), new BigDecimal(koerpergewicht), new BigDecimal(20)),
			2800,
			2800));
	}

	@Test
	@DisplayName("null validieren")
	void test01()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> sut.berechneAnpassungsfaktor(null, LocalDate.now())),
			() -> assertThrows(NullPointerException.class, () -> sut.berechneAnpassungsfaktor(BENUTZER_JUSTIN, null)));
	}

	@Test
	@DisplayName("den richtigen Anpassungsfaktor berechnen")
	void test02()
	{
		assertAll(
			() -> assertThat(sut.berechneAnpassungsfaktor(erstelleBenutzer(
					LocalDate.of(2008, 12, 6),
					new Benutzerangabe(
						Geschlecht.MAENNLICH,
						Erfahrung.BEGINNER,
						Ernaehrung.SCHLECHT,
						Schlafqualitaet.SCHLECHT,
						Stress.NIEDRIG,
						Doping.NEIN,
						Regenerationsfaehigkeit.SCHLECHT),
					Kraftlevel.CLASS_5,
					160,
					50),
				DATUM).werteAus()).isEqualTo(3),
			() -> assertThat(sut.berechneAnpassungsfaktor(erstelleBenutzer(
					LocalDate.of(1998, 12, 6),
					new Benutzerangabe(
						Geschlecht.MAENNLICH,
						Erfahrung.FORTGESCHRITTEN,
						Ernaehrung.DURCHSCHNITT,
						Schlafqualitaet.DURCHSCHNITT,
						Stress.MITTELMAESSIG,
						Doping.JA,
						Regenerationsfaehigkeit.UNTERDURCHSCHNITTLICH),
					Kraftlevel.CLASS_4,
					180,
					90),
				DATUM).werteAus()).isEqualTo(7),
			() -> assertThat(sut.berechneAnpassungsfaktor(erstelleBenutzer(
					LocalDate.of(1988, 12, 6),
					new Benutzerangabe(
						Geschlecht.MAENNLICH,
						Erfahrung.SEHR_FORTGESCHRITTEN,
						Ernaehrung.GUT,
						Schlafqualitaet.GUT,
						Stress.HOCH,
						Doping.JA,
						Regenerationsfaehigkeit.DURCHSCHNITTLICH),
					Kraftlevel.CLASS_3,
					190,
					110),
				DATUM).werteAus()).isEqualTo(-2),
			() -> assertThat(sut.berechneAnpassungsfaktor(erstelleBenutzer(
					LocalDate.of(1978, 12, 6),
					new Benutzerangabe(
						Geschlecht.MAENNLICH,
						Erfahrung.EXPERTE,
						Ernaehrung.GUT,
						Schlafqualitaet.GUT,
						Stress.HOCH,
						Doping.JA,
						Regenerationsfaehigkeit.GUT),
					Kraftlevel.CLASS_2,
					200,
					130),
				DATUM).werteAus()).isEqualTo(-8),
			() -> assertThat(sut.berechneAnpassungsfaktor(erstelleBenutzer(
					LocalDate.of(1968, 12, 6),
					new Benutzerangabe(
						Geschlecht.MAENNLICH,
						Erfahrung.EXPERTE,
						Ernaehrung.GUT,
						Schlafqualitaet.GUT,
						Stress.HOCH,
						Doping.JA,
						Regenerationsfaehigkeit.PERFEKT),
					Kraftlevel.CLASS_1,
					200,
					130),
				DATUM).werteAus()).isEqualTo(-10),
			() -> assertThat(sut.berechneAnpassungsfaktor(erstelleBenutzer(
					LocalDate.of(1968, 12, 6),
					new Benutzerangabe(
						Geschlecht.MAENNLICH,
						Erfahrung.EXPERTE,
						Ernaehrung.GUT,
						Schlafqualitaet.GUT,
						Stress.HOCH,
						Doping.JA,
						Regenerationsfaehigkeit.PERFEKT),
					Kraftlevel.MASTER,
					200,
					130),
				DATUM).werteAus()).isEqualTo(-12),
			() -> assertThat(sut.berechneAnpassungsfaktor(erstelleBenutzer(
					LocalDate.of(1968, 12, 6),
					new Benutzerangabe(
						Geschlecht.MAENNLICH,
						Erfahrung.EXPERTE,
						Ernaehrung.GUT,
						Schlafqualitaet.GUT,
						Stress.HOCH,
						Doping.JA,
						Regenerationsfaehigkeit.PERFEKT),
					Kraftlevel.ELITE,
					200,
					130),
				DATUM).werteAus()).isEqualTo(-12),
			() -> assertThat(sut.berechneAnpassungsfaktor(erstelleBenutzer(
					LocalDate.of(2008, 12, 6),
					new Benutzerangabe(
						Geschlecht.WEIBLICH,
						Erfahrung.BEGINNER,
						Ernaehrung.SCHLECHT,
						Schlafqualitaet.SCHLECHT,
						Stress.NIEDRIG,
						Doping.NEIN,
						Regenerationsfaehigkeit.SCHLECHT),
					Kraftlevel.CLASS_5,
					150,
					50),
				DATUM).werteAus()).isEqualTo(8),
			() -> assertThat(sut.berechneAnpassungsfaktor(erstelleBenutzer(
					LocalDate.of(1998, 12, 6),
					new Benutzerangabe(
						Geschlecht.WEIBLICH,
						Erfahrung.FORTGESCHRITTEN,
						Ernaehrung.DURCHSCHNITT,
						Schlafqualitaet.DURCHSCHNITT,
						Stress.MITTELMAESSIG,
						Doping.JA,
						Regenerationsfaehigkeit.UNTERDURCHSCHNITTLICH),
					Kraftlevel.CLASS_4,
					165,
					70),
				DATUM).werteAus()).isEqualTo(12),
			() -> assertThat(sut.berechneAnpassungsfaktor(erstelleBenutzer(
					LocalDate.of(1988, 12, 6),
					new Benutzerangabe(
						Geschlecht.WEIBLICH,
						Erfahrung.SEHR_FORTGESCHRITTEN,
						Ernaehrung.GUT,
						Schlafqualitaet.GUT,
						Stress.HOCH,
						Doping.JA,
						Regenerationsfaehigkeit.DURCHSCHNITTLICH),
					Kraftlevel.CLASS_3,
					170,
					80),
				DATUM).werteAus()).isEqualTo(3),
			() -> assertThat(sut.berechneAnpassungsfaktor(erstelleBenutzer(
					LocalDate.of(1978, 12, 6),
					new Benutzerangabe(
						Geschlecht.WEIBLICH,
						Erfahrung.EXPERTE,
						Ernaehrung.GUT,
						Schlafqualitaet.GUT,
						Stress.HOCH,
						Doping.JA,
						Regenerationsfaehigkeit.GUT),
					Kraftlevel.CLASS_2,
					180,
					90),
				DATUM).werteAus()).isEqualTo(-3),
			() -> assertThat(sut.berechneAnpassungsfaktor(erstelleBenutzer(
					LocalDate.of(1968, 12, 6),
					new Benutzerangabe(
						Geschlecht.WEIBLICH,
						Erfahrung.EXPERTE,
						Ernaehrung.GUT,
						Schlafqualitaet.GUT,
						Stress.HOCH,
						Doping.JA,
						Regenerationsfaehigkeit.PERFEKT),
					Kraftlevel.CLASS_1,
					180,
					90),
				DATUM).werteAus()).isEqualTo(-5),
			() -> assertThat(sut.berechneAnpassungsfaktor(erstelleBenutzer(
					LocalDate.of(1968, 12, 6),
					new Benutzerangabe(
						Geschlecht.WEIBLICH,
						Erfahrung.EXPERTE,
						Ernaehrung.GUT,
						Schlafqualitaet.GUT,
						Stress.HOCH,
						Doping.JA,
						Regenerationsfaehigkeit.PERFEKT),
					Kraftlevel.MASTER,
					180,
					90),
				DATUM).werteAus()).isEqualTo(-7),
			() -> assertThat(sut.berechneAnpassungsfaktor(erstelleBenutzer(
					LocalDate.of(1968, 12, 6),
					new Benutzerangabe(
						Geschlecht.WEIBLICH,
						Erfahrung.EXPERTE,
						Ernaehrung.GUT,
						Schlafqualitaet.GUT,
						Stress.HOCH,
						Doping.JA,
						Regenerationsfaehigkeit.PERFEKT),
					Kraftlevel.ELITE,
					180,
					90),
				DATUM).werteAus()).isEqualTo(-7));
	}
}
