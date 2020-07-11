package de.justinharder.trainharder.setup;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import de.justinharder.trainharder.model.domain.Authentifizierung;
import de.justinharder.trainharder.model.domain.Belastungsfaktor;
import de.justinharder.trainharder.model.domain.Benutzer;
import de.justinharder.trainharder.model.domain.Koerpermessung;
import de.justinharder.trainharder.model.domain.Kraftwert;
import de.justinharder.trainharder.model.domain.Primaerschluessel;
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
import de.justinharder.trainharder.view.dto.AuthentifizierungDto;
import de.justinharder.trainharder.view.dto.BelastungsfaktorDto;
import de.justinharder.trainharder.view.dto.BenutzerDto;
import de.justinharder.trainharder.view.dto.KraftwertDto;
import de.justinharder.trainharder.view.dto.UebungDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Testdaten
{
	private static final String DATUMSFORMAT = "dd.MM.yyyy";

	public static final Primaerschluessel AUTHENTIFIZIERUNG_JUSTIN_ID = new Primaerschluessel();
	public static final Primaerschluessel AUTHENTIFIZIERUNG_EDUARD_ID = new Primaerschluessel();
	public static final Primaerschluessel AUTHENTIFIZIERUNG_ANETTE_ID = new Primaerschluessel();
	public static final Primaerschluessel AUTHENTIFIZIERUNG_GOTT_ID = new Primaerschluessel();

	public static final Authentifizierung AUTHENTIFIZIERUNG_JUSTIN = new Authentifizierung();
	public static final Authentifizierung AUTHENTIFIZIERUNG_EDUARD = new Authentifizierung();
	public static final Authentifizierung AUTHENTIFIZIERUNG_ANETTE = new Authentifizierung();
	public static final Authentifizierung AUTHENTIFIZIERUNG_GOTT = new Authentifizierung();

	public static final AuthentifizierungDto AUTHENTIFIZIERUNGEINTRAG_JUSTIN = new AuthentifizierungDto();
	public static final AuthentifizierungDto AUTHENTIFIZIERUNGEINTRAG_EDUARD = new AuthentifizierungDto();
	public static final AuthentifizierungDto AUTHENTIFIZIERUNGEINTRAG_ANETTE = new AuthentifizierungDto();
	public static final AuthentifizierungDto AUTHENTIFIZIERUNGEINTRAG_GOTT = new AuthentifizierungDto();

	public static final Primaerschluessel KOERPERMESSUNG_JUSTIN_ID = new Primaerschluessel();
	public static final Primaerschluessel KOERPERMESSUNG_EDUARD_ID = new Primaerschluessel();
	public static final Primaerschluessel KOERPERMESSUNG_ANNA_ID = new Primaerschluessel();
	public static final Primaerschluessel KOERPERMESSUNG_ANETTE_ID = new Primaerschluessel();
	public static final Primaerschluessel KOERPERMESSUNG_GOTT_ID = new Primaerschluessel();
	public static final Primaerschluessel KOERPERMESSUNG_BABA_ID = new Primaerschluessel();

	public static final Koerpermessung KOERPERMESSUNG_JUSTIN = new Koerpermessung();
	public static final Koerpermessung KOERPERMESSUNG_EDUARD = new Koerpermessung();
	public static final Koerpermessung KOERPERMESSUNG_ANNA = new Koerpermessung();
	public static final Koerpermessung KOERPERMESSUNG_ANETTE = new Koerpermessung();
	public static final Koerpermessung KOERPERMESSUNG_GOTT = new Koerpermessung();
	public static final Koerpermessung KOERPERMESSUNG_BABA = new Koerpermessung();

	public static final Primaerschluessel BENUTZER_JUSTIN_ID = new Primaerschluessel();
	public static final Primaerschluessel BENUTZER_EDUARD_ID = new Primaerschluessel();
	public static final Primaerschluessel BENUTZER_ANNA_ID = new Primaerschluessel();
	public static final Primaerschluessel BENUTZER_ANETTE_ID = new Primaerschluessel();
	public static final Primaerschluessel BENUTZER_GOTT_ID = new Primaerschluessel();
	public static final Primaerschluessel BENUTZER_BABA_ID = new Primaerschluessel();

	public static final Benutzer BENUTZER_JUSTIN = new Benutzer();
	public static final Benutzer BENUTZER_EDUARD = new Benutzer();
	public static final Benutzer BENUTZER_ANNA = new Benutzer();
	public static final Benutzer BENUTZER_ANETTE = new Benutzer();
	public static final Benutzer BENUTZER_GOTT = new Benutzer();
	public static final Benutzer BENUTZER_BABA = new Benutzer();

	public static final BenutzerDto BENUTZEREINTRAG_JUSTIN = new BenutzerDto();
	public static final BenutzerDto BENUTZEREINTRAG_ANETTE = new BenutzerDto();
	public static final BenutzerDto BENUTZEREINTRAG_GOTT = new BenutzerDto();

	public static final Primaerschluessel BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN_ID = new Primaerschluessel();
	public static final Primaerschluessel BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE_ID = new Primaerschluessel();
	public static final Primaerschluessel BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN_ID = new Primaerschluessel();

	public static final Belastungsfaktor BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN = new Belastungsfaktor();
	public static final Belastungsfaktor BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE = new Belastungsfaktor();
	public static final Belastungsfaktor BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN = new Belastungsfaktor();

	public static final BelastungsfaktorDto BELASTUNGSFAKTOREINTRAG_WETTKAMPFBANKDRUECKEN =
		new BelastungsfaktorDto();
	public static final BelastungsfaktorDto BELASTUNGSFAKTOREINTRAG_LOWBAR_KNIEBEUGE =
		new BelastungsfaktorDto();
	public static final BelastungsfaktorDto BELASTUNGSFAKTOREINTRAG_KONVENTIONELLES_KREUZHEBEN =
		new BelastungsfaktorDto();

	public static final Primaerschluessel WETTKAMPFBANKDRUECKEN_ID = new Primaerschluessel();
	public static final Primaerschluessel LOWBAR_KNIEBEUGE_ID = new Primaerschluessel();
	public static final Primaerschluessel KONVENTIONELLES_KREUZHEBEN_ID = new Primaerschluessel();

	public static final Uebung WETTKAMPFBANKDRUECKEN = new Uebung();
	public static final Uebung LOWBAR_KNIEBEUGE = new Uebung();
	public static final Uebung KONVENTIONELLES_KREUZHEBEN = new Uebung();

	public static final UebungDto UEBUNGEINTRAG_WETTKAMPFBANKDRUECKEN = new UebungDto();
	public static final UebungDto UEBUNGEINTRAG_LOWBAR_KNIEBEUGE = new UebungDto();
	public static final UebungDto UEBUNGEINTRAG_KONVENTIONELLES_KREUZHEBEN = new UebungDto();

	public static final Primaerschluessel KRAFTWERT_WETTKAMPFBANKDRUECKEN_ID = new Primaerschluessel();
	public static final Primaerschluessel KRAFTWERT_LOWBAR_KNIEBEUGE_ID = new Primaerschluessel();
	public static final Primaerschluessel KRAFTWERT_KONVENTIONELLES_KREUZHEBEN_ID = new Primaerschluessel();

	public static final Kraftwert KRAFTWERT_WETTKAMPFBANKDRUECKEN = new Kraftwert();
	public static final Kraftwert KRAFTWERT_LOWBAR_KNIEBEUGE = new Kraftwert();
	public static final Kraftwert KRAFTWERT_KONVENTIONELLES_KREUZHEBEN = new Kraftwert();

	public static final KraftwertDto KRAFTWERTEINTRAG_WETTKAMPFBANKDRUECKEN = new KraftwertDto();
	public static final KraftwertDto KRAFTWERTEINTRAG_LOWBAR_KNIEBEUGE = new KraftwertDto();
	public static final KraftwertDto KRAFTWERTEINTRAG_KONVENTIONELLES_KREUZHEBEN = new KraftwertDto();

	static
	{
		AUTHENTIFIZIERUNG_JUSTIN.setPrimaerschluessel(AUTHENTIFIZIERUNG_JUSTIN_ID);
		AUTHENTIFIZIERUNG_JUSTIN.setMail("mail@justinharder.de");
		AUTHENTIFIZIERUNG_JUSTIN.setBenutzername("harder");
		AUTHENTIFIZIERUNG_JUSTIN.setPasswort("JustinHarder#98");
		AUTHENTIFIZIERUNG_JUSTIN.setBenutzer(BENUTZER_JUSTIN);

		AUTHENTIFIZIERUNG_EDUARD.setPrimaerschluessel(AUTHENTIFIZIERUNG_EDUARD_ID);
		AUTHENTIFIZIERUNG_EDUARD.setMail("mail@eduard.de");
		AUTHENTIFIZIERUNG_EDUARD.setBenutzername("eduard");
		AUTHENTIFIZIERUNG_EDUARD.setPasswort("EduardEduardEduard_98");
		AUTHENTIFIZIERUNG_EDUARD.setBenutzer(BENUTZER_EDUARD);

		AUTHENTIFIZIERUNG_ANETTE.setPrimaerschluessel(AUTHENTIFIZIERUNG_EDUARD_ID);
		AUTHENTIFIZIERUNG_ANETTE.setMail("mail@anette.de");
		AUTHENTIFIZIERUNG_ANETTE.setBenutzername("anette");
		AUTHENTIFIZIERUNG_ANETTE.setPasswort("AnetteAnette_98");
		AUTHENTIFIZIERUNG_ANETTE.setBenutzer(BENUTZER_ANETTE);

		AUTHENTIFIZIERUNG_GOTT.setPrimaerschluessel(AUTHENTIFIZIERUNG_GOTT_ID);
		AUTHENTIFIZIERUNG_GOTT.setMail("mail@gott.de");
		AUTHENTIFIZIERUNG_GOTT.setBenutzername("gott");
		AUTHENTIFIZIERUNG_GOTT.setPasswort("GottHarder_98");
		AUTHENTIFIZIERUNG_GOTT.setBenutzer(BENUTZER_GOTT);

		AUTHENTIFIZIERUNGEINTRAG_JUSTIN
			.setPrimaerschluessel(AUTHENTIFIZIERUNG_JUSTIN.getPrimaerschluessel().getId().toString());
		AUTHENTIFIZIERUNGEINTRAG_JUSTIN.setMail(AUTHENTIFIZIERUNG_JUSTIN.getMail());
		AUTHENTIFIZIERUNGEINTRAG_JUSTIN.setBenutzername(AUTHENTIFIZIERUNG_JUSTIN.getBenutzername());
		AUTHENTIFIZIERUNGEINTRAG_JUSTIN.setPasswort(AUTHENTIFIZIERUNG_JUSTIN.getPasswort());

		AUTHENTIFIZIERUNGEINTRAG_EDUARD
			.setPrimaerschluessel(AUTHENTIFIZIERUNG_EDUARD.getPrimaerschluessel().getId().toString());
		AUTHENTIFIZIERUNGEINTRAG_EDUARD.setMail(AUTHENTIFIZIERUNG_EDUARD.getMail());
		AUTHENTIFIZIERUNGEINTRAG_EDUARD.setBenutzername(AUTHENTIFIZIERUNG_EDUARD.getBenutzername());
		AUTHENTIFIZIERUNGEINTRAG_EDUARD.setPasswort(AUTHENTIFIZIERUNG_EDUARD.getPasswort());

		AUTHENTIFIZIERUNGEINTRAG_ANETTE
			.setPrimaerschluessel(AUTHENTIFIZIERUNG_ANETTE.getPrimaerschluessel().getId().toString());
		AUTHENTIFIZIERUNGEINTRAG_ANETTE.setMail(AUTHENTIFIZIERUNG_ANETTE.getMail());
		AUTHENTIFIZIERUNGEINTRAG_ANETTE.setBenutzername(AUTHENTIFIZIERUNG_ANETTE.getBenutzername());
		AUTHENTIFIZIERUNGEINTRAG_ANETTE.setPasswort(AUTHENTIFIZIERUNG_ANETTE.getPasswort());

		AUTHENTIFIZIERUNGEINTRAG_GOTT
			.setPrimaerschluessel(AUTHENTIFIZIERUNG_GOTT.getPrimaerschluessel().getId().toString());
		AUTHENTIFIZIERUNGEINTRAG_GOTT.setMail(AUTHENTIFIZIERUNG_GOTT.getMail());
		AUTHENTIFIZIERUNGEINTRAG_GOTT.setBenutzername(AUTHENTIFIZIERUNG_GOTT.getBenutzername());
		AUTHENTIFIZIERUNGEINTRAG_GOTT.setPasswort(AUTHENTIFIZIERUNG_GOTT.getPasswort());

		KOERPERMESSUNG_JUSTIN.setPrimaerschluessel(KOERPERMESSUNG_JUSTIN_ID);
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

		KOERPERMESSUNG_EDUARD.setPrimaerschluessel(KOERPERMESSUNG_EDUARD_ID);
		KOERPERMESSUNG_EDUARD.setKoerpergroesse(182);
		KOERPERMESSUNG_EDUARD.setKoerpergewicht(64);
		KOERPERMESSUNG_EDUARD.setBenutzer(BENUTZER_EDUARD);

		KOERPERMESSUNG_ANNA.setPrimaerschluessel(KOERPERMESSUNG_ANNA_ID);
		KOERPERMESSUNG_ANNA.setKoerpergroesse(182);
		KOERPERMESSUNG_ANNA.setKoerpergewicht(64);

		KOERPERMESSUNG_ANETTE.setPrimaerschluessel(KOERPERMESSUNG_ANETTE_ID);
		KOERPERMESSUNG_ANETTE.setKoerpergroesse(203);
		KOERPERMESSUNG_ANETTE.setKoerpergewicht(125);

		KOERPERMESSUNG_GOTT.setPrimaerschluessel(KOERPERMESSUNG_GOTT_ID);
		KOERPERMESSUNG_GOTT.setKoerpergroesse(190);
		KOERPERMESSUNG_GOTT.setKoerpergewicht(145);

		KOERPERMESSUNG_BABA.setPrimaerschluessel(KOERPERMESSUNG_BABA_ID);
		KOERPERMESSUNG_BABA.setKoerpergroesse(160);
		KOERPERMESSUNG_BABA.setKoerpergewicht(105);

		BENUTZER_JUSTIN.setPrimaerschluessel(BENUTZER_JUSTIN_ID);
		BENUTZER_JUSTIN.setVorname("Justin");
		BENUTZER_JUSTIN.setNachname("Harder");
		BENUTZER_JUSTIN.setLebensalter(21);
		BENUTZER_JUSTIN.setKraftlevel(Kraftlevel.CLASS_5);
		BENUTZER_JUSTIN.setGeschlecht(Geschlecht.MAENNLICH);
		BENUTZER_JUSTIN.setErfahrung(Erfahrung.BEGINNER);
		BENUTZER_JUSTIN.setErnaehrung(Ernaehrung.GUT);
		BENUTZER_JUSTIN.setSchlafqualitaet(Schlafqualitaet.GUT);
		BENUTZER_JUSTIN.setStress(Stress.MITTELMAESSIG);
		BENUTZER_JUSTIN.setDoping(Doping.NEIN);
		BENUTZER_JUSTIN.setRegenerationsfaehigkeit(Regenerationsfaehigkeit.GUT);
		BENUTZER_JUSTIN.setAuthentifizierung(AUTHENTIFIZIERUNG_JUSTIN);
		BENUTZER_JUSTIN.fuegeKoerpermessungHinzu(KOERPERMESSUNG_JUSTIN);

		BENUTZEREINTRAG_JUSTIN.setPrimaerschluessel(BENUTZER_JUSTIN.getPrimaerschluessel().getId().toString());
		BENUTZEREINTRAG_JUSTIN.setVorname(BENUTZER_JUSTIN.getVorname());
		BENUTZEREINTRAG_JUSTIN.setNachname(BENUTZER_JUSTIN.getNachname());
		BENUTZEREINTRAG_JUSTIN.setLebensalter(BENUTZER_JUSTIN.getLebensalter());
		BENUTZEREINTRAG_JUSTIN.setKraftlevel(BENUTZER_JUSTIN.getKraftlevel().name());
		BENUTZEREINTRAG_JUSTIN.setGeschlecht(BENUTZER_JUSTIN.getGeschlecht().name());
		BENUTZEREINTRAG_JUSTIN.setErfahrung(BENUTZER_JUSTIN.getErfahrung().name());
		BENUTZEREINTRAG_JUSTIN.setErnaehrung(BENUTZER_JUSTIN.getErnaehrung().name());
		BENUTZEREINTRAG_JUSTIN.setSchlafqualitaet(BENUTZER_JUSTIN.getSchlafqualitaet().name());
		BENUTZEREINTRAG_JUSTIN.setStress(BENUTZER_JUSTIN.getStress().name());
		BENUTZEREINTRAG_JUSTIN.setDoping(BENUTZER_JUSTIN.getDoping().name());
		BENUTZEREINTRAG_JUSTIN.setRegenerationsfaehigkeit(BENUTZER_JUSTIN.getRegenerationsfaehigkeit().name());
		BENUTZEREINTRAG_JUSTIN.setAuthentifizierung(AUTHENTIFIZIERUNGEINTRAG_JUSTIN);

		BENUTZER_EDUARD.setPrimaerschluessel(BENUTZER_EDUARD_ID);
		BENUTZER_EDUARD.setVorname("Eduard");
		BENUTZER_EDUARD.setNachname("Stremel");
		BENUTZER_EDUARD.setLebensalter(14);
		BENUTZER_EDUARD.setKraftlevel(Kraftlevel.CLASS_4);
		BENUTZER_EDUARD.setGeschlecht(Geschlecht.MAENNLICH);
		BENUTZER_EDUARD.setErfahrung(Erfahrung.FORTGESCHRITTEN);
		BENUTZER_EDUARD.setErnaehrung(Ernaehrung.SCHLECHT);
		BENUTZER_EDUARD.setSchlafqualitaet(Schlafqualitaet.SCHLECHT);
		BENUTZER_EDUARD.setStress(Stress.NIEDRIG);
		BENUTZER_EDUARD.setDoping(Doping.JA);
		BENUTZER_EDUARD.setRegenerationsfaehigkeit(Regenerationsfaehigkeit.PERFEKT);
		BENUTZER_EDUARD.setAuthentifizierung(AUTHENTIFIZIERUNG_EDUARD);
		BENUTZER_EDUARD.fuegeKoerpermessungHinzu(KOERPERMESSUNG_EDUARD);

		BENUTZER_ANNA.setPrimaerschluessel(BENUTZER_ANNA_ID);
		BENUTZER_ANNA.setVorname("Anna");
		BENUTZER_ANNA.setNachname("Aufbau");
		BENUTZER_ANNA.setLebensalter(14);
		BENUTZER_ANNA.setKraftlevel(Kraftlevel.CLASS_2);
		BENUTZER_ANNA.setGeschlecht(Geschlecht.WEIBLICH);
		BENUTZER_ANNA.setErfahrung(Erfahrung.FORTGESCHRITTEN);
		BENUTZER_ANNA.setErnaehrung(Ernaehrung.SCHLECHT);
		BENUTZER_ANNA.setSchlafqualitaet(Schlafqualitaet.SCHLECHT);
		BENUTZER_ANNA.setStress(Stress.NIEDRIG);
		BENUTZER_ANNA.setDoping(Doping.JA);
		BENUTZER_ANNA.setRegenerationsfaehigkeit(Regenerationsfaehigkeit.PERFEKT);
		BENUTZER_ANNA.fuegeKoerpermessungHinzu(KOERPERMESSUNG_ANNA);

		BENUTZER_ANETTE.setPrimaerschluessel(BENUTZER_ANETTE_ID);
		BENUTZER_ANETTE.setVorname("Anette");
		BENUTZER_ANETTE.setNachname("Masseschwein");
		BENUTZER_ANETTE.setLebensalter(43);
		BENUTZER_ANETTE.setKraftlevel(Kraftlevel.CLASS_3);
		BENUTZER_ANETTE.setGeschlecht(Geschlecht.WEIBLICH);
		BENUTZER_ANETTE.setErfahrung(Erfahrung.EXPERTE);
		BENUTZER_ANETTE.setErnaehrung(Ernaehrung.GUT);
		BENUTZER_ANETTE.setSchlafqualitaet(Schlafqualitaet.DURCHSCHNITT);
		BENUTZER_ANETTE.setStress(Stress.HOCH);
		BENUTZER_ANETTE.setDoping(Doping.NEIN);
		BENUTZER_ANETTE.setRegenerationsfaehigkeit(Regenerationsfaehigkeit.UNTERDURCHSCHNITTLICH);
		BENUTZER_ANETTE.setAuthentifizierung(AUTHENTIFIZIERUNG_ANETTE);
		BENUTZER_ANETTE.fuegeKoerpermessungHinzu(KOERPERMESSUNG_ANETTE);

		BENUTZEREINTRAG_ANETTE.setPrimaerschluessel(BENUTZER_ANETTE.getPrimaerschluessel().getId().toString());
		BENUTZEREINTRAG_ANETTE.setVorname("Anette");
		BENUTZEREINTRAG_ANETTE.setNachname("Masseschwein");
		BENUTZEREINTRAG_ANETTE.setLebensalter(43);
		BENUTZEREINTRAG_ANETTE.setKraftlevel(Kraftlevel.CLASS_3.name());
		BENUTZEREINTRAG_ANETTE.setGeschlecht(Geschlecht.WEIBLICH.name());
		BENUTZEREINTRAG_ANETTE.setErfahrung(Erfahrung.EXPERTE.name());
		BENUTZEREINTRAG_ANETTE.setErnaehrung(Ernaehrung.GUT.name());
		BENUTZEREINTRAG_ANETTE.setSchlafqualitaet(Schlafqualitaet.DURCHSCHNITT.name());
		BENUTZEREINTRAG_ANETTE.setStress(Stress.HOCH.name());
		BENUTZEREINTRAG_ANETTE.setDoping(Doping.NEIN.name());
		BENUTZEREINTRAG_ANETTE.setRegenerationsfaehigkeit(Regenerationsfaehigkeit.UNTERDURCHSCHNITTLICH.name());
		BENUTZEREINTRAG_ANETTE.setAuthentifizierung(AUTHENTIFIZIERUNGEINTRAG_ANETTE);

		BENUTZER_GOTT.setPrimaerschluessel(BENUTZER_GOTT_ID);
		BENUTZER_GOTT.setVorname("Gott");
		BENUTZER_GOTT.setNachname("Harder");
		BENUTZER_GOTT.setLebensalter(32);
		BENUTZER_GOTT.setKraftlevel(Kraftlevel.ELITE);
		BENUTZER_GOTT.setGeschlecht(Geschlecht.MAENNLICH);
		BENUTZER_GOTT.setErfahrung(Erfahrung.EXPERTE);
		BENUTZER_GOTT.setErnaehrung(Ernaehrung.GUT);
		BENUTZER_GOTT.setSchlafqualitaet(Schlafqualitaet.GUT);
		BENUTZER_GOTT.setStress(Stress.NIEDRIG);
		BENUTZER_GOTT.setDoping(Doping.JA);
		BENUTZER_GOTT.setRegenerationsfaehigkeit(Regenerationsfaehigkeit.PERFEKT);
		BENUTZER_GOTT.setAuthentifizierung(AUTHENTIFIZIERUNG_GOTT);
		BENUTZER_GOTT.fuegeKoerpermessungHinzu(KOERPERMESSUNG_GOTT);

		BENUTZEREINTRAG_GOTT.setPrimaerschluessel(BENUTZER_GOTT.getPrimaerschluessel().getId().toString());
		BENUTZEREINTRAG_GOTT.setVorname("Gott");
		BENUTZEREINTRAG_GOTT.setNachname("Harder");
		BENUTZEREINTRAG_GOTT.setLebensalter(32);
		BENUTZEREINTRAG_GOTT.setKraftlevel(Kraftlevel.ELITE.name());
		BENUTZEREINTRAG_GOTT.setGeschlecht(Geschlecht.MAENNLICH.name());
		BENUTZEREINTRAG_GOTT.setErfahrung(Erfahrung.EXPERTE.name());
		BENUTZEREINTRAG_GOTT.setErnaehrung(Ernaehrung.GUT.name());
		BENUTZEREINTRAG_GOTT.setSchlafqualitaet(Schlafqualitaet.GUT.name());
		BENUTZEREINTRAG_GOTT.setStress(Stress.NIEDRIG.name());
		BENUTZEREINTRAG_GOTT.setDoping(Doping.JA.name());
		BENUTZEREINTRAG_GOTT.setRegenerationsfaehigkeit(Regenerationsfaehigkeit.PERFEKT.name());
		BENUTZEREINTRAG_GOTT.setAuthentifizierung(AUTHENTIFIZIERUNGEINTRAG_GOTT);

		BENUTZER_BABA.setPrimaerschluessel(BENUTZER_BABA_ID);
		BENUTZER_BABA.setVorname("Baba");
		BENUTZER_BABA.setNachname("von Gewichten");
		BENUTZER_BABA.setLebensalter(55);
		BENUTZER_BABA.setKraftlevel(Kraftlevel.CLASS_1);
		BENUTZER_BABA.setGeschlecht(Geschlecht.MAENNLICH);
		BENUTZER_BABA.setErfahrung(Erfahrung.SEHR_FORTGESCHRITTEN);
		BENUTZER_BABA.setErnaehrung(Ernaehrung.GUT);
		BENUTZER_BABA.setSchlafqualitaet(Schlafqualitaet.GUT);
		BENUTZER_BABA.setStress(Stress.NIEDRIG);
		BENUTZER_BABA.setDoping(Doping.JA);
		BENUTZER_BABA.setRegenerationsfaehigkeit(Regenerationsfaehigkeit.SCHLECHT);
		BENUTZER_BABA.fuegeKoerpermessungHinzu(KOERPERMESSUNG_BABA);

		BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN.setPrimaerschluessel(BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN_ID);
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
		BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN.setUebung(WETTKAMPFBANKDRUECKEN);

		BELASTUNGSFAKTOREINTRAG_WETTKAMPFBANKDRUECKEN
			.setPrimaerschluessel(BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN.getPrimaerschluessel().getId().toString());
		BELASTUNGSFAKTOREINTRAG_WETTKAMPFBANKDRUECKEN.setBack(BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN.getBack());
		BELASTUNGSFAKTOREINTRAG_WETTKAMPFBANKDRUECKEN
			.setBenchpress(BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN.getBenchpress());
		BELASTUNGSFAKTOREINTRAG_WETTKAMPFBANKDRUECKEN.setBiceps(BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN.getBiceps());
		BELASTUNGSFAKTOREINTRAG_WETTKAMPFBANKDRUECKEN.setChest(BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN.getChest());
		BELASTUNGSFAKTOREINTRAG_WETTKAMPFBANKDRUECKEN.setCore(BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN.getCore());
		BELASTUNGSFAKTOREINTRAG_WETTKAMPFBANKDRUECKEN.setDeadlift(BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN.getDeadlift());
		BELASTUNGSFAKTOREINTRAG_WETTKAMPFBANKDRUECKEN.setGlutes(BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN.getGlutes());
		BELASTUNGSFAKTOREINTRAG_WETTKAMPFBANKDRUECKEN
			.setHamstrings(BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN.getHamstrings());
		BELASTUNGSFAKTOREINTRAG_WETTKAMPFBANKDRUECKEN.setQuads(BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN.getQuads());
		BELASTUNGSFAKTOREINTRAG_WETTKAMPFBANKDRUECKEN.setShoulder(BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN.getShoulder());
		BELASTUNGSFAKTOREINTRAG_WETTKAMPFBANKDRUECKEN.setSquat(BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN.getSquat());
		BELASTUNGSFAKTOREINTRAG_WETTKAMPFBANKDRUECKEN.setTriceps(BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN.getTriceps());

		WETTKAMPFBANKDRUECKEN.setPrimaerschluessel(WETTKAMPFBANKDRUECKEN_ID);
		WETTKAMPFBANKDRUECKEN.setName("Wettkampfbankdrücken (pausiert)");
		WETTKAMPFBANKDRUECKEN.setUebungsart(Uebungsart.GRUNDUEBUNG);
		WETTKAMPFBANKDRUECKEN.setUebungskategorie(Uebungskategorie.WETTKAMPF_BANKDRUECKEN);
		WETTKAMPFBANKDRUECKEN.setBelastungsfaktor(BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN);

		UEBUNGEINTRAG_WETTKAMPFBANKDRUECKEN
			.setPrimaerschluessel(WETTKAMPFBANKDRUECKEN.getPrimaerschluessel().getId().toString());
		UEBUNGEINTRAG_WETTKAMPFBANKDRUECKEN.setName(WETTKAMPFBANKDRUECKEN.getName());
		UEBUNGEINTRAG_WETTKAMPFBANKDRUECKEN.setUebungsart(WETTKAMPFBANKDRUECKEN.getUebungsart().name());
		UEBUNGEINTRAG_WETTKAMPFBANKDRUECKEN.setUebungskategorie(WETTKAMPFBANKDRUECKEN.getUebungskategorie().name());
		UEBUNGEINTRAG_WETTKAMPFBANKDRUECKEN.setBelastungsfaktor(BELASTUNGSFAKTOREINTRAG_WETTKAMPFBANKDRUECKEN);

		KRAFTWERT_WETTKAMPFBANKDRUECKEN.setPrimaerschluessel(KRAFTWERT_WETTKAMPFBANKDRUECKEN_ID);
		KRAFTWERT_WETTKAMPFBANKDRUECKEN.setUebung(WETTKAMPFBANKDRUECKEN);
		KRAFTWERT_WETTKAMPFBANKDRUECKEN.setMaximum(100);
		KRAFTWERT_WETTKAMPFBANKDRUECKEN.setKoerpergewicht(BENUTZER_JUSTIN.getAktuellesKoerpergewicht());
		KRAFTWERT_WETTKAMPFBANKDRUECKEN.setDatum(LocalDate.now());
		KRAFTWERT_WETTKAMPFBANKDRUECKEN.setWiederholungen(Wiederholungen.ONE_REP_MAX);
		KRAFTWERT_WETTKAMPFBANKDRUECKEN.setBenutzer(BENUTZER_JUSTIN);

		KRAFTWERTEINTRAG_WETTKAMPFBANKDRUECKEN
			.setPrimaerschluessel(KRAFTWERT_WETTKAMPFBANKDRUECKEN.getPrimaerschluessel().getId().toString());
		KRAFTWERTEINTRAG_WETTKAMPFBANKDRUECKEN.setMaximum(KRAFTWERT_WETTKAMPFBANKDRUECKEN.getMaximum());
		KRAFTWERTEINTRAG_WETTKAMPFBANKDRUECKEN.setKoerpergewicht(KRAFTWERT_WETTKAMPFBANKDRUECKEN.getKoerpergewicht());
		KRAFTWERTEINTRAG_WETTKAMPFBANKDRUECKEN
			.setDatum(KRAFTWERT_WETTKAMPFBANKDRUECKEN.getDatum().format(DateTimeFormatter.ofPattern(DATUMSFORMAT)));
		KRAFTWERTEINTRAG_WETTKAMPFBANKDRUECKEN
			.setWiederholungen(KRAFTWERT_WETTKAMPFBANKDRUECKEN.getWiederholungen().name());

		BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE.setPrimaerschluessel(BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE_ID);
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
		BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE.setUebung(LOWBAR_KNIEBEUGE);

		BELASTUNGSFAKTOREINTRAG_LOWBAR_KNIEBEUGE
			.setPrimaerschluessel(BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE.getPrimaerschluessel().getId().toString());
		BELASTUNGSFAKTOREINTRAG_LOWBAR_KNIEBEUGE.setBack(BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE.getBack());
		BELASTUNGSFAKTOREINTRAG_LOWBAR_KNIEBEUGE.setBenchpress(BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE.getBenchpress());
		BELASTUNGSFAKTOREINTRAG_LOWBAR_KNIEBEUGE.setBiceps(BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE.getBiceps());
		BELASTUNGSFAKTOREINTRAG_LOWBAR_KNIEBEUGE.setChest(BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE.getChest());
		BELASTUNGSFAKTOREINTRAG_LOWBAR_KNIEBEUGE.setCore(BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE.getCore());
		BELASTUNGSFAKTOREINTRAG_LOWBAR_KNIEBEUGE.setDeadlift(BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE.getDeadlift());
		BELASTUNGSFAKTOREINTRAG_LOWBAR_KNIEBEUGE.setGlutes(BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE.getGlutes());
		BELASTUNGSFAKTOREINTRAG_LOWBAR_KNIEBEUGE.setHamstrings(BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE.getHamstrings());
		BELASTUNGSFAKTOREINTRAG_LOWBAR_KNIEBEUGE.setQuads(BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE.getQuads());
		BELASTUNGSFAKTOREINTRAG_LOWBAR_KNIEBEUGE.setShoulder(BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE.getShoulder());
		BELASTUNGSFAKTOREINTRAG_LOWBAR_KNIEBEUGE.setSquat(BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE.getSquat());
		BELASTUNGSFAKTOREINTRAG_LOWBAR_KNIEBEUGE.setTriceps(BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE.getTriceps());

		LOWBAR_KNIEBEUGE.setPrimaerschluessel(LOWBAR_KNIEBEUGE_ID);
		LOWBAR_KNIEBEUGE.setName("Lowbar-Kniebeuge");
		LOWBAR_KNIEBEUGE.setUebungsart(Uebungsart.GRUNDUEBUNG);
		LOWBAR_KNIEBEUGE.setUebungskategorie(Uebungskategorie.WETTKAMPF_KNIEBEUGE);
		LOWBAR_KNIEBEUGE.setBelastungsfaktor(BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE);

		UEBUNGEINTRAG_LOWBAR_KNIEBEUGE.setPrimaerschluessel(LOWBAR_KNIEBEUGE.getPrimaerschluessel().getId().toString());
		UEBUNGEINTRAG_LOWBAR_KNIEBEUGE.setName(LOWBAR_KNIEBEUGE.getName());
		UEBUNGEINTRAG_LOWBAR_KNIEBEUGE.setUebungsart(LOWBAR_KNIEBEUGE.getUebungsart().name());
		UEBUNGEINTRAG_LOWBAR_KNIEBEUGE.setUebungskategorie(LOWBAR_KNIEBEUGE.getUebungskategorie().name());
		UEBUNGEINTRAG_LOWBAR_KNIEBEUGE.setBelastungsfaktor(BELASTUNGSFAKTOREINTRAG_LOWBAR_KNIEBEUGE);

		KRAFTWERT_LOWBAR_KNIEBEUGE.setPrimaerschluessel(KRAFTWERT_LOWBAR_KNIEBEUGE_ID);
		KRAFTWERT_LOWBAR_KNIEBEUGE.setUebung(LOWBAR_KNIEBEUGE);
		KRAFTWERT_LOWBAR_KNIEBEUGE.setMaximum(150);
		KRAFTWERT_LOWBAR_KNIEBEUGE.setKoerpergewicht(BENUTZER_JUSTIN.getAktuellesKoerpergewicht());
		KRAFTWERT_LOWBAR_KNIEBEUGE.setDatum(LocalDate.now());
		KRAFTWERT_LOWBAR_KNIEBEUGE.setWiederholungen(Wiederholungen.ONE_REP_MAX);
		KRAFTWERT_LOWBAR_KNIEBEUGE.setBenutzer(BENUTZER_JUSTIN);

		KRAFTWERTEINTRAG_LOWBAR_KNIEBEUGE
			.setPrimaerschluessel(KRAFTWERT_LOWBAR_KNIEBEUGE.getPrimaerschluessel().getId().toString());
		KRAFTWERTEINTRAG_LOWBAR_KNIEBEUGE.setMaximum(KRAFTWERT_LOWBAR_KNIEBEUGE.getMaximum());
		KRAFTWERTEINTRAG_LOWBAR_KNIEBEUGE.setKoerpergewicht(KRAFTWERT_LOWBAR_KNIEBEUGE.getKoerpergewicht());
		KRAFTWERTEINTRAG_LOWBAR_KNIEBEUGE
			.setDatum(KRAFTWERT_LOWBAR_KNIEBEUGE.getDatum().format(DateTimeFormatter.ofPattern(DATUMSFORMAT)));
		KRAFTWERTEINTRAG_LOWBAR_KNIEBEUGE.setWiederholungen(KRAFTWERT_LOWBAR_KNIEBEUGE.getWiederholungen().name());

		BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN
			.setPrimaerschluessel(BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN_ID);
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
		BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN.setUebung(KONVENTIONELLES_KREUZHEBEN);

		BELASTUNGSFAKTOREINTRAG_KONVENTIONELLES_KREUZHEBEN
			.setPrimaerschluessel(
				BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN.getPrimaerschluessel().getId().toString());
		BELASTUNGSFAKTOREINTRAG_KONVENTIONELLES_KREUZHEBEN
			.setBack(BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN.getBack());
		BELASTUNGSFAKTOREINTRAG_KONVENTIONELLES_KREUZHEBEN
			.setBenchpress(BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN.getBenchpress());
		BELASTUNGSFAKTOREINTRAG_KONVENTIONELLES_KREUZHEBEN
			.setBiceps(BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN.getBiceps());
		BELASTUNGSFAKTOREINTRAG_KONVENTIONELLES_KREUZHEBEN
			.setChest(BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN.getChest());
		BELASTUNGSFAKTOREINTRAG_KONVENTIONELLES_KREUZHEBEN
			.setCore(BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN.getCore());
		BELASTUNGSFAKTOREINTRAG_KONVENTIONELLES_KREUZHEBEN
			.setDeadlift(BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN.getDeadlift());
		BELASTUNGSFAKTOREINTRAG_KONVENTIONELLES_KREUZHEBEN
			.setGlutes(BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN.getGlutes());
		BELASTUNGSFAKTOREINTRAG_KONVENTIONELLES_KREUZHEBEN
			.setHamstrings(BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN.getHamstrings());
		BELASTUNGSFAKTOREINTRAG_KONVENTIONELLES_KREUZHEBEN
			.setQuads(BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN.getQuads());
		BELASTUNGSFAKTOREINTRAG_KONVENTIONELLES_KREUZHEBEN
			.setShoulder(BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN.getShoulder());
		BELASTUNGSFAKTOREINTRAG_KONVENTIONELLES_KREUZHEBEN
			.setSquat(BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN.getSquat());
		BELASTUNGSFAKTOREINTRAG_KONVENTIONELLES_KREUZHEBEN
			.setTriceps(BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN.getTriceps());

		KONVENTIONELLES_KREUZHEBEN.setPrimaerschluessel(KONVENTIONELLES_KREUZHEBEN_ID);
		KONVENTIONELLES_KREUZHEBEN.setName("Konventionelles Kreuzheben");
		KONVENTIONELLES_KREUZHEBEN.setUebungsart(Uebungsart.GRUNDUEBUNG);
		KONVENTIONELLES_KREUZHEBEN.setUebungskategorie(Uebungskategorie.WETTKAMPF_KREUZHEBEN);
		KONVENTIONELLES_KREUZHEBEN.setBelastungsfaktor(BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN);

		UEBUNGEINTRAG_KONVENTIONELLES_KREUZHEBEN
			.setPrimaerschluessel(KONVENTIONELLES_KREUZHEBEN.getPrimaerschluessel().getId().toString());
		UEBUNGEINTRAG_KONVENTIONELLES_KREUZHEBEN.setName(KONVENTIONELLES_KREUZHEBEN.getName());
		UEBUNGEINTRAG_KONVENTIONELLES_KREUZHEBEN.setUebungsart(KONVENTIONELLES_KREUZHEBEN.getUebungsart().name());
		UEBUNGEINTRAG_KONVENTIONELLES_KREUZHEBEN
			.setUebungskategorie(KONVENTIONELLES_KREUZHEBEN.getUebungskategorie().name());
		UEBUNGEINTRAG_KONVENTIONELLES_KREUZHEBEN
			.setBelastungsfaktor(BELASTUNGSFAKTOREINTRAG_KONVENTIONELLES_KREUZHEBEN);

		KRAFTWERT_KONVENTIONELLES_KREUZHEBEN.setPrimaerschluessel(KRAFTWERT_KONVENTIONELLES_KREUZHEBEN_ID);
		KRAFTWERT_KONVENTIONELLES_KREUZHEBEN.setUebung(KONVENTIONELLES_KREUZHEBEN);
		KRAFTWERT_KONVENTIONELLES_KREUZHEBEN.setMaximum(200);
		KRAFTWERT_KONVENTIONELLES_KREUZHEBEN.setKoerpergewicht(BENUTZER_JUSTIN.getAktuellesKoerpergewicht());
		KRAFTWERT_KONVENTIONELLES_KREUZHEBEN.setDatum(LocalDate.now());
		KRAFTWERT_KONVENTIONELLES_KREUZHEBEN.setWiederholungen(Wiederholungen.ONE_REP_MAX);
		KRAFTWERT_KONVENTIONELLES_KREUZHEBEN.setBenutzer(BENUTZER_JUSTIN);

		KRAFTWERTEINTRAG_KONVENTIONELLES_KREUZHEBEN
			.setPrimaerschluessel(KRAFTWERT_KONVENTIONELLES_KREUZHEBEN.getPrimaerschluessel().getId().toString());
		KRAFTWERTEINTRAG_KONVENTIONELLES_KREUZHEBEN.setMaximum(KRAFTWERT_KONVENTIONELLES_KREUZHEBEN.getMaximum());
		KRAFTWERTEINTRAG_KONVENTIONELLES_KREUZHEBEN
			.setKoerpergewicht(KRAFTWERT_KONVENTIONELLES_KREUZHEBEN.getKoerpergewicht());
		KRAFTWERTEINTRAG_KONVENTIONELLES_KREUZHEBEN.setDatum(
			KRAFTWERT_KONVENTIONELLES_KREUZHEBEN.getDatum().format(DateTimeFormatter.ofPattern(DATUMSFORMAT)));
		KRAFTWERTEINTRAG_KONVENTIONELLES_KREUZHEBEN
			.setWiederholungen(KRAFTWERT_KONVENTIONELLES_KREUZHEBEN.getWiederholungen().name());
	}
}
