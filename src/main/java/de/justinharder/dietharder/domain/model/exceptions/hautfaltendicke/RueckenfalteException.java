package de.justinharder.dietharder.domain.model.exceptions.hautfaltendicke;

import java.io.Serial;

public class RueckenfalteException extends RuntimeException
{
	@Serial
	private static final long serialVersionUID = 6781393810797234590L;

	public RueckenfalteException(String nachricht)
	{
		super(nachricht);
	}
}
