package de.justinharder.dietharder.domain.model.exceptions.umfaenge;

import java.io.Serial;

public class BauchUmfangException extends RuntimeException
{
	@Serial
	private static final long serialVersionUID = 9039074971530014845L;

	public BauchUmfangException(String nachricht)
	{
		super(nachricht);
	}
}
