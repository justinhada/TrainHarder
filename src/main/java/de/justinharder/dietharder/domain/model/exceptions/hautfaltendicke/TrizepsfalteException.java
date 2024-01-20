package de.justinharder.dietharder.domain.model.exceptions.hautfaltendicke;

import java.io.Serial;

public class TrizepsfalteException extends RuntimeException
{
	@Serial
	private static final long serialVersionUID = 5477672745065092646L;

	public TrizepsfalteException(String nachricht)
	{
		super(nachricht);
	}
}
