package de.justinharder.trainharder.model.domain.embeddables;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.trainharder.setup.Testdaten;

public class PasswortSollte
{
	private Passwort sut;

	@BeforeEach
	public void setup()
	{
		sut = new Passwort(Testdaten.PASSWORT.getSalt(), Testdaten.PASSWORT.getPasswortHash());
	}

	@Test
	@DisplayName("einen AllArgsConstructor und Getter besitzen")
	public void test01()
	{
		assertAll(
			() -> assertThat(sut.getSalt()).isEqualTo(Testdaten.PASSWORT.getSalt()),
			() -> assertThat(sut.getPasswortHash()).isEqualTo(Testdaten.PASSWORT.getPasswortHash()));
	}

	@Test
	@DisplayName("einen NoArgsConstructor und Setter besitzen")
	public void test02()
	{
		sut = new Passwort()
			.setSalt(Testdaten.PASSWORT.getSalt())
			.setPasswortHash(Testdaten.PASSWORT.getPasswortHash());

		assertAll(
			() -> assertThat(sut.getSalt()).isEqualTo(Testdaten.PASSWORT.getSalt()),
			() -> assertThat(sut.getPasswortHash()).isEqualTo(Testdaten.PASSWORT.getPasswortHash()));
	}

	@Test
	@DisplayName("sich vergleichen")
	@SuppressWarnings("unlikely-arg-type")
	public void test05()
	{
		final var anderesPasswort = new Passwort(
			new byte[]
			{ -91, 56, -96, 113, -49, 24, 56, -94, -125, 43, 99, 45, 84, 52, 72, 19 },
			new byte[]
			{ -35, 61, 38, -97, 17, -55, 58, -123, 42, 57, 101, 23, 52, 74, -16, -107 });

		assertAll(
			() -> assertThat(sut.equals(sut)).isEqualTo(true),
			() -> assertThat(sut.equals(null)).isEqualTo(false),
			() -> assertThat(sut.equals(Testdaten.AUTHENTIFIZIERUNG_JUSTIN)).isEqualTo(false),
			() -> assertThat(sut.equals(anderesPasswort)).isEqualTo(false),
			() -> assertThat(sut.hashCode()).isNotEqualTo(anderesPasswort.hashCode()));
	}

	@Test
	@DisplayName("eine toString()-Methode haben")
	public void test06()
	{
		final var erwartet =
			"Passwort(salt=[-92, 56, -96, 113, -49, 24, 56, -94, -125, 43, 99, 45, 84, 52, 72, 19], passwortHash=[-35, 61, 38, -97, 17, -55, 58, -123, 42, 57, 101, 23, 52, 74, -16, -107])";

		assertThat(sut.toString()).isEqualTo(erwartet);
	}
}
