package de.justinharder.powerlifting.model.domain.exceptions;

public class AnmeldedatenNichtGefundenException extends Exception
{
	private static final long serialVersionUID = 5139676572509720030L;

	public AnmeldedatenNichtGefundenException(final String nachricht)
	{
		super(nachricht);
	}
}
