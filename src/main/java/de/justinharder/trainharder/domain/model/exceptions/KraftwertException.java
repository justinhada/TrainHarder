package de.justinharder.trainharder.domain.model.exceptions;

public class KraftwertNichtGefundenException extends Exception
{
	private static final long serialVersionUID = 7054585568041971780L;

	public KraftwertNichtGefundenException(String nachricht)
	{
		super(nachricht);
	}
}
