package de.justinharder.trainharder.domain.model.exceptions;

public class AuthentifizierungException extends Exception
{
	private static final long serialVersionUID = 5139676572509720030L;

	public AuthentifizierungException(String nachricht)
	{
		super(nachricht);
	}
}
