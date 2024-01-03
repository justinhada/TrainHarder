package de.justinharder.trainharder.domain.model.exceptions;

public class KraftwertException extends Exception
{
	private static final long serialVersionUID = 7054585568041971780L;

	public KraftwertException(String nachricht)
	{
		super(nachricht);
	}
}
