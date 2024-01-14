package de.justinharder.dietharder.domain.services.dto.ziel;

import de.justinharder.dietharder.domain.services.dto.datensatz.GespeicherterDatensatz;

public class GespeichertesZiel extends GespeicherterDatensatz
{
	public GespeichertesZiel(String id, String datum, String koerpergewicht, String koerperfettAnteil)
	{
		super(id, datum, koerpergewicht, koerperfettAnteil);
	}
}
