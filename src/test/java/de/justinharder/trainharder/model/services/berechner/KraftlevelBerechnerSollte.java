package de.justinharder.trainharder.model.services.berechner;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.trainharder.model.domain.Belastungsfaktor;
import de.justinharder.trainharder.model.domain.Benutzer;
import de.justinharder.trainharder.model.domain.Koerpermessung;
import de.justinharder.trainharder.model.domain.Kraftwert;
import de.justinharder.trainharder.model.domain.Uebung;
import de.justinharder.trainharder.model.domain.enums.Doping;
import de.justinharder.trainharder.model.domain.enums.Erfahrung;
import de.justinharder.trainharder.model.domain.enums.Ernaehrung;
import de.justinharder.trainharder.model.domain.enums.Geschlecht;
import de.justinharder.trainharder.model.domain.enums.Kraftlevel;
import de.justinharder.trainharder.model.domain.enums.Regenerationsfaehigkeit;
import de.justinharder.trainharder.model.domain.enums.Schlafqualitaet;
import de.justinharder.trainharder.model.domain.enums.Stress;
import de.justinharder.trainharder.model.domain.enums.Uebungsart;
import de.justinharder.trainharder.model.domain.enums.Uebungskategorie;
import de.justinharder.trainharder.model.domain.enums.Wiederholungen;
import de.justinharder.trainharder.model.services.berechner.KraftlevelBerechner;
import de.justinharder.trainharder.setup.Testdaten;

public class KraftlevelBerechnerSollte
{
	private static final Uebung WETTKAMPF_KNIEBEUGE = new Uebung(
		"WettkampfKniebeuge",
		Uebungsart.GRUNDUEBUNG,
		Uebungskategorie.WETTKAMPF_KNIEBEUGE,
		new Belastungsfaktor());
	private static final Uebung WETTKAMPF_BANKDRUECKEN = new Uebung(
		"WettkampfBankdruecken",
		Uebungsart.GRUNDUEBUNG,
		Uebungskategorie.WETTKAMPF_BANKDRUECKEN,
		new Belastungsfaktor());
	private static final Uebung WETTKAMPF_KREUZHEBEN = new Uebung(
		"WettkampfKreuzheben",
		Uebungsart.GRUNDUEBUNG,
		Uebungskategorie.WETTKAMPF_KREUZHEBEN,
		new Belastungsfaktor());

	private KraftlevelBerechner sut;

	@Test
	@DisplayName("das Kraftlevel für Justin setzen")
	@Disabled("scheiße")
	public void test01()
	{
		final var benutzer = Testdaten.BENUTZER_JUSTIN;
		final var kniebeugeKraftwert = new Kraftwert(
			110,
			benutzer.getAktuellesKoerpergewicht(),
			LocalDate.now(),
			Wiederholungen.ONE_REP_MAX,
			WETTKAMPF_KNIEBEUGE,
			benutzer);
		benutzer.fuegeKraftwertHinzu(kniebeugeKraftwert);
		final var bankdrueckenKraftwert = new Kraftwert(
			95,
			benutzer.getAktuellesKoerpergewicht(),
			LocalDate.now(),
			Wiederholungen.ONE_REP_MAX,
			WETTKAMPF_BANKDRUECKEN,
			benutzer);
		benutzer.fuegeKraftwertHinzu(bankdrueckenKraftwert);
		final var kreuzhebenKraftwert = new Kraftwert(
			140,
			benutzer.getAktuellesKoerpergewicht(),
			LocalDate.now(),
			Wiederholungen.ONE_REP_MAX,
			WETTKAMPF_KREUZHEBEN,
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
			new Koerpermessung(90, 29, 24, 70, 20, 11, 50, 50, 70, 3, 17, 1900, 2500, 2900, 25, LocalDate.now(),
				benutzer);
		benutzer.fuegeKoerpermessungHinzu(koerperdaten);
		benutzer.setVorname("M.");
		benutzer.setNachname("Musterfrau");
		benutzer.setLebensalter(43);
		benutzer.setGeschlecht(Geschlecht.WEIBLICH);
		benutzer.setErfahrung(Erfahrung.BEGINNER);
		benutzer.setErnaehrung(Ernaehrung.GUT);
		benutzer.setSchlafqualitaet(Schlafqualitaet.GUT);
		benutzer.setStress(Stress.MITTELMAESSIG);
		benutzer.setDoping(Doping.NEIN);
		benutzer.setRegenerationsfaehigkeit(Regenerationsfaehigkeit.GUT);
		final var kniebeugeKraftwert = new Kraftwert(
			110,
			benutzer.getAktuellesKoerpergewicht(),
			LocalDate.now(),
			Wiederholungen.ONE_REP_MAX,
			WETTKAMPF_KNIEBEUGE,
			benutzer);
		benutzer.fuegeKraftwertHinzu(kniebeugeKraftwert);
		final var bankdrueckenKraftwert = new Kraftwert(
			95,
			benutzer.getAktuellesKoerpergewicht(),
			LocalDate.now(),
			Wiederholungen.ONE_REP_MAX,
			WETTKAMPF_BANKDRUECKEN,
			benutzer);
		benutzer.fuegeKraftwertHinzu(bankdrueckenKraftwert);
		final var kreuzhebenKraftwert = new Kraftwert(
			140,
			benutzer.getAktuellesKoerpergewicht(),
			LocalDate.now(),
			Wiederholungen.ONE_REP_MAX,
			WETTKAMPF_KREUZHEBEN,
			benutzer);
		benutzer.fuegeKraftwertHinzu(kreuzhebenKraftwert);

		sut = new KraftlevelBerechner(benutzer);
		sut.setzeKraftlevel();

		assertThat(benutzer.getKraftlevel()).isEqualTo(Kraftlevel.CLASS_3);
	}
}
