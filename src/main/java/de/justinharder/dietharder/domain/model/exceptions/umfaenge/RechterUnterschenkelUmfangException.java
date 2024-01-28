package de.justinharder.dietharder.domain.model.exceptions.umfaenge;

import java.io.Serial;

public class RechterUnterschenkelUmfangException extends RuntimeException
{
	@Serial
	private static final long serialVersionUID = 4406532678734402336L;

	public RechterUnterschenkelUmfangException(String nachricht)
	{
		super(nachricht);
	}
}
