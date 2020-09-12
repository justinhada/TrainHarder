package de.justinharder.trainharder.model.services.authentifizierung.passwort;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.trainharder.model.domain.embeddables.Passwort;

class PasswortHasherSollte
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
		final var erwartet = "AAAAAAAAAAAAAAAAAAAAAA==";

		final var salt = new byte[16];
		final var ergebnis = sut.generiereSalt(salt);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(secureRandom).nextBytes(salt);
	}

	@Test
	@DisplayName("ein Passwort hashen")
	void test02() throws InvalidKeySpecException, NoSuchAlgorithmException
	{
		final var erwartet = "GBy6erWCKE3CqEuWqYOk/w==";

		final var salt = "AAAAAAAAAAAAAAAAAAAAAA==";
		final var ergebnis = sut.hash("Justinharder#98", salt);

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("zwei Passwörter vergleichen")
	void test03() throws InvalidKeySpecException, NoSuchAlgorithmException
	{
		assertAll(() ->
		{
			final var erwartet = true;

			final var ergebnis =
				sut.check(new Passwort("f1qvR4c6WNx0sW2PuQsu8g==", "ZDsH4I6vevFMzi4r010ScA=="), "Justinharder#98");

			assertThat(ergebnis).isEqualTo(erwartet);
		}, () ->
		{
			final var erwartet = false;

			final var ergebnis =
				sut.check(new Passwort("f1qvR4c6WNx0sW2PuQsu8g==", "ZDsH4I6vevFMzi4r010ScA=="), "NichtJustinharder#98");

			assertThat(ergebnis).isEqualTo(erwartet);
		});
	}
}
