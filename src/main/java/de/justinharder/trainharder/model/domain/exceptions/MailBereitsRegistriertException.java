package de.justinharder.trainharder.model.domain.exceptions;

public class MailBereitsRegistriertException extends Exception
{
	private static final long serialVersionUID = -9139387131091037014L;

	public MailBereitsRegistriertException(final String nachricht)
	{
		super(nachricht);
	}
}
