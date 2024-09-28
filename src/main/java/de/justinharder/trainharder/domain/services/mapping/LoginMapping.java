package de.justinharder.trainharder.domain.services.mapping;

import de.justinharder.base.domain.services.mapping.Mapping;
import de.justinharder.trainharder.domain.model.Login;
import de.justinharder.trainharder.domain.services.dto.login.GespeicherterLogin;
import jakarta.enterprise.context.Dependent;
import lombok.NonNull;

@Dependent
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

	@Override
	public LoginPaginationResponse mappe(@NonNull List<Login> login)
	{
		return new LoginPaginationResponse(
			login.size(),
			login.stream()
				.map(this::mappe)
				.toList())
			.setSelf(null) // TODO: Implementieren, eigene URL
			.setFirst(null) // TODO: Implementieren, erste URL
			.setPrev(null) // TODO: Implementieren, vorherige URL
			.setNext(null) // TODO: Implementieren, nächste URL
			.setLast(null); // TODO: Implementieren, letzte URL
	}
}
