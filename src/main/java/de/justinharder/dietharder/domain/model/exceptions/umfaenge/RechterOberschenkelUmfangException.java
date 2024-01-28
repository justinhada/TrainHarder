package de.justinharder.dietharder.domain.model.exceptions.umfaenge;

import java.io.Serial;

public class RechterOberschenkelUmfangException extends RuntimeException
{
	@Serial
	private static final long serialVersionUID = -7739211849859343052L;

	public RechterOberschenkelUmfangException(String nachricht)
	{
		super(nachricht);
	}
}
