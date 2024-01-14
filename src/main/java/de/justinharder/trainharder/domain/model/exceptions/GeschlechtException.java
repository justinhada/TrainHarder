package de.justinharder.trainharder.domain.model.exceptions;

import java.io.Serial;

public class GeschlechtException extends RuntimeException
{
	@Serial
	private static final long serialVersionUID = 7918190970735152209L;

	public GeschlechtException(String nachricht)
	{
		super(nachricht);
	}
}
