package de.justinharder.dietharder.domain.model.exceptions;

import java.io.Serial;

public class ZielException extends RuntimeException
{
	@Serial
	private static final long serialVersionUID = 657123934541956296L;

	public ZielException(String nachricht)
	{
		super(nachricht);
	}
}
