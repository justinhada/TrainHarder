package de.justinharder.powerlifting.model.domain.exceptions;

public class UngueltigesMaximumException extends Exception
{
	private static final long serialVersionUID = 7503469787236516845L;

	public UngueltigesMaximumException(final String nachricht)
	{
		super(nachricht);
	}
}
