package de.justinharder.old.domain.model.exceptions;

import java.io.Serial;

public class KraftwertException extends Exception
{
	@Serial
	private static final long serialVersionUID = 7054585568041971780L;

	public KraftwertException(String nachricht)
	{
		super(nachricht);
	}
}
