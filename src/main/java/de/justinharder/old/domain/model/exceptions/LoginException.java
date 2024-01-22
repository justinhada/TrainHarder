package de.justinharder.old.domain.model.exceptions;

import java.io.Serial;

public class LoginException extends Exception
{
	@Serial
	private static final long serialVersionUID = -1910353351519611596L;

	public LoginException(String nachricht)
	{
		super(nachricht);
	}
}
