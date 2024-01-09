package de.justinharder.old.domain.model.exceptions;

public class BelastungException extends Exception
{
	private static final long serialVersionUID = 8618700706284780379L;

	public BelastungException(String nachricht)
	{
		super(nachricht);
	}
}
