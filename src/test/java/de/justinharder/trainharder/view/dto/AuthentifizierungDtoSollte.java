package de.justinharder.trainharder.view.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.setup.Testdaten;

class AuthentifizierungDtoSollte
{
	private AuthentifizierungDto sut;

	private String primaerschluessel;

	@BeforeEach
	void setup()
	{
		primaerschluessel = new Primaerschluessel().getId().toString();
		sut = new AuthentifizierungDto(primaerschluessel, "mail@justinharder.de", "harder");
	}

	@Test
	@DisplayName("einen AllArgsConstructor und Getter besitzen")
	void test01()
	{
		assertAll(
			() -> assertThat(sut.getPrimaerschluessel()).isEqualTo(primaerschluessel),
			() -> assertThat(sut.getMail()).isEqualTo("mail@justinharder.de"),
			() -> assertThat(sut.getBenutzername()).isEqualTo("harder"));
	}

	@Test
	@DisplayName("einen NoArgsConstructor und Setter besitzen")
	void test02()
	{
		sut = new AuthentifizierungDto()
			.setPrimaerschluessel(primaerschluessel)
			.setMail("mail@justinharder.de")
			.setBenutzername("harder");

		assertAll(
			() -> assertThat(sut.getPrimaerschluessel()).isEqualTo(primaerschluessel),
			() -> assertThat(sut.getMail()).isEqualTo("mail@justinharder.de"),
			() -> assertThat(sut.getBenutzername()).isEqualTo("harder"));
	}

	@Test
	@DisplayName("sich vergleichen")
	@SuppressWarnings("unlikely-arg-type")
	void test05()
	{
		EqualsVerifier.forClass(AuthentifizierungDto.class).verify();
	}

	@Test
	@DisplayName("eine toString()-Methode haben")
	void test06()
	{
		final var erwartet = "AuthentifizierungDto(primaerschluessel=" + primaerschluessel
			+ ", mail=mail@justinharder.de, benutzername=harder)";

		assertThat(sut).hasToString(erwartet);
	}
}
