package de.justinharder.trainharder.view.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.setup.Testdaten;

class KoerpermessungDtoSollte
{
	private KoerpermessungDto sut;

	@BeforeEach
	void setup()
	{
		sut = new KoerpermessungDto(
			Testdaten.KOERPERMESSUNG_JUSTIN_ID.getId().toString(),
			LocalDate.of(2020, 7, 29).format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
			178,
			90,
			25,
			2500,
			2900);
	}

	@Test
	@DisplayName("einen AllArgsConstructor und Getter besitzen")
	void test01()
	{
		assertAll(
			() -> assertThat(sut.getPrimaerschluessel())
				.isEqualTo(Testdaten.KOERPERMESSUNG_JUSTIN_ID.getId().toString()),
			() -> assertThat(sut.getDatum())
				.isEqualTo(LocalDate.of(2020, 7, 29).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))),
			() -> assertThat(sut.getKoerpergroesse()).isEqualTo(178),
			() -> assertThat(sut.getKoerpergewicht()).isEqualTo(90),
			() -> assertThat(sut.getKoerperfettAnteil()).isEqualTo(25),
			() -> assertThat(sut.getFettfreiesKoerpergewicht())
				.isEqualTo(Testdaten.KOERPERMASSE_JUSTIN.getFettfreiesKoerpergewicht()),
			() -> assertThat(sut.getBodyMassIndex()).isEqualTo(Testdaten.KOERPERMASSE_JUSTIN.getBodyMassIndex()),
			() -> assertThat(sut.getFatFreeMassIndex()).isEqualTo(Testdaten.KOERPERMASSE_JUSTIN.getFatFreeMassIndex()),
			() -> assertThat(sut.getKalorieneinnahme()).isEqualTo(2500),
			() -> assertThat(sut.getKalorienverbrauch()).isEqualTo(2900));
	}

	@Test
	@DisplayName("einen NoArgsConstructor und Setter besitzen")
	void test02()
	{
		sut = new KoerpermessungDto()
			.setDatum(LocalDate.of(2020, 7, 29).format(DateTimeFormatter.ofPattern("dd.MM.yyyy")))
			.setKoerpergroesse(178)
			.setKoerpergewicht(90)
			.setKoerperfettAnteil(25)
			.setKalorieneinnahme(2500)
			.setKalorienverbrauch(2900);

		assertAll(
			() -> assertThat(sut.getDatum())
				.isEqualTo(LocalDate.of(2020, 7, 29).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))),
			() -> assertThat(sut.getKoerpergroesse()).isEqualTo(178),
			() -> assertThat(sut.getKoerpergewicht()).isEqualTo(90),
			() -> assertThat(sut.getKoerperfettAnteil()).isEqualTo(25),
			() -> assertThat(sut.getFettfreiesKoerpergewicht())
				.isEqualTo(Testdaten.KOERPERMASSE_JUSTIN.getFettfreiesKoerpergewicht()),
			() -> assertThat(sut.getBodyMassIndex()).isEqualTo(Testdaten.KOERPERMASSE_JUSTIN.getBodyMassIndex()),
			() -> assertThat(sut.getFatFreeMassIndex()).isEqualTo(Testdaten.KOERPERMASSE_JUSTIN.getFatFreeMassIndex()),
			() -> assertThat(sut.getKalorieneinnahme()).isEqualTo(2500),
			() -> assertThat(sut.getKalorienverbrauch()).isEqualTo(2900));
	}

	@Test
	@DisplayName("sich vergleichen")
	@SuppressWarnings("unlikely-arg-type")
	void test05()
	{
		EqualsVerifier.forClass(KoerpermessungDto.class)
			.suppress(Warning.STRICT_INHERITANCE)
			.suppress(Warning.NONFINAL_FIELDS)
			.verify();
	}

	@Test
	@DisplayName("eine toString()-Methode haben")
	void test06()
	{
		final var erwartet = "KoerpermessungDto(primaerschluessel="
			+ Testdaten.KOERPERMESSUNG_JUSTIN_ID.getId().toString()
			+ ", datum=29.07.2020, koerpergroesse=178, koerpergewicht=90.0, koerperfettAnteil=25.0, fettfreiesKoerpergewicht=67.5, bodyMassIndex=28.41, fatFreeMassIndex=21.43, kalorieneinnahme=2500, kalorienverbrauch=2900)";

		assertThat(sut).hasToString(erwartet);
	}
}
