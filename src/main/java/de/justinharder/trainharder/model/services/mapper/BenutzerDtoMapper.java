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
			benutzer.getName().getVorname(),
			benutzer.getName().getNachname(),
			benutzer.getLebensalter(),
			benutzer.getBenutzerangabe().getKraftlevel().name(),
			benutzer.getBenutzerangabe().getGeschlecht().name(),
			benutzer.getBenutzerangabe().getErfahrung().name(),
			benutzer.getBenutzerangabe().getErnaehrung().name(),
			benutzer.getBenutzerangabe().getSchlafqualitaet().name(),
			benutzer.getBenutzerangabe().getStress().name(),
			benutzer.getBenutzerangabe().getDoping().name(),
			benutzer.getBenutzerangabe().getRegenerationsfaehigkeit().name(),
			authentifizierungDtoMapper.konvertiere(benutzer.getAuthentifizierung()));
	}
}
