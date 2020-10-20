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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class KraftlevelBerechnerSollte
{
	private KraftlevelErmittlung sut;

	@Test
	@DisplayName("das Kraftlevel für Justin setzen")
	void test01()
	{
		final var benutzer = Testdaten.BENUTZER_JUSTIN;

		sut = new KraftlevelErmittlung(benutzer);
		sut.ermittle();

		assertThat(benutzer.getBenutzerangabe().getKraftlevel()).isEqualTo(Kraftlevel.CLASS_5);
	}

	@Test
	@DisplayName("das Kraftlevel für eine Frau setzen")
	void test02()
	{
		final var benutzer = new Benutzer();
		final var koerpermessung =
			new Koerpermessung(
				new Primaerschluessel(),
				LocalDate.now(),
				new Koerpermasse(178, 90, 24),
				2500,
				2900,
				benutzer);
		benutzer.fuegeKoerpermessungHinzu(koerpermessung);
		benutzer.setName(new Name("M.", "Musterfrau"));
		benutzer.setGeburtsdatum(LocalDate.of(1976, 12, 6));
		benutzer.setBenutzerangabe(new Benutzerangabe(
			Geschlecht.WEIBLICH,
			Erfahrung.BEGINNER,
			Ernaehrung.GUT,
			Schlafqualitaet.GUT,
			Stress.MITTELMAESSIG,
			Doping.NEIN,
			Regenerationsfaehigkeit.GUT));
		final var kniebeugeKraftwert = new Kraftwert(
			new Primaerschluessel(),
			110,
			benutzer.getKoerpergewicht(),
			LocalDate.now(),
			Wiederholungen.ONE_REP_MAX,
			Testdaten.UEBUNG_LOWBAR_KNIEBEUGE,
			benutzer);
		benutzer.fuegeKraftwertHinzu(kniebeugeKraftwert);
		final var bankdrueckenKraftwert = new Kraftwert(
			new Primaerschluessel(),
			95,
			benutzer.getKoerpergewicht(),
			LocalDate.now(),
			Wiederholungen.ONE_REP_MAX,
			Testdaten.UEBUNG_WETTKAMPFBANKDRUECKEN,
			benutzer);
		benutzer.fuegeKraftwertHinzu(bankdrueckenKraftwert);
		final var kreuzhebenKraftwert = new Kraftwert(
			new Primaerschluessel(),
			140,
			benutzer.getKoerpergewicht(),
			LocalDate.now(),
			Wiederholungen.ONE_REP_MAX,
			Testdaten.UEBUNG_KONVENTIONELLES_KREUZHEBEN,
			benutzer);
		benutzer.fuegeKraftwertHinzu(kreuzhebenKraftwert);

		sut = new KraftlevelErmittlung(benutzer);
		sut.ermittle();

		assertThat(benutzer.getBenutzerangabe().getKraftlevel()).isEqualTo(Kraftlevel.CLASS_3);
	}
}
