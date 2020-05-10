package de.justinharder.trainharder.model.domain.exceptions;

public class AuthentifizierungNichtGefundenException extends Exception
{
	private static final long serialVersionUID = 5139676572509720030L;

	public AuthentifizierungNichtGefundenException(final String nachricht)
	{
		super(nachricht);
	}
}
