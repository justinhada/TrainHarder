package de.justinharder.trainharder.model.services.mapper;

import java.util.List;
import java.util.stream.Collectors;

import de.justinharder.trainharder.model.domain.Authentifizierung;
import de.justinharder.trainharder.view.dto.AuthentifizierungDto;

public class AuthentifizierungDtoMapper
{
	public List<AuthentifizierungDto> konvertiereAlle(final List<Authentifizierung> authentifizierungen)
	{
		return authentifizierungen
			.stream()
			.map(this::konvertiere)
			.collect(Collectors.toList());
	}

	public AuthentifizierungDto konvertiere(final Authentifizierung authentifizierung)
	{
		return new AuthentifizierungDto(
			authentifizierung.getPrimaerschluessel().getId().toString(),
			authentifizierung.getMail(),
			authentifizierung.getBenutzername(),
			authentifizierung.getPasswort());
	}
}
