package de.justinharder.trainharder.domain.model.exceptions;

public class MailVergebenException extends Exception
{
	private static final long serialVersionUID = -9139387131091037014L;

	public MailVergebenException(String nachricht)
	{
		super(nachricht);
	}
}
