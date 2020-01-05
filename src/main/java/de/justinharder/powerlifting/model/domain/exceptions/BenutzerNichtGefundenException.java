package de.justinharder.powerlifting.model.domain.exceptions;

public class BenutzerNichtGefundenException extends Exception
{
	private static final long serialVersionUID = -8124391141573256519L;

	public BenutzerNichtGefundenException(final String nachricht)
	{
		super(nachricht);
	}
}
