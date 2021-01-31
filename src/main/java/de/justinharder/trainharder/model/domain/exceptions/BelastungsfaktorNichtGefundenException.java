package de.justinharder.trainharder.model.domain.exceptions;

public class BelastungsfaktorNichtGefundenException extends Exception
{
	private static final long serialVersionUID = 8618700706284780379L;

	public BelastungsfaktorNichtGefundenException(String nachricht)
	{
		super(nachricht);
	}
}