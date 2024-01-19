package de.justinharder.trainharder.domain.model.exceptions;

import java.io.Serial;

public class BenutzerException extends Exception
{
	@Serial
	private static final long serialVersionUID = -8631633674776296022L;

	public BenutzerException(String nachricht)
	{
		super(nachricht);
	}
}
