package de.justinharder.trainharder.model.services.mapper;

import de.justinharder.trainharder.model.domain.Benutzer;
import de.justinharder.trainharder.view.dto.BenutzerDto;
import de.justinharder.trainharder.view.dto.BenutzerangabeDto;
import de.justinharder.trainharder.view.dto.NameDto;
import lombok.NonNull;

import javax.inject.Inject;

public class BenutzerDtoMapper implements DtoMapper<Benutzer, BenutzerDto>
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

	@Override
	public BenutzerDto mappe(@NonNull Benutzer benutzer)
	{
		return new BenutzerDto(
			benutzer.getPrimaerschluessel().getId().toString(),
			new NameDto(benutzer.getName().getVorname(), benutzer.getName().getNachname()),
			benutzer.getGeburtsdatum(),
			new BenutzerangabeDto(
				benutzer.getBenutzerangabe().getGeschlecht().name(),
				benutzer.getBenutzerangabe().getErfahrung().name(),
				benutzer.getBenutzerangabe().getErnaehrung().name(),
				benutzer.getBenutzerangabe().getSchlafqualitaet().name(),
				benutzer.getBenutzerangabe().getStress().name(),
				benutzer.getBenutzerangabe().getDoping().name(),
				benutzer.getBenutzerangabe().getRegenerationsfaehigkeit().name())
				.setKraftlevel(benutzer.getBenutzerangabe().getKraftlevel().name()),
			authentifizierungDtoMapper.mappe(benutzer.getAuthentifizierung()),
			koerpermessungDtoMapper.mappeAlle(benutzer.getKoerpermessungen()));
	}
}
