package de.justinharder.trainharder.model.domain.exceptions;

public class BenutzernameVergebenException extends Exception
{
	private static final long serialVersionUID = 7646830880003860994L;

	public BenutzernameVergebenException(final String nachricht)
	{
		super(nachricht);
	}
}
