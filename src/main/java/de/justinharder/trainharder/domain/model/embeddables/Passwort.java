package de.justinharder.trainharder.domain.model.embeddables;

import io.quarkus.security.jpa.Password;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@ToString
@Embeddable
@NoArgsConstructor
@RequiredArgsConstructor
@Accessors(chain = true)
public class Passwort implements Serializable
{
	@Serial
	private static final long serialVersionUID = 6610897321271486027L;

	@NonNull
	@Column(name = "Salt", nullable = false)
	private String salt;

	@NonNull
	@Password
	@Column(name = "PasswortHash", nullable = false)
	private String passwortHash;

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (!(o instanceof Passwort passwort))
		{
			return false;
		}
		return Objects.equals(salt, passwort.salt) && Objects.equals(passwortHash, passwort.passwortHash);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(salt, passwortHash);
	}
}
