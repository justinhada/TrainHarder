package de.justinharder.trainharder.model.domain.exceptions;

public class MailServerException extends RuntimeException
{
	private static final long serialVersionUID = 7319896133971648787L;

	public MailServerException(String nachricht, Throwable ursache)
	{
		super(nachricht, ursache);
	}
}