package de.justinharder.trainharder.view.dto;

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
	private BenutzerDto sut;

	@BeforeEach
	void setup()
	{
		sut = new BenutzerDto(
			Testdaten.BENUTZER_JUSTIN_ID.getId().toString(),
			"Justin",
			"Harder",
			LocalDate.of(1998, 12, 6),
			"CLASS_5",
			"MAENNLICH",
			"FORTGESCHRITTEN",
			"GUT",
			"GUT",
			"MITTELMAESSIG",
			"NEIN",
			"GUT",
			Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN,
			List.of(Testdaten.KOERPERMESSUNG_DTO_JUSTIN));
	}

	@Test
	@DisplayName("einen AllArgsConstructor und Getter besitzen")
	void test01()
	{
		assertAll(
			() -> assertThat(sut.getPrimaerschluessel()).isEqualTo(Testdaten.BENUTZER_JUSTIN_ID.getId().toString()),
			() -> assertThat(sut.getVorname()).isEqualTo("Justin"),
			() -> assertThat(sut.getNachname()).isEqualTo("Harder"),
			() -> assertThat(sut.getGeburtsdatum()).isEqualTo(LocalDate.of(1998, 12, 6)),
			() -> assertThat(sut.getKraftlevel()).isEqualTo("CLASS_5"),
			() -> assertThat(sut.getGeschlecht()).isEqualTo("MAENNLICH"),
			() -> assertThat(sut.getErfahrung()).isEqualTo("FORTGESCHRITTEN"),
			() -> assertThat(sut.getErnaehrung()).isEqualTo("GUT"),
			() -> assertThat(sut.getSchlafqualitaet()).isEqualTo("GUT"),
			() -> assertThat(sut.getStress()).isEqualTo("MITTELMAESSIG"),
			() -> assertThat(sut.getDoping()).isEqualTo("NEIN"),
			() -> assertThat(sut.getRegenerationsfaehigkeit()).isEqualTo("GUT"),
			() -> assertThat(sut.getKoerpergewicht()).isEqualTo(90),
			() -> assertThat(sut.getAuthentifizierung()).isEqualTo(Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN));
	}

	@Test
	@DisplayName("einen NoArgsConstructor und Setter besitzen")
	void test02()
	{
		sut = new BenutzerDto()
			.setVorname("Justin")
			.setNachname("Harder")
			.setGeburtsdatum(LocalDate.of(1998, 12, 6))
			.setKraftlevel("CLASS_5")
			.setGeschlecht("MAENNLICH")
			.setErfahrung("FORTGESCHRITTEN")
			.setErnaehrung("GUT")
			.setSchlafqualitaet("GUT")
			.setStress("MITTELMAESSIG")
			.setDoping("NEIN")
			.setRegenerationsfaehigkeit("GUT");

		assertAll(
			() -> assertThat(sut.getVorname()).isEqualTo("Justin"),
			() -> assertThat(sut.getNachname()).isEqualTo("Harder"),
			() -> assertThat(sut.getGeburtsdatum()).isEqualTo(LocalDate.of(1998, 12, 6)),
			() -> assertThat(sut.getKraftlevel()).isEqualTo("CLASS_5"),
			() -> assertThat(sut.getGeschlecht()).isEqualTo("MAENNLICH"),
			() -> assertThat(sut.getErfahrung()).isEqualTo("FORTGESCHRITTEN"),
			() -> assertThat(sut.getErnaehrung()).isEqualTo("GUT"),
			() -> assertThat(sut.getSchlafqualitaet()).isEqualTo("GUT"),
			() -> assertThat(sut.getStress()).isEqualTo("MITTELMAESSIG"),
			() -> assertThat(sut.getDoping()).isEqualTo("NEIN"),
			() -> assertThat(sut.getRegenerationsfaehigkeit()).isEqualTo("GUT"));
	}

	@Test
	@DisplayName("sich vergleichen")
	void test03()
	{
		EqualsVerifier.forClass(BenutzerDto.class)
			.suppress(Warning.STRICT_INHERITANCE)
			.suppress(Warning.NONFINAL_FIELDS)
			.suppress(Warning.NULL_FIELDS)
			.verify();
	}

	@Test
	@DisplayName("eine toString()-Methode haben")
	void test04()
	{
		var erwartet = "BenutzerDto(super=Dto(primaerschluessel=" + Testdaten.BENUTZER_JUSTIN_ID.getId().toString()
			+ "), vorname=Justin, nachname=Harder, geburtsdatum=1998-12-06, kraftlevel=CLASS_5, geschlecht=MAENNLICH, erfahrung=FORTGESCHRITTEN, ernaehrung=GUT, schlafqualitaet=GUT, stress=MITTELMAESSIG, doping=NEIN, regenerationsfaehigkeit=GUT, authentifizierung=AuthentifizierungDto(super=Dto(primaerschluessel="
			+ Testdaten.AUTHENTIFIZIERUNG_JUSTIN_ID.getId().toString()
			+ "), mail=mail@justinharder.de, benutzername=harder), koerpermessungen=[KoerpermessungDto(super=Dto(primaerschluessel="
			+ Testdaten.KOERPERMESSUNG_JUSTIN_ID.getId().toString()
			+ "), datum=29.07.2020, koerpergroesse=178, koerpergewicht=90.0, koerperfettAnteil=25.0, fettfreiesKoerpergewicht=67.5, bodyMassIndex=28.41, fatFreeMassIndex=21.43, kalorieneinnahme=2500, kalorienverbrauch=2900)])";

		assertThat(sut).hasToString(erwartet);
	}

	@Test
	@DisplayName("null validieren")
	void test05()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class,
				() -> new BenutzerDto(null, "vorname", "nachname", LocalDate.now(), "kraftlevel", "geschlecht",
					"erfahrung", "ernaehrung", "schlafqualitaet", "stress", "doping", "regenerationsfaehigkeit",
					new AuthentifizierungDto(), List.of(new KoerpermessungDto()))),
			() -> assertThrows(NullPointerException.class,
				() -> new BenutzerDto("primaerschluessel", null, "nachname", LocalDate.now(), "kraftlevel",
					"geschlecht", "erfahrung", "ernaehrung", "schlafqualitaet", "stress", "doping",
					"regenerationsfaehigkeit", new AuthentifizierungDto(), List.of(new KoerpermessungDto()))),
			() -> assertThrows(NullPointerException.class,
				() -> new BenutzerDto("primaerschluessel", "vorname", null, LocalDate.now(), "kraftlevel", "geschlecht",
					"erfahrung", "ernaehrung", "schlafqualitaet", "stress", "doping", "regenerationsfaehigkeit",
					new AuthentifizierungDto(), List.of(new KoerpermessungDto()))),
			() -> assertThrows(NullPointerException.class,
				() -> new BenutzerDto("primaerschluessel", "vorname", "nachname", null, "kraftlevel", "geschlecht",
					"erfahrung", "ernaehrung", "schlafqualitaet", "stress", "doping", "regenerationsfaehigkeit",
					new AuthentifizierungDto(), List.of(new KoerpermessungDto()))),
			() -> assertThrows(NullPointerException.class,
				() -> new BenutzerDto("primaerschluessel", "vorname", "nachname", LocalDate.now(), null, "geschlecht",
					"erfahrung", "ernaehrung", "schlafqualitaet", "stress", "doping", "regenerationsfaehigkeit",
					new AuthentifizierungDto(), List.of(new KoerpermessungDto()))),
			() -> assertThrows(NullPointerException.class,
				() -> new BenutzerDto("primaerschluessel", "vorname", "nachname", LocalDate.now(), "kraftlevel", null,
					"erfahrung", "ernaehrung", "schlafqualitaet", "stress", "doping", "regenerationsfaehigkeit",
					new AuthentifizierungDto(), List.of(new KoerpermessungDto()))),
			() -> assertThrows(NullPointerException.class,
				() -> new BenutzerDto("primaerschluessel", "vorname", "nachname", LocalDate.now(), "kraftlevel",
					"geschlecht", null, "ernaehrung", "schlafqualitaet", "stress", "doping", "regenerationsfaehigkeit",
					new AuthentifizierungDto(), List.of(new KoerpermessungDto()))),
			() -> assertThrows(NullPointerException.class,
				() -> new BenutzerDto("primaerschluessel", "vorname", "nachname", LocalDate.now(), "kraftlevel",
					"geschlecht", "erfahrung", null, "schlafqualitaet", "stress", "doping", "regenerationsfaehigkeit",
					new AuthentifizierungDto(), List.of(new KoerpermessungDto()))),
			() -> assertThrows(NullPointerException.class,
				() -> new BenutzerDto("primaerschluessel", "vorname", "nachname", LocalDate.now(), "kraftlevel",
					"geschlecht", "erfahrung", "ernaehrung", null, "stress", "doping", "regenerationsfaehigkeit",
					new AuthentifizierungDto(), List.of(new KoerpermessungDto()))),
			() -> assertThrows(NullPointerException.class,
				() -> new BenutzerDto("primaerschluessel", "vorname", "nachname", LocalDate.now(), "kraftlevel",
					"geschlecht", "erfahrung", "ernaehrung", "schlafqualitaet", null, "doping",
					"regenerationsfaehigkeit", new AuthentifizierungDto(), List.of(new KoerpermessungDto()))),
			() -> assertThrows(NullPointerException.class,
				() -> new BenutzerDto("primaerschluessel", "vorname", "nachname", LocalDate.now(), "kraftlevel",
					"geschlecht", "erfahrung", "ernaehrung", "schlafqualitaet", "stress", null,
					"regenerationsfaehigkeit", new AuthentifizierungDto(), List.of(new KoerpermessungDto()))),
			() -> assertThrows(NullPointerException.class,
				() -> new BenutzerDto("primaerschluessel", "vorname", "nachname", LocalDate.now(), "kraftlevel",
					"geschlecht", "erfahrung", "ernaehrung", "schlafqualitaet", "stress", "doping", null,
					new AuthentifizierungDto(), List.of(new KoerpermessungDto()))),
			() -> assertThrows(NullPointerException.class,
				() -> new BenutzerDto("primaerschluessel", "vorname", "nachname", LocalDate.now(), "kraftlevel",
					"geschlecht", "erfahrung", "ernaehrung", "schlafqualitaet", "stress", "doping",
					"regenerationsfaehigkeit", null, List.of(new KoerpermessungDto()))),
			() -> assertThrows(NullPointerException.class,
				() -> new BenutzerDto("primaerschluessel", "vorname", "nachname", LocalDate.now(), "kraftlevel",
					"geschlecht", "erfahrung", "ernaehrung", "schlafqualitaet", "stress", "doping",
					"regenerationsfaehigkeit", new AuthentifizierungDto(), null)),
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
