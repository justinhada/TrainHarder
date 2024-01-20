package de.justinharder.dietharder.domain.model.exceptions;

import java.io.Serial;

public class HautfaltendickeException extends Exception
{
	@Serial
	private static final long serialVersionUID = 1162188464413367699L;

	public HautfaltendickeException(String nachricht)
	{
		super(nachricht);
	}
}
