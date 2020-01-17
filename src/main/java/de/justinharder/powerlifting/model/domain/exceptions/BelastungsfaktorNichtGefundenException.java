package de.justinharder.powerlifting.model.domain.exceptions;

public class BelastungsfaktorNichtGefundenException extends Exception
{
	private static final long serialVersionUID = 8618700706284780379L;

	public BelastungsfaktorNichtGefundenException(final String nachricht)
	{
		super(nachricht);
	}
}
