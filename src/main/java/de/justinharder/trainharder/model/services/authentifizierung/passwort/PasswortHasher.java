package de.justinharder.trainharder.model.services.authentifizierung.passwort;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.inject.Inject;

import de.justinharder.trainharder.model.domain.embeddables.Passwort;
import lombok.Setter;

public class PasswortHasher
{
	private static final int LAENGE = 128;
	private static final int ITERATIONS = 65536;
	private static final String ALGORITHMUS = "PBKDF2WithHmacSHA1";

	@Setter
	private SecureRandom secureRandom;

	@Inject
	public PasswortHasher()
	{
		secureRandom = new SecureRandom();
	}

	public byte[] generiereSalt(final byte[] salt)
	{
		secureRandom.nextBytes(salt);
		return salt;
	}

	public byte[] hash(final String passwort, final byte[] salt)
		throws InvalidKeySpecException, NoSuchAlgorithmException
	{
		return pbkdf2(passwort.toCharArray(), salt, ITERATIONS);
	}

	public boolean check(final Passwort aktuellesPasswort, final String passwort)
		throws InvalidKeySpecException, NoSuchAlgorithmException
	{
		final var hash = pbkdf2(passwort.toCharArray(), aktuellesPasswort.getSalt(), ITERATIONS);
		return Arrays.equals(hash, aktuellesPasswort.getPasswortHash());
	}

	private static byte[] pbkdf2(final char[] passwort, final byte[] salt, final int iterationCount)
		throws InvalidKeySpecException, NoSuchAlgorithmException
	{
		return SecretKeyFactory
			.getInstance(ALGORITHMUS)
			.generateSecret(new PBEKeySpec(passwort, salt, iterationCount, LAENGE))
			.getEncoded();
	}
}
