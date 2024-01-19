package de.justinharder.trainharder.domain.model.exceptions;

import java.io.Serial;

public class LoginException extends Exception
{
	@Serial
	private static final long serialVersionUID = 1846561917192393534L;

	public LoginException(String nachricht)
	{
		super(nachricht);
	}
}
