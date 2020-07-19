package de.justinharder.trainharder.model.services.authentifizierung;

import javax.inject.Inject;

import de.justinharder.trainharder.model.domain.exceptions.LoginException;
import de.justinharder.trainharder.model.repository.AuthentifizierungRepository;
import de.justinharder.trainharder.model.services.mapper.AuthentifizierungDtoMapper;
import de.justinharder.trainharder.view.dto.AuthentifizierungDto;

public class LoginService
{
	private final AuthentifizierungRepository authentifizierungRepository;
	private final AuthentifizierungDtoMapper authentifizierungDtoMapper;

	@Inject
	public LoginService(
		final AuthentifizierungRepository authentifizierungRepository,
		final AuthentifizierungDtoMapper authentifizierungDtoMapper)
	{
		this.authentifizierungRepository = authentifizierungRepository;
		this.authentifizierungDtoMapper = authentifizierungDtoMapper;
	}

	public AuthentifizierungDto login(final String benutzername, final String passwort) throws LoginException
	{
		return authentifizierungRepository.login(benutzername, passwort)
			.map(authentifizierungDtoMapper::konvertiere)
			.orElseThrow(() -> new LoginException("Der Benutzername oder das Passwort ist leider falsch!"));
	}
}
