package de.justinharder.trainharder.model.services.mapper;

import de.justinharder.trainharder.model.domain.Koerpermessung;
import de.justinharder.trainharder.view.dto.KoerpermessungDto;
import lombok.NonNull;

import javax.inject.Inject;
import java.time.format.DateTimeFormatter;

public class KoerpermessungDtoMapper implements DtoMapper<Koerpermessung, KoerpermessungDto>
{
	private final KoerpermasseDtoMapper koerpermasseDtoMapper;

	@Inject
	public KoerpermessungDtoMapper(KoerpermasseDtoMapper koerpermasseDtoMapper)
	{
		this.koerpermasseDtoMapper = koerpermasseDtoMapper;
	}

	@Override
	public KoerpermessungDto mappe(@NonNull Koerpermessung koerpermessung)
	{
		return new KoerpermessungDto(
			koerpermessung.getPrimaerschluessel().getId().toString(),
			koerpermessung.getDatum().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
			koerpermasseDtoMapper.mappe(koerpermessung.getKoerpermasse()),
			koerpermessung.getKalorieneinnahme(),
			koerpermessung.getKalorienverbrauch());
	}
}