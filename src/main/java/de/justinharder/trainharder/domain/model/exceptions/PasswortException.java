package de.justinharder.trainharder.domain.model.exceptions;

import java.io.Serial;

public class PasswortException extends RuntimeException
{
	@Serial
	private static final long serialVersionUID = -7724454710278424043L;

	public PasswortException(String nachricht)
	{
		super(nachricht);
	}
}
