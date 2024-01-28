package de.justinharder.dietharder.domain.model.exceptions.umfaenge;

import java.io.Serial;

public class SchulterUmfangException extends RuntimeException
{
	@Serial
	private static final long serialVersionUID = 2925252868158776035L;

	public SchulterUmfangException(String nachricht)
	{
		super(nachricht);
	}
}
