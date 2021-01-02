package de.justinharder.trainharder.setup;

import de.justinharder.trainharder.model.domain.*;
import de.justinharder.trainharder.model.domain.embeddables.*;
import de.justinharder.trainharder.model.domain.enums.*;
import de.justinharder.trainharder.view.dto.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Testdaten
{
	private static final DateTimeFormatter DATUMFORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
	private static final String KOERPERGEWICHT = "90.00";

	public static final Primaerschluessel AUTHENTIFIZIERUNG_JUSTIN_ID = new Primaerschluessel();
	public static final Primaerschluessel AUTHENTIFIZIERUNG_EDUARD_ID = new Primaerschluessel();

	public static final Passwort PASSWORT = new Passwort();

	public static final Authentifizierung AUTHENTIFIZIERUNG_JUSTIN = new Authentifizierung();
	public static final Authentifizierung AUTHENTIFIZIERUNG_EDUARD = new Authentifizierung();

	public static final AuthentifizierungDto AUTHENTIFIZIERUNG_DTO_JUSTIN = new AuthentifizierungDto();
	public static final AuthentifizierungDto AUTHENTIFIZIERUNG_DTO_EDUARD = new AuthentifizierungDto();

	public static final Koerpermasse KOERPERMASSE_JUSTIN =
		new Koerpermasse(new BigDecimal(178), new BigDecimal(90), new BigDecimal(25));
	public static final Koerpermasse KOERPERMASSE_EDUARD =
		new Koerpermasse(new BigDecimal(182), new BigDecimal(64), new BigDecimal(9));

	public static final KoerpermasseDto KOERPERMASSE_DTO_JUSTIN = new KoerpermasseDto();
	public static final KoerpermasseDto KOERPERMASSE_DTO_EDUARD = new KoerpermasseDto();

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

	public static final GrunduebungBelastung GRUNDUEBUNG_BELASTUNG_WETTKAMPFBANKDRUECKEN = new GrunduebungBelastung();
	public static final GrunduebungBelastung GRUNDUEBUNG_BELASTUNG_LOWBAR_KNIEBEUGE = new GrunduebungBelastung();
	public static final GrunduebungBelastung GRUNDUEBUNG_BELASTUNG_KONVENTIONELLES_KREUZHEBEN =
		new GrunduebungBelastung();

	public static final OberkoerperBelastung OBERKOERPER_BELASTUNG_WETTKAMPFBANKDRUECKEN = new OberkoerperBelastung();
	public static final OberkoerperBelastung OBERKOERPER_BELASTUNG_LOWBAR_KNIEBEUGE = new OberkoerperBelastung();
	public static final OberkoerperBelastung OBERKOERPER_BELASTUNG_KONVENTIONELLES_KREUZHEBEN =
		new OberkoerperBelastung();

	public static final UnterkoerperBelastung UNTERKOERPER_BELASTUNG_WETTKAMPFBANKDRUECKEN =
		new UnterkoerperBelastung();
	public static final UnterkoerperBelastung UNTERKOERPER_BELASTUNG_LOWBAR_KNIEBEUGE = new UnterkoerperBelastung();
	public static final UnterkoerperBelastung UNTERKOERPER_BELASTUNG_KONVENTIONELLES_KREUZHEBEN =
		new UnterkoerperBelastung();

	public static final Primaerschluessel BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN_ID = new Primaerschluessel();
	public static final Primaerschluessel BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE_ID = new Primaerschluessel();
	public static final Primaerschluessel BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN_ID = new Primaerschluessel();

	public static final Belastungsfaktor BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN = new Belastungsfaktor();
	public static final Belastungsfaktor BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE = new Belastungsfaktor();
	public static final Belastungsfaktor BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN = new Belastungsfaktor();

	public static final GrunduebungBelastungDto GRUNDUEBUNG_BELASTUNG_DTO_WETTKAMPFBANKDRUECKEN =
		new GrunduebungBelastungDto();
	public static final GrunduebungBelastungDto GRUNDUEBUNG_BELASTUNG_DTO_LOWBAR_KNIEBEUGE =
		new GrunduebungBelastungDto();
	public static final GrunduebungBelastungDto GRUNDUEBUNG_BELASTUNG_DTO_KONVENTIONELLES_KREUZHEBEN =
		new GrunduebungBelastungDto();

	public static final OberkoerperBelastungDto OBERKOERPER_BELASTUNG_DTO_WETTKAMPFBANKDRUECKEN =
		new OberkoerperBelastungDto();
	public static final OberkoerperBelastungDto OBERKOERPER_BELASTUNG_DTO_LOWBAR_KNIEBEUGE =
		new OberkoerperBelastungDto();
	public static final OberkoerperBelastungDto OBERKOERPER_BELASTUNG_DTO_KONVENTIONELLES_KREUZHEBEN =
		new OberkoerperBelastungDto();

	public static final UnterkoerperBelastungDto UNTERKOERPER_BELASTUNG_DTO_WETTKAMPFBANKDRUECKEN =
		new UnterkoerperBelastungDto();
	public static final UnterkoerperBelastungDto UNTERKOERPER_BELASTUNG_DTO_LOWBAR_KNIEBEUGE =
		new UnterkoerperBelastungDto();
	public static final UnterkoerperBelastungDto UNTERKOERPER_BELASTUNG_DTO_KONVENTIONELLES_KREUZHEBEN =
		new UnterkoerperBelastungDto();

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

		KOERPERMASSE_DTO_JUSTIN
			.setKoerpergroesse("178")
			.setKoerpergewicht(KOERPERGEWICHT)
			.setKoerperfettAnteil("25.0")
			.setFettfreiesKoerpergewicht("67.50")
			.setBodyMassIndex("28.4")
			.setFatFreeMassIndex("21.4");

		KOERPERMESSUNG_JUSTIN
			.setPrimaerschluessel(KOERPERMESSUNG_JUSTIN_ID)
			.setDatum(LocalDate.of(2020, 7, 29))
			.setKoerpermasse(KOERPERMASSE_JUSTIN)
			.setKalorieneinnahme(2500)
			.setKalorienverbrauch(2900)
			.setBenutzer(BENUTZER_JUSTIN);

		KOERPERMESSUNG_DTO_JUSTIN
			.setPrimaerschluessel(KOERPERMESSUNG_JUSTIN.getPrimaerschluessel().getId().toString())
			.setDatum(KOERPERMESSUNG_JUSTIN.getDatum().format(DATUMFORMAT))
			.setKoerpermasse(KOERPERMASSE_DTO_JUSTIN)
			.setKalorieneinnahme(KOERPERMESSUNG_JUSTIN.getKalorieneinnahme())
			.setKalorienverbrauch(KOERPERMESSUNG_JUSTIN.getKalorienverbrauch());

		KOERPERMASSE_DTO_EDUARD
			.setKoerpergroesse("182")
			.setKoerpergewicht("64.00")
			.setKoerperfettAnteil("9.0")
			.setFettfreiesKoerpergewicht("58.24")
			.setBodyMassIndex("19.3")
			.setFatFreeMassIndex("17.5");

		KOERPERMESSUNG_EDUARD
			.setPrimaerschluessel(KOERPERMESSUNG_EDUARD_ID)
			.setDatum(LocalDate.of(2020, 7, 29))
			.setKoerpermasse(KOERPERMASSE_EDUARD)
			.setKalorieneinnahme(2500)
			.setKalorienverbrauch(2900)
			.setBenutzer(BENUTZER_EDUARD);

		KOERPERMESSUNG_DTO_EDUARD
			.setPrimaerschluessel(KOERPERMESSUNG_EDUARD.getPrimaerschluessel().getId().toString())
			.setDatum(KOERPERMESSUNG_EDUARD.getDatum().format(DATUMFORMAT))
			.setKoerpermasse(KOERPERMASSE_DTO_EDUARD)
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
			.fuegeKoerpermessungHinzu(KOERPERMESSUNG_JUSTIN)
			.fuegeKraftwertHinzu(KRAFTWERT_LOWBAR_KNIEBEUGE)
			.fuegeKraftwertHinzu(KRAFTWERT_WETTKAMPFBANKDRUECKEN)
			.fuegeKraftwertHinzu(KRAFTWERT_KONVENTIONELLES_KREUZHEBEN);

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

		GRUNDUEBUNG_BELASTUNG_WETTKAMPFBANKDRUECKEN
			.setSquat(0.0)
			.setBenchpress(1.0)
			.setDeadlift(0.0);

		OBERKOERPER_BELASTUNG_WETTKAMPFBANKDRUECKEN
			.setTriceps(0.7)
			.setChest(1.0)
			.setCore(0.0)
			.setBack(0.0)
			.setBiceps(0.0)
			.setShoulder(0.1);

		UNTERKOERPER_BELASTUNG_WETTKAMPFBANKDRUECKEN
			.setGlutes(0.0)
			.setQuads(0.0)
			.setHamstrings(0.0);

		BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN
			.setPrimaerschluessel(BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN_ID)
			.setGrunduebungBelastung(GRUNDUEBUNG_BELASTUNG_WETTKAMPFBANKDRUECKEN)
			.setOberkoerperBelastung(OBERKOERPER_BELASTUNG_WETTKAMPFBANKDRUECKEN)
			.setUnterkoerperBelastung(UNTERKOERPER_BELASTUNG_WETTKAMPFBANKDRUECKEN)
			.setUebung(UEBUNG_WETTKAMPFBANKDRUECKEN);

		GRUNDUEBUNG_BELASTUNG_DTO_WETTKAMPFBANKDRUECKEN
			.setSquat("0.0")
			.setBenchpress("1.0")
			.setDeadlift("0.0");

		OBERKOERPER_BELASTUNG_DTO_WETTKAMPFBANKDRUECKEN
			.setTriceps("0.7")
			.setChest("1.0")
			.setCore("0.0")
			.setBack("0.0")
			.setBiceps("0.0")
			.setShoulder("0.1");

		UNTERKOERPER_BELASTUNG_DTO_WETTKAMPFBANKDRUECKEN
			.setGlutes("0.0")
			.setQuads("0.0")
			.setHamstrings("0.0");

		BELASTUNGSFAKTOR_DTO_WETTKAMPFBANKDRUECKEN
			.setPrimaerschluessel(BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN.getPrimaerschluessel().getId().toString())
			.setGrunduebungBelastung(GRUNDUEBUNG_BELASTUNG_DTO_WETTKAMPFBANKDRUECKEN)
			.setOberkoerperBelastung(OBERKOERPER_BELASTUNG_DTO_WETTKAMPFBANKDRUECKEN)
			.setUnterkoerperBelastung(UNTERKOERPER_BELASTUNG_DTO_WETTKAMPFBANKDRUECKEN);

		UEBUNG_WETTKAMPFBANKDRUECKEN
			.setPrimaerschluessel(UEBUNG_WETTKAMPFBANKDRUECKEN_ID)
			.setName("Wettkampfbankdrücken (pausiert)")
			.setUebungsart(Uebungsart.GRUNDUEBUNG)
			.setUebungskategorie(Uebungskategorie.WETTKAMPF_BANKDRUECKEN)
			.setBelastungsfaktor(BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN)
			.fuegeKraftwertHinzu(KRAFTWERT_WETTKAMPFBANKDRUECKEN);

		UEBUNG_DTO_WETTKAMPFBANKDRUECKEN
			.setPrimaerschluessel(UEBUNG_WETTKAMPFBANKDRUECKEN.getPrimaerschluessel().getId().toString())
			.setName(UEBUNG_WETTKAMPFBANKDRUECKEN.getName())
			.setUebungsart(UEBUNG_WETTKAMPFBANKDRUECKEN.getUebungsart().name())
			.setUebungskategorie(UEBUNG_WETTKAMPFBANKDRUECKEN.getUebungskategorie().name())
			.setBelastungsfaktor(BELASTUNGSFAKTOR_DTO_WETTKAMPFBANKDRUECKEN);

		KRAFTWERT_WETTKAMPFBANKDRUECKEN
			.setPrimaerschluessel(KRAFTWERT_WETTKAMPFBANKDRUECKEN_ID)
			.setUebung(UEBUNG_WETTKAMPFBANKDRUECKEN)
			.setGewicht(new BigDecimal(100))
			.setKoerpergewicht(BENUTZER_JUSTIN.getKoerpergewicht())
			.setDatum(LocalDate.now())
			.setWiederholungen(Wiederholungen.ONE_REP_MAX)
			.setBenutzer(BENUTZER_JUSTIN);

		KRAFTWERT_DTO_WETTKAMPFBANKDRUECKEN
			.setPrimaerschluessel(KRAFTWERT_WETTKAMPFBANKDRUECKEN.getPrimaerschluessel().getId().toString())
			.setGewicht("100.00")
			.setKoerpergewicht(KOERPERGEWICHT)
			.setDatum(KRAFTWERT_WETTKAMPFBANKDRUECKEN.getDatum().format(DATUMFORMAT))
			.setWiederholungen(KRAFTWERT_WETTKAMPFBANKDRUECKEN.getWiederholungen().getWert());

		GRUNDUEBUNG_BELASTUNG_LOWBAR_KNIEBEUGE
			.setSquat(1.0)
			.setBenchpress(0.0)
			.setDeadlift(0.0);

		OBERKOERPER_BELASTUNG_LOWBAR_KNIEBEUGE
			.setTriceps(0.0)
			.setChest(0.0)
			.setCore(0.3)
			.setBack(0.2)
			.setBiceps(0.0)
			.setShoulder(0.0);

		UNTERKOERPER_BELASTUNG_LOWBAR_KNIEBEUGE
			.setGlutes(1.0)
			.setQuads(1.0)
			.setHamstrings(0.5);

		BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE
			.setPrimaerschluessel(BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE_ID)
			.setGrunduebungBelastung(GRUNDUEBUNG_BELASTUNG_LOWBAR_KNIEBEUGE)
			.setOberkoerperBelastung(OBERKOERPER_BELASTUNG_LOWBAR_KNIEBEUGE)
			.setUnterkoerperBelastung(UNTERKOERPER_BELASTUNG_LOWBAR_KNIEBEUGE)
			.setUebung(UEBUNG_LOWBAR_KNIEBEUGE);

		GRUNDUEBUNG_BELASTUNG_DTO_LOWBAR_KNIEBEUGE
			.setSquat("1.0")
			.setBenchpress("0.0")
			.setDeadlift("0.0");

		OBERKOERPER_BELASTUNG_DTO_LOWBAR_KNIEBEUGE
			.setTriceps("0.0")
			.setChest("0.0")
			.setCore("0.3")
			.setBack("0.2")
			.setBiceps("0.0")
			.setShoulder("0.0");

		UNTERKOERPER_BELASTUNG_DTO_LOWBAR_KNIEBEUGE
			.setGlutes("1.0")
			.setQuads("1.0")
			.setHamstrings("0.5");

		BELASTUNGSFAKTOR_DTO_LOWBAR_KNIEBEUGE
			.setPrimaerschluessel(BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE.getPrimaerschluessel().getId().toString())
			.setGrunduebungBelastung(GRUNDUEBUNG_BELASTUNG_DTO_LOWBAR_KNIEBEUGE)
			.setOberkoerperBelastung(OBERKOERPER_BELASTUNG_DTO_LOWBAR_KNIEBEUGE)
			.setUnterkoerperBelastung(UNTERKOERPER_BELASTUNG_DTO_LOWBAR_KNIEBEUGE);

		UEBUNG_LOWBAR_KNIEBEUGE
			.setPrimaerschluessel(UEBUNG_LOWBAR_KNIEBEUGE_ID)
			.setName("Lowbar-Kniebeuge")
			.setUebungsart(Uebungsart.GRUNDUEBUNG)
			.setUebungskategorie(Uebungskategorie.WETTKAMPF_KNIEBEUGE)
			.setBelastungsfaktor(BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE)
			.fuegeKraftwertHinzu(KRAFTWERT_LOWBAR_KNIEBEUGE);

		UEBUNG_DTO_LOWBAR_KNIEBEUGE
			.setPrimaerschluessel(UEBUNG_LOWBAR_KNIEBEUGE.getPrimaerschluessel().getId().toString())
			.setName(UEBUNG_LOWBAR_KNIEBEUGE.getName())
			.setUebungsart(UEBUNG_LOWBAR_KNIEBEUGE.getUebungsart().name())
			.setUebungskategorie(UEBUNG_LOWBAR_KNIEBEUGE.getUebungskategorie().name())
			.setBelastungsfaktor(BELASTUNGSFAKTOR_DTO_LOWBAR_KNIEBEUGE);

		KRAFTWERT_LOWBAR_KNIEBEUGE
			.setPrimaerschluessel(KRAFTWERT_LOWBAR_KNIEBEUGE_ID)
			.setUebung(UEBUNG_LOWBAR_KNIEBEUGE)
			.setGewicht(new BigDecimal(150))
			.setKoerpergewicht(BENUTZER_JUSTIN.getKoerpergewicht())
			.setDatum(LocalDate.now())
			.setWiederholungen(Wiederholungen.ONE_REP_MAX)
			.setBenutzer(BENUTZER_JUSTIN);

		KRAFTWERT_DTO_LOWBAR_KNIEBEUGE
			.setPrimaerschluessel(KRAFTWERT_LOWBAR_KNIEBEUGE.getPrimaerschluessel().getId().toString())
			.setGewicht("150.00")
			.setKoerpergewicht(KOERPERGEWICHT)
			.setDatum(KRAFTWERT_LOWBAR_KNIEBEUGE.getDatum().format(DATUMFORMAT))
			.setWiederholungen(KRAFTWERT_LOWBAR_KNIEBEUGE.getWiederholungen().getWert());

		GRUNDUEBUNG_BELASTUNG_KONVENTIONELLES_KREUZHEBEN
			.setSquat(0.0)
			.setBenchpress(0.0)
			.setDeadlift(1.0);

		OBERKOERPER_BELASTUNG_KONVENTIONELLES_KREUZHEBEN
			.setTriceps(0.0)
			.setChest(0.0)
			.setCore(0.3)
			.setBack(0.5)
			.setBiceps(0.0)
			.setShoulder(0.0);

		UNTERKOERPER_BELASTUNG_KONVENTIONELLES_KREUZHEBEN
			.setGlutes(0.5)
			.setQuads(0.3)
			.setHamstrings(0.5);

		BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN
			.setPrimaerschluessel(BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN_ID)
			.setGrunduebungBelastung(GRUNDUEBUNG_BELASTUNG_KONVENTIONELLES_KREUZHEBEN)
			.setOberkoerperBelastung(OBERKOERPER_BELASTUNG_KONVENTIONELLES_KREUZHEBEN)
			.setUnterkoerperBelastung(UNTERKOERPER_BELASTUNG_KONVENTIONELLES_KREUZHEBEN)
			.setUebung(UEBUNG_KONVENTIONELLES_KREUZHEBEN);

		GRUNDUEBUNG_BELASTUNG_DTO_KONVENTIONELLES_KREUZHEBEN
			.setSquat("0.0")
			.setBenchpress("0.0")
			.setDeadlift("1.0");

		OBERKOERPER_BELASTUNG_DTO_KONVENTIONELLES_KREUZHEBEN
			.setTriceps("0.0")
			.setChest("0.0")
			.setCore("0.3")
			.setBack("0.5")
			.setBiceps("0.0")
			.setShoulder("0.0");

		UNTERKOERPER_BELASTUNG_DTO_KONVENTIONELLES_KREUZHEBEN
			.setGlutes("0.5")
			.setQuads("0.3")
			.setHamstrings("0.5");

		BELASTUNGSFAKTOR_DTO_KONVENTIONELLES_KREUZHEBEN
			.setPrimaerschluessel(BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN.getPrimaerschluessel().getId().toString())
			.setGrunduebungBelastung(GRUNDUEBUNG_BELASTUNG_DTO_KONVENTIONELLES_KREUZHEBEN)
			.setOberkoerperBelastung(OBERKOERPER_BELASTUNG_DTO_KONVENTIONELLES_KREUZHEBEN)
			.setUnterkoerperBelastung(UNTERKOERPER_BELASTUNG_DTO_KONVENTIONELLES_KREUZHEBEN);

		UEBUNG_KONVENTIONELLES_KREUZHEBEN
			.setPrimaerschluessel(UEBUNG_KONVENTIONELLES_KREUZHEBEN_ID)
			.setName("Konventionelles Kreuzheben")
			.setUebungsart(Uebungsart.GRUNDUEBUNG)
			.setUebungskategorie(Uebungskategorie.WETTKAMPF_KREUZHEBEN)
			.setBelastungsfaktor(BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN)
			.fuegeKraftwertHinzu(KRAFTWERT_KONVENTIONELLES_KREUZHEBEN);

		UEBUNG_DTO_KONVENTIONELLES_KREUZHEBEN
			.setPrimaerschluessel(UEBUNG_KONVENTIONELLES_KREUZHEBEN.getPrimaerschluessel().getId().toString())
			.setName(UEBUNG_KONVENTIONELLES_KREUZHEBEN.getName())
			.setUebungsart(UEBUNG_KONVENTIONELLES_KREUZHEBEN.getUebungsart().name())
			.setUebungskategorie(UEBUNG_KONVENTIONELLES_KREUZHEBEN.getUebungskategorie().name())
			.setBelastungsfaktor(BELASTUNGSFAKTOR_DTO_KONVENTIONELLES_KREUZHEBEN);

		KRAFTWERT_KONVENTIONELLES_KREUZHEBEN
			.setPrimaerschluessel(KRAFTWERT_KONVENTIONELLES_KREUZHEBEN_ID)
			.setUebung(UEBUNG_KONVENTIONELLES_KREUZHEBEN)
			.setGewicht(new BigDecimal(200))
			.setKoerpergewicht(BENUTZER_JUSTIN.getKoerpergewicht())
			.setDatum(LocalDate.now())
			.setWiederholungen(Wiederholungen.ONE_REP_MAX)
			.setBenutzer(BENUTZER_JUSTIN);

		KRAFTWERT_DTO_KONVENTIONELLES_KREUZHEBEN
			.setPrimaerschluessel(KRAFTWERT_KONVENTIONELLES_KREUZHEBEN.getPrimaerschluessel().getId().toString())
			.setGewicht("200.00")
			.setKoerpergewicht(KOERPERGEWICHT)
			.setDatum(KRAFTWERT_KONVENTIONELLES_KREUZHEBEN.getDatum().format(DATUMFORMAT))
			.setWiederholungen(KRAFTWERT_KONVENTIONELLES_KREUZHEBEN.getWiederholungen().getWert());
	}
}
