package de.justinharder.dietharder.domain.model.exceptions.umfaenge;

import java.io.Serial;

public class HalsUmfangException extends RuntimeException
{
	@Serial
	private static final long serialVersionUID = 8675239262458131763L;

	public HalsUmfangException(String nachricht)
	{
		super(nachricht);
	}
}
