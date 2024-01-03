package de.justinharder.trainharder.domain.model.exceptions;

public class BenutzernameVergebenException extends Exception
{
	private static final long serialVersionUID = 7646830880003860994L;

	public BenutzernameVergebenException(String nachricht)
	{
		super(nachricht);
	}
}
