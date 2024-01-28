package de.justinharder.dietharder.domain.model.exceptions.umfaenge;

import java.io.Serial;

public class HueftUmfangException extends RuntimeException
{
	@Serial
	private static final long serialVersionUID = 6478908079303338194L;

	public HueftUmfangException(String nachricht)
	{
		super(nachricht);
	}
}
