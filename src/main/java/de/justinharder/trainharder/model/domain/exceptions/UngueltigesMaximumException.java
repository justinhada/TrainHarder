package de.justinharder.trainharder.model.domain.exceptions;

public class UngueltigesMaximumException extends Exception
{
	private static final long serialVersionUID = 7503469787236516845L;

	public UngueltigesMaximumException(String nachricht)
	{
		super(nachricht);
	}
}
