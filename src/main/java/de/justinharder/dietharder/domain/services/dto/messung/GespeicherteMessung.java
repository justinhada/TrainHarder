package de.justinharder.dietharder.domain.services.dto.messung;

import de.justinharder.dietharder.domain.services.dto.datensatz.GespeicherterDatensatz;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class GespeicherteMessung extends GespeicherterDatensatz<GespeicherteMessung>
{
	public GespeicherteMessung(String id, String datum, String koerpergewicht, String koerperfettAnteil)
	{
		super(id, datum, koerpergewicht, koerperfettAnteil);
	}
}
