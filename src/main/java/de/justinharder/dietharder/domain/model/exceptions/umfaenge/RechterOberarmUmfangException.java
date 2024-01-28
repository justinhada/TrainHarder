package de.justinharder.dietharder.domain.model.exceptions.umfaenge;

import java.io.Serial;

public class RechterOberarmUmfangException extends RuntimeException
{
	@Serial
	private static final long serialVersionUID = 4063932999649007151L;

	public RechterOberarmUmfangException(String nachricht)
	{
		super(nachricht);
	}
}
