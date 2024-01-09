package de.justinharder.old.domain.model.exceptions;

public class LoginException extends Exception
{
	private static final long serialVersionUID = -1910353351519611596L;

	public LoginException(String nachricht)
	{
		super(nachricht);
	}
}
