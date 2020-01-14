package de.justinharder.powerlifting.model.domain.exceptions;

public class UebungNichtGefundenException extends Exception
{
	private static final long serialVersionUID = -8331124212259619640L;

	public UebungNichtGefundenException(final String nachricht)
	{
		super(nachricht);
	}
}
