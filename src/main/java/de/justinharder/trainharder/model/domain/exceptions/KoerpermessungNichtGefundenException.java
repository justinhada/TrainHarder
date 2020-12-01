package de.justinharder.trainharder.model.domain.exceptions;

public class KoerpermessungNichtGefundenException extends Exception
{
	private static final long serialVersionUID = -7122300025252830474L;

	public KoerpermessungNichtGefundenException(String nachricht)
	{
		super(nachricht);
	}
}
