package de.justinharder.trainharder.model.services.mapper;

import de.justinharder.trainharder.model.domain.Benutzer;
import de.justinharder.trainharder.view.dto.BenutzerDto;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class BenutzerDtoMapper
{
	private final AuthentifizierungDtoMapper authentifizierungDtoMapper;
	private final KoerpermessungDtoMapper koerpermessungDtoMapper;

	@Inject
	public BenutzerDtoMapper(
		AuthentifizierungDtoMapper authentifizierungDtoMapper,
		KoerpermessungDtoMapper koerpermessungDtoMapper)
	{
		this.authentifizierungDtoMapper = authentifizierungDtoMapper;
		this.koerpermessungDtoMapper = koerpermessungDtoMapper;
	}

	public List<BenutzerDto> mappeAlle(List<Benutzer> benutzer)
	{
		return benutzer.stream()
			.map(this::mappe)
			.collect(Collectors.toList());
	}

	public BenutzerDto mappe(Benutzer benutzer)
	{
		return new BenutzerDto(
			benutzer.getPrimaerschluessel().getId().toString(),
			benutzer.getName().getVorname(),
			benutzer.getName().getNachname(),
			benutzer.getGeburtsdatum(),
			benutzer.getBenutzerangabe().getKraftlevel().name(),
			benutzer.getBenutzerangabe().getGeschlecht().name(),
			benutzer.getBenutzerangabe().getErfahrung().name(),
			benutzer.getBenutzerangabe().getErnaehrung().name(),
			benutzer.getBenutzerangabe().getSchlafqualitaet().name(),
			benutzer.getBenutzerangabe().getStress().name(),
			benutzer.getBenutzerangabe().getDoping().name(),
			benutzer.getBenutzerangabe().getRegenerationsfaehigkeit().name(),
			authentifizierungDtoMapper.mappe(benutzer.getAuthentifizierung()),
			koerpermessungDtoMapper.mappeAlle(benutzer.getKoerpermessungen()));
	}
}
