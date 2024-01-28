package de.justinharder.dietharder.domain.model.exceptions.umfaenge;

import java.io.Serial;

public class LinkerOberarmUmfangException extends RuntimeException
{
	@Serial
	private static final long serialVersionUID = 7246796935790007789L;

	public LinkerOberarmUmfangException(String nachricht)
	{
		super(nachricht);
	}
}
