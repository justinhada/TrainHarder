package de.justinharder.trainharder.domain.model.exceptions;

public class BenutzerException extends Exception
{
	private static final long serialVersionUID = -8124391141573256519L;

	public BenutzerException(String nachricht)
	{
		super(nachricht);
	}
}
