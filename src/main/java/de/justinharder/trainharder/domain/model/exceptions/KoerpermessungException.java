package de.justinharder.trainharder.domain.model.exceptions;

public class KoerpermessungException extends Exception
{
	private static final long serialVersionUID = -7122300025252830474L;

	public KoerpermessungException(String nachricht)
	{
		super(nachricht);
	}
}
