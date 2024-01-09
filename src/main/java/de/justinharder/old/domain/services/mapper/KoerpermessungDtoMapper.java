package de.justinharder.old.domain.services.mapper;

import de.justinharder.old.domain.model.Koerpermessung;
import de.justinharder.old.domain.services.dto.KoerpermessungDto;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import lombok.NonNull;

import java.time.format.DateTimeFormatter;

@Dependent
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
			koerpermessung.getId().getWert().toString(),
			koerpermessung.getDatum().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
			koerpermasseDtoMapper.mappe(koerpermessung.getKoerpermasse()),
			koerpermessung.getKalorieneinnahme(),
			koerpermessung.getKalorienverbrauch());
	}
}
