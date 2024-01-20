package de.justinharder.dietharder.domain.model.exceptions.hautfaltendicke;

import java.io.Serial;

public class BeinfalteException extends RuntimeException
{
	@Serial
	private static final long serialVersionUID = -1257875502091405442L;

	public BeinfalteException(String nachricht)
	{
		super(nachricht);
	}
}
