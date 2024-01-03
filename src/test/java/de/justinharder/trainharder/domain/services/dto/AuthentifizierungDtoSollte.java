package de.justinharder.trainharder.domain.services.dto;

import de.justinharder.trainharder.domain.model.embeddables.Primaerschluessel;
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
	private static final String PRIMAERSCHLUESSEL = new Primaerschluessel().getId().toString();

	private AuthentifizierungDto sut;

	@BeforeEach
	void setup()
	{
		sut = new AuthentifizierungDto(PRIMAERSCHLUESSEL, "mail@justinharder.de", "harder");
	}

	@Test
	@DisplayName("einen NoArgsConstructor und Setter besitzen")
	void test01()
	{
		sut = new AuthentifizierungDto()
			.setPrimaerschluessel(PRIMAERSCHLUESSEL)
			.setMail("mail@justinharder.de")
			.setBenutzername("harder");

		assertAll(
			() -> assertThat(sut.getPrimaerschluessel()).isEqualTo(PRIMAERSCHLUESSEL),
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
		assertThat(sut).hasToString("AuthentifizierungDto(super=EntitaetDto(primaerschluessel=" + PRIMAERSCHLUESSEL + "), mail=mail@justinharder.de, benutzername=harder)");
	}

	@Test
	@DisplayName("null validieren")
	void test04()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> new AuthentifizierungDto(null, "mail", "benutzername")),
			() -> assertThrows(NullPointerException.class, () -> new AuthentifizierungDto("primaerschluessel", null, "benutzername")),
			() -> assertThrows(NullPointerException.class, () -> new AuthentifizierungDto("primaerschluessel", "mail", null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setPrimaerschluessel(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setMail(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setBenutzername(null)));
	}
}
