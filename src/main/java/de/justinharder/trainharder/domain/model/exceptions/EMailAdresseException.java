package de.justinharder.trainharder.domain.model.exceptions;

import java.io.Serial;

public class EMailAdresseException extends RuntimeException
{
	@Serial
	private static final long serialVersionUID = -2987353471963408549L;

	public EMailAdresseException(String nachricht)
	{
		super(nachricht);
	}
}
