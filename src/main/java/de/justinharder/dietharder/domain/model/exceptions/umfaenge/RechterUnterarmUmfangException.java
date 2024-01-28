package de.justinharder.dietharder.domain.model.exceptions.umfaenge;

import java.io.Serial;

public class RechterUnterarmUmfangException extends RuntimeException
{
	@Serial
	private static final long serialVersionUID = -4195395051208330564L;

	public RechterUnterarmUmfangException(String nachricht)
	{
		super(nachricht);
	}
}
