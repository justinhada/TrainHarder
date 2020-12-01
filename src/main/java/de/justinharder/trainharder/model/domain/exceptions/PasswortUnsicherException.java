package de.justinharder.trainharder.model.domain.exceptions;

public class PasswortUnsicherException extends Exception
{
	private static final long serialVersionUID = -8643094892502666702L;

	public PasswortUnsicherException(String nachricht)
	{
		super(nachricht);
	}
}
