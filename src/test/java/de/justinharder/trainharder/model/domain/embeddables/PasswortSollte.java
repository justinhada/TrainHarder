package de.justinharder.trainharder.model.domain.embeddables;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.trainharder.setup.Testdaten;

class PasswortSollte
{
	private Passwort sut;

	@BeforeEach
	void setup()
	{
		sut = new Passwort(Testdaten.PASSWORT.getSalt(), Testdaten.PASSWORT.getPasswortHash());
	}

	@Test
	@DisplayName("einen AllArgsConstructor und Getter besitzen")
	void test01()
	{
		assertAll(
			() -> assertThat(sut.getSalt()).isEqualTo(Testdaten.PASSWORT.getSalt()),
			() -> assertThat(sut.getPasswortHash()).isEqualTo(Testdaten.PASSWORT.getPasswortHash()));
	}

	@Test
	@DisplayName("einen NoArgsConstructor und Setter besitzen")
	void test02()
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
	void test05()
	{
		final var anderesPasswort = new Passwort("213MFKf4DTBEXnWG7tXvhA==", "lllZ8W5m2jf5TtSBnNfB/w==");

		assertAll(
			() -> assertThat(sut.equals(sut)).isEqualTo(true),
			() -> assertThat(sut.equals(null)).isEqualTo(false),
			() -> assertThat(sut.equals(Testdaten.AUTHENTIFIZIERUNG_JUSTIN)).isEqualTo(false),
			() -> assertThat(sut.equals(anderesPasswort)).isEqualTo(false),
			() -> assertThat(sut.hashCode()).isNotEqualTo(anderesPasswort.hashCode()));
	}

	@Test
	@DisplayName("eine toString()-Methode haben")
	void test06()
	{
		final var erwartet = "Passwort(salt=lhwMFKf4DTBEXnWG7tXvhA==, passwortHash=mNMZ8W5m2jf5TtSBnNfB/w==)";

		assertThat(sut.toString()).isEqualTo(erwartet);
	}
}
