package de.justinharder.dietharder.domain.model.exceptions.hautfaltendicke;

import java.io.Serial;

public class BrustfalteException extends RuntimeException
{
	@Serial
	private static final long serialVersionUID = -2086815925533284706L;

	public BrustfalteException(String nachricht)
	{
		super(nachricht);
	}
}
