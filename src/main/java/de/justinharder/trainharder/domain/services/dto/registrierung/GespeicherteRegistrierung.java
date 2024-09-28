package de.justinharder.trainharder.domain.services.dto.registrierung;

import de.justinharder.base.domain.services.dto.GespeichertesDTO;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class GespeicherteRegistrierung extends GespeichertesDTO<GespeicherteRegistrierung>
{
	@NonNull
	private final String eMailAdresse;

	public GespeicherteRegistrierung(String id, @NonNull String eMailAdresse)
	{
		super(id);
		this.eMailAdresse = eMailAdresse;
	}
}
