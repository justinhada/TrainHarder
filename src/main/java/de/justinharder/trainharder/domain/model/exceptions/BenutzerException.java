package de.justinharder.trainharder.domain.model.exceptions;

public class BenutzerNichtGefundenException extends Exception
{
	private static final long serialVersionUID = -8124391141573256519L;

	public BenutzerNichtGefundenException(String nachricht)
	{
		super(nachricht);
	}
}
