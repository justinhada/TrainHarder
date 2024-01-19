package de.justinharder.dietharder.domain.model.exceptions;

import java.io.Serial;

public class MessungException extends Exception
{
	@Serial
	private static final long serialVersionUID = -7062474769709567654L;

	public MessungException(String nachricht)
	{
		super(nachricht);
	}
}
