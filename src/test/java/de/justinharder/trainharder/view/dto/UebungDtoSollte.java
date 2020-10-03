package de.justinharder.trainharder.view.dto;

import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class UebungDtoSollte
{
	private UebungDto sut;

	private String primaerschluessel;
	private String belastungsfaktorPrimaerschluessel;
	private BelastungsfaktorDto belastungsfaktor;

	@BeforeEach
	void setup()
	{
		primaerschluessel = new Primaerschluessel().getId().toString();
		belastungsfaktorPrimaerschluessel = new Primaerschluessel().getId().toString();
		belastungsfaktor = new BelastungsfaktorDto(belastungsfaktorPrimaerschluessel,
			0.0, 1.0, 0.0, 0.7, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.1);
		sut = new UebungDto(
			primaerschluessel,
			"Wettkampfbankdrücken (pausiert)",
			"Grundübung",
			"Wettkampf Bankdrücken",
			belastungsfaktor);
	}

	@Test
	@DisplayName("einen AllArgsConstructor und Getter besitzen")
	void test01()
	{
		assertAll(
			() -> assertThat(sut.getPrimaerschluessel()).isEqualTo(primaerschluessel),
			() -> assertThat(sut.getName()).isEqualTo("Wettkampfbankdrücken (pausiert)"),
			() -> assertThat(sut.getUebungsart()).isEqualTo("Grundübung"),
			() -> assertThat(sut.getUebungskategorie()).isEqualTo("Wettkampf Bankdrücken"),
			() -> assertThat(sut.getBelastungsfaktor()).isEqualTo(belastungsfaktor));
	}

	@Test
	@DisplayName("einen NoArgsConstructor und Setter besitzen")
	void test02()
	{
		sut = new UebungDto()
			.setPrimaerschluessel(primaerschluessel)
			.setName("Wettkampfbankdrücken (pausiert)")
			.setUebungsart("Grundübung")
			.setUebungskategorie("Wettkampf Bankdrücken")
			.setBelastungsfaktor(belastungsfaktor);

		assertAll(
			() -> assertThat(sut.getPrimaerschluessel()).isEqualTo(primaerschluessel),
			() -> assertThat(sut.getName()).isEqualTo("Wettkampfbankdrücken (pausiert)"),
			() -> assertThat(sut.getUebungsart()).isEqualTo("Grundübung"),
			() -> assertThat(sut.getUebungskategorie()).isEqualTo("Wettkampf Bankdrücken"),
			() -> assertThat(sut.getBelastungsfaktor()).isEqualTo(belastungsfaktor));
	}

	@Test
	@DisplayName("sich vergleichen")
	void test05()
	{
		EqualsVerifier.forClass(UebungDto.class)
			.suppress(Warning.STRICT_INHERITANCE)
			.suppress(Warning.NONFINAL_FIELDS)
			.suppress(Warning.NULL_FIELDS)
			.verify();
	}

	@Test
	@DisplayName("eine toString()-Methode haben")
	void test06()
	{
		final var erwartet = "UebungDto(primaerschluessel=" + primaerschluessel
			+ ", name=Wettkampfbankdrücken (pausiert), uebungsart=Grundübung, uebungskategorie=Wettkampf Bankdrücken, belastungsfaktor=BelastungsfaktorDto(primaerschluessel="
			+ belastungsfaktorPrimaerschluessel
			+ ", squat=0.0, benchpress=1.0, deadlift=0.0, triceps=0.7, chest=1.0, core=0.0, back=0.0, biceps=0.0, glutes=0.0, quads=0.0, hamstrings=0.0, shoulder=0.1))";

		assertThat(sut).hasToString(erwartet);
	}
}
