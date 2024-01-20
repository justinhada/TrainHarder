package de.justinharder.dietharder.domain.model.exceptions.hautfaltendicke;

import java.io.Serial;

public class BauchfalteException extends RuntimeException
{
	@Serial
	private static final long serialVersionUID = -2364282148200058354L;

	public BauchfalteException(String nachricht)
	{
		super(nachricht);
	}
}
