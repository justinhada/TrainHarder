package de.justinharder.trainharder.model.domain.embeddables;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Passwort implements Serializable
{
	private static final long serialVersionUID = 6610897321271486027L;

	@Column(name = "Salt", columnDefinition = "VARBINARY(128)", nullable = false)
	private byte[] salt;
	@Column(name = "PasswortHash", columnDefinition = "VARBINARY(128)", nullable = false)
	private byte[] passwortHash; 

	public Passwort setSalt(final byte[] salt)
	{
		this.salt = salt;
		return this;
	}

	public Passwort setPasswortHash(final byte[] passwortHash)
	{
		this.passwortHash = passwortHash;
		return this;
	}
}
