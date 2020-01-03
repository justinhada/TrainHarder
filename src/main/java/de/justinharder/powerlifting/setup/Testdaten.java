package de.justinharder.powerlifting.setup;

import de.justinharder.powerlifting.model.domain.Belastungsfaktor;
import de.justinharder.powerlifting.model.domain.Benutzer;
import de.justinharder.powerlifting.model.domain.Kraftwert;
import de.justinharder.powerlifting.model.domain.Uebung;
import de.justinharder.powerlifting.model.domain.dto.BenutzerEintrag;
import de.justinharder.powerlifting.model.domain.dto.KraftwertEintrag;
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

public class Testdaten
{
	public static final Benutzer JUSTIN_BENUTZER = new Benutzer();
	public static final Benutzer EDUARD = new Benutzer();
	public static final Benutzer ANNA = new Benutzer();
	public static final Benutzer ANETTE = new Benutzer();
	public static final Benutzer GOTT = new Benutzer();
	public static final Benutzer BABA = new Benutzer();

	public static final BenutzerEintrag JUSTIN_BENUTZEREINTRAG = new BenutzerEintrag();
	public static final BenutzerEintrag ANETTE_BENUTZEREINTRAG = new BenutzerEintrag();

	public static final Belastungsfaktor BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN = new Belastungsfaktor();
	public static final Belastungsfaktor BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE = new Belastungsfaktor();
	public static final Belastungsfaktor BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN = new Belastungsfaktor();

	public static final Uebung WETTKAMPFBANKDRUECKEN = new Uebung();
	public static final Uebung LOWBAR_KNIEBEUGE = new Uebung();
	public static final Uebung KONVENTIONELLES_KREUZHEBEN = new Uebung();

	public static final Kraftwert KRAFTWERT_WETTKAMPFBANKDRUECKEN = new Kraftwert();
	public static final Kraftwert KRAFTWERT_LOWBAR_KNIEBEUGE = new Kraftwert();
	public static final Kraftwert KRAFTWERT_KONVENTIONELLES_KREUZHEBEN = new Kraftwert();

	public static final KraftwertEintrag KRAFTWERTEINTRAG_WETTKAMPFBANKDRUECKEN = new KraftwertEintrag();
	public static final KraftwertEintrag KRAFTWERTEINTRAG_LOWBAR_KNIEBEUGE = new KraftwertEintrag();
	public static final KraftwertEintrag KRAFTWERTEINTRAG_KONVENTIONELLES_KREUZHEBEN = new KraftwertEintrag();

	static
	{
		JUSTIN_BENUTZER.setVorname("Justin");
		JUSTIN_BENUTZER.setNachname("Harder");
		JUSTIN_BENUTZER.setKoerpergewicht(90);
		JUSTIN_BENUTZER.setKoerpergroesse(178);
		JUSTIN_BENUTZER.setLebensalter(21);
		JUSTIN_BENUTZER.setKraftlevel(Kraftlevel.CLASS_5);
		JUSTIN_BENUTZER.setGeschlecht(Geschlecht.MAENNLICH);
		JUSTIN_BENUTZER.setErfahrung(Erfahrung.BEGINNER);
		JUSTIN_BENUTZER.setErnaehrung(Ernaehrung.GUT);
		JUSTIN_BENUTZER.setSchlafqualitaet(Schlafqualitaet.GUT);
		JUSTIN_BENUTZER.setStress(Stress.MITTELMAESSIG);
		JUSTIN_BENUTZER.setDoping(Doping.NEIN);
		JUSTIN_BENUTZER.setRegenerationsfaehigkeit(Regenerationsfaehigkeit.GUT);

		JUSTIN_BENUTZEREINTRAG.setVorname("Justin");
		JUSTIN_BENUTZEREINTRAG.setNachname("Harder");
		JUSTIN_BENUTZEREINTRAG.setKoerpergewicht(90);
		JUSTIN_BENUTZEREINTRAG.setKoerpergroesse(178);
		JUSTIN_BENUTZEREINTRAG.setLebensalter(21);
		JUSTIN_BENUTZEREINTRAG.setKraftlevel(Kraftlevel.CLASS_5);
		JUSTIN_BENUTZEREINTRAG.setGeschlecht(Geschlecht.MAENNLICH);
		JUSTIN_BENUTZEREINTRAG.setErfahrung(Erfahrung.BEGINNER);
		JUSTIN_BENUTZEREINTRAG.setErnaehrung(Ernaehrung.GUT);
		JUSTIN_BENUTZEREINTRAG.setSchlafqualitaet(Schlafqualitaet.GUT);
		JUSTIN_BENUTZEREINTRAG.setStress(Stress.MITTELMAESSIG);
		JUSTIN_BENUTZEREINTRAG.setDoping(Doping.NEIN);
		JUSTIN_BENUTZEREINTRAG.setRegenerationsfaehigkeit(Regenerationsfaehigkeit.GUT);

		EDUARD.setVorname("Eduard");
		EDUARD.setNachname("Stremel");
		EDUARD.setKoerpergewicht(64);
		EDUARD.setKoerpergroesse(182);
		EDUARD.setLebensalter(14);
		EDUARD.setKraftlevel(Kraftlevel.CLASS_4);
		EDUARD.setGeschlecht(Geschlecht.MAENNLICH);
		EDUARD.setErfahrung(Erfahrung.FORTGESCHRITTEN);
		EDUARD.setErnaehrung(Ernaehrung.SCHLECHT);
		EDUARD.setSchlafqualitaet(Schlafqualitaet.SCHLECHT);
		EDUARD.setStress(Stress.NIEDRIG);
		EDUARD.setDoping(Doping.JA);
		EDUARD.setRegenerationsfaehigkeit(Regenerationsfaehigkeit.PERFEKT);

		ANNA.setVorname("Anna");
		ANNA.setNachname("Aufbau");
		ANNA.setKoerpergewicht(64);
		ANNA.setKoerpergroesse(182);
		ANNA.setLebensalter(14);
		ANNA.setKraftlevel(Kraftlevel.CLASS_2);
		ANNA.setGeschlecht(Geschlecht.WEIBLICH);
		ANNA.setErfahrung(Erfahrung.FORTGESCHRITTEN);
		ANNA.setErnaehrung(Ernaehrung.SCHLECHT);
		ANNA.setSchlafqualitaet(Schlafqualitaet.SCHLECHT);
		ANNA.setStress(Stress.NIEDRIG);
		ANNA.setDoping(Doping.JA);
		ANNA.setRegenerationsfaehigkeit(Regenerationsfaehigkeit.PERFEKT);

		ANETTE.setVorname("Anette");
		ANETTE.setNachname("Masseschwein");
		ANETTE.setKoerpergewicht(120);
		ANETTE.setKoerpergroesse(203);
		ANETTE.setLebensalter(43);
		ANETTE.setKraftlevel(Kraftlevel.CLASS_3);
		ANETTE.setGeschlecht(Geschlecht.WEIBLICH);
		ANETTE.setErfahrung(Erfahrung.EXPERTE);
		ANETTE.setErnaehrung(Ernaehrung.GUT);
		ANETTE.setSchlafqualitaet(Schlafqualitaet.DURCHSCHNITT);
		ANETTE.setStress(Stress.HOCH);
		ANETTE.setDoping(Doping.NEIN);
		ANETTE.setRegenerationsfaehigkeit(Regenerationsfaehigkeit.UNTERDURCHSCHNITTLICH);

		ANETTE_BENUTZEREINTRAG.setVorname("Anette");
		ANETTE_BENUTZEREINTRAG.setNachname("Masseschwein");
		ANETTE_BENUTZEREINTRAG.setKoerpergewicht(120);
		ANETTE_BENUTZEREINTRAG.setKoerpergroesse(203);
		ANETTE_BENUTZEREINTRAG.setLebensalter(43);
		ANETTE_BENUTZEREINTRAG.setKraftlevel(Kraftlevel.CLASS_3);
		ANETTE_BENUTZEREINTRAG.setGeschlecht(Geschlecht.WEIBLICH);
		ANETTE_BENUTZEREINTRAG.setErfahrung(Erfahrung.EXPERTE);
		ANETTE_BENUTZEREINTRAG.setErnaehrung(Ernaehrung.GUT);
		ANETTE_BENUTZEREINTRAG.setSchlafqualitaet(Schlafqualitaet.DURCHSCHNITT);
		ANETTE_BENUTZEREINTRAG.setStress(Stress.HOCH);
		ANETTE_BENUTZEREINTRAG.setDoping(Doping.NEIN);
		ANETTE_BENUTZEREINTRAG.setRegenerationsfaehigkeit(Regenerationsfaehigkeit.UNTERDURCHSCHNITTLICH);

		GOTT.setVorname("Elite");
		GOTT.setNachname("sein Vater");
		GOTT.setKoerpergewicht(145);
		GOTT.setKoerpergroesse(190);
		GOTT.setLebensalter(32);
		GOTT.setKraftlevel(Kraftlevel.ELITE);
		GOTT.setGeschlecht(Geschlecht.MAENNLICH);
		GOTT.setErfahrung(Erfahrung.EXPERTE);
		GOTT.setErnaehrung(Ernaehrung.GUT);
		GOTT.setSchlafqualitaet(Schlafqualitaet.GUT);
		GOTT.setStress(Stress.NIEDRIG);
		GOTT.setDoping(Doping.JA);
		GOTT.setRegenerationsfaehigkeit(Regenerationsfaehigkeit.PERFEKT);

		BABA.setVorname("Baba");
		BABA.setNachname("von Gewichten");
		BABA.setKoerpergewicht(110);
		BABA.setKoerpergroesse(160);
		BABA.setLebensalter(55);
		BABA.setKraftlevel(Kraftlevel.CLASS_1);
		BABA.setGeschlecht(Geschlecht.MAENNLICH);
		BABA.setErfahrung(Erfahrung.SEHR_FORTGESCHRITTEN);
		BABA.setErnaehrung(Ernaehrung.GUT);
		BABA.setSchlafqualitaet(Schlafqualitaet.GUT);
		BABA.setStress(Stress.NIEDRIG);
		BABA.setDoping(Doping.JA);
		BABA.setRegenerationsfaehigkeit(Regenerationsfaehigkeit.SCHLECHT);

		BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN.setBack(0.0);
		BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN.setBenchpress(1.0);
		BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN.setBiceps(0.0);
		BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN.setChest(1.0);
		BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN.setCore(0.0);
		BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN.setDeadlift(0.0);
		BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN.setGlutes(0.0);
		BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN.setHamstrings(0.0);
		BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN.setQuads(0.0);
		BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN.setShoulder(0.1);
		BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN.setSquat(0.0);
		BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN.setTriceps(0.7);

		WETTKAMPFBANKDRUECKEN.setName("Wettkampfbankdrücken (pausiert)");
		WETTKAMPFBANKDRUECKEN.setUebungsart(Uebungsart.GRUNDUEBUNG);
		WETTKAMPFBANKDRUECKEN.setUebungskategorie(Uebungskategorie.WETTKAMPF_BANKDRUECKEN);
		WETTKAMPFBANKDRUECKEN.setBelastungsfaktor(BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN);

		KRAFTWERT_WETTKAMPFBANKDRUECKEN.setUebung(WETTKAMPFBANKDRUECKEN);
		KRAFTWERT_WETTKAMPFBANKDRUECKEN.setMaximum(100);
		KRAFTWERT_WETTKAMPFBANKDRUECKEN.setBenutzer(JUSTIN_BENUTZER);

		KRAFTWERTEINTRAG_WETTKAMPFBANKDRUECKEN.setBenutzerVorname(JUSTIN_BENUTZER.getVorname());
		KRAFTWERTEINTRAG_WETTKAMPFBANKDRUECKEN.setBenutzerNachname(JUSTIN_BENUTZER.getNachname());
		KRAFTWERTEINTRAG_WETTKAMPFBANKDRUECKEN.setUebungName(WETTKAMPFBANKDRUECKEN.getName());
		KRAFTWERTEINTRAG_WETTKAMPFBANKDRUECKEN.setMaximum(KRAFTWERT_WETTKAMPFBANKDRUECKEN.getMaximum());

		BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE.setBack(0.2);
		BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE.setBenchpress(0.0);
		BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE.setBiceps(0.0);
		BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE.setChest(0.0);
		BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE.setCore(0.3);
		BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE.setDeadlift(0.0);
		BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE.setGlutes(1.0);
		BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE.setHamstrings(0.5);
		BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE.setQuads(1.0);
		BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE.setShoulder(0.0);
		BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE.setSquat(1.0);
		BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE.setTriceps(0.0);

		LOWBAR_KNIEBEUGE.setName("Lowbar-Kniebeuge");
		LOWBAR_KNIEBEUGE.setUebungsart(Uebungsart.GRUNDUEBUNG);
		LOWBAR_KNIEBEUGE.setUebungskategorie(Uebungskategorie.WETTKAMPF_KNIEBEUGE);
		LOWBAR_KNIEBEUGE.setBelastungsfaktor(BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE);

		KRAFTWERT_LOWBAR_KNIEBEUGE.setUebung(LOWBAR_KNIEBEUGE);
		KRAFTWERT_LOWBAR_KNIEBEUGE.setMaximum(150);
		KRAFTWERT_LOWBAR_KNIEBEUGE.setBenutzer(JUSTIN_BENUTZER);

		KRAFTWERTEINTRAG_LOWBAR_KNIEBEUGE.setBenutzerVorname(JUSTIN_BENUTZER.getVorname());
		KRAFTWERTEINTRAG_LOWBAR_KNIEBEUGE.setBenutzerNachname(JUSTIN_BENUTZER.getNachname());
		KRAFTWERTEINTRAG_LOWBAR_KNIEBEUGE.setUebungName(LOWBAR_KNIEBEUGE.getName());
		KRAFTWERTEINTRAG_LOWBAR_KNIEBEUGE.setMaximum(KRAFTWERT_LOWBAR_KNIEBEUGE.getMaximum());

		BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN.setBack(0.5);
		BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN.setBenchpress(0.0);
		BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN.setBiceps(0.0);
		BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN.setChest(0.0);
		BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN.setCore(0.3);
		BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN.setDeadlift(1.0);
		BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN.setGlutes(0.5);
		BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN.setHamstrings(0.5);
		BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN.setQuads(0.3);
		BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN.setShoulder(0.0);
		BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN.setSquat(0.0);
		BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN.setTriceps(0.0);

		KONVENTIONELLES_KREUZHEBEN.setName("Konventionelles Kreuzheben");
		KONVENTIONELLES_KREUZHEBEN.setUebungsart(Uebungsart.GRUNDUEBUNG);
		KONVENTIONELLES_KREUZHEBEN.setUebungskategorie(Uebungskategorie.WETTKAMPF_KREUZHEBEN);
		KONVENTIONELLES_KREUZHEBEN.setBelastungsfaktor(BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN);

		KRAFTWERT_KONVENTIONELLES_KREUZHEBEN.setUebung(KONVENTIONELLES_KREUZHEBEN);
		KRAFTWERT_KONVENTIONELLES_KREUZHEBEN.setMaximum(200);
		KRAFTWERT_KONVENTIONELLES_KREUZHEBEN.setBenutzer(JUSTIN_BENUTZER);

		KRAFTWERTEINTRAG_KONVENTIONELLES_KREUZHEBEN.setBenutzerVorname(JUSTIN_BENUTZER.getVorname());
		KRAFTWERTEINTRAG_KONVENTIONELLES_KREUZHEBEN.setBenutzerNachname(JUSTIN_BENUTZER.getNachname());
		KRAFTWERTEINTRAG_KONVENTIONELLES_KREUZHEBEN.setUebungName(KONVENTIONELLES_KREUZHEBEN.getName());
		KRAFTWERTEINTRAG_KONVENTIONELLES_KREUZHEBEN.setMaximum(KRAFTWERT_KONVENTIONELLES_KREUZHEBEN.getMaximum());
	}
}
