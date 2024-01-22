package de.justinharder.old.domain.model.exceptions;

import java.io.Serial;

public class KoerpermessungException extends Exception
{
	@Serial
	private static final long serialVersionUID = -7122300025252830474L;

	public KoerpermessungException(String nachricht)
	{
		super(nachricht);
	}
}
