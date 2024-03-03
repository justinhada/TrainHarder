package de.justinharder.trainharder.domain.model.exceptions;

import java.io.Serial;

public class RegistrierungException extends Exception
{
	@Serial
	private static final long serialVersionUID = 3877814037306699990L;

	public RegistrierungException(String nachricht)
	{
		super(nachricht);
	}
}
