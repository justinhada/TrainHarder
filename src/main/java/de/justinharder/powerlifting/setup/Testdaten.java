package de.justinharder.powerlifting.setup;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import de.justinharder.powerlifting.model.domain.Anmeldedaten;
import de.justinharder.powerlifting.model.domain.Belastungsfaktor;
import de.justinharder.powerlifting.model.domain.Benutzer;
import de.justinharder.powerlifting.model.domain.Koerpermessung;
import de.justinharder.powerlifting.model.domain.Kraftwert;
import de.justinharder.powerlifting.model.domain.Uebung;
import de.justinharder.powerlifting.model.domain.dto.AnmeldedatenEintrag;
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
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Testdaten
{
	public static final Anmeldedaten ANMELDEDATEN_JUSTIN = new Anmeldedaten();
	public static final Anmeldedaten ANMELDEDATEN_EDUARD = new Anmeldedaten();

	public static final AnmeldedatenEintrag ANMELDEDATENEINTRAG_JUSTIN = new AnmeldedatenEintrag();
	public static final AnmeldedatenEintrag ANMELDEDATENEINTRAG_EDUARD = new AnmeldedatenEintrag();

	public static final Koerpermessung KOERPERMESSUNG_JUSTIN = new Koerpermessung();
	public static final Koerpermessung KOERPERMESSUNG_EDUARD = new Koerpermessung();
	public static final Koerpermessung KOERPERMESSUNG_ANNA = new Koerpermessung();
	public static final Koerpermessung KOERPERMESSUNG_ANETTE = new Koerpermessung();
	public static final Koerpermessung KOERPERMESSUNG_GOTT = new Koerpermessung();
	public static final Koerpermessung KOERPERMESSUNG_BABA = new Koerpermessung();

	public static final Benutzer BENUTZER_JUSTIN = new Benutzer();
	public static final Benutzer BENUTZER_EDUARD = new Benutzer();
	public static final Benutzer BENUTZER_ANNA = new Benutzer();
	public static final Benutzer BENUTZER_ANETTE = new Benutzer();
	public static final Benutzer BENUTZER_GOTT = new Benutzer();
	public static final Benutzer BENUTZER_BABA = new Benutzer();

	public static final BenutzerEintrag BENUTZEREINTRAG_JUSTIN = new BenutzerEintrag();
	public static final BenutzerEintrag BENUTZEREINTRAG_ANETTE = new BenutzerEintrag();
	public static final BenutzerEintrag BENUTZEREINTRAG_GOTT = new BenutzerEintrag();

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
		ANMELDEDATEN_JUSTIN.setId(0);
		ANMELDEDATEN_JUSTIN.setMail("mail@justinharder.de");
		ANMELDEDATEN_JUSTIN.setBenutzername("harder");
		ANMELDEDATEN_JUSTIN.setPasswort("JustinHarder#98");
		ANMELDEDATEN_JUSTIN.setBenutzer(BENUTZER_JUSTIN);

		ANMELDEDATEN_EDUARD.setId(0);
		ANMELDEDATEN_EDUARD.setMail("mail@eduard.de");
		ANMELDEDATEN_EDUARD.setBenutzername("eduard");
		ANMELDEDATEN_EDUARD.setPasswort("EduardEduardEduard_98");
		ANMELDEDATEN_EDUARD.setBenutzer(BENUTZER_EDUARD);

		ANMELDEDATENEINTRAG_JUSTIN.setId(ANMELDEDATEN_JUSTIN.getId());
		ANMELDEDATENEINTRAG_JUSTIN.setMail(ANMELDEDATEN_JUSTIN.getMail());
		ANMELDEDATENEINTRAG_JUSTIN.setBenutzername(ANMELDEDATEN_JUSTIN.getBenutzername());
		ANMELDEDATENEINTRAG_JUSTIN.setPasswort(ANMELDEDATEN_JUSTIN.getPasswort());

		ANMELDEDATENEINTRAG_EDUARD.setId(ANMELDEDATEN_EDUARD.getId());
		ANMELDEDATENEINTRAG_EDUARD.setMail(ANMELDEDATEN_EDUARD.getMail());
		ANMELDEDATENEINTRAG_EDUARD.setBenutzername(ANMELDEDATEN_EDUARD.getBenutzername());
		ANMELDEDATENEINTRAG_EDUARD.setPasswort(ANMELDEDATEN_EDUARD.getPasswort());

		KOERPERMESSUNG_JUSTIN.setId(0);
		KOERPERMESSUNG_JUSTIN.setKoerpergroesse(178);
		KOERPERMESSUNG_JUSTIN.setKoerpergewicht(90);
		KOERPERMESSUNG_JUSTIN.setBodyMassIndex(28.41);
		KOERPERMESSUNG_JUSTIN.setFatFreeMassIndex(21.43);
		KOERPERMESSUNG_JUSTIN.setKoerperfettAnteil(25);
		KOERPERMESSUNG_JUSTIN.setFettfreiesKoerpergewicht(71);
		KOERPERMESSUNG_JUSTIN.setSubkutanesFett(20);
		KOERPERMESSUNG_JUSTIN.setViszeralfett(11);
		KOERPERMESSUNG_JUSTIN.setKoerperwasser(56);
		KOERPERMESSUNG_JUSTIN.setSkelettmuskel(49);
		KOERPERMESSUNG_JUSTIN.setMuskelmasse(67);
		KOERPERMESSUNG_JUSTIN.setKnochenmasse(4);
		KOERPERMESSUNG_JUSTIN.setProtein(17.5);
		KOERPERMESSUNG_JUSTIN.setGrundumsatz(1900);
		KOERPERMESSUNG_JUSTIN.setEingenommeneKalorien(2500);
		KOERPERMESSUNG_JUSTIN.setVerbrannteKalorien(2900);
		KOERPERMESSUNG_JUSTIN.setBiologischesAlter(25);
		KOERPERMESSUNG_JUSTIN.setDatum(LocalDate.now());
		KOERPERMESSUNG_JUSTIN.setBenutzer(BENUTZER_JUSTIN);

		KOERPERMESSUNG_EDUARD.setKoerpergroesse(182);
		KOERPERMESSUNG_EDUARD.setKoerpergewicht(64);

		KOERPERMESSUNG_ANNA.setKoerpergroesse(182);
		KOERPERMESSUNG_ANNA.setKoerpergewicht(64);

		KOERPERMESSUNG_ANETTE.setKoerpergroesse(203);
		KOERPERMESSUNG_ANETTE.setKoerpergewicht(125);

		KOERPERMESSUNG_GOTT.setKoerpergroesse(190);
		KOERPERMESSUNG_GOTT.setKoerpergewicht(145);

		KOERPERMESSUNG_BABA.setKoerpergroesse(160);
		KOERPERMESSUNG_BABA.setKoerpergewicht(105);

		BENUTZER_JUSTIN.setId(0);
		BENUTZER_JUSTIN.setVorname("Justin");
		BENUTZER_JUSTIN.setNachname("Harder");
		BENUTZER_JUSTIN.fuegeKoerpermessungHinzu(KOERPERMESSUNG_JUSTIN);
		BENUTZER_JUSTIN.setLebensalter(21);
		BENUTZER_JUSTIN.setKraftlevel(Kraftlevel.CLASS_5);
		BENUTZER_JUSTIN.setGeschlecht(Geschlecht.MAENNLICH);
		BENUTZER_JUSTIN.setErfahrung(Erfahrung.BEGINNER);
		BENUTZER_JUSTIN.setErnaehrung(Ernaehrung.GUT);
		BENUTZER_JUSTIN.setSchlafqualitaet(Schlafqualitaet.GUT);
		BENUTZER_JUSTIN.setStress(Stress.MITTELMAESSIG);
		BENUTZER_JUSTIN.setDoping(Doping.NEIN);
		BENUTZER_JUSTIN.setRegenerationsfaehigkeit(Regenerationsfaehigkeit.GUT);
		BENUTZER_JUSTIN.setAnmeldedaten(ANMELDEDATEN_JUSTIN);

		BENUTZEREINTRAG_JUSTIN.setId(BENUTZER_JUSTIN.getId());
		BENUTZEREINTRAG_JUSTIN.setVorname(BENUTZER_JUSTIN.getVorname());
		BENUTZEREINTRAG_JUSTIN.setNachname(BENUTZER_JUSTIN.getNachname());
		BENUTZEREINTRAG_JUSTIN.setKoerpergewicht(BENUTZER_JUSTIN.getAktuellesKoerpergewicht());
		BENUTZEREINTRAG_JUSTIN.setKoerpergroesse(BENUTZER_JUSTIN.getAktuelleKoerpergroesse());
		BENUTZEREINTRAG_JUSTIN.setLebensalter(BENUTZER_JUSTIN.getLebensalter());
		BENUTZEREINTRAG_JUSTIN.setKraftlevel(BENUTZER_JUSTIN.getKraftlevel().name());
		BENUTZEREINTRAG_JUSTIN.setGeschlecht(BENUTZER_JUSTIN.getGeschlecht().name());
		BENUTZEREINTRAG_JUSTIN.setErfahrung(BENUTZER_JUSTIN.getErfahrung().name());
		BENUTZEREINTRAG_JUSTIN.setErnaehrung(BENUTZER_JUSTIN.getErnaehrung().name());
		BENUTZEREINTRAG_JUSTIN.setSchlafqualitaet(BENUTZER_JUSTIN.getSchlafqualitaet().name());
		BENUTZEREINTRAG_JUSTIN.setStress(BENUTZER_JUSTIN.getStress().name());
		BENUTZEREINTRAG_JUSTIN.setDoping(BENUTZER_JUSTIN.getDoping().name());
		BENUTZEREINTRAG_JUSTIN.setRegenerationsfaehigkeit(BENUTZER_JUSTIN.getRegenerationsfaehigkeit().name());

		BENUTZER_EDUARD.setId(0);
		BENUTZER_EDUARD.setVorname("Eduard");
		BENUTZER_EDUARD.setNachname("Stremel");
		BENUTZER_EDUARD.fuegeKoerpermessungHinzu(KOERPERMESSUNG_EDUARD);
		BENUTZER_EDUARD.setLebensalter(14);
		BENUTZER_EDUARD.setKraftlevel(Kraftlevel.CLASS_4);
		BENUTZER_EDUARD.setGeschlecht(Geschlecht.MAENNLICH);
		BENUTZER_EDUARD.setErfahrung(Erfahrung.FORTGESCHRITTEN);
		BENUTZER_EDUARD.setErnaehrung(Ernaehrung.SCHLECHT);
		BENUTZER_EDUARD.setSchlafqualitaet(Schlafqualitaet.SCHLECHT);
		BENUTZER_EDUARD.setStress(Stress.NIEDRIG);
		BENUTZER_EDUARD.setDoping(Doping.JA);
		BENUTZER_EDUARD.setRegenerationsfaehigkeit(Regenerationsfaehigkeit.PERFEKT);

		BENUTZER_ANNA.setId(0);
		BENUTZER_ANNA.setVorname("Anna");
		BENUTZER_ANNA.setNachname("Aufbau");
		BENUTZER_ANNA.fuegeKoerpermessungHinzu(KOERPERMESSUNG_ANNA);
		BENUTZER_ANNA.setLebensalter(14);
		BENUTZER_ANNA.setKraftlevel(Kraftlevel.CLASS_2);
		BENUTZER_ANNA.setGeschlecht(Geschlecht.WEIBLICH);
		BENUTZER_ANNA.setErfahrung(Erfahrung.FORTGESCHRITTEN);
		BENUTZER_ANNA.setErnaehrung(Ernaehrung.SCHLECHT);
		BENUTZER_ANNA.setSchlafqualitaet(Schlafqualitaet.SCHLECHT);
		BENUTZER_ANNA.setStress(Stress.NIEDRIG);
		BENUTZER_ANNA.setDoping(Doping.JA);
		BENUTZER_ANNA.setRegenerationsfaehigkeit(Regenerationsfaehigkeit.PERFEKT);

		BENUTZER_ANETTE.setId(0);
		BENUTZER_ANETTE.setVorname("Anette");
		BENUTZER_ANETTE.setNachname("Masseschwein");
		BENUTZER_ANETTE.fuegeKoerpermessungHinzu(KOERPERMESSUNG_ANETTE);
		BENUTZER_ANETTE.setLebensalter(43);
		BENUTZER_ANETTE.setKraftlevel(Kraftlevel.CLASS_3);
		BENUTZER_ANETTE.setGeschlecht(Geschlecht.WEIBLICH);
		BENUTZER_ANETTE.setErfahrung(Erfahrung.EXPERTE);
		BENUTZER_ANETTE.setErnaehrung(Ernaehrung.GUT);
		BENUTZER_ANETTE.setSchlafqualitaet(Schlafqualitaet.DURCHSCHNITT);
		BENUTZER_ANETTE.setStress(Stress.HOCH);
		BENUTZER_ANETTE.setDoping(Doping.NEIN);
		BENUTZER_ANETTE.setRegenerationsfaehigkeit(Regenerationsfaehigkeit.UNTERDURCHSCHNITTLICH);

		BENUTZEREINTRAG_ANETTE.setId(0);
		BENUTZEREINTRAG_ANETTE.setVorname("Anette");
		BENUTZEREINTRAG_ANETTE.setNachname("Masseschwein");
		BENUTZEREINTRAG_ANETTE.setKoerpergewicht(BENUTZER_ANETTE.getAktuellesKoerpergewicht());
		BENUTZEREINTRAG_ANETTE.setKoerpergroesse(203);
		BENUTZEREINTRAG_ANETTE.setLebensalter(43);
		BENUTZEREINTRAG_ANETTE.setKraftlevel(Kraftlevel.CLASS_3.name());
		BENUTZEREINTRAG_ANETTE.setGeschlecht(Geschlecht.WEIBLICH.name());
		BENUTZEREINTRAG_ANETTE.setErfahrung(Erfahrung.EXPERTE.name());
		BENUTZEREINTRAG_ANETTE.setErnaehrung(Ernaehrung.GUT.name());
		BENUTZEREINTRAG_ANETTE.setSchlafqualitaet(Schlafqualitaet.DURCHSCHNITT.name());
		BENUTZEREINTRAG_ANETTE.setStress(Stress.HOCH.name());
		BENUTZEREINTRAG_ANETTE.setDoping(Doping.NEIN.name());
		BENUTZEREINTRAG_ANETTE.setRegenerationsfaehigkeit(Regenerationsfaehigkeit.UNTERDURCHSCHNITTLICH.name());

		BENUTZER_GOTT.setId(0);
		BENUTZER_GOTT.setVorname("Gott");
		BENUTZER_GOTT.setNachname("Harder");
		BENUTZER_GOTT.fuegeKoerpermessungHinzu(KOERPERMESSUNG_GOTT);
		BENUTZER_GOTT.setLebensalter(32);
		BENUTZER_GOTT.setKraftlevel(Kraftlevel.ELITE);
		BENUTZER_GOTT.setGeschlecht(Geschlecht.MAENNLICH);
		BENUTZER_GOTT.setErfahrung(Erfahrung.EXPERTE);
		BENUTZER_GOTT.setErnaehrung(Ernaehrung.GUT);
		BENUTZER_GOTT.setSchlafqualitaet(Schlafqualitaet.GUT);
		BENUTZER_GOTT.setStress(Stress.NIEDRIG);
		BENUTZER_GOTT.setDoping(Doping.JA);
		BENUTZER_GOTT.setRegenerationsfaehigkeit(Regenerationsfaehigkeit.PERFEKT);

		BENUTZEREINTRAG_GOTT.setId(0);
		BENUTZEREINTRAG_GOTT.setVorname("Gott");
		BENUTZEREINTRAG_GOTT.setNachname("Harder");
		BENUTZEREINTRAG_GOTT.setKoerpergewicht(BENUTZER_GOTT.getAktuellesKoerpergewicht());
		BENUTZEREINTRAG_GOTT.setKoerpergroesse(BENUTZER_GOTT.getAktuelleKoerpergroesse());
		BENUTZEREINTRAG_GOTT.setLebensalter(32);
		BENUTZEREINTRAG_GOTT.setKraftlevel(Kraftlevel.ELITE.name());
		BENUTZEREINTRAG_GOTT.setGeschlecht(Geschlecht.MAENNLICH.name());
		BENUTZEREINTRAG_GOTT.setErfahrung(Erfahrung.EXPERTE.name());
		BENUTZEREINTRAG_GOTT.setErnaehrung(Ernaehrung.GUT.name());
		BENUTZEREINTRAG_GOTT.setSchlafqualitaet(Schlafqualitaet.GUT.name());
		BENUTZEREINTRAG_GOTT.setStress(Stress.NIEDRIG.name());
		BENUTZEREINTRAG_GOTT.setDoping(Doping.JA.name());
		BENUTZEREINTRAG_GOTT.setRegenerationsfaehigkeit(Regenerationsfaehigkeit.PERFEKT.name());

		BENUTZER_BABA.setId(0);
		BENUTZER_BABA.setVorname("Baba");
		BENUTZER_BABA.setNachname("von Gewichten");
		BENUTZER_BABA.fuegeKoerpermessungHinzu(KOERPERMESSUNG_BABA);
		BENUTZER_BABA.setLebensalter(55);
		BENUTZER_BABA.setKraftlevel(Kraftlevel.CLASS_1);
		BENUTZER_BABA.setGeschlecht(Geschlecht.MAENNLICH);
		BENUTZER_BABA.setErfahrung(Erfahrung.SEHR_FORTGESCHRITTEN);
		BENUTZER_BABA.setErnaehrung(Ernaehrung.GUT);
		BENUTZER_BABA.setSchlafqualitaet(Schlafqualitaet.GUT);
		BENUTZER_BABA.setStress(Stress.NIEDRIG);
		BENUTZER_BABA.setDoping(Doping.JA);
		BENUTZER_BABA.setRegenerationsfaehigkeit(Regenerationsfaehigkeit.SCHLECHT);

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
		KRAFTWERT_WETTKAMPFBANKDRUECKEN.setBenutzer(BENUTZER_JUSTIN);
		KRAFTWERT_WETTKAMPFBANKDRUECKEN.setMaximum(100);
		KRAFTWERT_WETTKAMPFBANKDRUECKEN.setKoerpergewicht(BENUTZER_JUSTIN.getAktuellesKoerpergewicht());
		KRAFTWERT_WETTKAMPFBANKDRUECKEN.setDatum(LocalDate.now());
		KRAFTWERT_WETTKAMPFBANKDRUECKEN.setWiederholungen(Wiederholungen.ONE_REP_MAX);

		KRAFTWERTEINTRAG_WETTKAMPFBANKDRUECKEN.setId(0);
		//		KRAFTWERTEINTRAG_WETTKAMPFBANKDRUECKEN
		//			.setUebungId(String.valueOf(KRAFTWERT_WETTKAMPFBANKDRUECKEN.getUebung().getId()));
		//		KRAFTWERTEINTRAG_WETTKAMPFBANKDRUECKEN
		//			.setBenutzerId(String.valueOf(KRAFTWERT_WETTKAMPFBANKDRUECKEN.getBenutzer().getId()));
		KRAFTWERTEINTRAG_WETTKAMPFBANKDRUECKEN.setMaximum(KRAFTWERT_WETTKAMPFBANKDRUECKEN.getMaximum());
		KRAFTWERTEINTRAG_WETTKAMPFBANKDRUECKEN.setKoerpergewicht(KRAFTWERT_WETTKAMPFBANKDRUECKEN.getKoerpergewicht());
		KRAFTWERTEINTRAG_WETTKAMPFBANKDRUECKEN
			.setDatum(KRAFTWERT_WETTKAMPFBANKDRUECKEN.getDatum().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
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
		KRAFTWERT_LOWBAR_KNIEBEUGE.setBenutzer(BENUTZER_JUSTIN);
		//		KRAFTWERT_LOWBAR_KNIEBEUGE.setKoerpergewicht(JUSTIN_BENUTZER.getKoerpergewicht());
		KRAFTWERT_LOWBAR_KNIEBEUGE.setDatum(LocalDate.now());
		KRAFTWERT_LOWBAR_KNIEBEUGE.setWiederholungen(Wiederholungen.ONE_REP_MAX);

		KRAFTWERTEINTRAG_LOWBAR_KNIEBEUGE.setId(0);
		//		KRAFTWERTEINTRAG_LOWBAR_KNIEBEUGE.setUebungId(String.valueOf(KRAFTWERT_LOWBAR_KNIEBEUGE.getUebung().getId()));
		//		KRAFTWERTEINTRAG_LOWBAR_KNIEBEUGE
		//			.setBenutzerId(String.valueOf(KRAFTWERT_LOWBAR_KNIEBEUGE.getBenutzer().getId()));
		KRAFTWERTEINTRAG_LOWBAR_KNIEBEUGE.setMaximum(KRAFTWERT_LOWBAR_KNIEBEUGE.getMaximum());
		KRAFTWERTEINTRAG_LOWBAR_KNIEBEUGE.setKoerpergewicht(KRAFTWERT_LOWBAR_KNIEBEUGE.getKoerpergewicht());
		KRAFTWERTEINTRAG_LOWBAR_KNIEBEUGE
			.setDatum(KRAFTWERT_LOWBAR_KNIEBEUGE.getDatum().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
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
		KRAFTWERT_KONVENTIONELLES_KREUZHEBEN.setBenutzer(BENUTZER_JUSTIN);
		//		KRAFTWERT_KONVENTIONELLES_KREUZHEBEN.setKoerpergewicht(JUSTIN_BENUTZER.getKoerpergewicht());
		KRAFTWERT_KONVENTIONELLES_KREUZHEBEN.setDatum(LocalDate.now());
		KRAFTWERT_KONVENTIONELLES_KREUZHEBEN.setWiederholungen(Wiederholungen.ONE_REP_MAX);

		KRAFTWERTEINTRAG_KONVENTIONELLES_KREUZHEBEN.setId(0);
		//		KRAFTWERTEINTRAG_KONVENTIONELLES_KREUZHEBEN
		//			.setUebungId(String.valueOf(KRAFTWERT_KONVENTIONELLES_KREUZHEBEN.getUebung().getId()));
		//		KRAFTWERTEINTRAG_KONVENTIONELLES_KREUZHEBEN
		//			.setBenutzerId(String.valueOf(KRAFTWERT_KONVENTIONELLES_KREUZHEBEN.getBenutzer().getId()));
		KRAFTWERTEINTRAG_KONVENTIONELLES_KREUZHEBEN.setMaximum(KRAFTWERT_KONVENTIONELLES_KREUZHEBEN.getMaximum());
		KRAFTWERTEINTRAG_KONVENTIONELLES_KREUZHEBEN
			.setKoerpergewicht(KRAFTWERT_KONVENTIONELLES_KREUZHEBEN.getKoerpergewicht());
		KRAFTWERTEINTRAG_KONVENTIONELLES_KREUZHEBEN.setDatum(
			KRAFTWERT_KONVENTIONELLES_KREUZHEBEN.getDatum().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
		KRAFTWERTEINTRAG_KONVENTIONELLES_KREUZHEBEN
			.setWiederholungen(KRAFTWERT_KONVENTIONELLES_KREUZHEBEN.getWiederholungen().name());
	}
}
