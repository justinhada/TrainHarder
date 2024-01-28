package de.justinharder.dietharder.domain.model.exceptions.umfaenge;

import java.io.Serial;

public class LinkerUnterschenkelUmfangException extends RuntimeException
{
	@Serial
	private static final long serialVersionUID = 1371972898357020374L;

	public LinkerUnterschenkelUmfangException(String nachricht)
	{
		super(nachricht);
	}
}
