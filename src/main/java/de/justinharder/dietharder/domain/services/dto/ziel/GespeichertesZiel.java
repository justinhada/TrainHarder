package de.justinharder.dietharder.domain.services.dto.ziel;

import de.justinharder.dietharder.domain.services.dto.datensatz.GespeicherterDatensatz;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class GespeichertesZiel extends GespeicherterDatensatz<GespeichertesZiel>
{
	public GespeichertesZiel(String id, String datum, String koerpergewicht, String koerperfettAnteil)
	{
		super(id, datum, koerpergewicht, koerperfettAnteil);
	}
}
