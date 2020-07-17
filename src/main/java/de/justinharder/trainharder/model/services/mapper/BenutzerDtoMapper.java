package de.justinharder.trainharder.model.services.mapper;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import de.justinharder.trainharder.model.domain.Benutzer;
import de.justinharder.trainharder.view.dto.BenutzerDto;

public class BenutzerDtoMapper
{
	private final AuthentifizierungDtoMapper authentifizierungDtoMapper;

	@Inject
	public BenutzerDtoMapper(final AuthentifizierungDtoMapper authentifizierungDtoMapper)
	{
		this.authentifizierungDtoMapper = authentifizierungDtoMapper;
	}

	public List<BenutzerDto> konvertiereAlle(final List<Benutzer> benutzer)
	{
		return benutzer.stream()
			.map(this::konvertiere)
			.collect(Collectors.toList());
	}

	public BenutzerDto konvertiere(final Benutzer benutzer)
	{
		return new BenutzerDto(
			benutzer.getPrimaerschluessel().getId().toString(),
			benutzer.getVorname(),
			benutzer.getNachname(),
			benutzer.getLebensalter(),
			benutzer.getKraftlevel().name(),
			benutzer.getGeschlecht().name(),
			benutzer.getErfahrung().name(),
			benutzer.getErnaehrung().name(),
			benutzer.getSchlafqualitaet().name(),
			benutzer.getStress().name(),
			benutzer.getDoping().name(),
			benutzer.getRegenerationsfaehigkeit().name(),
			authentifizierungDtoMapper.konvertiere(benutzer.getAuthentifizierung()));
	}
}
