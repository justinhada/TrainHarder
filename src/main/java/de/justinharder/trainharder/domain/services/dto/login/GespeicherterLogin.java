package de.justinharder.trainharder.domain.services.dto.login;

import de.justinharder.base.domain.services.dto.GespeichertesDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
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
