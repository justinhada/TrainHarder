package de.justinharder.dietharder.domain.model.exceptions;

import java.io.Serial;

public class KoerpergewichtException extends RuntimeException
{
	@Serial
	private static final long serialVersionUID = -6780980750545692142L;

	public KoerpergewichtException(String nachricht)
	{
		super(nachricht);
	}
}
