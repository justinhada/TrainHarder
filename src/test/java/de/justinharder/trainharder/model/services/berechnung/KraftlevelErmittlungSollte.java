package de.justinharder.trainharder.model.services.berechnung;

import de.justinharder.trainharder.model.domain.Benutzer;
import de.justinharder.trainharder.model.domain.Koerpermessung;
import de.justinharder.trainharder.model.domain.Kraftwert;
import de.justinharder.trainharder.model.domain.embeddables.Benutzerangabe;
import de.justinharder.trainharder.model.domain.embeddables.Koerpermasse;
import de.justinharder.trainharder.model.domain.embeddables.Name;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.model.domain.enums.*;
import de.justinharder.trainharder.setup.Testdaten;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class KraftlevelErmittlungSollte
{
	private KraftlevelErmittlung sut;

	@BeforeEach
	public void setup()
	{
		sut = new KraftlevelErmittlung();
	}

	private Benutzer erstelleBenutzer(Geschlecht geschlecht, double kniebeuge, double bankdruecken, double kreuzheben)
	{
		var benutzer = new Benutzer();
		return benutzer
			.fuegeKoerpermessungHinzu(new Koerpermessung(
				new Primaerschluessel(),
				LocalDate.now(),
				new Koerpermasse(178, 90, 24),
				2500,
				2900,
				benutzer))
			.setName(new Name("M.", "Muster"))
			.setGeburtsdatum(LocalDate.of(1976, 12, 6))
			.setBenutzerangabe(new Benutzerangabe(
				geschlecht,
				Erfahrung.BEGINNER,
				Ernaehrung.GUT,
				Schlafqualitaet.GUT,
				Stress.MITTELMAESSIG,
				Doping.NEIN,
				Regenerationsfaehigkeit.GUT))
			.fuegeKraftwertHinzu(new Kraftwert(
				new Primaerschluessel(),
				kniebeuge,
				benutzer.getKoerpergewicht(),
				LocalDate.now(),
				Wiederholungen.ONE_REP_MAX,
				Testdaten.UEBUNG_LOWBAR_KNIEBEUGE,
				benutzer))
			.fuegeKraftwertHinzu(new Kraftwert(
				new Primaerschluessel(),
				bankdruecken,
				benutzer.getKoerpergewicht(),
				LocalDate.now(),
				Wiederholungen.ONE_REP_MAX,
				Testdaten.UEBUNG_WETTKAMPFBANKDRUECKEN,
				benutzer))
			.fuegeKraftwertHinzu(new Kraftwert(
				new Primaerschluessel(),
				kreuzheben,
				benutzer.getKoerpergewicht(),
				LocalDate.now(),
				Wiederholungen.ONE_REP_MAX,
				Testdaten.UEBUNG_KONVENTIONELLES_KREUZHEBEN,
				benutzer));
	}

	private Benutzer erstelleMann(double kniebeuge, double bankdruecken, double kreuzheben)
	{
		return erstelleBenutzer(Geschlecht.MAENNLICH, kniebeuge, bankdruecken, kreuzheben);
	}

	private Benutzer erstelleFrau(double kniebeuge, double bankdruecken, double kreuzheben)
	{
		return erstelleBenutzer(Geschlecht.WEIBLICH, kniebeuge, bankdruecken, kreuzheben);
	}

	@Test
	@DisplayName("das Kraftlevel für einen Mann ermitteln")
	void test01()
	{
		assertAll(
			() -> assertThat(sut.ermittle(erstelleMann(110, 95, 140))).isEqualTo(Kraftlevel.CLASS_5),
			() -> assertThat(sut.ermittle(erstelleMann(150, 110, 170))).isEqualTo(Kraftlevel.CLASS_4),
			() -> assertThat(sut.ermittle(erstelleMann(170, 130, 200))).isEqualTo(Kraftlevel.CLASS_3),
			() -> assertThat(sut.ermittle(erstelleMann(200, 145, 230))).isEqualTo(Kraftlevel.CLASS_2),
			() -> assertThat(sut.ermittle(erstelleMann(220, 160, 250))).isEqualTo(Kraftlevel.CLASS_1),
			() -> assertThat(sut.ermittle(erstelleMann(240, 170, 270))).isEqualTo(Kraftlevel.MASTER),
			() -> assertThat(sut.ermittle(erstelleMann(260, 185, 290))).isEqualTo(Kraftlevel.ELITE));
	}

	@Test
	@DisplayName("das Kraftlevel für eine Frau ermitteln")
	void test02()
	{
		assertAll(
			() -> assertThat(sut.ermittle(erstelleFrau(100, 50, 110))).isEqualTo(Kraftlevel.CLASS_5),
			() -> assertThat(sut.ermittle(erstelleFrau(110, 60, 120))).isEqualTo(Kraftlevel.CLASS_4),
			() -> assertThat(sut.ermittle(erstelleFrau(120, 70, 130))).isEqualTo(Kraftlevel.CLASS_3),
			() -> assertThat(sut.ermittle(erstelleFrau(140, 90, 150))).isEqualTo(Kraftlevel.CLASS_2),
			() -> assertThat(sut.ermittle(erstelleFrau(150, 100, 160))).isEqualTo(Kraftlevel.CLASS_1),
			() -> assertThat(sut.ermittle(erstelleFrau(170, 110, 180))).isEqualTo(Kraftlevel.MASTER),
			() -> assertThat(sut.ermittle(erstelleFrau(200, 120, 220))).isEqualTo(Kraftlevel.ELITE));
	}
}
