package de.justinharder.trainharder.model.domain.exceptions;

public class MailException extends Exception
{
	private static final long serialVersionUID = 8622438863522395764L;

	public MailException(final String nachricht, final Throwable fehlergrund)
	{
		super(nachricht, fehlergrund);
	}
}
