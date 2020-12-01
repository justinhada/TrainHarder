package de.justinharder.trainharder.model.domain.exceptions;

public class UngueltigeWiederholungenException extends Exception
{
	private static final long serialVersionUID = 4222858778941563915L;

	public UngueltigeWiederholungenException(String nachricht)
	{
		super(nachricht);
	}
}
