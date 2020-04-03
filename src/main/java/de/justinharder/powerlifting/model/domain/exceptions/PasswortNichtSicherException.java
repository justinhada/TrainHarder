package de.justinharder.powerlifting.model.domain.exceptions;

public class PasswortNichtSicherException extends Exception
{
	private static final long serialVersionUID = -8643094892502666702L;

	public PasswortNichtSicherException(final String nachricht)
	{
		super(nachricht);
	}
}
