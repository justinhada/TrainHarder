package de.justinharder.powerlifting.setup;

import java.time.LocalDate;

import de.justinharder.powerlifting.model.domain.Belastungsfaktor;
import de.justinharder.powerlifting.model.domain.Benutzer;
import de.justinharder.powerlifting.model.domain.Kraftwert;
import de.justinharder.powerlifting.model.domain.Uebung;
import de.justinharder.powerlifting.model.domain.dto.BelastungsfaktorEintrag;
import de.justinharder.powerlifting.model.domain.dto.BenutzerEintrag;
import de.justinharder.powerlifting.model.domain.dto.KraftwertEintrag;
import de.justinharder.powerlifting.model.domain.dto.UebungEintrag;
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

public class Testdaten
{
	public static final Benutzer JUSTIN_BENUTZER = new Benutzer();
	public static final Benutzer EDUARD_BENUTZER = new Benutzer();
	public static final Benutzer ANNA_BENUTZER = new Benutzer();
	public static final Benutzer ANETTE_BENUTZER = new Benutzer();
	public static final Benutzer GOTT_BENUTZER = new Benutzer();
	public static final Benutzer BABA_BENUTZER = new Benutzer();

	public static final BenutzerEintrag JUSTIN_BENUTZEREINTRAG = new BenutzerEintrag();
	public static final BenutzerEintrag ANETTE_BENUTZEREINTRAG = new BenutzerEintrag();
	public static final BenutzerEintrag GOTT_BENUTZEREINTRAG = new BenutzerEintrag();

	public static final Belastungsfaktor BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN = new Belastungsfaktor();
	public static final Belastungsfaktor BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE = new Belastungsfaktor();
	public static final Belastungsfaktor BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN = new Belastungsfaktor();

	public static final BelastungsfaktorEintrag BELASTUNGSFAKTOREINTRAG_WETTKAMPFBANKDRUECKEN =
		new BelastungsfaktorEintrag();
	public static final BelastungsfaktorEintrag BELASTUNGSFAKTOREINTRAG_LOWBAR_KNIEBEUGE =
		new BelastungsfaktorEintrag();
	public static final BelastungsfaktorEintrag BELASTUNGSFAKTOREINTRAG_KONVENTIONELLES_KREUZHEBEN =
		new BelastungsfaktorEintrag();

	public static final Uebung WETTKAMPFBANKDRUECKEN = new Uebung();
	public static final Uebung LOWBAR_KNIEBEUGE = new Uebung();
	public static final Uebung KONVENTIONELLES_KREUZHEBEN = new Uebung();

	public static final UebungEintrag UEBUNGEINTRAG_WETTKAMPFBANKDRUECKEN = new UebungEintrag();
	public static final UebungEintrag UEBUNGEINTRAG_LOWBAR_KNIEBEUGE = new UebungEintrag();
	public static final UebungEintrag UEBUNGEINTRAG_KONVENTIONELLES_KREUZHEBEN = new UebungEintrag();

	public static final Kraftwert KRAFTWERT_WETTKAMPFBANKDRUECKEN = new Kraftwert();
	public static final Kraftwert KRAFTWERT_LOWBAR_KNIEBEUGE = new Kraftwert();
	public static final Kraftwert KRAFTWERT_KONVENTIONELLES_KREUZHEBEN = new Kraftwert();

	public static final KraftwertEintrag KRAFTWERTEINTRAG_WETTKAMPFBANKDRUECKEN = new KraftwertEintrag();
	public static final KraftwertEintrag KRAFTWERTEINTRAG_LOWBAR_KNIEBEUGE = new KraftwertEintrag();
	public static final KraftwertEintrag KRAFTWERTEINTRAG_KONVENTIONELLES_KREUZHEBEN = new KraftwertEintrag();

	static
	{
		JUSTIN_BENUTZER.setId(0);
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

		JUSTIN_BENUTZEREINTRAG.setId(0);
		JUSTIN_BENUTZEREINTRAG.setVorname("Justin");
		JUSTIN_BENUTZEREINTRAG.setNachname("Harder");
		JUSTIN_BENUTZEREINTRAG.setKoerpergewicht(90);
		JUSTIN_BENUTZEREINTRAG.setKoerpergroesse(178);
		JUSTIN_BENUTZEREINTRAG.setLebensalter(21);
		JUSTIN_BENUTZEREINTRAG.setKraftlevel(Kraftlevel.CLASS_5.name());
		JUSTIN_BENUTZEREINTRAG.setGeschlecht(Geschlecht.MAENNLICH.name());
		JUSTIN_BENUTZEREINTRAG.setErfahrung(Erfahrung.BEGINNER.name());
		JUSTIN_BENUTZEREINTRAG.setErnaehrung(Ernaehrung.GUT.name());
		JUSTIN_BENUTZEREINTRAG.setSchlafqualitaet(Schlafqualitaet.GUT.name());
		JUSTIN_BENUTZEREINTRAG.setStress(Stress.MITTELMAESSIG.name());
		JUSTIN_BENUTZEREINTRAG.setDoping(Doping.NEIN.name());
		JUSTIN_BENUTZEREINTRAG.setRegenerationsfaehigkeit(Regenerationsfaehigkeit.GUT.name());

		EDUARD_BENUTZER.setId(0);
		EDUARD_BENUTZER.setVorname("Eduard");
		EDUARD_BENUTZER.setNachname("Stremel");
		EDUARD_BENUTZER.setKoerpergewicht(64);
		EDUARD_BENUTZER.setKoerpergroesse(182);
		EDUARD_BENUTZER.setLebensalter(14);
		EDUARD_BENUTZER.setKraftlevel(Kraftlevel.CLASS_4);
		EDUARD_BENUTZER.setGeschlecht(Geschlecht.MAENNLICH);
		EDUARD_BENUTZER.setErfahrung(Erfahrung.FORTGESCHRITTEN);
		EDUARD_BENUTZER.setErnaehrung(Ernaehrung.SCHLECHT);
		EDUARD_BENUTZER.setSchlafqualitaet(Schlafqualitaet.SCHLECHT);
		EDUARD_BENUTZER.setStress(Stress.NIEDRIG);
		EDUARD_BENUTZER.setDoping(Doping.JA);
		EDUARD_BENUTZER.setRegenerationsfaehigkeit(Regenerationsfaehigkeit.PERFEKT);

		ANNA_BENUTZER.setId(0);
		ANNA_BENUTZER.setVorname("Anna");
		ANNA_BENUTZER.setNachname("Aufbau");
		ANNA_BENUTZER.setKoerpergewicht(64);
		ANNA_BENUTZER.setKoerpergroesse(182);
		ANNA_BENUTZER.setLebensalter(14);
		ANNA_BENUTZER.setKraftlevel(Kraftlevel.CLASS_2);
		ANNA_BENUTZER.setGeschlecht(Geschlecht.WEIBLICH);
		ANNA_BENUTZER.setErfahrung(Erfahrung.FORTGESCHRITTEN);
		ANNA_BENUTZER.setErnaehrung(Ernaehrung.SCHLECHT);
		ANNA_BENUTZER.setSchlafqualitaet(Schlafqualitaet.SCHLECHT);
		ANNA_BENUTZER.setStress(Stress.NIEDRIG);
		ANNA_BENUTZER.setDoping(Doping.JA);
		ANNA_BENUTZER.setRegenerationsfaehigkeit(Regenerationsfaehigkeit.PERFEKT);

		ANETTE_BENUTZER.setId(0);
		ANETTE_BENUTZER.setVorname("Anette");
		ANETTE_BENUTZER.setNachname("Masseschwein");
		ANETTE_BENUTZER.setKoerpergewicht(120);
		ANETTE_BENUTZER.setKoerpergroesse(203);
		ANETTE_BENUTZER.setLebensalter(43);
		ANETTE_BENUTZER.setKraftlevel(Kraftlevel.CLASS_3);
		ANETTE_BENUTZER.setGeschlecht(Geschlecht.WEIBLICH);
		ANETTE_BENUTZER.setErfahrung(Erfahrung.EXPERTE);
		ANETTE_BENUTZER.setErnaehrung(Ernaehrung.GUT);
		ANETTE_BENUTZER.setSchlafqualitaet(Schlafqualitaet.DURCHSCHNITT);
		ANETTE_BENUTZER.setStress(Stress.HOCH);
		ANETTE_BENUTZER.setDoping(Doping.NEIN);
		ANETTE_BENUTZER.setRegenerationsfaehigkeit(Regenerationsfaehigkeit.UNTERDURCHSCHNITTLICH);

		ANETTE_BENUTZEREINTRAG.setId(0);
		ANETTE_BENUTZEREINTRAG.setVorname("Anette");
		ANETTE_BENUTZEREINTRAG.setNachname("Masseschwein");
		ANETTE_BENUTZEREINTRAG.setKoerpergewicht(120);
		ANETTE_BENUTZEREINTRAG.setKoerpergroesse(203);
		ANETTE_BENUTZEREINTRAG.setLebensalter(43);
		ANETTE_BENUTZEREINTRAG.setKraftlevel(Kraftlevel.CLASS_3.name());
		ANETTE_BENUTZEREINTRAG.setGeschlecht(Geschlecht.WEIBLICH.name());
		ANETTE_BENUTZEREINTRAG.setErfahrung(Erfahrung.EXPERTE.name());
		ANETTE_BENUTZEREINTRAG.setErnaehrung(Ernaehrung.GUT.name());
		ANETTE_BENUTZEREINTRAG.setSchlafqualitaet(Schlafqualitaet.DURCHSCHNITT.name());
		ANETTE_BENUTZEREINTRAG.setStress(Stress.HOCH.name());
		ANETTE_BENUTZEREINTRAG.setDoping(Doping.NEIN.name());
		ANETTE_BENUTZEREINTRAG.setRegenerationsfaehigkeit(Regenerationsfaehigkeit.UNTERDURCHSCHNITTLICH.name());

		GOTT_BENUTZER.setId(0);
		GOTT_BENUTZER.setVorname("Gott");
		GOTT_BENUTZER.setNachname("Harder");
		GOTT_BENUTZER.setKoerpergewicht(145);
		GOTT_BENUTZER.setKoerpergroesse(190);
		GOTT_BENUTZER.setLebensalter(32);
		GOTT_BENUTZER.setKraftlevel(Kraftlevel.ELITE);
		GOTT_BENUTZER.setGeschlecht(Geschlecht.MAENNLICH);
		GOTT_BENUTZER.setErfahrung(Erfahrung.EXPERTE);
		GOTT_BENUTZER.setErnaehrung(Ernaehrung.GUT);
		GOTT_BENUTZER.setSchlafqualitaet(Schlafqualitaet.GUT);
		GOTT_BENUTZER.setStress(Stress.NIEDRIG);
		GOTT_BENUTZER.setDoping(Doping.JA);
		GOTT_BENUTZER.setRegenerationsfaehigkeit(Regenerationsfaehigkeit.PERFEKT);

		GOTT_BENUTZEREINTRAG.setId(0);
		GOTT_BENUTZEREINTRAG.setVorname("Gott");
		GOTT_BENUTZEREINTRAG.setNachname("Harder");
		GOTT_BENUTZEREINTRAG.setKoerpergewicht(145);
		GOTT_BENUTZEREINTRAG.setKoerpergroesse(190);
		GOTT_BENUTZEREINTRAG.setLebensalter(32);
		GOTT_BENUTZEREINTRAG.setKraftlevel(Kraftlevel.ELITE.name());
		GOTT_BENUTZEREINTRAG.setGeschlecht(Geschlecht.MAENNLICH.name());
		GOTT_BENUTZEREINTRAG.setErfahrung(Erfahrung.EXPERTE.name());
		GOTT_BENUTZEREINTRAG.setErnaehrung(Ernaehrung.GUT.name());
		GOTT_BENUTZEREINTRAG.setSchlafqualitaet(Schlafqualitaet.GUT.name());
		GOTT_BENUTZEREINTRAG.setStress(Stress.NIEDRIG.name());
		GOTT_BENUTZEREINTRAG.setDoping(Doping.JA.name());
		GOTT_BENUTZEREINTRAG.setRegenerationsfaehigkeit(Regenerationsfaehigkeit.PERFEKT.name());

		BABA_BENUTZER.setId(0);
		BABA_BENUTZER.setVorname("Baba");
		BABA_BENUTZER.setNachname("von Gewichten");
		BABA_BENUTZER.setKoerpergewicht(110);
		BABA_BENUTZER.setKoerpergroesse(160);
		BABA_BENUTZER.setLebensalter(55);
		BABA_BENUTZER.setKraftlevel(Kraftlevel.CLASS_1);
		BABA_BENUTZER.setGeschlecht(Geschlecht.MAENNLICH);
		BABA_BENUTZER.setErfahrung(Erfahrung.SEHR_FORTGESCHRITTEN);
		BABA_BENUTZER.setErnaehrung(Ernaehrung.GUT);
		BABA_BENUTZER.setSchlafqualitaet(Schlafqualitaet.GUT);
		BABA_BENUTZER.setStress(Stress.NIEDRIG);
		BABA_BENUTZER.setDoping(Doping.JA);
		BABA_BENUTZER.setRegenerationsfaehigkeit(Regenerationsfaehigkeit.SCHLECHT);

		BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN.setId(0);
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

		BELASTUNGSFAKTOREINTRAG_WETTKAMPFBANKDRUECKEN.setId(0);
		BELASTUNGSFAKTOREINTRAG_WETTKAMPFBANKDRUECKEN.setBack(0.0);
		BELASTUNGSFAKTOREINTRAG_WETTKAMPFBANKDRUECKEN.setBenchpress(1.0);
		BELASTUNGSFAKTOREINTRAG_WETTKAMPFBANKDRUECKEN.setBiceps(0.0);
		BELASTUNGSFAKTOREINTRAG_WETTKAMPFBANKDRUECKEN.setChest(1.0);
		BELASTUNGSFAKTOREINTRAG_WETTKAMPFBANKDRUECKEN.setCore(0.0);
		BELASTUNGSFAKTOREINTRAG_WETTKAMPFBANKDRUECKEN.setDeadlift(0.0);
		BELASTUNGSFAKTOREINTRAG_WETTKAMPFBANKDRUECKEN.setGlutes(0.0);
		BELASTUNGSFAKTOREINTRAG_WETTKAMPFBANKDRUECKEN.setHamstrings(0.0);
		BELASTUNGSFAKTOREINTRAG_WETTKAMPFBANKDRUECKEN.setQuads(0.0);
		BELASTUNGSFAKTOREINTRAG_WETTKAMPFBANKDRUECKEN.setShoulder(0.1);
		BELASTUNGSFAKTOREINTRAG_WETTKAMPFBANKDRUECKEN.setSquat(0.0);
		BELASTUNGSFAKTOREINTRAG_WETTKAMPFBANKDRUECKEN.setTriceps(0.7);

		WETTKAMPFBANKDRUECKEN.setId(0);
		WETTKAMPFBANKDRUECKEN.setName("Wettkampfbankdrücken (pausiert)");
		WETTKAMPFBANKDRUECKEN.setUebungsart(Uebungsart.GRUNDUEBUNG);
		WETTKAMPFBANKDRUECKEN.setUebungskategorie(Uebungskategorie.WETTKAMPF_BANKDRUECKEN);
		WETTKAMPFBANKDRUECKEN.setBelastungsfaktor(BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN);

		UEBUNGEINTRAG_WETTKAMPFBANKDRUECKEN.setId(WETTKAMPFBANKDRUECKEN.getId());
		UEBUNGEINTRAG_WETTKAMPFBANKDRUECKEN.setName(WETTKAMPFBANKDRUECKEN.getName());
		UEBUNGEINTRAG_WETTKAMPFBANKDRUECKEN.setUebungsart(WETTKAMPFBANKDRUECKEN.getUebungsart().name());
		UEBUNGEINTRAG_WETTKAMPFBANKDRUECKEN.setUebungskategorie(WETTKAMPFBANKDRUECKEN.getUebungskategorie().name());

		KRAFTWERT_WETTKAMPFBANKDRUECKEN.setId(0);
		KRAFTWERT_WETTKAMPFBANKDRUECKEN.setUebung(WETTKAMPFBANKDRUECKEN);
		KRAFTWERT_WETTKAMPFBANKDRUECKEN.setBenutzer(JUSTIN_BENUTZER);
		KRAFTWERT_WETTKAMPFBANKDRUECKEN.setMaximum(100);
		KRAFTWERT_WETTKAMPFBANKDRUECKEN.setKoerpergewicht(JUSTIN_BENUTZER.getKoerpergewicht());
		KRAFTWERT_WETTKAMPFBANKDRUECKEN.setDatum(LocalDate.now());
		KRAFTWERT_WETTKAMPFBANKDRUECKEN.setWiederholungen(Wiederholungen.ONE_REP_MAX);

		KRAFTWERTEINTRAG_WETTKAMPFBANKDRUECKEN.setId(0);
		KRAFTWERTEINTRAG_WETTKAMPFBANKDRUECKEN.setMaximum(KRAFTWERT_WETTKAMPFBANKDRUECKEN.getMaximum());
		KRAFTWERTEINTRAG_WETTKAMPFBANKDRUECKEN.setKoerpergewicht(KRAFTWERT_WETTKAMPFBANKDRUECKEN.getKoerpergewicht());
		KRAFTWERTEINTRAG_WETTKAMPFBANKDRUECKEN.setDatum(KRAFTWERT_WETTKAMPFBANKDRUECKEN.getDatum());
		KRAFTWERTEINTRAG_WETTKAMPFBANKDRUECKEN
			.setWiederholungen(KRAFTWERT_WETTKAMPFBANKDRUECKEN.getWiederholungen().name());

		BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE.setId(0);
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

		BELASTUNGSFAKTOREINTRAG_LOWBAR_KNIEBEUGE.setId(0);
		BELASTUNGSFAKTOREINTRAG_LOWBAR_KNIEBEUGE.setBack(0.2);
		BELASTUNGSFAKTOREINTRAG_LOWBAR_KNIEBEUGE.setBenchpress(0.0);
		BELASTUNGSFAKTOREINTRAG_LOWBAR_KNIEBEUGE.setBiceps(0.0);
		BELASTUNGSFAKTOREINTRAG_LOWBAR_KNIEBEUGE.setChest(0.0);
		BELASTUNGSFAKTOREINTRAG_LOWBAR_KNIEBEUGE.setCore(0.3);
		BELASTUNGSFAKTOREINTRAG_LOWBAR_KNIEBEUGE.setDeadlift(0.0);
		BELASTUNGSFAKTOREINTRAG_LOWBAR_KNIEBEUGE.setGlutes(1.0);
		BELASTUNGSFAKTOREINTRAG_LOWBAR_KNIEBEUGE.setHamstrings(0.5);
		BELASTUNGSFAKTOREINTRAG_LOWBAR_KNIEBEUGE.setQuads(1.0);
		BELASTUNGSFAKTOREINTRAG_LOWBAR_KNIEBEUGE.setShoulder(0.0);
		BELASTUNGSFAKTOREINTRAG_LOWBAR_KNIEBEUGE.setSquat(1.0);
		BELASTUNGSFAKTOREINTRAG_LOWBAR_KNIEBEUGE.setTriceps(0.0);

		LOWBAR_KNIEBEUGE.setId(0);
		LOWBAR_KNIEBEUGE.setName("Lowbar-Kniebeuge");
		LOWBAR_KNIEBEUGE.setUebungsart(Uebungsart.GRUNDUEBUNG);
		LOWBAR_KNIEBEUGE.setUebungskategorie(Uebungskategorie.WETTKAMPF_KNIEBEUGE);
		LOWBAR_KNIEBEUGE.setBelastungsfaktor(BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE);

		UEBUNGEINTRAG_LOWBAR_KNIEBEUGE.setId(LOWBAR_KNIEBEUGE.getId());
		UEBUNGEINTRAG_LOWBAR_KNIEBEUGE.setName(LOWBAR_KNIEBEUGE.getName());
		UEBUNGEINTRAG_LOWBAR_KNIEBEUGE.setUebungsart(LOWBAR_KNIEBEUGE.getUebungsart().name());
		UEBUNGEINTRAG_LOWBAR_KNIEBEUGE.setUebungskategorie(LOWBAR_KNIEBEUGE.getUebungskategorie().name());

		KRAFTWERT_LOWBAR_KNIEBEUGE.setId(0);
		KRAFTWERT_LOWBAR_KNIEBEUGE.setUebung(LOWBAR_KNIEBEUGE);
		KRAFTWERT_LOWBAR_KNIEBEUGE.setMaximum(150);
		KRAFTWERT_LOWBAR_KNIEBEUGE.setBenutzer(JUSTIN_BENUTZER);
		KRAFTWERT_LOWBAR_KNIEBEUGE.setKoerpergewicht(JUSTIN_BENUTZER.getKoerpergewicht());
		KRAFTWERT_LOWBAR_KNIEBEUGE.setDatum(LocalDate.now());
		KRAFTWERT_LOWBAR_KNIEBEUGE.setWiederholungen(Wiederholungen.ONE_REP_MAX);

		KRAFTWERTEINTRAG_LOWBAR_KNIEBEUGE.setId(0);
		KRAFTWERTEINTRAG_LOWBAR_KNIEBEUGE.setMaximum(KRAFTWERT_LOWBAR_KNIEBEUGE.getMaximum());
		KRAFTWERTEINTRAG_LOWBAR_KNIEBEUGE.setKoerpergewicht(KRAFTWERT_LOWBAR_KNIEBEUGE.getKoerpergewicht());
		KRAFTWERTEINTRAG_LOWBAR_KNIEBEUGE.setDatum(KRAFTWERT_LOWBAR_KNIEBEUGE.getDatum());
		KRAFTWERTEINTRAG_LOWBAR_KNIEBEUGE.setWiederholungen(KRAFTWERT_LOWBAR_KNIEBEUGE.getWiederholungen().name());

		BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN.setId(0);
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

		BELASTUNGSFAKTOREINTRAG_KONVENTIONELLES_KREUZHEBEN.setId(0);
		BELASTUNGSFAKTOREINTRAG_KONVENTIONELLES_KREUZHEBEN.setBack(0.5);
		BELASTUNGSFAKTOREINTRAG_KONVENTIONELLES_KREUZHEBEN.setBenchpress(0.0);
		BELASTUNGSFAKTOREINTRAG_KONVENTIONELLES_KREUZHEBEN.setBiceps(0.0);
		BELASTUNGSFAKTOREINTRAG_KONVENTIONELLES_KREUZHEBEN.setChest(0.0);
		BELASTUNGSFAKTOREINTRAG_KONVENTIONELLES_KREUZHEBEN.setCore(0.3);
		BELASTUNGSFAKTOREINTRAG_KONVENTIONELLES_KREUZHEBEN.setDeadlift(1.0);
		BELASTUNGSFAKTOREINTRAG_KONVENTIONELLES_KREUZHEBEN.setGlutes(0.5);
		BELASTUNGSFAKTOREINTRAG_KONVENTIONELLES_KREUZHEBEN.setHamstrings(0.5);
		BELASTUNGSFAKTOREINTRAG_KONVENTIONELLES_KREUZHEBEN.setQuads(0.3);
		BELASTUNGSFAKTOREINTRAG_KONVENTIONELLES_KREUZHEBEN.setShoulder(0.0);
		BELASTUNGSFAKTOREINTRAG_KONVENTIONELLES_KREUZHEBEN.setSquat(0.0);
		BELASTUNGSFAKTOREINTRAG_KONVENTIONELLES_KREUZHEBEN.setTriceps(0.0);

		KONVENTIONELLES_KREUZHEBEN.setId(0);
		KONVENTIONELLES_KREUZHEBEN.setName("Konventionelles Kreuzheben");
		KONVENTIONELLES_KREUZHEBEN.setUebungsart(Uebungsart.GRUNDUEBUNG);
		KONVENTIONELLES_KREUZHEBEN.setUebungskategorie(Uebungskategorie.WETTKAMPF_KREUZHEBEN);
		KONVENTIONELLES_KREUZHEBEN.setBelastungsfaktor(BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN);

		UEBUNGEINTRAG_KONVENTIONELLES_KREUZHEBEN.setId(KONVENTIONELLES_KREUZHEBEN.getId());
		UEBUNGEINTRAG_KONVENTIONELLES_KREUZHEBEN.setName(KONVENTIONELLES_KREUZHEBEN.getName());
		UEBUNGEINTRAG_KONVENTIONELLES_KREUZHEBEN.setUebungsart(KONVENTIONELLES_KREUZHEBEN.getUebungsart().name());
		UEBUNGEINTRAG_KONVENTIONELLES_KREUZHEBEN
			.setUebungskategorie(KONVENTIONELLES_KREUZHEBEN.getUebungskategorie().name());

		KRAFTWERT_KONVENTIONELLES_KREUZHEBEN.setId(0);
		KRAFTWERT_KONVENTIONELLES_KREUZHEBEN.setUebung(KONVENTIONELLES_KREUZHEBEN);
		KRAFTWERT_KONVENTIONELLES_KREUZHEBEN.setMaximum(200);
		KRAFTWERT_KONVENTIONELLES_KREUZHEBEN.setBenutzer(JUSTIN_BENUTZER);
		KRAFTWERT_KONVENTIONELLES_KREUZHEBEN.setKoerpergewicht(JUSTIN_BENUTZER.getKoerpergewicht());
		KRAFTWERT_KONVENTIONELLES_KREUZHEBEN.setDatum(LocalDate.now());
		KRAFTWERT_KONVENTIONELLES_KREUZHEBEN.setWiederholungen(Wiederholungen.ONE_REP_MAX);

		KRAFTWERTEINTRAG_KONVENTIONELLES_KREUZHEBEN.setId(0);
		KRAFTWERTEINTRAG_KONVENTIONELLES_KREUZHEBEN.setMaximum(KRAFTWERT_KONVENTIONELLES_KREUZHEBEN.getMaximum());
		KRAFTWERTEINTRAG_KONVENTIONELLES_KREUZHEBEN
			.setKoerpergewicht(KRAFTWERT_KONVENTIONELLES_KREUZHEBEN.getKoerpergewicht());
		KRAFTWERTEINTRAG_KONVENTIONELLES_KREUZHEBEN.setDatum(KRAFTWERT_KONVENTIONELLES_KREUZHEBEN.getDatum());
		KRAFTWERTEINTRAG_KONVENTIONELLES_KREUZHEBEN
			.setWiederholungen(KRAFTWERT_KONVENTIONELLES_KREUZHEBEN.getWiederholungen().name());
	}

	private Testdaten()
	{}
}
