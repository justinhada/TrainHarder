package de.justinharder.powerlifting.model.domain.exceptions;

public class UngueltigeWiederholungenException extends Exception
{
	private static final long serialVersionUID = 4222858778941563915L;

	public UngueltigeWiederholungenException(final String nachricht)
	{
		super(nachricht);
	}
}
