package de.justinharder.old.domain.model.exceptions;

import java.io.Serial;

public class BenutzernameVergebenException extends Exception
{
	@Serial
	private static final long serialVersionUID = 7646830880003860994L;

	public BenutzernameVergebenException(String nachricht)
	{
		super(nachricht);
	}
}
