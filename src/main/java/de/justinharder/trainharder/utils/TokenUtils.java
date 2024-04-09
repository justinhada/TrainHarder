package de.justinharder.trainharder.utils;

import de.justinharder.trainharder.domain.model.attribute.Benutzername;
import de.justinharder.trainharder.domain.model.attribute.Rolle;
import io.smallrye.jwt.build.Jwt;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TokenUtils
{
	public static String generateToken(Benutzername benutzername, Set<Rolle> rollen)
		throws IOException, NoSuchAlgorithmException, InvalidKeySpecException
	{
		var currentTimeInSecs = (int) (System.currentTimeMillis() / 1000);
		var privateKey = "/privateKey.pem";

		return Jwt
			.claims()
			.issuer("TrainHarder")
			.subject(benutzername.getWert())
			.issuedAt(currentTimeInSecs)
			.expiresAt(currentTimeInSecs + 3600)
			.groups(rollen.stream().map(Rolle::toString).collect(Collectors.toSet()))
			.jws()
			.signatureKeyId(privateKey)
			.sign(readPrivateKey(privateKey));
	}

	private static PrivateKey readPrivateKey(String pemResName)
		throws IOException, NoSuchAlgorithmException, InvalidKeySpecException
	{
		try (var inputStream = TokenUtils.class.getResourceAsStream(pemResName))
		{
			byte[] tmp = new byte[4096];

			return KeyFactory
				.getInstance("RSA")
				.generatePrivate(new PKCS8EncodedKeySpec(Base64.getDecoder().decode(
					new String(tmp, 0, inputStream.read(tmp), StandardCharsets.UTF_8)
						.replaceAll("-----BEGIN (.*)-----", "")
						.replaceAll("-----END (.*)----", "")
						.replaceAll("\r\n", "")
						.replaceAll("\n", "")
						.trim())));
		}
	}

}
