package de.justinharder.trainharder.setup;

import de.justinharder.base.domain.model.attribute.ID;
import de.justinharder.trainharder.domain.model.*;
import de.justinharder.trainharder.domain.model.attribute.*;
import de.justinharder.trainharder.domain.model.enums.*;
import de.justinharder.trainharder.domain.services.dto.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Testdaten
{
	public static final Passwort PASSWORT = new Passwort("lhwMFKf4DTBEXnWG7tXvhA==", "mNMZ8W5m2jf5TtSBnNfB/w==");

	public static final Authentifizierung AUTHENTIFIZIERUNG_JUSTIN = new Authentifizierung(
		new ID("76cda8bb-09f2-45bb-93c8-f35fc6241d08"),
		"mail@justinharder.de",
		"harder",
		PASSWORT)
		.setAktiv(false)
		.setResetUuid(UUID.fromString("db748cb0-a773-4e99-bbff-46344732bf92"));
	public static final Authentifizierung AUTHENTIFIZIERUNG_EDUARD = new Authentifizierung(
		new ID("1056e84b-e201-4eae-9b18-9bb8ecc638ab"),
		"mail@eduard.de",
		"eduard",
		PASSWORT)
		.setAktiv(true);

	public static final AuthentifizierungDto AUTHENTIFIZIERUNG_DTO_JUSTIN = new AuthentifizierungDto(
		AUTHENTIFIZIERUNG_JUSTIN.getId().getWert().toString(),
		AUTHENTIFIZIERUNG_JUSTIN.getMail(),
		AUTHENTIFIZIERUNG_JUSTIN.getBenutzername());
	public static final AuthentifizierungDto AUTHENTIFIZIERUNG_DTO_EDUARD = new AuthentifizierungDto(
		AUTHENTIFIZIERUNG_EDUARD.getId().getWert().toString(),
		AUTHENTIFIZIERUNG_EDUARD.getMail(),
		AUTHENTIFIZIERUNG_EDUARD.getBenutzername());

	public static final Koerpermasse KOERPERMASSE_JUSTIN =
		new Koerpermasse(new BigDecimal(178), new BigDecimal(90), new BigDecimal(25));
	public static final Koerpermasse KOERPERMASSE_EDUARD =
		new Koerpermasse(new BigDecimal(182), new BigDecimal(64), new BigDecimal(9));

	public static final KoerpermasseDto KOERPERMASSE_DTO_JUSTIN = new KoerpermasseDto(
		"178",
		"90.00",
		"25.0",
		"67.50",
		"28.4",
		"21.4");
	public static final KoerpermasseDto KOERPERMASSE_DTO_EDUARD = new KoerpermasseDto(
		"182",
		"64.00",
		"9.0",
		"58.24",
		"19.3",
		"17.5");

	public static final Koerpermessung KOERPERMESSUNG_JUSTIN = new Koerpermessung(
		new ID("fce7ffb5-7aa3-49db-bfe4-7b52a0e234d0"),
		LocalDate.of(2020, 7, 29),
		KOERPERMASSE_JUSTIN,
		2500,
		2900);
	public static final Koerpermessung KOERPERMESSUNG_EDUARD = new Koerpermessung(
		new ID("36903e76-d888-4994-bfa2-d1f373d12ed7"),
		LocalDate.of(2020, 7, 29),
		KOERPERMASSE_EDUARD,
		2500,
		2900);

	public static final KoerpermessungDto KOERPERMESSUNG_DTO_JUSTIN = new KoerpermessungDto(
		KOERPERMESSUNG_JUSTIN.getId().getWert().toString(),
		KOERPERMESSUNG_JUSTIN.getDatum().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
		KOERPERMASSE_DTO_JUSTIN,
		KOERPERMESSUNG_JUSTIN.getKalorieneinnahme(),
		KOERPERMESSUNG_JUSTIN.getKalorienverbrauch());
	public static final KoerpermessungDto KOERPERMESSUNG_DTO_EDUARD = new KoerpermessungDto(
		KOERPERMESSUNG_EDUARD.getId().getWert().toString(),
		KOERPERMESSUNG_EDUARD.getDatum().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
		KOERPERMASSE_DTO_EDUARD,
		KOERPERMESSUNG_EDUARD.getKalorieneinnahme(),
		KOERPERMESSUNG_EDUARD.getKalorienverbrauch());

	public static final Benutzerangabe BENUTZERANGABE_JUSTIN = new Benutzerangabe(
		Geschlecht.MAENNLICH,
		Erfahrung.BEGINNER,
		Ernaehrung.GUT,
		Schlafqualitaet.GUT,
		Stress.MITTELMAESSIG,
		Doping.NEIN,
		Regenerationsfaehigkeit.GUT)
		.setKraftlevel(Kraftlevel.CLASS_5);
	public static final Benutzerangabe BENUTZERANGABE_EDUARD = new Benutzerangabe(
		Geschlecht.MAENNLICH,
		Erfahrung.FORTGESCHRITTEN,
		Ernaehrung.SCHLECHT,
		Schlafqualitaet.SCHLECHT,
		Stress.NIEDRIG,
		Doping.JA,
		Regenerationsfaehigkeit.PERFEKT)
		.setKraftlevel(Kraftlevel.CLASS_4);

	public static final Benutzer BENUTZER_JUSTIN = new Benutzer(
		new ID("7c4a9562-a165-40b9-a645-4cf2cee9a1e3"),
		new Name("Justin", "Harder"),
		LocalDate.of(1998, 12, 6),
		BENUTZERANGABE_JUSTIN,
		AUTHENTIFIZIERUNG_JUSTIN)
		.fuegeKoerpermessungHinzu(KOERPERMESSUNG_JUSTIN);
	public static final Benutzer BENUTZER_EDUARD = new Benutzer(
		new ID("9341a123-6b75-4b89-9454-d096b9ed2bd2"),
		new Name("Eduard", "Stremel"),
		LocalDate.of(1998, 11, 9),
		BENUTZERANGABE_EDUARD,
		AUTHENTIFIZIERUNG_EDUARD)
		.fuegeKoerpermessungHinzu(KOERPERMESSUNG_EDUARD);

	public static final BenutzerDto BENUTZER_DTO_JUSTIN = new BenutzerDto(
		BENUTZER_JUSTIN.getId().getWert().toString(),
		new NameDto(BENUTZER_JUSTIN.getName().getVorname(), BENUTZER_JUSTIN.getName().getNachname()),
		BENUTZER_JUSTIN.getGeburtsdatum(),
		new BenutzerangabeDto(
			BENUTZER_JUSTIN.getBenutzerangabe().getGeschlecht().name(),
			BENUTZER_JUSTIN.getBenutzerangabe().getErfahrung().name(),
			BENUTZER_JUSTIN.getBenutzerangabe().getErnaehrung().name(),
			BENUTZER_JUSTIN.getBenutzerangabe().getSchlafqualitaet().name(),
			BENUTZER_JUSTIN.getBenutzerangabe().getStress().name(),
			BENUTZER_JUSTIN.getBenutzerangabe().getDoping().name(),
			BENUTZER_JUSTIN.getBenutzerangabe().getRegenerationsfaehigkeit().name())
			.setKraftlevel(BENUTZER_JUSTIN.getBenutzerangabe().getKraftlevel().name()),
		AUTHENTIFIZIERUNG_DTO_JUSTIN,
		List.of(KOERPERMESSUNG_DTO_JUSTIN));
	public static final BenutzerDto BENUTZER_DTO_EDUARD = new BenutzerDto(
		BENUTZER_EDUARD.getId().getWert().toString(),
		new NameDto(BENUTZER_EDUARD.getName().getVorname(), BENUTZER_EDUARD.getName().getNachname()),
		BENUTZER_EDUARD.getGeburtsdatum(),
		new BenutzerangabeDto(
			BENUTZER_EDUARD.getBenutzerangabe().getGeschlecht().name(),
			BENUTZER_EDUARD.getBenutzerangabe().getErfahrung().name(),
			BENUTZER_EDUARD.getBenutzerangabe().getErnaehrung().name(),
			BENUTZER_EDUARD.getBenutzerangabe().getSchlafqualitaet().name(),
			BENUTZER_EDUARD.getBenutzerangabe().getStress().name(),
			BENUTZER_EDUARD.getBenutzerangabe().getDoping().name(),
			BENUTZER_EDUARD.getBenutzerangabe().getRegenerationsfaehigkeit().name())
			.setKraftlevel(BENUTZER_EDUARD.getBenutzerangabe().getKraftlevel().name()),
		AUTHENTIFIZIERUNG_DTO_EDUARD,
		List.of(KOERPERMESSUNG_DTO_EDUARD));

	public static final GrunduebungBelastung GRUNDUEBUNG_BELASTUNG_WETTKAMPFBANKDRUECKEN =
		new GrunduebungBelastung(0.0, 1.0, 0.0);
	public static final GrunduebungBelastung GRUNDUEBUNG_BELASTUNG_LOWBAR_KNIEBEUGE =
		new GrunduebungBelastung(1.0, 0.0, 0.0);
	public static final GrunduebungBelastung GRUNDUEBUNG_BELASTUNG_KONVENTIONELLES_KREUZHEBEN =
		new GrunduebungBelastung(0.0, 0.0, 1.0);

	public static final OberkoerperBelastung OBERKOERPER_BELASTUNG_WETTKAMPFBANKDRUECKEN =
		new OberkoerperBelastung(0.7, 1.0, 0.0, 0.0, 0.0, 0.1);
	public static final OberkoerperBelastung OBERKOERPER_BELASTUNG_LOWBAR_KNIEBEUGE =
		new OberkoerperBelastung(0.0, 0.0, 0.3, 0.2, 0.0, 0.0);
	public static final OberkoerperBelastung OBERKOERPER_BELASTUNG_KONVENTIONELLES_KREUZHEBEN =
		new OberkoerperBelastung(0.0, 0.0, 0.3, 0.5, 0.0, 0.0);

	public static final UnterkoerperBelastung UNTERKOERPER_BELASTUNG_WETTKAMPFBANKDRUECKEN =
		new UnterkoerperBelastung(0.0, 0.0, 0.0);
	public static final UnterkoerperBelastung UNTERKOERPER_BELASTUNG_LOWBAR_KNIEBEUGE =
		new UnterkoerperBelastung(1.0, 1.0, 0.5);
	public static final UnterkoerperBelastung UNTERKOERPER_BELASTUNG_KONVENTIONELLES_KREUZHEBEN =
		new UnterkoerperBelastung(0.5, 0.3, 0.5);

	public static final Belastung BELASTUNG_WETTKAMPFBANKDRUECKEN = new Belastung(
		new ID("48bb15b2-0fb5-4f0d-a390-1ed768fedb45"),
		GRUNDUEBUNG_BELASTUNG_WETTKAMPFBANKDRUECKEN,
		OBERKOERPER_BELASTUNG_WETTKAMPFBANKDRUECKEN,
		UNTERKOERPER_BELASTUNG_WETTKAMPFBANKDRUECKEN);
	public static final Belastung BELASTUNG_LOWBAR_KNIEBEUGE = new Belastung(
		new ID("8f077e9c-a896-4779-af4f-c04d95daa319"),
		GRUNDUEBUNG_BELASTUNG_LOWBAR_KNIEBEUGE,
		OBERKOERPER_BELASTUNG_LOWBAR_KNIEBEUGE,
		UNTERKOERPER_BELASTUNG_LOWBAR_KNIEBEUGE);
	public static final Belastung BELASTUNG_KONVENTIONELLES_KREUZHEBEN = new Belastung(
		new ID("7145b3e7-f3ef-4ab7-a739-587a28c63b90"),
		GRUNDUEBUNG_BELASTUNG_KONVENTIONELLES_KREUZHEBEN,
		OBERKOERPER_BELASTUNG_KONVENTIONELLES_KREUZHEBEN,
		UNTERKOERPER_BELASTUNG_KONVENTIONELLES_KREUZHEBEN);

	public static final GrunduebungBelastungDto GRUNDUEBUNG_BELASTUNG_DTO_WETTKAMPFBANKDRUECKEN =
		new GrunduebungBelastungDto("0.0", "1.0", "0.0");
	public static final GrunduebungBelastungDto GRUNDUEBUNG_BELASTUNG_DTO_LOWBAR_KNIEBEUGE =
		new GrunduebungBelastungDto("1.0", "0.0", "0.0");
	public static final GrunduebungBelastungDto GRUNDUEBUNG_BELASTUNG_DTO_KONVENTIONELLES_KREUZHEBEN =
		new GrunduebungBelastungDto("0.0", "0.0", "1.0");

	public static final OberkoerperBelastungDto OBERKOERPER_BELASTUNG_DTO_WETTKAMPFBANKDRUECKEN =
		new OberkoerperBelastungDto("0.7", "1.0", "0.0", "0.0", "0.0", "0.1");
	public static final OberkoerperBelastungDto OBERKOERPER_BELASTUNG_DTO_LOWBAR_KNIEBEUGE =
		new OberkoerperBelastungDto("0.0", "0.0", "0.3", "0.2", "0.0", "0.0");
	public static final OberkoerperBelastungDto OBERKOERPER_BELASTUNG_DTO_KONVENTIONELLES_KREUZHEBEN =
		new OberkoerperBelastungDto("0.0", "0.0", "0.3", "0.5", "0.0", "0.0");

	public static final UnterkoerperBelastungDto UNTERKOERPER_BELASTUNG_DTO_WETTKAMPFBANKDRUECKEN =
		new UnterkoerperBelastungDto("0.0", "0.0", "0.0");
	public static final UnterkoerperBelastungDto UNTERKOERPER_BELASTUNG_DTO_LOWBAR_KNIEBEUGE =
		new UnterkoerperBelastungDto("1.0", "1.0", "0.5");
	public static final UnterkoerperBelastungDto UNTERKOERPER_BELASTUNG_DTO_KONVENTIONELLES_KREUZHEBEN =
		new UnterkoerperBelastungDto("0.5", "0.3", "0.5");

	public static final BelastungDto BELASTUNG_DTO_WETTKAMPFBANKDRUECKEN = new BelastungDto(
		BELASTUNG_WETTKAMPFBANKDRUECKEN.getId().getWert().toString(),
		GRUNDUEBUNG_BELASTUNG_DTO_WETTKAMPFBANKDRUECKEN,
		OBERKOERPER_BELASTUNG_DTO_WETTKAMPFBANKDRUECKEN,
		UNTERKOERPER_BELASTUNG_DTO_WETTKAMPFBANKDRUECKEN);
	public static final BelastungDto BELASTUNG_DTO_LOWBAR_KNIEBEUGE = new BelastungDto(
		BELASTUNG_LOWBAR_KNIEBEUGE.getId().getWert().toString(),
		GRUNDUEBUNG_BELASTUNG_DTO_LOWBAR_KNIEBEUGE,
		OBERKOERPER_BELASTUNG_DTO_LOWBAR_KNIEBEUGE,
		UNTERKOERPER_BELASTUNG_DTO_LOWBAR_KNIEBEUGE);
	public static final BelastungDto BELASTUNG_DTO_KONVENTIONELLES_KREUZHEBEN = new BelastungDto(
		BELASTUNG_KONVENTIONELLES_KREUZHEBEN.getId().getWert().toString(),
		GRUNDUEBUNG_BELASTUNG_DTO_KONVENTIONELLES_KREUZHEBEN,
		OBERKOERPER_BELASTUNG_DTO_KONVENTIONELLES_KREUZHEBEN,
		UNTERKOERPER_BELASTUNG_DTO_KONVENTIONELLES_KREUZHEBEN);

	public static final Uebung UEBUNG_WETTKAMPFBANKDRUECKEN = new Uebung(
		new ID("628c016b-4084-4f37-af31-472f421ecbdc"),
		"Wettkampfbankdrücken (pausiert)",
		Uebungsart.GRUNDUEBUNG,
		Uebungskategorie.WETTKAMPF_BANKDRUECKEN,
		BELASTUNG_WETTKAMPFBANKDRUECKEN);
	public static final Uebung UEBUNG_LOWBAR_KNIEBEUGE = new Uebung(
		new ID("ae57e405-b464-4726-b6da-0169ab04e259"),
		"Lowbar-Kniebeuge",
		Uebungsart.GRUNDUEBUNG,
		Uebungskategorie.WETTKAMPF_KNIEBEUGE,
		BELASTUNG_LOWBAR_KNIEBEUGE);
	public static final Uebung UEBUNG_KONVENTIONELLES_KREUZHEBEN = new Uebung(
		new ID("96559ebd-48b0-4eef-970a-4ff263348145"),
		"Konventionelles Kreuzheben",
		Uebungsart.GRUNDUEBUNG,
		Uebungskategorie.WETTKAMPF_KREUZHEBEN,
		BELASTUNG_KONVENTIONELLES_KREUZHEBEN);

	public static final UebungDto UEBUNG_DTO_WETTKAMPFBANKDRUECKEN = new UebungDto(
		UEBUNG_WETTKAMPFBANKDRUECKEN.getId().getWert().toString(),
		UEBUNG_WETTKAMPFBANKDRUECKEN.getBezeichnung(),
		UEBUNG_WETTKAMPFBANKDRUECKEN.getUebungsart().name(),
		UEBUNG_WETTKAMPFBANKDRUECKEN.getUebungskategorie().name(),
		BELASTUNG_DTO_WETTKAMPFBANKDRUECKEN);
	public static final UebungDto UEBUNG_DTO_LOWBAR_KNIEBEUGE = new UebungDto(
		UEBUNG_LOWBAR_KNIEBEUGE.getId().getWert().toString(),
		UEBUNG_LOWBAR_KNIEBEUGE.getBezeichnung(),
		UEBUNG_LOWBAR_KNIEBEUGE.getUebungsart().name(),
		UEBUNG_LOWBAR_KNIEBEUGE.getUebungskategorie().name(),
		BELASTUNG_DTO_LOWBAR_KNIEBEUGE);
	public static final UebungDto UEBUNG_DTO_KONVENTIONELLES_KREUZHEBEN = new UebungDto(
		UEBUNG_KONVENTIONELLES_KREUZHEBEN.getId().getWert().toString(),
		UEBUNG_KONVENTIONELLES_KREUZHEBEN.getBezeichnung(),
		UEBUNG_KONVENTIONELLES_KREUZHEBEN.getUebungsart().name(),
		UEBUNG_KONVENTIONELLES_KREUZHEBEN.getUebungskategorie().name(),
		BELASTUNG_DTO_KONVENTIONELLES_KREUZHEBEN);

	public static final Kraftwert KRAFTWERT_WETTKAMPFBANKDRUECKEN = new Kraftwert(
		new ID("37022a20-0225-49a8-a47b-7f4555e75580"),
		LocalDate.of(2023, 1, 1),
		new BigDecimal(100),
		Wiederholungen.ONE_REP_MAX,
		UEBUNG_WETTKAMPFBANKDRUECKEN,
		BENUTZER_JUSTIN);
	public static final Kraftwert KRAFTWERT_LOWBAR_KNIEBEUGE = new Kraftwert(
		new ID("01782719-4ecb-4ebd-bdf1-4d8eb1d22701"),
		LocalDate.of(2023, 1, 1),
		new BigDecimal(150),
		Wiederholungen.ONE_REP_MAX,
		UEBUNG_LOWBAR_KNIEBEUGE,
		BENUTZER_JUSTIN);
	public static final Kraftwert KRAFTWERT_KONVENTIONELLES_KREUZHEBEN = new Kraftwert(
		new ID("6a6508bd-315d-4c09-b70a-33ad52987781"),
		LocalDate.of(2023, 1, 1),
		new BigDecimal(200),
		Wiederholungen.ONE_REP_MAX,
		UEBUNG_KONVENTIONELLES_KREUZHEBEN,
		BENUTZER_JUSTIN);

	public static final KraftwertDto KRAFTWERT_DTO_WETTKAMPFBANKDRUECKEN = new KraftwertDto(
		KRAFTWERT_WETTKAMPFBANKDRUECKEN.getId().getWert().toString(),
		KRAFTWERT_WETTKAMPFBANKDRUECKEN.getDatum().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
		"100.00",
		KRAFTWERT_WETTKAMPFBANKDRUECKEN.getWiederholungen().getWert());
	public static final KraftwertDto KRAFTWERT_DTO_LOWBAR_KNIEBEUGE = new KraftwertDto(
		KRAFTWERT_LOWBAR_KNIEBEUGE.getId().getWert().toString(),
		KRAFTWERT_LOWBAR_KNIEBEUGE.getDatum().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
		"150.00",
		KRAFTWERT_LOWBAR_KNIEBEUGE.getWiederholungen().getWert());
	public static final KraftwertDto KRAFTWERT_DTO_KONVENTIONELLES_KREUZHEBEN = new KraftwertDto(
		KRAFTWERT_KONVENTIONELLES_KREUZHEBEN.getId().getWert().toString(),
		KRAFTWERT_KONVENTIONELLES_KREUZHEBEN.getDatum().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
		"200.00",
		KRAFTWERT_KONVENTIONELLES_KREUZHEBEN.getWiederholungen().getWert());
}
