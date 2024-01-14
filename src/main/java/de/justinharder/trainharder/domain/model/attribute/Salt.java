package de.justinharder.trainharder.domain.model.attribute;

import de.justinharder.base.domain.model.attribute.WertObjekt;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serial;
import java.security.SecureRandom;
import java.util.Base64;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Salt extends WertObjekt<String>
{
	@Serial
	private static final long serialVersionUID = 7660156071669811341L;

	@NonNull
	@Column(name = "Salt", nullable = false)
	private String wert;

	public static Salt random()
	{
		var wert = new byte[16];
		new SecureRandom().nextBytes(wert);
		return new Salt(Base64.getEncoder().encodeToString(wert));
	}
}
