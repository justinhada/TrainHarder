package de.justinharder.old.domain.services.mapper;

import de.justinharder.old.domain.model.Benutzer;
import de.justinharder.old.domain.services.dto.BenutzerDto;
import de.justinharder.old.domain.services.dto.BenutzerangabeDto;
import de.justinharder.old.domain.services.dto.NameDto;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import lombok.NonNull;

@Dependent
public class BenutzerDtoMapper implements DtoMapper<Benutzer, BenutzerDto>
{
	private final AuthentifizierungDtoMapper authentifizierungDtoMapper;
	private final KoerpermessungDtoMapper koerpermessungDtoMapper;

	@Inject
	public BenutzerDtoMapper(AuthentifizierungDtoMapper authentifizierungDtoMapper, KoerpermessungDtoMapper koerpermessungDtoMapper)
	{
		this.authentifizierungDtoMapper = authentifizierungDtoMapper;
		this.koerpermessungDtoMapper = koerpermessungDtoMapper;
	}

	@Override
	public BenutzerDto mappe(@NonNull Benutzer benutzer)
	{
		return new BenutzerDto(
			benutzer.getId().getWert().toString(),
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
