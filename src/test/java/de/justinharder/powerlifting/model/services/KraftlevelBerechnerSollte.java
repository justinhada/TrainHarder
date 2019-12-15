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
		final Benutzer benutzer = new Benutzer();
		final var kniebeugeKraftwert = new Kraftwert(new WettkampfKniebeuge(), 110);
		final var bankdrueckenKraftwert = new Kraftwert(new WettkampfBankdruecken(), 95);
		final var kreuzhebenKraftwert = new Kraftwert(new WettkampfKreuzheben(), 140);
		final var kraftwerte = benutzer.getKraftwerte();
		kraftwerte.fuegeKraftwertHinzu(kniebeugeKraftwert);
		kraftwerte.fuegeKraftwertHinzu(bankdrueckenKraftwert);
		kraftwerte.fuegeKraftwertHinzu(kreuzhebenKraftwert);
		benutzer.setVorname("Justin");
		benutzer.setNachname("Harder");
		benutzer.setKoerpergewicht(90);
		benutzer.setKoerpergroesse(178);
		benutzer.setAlter(20);
		benutzer.setGeschlecht(Geschlecht.MAENNLICH);
		benutzer.setErfahrung(Erfahrung.BEGINNER);
		benutzer.setErnaehrung(Ernaehrung.GUT);
		benutzer.setSchlafqualitaet(Schlafqualitaet.GUT);
		benutzer.setStress(Stress.MITTELMAESSIG);
		benutzer.setDoping(Doping.NEIN);
		benutzer.setRegenerationsfaehigkeit(Regenerationsfaehigkeit.GUT);
		benutzer.setKraftwerte(kraftwerte);

		sut = new KraftlevelBerechner(benutzer);
		sut.setzeKraftlevel();

		assertThat(benutzer.getKraftlevel()).isEqualTo(Kraftlevel.CLASS_5);
	}

	@Test
	@DisplayName("das Kraftlevel für eine Frau setzen")
	public void test02()
	{
		final Benutzer benutzer = new Benutzer();
		final var kniebeugeKraftwert = new Kraftwert(new WettkampfKniebeuge(), 110);
		final var bankdrueckenKraftwert = new Kraftwert(new WettkampfBankdruecken(), 95);
		final var kreuzhebenKraftwert = new Kraftwert(new WettkampfKreuzheben(), 140);
		final var kraftwerte = benutzer.getKraftwerte();
		kraftwerte.fuegeKraftwertHinzu(kniebeugeKraftwert);
		kraftwerte.fuegeKraftwertHinzu(bankdrueckenKraftwert);
		kraftwerte.fuegeKraftwertHinzu(kreuzhebenKraftwert);
		benutzer.setVorname("M.");
		benutzer.setNachname("Musterfrau");
		benutzer.setKoerpergewicht(90);
		benutzer.setKoerpergroesse(180);
		benutzer.setAlter(43);
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
