package de.justinharder.dietharder.domain.model.exceptions.umfaenge;

import java.io.Serial;

public class LinkerOberschenkelUmfangException extends RuntimeException
{
	@Serial
	private static final long serialVersionUID = -1163835132269446102L;

	public LinkerOberschenkelUmfangException(String nachricht)
	{
		super(nachricht);
	}
}
