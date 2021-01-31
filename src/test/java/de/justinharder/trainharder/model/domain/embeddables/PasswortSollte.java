package de.justinharder.trainharder.model.domain.embeddables;

import de.justinharder.trainharder.setup.Testdaten;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
	void test03()
	{
		EqualsVerifier.forClass(Passwort.class)
			.suppress(Warning.NULL_FIELDS)
			.verify();
	}

	@Test
	@DisplayName("eine toString()-Methode haben")
	void test04()
	{
		assertThat(sut).hasToString("Passwort(salt=lhwMFKf4DTBEXnWG7tXvhA==, passwortHash=mNMZ8W5m2jf5TtSBnNfB/w==)");
	}

	@Test
	@DisplayName("null validieren")
	void test05()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> new Passwort(null, "passwortHash")),
			() -> assertThrows(NullPointerException.class, () -> new Passwort("salt", null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setSalt(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setPasswortHash(null)));
	}
}