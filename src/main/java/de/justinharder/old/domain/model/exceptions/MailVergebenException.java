package de.justinharder.old.domain.model.exceptions;

import java.io.Serial;

public class MailVergebenException extends Exception
{
	@Serial
	private static final long serialVersionUID = -9139387131091037014L;

	public MailVergebenException(String nachricht)
	{
		super(nachricht);
	}
}
