package de.justinharder.trainharder.domain.service.dto.login;

import de.justinharder.base.domain.services.dto.GespeichertesDTO;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class GespeicherterLogin extends GespeichertesDTO<GespeicherterLogin>
{
	@NonNull
	private final String eMailAdresse;

	@NonNull
	private final String benutzername;

	public GespeicherterLogin(String id, @NonNull String eMailAdresse, @NonNull String benutzername)
	{
		super(id);
		this.eMailAdresse = eMailAdresse;
		this.benutzername = benutzername;
	}
}
