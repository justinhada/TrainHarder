package de.justinharder.trainharder.model.domain.exceptions;

public class MailVergebenException extends Exception
{
	private static final long serialVersionUID = -9139387131091037014L;

	public MailVergebenException(final String nachricht)
	{
		super(nachricht);
	}
}
