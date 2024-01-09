package de.justinharder.old.domain.services.dto;

import de.justinharder.base.domain.model.attribute.ID;
import de.justinharder.old.domain.services.dto.KraftwertDto;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("KraftwertDto sollte")
class KraftwertDtoTest
{
	private static final String ID = new ID().getWert().toString();
	private static final String DATUM = "22.08.2020";
	private static final String GEWICHT = "100.00";
	private static final String WIEDERHOLUNGEN = "1RM";

	private KraftwertDto sut;

	@BeforeEach
	void setup()
	{
		sut = new KraftwertDto(ID, DATUM, GEWICHT, WIEDERHOLUNGEN);
	}

	@Test
	@DisplayName("Getter besitzen")
	void test02()
	{
		assertAll(
			() -> assertThat(sut.getId()).isEqualTo(ID),
			() -> assertThat(sut.getDatum()).isEqualTo(DATUM),
			() -> assertThat(sut.getGewicht()).isEqualTo(GEWICHT),
			() -> assertThat(sut.getWiederholungen()).isEqualTo(WIEDERHOLUNGEN));
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
		assertThat(sut).hasToString(
			"KraftwertDto(super=EntitaetDto(id=" + ID + "), datum=22.08.2020, gewicht=100.00, wiederholungen=1RM)");
	}

	@Test
	@DisplayName("null validieren")
	void test05()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class,
				() -> new KraftwertDto(null, DATUM, GEWICHT, WIEDERHOLUNGEN)),
			() -> assertThrows(NullPointerException.class,
				() -> new KraftwertDto(ID, null, GEWICHT, WIEDERHOLUNGEN)),
			() -> assertThrows(NullPointerException.class,
				() -> new KraftwertDto(ID, DATUM, null, WIEDERHOLUNGEN)),
			() -> assertThrows(NullPointerException.class,
				() -> new KraftwertDto(ID, DATUM, GEWICHT, null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setId(null)));
	}
}
