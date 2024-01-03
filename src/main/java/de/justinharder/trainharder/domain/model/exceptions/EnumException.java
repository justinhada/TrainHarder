package de.justinharder.trainharder.domain.model.exceptions;

public class EnumException extends RuntimeException
{
	private static final long serialVersionUID = -2863779655877734687L;

	public EnumException(String nachricht)
	{
		super(nachricht);
	}
}
