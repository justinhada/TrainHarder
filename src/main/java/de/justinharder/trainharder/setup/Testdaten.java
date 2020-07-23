package de.justinharder.trainharder.setup;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import de.justinharder.trainharder.model.domain.Authentifizierung;
import de.justinharder.trainharder.model.domain.Belastungsfaktor;
import de.justinharder.trainharder.model.domain.Benutzer;
import de.justinharder.trainharder.model.domain.Koerpermessung;
import de.justinharder.trainharder.model.domain.Kraftwert;
import de.justinharder.trainharder.model.domain.Uebung;
import de.justinharder.trainharder.model.domain.embeddables.Benutzerangabe;
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

	public static final AuthentifizierungDto AUTHENTIFIZIERUNG_DTO_JUSTIN = new AuthentifizierungDto();
	public static final AuthentifizierungDto AUTHENTIFIZIERUNG_DTO_EDUARD = new AuthentifizierungDto();
	public static final AuthentifizierungDto AUTHENTIFIZIERUNG_DTO_ANETTE = new AuthentifizierungDto();
	public static final AuthentifizierungDto AUTHENTIFIZIERUNG_DTO_GOTT = new AuthentifizierungDto();

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

	public static final Benutzerangabe BENUTZERANGABE_JUSTIN = new Benutzerangabe();
	public static final Benutzerangabe BENUTZERANGABE_EDUARD = new Benutzerangabe();
	public static final Benutzerangabe BENUTZERANGABE_ANNA = new Benutzerangabe();
	public static final Benutzerangabe BENUTZERANGABE_ANETTE = new Benutzerangabe();
	public static final Benutzerangabe BENUTZERANGABE_GOTT = new Benutzerangabe();
	public static final Benutzerangabe BENUTZERANGABE_BABA = new Benutzerangabe();

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

	public static final BenutzerDto BENUTZER_DTO_JUSTIN = new BenutzerDto();
	public static final BenutzerDto BENUTZER_DTO_ANETTE = new BenutzerDto();
	public static final BenutzerDto BENUTZER_DTO_GOTT = new BenutzerDto();

	public static final Primaerschluessel BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN_ID = new Primaerschluessel();
	public static final Primaerschluessel BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE_ID = new Primaerschluessel();
	public static final Primaerschluessel BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN_ID = new Primaerschluessel();

	public static final Belastungsfaktor BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN = new Belastungsfaktor();
	public static final Belastungsfaktor BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE = new Belastungsfaktor();
	public static final Belastungsfaktor BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN = new Belastungsfaktor();

	public static final BelastungsfaktorDto BELASTUNGSFAKTOR_DTO_WETTKAMPFBANKDRUECKEN = new BelastungsfaktorDto();
	public static final BelastungsfaktorDto BELASTUNGSFAKTOR_DTO_LOWBAR_KNIEBEUGE = new BelastungsfaktorDto();
	public static final BelastungsfaktorDto BELASTUNGSFAKTOR_DTO_KONVENTIONELLES_KREUZHEBEN = new BelastungsfaktorDto();

	public static final Primaerschluessel WETTKAMPFBANKDRUECKEN_ID = new Primaerschluessel();
	public static final Primaerschluessel LOWBAR_KNIEBEUGE_ID = new Primaerschluessel();
	public static final Primaerschluessel KONVENTIONELLES_KREUZHEBEN_ID = new Primaerschluessel();

	public static final Uebung WETTKAMPFBANKDRUECKEN = new Uebung();
	public static final Uebung LOWBAR_KNIEBEUGE = new Uebung();
	public static final Uebung KONVENTIONELLES_KREUZHEBEN = new Uebung();

	public static final UebungDto UEBUNG_DTO_WETTKAMPFBANKDRUECKEN = new UebungDto();
	public static final UebungDto UEBUNG_DTO_LOWBAR_KNIEBEUGE = new UebungDto();
	public static final UebungDto UEBUNG_DTO_KONVENTIONELLES_KREUZHEBEN = new UebungDto();

	public static final Primaerschluessel KRAFTWERT_WETTKAMPFBANKDRUECKEN_ID = new Primaerschluessel();
	public static final Primaerschluessel KRAFTWERT_LOWBAR_KNIEBEUGE_ID = new Primaerschluessel();
	public static final Primaerschluessel KRAFTWERT_KONVENTIONELLES_KREUZHEBEN_ID = new Primaerschluessel();

	public static final Kraftwert KRAFTWERT_WETTKAMPFBANKDRUECKEN = new Kraftwert();
	public static final Kraftwert KRAFTWERT_LOWBAR_KNIEBEUGE = new Kraftwert();
	public static final Kraftwert KRAFTWERT_KONVENTIONELLES_KREUZHEBEN = new Kraftwert();

	public static final KraftwertDto KRAFTWERT_DTO_WETTKAMPFBANKDRUECKEN = new KraftwertDto();
	public static final KraftwertDto KRAFTWERT_DTO_LOWBAR_KNIEBEUGE = new KraftwertDto();
	public static final KraftwertDto KRAFTWERT_DTO_KONVENTIONELLES_KREUZHEBEN = new KraftwertDto();

	static
	{
		AUTHENTIFIZIERUNG_JUSTIN.setPrimaerschluessel(AUTHENTIFIZIERUNG_JUSTIN_ID);
		AUTHENTIFIZIERUNG_JUSTIN.setMail("mail@justinharder.de");
		AUTHENTIFIZIERUNG_JUSTIN.setBenutzername("harder");
		AUTHENTIFIZIERUNG_JUSTIN.setPasswort("JustinHarder#98");
		AUTHENTIFIZIERUNG_JUSTIN.setAktiv(false);
		AUTHENTIFIZIERUNG_JUSTIN.setResetUuid(UUID.randomUUID());
		AUTHENTIFIZIERUNG_JUSTIN.setBenutzer(BENUTZER_JUSTIN);

		AUTHENTIFIZIERUNG_EDUARD.setPrimaerschluessel(AUTHENTIFIZIERUNG_EDUARD_ID);
		AUTHENTIFIZIERUNG_EDUARD.setMail("mail@eduard.de");
		AUTHENTIFIZIERUNG_EDUARD.setBenutzername("eduard");
		AUTHENTIFIZIERUNG_EDUARD.setPasswort("EduardEduardEduard_98");
		AUTHENTIFIZIERUNG_EDUARD.setAktiv(true);
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

		AUTHENTIFIZIERUNG_DTO_JUSTIN
			.setPrimaerschluessel(AUTHENTIFIZIERUNG_JUSTIN.getPrimaerschluessel().getId().toString());
		AUTHENTIFIZIERUNG_DTO_JUSTIN.setMail(AUTHENTIFIZIERUNG_JUSTIN.getMail());
		AUTHENTIFIZIERUNG_DTO_JUSTIN.setBenutzername(AUTHENTIFIZIERUNG_JUSTIN.getBenutzername());
		AUTHENTIFIZIERUNG_DTO_JUSTIN.setPasswort(AUTHENTIFIZIERUNG_JUSTIN.getPasswort());

		AUTHENTIFIZIERUNG_DTO_EDUARD
			.setPrimaerschluessel(AUTHENTIFIZIERUNG_EDUARD.getPrimaerschluessel().getId().toString());
		AUTHENTIFIZIERUNG_DTO_EDUARD.setMail(AUTHENTIFIZIERUNG_EDUARD.getMail());
		AUTHENTIFIZIERUNG_DTO_EDUARD.setBenutzername(AUTHENTIFIZIERUNG_EDUARD.getBenutzername());
		AUTHENTIFIZIERUNG_DTO_EDUARD.setPasswort(AUTHENTIFIZIERUNG_EDUARD.getPasswort());

		AUTHENTIFIZIERUNG_DTO_ANETTE
			.setPrimaerschluessel(AUTHENTIFIZIERUNG_ANETTE.getPrimaerschluessel().getId().toString());
		AUTHENTIFIZIERUNG_DTO_ANETTE.setMail(AUTHENTIFIZIERUNG_ANETTE.getMail());
		AUTHENTIFIZIERUNG_DTO_ANETTE.setBenutzername(AUTHENTIFIZIERUNG_ANETTE.getBenutzername());
		AUTHENTIFIZIERUNG_DTO_ANETTE.setPasswort(AUTHENTIFIZIERUNG_ANETTE.getPasswort());

		AUTHENTIFIZIERUNG_DTO_GOTT
			.setPrimaerschluessel(AUTHENTIFIZIERUNG_GOTT.getPrimaerschluessel().getId().toString());
		AUTHENTIFIZIERUNG_DTO_GOTT.setMail(AUTHENTIFIZIERUNG_GOTT.getMail());
		AUTHENTIFIZIERUNG_DTO_GOTT.setBenutzername(AUTHENTIFIZIERUNG_GOTT.getBenutzername());
		AUTHENTIFIZIERUNG_DTO_GOTT.setPasswort(AUTHENTIFIZIERUNG_GOTT.getPasswort());

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

		BENUTZERANGABE_JUSTIN.setKraftlevel(Kraftlevel.CLASS_5);
		BENUTZERANGABE_JUSTIN.setGeschlecht(Geschlecht.MAENNLICH);
		BENUTZERANGABE_JUSTIN.setErfahrung(Erfahrung.BEGINNER);
		BENUTZERANGABE_JUSTIN.setErnaehrung(Ernaehrung.GUT);
		BENUTZERANGABE_JUSTIN.setSchlafqualitaet(Schlafqualitaet.GUT);
		BENUTZERANGABE_JUSTIN.setStress(Stress.MITTELMAESSIG);
		BENUTZERANGABE_JUSTIN.setDoping(Doping.NEIN);
		BENUTZERANGABE_JUSTIN.setRegenerationsfaehigkeit(Regenerationsfaehigkeit.GUT);

		BENUTZER_JUSTIN.setPrimaerschluessel(BENUTZER_JUSTIN_ID);
		BENUTZER_JUSTIN.setName(new Name("Justin", "Harder"));
		BENUTZER_JUSTIN.setLebensalter(21);
		BENUTZER_JUSTIN.setBenutzerangabe(BENUTZERANGABE_JUSTIN);
		BENUTZER_JUSTIN.setAuthentifizierung(AUTHENTIFIZIERUNG_JUSTIN);
		BENUTZER_JUSTIN.fuegeKoerpermessungHinzu(KOERPERMESSUNG_JUSTIN);

		BENUTZER_DTO_JUSTIN.setPrimaerschluessel(BENUTZER_JUSTIN.getPrimaerschluessel().getId().toString());
		BENUTZER_DTO_JUSTIN.setVorname(BENUTZER_JUSTIN.getName().getVorname());
		BENUTZER_DTO_JUSTIN.setNachname(BENUTZER_JUSTIN.getName().getNachname());
		BENUTZER_DTO_JUSTIN.setLebensalter(BENUTZER_JUSTIN.getLebensalter());
		BENUTZER_DTO_JUSTIN.setKraftlevel(BENUTZER_JUSTIN.getBenutzerangabe().getKraftlevel().name());
		BENUTZER_DTO_JUSTIN.setGeschlecht(BENUTZER_JUSTIN.getBenutzerangabe().getGeschlecht().name());
		BENUTZER_DTO_JUSTIN.setErfahrung(BENUTZER_JUSTIN.getBenutzerangabe().getErfahrung().name());
		BENUTZER_DTO_JUSTIN.setErnaehrung(BENUTZER_JUSTIN.getBenutzerangabe().getErnaehrung().name());
		BENUTZER_DTO_JUSTIN.setSchlafqualitaet(BENUTZER_JUSTIN.getBenutzerangabe().getSchlafqualitaet().name());
		BENUTZER_DTO_JUSTIN.setStress(BENUTZER_JUSTIN.getBenutzerangabe().getStress().name());
		BENUTZER_DTO_JUSTIN.setDoping(BENUTZER_JUSTIN.getBenutzerangabe().getDoping().name());
		BENUTZER_DTO_JUSTIN
			.setRegenerationsfaehigkeit(BENUTZER_JUSTIN.getBenutzerangabe().getRegenerationsfaehigkeit().name());
		BENUTZER_DTO_JUSTIN.setAuthentifizierung(AUTHENTIFIZIERUNG_DTO_JUSTIN);

		BENUTZERANGABE_EDUARD.setKraftlevel(Kraftlevel.CLASS_4);
		BENUTZERANGABE_EDUARD.setGeschlecht(Geschlecht.MAENNLICH);
		BENUTZERANGABE_EDUARD.setErfahrung(Erfahrung.FORTGESCHRITTEN);
		BENUTZERANGABE_EDUARD.setErnaehrung(Ernaehrung.SCHLECHT);
		BENUTZERANGABE_EDUARD.setSchlafqualitaet(Schlafqualitaet.SCHLECHT);
		BENUTZERANGABE_EDUARD.setStress(Stress.NIEDRIG);
		BENUTZERANGABE_EDUARD.setDoping(Doping.JA);
		BENUTZERANGABE_EDUARD.setRegenerationsfaehigkeit(Regenerationsfaehigkeit.PERFEKT);

		BENUTZER_EDUARD.setPrimaerschluessel(BENUTZER_EDUARD_ID);
		BENUTZER_EDUARD.setName(new Name("Eduard", "Stremel"));
		BENUTZER_EDUARD.setLebensalter(14);
		BENUTZER_EDUARD.setBenutzerangabe(BENUTZERANGABE_EDUARD);
		BENUTZER_EDUARD.setAuthentifizierung(AUTHENTIFIZIERUNG_EDUARD);
		BENUTZER_EDUARD.fuegeKoerpermessungHinzu(KOERPERMESSUNG_EDUARD);

		BENUTZERANGABE_ANNA.setKraftlevel(Kraftlevel.CLASS_2);
		BENUTZERANGABE_ANNA.setGeschlecht(Geschlecht.WEIBLICH);
		BENUTZERANGABE_ANNA.setErfahrung(Erfahrung.FORTGESCHRITTEN);
		BENUTZERANGABE_ANNA.setErnaehrung(Ernaehrung.SCHLECHT);
		BENUTZERANGABE_ANNA.setSchlafqualitaet(Schlafqualitaet.SCHLECHT);
		BENUTZERANGABE_ANNA.setStress(Stress.NIEDRIG);
		BENUTZERANGABE_ANNA.setDoping(Doping.JA);
		BENUTZERANGABE_ANNA.setRegenerationsfaehigkeit(Regenerationsfaehigkeit.PERFEKT);

		BENUTZER_ANNA.setPrimaerschluessel(BENUTZER_ANNA_ID);
		BENUTZER_ANNA.setName(new Name("Anna", "Aufbau"));
		BENUTZER_ANNA.setLebensalter(14);
		BENUTZER_ANNA.setBenutzerangabe(BENUTZERANGABE_ANNA);
		BENUTZER_ANNA.fuegeKoerpermessungHinzu(KOERPERMESSUNG_ANNA);

		BENUTZERANGABE_ANETTE.setKraftlevel(Kraftlevel.CLASS_3);
		BENUTZERANGABE_ANETTE.setGeschlecht(Geschlecht.WEIBLICH);
		BENUTZERANGABE_ANETTE.setErfahrung(Erfahrung.EXPERTE);
		BENUTZERANGABE_ANETTE.setErnaehrung(Ernaehrung.GUT);
		BENUTZERANGABE_ANETTE.setSchlafqualitaet(Schlafqualitaet.DURCHSCHNITT);
		BENUTZERANGABE_ANETTE.setStress(Stress.HOCH);
		BENUTZERANGABE_ANETTE.setDoping(Doping.NEIN);
		BENUTZERANGABE_ANETTE.setRegenerationsfaehigkeit(Regenerationsfaehigkeit.UNTERDURCHSCHNITTLICH);

		BENUTZER_ANETTE.setPrimaerschluessel(BENUTZER_ANETTE_ID);
		BENUTZER_ANETTE.setName(new Name("Anette", "Masseschwein"));
		BENUTZER_ANETTE.setLebensalter(43);
		BENUTZER_ANETTE.setBenutzerangabe(BENUTZERANGABE_ANETTE);
		BENUTZER_ANETTE.setAuthentifizierung(AUTHENTIFIZIERUNG_ANETTE);
		BENUTZER_ANETTE.fuegeKoerpermessungHinzu(KOERPERMESSUNG_ANETTE);

		BENUTZER_DTO_ANETTE.setPrimaerschluessel(BENUTZER_ANETTE.getPrimaerschluessel().getId().toString());
		BENUTZER_DTO_ANETTE.setVorname("Anette");
		BENUTZER_DTO_ANETTE.setNachname("Masseschwein");
		BENUTZER_DTO_ANETTE.setLebensalter(43);
		BENUTZER_DTO_ANETTE.setKraftlevel(Kraftlevel.CLASS_3.name());
		BENUTZER_DTO_ANETTE.setGeschlecht(Geschlecht.WEIBLICH.name());
		BENUTZER_DTO_ANETTE.setErfahrung(Erfahrung.EXPERTE.name());
		BENUTZER_DTO_ANETTE.setErnaehrung(Ernaehrung.GUT.name());
		BENUTZER_DTO_ANETTE.setSchlafqualitaet(Schlafqualitaet.DURCHSCHNITT.name());
		BENUTZER_DTO_ANETTE.setStress(Stress.HOCH.name());
		BENUTZER_DTO_ANETTE.setDoping(Doping.NEIN.name());
		BENUTZER_DTO_ANETTE.setRegenerationsfaehigkeit(Regenerationsfaehigkeit.UNTERDURCHSCHNITTLICH.name());
		BENUTZER_DTO_ANETTE.setAuthentifizierung(AUTHENTIFIZIERUNG_DTO_ANETTE);

		BENUTZERANGABE_GOTT.setKraftlevel(Kraftlevel.ELITE);
		BENUTZERANGABE_GOTT.setGeschlecht(Geschlecht.MAENNLICH);
		BENUTZERANGABE_GOTT.setErfahrung(Erfahrung.EXPERTE);
		BENUTZERANGABE_GOTT.setErnaehrung(Ernaehrung.GUT);
		BENUTZERANGABE_GOTT.setSchlafqualitaet(Schlafqualitaet.GUT);
		BENUTZERANGABE_GOTT.setStress(Stress.NIEDRIG);
		BENUTZERANGABE_GOTT.setDoping(Doping.JA);
		BENUTZERANGABE_GOTT.setRegenerationsfaehigkeit(Regenerationsfaehigkeit.PERFEKT);

		BENUTZER_GOTT.setPrimaerschluessel(BENUTZER_GOTT_ID);
		BENUTZER_GOTT.setName(new Name("Gott", "Harder"));
		BENUTZER_GOTT.setLebensalter(32);
		BENUTZER_GOTT.setBenutzerangabe(BENUTZERANGABE_GOTT);
		BENUTZER_GOTT.setAuthentifizierung(AUTHENTIFIZIERUNG_GOTT);
		BENUTZER_GOTT.fuegeKoerpermessungHinzu(KOERPERMESSUNG_GOTT);

		BENUTZER_DTO_GOTT.setPrimaerschluessel(BENUTZER_GOTT.getPrimaerschluessel().getId().toString());
		BENUTZER_DTO_GOTT.setVorname("Gott");
		BENUTZER_DTO_GOTT.setNachname("Harder");
		BENUTZER_DTO_GOTT.setLebensalter(32);
		BENUTZER_DTO_GOTT.setKraftlevel(Kraftlevel.ELITE.name());
		BENUTZER_DTO_GOTT.setGeschlecht(Geschlecht.MAENNLICH.name());
		BENUTZER_DTO_GOTT.setErfahrung(Erfahrung.EXPERTE.name());
		BENUTZER_DTO_GOTT.setErnaehrung(Ernaehrung.GUT.name());
		BENUTZER_DTO_GOTT.setSchlafqualitaet(Schlafqualitaet.GUT.name());
		BENUTZER_DTO_GOTT.setStress(Stress.NIEDRIG.name());
		BENUTZER_DTO_GOTT.setDoping(Doping.JA.name());
		BENUTZER_DTO_GOTT.setRegenerationsfaehigkeit(Regenerationsfaehigkeit.PERFEKT.name());
		BENUTZER_DTO_GOTT.setAuthentifizierung(AUTHENTIFIZIERUNG_DTO_GOTT);

		BENUTZERANGABE_BABA.setKraftlevel(Kraftlevel.CLASS_1);
		BENUTZERANGABE_BABA.setGeschlecht(Geschlecht.MAENNLICH);
		BENUTZERANGABE_BABA.setErfahrung(Erfahrung.SEHR_FORTGESCHRITTEN);
		BENUTZERANGABE_BABA.setErnaehrung(Ernaehrung.GUT);
		BENUTZERANGABE_BABA.setSchlafqualitaet(Schlafqualitaet.GUT);
		BENUTZERANGABE_BABA.setStress(Stress.NIEDRIG);
		BENUTZERANGABE_BABA.setDoping(Doping.JA);
		BENUTZERANGABE_BABA.setRegenerationsfaehigkeit(Regenerationsfaehigkeit.SCHLECHT);

		BENUTZER_BABA.setPrimaerschluessel(BENUTZER_BABA_ID);
		BENUTZER_BABA.setName(new Name("Baba", "von Gewichten"));
		BENUTZER_BABA.setLebensalter(55);
		BENUTZER_BABA.setBenutzerangabe(BENUTZERANGABE_BABA);
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

		BELASTUNGSFAKTOR_DTO_WETTKAMPFBANKDRUECKEN
			.setPrimaerschluessel(BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN.getPrimaerschluessel().getId().toString());
		BELASTUNGSFAKTOR_DTO_WETTKAMPFBANKDRUECKEN.setBack(BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN.getBack());
		BELASTUNGSFAKTOR_DTO_WETTKAMPFBANKDRUECKEN
			.setBenchpress(BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN.getBenchpress());
		BELASTUNGSFAKTOR_DTO_WETTKAMPFBANKDRUECKEN.setBiceps(BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN.getBiceps());
		BELASTUNGSFAKTOR_DTO_WETTKAMPFBANKDRUECKEN.setChest(BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN.getChest());
		BELASTUNGSFAKTOR_DTO_WETTKAMPFBANKDRUECKEN.setCore(BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN.getCore());
		BELASTUNGSFAKTOR_DTO_WETTKAMPFBANKDRUECKEN.setDeadlift(BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN.getDeadlift());
		BELASTUNGSFAKTOR_DTO_WETTKAMPFBANKDRUECKEN.setGlutes(BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN.getGlutes());
		BELASTUNGSFAKTOR_DTO_WETTKAMPFBANKDRUECKEN
			.setHamstrings(BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN.getHamstrings());
		BELASTUNGSFAKTOR_DTO_WETTKAMPFBANKDRUECKEN.setQuads(BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN.getQuads());
		BELASTUNGSFAKTOR_DTO_WETTKAMPFBANKDRUECKEN.setShoulder(BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN.getShoulder());
		BELASTUNGSFAKTOR_DTO_WETTKAMPFBANKDRUECKEN.setSquat(BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN.getSquat());
		BELASTUNGSFAKTOR_DTO_WETTKAMPFBANKDRUECKEN.setTriceps(BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN.getTriceps());

		WETTKAMPFBANKDRUECKEN.setPrimaerschluessel(WETTKAMPFBANKDRUECKEN_ID);
		WETTKAMPFBANKDRUECKEN.setName("Wettkampfbankdrücken (pausiert)");
		WETTKAMPFBANKDRUECKEN.setUebungsart(Uebungsart.GRUNDUEBUNG);
		WETTKAMPFBANKDRUECKEN.setUebungskategorie(Uebungskategorie.WETTKAMPF_BANKDRUECKEN);
		WETTKAMPFBANKDRUECKEN.setBelastungsfaktor(BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN);

		UEBUNG_DTO_WETTKAMPFBANKDRUECKEN
			.setPrimaerschluessel(WETTKAMPFBANKDRUECKEN.getPrimaerschluessel().getId().toString());
		UEBUNG_DTO_WETTKAMPFBANKDRUECKEN.setName(WETTKAMPFBANKDRUECKEN.getName());
		UEBUNG_DTO_WETTKAMPFBANKDRUECKEN.setUebungsart(WETTKAMPFBANKDRUECKEN.getUebungsart().name());
		UEBUNG_DTO_WETTKAMPFBANKDRUECKEN.setUebungskategorie(WETTKAMPFBANKDRUECKEN.getUebungskategorie().name());
		UEBUNG_DTO_WETTKAMPFBANKDRUECKEN.setBelastungsfaktor(BELASTUNGSFAKTOR_DTO_WETTKAMPFBANKDRUECKEN);

		KRAFTWERT_WETTKAMPFBANKDRUECKEN.setPrimaerschluessel(KRAFTWERT_WETTKAMPFBANKDRUECKEN_ID);
		KRAFTWERT_WETTKAMPFBANKDRUECKEN.setUebung(WETTKAMPFBANKDRUECKEN);
		KRAFTWERT_WETTKAMPFBANKDRUECKEN.setMaximum(100);
		KRAFTWERT_WETTKAMPFBANKDRUECKEN.setKoerpergewicht(BENUTZER_JUSTIN.getAktuellesKoerpergewicht());
		KRAFTWERT_WETTKAMPFBANKDRUECKEN.setDatum(LocalDate.now());
		KRAFTWERT_WETTKAMPFBANKDRUECKEN.setWiederholungen(Wiederholungen.ONE_REP_MAX);
		KRAFTWERT_WETTKAMPFBANKDRUECKEN.setBenutzer(BENUTZER_JUSTIN);

		KRAFTWERT_DTO_WETTKAMPFBANKDRUECKEN
			.setPrimaerschluessel(KRAFTWERT_WETTKAMPFBANKDRUECKEN.getPrimaerschluessel().getId().toString());
		KRAFTWERT_DTO_WETTKAMPFBANKDRUECKEN.setMaximum(KRAFTWERT_WETTKAMPFBANKDRUECKEN.getMaximum());
		KRAFTWERT_DTO_WETTKAMPFBANKDRUECKEN.setKoerpergewicht(KRAFTWERT_WETTKAMPFBANKDRUECKEN.getKoerpergewicht());
		KRAFTWERT_DTO_WETTKAMPFBANKDRUECKEN
			.setDatum(KRAFTWERT_WETTKAMPFBANKDRUECKEN.getDatum().format(DateTimeFormatter.ofPattern(DATUMSFORMAT)));
		KRAFTWERT_DTO_WETTKAMPFBANKDRUECKEN
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

		BELASTUNGSFAKTOR_DTO_LOWBAR_KNIEBEUGE
			.setPrimaerschluessel(BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE.getPrimaerschluessel().getId().toString());
		BELASTUNGSFAKTOR_DTO_LOWBAR_KNIEBEUGE.setBack(BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE.getBack());
		BELASTUNGSFAKTOR_DTO_LOWBAR_KNIEBEUGE.setBenchpress(BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE.getBenchpress());
		BELASTUNGSFAKTOR_DTO_LOWBAR_KNIEBEUGE.setBiceps(BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE.getBiceps());
		BELASTUNGSFAKTOR_DTO_LOWBAR_KNIEBEUGE.setChest(BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE.getChest());
		BELASTUNGSFAKTOR_DTO_LOWBAR_KNIEBEUGE.setCore(BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE.getCore());
		BELASTUNGSFAKTOR_DTO_LOWBAR_KNIEBEUGE.setDeadlift(BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE.getDeadlift());
		BELASTUNGSFAKTOR_DTO_LOWBAR_KNIEBEUGE.setGlutes(BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE.getGlutes());
		BELASTUNGSFAKTOR_DTO_LOWBAR_KNIEBEUGE.setHamstrings(BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE.getHamstrings());
		BELASTUNGSFAKTOR_DTO_LOWBAR_KNIEBEUGE.setQuads(BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE.getQuads());
		BELASTUNGSFAKTOR_DTO_LOWBAR_KNIEBEUGE.setShoulder(BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE.getShoulder());
		BELASTUNGSFAKTOR_DTO_LOWBAR_KNIEBEUGE.setSquat(BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE.getSquat());
		BELASTUNGSFAKTOR_DTO_LOWBAR_KNIEBEUGE.setTriceps(BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE.getTriceps());

		LOWBAR_KNIEBEUGE.setPrimaerschluessel(LOWBAR_KNIEBEUGE_ID);
		LOWBAR_KNIEBEUGE.setName("Lowbar-Kniebeuge");
		LOWBAR_KNIEBEUGE.setUebungsart(Uebungsart.GRUNDUEBUNG);
		LOWBAR_KNIEBEUGE.setUebungskategorie(Uebungskategorie.WETTKAMPF_KNIEBEUGE);
		LOWBAR_KNIEBEUGE.setBelastungsfaktor(BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE);

		UEBUNG_DTO_LOWBAR_KNIEBEUGE.setPrimaerschluessel(LOWBAR_KNIEBEUGE.getPrimaerschluessel().getId().toString());
		UEBUNG_DTO_LOWBAR_KNIEBEUGE.setName(LOWBAR_KNIEBEUGE.getName());
		UEBUNG_DTO_LOWBAR_KNIEBEUGE.setUebungsart(LOWBAR_KNIEBEUGE.getUebungsart().name());
		UEBUNG_DTO_LOWBAR_KNIEBEUGE.setUebungskategorie(LOWBAR_KNIEBEUGE.getUebungskategorie().name());
		UEBUNG_DTO_LOWBAR_KNIEBEUGE.setBelastungsfaktor(BELASTUNGSFAKTOR_DTO_LOWBAR_KNIEBEUGE);

		KRAFTWERT_LOWBAR_KNIEBEUGE.setPrimaerschluessel(KRAFTWERT_LOWBAR_KNIEBEUGE_ID);
		KRAFTWERT_LOWBAR_KNIEBEUGE.setUebung(LOWBAR_KNIEBEUGE);
		KRAFTWERT_LOWBAR_KNIEBEUGE.setMaximum(150);
		KRAFTWERT_LOWBAR_KNIEBEUGE.setKoerpergewicht(BENUTZER_JUSTIN.getAktuellesKoerpergewicht());
		KRAFTWERT_LOWBAR_KNIEBEUGE.setDatum(LocalDate.now());
		KRAFTWERT_LOWBAR_KNIEBEUGE.setWiederholungen(Wiederholungen.ONE_REP_MAX);
		KRAFTWERT_LOWBAR_KNIEBEUGE.setBenutzer(BENUTZER_JUSTIN);

		KRAFTWERT_DTO_LOWBAR_KNIEBEUGE
			.setPrimaerschluessel(KRAFTWERT_LOWBAR_KNIEBEUGE.getPrimaerschluessel().getId().toString());
		KRAFTWERT_DTO_LOWBAR_KNIEBEUGE.setMaximum(KRAFTWERT_LOWBAR_KNIEBEUGE.getMaximum());
		KRAFTWERT_DTO_LOWBAR_KNIEBEUGE.setKoerpergewicht(KRAFTWERT_LOWBAR_KNIEBEUGE.getKoerpergewicht());
		KRAFTWERT_DTO_LOWBAR_KNIEBEUGE
			.setDatum(KRAFTWERT_LOWBAR_KNIEBEUGE.getDatum().format(DateTimeFormatter.ofPattern(DATUMSFORMAT)));
		KRAFTWERT_DTO_LOWBAR_KNIEBEUGE.setWiederholungen(KRAFTWERT_LOWBAR_KNIEBEUGE.getWiederholungen().name());

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

		BELASTUNGSFAKTOR_DTO_KONVENTIONELLES_KREUZHEBEN
			.setPrimaerschluessel(
				BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN.getPrimaerschluessel().getId().toString());
		BELASTUNGSFAKTOR_DTO_KONVENTIONELLES_KREUZHEBEN
			.setBack(BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN.getBack());
		BELASTUNGSFAKTOR_DTO_KONVENTIONELLES_KREUZHEBEN
			.setBenchpress(BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN.getBenchpress());
		BELASTUNGSFAKTOR_DTO_KONVENTIONELLES_KREUZHEBEN
			.setBiceps(BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN.getBiceps());
		BELASTUNGSFAKTOR_DTO_KONVENTIONELLES_KREUZHEBEN
			.setChest(BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN.getChest());
		BELASTUNGSFAKTOR_DTO_KONVENTIONELLES_KREUZHEBEN
			.setCore(BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN.getCore());
		BELASTUNGSFAKTOR_DTO_KONVENTIONELLES_KREUZHEBEN
			.setDeadlift(BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN.getDeadlift());
		BELASTUNGSFAKTOR_DTO_KONVENTIONELLES_KREUZHEBEN
			.setGlutes(BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN.getGlutes());
		BELASTUNGSFAKTOR_DTO_KONVENTIONELLES_KREUZHEBEN
			.setHamstrings(BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN.getHamstrings());
		BELASTUNGSFAKTOR_DTO_KONVENTIONELLES_KREUZHEBEN
			.setQuads(BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN.getQuads());
		BELASTUNGSFAKTOR_DTO_KONVENTIONELLES_KREUZHEBEN
			.setShoulder(BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN.getShoulder());
		BELASTUNGSFAKTOR_DTO_KONVENTIONELLES_KREUZHEBEN
			.setSquat(BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN.getSquat());
		BELASTUNGSFAKTOR_DTO_KONVENTIONELLES_KREUZHEBEN
			.setTriceps(BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN.getTriceps());

		KONVENTIONELLES_KREUZHEBEN.setPrimaerschluessel(KONVENTIONELLES_KREUZHEBEN_ID);
		KONVENTIONELLES_KREUZHEBEN.setName("Konventionelles Kreuzheben");
		KONVENTIONELLES_KREUZHEBEN.setUebungsart(Uebungsart.GRUNDUEBUNG);
		KONVENTIONELLES_KREUZHEBEN.setUebungskategorie(Uebungskategorie.WETTKAMPF_KREUZHEBEN);
		KONVENTIONELLES_KREUZHEBEN.setBelastungsfaktor(BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN);

		UEBUNG_DTO_KONVENTIONELLES_KREUZHEBEN
			.setPrimaerschluessel(KONVENTIONELLES_KREUZHEBEN.getPrimaerschluessel().getId().toString());
		UEBUNG_DTO_KONVENTIONELLES_KREUZHEBEN.setName(KONVENTIONELLES_KREUZHEBEN.getName());
		UEBUNG_DTO_KONVENTIONELLES_KREUZHEBEN.setUebungsart(KONVENTIONELLES_KREUZHEBEN.getUebungsart().name());
		UEBUNG_DTO_KONVENTIONELLES_KREUZHEBEN
			.setUebungskategorie(KONVENTIONELLES_KREUZHEBEN.getUebungskategorie().name());
		UEBUNG_DTO_KONVENTIONELLES_KREUZHEBEN
			.setBelastungsfaktor(BELASTUNGSFAKTOR_DTO_KONVENTIONELLES_KREUZHEBEN);

		KRAFTWERT_KONVENTIONELLES_KREUZHEBEN.setPrimaerschluessel(KRAFTWERT_KONVENTIONELLES_KREUZHEBEN_ID);
		KRAFTWERT_KONVENTIONELLES_KREUZHEBEN.setUebung(KONVENTIONELLES_KREUZHEBEN);
		KRAFTWERT_KONVENTIONELLES_KREUZHEBEN.setMaximum(200);
		KRAFTWERT_KONVENTIONELLES_KREUZHEBEN.setKoerpergewicht(BENUTZER_JUSTIN.getAktuellesKoerpergewicht());
		KRAFTWERT_KONVENTIONELLES_KREUZHEBEN.setDatum(LocalDate.now());
		KRAFTWERT_KONVENTIONELLES_KREUZHEBEN.setWiederholungen(Wiederholungen.ONE_REP_MAX);
		KRAFTWERT_KONVENTIONELLES_KREUZHEBEN.setBenutzer(BENUTZER_JUSTIN);

		KRAFTWERT_DTO_KONVENTIONELLES_KREUZHEBEN
			.setPrimaerschluessel(KRAFTWERT_KONVENTIONELLES_KREUZHEBEN.getPrimaerschluessel().getId().toString());
		KRAFTWERT_DTO_KONVENTIONELLES_KREUZHEBEN.setMaximum(KRAFTWERT_KONVENTIONELLES_KREUZHEBEN.getMaximum());
		KRAFTWERT_DTO_KONVENTIONELLES_KREUZHEBEN
			.setKoerpergewicht(KRAFTWERT_KONVENTIONELLES_KREUZHEBEN.getKoerpergewicht());
		KRAFTWERT_DTO_KONVENTIONELLES_KREUZHEBEN.setDatum(
			KRAFTWERT_KONVENTIONELLES_KREUZHEBEN.getDatum().format(DateTimeFormatter.ofPattern(DATUMSFORMAT)));
		KRAFTWERT_DTO_KONVENTIONELLES_KREUZHEBEN
			.setWiederholungen(KRAFTWERT_KONVENTIONELLES_KREUZHEBEN.getWiederholungen().name());
	}
}
