package de.justinharder.trainharder.domain.model.exceptions;

public class AuthentifizierungNichtGefundenException extends Exception
{
	private static final long serialVersionUID = 5139676572509720030L;

	public AuthentifizierungNichtGefundenException(String nachricht)
	{
		super(nachricht);
	}
}
