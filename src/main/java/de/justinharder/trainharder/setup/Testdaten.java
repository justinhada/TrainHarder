package de.justinharder.trainharder.setup;

import de.justinharder.trainharder.model.domain.*;
import de.justinharder.trainharder.model.domain.embeddables.*;
import de.justinharder.trainharder.model.domain.enums.*;
import de.justinharder.trainharder.view.dto.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Testdaten
{
	private static final String DATUMSFORMAT = "dd.MM.yyyy";

	public static final Primaerschluessel AUTHENTIFIZIERUNG_JUSTIN_ID = new Primaerschluessel();
	public static final Primaerschluessel AUTHENTIFIZIERUNG_EDUARD_ID = new Primaerschluessel();

	public static final Passwort PASSWORT = new Passwort();

	public static final Authentifizierung AUTHENTIFIZIERUNG_JUSTIN = new Authentifizierung();
	public static final Authentifizierung AUTHENTIFIZIERUNG_EDUARD = new Authentifizierung();

	public static final AuthentifizierungDto AUTHENTIFIZIERUNG_DTO_JUSTIN = new AuthentifizierungDto();
	public static final AuthentifizierungDto AUTHENTIFIZIERUNG_DTO_EDUARD = new AuthentifizierungDto();

	public static final Koerpermasse KOERPERMASSE_JUSTIN = new Koerpermasse();
	public static final Koerpermasse KOERPERMASSE_EDUARD = new Koerpermasse();

	public static final Primaerschluessel KOERPERMESSUNG_JUSTIN_ID = new Primaerschluessel();
	public static final Primaerschluessel KOERPERMESSUNG_EDUARD_ID = new Primaerschluessel();

	public static final Koerpermessung KOERPERMESSUNG_JUSTIN = new Koerpermessung();
	public static final Koerpermessung KOERPERMESSUNG_EDUARD = new Koerpermessung();

	public static final KoerpermessungDto KOERPERMESSUNG_DTO_JUSTIN = new KoerpermessungDto();
	public static final KoerpermessungDto KOERPERMESSUNG_DTO_EDUARD = new KoerpermessungDto();

	public static final Benutzerangabe BENUTZERANGABE_JUSTIN = new Benutzerangabe();
	public static final Benutzerangabe BENUTZERANGABE_EDUARD = new Benutzerangabe();

	public static final Primaerschluessel BENUTZER_JUSTIN_ID = new Primaerschluessel();
	public static final Primaerschluessel BENUTZER_EDUARD_ID = new Primaerschluessel();

	public static final Benutzer BENUTZER_JUSTIN = new Benutzer();
	public static final Benutzer BENUTZER_EDUARD = new Benutzer();

	public static final BenutzerDto BENUTZER_DTO_JUSTIN = new BenutzerDto();
	public static final BenutzerDto BENUTZER_DTO_EDUARD = new BenutzerDto();

	public static final Primaerschluessel BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN_ID = new Primaerschluessel();
	public static final Primaerschluessel BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE_ID = new Primaerschluessel();
	public static final Primaerschluessel BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN_ID = new Primaerschluessel();

	public static final Belastungsfaktor BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN = new Belastungsfaktor();
	public static final Belastungsfaktor BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE = new Belastungsfaktor();
	public static final Belastungsfaktor BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN = new Belastungsfaktor();

	public static final BelastungsfaktorDto BELASTUNGSFAKTOR_DTO_WETTKAMPFBANKDRUECKEN = new BelastungsfaktorDto();
	public static final BelastungsfaktorDto BELASTUNGSFAKTOR_DTO_LOWBAR_KNIEBEUGE = new BelastungsfaktorDto();
	public static final BelastungsfaktorDto BELASTUNGSFAKTOR_DTO_KONVENTIONELLES_KREUZHEBEN = new BelastungsfaktorDto();

	public static final Primaerschluessel UEBUNG_WETTKAMPFBANKDRUECKEN_ID = new Primaerschluessel();
	public static final Primaerschluessel UEBUNG_LOWBAR_KNIEBEUGE_ID = new Primaerschluessel();
	public static final Primaerschluessel UEBUNG_KONVENTIONELLES_KREUZHEBEN_ID = new Primaerschluessel();

	public static final Uebung UEBUNG_WETTKAMPFBANKDRUECKEN = new Uebung();
	public static final Uebung UEBUNG_LOWBAR_KNIEBEUGE = new Uebung();
	public static final Uebung UEBUNG_KONVENTIONELLES_KREUZHEBEN = new Uebung();

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
		PASSWORT
			.setSalt("lhwMFKf4DTBEXnWG7tXvhA==")
			.setPasswortHash("mNMZ8W5m2jf5TtSBnNfB/w==");

		AUTHENTIFIZIERUNG_JUSTIN
			.setPrimaerschluessel(AUTHENTIFIZIERUNG_JUSTIN_ID)
			.setMail("mail@justinharder.de")
			.setBenutzername("harder")
			.setPasswort(PASSWORT)
			.setAktiv(false)
			.setResetUuid(UUID.randomUUID())
			.setBenutzer(BENUTZER_JUSTIN);

		AUTHENTIFIZIERUNG_EDUARD
			.setPrimaerschluessel(AUTHENTIFIZIERUNG_EDUARD_ID)
			.setMail("mail@eduard.de")
			.setBenutzername("eduard")
			.setPasswort(PASSWORT)
			.setAktiv(true)
			.setBenutzer(BENUTZER_EDUARD);

		AUTHENTIFIZIERUNG_DTO_JUSTIN
			.setPrimaerschluessel(AUTHENTIFIZIERUNG_JUSTIN.getPrimaerschluessel().getId().toString())
			.setMail(AUTHENTIFIZIERUNG_JUSTIN.getMail())
			.setBenutzername(AUTHENTIFIZIERUNG_JUSTIN.getBenutzername());

		AUTHENTIFIZIERUNG_DTO_EDUARD
			.setPrimaerschluessel(AUTHENTIFIZIERUNG_EDUARD.getPrimaerschluessel().getId().toString())
			.setMail(AUTHENTIFIZIERUNG_EDUARD.getMail())
			.setBenutzername(AUTHENTIFIZIERUNG_EDUARD.getBenutzername());

		KOERPERMASSE_JUSTIN
			.setKoerpergroesse(178)
			.setKoerpergewicht(90)
			.setKoerperfettAnteil(25);

		KOERPERMESSUNG_JUSTIN
			.setPrimaerschluessel(KOERPERMESSUNG_JUSTIN_ID)
			.setDatum(LocalDate.of(2020, 7, 29))
			.setKoerpermasse(KOERPERMASSE_JUSTIN)
			.setKalorieneinnahme(2500)
			.setKalorienverbrauch(2900)
			.setBenutzer(BENUTZER_JUSTIN);

		KOERPERMESSUNG_DTO_JUSTIN
			.setPrimaerschluessel(KOERPERMESSUNG_JUSTIN.getPrimaerschluessel().getId().toString())
			.setDatum(KOERPERMESSUNG_JUSTIN.getDatum().format(DateTimeFormatter.ofPattern(DATUMSFORMAT)))
			.setKoerpergroesse(KOERPERMESSUNG_JUSTIN.getKoerpermasse().getKoerpergroesse())
			.setKoerpergewicht(KOERPERMESSUNG_JUSTIN.getKoerpermasse().getKoerpergewicht())
			.setKoerperfettAnteil(KOERPERMESSUNG_JUSTIN.getKoerpermasse().getKoerperfettAnteil())
			.setKalorieneinnahme(KOERPERMESSUNG_JUSTIN.getKalorieneinnahme())
			.setKalorienverbrauch(KOERPERMESSUNG_JUSTIN.getKalorienverbrauch());

		KOERPERMASSE_EDUARD
			.setKoerpergroesse(182)
			.setKoerpergewicht(64)
			.setKoerperfettAnteil(9);

		KOERPERMESSUNG_EDUARD
			.setPrimaerschluessel(KOERPERMESSUNG_EDUARD_ID)
			.setDatum(LocalDate.of(2020, 7, 29))
			.setKoerpermasse(KOERPERMASSE_EDUARD)
			.setKalorieneinnahme(2500)
			.setKalorienverbrauch(2900)
			.setBenutzer(BENUTZER_EDUARD);

		KOERPERMESSUNG_DTO_EDUARD
			.setPrimaerschluessel(KOERPERMESSUNG_EDUARD.getPrimaerschluessel().getId().toString())
			.setDatum(KOERPERMESSUNG_EDUARD.getDatum().format(DateTimeFormatter.ofPattern(DATUMSFORMAT)))
			.setKoerpergroesse(KOERPERMESSUNG_EDUARD.getKoerpermasse().getKoerpergroesse())
			.setKoerpergewicht(KOERPERMESSUNG_EDUARD.getKoerpermasse().getKoerpergewicht())
			.setKoerperfettAnteil(KOERPERMESSUNG_EDUARD.getKoerpermasse().getKoerperfettAnteil())
			.setKalorieneinnahme(KOERPERMESSUNG_EDUARD.getKalorieneinnahme())
			.setKalorienverbrauch(KOERPERMESSUNG_EDUARD.getKalorienverbrauch());

		BENUTZERANGABE_JUSTIN
			.setKraftlevel(Kraftlevel.CLASS_5)
			.setGeschlecht(Geschlecht.MAENNLICH)
			.setErfahrung(Erfahrung.BEGINNER)
			.setErnaehrung(Ernaehrung.GUT)
			.setSchlafqualitaet(Schlafqualitaet.GUT)
			.setStress(Stress.MITTELMAESSIG)
			.setDoping(Doping.NEIN)
			.setRegenerationsfaehigkeit(Regenerationsfaehigkeit.GUT);

		BENUTZER_JUSTIN
			.setPrimaerschluessel(BENUTZER_JUSTIN_ID)
			.setName(new Name("Justin", "Harder"))
			.setGeburtsdatum(LocalDate.of(1998, 12, 6))
			.setBenutzerangabe(BENUTZERANGABE_JUSTIN)
			.setAuthentifizierung(AUTHENTIFIZIERUNG_JUSTIN)
			.fuegeKoerpermessungHinzu(KOERPERMESSUNG_JUSTIN);

		BENUTZER_DTO_JUSTIN
			.setPrimaerschluessel(BENUTZER_JUSTIN.getPrimaerschluessel().getId().toString())
			.setVorname(BENUTZER_JUSTIN.getName().getVorname())
			.setNachname(BENUTZER_JUSTIN.getName().getNachname())
			.setGeburtsdatum(BENUTZER_JUSTIN.getGeburtsdatum())
			.setKraftlevel(BENUTZER_JUSTIN.getBenutzerangabe().getKraftlevel().name())
			.setGeschlecht(BENUTZER_JUSTIN.getBenutzerangabe().getGeschlecht().name())
			.setErfahrung(BENUTZER_JUSTIN.getBenutzerangabe().getErfahrung().name())
			.setErnaehrung(BENUTZER_JUSTIN.getBenutzerangabe().getErnaehrung().name())
			.setSchlafqualitaet(BENUTZER_JUSTIN.getBenutzerangabe().getSchlafqualitaet().name())
			.setStress(BENUTZER_JUSTIN.getBenutzerangabe().getStress().name())
			.setDoping(BENUTZER_JUSTIN.getBenutzerangabe().getDoping().name())
			.setRegenerationsfaehigkeit(BENUTZER_JUSTIN.getBenutzerangabe().getRegenerationsfaehigkeit().name())
			.setAuthentifizierung(AUTHENTIFIZIERUNG_DTO_JUSTIN)
			.fuegeKoerpermessungHinzu(KOERPERMESSUNG_DTO_JUSTIN);

		BENUTZERANGABE_EDUARD
			.setKraftlevel(Kraftlevel.CLASS_4)
			.setGeschlecht(Geschlecht.MAENNLICH)
			.setErfahrung(Erfahrung.FORTGESCHRITTEN)
			.setErnaehrung(Ernaehrung.SCHLECHT)
			.setSchlafqualitaet(Schlafqualitaet.SCHLECHT)
			.setStress(Stress.NIEDRIG)
			.setDoping(Doping.JA)
			.setRegenerationsfaehigkeit(Regenerationsfaehigkeit.PERFEKT);

		BENUTZER_EDUARD
			.setPrimaerschluessel(BENUTZER_EDUARD_ID)
			.setName(new Name("Eduard", "Stremel"))
			.setGeburtsdatum(LocalDate.of(1998, 11, 9))
			.setBenutzerangabe(BENUTZERANGABE_EDUARD)
			.setAuthentifizierung(AUTHENTIFIZIERUNG_EDUARD)
			.fuegeKoerpermessungHinzu(KOERPERMESSUNG_EDUARD);

		BENUTZER_DTO_EDUARD
			.setPrimaerschluessel(BENUTZER_EDUARD.getPrimaerschluessel().getId().toString())
			.setVorname(BENUTZER_EDUARD.getName().getVorname())
			.setNachname(BENUTZER_EDUARD.getName().getNachname())
			.setGeburtsdatum(BENUTZER_EDUARD.getGeburtsdatum())
			.setKraftlevel(BENUTZER_EDUARD.getBenutzerangabe().getKraftlevel().name())
			.setGeschlecht(BENUTZER_EDUARD.getBenutzerangabe().getGeschlecht().name())
			.setErfahrung(BENUTZER_EDUARD.getBenutzerangabe().getErfahrung().name())
			.setErnaehrung(BENUTZER_EDUARD.getBenutzerangabe().getErnaehrung().name())
			.setSchlafqualitaet(BENUTZER_EDUARD.getBenutzerangabe().getSchlafqualitaet().name())
			.setStress(BENUTZER_EDUARD.getBenutzerangabe().getStress().name())
			.setDoping(BENUTZER_EDUARD.getBenutzerangabe().getDoping().name())
			.setRegenerationsfaehigkeit(BENUTZER_EDUARD.getBenutzerangabe().getRegenerationsfaehigkeit().name())
			.setAuthentifizierung(AUTHENTIFIZIERUNG_DTO_EDUARD)
			.fuegeKoerpermessungHinzu(KOERPERMESSUNG_DTO_EDUARD);

		BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN
			.setPrimaerschluessel(BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN_ID)
			.setBack(0.0)
			.setBenchpress(1.0)
			.setBiceps(0.0)
			.setChest(1.0)
			.setCore(0.0)
			.setDeadlift(0.0)
			.setGlutes(0.0)
			.setHamstrings(0.0)
			.setQuads(0.0)
			.setShoulder(0.1)
			.setSquat(0.0)
			.setTriceps(0.7)
			.setUebung(UEBUNG_WETTKAMPFBANKDRUECKEN);

		BELASTUNGSFAKTOR_DTO_WETTKAMPFBANKDRUECKEN
			.setPrimaerschluessel(BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN.getPrimaerschluessel().getId().toString())
			.setBack(BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN.getBack())
			.setBenchpress(BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN.getBenchpress())
			.setBiceps(BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN.getBiceps())
			.setChest(BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN.getChest())
			.setCore(BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN.getCore())
			.setDeadlift(BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN.getDeadlift())
			.setGlutes(BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN.getGlutes())
			.setHamstrings(BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN.getHamstrings())
			.setQuads(BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN.getQuads())
			.setShoulder(BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN.getShoulder())
			.setSquat(BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN.getSquat())
			.setTriceps(BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN.getTriceps());

		UEBUNG_WETTKAMPFBANKDRUECKEN
			.setPrimaerschluessel(UEBUNG_WETTKAMPFBANKDRUECKEN_ID)
			.setName("Wettkampfbankdrücken (pausiert)")
			.setUebungsart(Uebungsart.GRUNDUEBUNG)
			.setUebungskategorie(Uebungskategorie.WETTKAMPF_BANKDRUECKEN)
			.setBelastungsfaktor(BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN);

		UEBUNG_DTO_WETTKAMPFBANKDRUECKEN
			.setPrimaerschluessel(UEBUNG_WETTKAMPFBANKDRUECKEN.getPrimaerschluessel().getId().toString())
			.setName(UEBUNG_WETTKAMPFBANKDRUECKEN.getName())
			.setUebungsart(UEBUNG_WETTKAMPFBANKDRUECKEN.getUebungsart().name())
			.setUebungskategorie(UEBUNG_WETTKAMPFBANKDRUECKEN.getUebungskategorie().name())
			.setBelastungsfaktor(BELASTUNGSFAKTOR_DTO_WETTKAMPFBANKDRUECKEN);

		KRAFTWERT_WETTKAMPFBANKDRUECKEN
			.setPrimaerschluessel(KRAFTWERT_WETTKAMPFBANKDRUECKEN_ID)
			.setUebung(UEBUNG_WETTKAMPFBANKDRUECKEN)
			.setGewicht(100)
			.setKoerpergewicht(BENUTZER_JUSTIN.getKoerpergewicht())
			.setDatum(LocalDate.now())
			.setWiederholungen(Wiederholungen.ONE_REP_MAX)
			.setBenutzer(BENUTZER_JUSTIN);

		KRAFTWERT_DTO_WETTKAMPFBANKDRUECKEN
			.setPrimaerschluessel(KRAFTWERT_WETTKAMPFBANKDRUECKEN.getPrimaerschluessel().getId().toString())
			.setGewicht(KRAFTWERT_WETTKAMPFBANKDRUECKEN.getGewicht())
			.setKoerpergewicht(KRAFTWERT_WETTKAMPFBANKDRUECKEN.getKoerpergewicht())
			.setDatum(KRAFTWERT_WETTKAMPFBANKDRUECKEN.getDatum().format(DateTimeFormatter.ofPattern(DATUMSFORMAT)))
			.setWiederholungen(KRAFTWERT_WETTKAMPFBANKDRUECKEN.getWiederholungen().getWert());

		BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE
			.setPrimaerschluessel(BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE_ID)
			.setBack(0.2)
			.setBenchpress(0.0)
			.setBiceps(0.0)
			.setChest(0.0)
			.setCore(0.3)
			.setDeadlift(0.0)
			.setGlutes(1.0)
			.setHamstrings(0.5)
			.setQuads(1.0)
			.setShoulder(0.0)
			.setSquat(1.0)
			.setTriceps(0.0)
			.setUebung(UEBUNG_LOWBAR_KNIEBEUGE);

		BELASTUNGSFAKTOR_DTO_LOWBAR_KNIEBEUGE
			.setPrimaerschluessel(BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE.getPrimaerschluessel().getId().toString())
			.setBack(BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE.getBack())
			.setBenchpress(BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE.getBenchpress())
			.setBiceps(BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE.getBiceps())
			.setChest(BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE.getChest())
			.setCore(BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE.getCore())
			.setDeadlift(BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE.getDeadlift())
			.setGlutes(BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE.getGlutes())
			.setHamstrings(BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE.getHamstrings())
			.setQuads(BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE.getQuads())
			.setShoulder(BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE.getShoulder())
			.setSquat(BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE.getSquat())
			.setTriceps(BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE.getTriceps());

		UEBUNG_LOWBAR_KNIEBEUGE
			.setPrimaerschluessel(UEBUNG_LOWBAR_KNIEBEUGE_ID)
			.setName("Lowbar-Kniebeuge")
			.setUebungsart(Uebungsart.GRUNDUEBUNG)
			.setUebungskategorie(Uebungskategorie.WETTKAMPF_KNIEBEUGE)
			.setBelastungsfaktor(BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE);

		UEBUNG_DTO_LOWBAR_KNIEBEUGE
			.setPrimaerschluessel(UEBUNG_LOWBAR_KNIEBEUGE.getPrimaerschluessel().getId().toString())
			.setName(UEBUNG_LOWBAR_KNIEBEUGE.getName())
			.setUebungsart(UEBUNG_LOWBAR_KNIEBEUGE.getUebungsart().name())
			.setUebungskategorie(UEBUNG_LOWBAR_KNIEBEUGE.getUebungskategorie().name())
			.setBelastungsfaktor(BELASTUNGSFAKTOR_DTO_LOWBAR_KNIEBEUGE);

		KRAFTWERT_LOWBAR_KNIEBEUGE
			.setPrimaerschluessel(KRAFTWERT_LOWBAR_KNIEBEUGE_ID)
			.setUebung(UEBUNG_LOWBAR_KNIEBEUGE)
			.setGewicht(150)
			.setKoerpergewicht(BENUTZER_JUSTIN.getKoerpergewicht())
			.setDatum(LocalDate.now())
			.setWiederholungen(Wiederholungen.ONE_REP_MAX)
			.setBenutzer(BENUTZER_JUSTIN);

		KRAFTWERT_DTO_LOWBAR_KNIEBEUGE
			.setPrimaerschluessel(KRAFTWERT_LOWBAR_KNIEBEUGE.getPrimaerschluessel().getId().toString())
			.setGewicht(KRAFTWERT_LOWBAR_KNIEBEUGE.getGewicht())
			.setKoerpergewicht(KRAFTWERT_LOWBAR_KNIEBEUGE.getKoerpergewicht())
			.setDatum(KRAFTWERT_LOWBAR_KNIEBEUGE.getDatum().format(DateTimeFormatter.ofPattern(DATUMSFORMAT)))
			.setWiederholungen(KRAFTWERT_LOWBAR_KNIEBEUGE.getWiederholungen().getWert());

		BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN
			.setPrimaerschluessel(BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN_ID)
			.setBack(0.5)
			.setBenchpress(0.0)
			.setBiceps(0.0)
			.setChest(0.0)
			.setCore(0.3)
			.setDeadlift(1.0)
			.setGlutes(0.5)
			.setHamstrings(0.5)
			.setQuads(0.3)
			.setShoulder(0.0)
			.setSquat(0.0)
			.setTriceps(0.0)
			.setUebung(UEBUNG_KONVENTIONELLES_KREUZHEBEN);

		BELASTUNGSFAKTOR_DTO_KONVENTIONELLES_KREUZHEBEN
			.setPrimaerschluessel(BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN.getPrimaerschluessel().getId().toString())
			.setBack(BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN.getBack())
			.setBenchpress(BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN.getBenchpress())
			.setBiceps(BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN.getBiceps())
			.setChest(BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN.getChest())
			.setCore(BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN.getCore())
			.setDeadlift(BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN.getDeadlift())
			.setGlutes(BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN.getGlutes())
			.setHamstrings(BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN.getHamstrings())
			.setQuads(BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN.getQuads())
			.setShoulder(BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN.getShoulder())
			.setSquat(BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN.getSquat())
			.setTriceps(BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN.getTriceps());

		UEBUNG_KONVENTIONELLES_KREUZHEBEN
			.setPrimaerschluessel(UEBUNG_KONVENTIONELLES_KREUZHEBEN_ID)
			.setName("Konventionelles Kreuzheben")
			.setUebungsart(Uebungsart.GRUNDUEBUNG)
			.setUebungskategorie(Uebungskategorie.WETTKAMPF_KREUZHEBEN)
			.setBelastungsfaktor(BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN);

		UEBUNG_DTO_KONVENTIONELLES_KREUZHEBEN
			.setPrimaerschluessel(UEBUNG_KONVENTIONELLES_KREUZHEBEN.getPrimaerschluessel().getId().toString())
			.setName(UEBUNG_KONVENTIONELLES_KREUZHEBEN.getName())
			.setUebungsart(UEBUNG_KONVENTIONELLES_KREUZHEBEN.getUebungsart().name())
			.setUebungskategorie(UEBUNG_KONVENTIONELLES_KREUZHEBEN.getUebungskategorie().name())
			.setBelastungsfaktor(BELASTUNGSFAKTOR_DTO_KONVENTIONELLES_KREUZHEBEN);

		KRAFTWERT_KONVENTIONELLES_KREUZHEBEN
			.setPrimaerschluessel(KRAFTWERT_KONVENTIONELLES_KREUZHEBEN_ID)
			.setUebung(UEBUNG_KONVENTIONELLES_KREUZHEBEN)
			.setGewicht(200)
			.setKoerpergewicht(BENUTZER_JUSTIN.getKoerpergewicht())
			.setDatum(LocalDate.now())
			.setWiederholungen(Wiederholungen.ONE_REP_MAX)
			.setBenutzer(BENUTZER_JUSTIN);

		KRAFTWERT_DTO_KONVENTIONELLES_KREUZHEBEN
			.setPrimaerschluessel(KRAFTWERT_KONVENTIONELLES_KREUZHEBEN.getPrimaerschluessel().getId().toString())
			.setGewicht(KRAFTWERT_KONVENTIONELLES_KREUZHEBEN.getGewicht())
			.setKoerpergewicht(KRAFTWERT_KONVENTIONELLES_KREUZHEBEN.getKoerpergewicht())
			.setDatum(KRAFTWERT_KONVENTIONELLES_KREUZHEBEN.getDatum().format(DateTimeFormatter.ofPattern(DATUMSFORMAT)))
			.setWiederholungen(KRAFTWERT_KONVENTIONELLES_KREUZHEBEN.getWiederholungen().getWert());
	}
}
