package de.justinharder.trainharder.model.domain.exceptions;

public class UebungNichtGefundenException extends Exception
{
	private static final long serialVersionUID = -8331124212259619640L;

	public UebungNichtGefundenException(String nachricht)
	{
		super(nachricht);
	}
}
