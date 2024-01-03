package de.justinharder.trainharder.domain.services.dto;

import de.justinharder.trainharder.domain.model.embeddables.ID;
import de.justinharder.trainharder.setup.Testdaten;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UebungDtoSollte
{
	private static final String ID = new ID().getId().toString();
	private static final String NAME = "Wettkampfbankdrücken (pausiert)";
	private static final String UEBUNGSART = "GRUNDUEBUNG";
	private static final String UEBUNGSKATEGORIE = "WETTKAMPF_BANKDRUECKEN";

	private UebungDto sut;

	@BeforeEach
	void setup()
	{
		sut = new UebungDto(ID, NAME, UEBUNGSART, UEBUNGSKATEGORIE, Testdaten.BELASTUNGSFAKTOR_DTO_WETTKAMPFBANKDRUECKEN);
	}

	@Test
	@DisplayName("einen NoArgsConstructor und Setter besitzen")
	void test01()
	{
		sut = new UebungDto()
			.setId(ID)
			.setName(NAME)
			.setUebungsart(UEBUNGSART)
			.setUebungskategorie(UEBUNGSKATEGORIE)
			.setBelastungsfaktor(Testdaten.BELASTUNGSFAKTOR_DTO_WETTKAMPFBANKDRUECKEN);

		assertAll(
			() -> assertThat(sut.getId()).isEqualTo(ID),
			() -> assertThat(sut.getName()).isEqualTo(NAME),
			() -> assertThat(sut.getUebungsart()).isEqualTo(UEBUNGSART),
			() -> assertThat(sut.getUebungskategorie()).isEqualTo(UEBUNGSKATEGORIE),
			() -> assertThat(sut.getBelastungsfaktor()).isEqualTo(Testdaten.BELASTUNGSFAKTOR_DTO_WETTKAMPFBANKDRUECKEN));
	}

	@Test
	@DisplayName("sich vergleichen")
	void test02()
	{
		EqualsVerifier.forClass(UebungDto.class)
			.suppress(Warning.STRICT_INHERITANCE)
			.suppress(Warning.NONFINAL_FIELDS)
			.suppress(Warning.NULL_FIELDS)
			.verify();
	}

	@Test
	@DisplayName("eine toString()-Methode haben")
	void test03()
	{
		assertThat(sut).hasToString("UebungDto(super=EntitaetDto(id=" + ID
			+ "), name=Wettkampfbankdrücken (pausiert), uebungsart=GRUNDUEBUNG, uebungskategorie=WETTKAMPF_BANKDRUECKEN, belastungsfaktor=BelastungDto(super=EntitaetDto(id="
			+ Testdaten.BELASTUNGSFAKTOR_DTO_WETTKAMPFBANKDRUECKEN.getId()
			+ "), grunduebungBelastung=GrunduebungBelastungDto(squat=0.0, benchpress=1.0, deadlift=0.0), oberkoerperBelastung=OberkoerperBelastungDto(triceps=0.7, chest=1.0, core=0.0, back=0.0, biceps=0.0, shoulder=0.1), unterkoerperBelastung=UnterkoerperBelastungDto(glutes=0.0, quads=0.0, hamstrings=0.0)))");
	}

	@Test
	@DisplayName("null validieren")
	void test04()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> new UebungDto(null, NAME, UEBUNGSART, UEBUNGSKATEGORIE, Testdaten.BELASTUNGSFAKTOR_DTO_WETTKAMPFBANKDRUECKEN)),
			() -> assertThrows(NullPointerException.class, () -> new UebungDto(ID, null, UEBUNGSART, UEBUNGSKATEGORIE, Testdaten.BELASTUNGSFAKTOR_DTO_WETTKAMPFBANKDRUECKEN)),
			() -> assertThrows(NullPointerException.class, () -> new UebungDto(ID, NAME, null, UEBUNGSKATEGORIE, Testdaten.BELASTUNGSFAKTOR_DTO_WETTKAMPFBANKDRUECKEN)),
			() -> assertThrows(NullPointerException.class, () -> new UebungDto(ID, NAME, UEBUNGSART, null, Testdaten.BELASTUNGSFAKTOR_DTO_WETTKAMPFBANKDRUECKEN)),
			() -> assertThrows(NullPointerException.class, () -> new UebungDto(ID, NAME, UEBUNGSART, UEBUNGSKATEGORIE, null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setId(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setName(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setUebungsart(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setUebungskategorie(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setBelastungsfaktor(null)));
	}
}
