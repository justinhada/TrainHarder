package de.justinharder.old.domain.services.dto;

import de.justinharder.base.domain.model.attribute.ID;
import de.justinharder.old.domain.services.dto.BelastungDto;
import de.justinharder.old.domain.services.dto.GrunduebungBelastungDto;
import de.justinharder.old.domain.services.dto.OberkoerperBelastungDto;
import de.justinharder.old.domain.services.dto.UnterkoerperBelastungDto;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("BelastungDto sollte")
class BelastungDtoTest
{
	private static final String ID = new ID().getWert().toString();
	private static final GrunduebungBelastungDto
		GRUNDUEBUNG_BELASTUNG_DTO = new GrunduebungBelastungDto("1.0", "0.0", "0.0");
	private static final OberkoerperBelastungDto
		OBERKOERPER_BELASTUNG_DTO = new OberkoerperBelastungDto("0.7", "1.0", "0.0", "0.0", "0.0", "0.1");
	private static final UnterkoerperBelastungDto
		UNTERKOERPER_BELASTUNG_DTO = new UnterkoerperBelastungDto("1.0", "1.0", "0.5");

	private BelastungDto sut;

	@BeforeEach
	void setup()
	{
		sut = new BelastungDto(ID, GRUNDUEBUNG_BELASTUNG_DTO, OBERKOERPER_BELASTUNG_DTO, UNTERKOERPER_BELASTUNG_DTO);
	}

	@Test
	@DisplayName("einen NoArgsConstructor und Setter besitzen")
	void test01()
	{
		sut = new BelastungDto()
			.setId(ID)
			.setGrunduebungBelastung(GRUNDUEBUNG_BELASTUNG_DTO)
			.setOberkoerperBelastung(OBERKOERPER_BELASTUNG_DTO)
			.setUnterkoerperBelastung(UNTERKOERPER_BELASTUNG_DTO);

		assertAll(
			() -> assertThat(sut.getId()).isEqualTo(ID),
			() -> assertThat(sut.getGrunduebungBelastung()).isEqualTo(GRUNDUEBUNG_BELASTUNG_DTO),
			() -> assertThat(sut.getOberkoerperBelastung()).isEqualTo(OBERKOERPER_BELASTUNG_DTO),
			() -> assertThat(sut.getUnterkoerperBelastung()).isEqualTo(UNTERKOERPER_BELASTUNG_DTO));
	}

	@Test
	@DisplayName("sich vergleichen")
	void test02()
	{
		EqualsVerifier.forClass(BelastungDto.class)
			.suppress(Warning.STRICT_INHERITANCE)
			.suppress(Warning.NONFINAL_FIELDS)
			.suppress(Warning.NULL_FIELDS)
			.verify();
	}

	@Test
	@DisplayName("eine toString()-Methode haben")
	void test03()
	{
		assertThat(sut).hasToString("BelastungDto(super=EntitaetDto(id=" + ID +
			"), grunduebungBelastung=GrunduebungBelastungDto(squat=1.0, benchpress=0.0, deadlift=0.0), oberkoerperBelastung=OberkoerperBelastungDto(triceps=0.7, chest=1.0, core=0.0, back=0.0, biceps=0.0, shoulder=0.1), unterkoerperBelastung=UnterkoerperBelastungDto(glutes=1.0, quads=1.0, hamstrings=0.5))");
	}

	@Test
	@DisplayName("null validieren")
	void test04()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class,
				() -> new BelastungDto(null, GRUNDUEBUNG_BELASTUNG_DTO, OBERKOERPER_BELASTUNG_DTO,
					UNTERKOERPER_BELASTUNG_DTO)),
			() -> assertThrows(NullPointerException.class,
				() -> new BelastungDto(ID, null, OBERKOERPER_BELASTUNG_DTO, UNTERKOERPER_BELASTUNG_DTO)),
			() -> assertThrows(NullPointerException.class,
				() -> new BelastungDto(ID, GRUNDUEBUNG_BELASTUNG_DTO, null, UNTERKOERPER_BELASTUNG_DTO)),
			() -> assertThrows(NullPointerException.class,
				() -> new BelastungDto(ID, GRUNDUEBUNG_BELASTUNG_DTO, OBERKOERPER_BELASTUNG_DTO, null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setId(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setGrunduebungBelastung(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setOberkoerperBelastung(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setUnterkoerperBelastung(null)));
	}
}
