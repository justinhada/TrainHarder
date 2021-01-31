package de.justinharder.trainharder.view.dto;

import de.justinharder.trainharder.setup.Testdaten;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class KoerpermessungDtoSollte
{
	private static final String PRIMAERSCHLUESSEL = Testdaten.KOERPERMESSUNG_JUSTIN_ID.getId().toString();
	private static final String DATUM = LocalDate.of(2020, 7, 29).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
	private static final KoerpermasseDto KOERPERMASSE_DTO = new KoerpermasseDto("178", "90.00", "20.0", "72.00", "28.4", "22.8");
	private static final int KALORIENEINNAHME = 2500;
	private static final int KALORIENVERBRAUCH = 2800;

	private KoerpermessungDto sut;

	@BeforeEach
	void setup()
	{
		sut = new KoerpermessungDto(PRIMAERSCHLUESSEL, DATUM, KOERPERMASSE_DTO, KALORIENEINNAHME, KALORIENVERBRAUCH);
	}

	@Test
	@DisplayName("einen NoArgsConstructor und Setter besitzen")
	void test01()
	{
		sut = new KoerpermessungDto()
			.setPrimaerschluessel(PRIMAERSCHLUESSEL)
			.setDatum(DATUM)
			.setKoerpermasse(KOERPERMASSE_DTO)
			.setKalorieneinnahme(KALORIENEINNAHME)
			.setKalorienverbrauch(KALORIENVERBRAUCH);

		assertAll(
			() -> assertThat(sut.getPrimaerschluessel()).isEqualTo(PRIMAERSCHLUESSEL),
			() -> assertThat(sut.getDatum()).isEqualTo(DATUM),
			() -> assertThat(sut.getKoerpermasse()).isEqualTo(KOERPERMASSE_DTO),
			() -> assertThat(sut.getKalorieneinnahme()).isEqualTo(KALORIENEINNAHME),
			() -> assertThat(sut.getKalorienverbrauch()).isEqualTo(KALORIENVERBRAUCH));
	}

	@Test
	@DisplayName("sich vergleichen")
	void test02()
	{
		EqualsVerifier.forClass(KoerpermessungDto.class)
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
			"KoerpermessungDto(super=EntitaetDto(primaerschluessel=" + PRIMAERSCHLUESSEL + "), datum=29.07.2020, koerpermasse=KoerpermasseDto(koerpergroesse=178, koerpergewicht=90.00, koerperfettAnteil=20.0, fettfreiesKoerpergewicht=72.00, bodyMassIndex=28.4, fatFreeMassIndex=22.8), kalorieneinnahme=2500, kalorienverbrauch=2800)");
	}

	@Test
	@DisplayName("null validieren")
	void test04()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> new KoerpermessungDto(null, DATUM, KOERPERMASSE_DTO, KALORIENEINNAHME, KALORIENVERBRAUCH)),
			() -> assertThrows(NullPointerException.class, () -> new KoerpermessungDto(PRIMAERSCHLUESSEL, null, KOERPERMASSE_DTO, KALORIENEINNAHME, KALORIENVERBRAUCH)),
			() -> assertThrows(NullPointerException.class, () -> new KoerpermessungDto(PRIMAERSCHLUESSEL, DATUM, null, KALORIENEINNAHME, KALORIENVERBRAUCH)),
			() -> assertThrows(NullPointerException.class, () -> sut.setPrimaerschluessel(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setDatum(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setKoerpermasse(null)));
	}
}