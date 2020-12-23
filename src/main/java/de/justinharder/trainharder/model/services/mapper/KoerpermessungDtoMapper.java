package de.justinharder.trainharder.model.services.mapper;

import de.justinharder.trainharder.model.domain.Koerpermessung;
import de.justinharder.trainharder.view.dto.KoerpermessungDto;
import lombok.NonNull;

import java.time.format.DateTimeFormatter;

public class KoerpermessungDtoMapper implements DtoMapper<Koerpermessung, KoerpermessungDto>
{
	@Override
	public KoerpermessungDto mappe(@NonNull Koerpermessung koerpermessung)
	{
		return new KoerpermessungDto(
			koerpermessung.getPrimaerschluessel().getId().toString(),
			koerpermessung.getDatum().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
			koerpermessung.getKoerpermasse().getKoerpergroesse(),
			koerpermessung.getKoerpermasse().getKoerpergewicht(),
			koerpermessung.getKoerpermasse().getKoerperfettAnteil(),
			koerpermessung.getKalorieneinnahme(),
			koerpermessung.getKalorienverbrauch());
	}
}
