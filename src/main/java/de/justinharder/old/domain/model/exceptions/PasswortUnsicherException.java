package de.justinharder.old.domain.model.exceptions;

public class PasswortUnsicherException extends Exception
{
	private static final long serialVersionUID = -8643094892502666702L;

	public PasswortUnsicherException(String nachricht)
	{
		super(nachricht);
	}
}
