package de.justinharder.old.domain.services.authentifizierung.passwort;

import de.justinharder.old.domain.model.attribute.Passwort;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import lombok.NonNull;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

@Dependent
public class PasswortHasher
{
	private static final int LAENGE = 128;
	private static final int ITERATIONEN = 65536;
	private static final String ALGORITHMUS = "PBKDF2WithHmacSHA1";

	private SecureRandom secureRandom;

	@Inject
	public PasswortHasher()
	{
		secureRandom = new SecureRandom();
	}

	public void setSecureRandom(@NonNull SecureRandom secureRandom)
	{
		this.secureRandom = secureRandom;
	}

	public String generiereSalt(byte[] salt)
	{
		secureRandom.nextBytes(salt);
		return Base64.getEncoder().encodeToString(salt);
	}

	public String hash(@NonNull String passwort, @NonNull String salt) throws InvalidKeySpecException, NoSuchAlgorithmException
	{
		return pbkdf2(passwort, salt);
	}

	public boolean check(@NonNull Passwort aktuellesPasswort, @NonNull String passwort) throws InvalidKeySpecException, NoSuchAlgorithmException
	{
		var hash = pbkdf2(passwort, aktuellesPasswort.getSalt());
		return hash.equals(aktuellesPasswort.getPasswortHash());
	}

	private static String pbkdf2(String passwort, String salt) throws InvalidKeySpecException, NoSuchAlgorithmException
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
