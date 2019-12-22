package de.justinharder.powerlifting.model.services;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.powerlifting.model.domain.Benutzer;
import de.justinharder.powerlifting.model.domain.Kraftwert;
import de.justinharder.powerlifting.model.domain.enums.Doping;
import de.justinharder.powerlifting.model.domain.enums.Erfahrung;
import de.justinharder.powerlifting.model.domain.enums.Ernaehrung;
import de.justinharder.powerlifting.model.domain.enums.Geschlecht;
import de.justinharder.powerlifting.model.domain.enums.Kraftlevel;
import de.justinharder.powerlifting.model.domain.enums.Regenerationsfaehigkeit;
import de.justinharder.powerlifting.model.domain.enums.Schlafqualitaet;
import de.justinharder.powerlifting.model.domain.enums.Stress;
import de.justinharder.powerlifting.model.domain.uebungen.WettkampfBankdruecken;
import de.justinharder.powerlifting.model.domain.uebungen.WettkampfKniebeuge;
import de.justinharder.powerlifting.model.domain.uebungen.WettkampfKreuzheben;

public class KraftlevelBerechnerSollte
{
	private KraftlevelBerechner sut;

	@Test
	@DisplayName("das Kraftlevel für Justin setzen")
	public void test01()
	{
		final var benutzer = new Benutzer();
		final var kniebeugeKraftwert = new Kraftwert(1, new WettkampfKniebeuge(), 110, benutzer);
		benutzer.fuegeKraftwertHinzu(kniebeugeKraftwert);
		final var bankdrueckenKraftwert = new Kraftwert(2, new WettkampfBankdruecken(), 95, benutzer);
		benutzer.fuegeKraftwertHinzu(bankdrueckenKraftwert);
		final var kreuzhebenKraftwert = new Kraftwert(3, new WettkampfKreuzheben(), 140, benutzer);
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
	public void test02()
	{
		final var benutzer = new Benutzer();
		final var kniebeugeKraftwert = new Kraftwert(1, new WettkampfKniebeuge(), 110, benutzer);
		benutzer.fuegeKraftwertHinzu(kniebeugeKraftwert);
		final var bankdrueckenKraftwert = new Kraftwert(2, new WettkampfBankdruecken(), 95, benutzer);
		benutzer.fuegeKraftwertHinzu(bankdrueckenKraftwert);
		final var kreuzhebenKraftwert = new Kraftwert(3, new WettkampfKreuzheben(), 140, benutzer);
		benutzer.fuegeKraftwertHinzu(kreuzhebenKraftwert);
		final var kraftwerte = benutzer.getKraftwerte();
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
		benutzer.setKraftwerte(kraftwerte);

		sut = new KraftlevelBerechner(benutzer);
		sut.setzeKraftlevel();

		assertThat(benutzer.getKraftlevel()).isEqualTo(Kraftlevel.CLASS_3);
	}
}
