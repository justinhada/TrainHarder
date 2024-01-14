package de.justinharder.trainharder.domain.service.mapping;

import de.justinharder.base.domain.services.mapping.Mapping;
import de.justinharder.trainharder.domain.model.Login;
import de.justinharder.trainharder.domain.service.dto.login.GespeicherterLogin;
import lombok.NonNull;

public class LoginMapping implements Mapping<Login, GespeicherterLogin>
{
	@Override
	public GespeicherterLogin mappe(@NonNull Login login)
	{
		return new GespeicherterLogin(
			login.getId().toString(),
			login.getEMailAdresse().getWert(),
			login.getBenutzername().getWert());
	}
}
