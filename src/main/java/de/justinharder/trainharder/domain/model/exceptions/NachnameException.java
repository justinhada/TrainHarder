package de.justinharder.trainharder.domain.model.exceptions;

import java.io.Serial;

public class NachnameException extends RuntimeException
{
	@Serial
	private static final long serialVersionUID = 1505995582984476430L;

	public NachnameException(String nachricht)
	{
		super(nachricht);
	}
}
