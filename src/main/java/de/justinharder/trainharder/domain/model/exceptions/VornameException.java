package de.justinharder.trainharder.domain.model.exceptions;

import java.io.Serial;

public class VornameException extends RuntimeException
{
	@Serial
	private static final long serialVersionUID = -1332855010060110226L;

	public VornameException(String nachricht)
	{
		super(nachricht);
	}
}
