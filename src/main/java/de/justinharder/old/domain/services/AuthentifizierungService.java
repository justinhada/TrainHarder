package de.justinharder.old.domain.services;

import de.justinharder.base.domain.model.attribute.ID;
import de.justinharder.old.domain.repository.AuthentifizierungRepository;
import de.justinharder.old.domain.services.dto.AuthentifizierungDto;
import de.justinharder.old.domain.services.mapper.AuthentifizierungDtoMapper;
import de.justinharder.old.domain.model.exceptions.AuthentifizierungException;
import jakarta.enterprise.context.Dependent;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Dependent
@RequiredArgsConstructor
public class AuthentifizierungService
{
	@NonNull
	private final AuthentifizierungRepository authentifizierungRepository;

	@NonNull
	private final AuthentifizierungDtoMapper authentifizierungDtoMapper;

	public AuthentifizierungDto finde(@NonNull String id) throws AuthentifizierungException
	{
		return authentifizierungRepository.finde(new ID(id))
			.map(authentifizierungDtoMapper::mappe)
			.orElseThrow(() -> new AuthentifizierungException(
				"Die Authentifizierung mit der ID %s existiert nicht!".formatted(id)));
	}

	public AuthentifizierungDto findeMitBenutzer(@NonNull String benutzerId)
		throws AuthentifizierungException
	{
		return authentifizierungRepository.findeMitBenutzer(new ID(benutzerId))
			.map(authentifizierungDtoMapper::mappe)
			.orElseThrow(() -> new AuthentifizierungException(
				"Die Authentifizierung mit der BenutzerID %s existiert nicht!".formatted(benutzerId)));
	}

	public AuthentifizierungDto findeMitBenutzername(@NonNull String benutzername)
		throws AuthentifizierungException
	{
		return authentifizierungRepository.findeMitBenutzername(benutzername)
			.map(authentifizierungDtoMapper::mappe)
			.orElseThrow(() -> new AuthentifizierungException(
				"Die Authentifizierung mit dem Benutzernamen %s existiert nicht!".formatted(benutzername)));
	}
}
