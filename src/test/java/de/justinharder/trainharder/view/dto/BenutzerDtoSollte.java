package de.justinharder.trainharder.view.dto;

import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.setup.Testdaten;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BenutzerDtoSollte
{
	private static final String PRIMAERSCHLUESSEL = new Primaerschluessel().getId().toString();
	private static final String VORNAME = "Justin";
	private static final String NACHNAME = "Harder";
	private static final LocalDate GEBURTSDATUM = LocalDate.of(1998, 12, 6);
	private static final String KRAFTLEVEL = "CLASS_5";
	private static final String GESCHLECHT = "MAENNLICH";
	private static final String ERFAHRUNG = "FORTGESCHRITTEN";
	private static final String ERNAEHRUNG = "GUT";
	private static final String SCHLAFQUALITAET = "GUT";
	private static final String STRESS = "MITTELMAESSIG";
	private static final String DOPING = "NEIN";
	private static final String REGENERATIONSFAEHIGKEIT = "GUT";

	private BenutzerDto sut;

	@BeforeEach
	void setup()
	{
		sut = new BenutzerDto(
			PRIMAERSCHLUESSEL,
			VORNAME,
			NACHNAME,
			GEBURTSDATUM,
			KRAFTLEVEL,
			GESCHLECHT,
			ERFAHRUNG,
			ERNAEHRUNG,
			SCHLAFQUALITAET,
			STRESS,
			DOPING,
			REGENERATIONSFAEHIGKEIT,
			Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN,
			List.of(Testdaten.KOERPERMESSUNG_DTO_JUSTIN));
	}

	@Test
	@DisplayName("einen NoArgsConstructor und Setter besitzen")
	void test01()
	{
		sut = new BenutzerDto()
			.setPrimaerschluessel(PRIMAERSCHLUESSEL)
			.setVorname(VORNAME)
			.setNachname(NACHNAME)
			.setGeburtsdatum(GEBURTSDATUM)
			.setKraftlevel(KRAFTLEVEL)
			.setGeschlecht(GESCHLECHT)
			.setErfahrung(ERFAHRUNG)
			.setErnaehrung(ERNAEHRUNG)
			.setSchlafqualitaet(SCHLAFQUALITAET)
			.setStress(STRESS)
			.setDoping(DOPING)
			.setRegenerationsfaehigkeit(REGENERATIONSFAEHIGKEIT)
			.setAuthentifizierung(Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN);

		assertAll(
			() -> assertThat(sut.getVorname()).isEqualTo(VORNAME),
			() -> assertThat(sut.getNachname()).isEqualTo(NACHNAME),
			() -> assertThat(sut.getGeburtsdatum()).isEqualTo(GEBURTSDATUM),
			() -> assertThat(sut.getKraftlevel()).isEqualTo(KRAFTLEVEL),
			() -> assertThat(sut.getGeschlecht()).isEqualTo(GESCHLECHT),
			() -> assertThat(sut.getErfahrung()).isEqualTo(ERFAHRUNG),
			() -> assertThat(sut.getErnaehrung()).isEqualTo(ERNAEHRUNG),
			() -> assertThat(sut.getSchlafqualitaet()).isEqualTo(SCHLAFQUALITAET),
			() -> assertThat(sut.getStress()).isEqualTo(STRESS),
			() -> assertThat(sut.getDoping()).isEqualTo(DOPING),
			() -> assertThat(sut.getRegenerationsfaehigkeit()).isEqualTo(REGENERATIONSFAEHIGKEIT),
			() -> assertThat(sut.getAuthentifizierung()).isEqualTo(Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN));
	}

	@Test
	@DisplayName("sich vergleichen")
	void test02()
	{
		EqualsVerifier.forClass(BenutzerDto.class)
			.suppress(Warning.STRICT_INHERITANCE)
			.suppress(Warning.NONFINAL_FIELDS)
			.suppress(Warning.NULL_FIELDS)
			.verify();
	}

	@Test
	@DisplayName("eine toString()-Methode haben")
	void test03()
	{
		assertThat(sut).hasToString(
			"BenutzerDto(super=Dto(primaerschluessel=" + PRIMAERSCHLUESSEL
				+ "), vorname=Justin, nachname=Harder, geburtsdatum=1998-12-06, kraftlevel=CLASS_5, geschlecht=MAENNLICH, erfahrung=FORTGESCHRITTEN, ernaehrung=GUT, schlafqualitaet=GUT, stress=MITTELMAESSIG, doping=NEIN, regenerationsfaehigkeit=GUT, authentifizierung=AuthentifizierungDto(super=Dto(primaerschluessel="
				+ Testdaten.AUTHENTIFIZIERUNG_JUSTIN_ID.getId().toString()
				+ "), mail=mail@justinharder.de, benutzername=harder), koerpermessungen=[KoerpermessungDto(super=Dto(primaerschluessel="
				+ Testdaten.KOERPERMESSUNG_JUSTIN_ID.getId().toString()
				+ "), datum=29.07.2020, koerpermasse=KoerpermasseDto(koerpergroesse=178, koerpergewicht=90.00, koerperfettAnteil=25.0, fettfreiesKoerpergewicht=67.50, bodyMassIndex=28.4, fatFreeMassIndex=21.4), kalorieneinnahme=2500, kalorienverbrauch=2900)])");
	}

	@Test
	@DisplayName("null validieren (Konstruktor)")
	void test04()
	{
		var koerpermessungDtos = List.of(new KoerpermessungDto());
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> new BenutzerDto(null, VORNAME, NACHNAME,
				GEBURTSDATUM, KRAFTLEVEL, GESCHLECHT, ERFAHRUNG, ERNAEHRUNG, SCHLAFQUALITAET, STRESS, DOPING,
				REGENERATIONSFAEHIGKEIT, Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN, koerpermessungDtos)),
			() -> assertThrows(NullPointerException.class, () -> new BenutzerDto(PRIMAERSCHLUESSEL, null, NACHNAME,
				GEBURTSDATUM, KRAFTLEVEL, GESCHLECHT, ERFAHRUNG, ERNAEHRUNG, SCHLAFQUALITAET, STRESS, DOPING,
				REGENERATIONSFAEHIGKEIT, Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN, koerpermessungDtos)),
			() -> assertThrows(NullPointerException.class, () -> new BenutzerDto(PRIMAERSCHLUESSEL, VORNAME, null,
				GEBURTSDATUM, KRAFTLEVEL, GESCHLECHT, ERFAHRUNG, ERNAEHRUNG, SCHLAFQUALITAET, STRESS, DOPING,
				REGENERATIONSFAEHIGKEIT, Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN, koerpermessungDtos)),
			() -> assertThrows(NullPointerException.class, () -> new BenutzerDto(PRIMAERSCHLUESSEL, VORNAME, NACHNAME,
				null, KRAFTLEVEL, GESCHLECHT, ERFAHRUNG, ERNAEHRUNG, SCHLAFQUALITAET, STRESS, DOPING,
				REGENERATIONSFAEHIGKEIT, Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN, koerpermessungDtos)),
			() -> assertThrows(NullPointerException.class, () -> new BenutzerDto(PRIMAERSCHLUESSEL, VORNAME, NACHNAME,
				GEBURTSDATUM, null, GESCHLECHT, ERFAHRUNG, ERNAEHRUNG, SCHLAFQUALITAET, STRESS, DOPING,
				REGENERATIONSFAEHIGKEIT, Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN, koerpermessungDtos)),
			() -> assertThrows(NullPointerException.class, () -> new BenutzerDto(PRIMAERSCHLUESSEL, VORNAME, NACHNAME,
				GEBURTSDATUM, KRAFTLEVEL, null, ERFAHRUNG, ERNAEHRUNG, SCHLAFQUALITAET, STRESS, DOPING,
				REGENERATIONSFAEHIGKEIT, Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN, koerpermessungDtos)),
			() -> assertThrows(NullPointerException.class, () -> new BenutzerDto(PRIMAERSCHLUESSEL, VORNAME, NACHNAME,
				GEBURTSDATUM, KRAFTLEVEL, GESCHLECHT, null, ERNAEHRUNG, SCHLAFQUALITAET, STRESS, DOPING,
				REGENERATIONSFAEHIGKEIT, Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN, koerpermessungDtos)),
			() -> assertThrows(NullPointerException.class, () -> new BenutzerDto(PRIMAERSCHLUESSEL, VORNAME, NACHNAME,
				GEBURTSDATUM, KRAFTLEVEL, GESCHLECHT, ERFAHRUNG, null, SCHLAFQUALITAET, STRESS, DOPING,
				REGENERATIONSFAEHIGKEIT, Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN, koerpermessungDtos)),
			() -> assertThrows(NullPointerException.class, () -> new BenutzerDto(PRIMAERSCHLUESSEL, VORNAME, NACHNAME,
				GEBURTSDATUM, KRAFTLEVEL, GESCHLECHT, ERFAHRUNG, ERNAEHRUNG, null, STRESS, DOPING,
				REGENERATIONSFAEHIGKEIT, Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN, koerpermessungDtos)),
			() -> assertThrows(NullPointerException.class, () -> new BenutzerDto(PRIMAERSCHLUESSEL, VORNAME, NACHNAME,
				GEBURTSDATUM, KRAFTLEVEL, GESCHLECHT, ERFAHRUNG, ERNAEHRUNG, SCHLAFQUALITAET, null, DOPING,
				REGENERATIONSFAEHIGKEIT, Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN, koerpermessungDtos)),
			() -> assertThrows(NullPointerException.class, () -> new BenutzerDto(PRIMAERSCHLUESSEL, VORNAME, NACHNAME,
				GEBURTSDATUM, KRAFTLEVEL, GESCHLECHT, ERFAHRUNG, ERNAEHRUNG, SCHLAFQUALITAET, STRESS, null,
				REGENERATIONSFAEHIGKEIT, Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN, koerpermessungDtos)),
			() -> assertThrows(NullPointerException.class, () -> new BenutzerDto(PRIMAERSCHLUESSEL, VORNAME, NACHNAME,
				GEBURTSDATUM, KRAFTLEVEL, GESCHLECHT, ERFAHRUNG, ERNAEHRUNG, SCHLAFQUALITAET, STRESS, DOPING,
				null, Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN, koerpermessungDtos)),
			() -> assertThrows(NullPointerException.class, () -> new BenutzerDto(PRIMAERSCHLUESSEL, VORNAME, NACHNAME,
				GEBURTSDATUM, KRAFTLEVEL, GESCHLECHT, ERFAHRUNG, ERNAEHRUNG, SCHLAFQUALITAET, STRESS, DOPING,
				REGENERATIONSFAEHIGKEIT, null, koerpermessungDtos)),
			() -> assertThrows(NullPointerException.class, () -> new BenutzerDto(PRIMAERSCHLUESSEL, VORNAME, NACHNAME,
				GEBURTSDATUM, KRAFTLEVEL, GESCHLECHT, ERFAHRUNG, ERNAEHRUNG, SCHLAFQUALITAET, STRESS, DOPING,
				REGENERATIONSFAEHIGKEIT, Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN, null)));
	}

	@Test
	@DisplayName("null validieren (Setter)")
	void test05()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> sut.setPrimaerschluessel(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setVorname(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setNachname(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setGeburtsdatum(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setKraftlevel(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setGeschlecht(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setErfahrung(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setErnaehrung(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setSchlafqualitaet(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setStress(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setDoping(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setRegenerationsfaehigkeit(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setAuthentifizierung(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.fuegeKoerpermessungHinzu(null)));
	}
}
