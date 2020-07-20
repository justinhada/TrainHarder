package de.justinharder.trainharder.model.services.berechnung;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.trainharder.model.domain.Benutzer;
import de.justinharder.trainharder.model.domain.Koerpermessung;
import de.justinharder.trainharder.model.domain.Kraftwert;
import de.justinharder.trainharder.model.domain.embeddables.Name;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.model.domain.enums.Doping;
import de.justinharder.trainharder.model.domain.enums.Erfahrung;
import de.justinharder.trainharder.model.domain.enums.Ernaehrung;
import de.justinharder.trainharder.model.domain.enums.Geschlecht;
import de.justinharder.trainharder.model.domain.enums.Kraftlevel;
import de.justinharder.trainharder.model.domain.enums.Regenerationsfaehigkeit;
import de.justinharder.trainharder.model.domain.enums.Schlafqualitaet;
import de.justinharder.trainharder.model.domain.enums.Stress;
import de.justinharder.trainharder.model.domain.enums.Wiederholungen;
import de.justinharder.trainharder.setup.Testdaten;

public class KraftlevelBerechnerSollte
{
	private KraftlevelBerechner sut;

	@Test
	@DisplayName("das Kraftlevel für Justin setzen")
	@Disabled("scheiße")
	public void test01()
	{
		final var benutzer = Testdaten.BENUTZER_JUSTIN;
		final var kniebeugeKraftwert = new Kraftwert(
			new Primaerschluessel(),
			110,
			benutzer.getAktuellesKoerpergewicht(),
			LocalDate.now(),
			Wiederholungen.ONE_REP_MAX,
			Testdaten.LOWBAR_KNIEBEUGE,
			benutzer);
		benutzer.fuegeKraftwertHinzu(kniebeugeKraftwert);
		final var bankdrueckenKraftwert = new Kraftwert(
			new Primaerschluessel(),
			95,
			benutzer.getAktuellesKoerpergewicht(),
			LocalDate.now(),
			Wiederholungen.ONE_REP_MAX,
			Testdaten.WETTKAMPFBANKDRUECKEN,
			benutzer);
		benutzer.fuegeKraftwertHinzu(bankdrueckenKraftwert);
		final var kreuzhebenKraftwert = new Kraftwert(
			new Primaerschluessel(),
			140,
			benutzer.getAktuellesKoerpergewicht(),
			LocalDate.now(),
			Wiederholungen.ONE_REP_MAX,
			Testdaten.KONVENTIONELLES_KREUZHEBEN,
			benutzer);
		benutzer.fuegeKraftwertHinzu(kreuzhebenKraftwert);

		sut = new KraftlevelBerechner(benutzer);
		sut.setzeKraftlevel();

		assertThat(benutzer.getKraftlevel()).isEqualTo(Kraftlevel.CLASS_5);
	}

	@Test
	@DisplayName("das Kraftlevel für eine Frau setzen")
	@Disabled("scheiße")
	public void test02()
	{
		final var benutzer = new Benutzer();
		final var koerperdaten =
			new Koerpermessung(new Primaerschluessel(), 90, 29, 24, 70, 20, 11, 50, 50, 70, 3, 17, 1900, 2500, 2900, 25,
				LocalDate.now(),
				benutzer);
		benutzer.fuegeKoerpermessungHinzu(koerperdaten);
		benutzer.setName(new Name("M.", "Musterfrau"));
		benutzer.setLebensalter(43);
		benutzer.setGeschlecht(Geschlecht.WEIBLICH);
		benutzer.setErfahrung(Erfahrung.BEGINNER);
		benutzer.setErnaehrung(Ernaehrung.GUT);
		benutzer.setSchlafqualitaet(Schlafqualitaet.GUT);
		benutzer.setStress(Stress.MITTELMAESSIG);
		benutzer.setDoping(Doping.NEIN);
		benutzer.setRegenerationsfaehigkeit(Regenerationsfaehigkeit.GUT);
		final var kniebeugeKraftwert = new Kraftwert(
			new Primaerschluessel(),
			110,
			benutzer.getAktuellesKoerpergewicht(),
			LocalDate.now(),
			Wiederholungen.ONE_REP_MAX,
			Testdaten.LOWBAR_KNIEBEUGE,
			benutzer);
		benutzer.fuegeKraftwertHinzu(kniebeugeKraftwert);
		final var bankdrueckenKraftwert = new Kraftwert(
			new Primaerschluessel(),
			95,
			benutzer.getAktuellesKoerpergewicht(),
			LocalDate.now(),
			Wiederholungen.ONE_REP_MAX,
			Testdaten.WETTKAMPFBANKDRUECKEN,
			benutzer);
		benutzer.fuegeKraftwertHinzu(bankdrueckenKraftwert);
		final var kreuzhebenKraftwert = new Kraftwert(
			new Primaerschluessel(),
			140,
			benutzer.getAktuellesKoerpergewicht(),
			LocalDate.now(),
			Wiederholungen.ONE_REP_MAX,
			Testdaten.KONVENTIONELLES_KREUZHEBEN,
			benutzer);
		benutzer.fuegeKraftwertHinzu(kreuzhebenKraftwert);

		sut = new KraftlevelBerechner(benutzer);
		sut.setzeKraftlevel();

		assertThat(benutzer.getKraftlevel()).isEqualTo(Kraftlevel.CLASS_3);
	}
}
