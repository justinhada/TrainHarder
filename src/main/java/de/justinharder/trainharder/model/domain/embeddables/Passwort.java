package de.justinharder.trainharder.model.domain.embeddables;

import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Getter
@ToString
@Embeddable
public class Passwort implements Serializable
{
	private static final long serialVersionUID = 6610897321271486027L;

	@Column(name = "Salt", nullable = false)
	private String salt;
	@Column(name = "PasswortHash", nullable = false)
	private String passwortHash;

	public Passwort()
	{}

	public Passwort(@NonNull String salt, @NonNull String passwortHash)
	{
		this.salt = salt;
		this.passwortHash = passwortHash;
	}

	public Passwort setSalt(@NonNull String salt)
	{
		this.salt = salt;
		return this;
	}

	public Passwort setPasswortHash(@NonNull String passwortHash)
	{
		this.passwortHash = passwortHash;
		return this;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (!(o instanceof Passwort))
		{
			return false;
		}
		var passwort = (Passwort) o;
		return Objects.equals(salt, passwort.salt) && Objects.equals(passwortHash, passwort.passwortHash);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(salt, passwortHash);
	}
}