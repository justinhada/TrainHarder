package de.justinharder.dietharder.domain.services.dto.datensatz;

import de.justinharder.base.domain.services.dto.GespeichertesDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public abstract class GespeicherterDatensatz<T extends GespeicherterDatensatz<T>> extends GespeichertesDTO<T>
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
