package de.justinharder.dietharder.domain.services.dto.hautfaltendicke;

import de.justinharder.base.domain.services.dto.GespeichertesDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class GespeicherteHautfaltendicke extends GespeichertesDTO<GespeicherteHautfaltendicke>
{
	@NonNull
	private final String datum;

	@NonNull
	private final String brustfalte;

	@NonNull
	private final String bauchfalte;

	@NonNull
	private final String beinfalte;

	@NonNull
	private final String hueftfalte;

	@NonNull
	private final String achselfalte;

	@NonNull
	private final String trizepsfalte;

	@NonNull
	private final String rueckenfalte;

	@NonNull
	private final String koerperfettAnteil;

	public GespeicherteHautfaltendicke(
		String id,
		@NonNull String datum,
		@NonNull String brustfalte,
		@NonNull String bauchfalte,
		@NonNull String beinfalte,
		@NonNull String hueftfalte,
		@NonNull String achselfalte,
		@NonNull String trizepsfalte,
		@NonNull String rueckenfalte,
		@NonNull String koerperfettAnteil)
	{
		super(id);
		this.datum = datum;
		this.brustfalte = brustfalte;
		this.bauchfalte = bauchfalte;
		this.beinfalte = beinfalte;
		this.hueftfalte = hueftfalte;
		this.achselfalte = achselfalte;
		this.trizepsfalte = trizepsfalte;
		this.rueckenfalte = rueckenfalte;
		this.koerperfettAnteil = koerperfettAnteil;
	}
}
