package de.justinharder.powerlifting.model.domain.exceptions;

public class UngueltigeRepsInReserveException extends Exception
{
	private static final long serialVersionUID = 7173929133491946535L;

	public UngueltigeRepsInReserveException(final String nachricht)
	{
		super(nachricht);
	}
}
