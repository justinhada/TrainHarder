package de.justinharder.dietharder.domain.model.exceptions;

import java.io.Serial;

public class KoerperfettAnteilException extends RuntimeException
{
	@Serial
	private static final long serialVersionUID = -7785362637572138728L;

	public KoerperfettAnteilException(String nachricht)
	{
		super(nachricht);
	}
}
