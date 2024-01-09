package de.justinharder.old.domain.services.dto;

import de.justinharder.old.domain.services.dto.NameDto;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("NameDto sollte")
class NameDtoTest
{
	private NameDto sut;

	private static final String VORNAME = "Justin";
	private static final String NACHNAME = "Harder";

	@BeforeEach
	void setup()
	{
		sut = new NameDto(VORNAME, NACHNAME);
	}

	@Test
	@DisplayName("einen NoArgsConstructor und Setter besitzen")
	void test01()
	{
		sut = new NameDto()
			.setVorname(VORNAME)
			.setNachname(NACHNAME);

		assertAll(
			() -> assertThat(sut.getVorname()).isEqualTo(VORNAME),
			() -> assertThat(sut.getNachname()).isEqualTo(NACHNAME));
	}

	@Test
	@DisplayName("sich vergleichen")
	void test02()
	{
		EqualsVerifier.forClass(NameDto.class)
			.suppress(Warning.STRICT_INHERITANCE)
			.suppress(Warning.NONFINAL_FIELDS)
			.verify();
	}

	@Test
	@DisplayName("eine toString()-Methode haben")
	void test03()
	{
		assertThat(sut).hasToString("NameDto(vorname=Justin, nachname=Harder)");
	}

	@Test
	@DisplayName("null validieren")
	void test04()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> new NameDto(null, NACHNAME)),
			() -> assertThrows(NullPointerException.class, () -> new NameDto(VORNAME, null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setVorname(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setNachname(null)));
	}
}
