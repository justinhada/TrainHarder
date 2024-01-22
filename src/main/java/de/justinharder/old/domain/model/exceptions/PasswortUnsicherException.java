package de.justinharder.old.domain.model.exceptions;

import java.io.Serial;

public class PasswortUnsicherException extends Exception
{
	@Serial
	private static final long serialVersionUID = -8643094892502666702L;

	public PasswortUnsicherException(String nachricht)
	{
		super(nachricht);
	}
}
