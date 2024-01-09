package de.justinharder.trainharder.domain.services.authentifizierung.passwort;

import de.justinharder.trainharder.domain.model.attribute.Passwort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@DisplayName("PasswortHasher sollte")
class PasswortHasherTest
{
	private PasswortHasher sut;

	private SecureRandom secureRandom;

	@BeforeEach
	void setup()
	{
		secureRandom = mock(SecureRandom.class);

		sut = new PasswortHasher();

		sut.setSecureRandom(secureRandom);
	}

	@Test
	@DisplayName("einen Salt generieren")
	void test01()
	{
		var erwartet = "AAAAAAAAAAAAAAAAAAAAAA==";

		var salt = new byte[16];
		var ergebnis = sut.generiereSalt(salt);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(secureRandom).nextBytes(salt);
	}

	@Test
	@DisplayName("ein Passwort hashen")
	void test02() throws InvalidKeySpecException, NoSuchAlgorithmException
	{
		var erwartet = "GBy6erWCKE3CqEuWqYOk/w==";

		var salt = "AAAAAAAAAAAAAAAAAAAAAA==";
		var ergebnis = sut.hash("Justinharder#98", salt);

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("zwei Passwörter vergleichen")
	void test03()
	{
		assertAll(
			() -> assertThat(sut.check(new Passwort("f1qvR4c6WNx0sW2PuQsu8g==", "ZDsH4I6vevFMzi4r010ScA=="),
				"Justinharder#98")).isTrue(),
			() -> assertThat(sut.check(new Passwort("f1qvR4c6WNx0sW2PuQsu8g==", "ZDsH4I6vevFMzi4r010ScA=="),
				"NichtJustinharder#98")).isFalse());
	}

	@Test
	@DisplayName("null validieren")
	void test04()
	{
		var passwort = new Passwort();
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> sut.setSecureRandom(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.hash(null, "salt")),
			() -> assertThrows(NullPointerException.class, () -> sut.hash("passwort", null)),
			() -> assertThrows(NullPointerException.class, () -> sut.check(null, "passwort")),
			() -> assertThrows(NullPointerException.class, () -> sut.check(passwort, null)));
	}
}
