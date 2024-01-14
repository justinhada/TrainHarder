package de.justinharder.trainharder.domain.model.attribute;

import de.justinharder.base.domain.model.attribute.WertObjekt;
import de.justinharder.trainharder.domain.model.exceptions.PasswortException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.Serial;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.function.IntPredicate;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Passwort extends WertObjekt<String>
{
	@Serial
	private static final long serialVersionUID = -1755430787167587386L;

	@NonNull
	@Column(name = "Passwort", nullable = false)
	private String wert;

	public static Passwort aus(@NonNull Salt salt, @NonNull String wert)
	{
		if (!validierePasswort(wert))
		{
			// TODO: List<Exception> innerhalb von PasswortException
			throw new PasswortException("Das Passwort ist ungültig!");
		}

		return hash(wert, salt);
	}

	public static boolean validierePasswort(String passwort)
	{
		return !passwort.isBlank()
			&& pruefeGrossbuchstabe(passwort)
			&& pruefeKleinbuchstabe(passwort)
			&& pruefeZahl(passwort)
			&& pruefeSonderzeichen(passwort)
			&& pruefeLaenge(passwort);
	}

	private static boolean pruefeGrossbuchstabe(String passwort)
	{
		return pruefeAuf(passwort, Character::isUpperCase);
	}

	private static boolean pruefeKleinbuchstabe(String passwort)
	{
		return pruefeAuf(passwort, Character::isLowerCase);
	}

	private static boolean pruefeZahl(String passwort)
	{
		return pruefeAuf(passwort, Character::isDigit);
	}

	private static boolean pruefeSonderzeichen(String passwort)
	{
		return pruefeAuf(passwort, character -> !Character.isLetterOrDigit(character));
	}

	private static boolean pruefeAuf(String passwort, IntPredicate pruefung)
	{
		return passwort.chars().anyMatch(pruefung);
	}

	private static boolean pruefeLaenge(String passwort)
	{
		return passwort.length() >= 12;
	}

	private static Passwort hash(String passwort, Salt salt)
	{
		try
		{
			return new Passwort(Base64.getEncoder().encodeToString(SecretKeyFactory
				.getInstance("PBKDF2WithHmacSHA1")
				.generateSecret(
					new PBEKeySpec(passwort.toCharArray(), Base64.getDecoder().decode(salt.getWert()), 65536, 128))
				.getEncoded()));
		}
		catch (InvalidKeySpecException | NoSuchAlgorithmException e)
		{
			throw new PasswortException("Das Passwort konnte nicht gespeichert werden!");
		}
	}
}
