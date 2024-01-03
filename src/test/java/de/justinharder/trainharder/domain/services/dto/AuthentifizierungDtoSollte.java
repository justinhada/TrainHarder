package de.justinharder.trainharder.domain.services.dto;

import de.justinharder.trainharder.domain.model.embeddables.ID;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AuthentifizierungDtoSollte
{
	private static final String ID = new ID().getWert().toString();

	private AuthentifizierungDto sut;

	@BeforeEach
	void setup()
	{
		sut = new AuthentifizierungDto(ID, "mail@justinharder.de", "harder");
	}

	@Test
	@DisplayName("einen NoArgsConstructor und Setter besitzen")
	void test01()
	{
		sut = new AuthentifizierungDto()
			.setId(ID)
			.setMail("mail@justinharder.de")
			.setBenutzername("harder");

		assertAll(
			() -> assertThat(sut.getId()).isEqualTo(ID),
			() -> assertThat(sut.getMail()).isEqualTo("mail@justinharder.de"),
			() -> assertThat(sut.getBenutzername()).isEqualTo("harder"));
	}

	@Test
	@DisplayName("sich vergleichen")
	void test02()
	{
		EqualsVerifier.forClass(AuthentifizierungDto.class)
			.suppress(Warning.STRICT_INHERITANCE)
			.suppress(Warning.NONFINAL_FIELDS)
			.suppress(Warning.NULL_FIELDS)
			.verify();
	}

	@Test
	@DisplayName("eine toString()-Methode haben")
	void test03()
	{
		assertThat(sut).hasToString("AuthentifizierungDto(super=EntitaetDto(id=" + ID + "), mail=mail@justinharder.de, benutzername=harder)");
	}

	@Test
	@DisplayName("null validieren")
	void test04()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> new AuthentifizierungDto(null, "mail", "benutzername")),
			() -> assertThrows(NullPointerException.class, () -> new AuthentifizierungDto("id", null, "benutzername")),
			() -> assertThrows(NullPointerException.class, () -> new AuthentifizierungDto("id", "mail", null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setId(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setMail(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setBenutzername(null)));
	}
}
