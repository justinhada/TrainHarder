package de.justinharder.trainharder.model.services.authentifizierung.passwort;

import de.justinharder.trainharder.model.domain.embeddables.Passwort;
import lombok.Setter;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.inject.Inject;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

public class PasswortHasher
{
	private static final int LAENGE = 128;
	private static final int ITERATIONEN = 65536;
	private static final String ALGORITHMUS = "PBKDF2WithHmacSHA1";

	@Setter
	private SecureRandom secureRandom;

	@Inject
	public PasswortHasher()
	{
		secureRandom = new SecureRandom();
	}

	public String generiereSalt(final byte[] salt)
	{
		secureRandom.nextBytes(salt);
		return Base64.getEncoder().encodeToString(salt);
	}

	public String hash(final String passwort, final String salt)
		throws InvalidKeySpecException, NoSuchAlgorithmException
	{
		return pbkdf2(passwort, salt);
	}

	public boolean check(final Passwort aktuellesPasswort, final String passwort)
		throws InvalidKeySpecException, NoSuchAlgorithmException
	{
		final var hash = pbkdf2(passwort, aktuellesPasswort.getSalt());
		return hash.equals(aktuellesPasswort.getPasswortHash());
	}

	private static String pbkdf2(final String passwort, final String salt)
		throws InvalidKeySpecException, NoSuchAlgorithmException
	{
		return Base64.getEncoder().encodeToString(SecretKeyFactory
			.getInstance(ALGORITHMUS)
			.generateSecret(new PBEKeySpec(
				passwort.toCharArray(),
				Base64.getDecoder().decode(salt),
				ITERATIONEN,
				LAENGE))
			.getEncoded());
	}
}
