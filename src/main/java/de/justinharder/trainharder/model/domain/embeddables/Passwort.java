package de.justinharder.trainharder.model.domain.embeddables;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
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

	public Passwort(final String salt, final String passwortHash)
	{
		this.salt = salt;
		this.passwortHash = passwortHash;
	}

	public Passwort setSalt(final String salt)
	{
		this.salt = salt;
		return this;
	}

	public Passwort setPasswortHash(final String passwortHash)
	{
		this.passwortHash = passwortHash;
		return this;
	}
}
