package de.justinharder.trainharder.domain.services.dto.benutzer;

import de.justinharder.base.domain.services.dto.GespeichertesDTO;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class GespeicherterBenutzer extends GespeichertesDTO<GespeicherterBenutzer>
{
	@NonNull
	private final String vorname;

	@NonNull
	private final String nachname;

	public GespeicherterBenutzer(String id, @NonNull String vorname, @NonNull String nachname)
	{
		super(id);
		this.vorname = vorname;
		this.nachname = nachname;
	}
}
