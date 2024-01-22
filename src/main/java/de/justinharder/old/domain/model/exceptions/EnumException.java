package de.justinharder.old.domain.model.exceptions;

import java.io.Serial;

public class EnumException extends RuntimeException
{
	@Serial
	private static final long serialVersionUID = -2863779655877734687L;

	public EnumException(String nachricht)
	{
		super(nachricht);
	}
}
