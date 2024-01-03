package de.justinharder.trainharder.domain.services.dto;

import de.justinharder.trainharder.domain.model.embeddables.Primaerschluessel;
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
	private static final NameDto NAME_DTO = new NameDto("Justin", "Harder");
	private static final LocalDate GEBURTSDATUM = LocalDate.of(1998, 12, 6);
	private static final BenutzerangabeDto
		BENUTZERANGABE_DTO = new BenutzerangabeDto("MAENNLICH", "FORTGESCHRITTEN", "GUT", "GUT", "MITTELMAESSIG", "NEIN", "GUT")
		.setKraftlevel("CLASS_5");

	private BenutzerDto sut;

	@BeforeEach
	void setup()
	{
		sut = new BenutzerDto(PRIMAERSCHLUESSEL, NAME_DTO, GEBURTSDATUM, BENUTZERANGABE_DTO, Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN, List.of(Testdaten.KOERPERMESSUNG_DTO_JUSTIN));
	}

	@Test
	@DisplayName("einen NoArgsConstructor und Setter besitzen")
	void test01()
	{
		sut = new BenutzerDto()
			.setPrimaerschluessel(PRIMAERSCHLUESSEL)
			.setName(NAME_DTO)
			.setGeburtsdatum(GEBURTSDATUM)
			.setBenutzerangabe(BENUTZERANGABE_DTO)
			.setAuthentifizierung(Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN)
			.fuegeKoerpermessungHinzu(Testdaten.KOERPERMESSUNG_DTO_JUSTIN);

		assertAll(
			() -> assertThat(sut.getPrimaerschluessel()).isEqualTo(PRIMAERSCHLUESSEL),
			() -> assertThat(sut.getName()).isEqualTo(NAME_DTO),
			() -> assertThat(sut.getGeburtsdatum()).isEqualTo(GEBURTSDATUM),
			() -> assertThat(sut.getBenutzerangabe()).isEqualTo(BENUTZERANGABE_DTO),
			() -> assertThat(sut.getKoerpergewicht()).isEqualTo(Testdaten.KOERPERMESSUNG_DTO_JUSTIN.getKoerpermasse().getKoerpergewicht()),
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
			"BenutzerDto(super=EntitaetDto(primaerschluessel=" + PRIMAERSCHLUESSEL
				+ "), name=NameDto(vorname=Justin, nachname=Harder), geburtsdatum=1998-12-06, benutzerangabe=BenutzerangabeDto(kraftlevel=CLASS_5, geschlecht=MAENNLICH, erfahrung=FORTGESCHRITTEN, ernaehrung=GUT, schlafqualitaet=GUT, stress=MITTELMAESSIG, doping=NEIN, regenerationsfaehigkeit=GUT), authentifizierung=AuthentifizierungDto(super=EntitaetDto(primaerschluessel="
				+ new Primaerschluessel().getId().toString()
				+ "), mail=mail@justinharder.de, benutzername=harder), koerpermessungen=[KoerpermessungDto(super=EntitaetDto(primaerschluessel="
				+ new Primaerschluessel().getId().toString()
				+ "), datum=29.07.2020, koerpermasse=KoerpermasseDto(koerpergroesse=178, koerpergewicht=90.00, koerperfettAnteil=25.0, fettfreiesKoerpergewicht=67.50, bodyMassIndex=28.4, fatFreeMassIndex=21.4), kalorieneinnahme=2500, kalorienverbrauch=2900)])");
	}

	@Test
	@DisplayName("null validieren")
	void test04()
	{
		var koerpermessungDtos = List.of(new KoerpermessungDto());
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> new BenutzerDto(null, NAME_DTO, GEBURTSDATUM, BENUTZERANGABE_DTO, Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN, koerpermessungDtos)),
			() -> assertThrows(NullPointerException.class, () -> new BenutzerDto(PRIMAERSCHLUESSEL, null, GEBURTSDATUM, BENUTZERANGABE_DTO, Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN,
				koerpermessungDtos)),
			() -> assertThrows(NullPointerException.class, () -> new BenutzerDto(PRIMAERSCHLUESSEL, NAME_DTO, null, BENUTZERANGABE_DTO, Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN, koerpermessungDtos)),
			() -> assertThrows(NullPointerException.class, () -> new BenutzerDto(PRIMAERSCHLUESSEL, NAME_DTO, GEBURTSDATUM, null, Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN, koerpermessungDtos)),
			() -> assertThrows(NullPointerException.class, () -> new BenutzerDto(PRIMAERSCHLUESSEL, NAME_DTO, GEBURTSDATUM, BENUTZERANGABE_DTO, null, koerpermessungDtos)),
			() -> assertThrows(NullPointerException.class, () -> new BenutzerDto(PRIMAERSCHLUESSEL, NAME_DTO, GEBURTSDATUM, BENUTZERANGABE_DTO, Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN, null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setPrimaerschluessel(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setName(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setGeburtsdatum(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setBenutzerangabe(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setAuthentifizierung(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.fuegeKoerpermessungHinzu(null)));
	}
}
