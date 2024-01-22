package de.justinharder.old.domain.model.exceptions;

import java.io.Serial;

public class AuthentifizierungException extends Exception
{
	@Serial
	private static final long serialVersionUID = 5139676572509720030L;

	public AuthentifizierungException(String nachricht)
	{
		super(nachricht);
	}
}
