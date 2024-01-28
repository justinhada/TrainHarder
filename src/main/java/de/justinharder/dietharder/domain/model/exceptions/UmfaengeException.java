package de.justinharder.dietharder.domain.model.exceptions;

import java.io.Serial;

public class UmfaengeException extends Exception
{
	@Serial
	private static final long serialVersionUID = 4158027268714830627L;

	public UmfaengeException(String nachricht)
	{
		super(nachricht);
	}
}
