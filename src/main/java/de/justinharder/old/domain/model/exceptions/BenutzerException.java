package de.justinharder.old.domain.model.exceptions;

import java.io.Serial;

public class BenutzerException extends RuntimeException
{
	@Serial
	private static final long serialVersionUID = -8124391141573256519L;

	public BenutzerException(String nachricht)
	{
		super(nachricht);
	}
}
