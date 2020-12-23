package de.justinharder.trainharder.view.dto;

import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
			() -> assertThat(sut.getGewicht()).isEqualTo(100),
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
			.setGewicht(100)
			.setKoerpergewicht(75.0)
			.setDatum("22.08.2020")
			.setWiederholungen("1RM");

		assertAll(
			() -> assertThat(sut.getPrimaerschluessel()).isEqualTo(primaerschluessel),
			() -> assertThat(sut.getGewicht()).isEqualTo(100),
			() -> assertThat(sut.getKoerpergewicht()).isEqualTo(75.0),
			() -> assertThat(sut.getDatum()).isEqualTo("22.08.2020"),
			() -> assertThat(sut.getWiederholungen()).isEqualTo("1RM"));
	}

	@Test
	@DisplayName("sich vergleichen")
	void test03()
	{
		EqualsVerifier.forClass(KraftwertDto.class)
			.suppress(Warning.STRICT_INHERITANCE)
			.suppress(Warning.NONFINAL_FIELDS)
			.suppress(Warning.NULL_FIELDS)
			.verify();
	}

	@Test
	@DisplayName("eine toString()-Methode haben")
	void test04()
	{
		var erwartet =
			"KraftwertDto(super=Dto(primaerschluessel=" + primaerschluessel + "), gewicht=100.0, koerpergewicht=75.0, datum=22.08.2020, wiederholungen=1RM)";

		assertThat(sut).hasToString(erwartet);
	}

	@Test
	@DisplayName("null validieren")
	void test05()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> new KraftwertDto(null,100,80,"datum","wiederholungen")),
			() -> assertThrows(NullPointerException.class, () -> new KraftwertDto("primaerschluessel",100,80,null,"wiederholungen")),
			() -> assertThrows(NullPointerException.class, () -> new KraftwertDto("primaerschluessel",100,80,"datum",null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setPrimaerschluessel(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setDatum(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setWiederholungen(null)));
	}
}
