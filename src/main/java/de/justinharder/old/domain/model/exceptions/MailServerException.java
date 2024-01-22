package de.justinharder.old.domain.model.exceptions;

import java.io.Serial;

public class MailServerException extends RuntimeException
{
	@Serial
	private static final long serialVersionUID = 7319896133971648787L;

	public MailServerException(String nachricht, Throwable ursache)
	{
		super(nachricht, ursache);
	}
}
