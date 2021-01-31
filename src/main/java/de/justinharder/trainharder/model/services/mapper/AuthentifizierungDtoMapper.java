package de.justinharder.trainharder.model.services.mapper;

import de.justinharder.trainharder.model.domain.Authentifizierung;
import de.justinharder.trainharder.view.dto.AuthentifizierungDto;
import lombok.NonNull;

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