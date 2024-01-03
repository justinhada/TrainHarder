package de.justinharder.trainharder.domain.services.mapper;

import de.justinharder.trainharder.domain.model.Authentifizierung;
import de.justinharder.trainharder.domain.services.dto.AuthentifizierungDto;
import jakarta.enterprise.context.Dependent;
import lombok.NonNull;

@Dependent
public class AuthentifizierungDtoMapper implements DtoMapper<Authentifizierung, AuthentifizierungDto>
{
	@Override
	public AuthentifizierungDto mappe(@NonNull Authentifizierung authentifizierung)
	{
		return new AuthentifizierungDto(
			authentifizierung.getPrimaerschluessel().getId().toString(),
			authentifizierung.getMail(),
			authentifizierung.getBenutzername());
	}
}
