package de.justinharder.powerlifting.model.services;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.powerlifting.model.domain.Belastungsfaktor;
import de.justinharder.powerlifting.model.domain.Benutzer;
import de.justinharder.powerlifting.model.domain.Kraftwert;
import de.justinharder.powerlifting.model.domain.Uebung;
import de.justinharder.powerlifting.model.domain.enums.Doping;
import de.justinharder.powerlifting.model.domain.enums.Erfahrung;
import de.justinharder.powerlifting.model.domain.enums.Ernaehrung;
import de.justinharder.powerlifting.model.domain.enums.Geschlecht;
import de.justinharder.powerlifting.model.domain.enums.Kraftlevel;
import de.justinharder.powerlifting.model.domain.enums.Regenerationsfaehigkeit;
import de.justinharder.powerlifting.model.domain.enums.Schlafqualitaet;
import de.justinharder.powerlifting.model.domain.enums.Stress;
import de.justinharder.powerlifting.model.domain.enums.Uebungsart;
import de.justinharder.powerlifting.model.domain.enums.Uebungskategorie;
import de.justinharder.powerlifting.model.domain.enums.Wiederholungen;

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
	public void test01()
	{
		final var benutzer = new Benutzer();
		final var kniebeugeKraftwert = new Kraftwert(WETTKAMPF_KNIEBEUGE, benutzer, 110, benutzer.getKoerpergewicht(),
			LocalDate.now(), Wiederholungen.ONE_REP_MAX);
		benutzer.fuegeKraftwertHinzu(kniebeugeKraftwert);
		final var bankdrueckenKraftwert = new Kraftwert(WETTKAMPF_BANKDRUECKEN, benutzer, 95,
			benutzer.getKoerpergewicht(), LocalDate.now(), Wiederholungen.ONE_REP_MAX);
		benutzer.fuegeKraftwertHinzu(bankdrueckenKraftwert);
		final var kreuzhebenKraftwert = new Kraftwert(WETTKAMPF_KREUZHEBEN, benutzer, 140, benutzer.getKoerpergewicht(),
			LocalDate.now(), Wiederholungen.ONE_REP_MAX);
		benutzer.fuegeKraftwertHinzu(kreuzhebenKraftwert);
		benutzer.setVorname("Justin");
		benutzer.setNachname("Harder");
		benutzer.setKoerpergewicht(90);
		benutzer.setKoerpergroesse(178);
		benutzer.setLebensalter(20);
		benutzer.setGeschlecht(Geschlecht.MAENNLICH);
		benutzer.setErfahrung(Erfahrung.BEGINNER);
		benutzer.setErnaehrung(Ernaehrung.GUT);
		benutzer.setSchlafqualitaet(Schlafqualitaet.GUT);
		benutzer.setStress(Stress.MITTELMAESSIG);
		benutzer.setDoping(Doping.NEIN);
		benutzer.setRegenerationsfaehigkeit(Regenerationsfaehigkeit.GUT);

		sut = new KraftlevelBerechner(benutzer);
		sut.setzeKraftlevel();

		assertThat(benutzer.getKraftlevel()).isEqualTo(Kraftlevel.CLASS_5);
	}

	@Test
	@DisplayName("das Kraftlevel für eine Frau setzen")
	@Disabled("Falsch")
	public void test02()
	{
		final var benutzer = new Benutzer();
		benutzer.setVorname("M.");
		benutzer.setNachname("Musterfrau");
		benutzer.setKoerpergewicht(90);
		benutzer.setKoerpergroesse(180);
		benutzer.setLebensalter(43);
		benutzer.setGeschlecht(Geschlecht.WEIBLICH);
		benutzer.setErfahrung(Erfahrung.BEGINNER);
		benutzer.setErnaehrung(Ernaehrung.GUT);
		benutzer.setSchlafqualitaet(Schlafqualitaet.GUT);
		benutzer.setStress(Stress.MITTELMAESSIG);
		benutzer.setDoping(Doping.NEIN);
		benutzer.setRegenerationsfaehigkeit(Regenerationsfaehigkeit.GUT);
		final var kniebeugeKraftwert = new Kraftwert(WETTKAMPF_KNIEBEUGE, benutzer, 110, benutzer.getKoerpergewicht(),
			LocalDate.now(), Wiederholungen.ONE_REP_MAX);
		benutzer.fuegeKraftwertHinzu(kniebeugeKraftwert);
		final var bankdrueckenKraftwert = new Kraftwert(WETTKAMPF_BANKDRUECKEN, benutzer, 95,
			benutzer.getKoerpergewicht(), LocalDate.now(), Wiederholungen.ONE_REP_MAX);
		benutzer.fuegeKraftwertHinzu(bankdrueckenKraftwert);
		final var kreuzhebenKraftwert = new Kraftwert(WETTKAMPF_KREUZHEBEN, benutzer, 140, benutzer.getKoerpergewicht(),
			LocalDate.now(), Wiederholungen.ONE_REP_MAX);
		benutzer.fuegeKraftwertHinzu(kreuzhebenKraftwert);

		sut = new KraftlevelBerechner(benutzer);
		sut.setzeKraftlevel();

		assertThat(benutzer.getKraftlevel()).isEqualTo(Kraftlevel.CLASS_3);
	}
}
