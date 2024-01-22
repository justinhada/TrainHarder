package de.justinharder.old.domain.model.exceptions;

import java.io.Serial;

public class BelastungException extends Exception
{
	@Serial
	private static final long serialVersionUID = 8618700706284780379L;

	public BelastungException(String nachricht)
	{
		super(nachricht);
	}
}
