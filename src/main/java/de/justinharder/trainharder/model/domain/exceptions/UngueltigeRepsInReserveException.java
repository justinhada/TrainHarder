package de.justinharder.trainharder.model.domain.exceptions;

public class UngueltigeRepsInReserveException extends Exception
{
	private static final long serialVersionUID = 7173929133491946535L;

	public UngueltigeRepsInReserveException(String nachricht)
	{
		super(nachricht);
	}
}
