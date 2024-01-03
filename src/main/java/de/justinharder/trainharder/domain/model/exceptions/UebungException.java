package de.justinharder.trainharder.domain.model.exceptions;

import java.io.Serial;

public class UebungException extends Exception
{
	@Serial
	private static final long serialVersionUID = -8331124212259619640L;

	public UebungException(String nachricht)
	{
		super(nachricht);
	}
}
