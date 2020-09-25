package de.justinharder.trainharder.view.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.setup.Testdaten;

class KraftwertDtoSollte
{
	private KraftwertDto sut;

	private String primaerschluessel;

	@BeforeEach
	void setup()
	{
		primaerschluessel = new Primaerschluessel().getId().toString();
		sut = new KraftwertDto(primaerschluessel, 100, 75.0, "22.08.2020", "1RM");
	}

	@Test
	@DisplayName("einen AllArgsConstructor und Getter besitzen")
	void test01()
	{
		assertAll(
			() -> assertThat(sut.getPrimaerschluessel()).isEqualTo(primaerschluessel),
			() -> assertThat(sut.getMaximum()).isEqualTo(100),
			() -> assertThat(sut.getKoerpergewicht()).isEqualTo(75.0),
			() -> assertThat(sut.getDatum()).isEqualTo("22.08.2020"),
			() -> assertThat(sut.getWiederholungen()).isEqualTo("1RM"));
	}

	@Test
	@DisplayName("einen NoArgsConstructor und Setter besitzen")
	void test02()
	{
		sut = new KraftwertDto()
			.setPrimaerschluessel(primaerschluessel)
			.setMaximum(100)
			.setKoerpergewicht(75.0)
			.setDatum("22.08.2020")
			.setWiederholungen("1RM");

		assertAll(
			() -> assertThat(sut.getPrimaerschluessel()).isEqualTo(primaerschluessel),
			() -> assertThat(sut.getMaximum()).isEqualTo(100),
			() -> assertThat(sut.getKoerpergewicht()).isEqualTo(75.0),
			() -> assertThat(sut.getDatum()).isEqualTo("22.08.2020"),
			() -> assertThat(sut.getWiederholungen()).isEqualTo("1RM"));
	}

	@Test
	@DisplayName("sich vergleichen")
	@SuppressWarnings("unlikely-arg-type")
	void test05()
	{
		EqualsVerifier.forClass(KraftwertDto.class)
			.suppress(Warning.STRICT_INHERITANCE)
			.suppress(Warning.NONFINAL_FIELDS)
			.verify();
	}

	@Test
	@DisplayName("eine toString()-Methode haben")
	void test06()
	{
		final var erwartet = "KraftwertDto(primaerschluessel=" + primaerschluessel
			+ ", maximum=100, koerpergewicht=75.0, datum=22.08.2020, wiederholungen=1RM)";

		assertThat(sut).hasToString(erwartet);
	}
}
