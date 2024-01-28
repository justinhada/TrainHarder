package de.justinharder.dietharder.domain.model.exceptions.umfaenge;

import java.io.Serial;

public class LinkerUnterarmUmfangException extends RuntimeException
{
	@Serial
	private static final long serialVersionUID = 5227122565206710991L;

	public LinkerUnterarmUmfangException(String nachricht)
	{
		super(nachricht);
	}
}
