package de.justinharder.dietharder.domain.model.exceptions.umfaenge;

import java.io.Serial;

public class BrustRueckenUmfangException extends RuntimeException
{
	@Serial
	private static final long serialVersionUID = -3304348652999313959L;

	public BrustRueckenUmfangException(String nachricht)
	{
		super(nachricht);
	}
}
