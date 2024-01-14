package de.justinharder.dietharder.domain.services.dto.messung;

import de.justinharder.dietharder.domain.services.dto.datensatz.GespeicherterDatensatz;

public class GespeicherteMessung extends GespeicherterDatensatz
{
	public GespeicherteMessung(String id, String datum, String koerpergewicht, String koerperfettAnteil)
	{
		super(id, datum, koerpergewicht, koerperfettAnteil);
	}
}
