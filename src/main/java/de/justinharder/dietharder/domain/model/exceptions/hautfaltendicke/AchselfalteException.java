package de.justinharder.dietharder.domain.model.exceptions.hautfaltendicke;

import java.io.Serial;

public class AchselfalteException extends RuntimeException
{
	@Serial
	private static final long serialVersionUID = 2265770858921656442L;

	public AchselfalteException(String nachricht)
	{
		super(nachricht);
	}
}
