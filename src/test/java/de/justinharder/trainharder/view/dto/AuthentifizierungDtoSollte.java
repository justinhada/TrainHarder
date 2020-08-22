package de.justinharder.trainharder.view.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.setup.Testdaten;

public class AuthentifizierungDtoSollte
{
	private AuthentifizierungDto sut;

	private String primaerschluessel;

	@BeforeEach
	public void setup()
	{
		primaerschluessel = new Primaerschluessel().getId().toString();
		sut = new AuthentifizierungDto(primaerschluessel, "mail@justinharder.de", "harder");
	}

	@Test
	@DisplayName("einen AllArgsConstructor und Getter besitzen")
	public void test01()
	{
		assertAll(
			() -> assertThat(sut.getPrimaerschluessel()).isEqualTo(primaerschluessel),
			() -> assertThat(sut.getMail()).isEqualTo("mail@justinharder.de"),
			() -> assertThat(sut.getBenutzername()).isEqualTo("harder"));
	}

	@Test
	@DisplayName("einen NoArgsConstructor und Setter besitzen")
	public void test02()
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
	public void test05()
	{
		final var anderesAuthentifizierungDto = new AuthentifizierungDto(
			new Primaerschluessel().getId().toString(), "justinharder@t-online.de", "harder");

		assertAll(
			() -> assertThat(sut.equals(sut)).isEqualTo(true),
			() -> assertThat(sut.equals(null)).isEqualTo(false),
			() -> assertThat(sut.equals(Testdaten.AUTHENTIFIZIERUNG_JUSTIN)).isEqualTo(false),
			() -> assertThat(sut.equals(anderesAuthentifizierungDto)).isEqualTo(false),
			() -> assertThat(sut.hashCode()).isNotEqualTo(anderesAuthentifizierungDto.hashCode()));
	}

	@Test
	@DisplayName("eine toString()-Methode haben")
	public void test06()
	{
		final var erwartet = "AuthentifizierungDto(primaerschluessel=" + primaerschluessel
			+ ", mail=mail@justinharder.de, benutzername=harder)";

		assertThat(sut.toString()).isEqualTo(erwartet);
	}
}
