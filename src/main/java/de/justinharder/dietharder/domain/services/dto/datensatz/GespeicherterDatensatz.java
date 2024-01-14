package de.justinharder.dietharder.domain.services.dto.datensatz;

import de.justinharder.base.domain.services.dto.GespeichertesDTO;
import lombok.Getter;
import lombok.NonNull;

@Getter
public abstract class GespeicherterDatensatz extends GespeichertesDTO<GespeicherterDatensatz>
{
	@NonNull
	private final String datum;

	@NonNull
	private final String koerpergewicht;

	@NonNull
	private final String koerperfettAnteil;

	public GespeicherterDatensatz(
		String id,
		@NonNull String datum,
		@NonNull String koerpergewicht,
		@NonNull String koerperfettAnteil)
	{
		super(id);
		this.datum = datum;
		this.koerpergewicht = koerpergewicht;
		this.koerperfettAnteil = koerperfettAnteil;
	}
}
