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

public class PasswortHasherSollte
{
	private PasswortHasher sut;

	private SecureRandom secureRandom;

	@BeforeEach
	public void setup()
	{
		secureRandom = mock(SecureRandom.class);

		sut = new PasswortHasher();

		sut.setSecureRandom(secureRandom);
	}

	@Test
	@DisplayName("einen Salt generieren")
	public void test01()
	{
		final var erwartet = new byte[]
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

		final var salt = new byte[16];
		final var ergebnis = sut.generiereSalt(salt);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(secureRandom).nextBytes(salt);
	}

	@Test
	@DisplayName("ein Passwort hashen")
	public void test02() throws InvalidKeySpecException, NoSuchAlgorithmException
	{
		final var erwartet = new byte[]
		{ 24, 28, -70, 122, -75, -126, 40, 77, -62, -88, 75, -106, -87, -125, -92, -1 };

		final var salt = new byte[]
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		final var ergebnis = sut.hash("Justinharder#98", salt);

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("zwei Passwörter vergleichen")
	public void test03() throws InvalidKeySpecException, NoSuchAlgorithmException
	{
		assertAll(() ->
		{
			final var erwartet = true;

			final var ergebnis = sut.check(new Passwort(
				new byte[]
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				new byte[]
			{ 24, 28, -70, 122, -75, -126, 40, 77, -62, -88, 75, -106, -87, -125, -92, -1 }),
				"Justinharder#98");

			assertThat(ergebnis).isEqualTo(erwartet);
		}, () ->
		{
			final var erwartet = false;

			final var ergebnis = sut.check(new Passwort(
				new byte[]
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				new byte[]
			{ 24, 28, -70, 122, -75, -126, 40, 77, -62, -88, 75, -106, -87, -125, -92, -1 }),
				"NichtJustinharder#98");

			assertThat(ergebnis).isEqualTo(erwartet);
		});
	}
}
