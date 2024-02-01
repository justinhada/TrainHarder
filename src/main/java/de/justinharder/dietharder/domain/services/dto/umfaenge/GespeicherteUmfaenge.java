package de.justinharder.dietharder.domain.services.dto.umfaenge;

import de.justinharder.base.domain.services.dto.GespeichertesDTO;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class GespeicherteUmfaenge extends GespeichertesDTO<GespeicherteUmfaenge>
{
	@NonNull
	private final String datum;

	@NonNull
	private final String halsUmfang;

	@NonNull
	private final String schulterUmfang;

	@NonNull
	private final String brustRueckenUmfang;

	@NonNull
	private final String linkerOberarmUmfang;

	@NonNull
	private final String rechterOberarmUmfang;

	@NonNull
	private final String linkerUnterarmUmfang;

	@NonNull
	private final String rechterUnterarmUmfang;

	@NonNull
	private final String bauchUmfang;

	@NonNull
	private final String hueftUmfang;

	@NonNull
	private final String linkerOberschenkelUmfang;

	@NonNull
	private final String rechterOberschenkelUmfang;

	@NonNull
	private final String linkerUnterschenkelUmfang;

	@NonNull
	private final String rechterUnterschenkelUmfang;

	public GespeicherteUmfaenge(
		String id,
		@NonNull String datum,
		@NonNull String halsUmfang,
		@NonNull String schulterUmfang,
		@NonNull String brustRueckenUmfang,
		@NonNull String linkerOberarmUmfang,
		@NonNull String rechterOberarmUmfang,
		@NonNull String linkerUnterarmUmfang,
		@NonNull String rechterUnterarmUmfang,
		@NonNull String bauchUmfang,
		@NonNull String hueftUmfang,
		@NonNull String linkerOberschenkelUmfang,
		@NonNull String rechterOberschenkelUmfang,
		@NonNull String linkerUnterschenkelUmfang,
		@NonNull String rechterUnterschenkelUmfang)
	{
		super(id);
		this.datum = datum;
		this.halsUmfang = halsUmfang;
		this.schulterUmfang = schulterUmfang;
		this.brustRueckenUmfang = brustRueckenUmfang;
		this.linkerOberarmUmfang = linkerOberarmUmfang;
		this.rechterOberarmUmfang = rechterOberarmUmfang;
		this.linkerUnterarmUmfang = linkerUnterarmUmfang;
		this.rechterUnterarmUmfang = rechterUnterarmUmfang;
		this.bauchUmfang = bauchUmfang;
		this.hueftUmfang = hueftUmfang;
		this.linkerOberschenkelUmfang = linkerOberschenkelUmfang;
		this.rechterOberschenkelUmfang = rechterOberschenkelUmfang;
		this.linkerUnterschenkelUmfang = linkerUnterschenkelUmfang;
		this.rechterUnterschenkelUmfang = rechterUnterschenkelUmfang;
	}
}
