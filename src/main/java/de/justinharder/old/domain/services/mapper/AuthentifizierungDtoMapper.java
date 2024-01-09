package de.justinharder.old.domain.services.mapper;

import de.justinharder.old.domain.model.Authentifizierung;
import de.justinharder.old.domain.services.dto.AuthentifizierungDto;
import jakarta.enterprise.context.Dependent;
import lombok.NonNull;

@Dependent
public class AuthentifizierungDtoMapper implements DtoMapper<Authentifizierung, AuthentifizierungDto>
{
	@Override
	public AuthentifizierungDto mappe(@NonNull Authentifizierung authentifizierung)
	{
		return new AuthentifizierungDto(
			authentifizierung.getId().getWert().toString(),
			authentifizierung.getMail(),
			authentifizierung.getBenutzername());
	}
}
