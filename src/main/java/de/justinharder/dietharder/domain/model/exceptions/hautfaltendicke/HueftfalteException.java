package de.justinharder.dietharder.domain.model.exceptions.hautfaltendicke;

import java.io.Serial;

public class HueftfalteException extends RuntimeException
{
	@Serial
	private static final long serialVersionUID = 2979763189219161145L;

	public HueftfalteException(String nachricht)
	{
		super(nachricht);
	}
}
