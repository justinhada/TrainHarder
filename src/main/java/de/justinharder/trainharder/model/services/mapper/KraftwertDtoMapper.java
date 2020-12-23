package de.justinharder.trainharder.model.services.mapper;

import de.justinharder.trainharder.model.domain.Kraftwert;
import de.justinharder.trainharder.view.dto.KraftwertDto;
import lombok.NonNull;

import java.time.format.DateTimeFormatter;

public class KraftwertDtoMapper implements DtoMapper<Kraftwert, KraftwertDto>
{
	private static final DateTimeFormatter DATUMSFORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

	@Override
	public KraftwertDto mappe(@NonNull Kraftwert kraftwert)
	{
		return new KraftwertDto(
			kraftwert.getPrimaerschluessel().getId().toString(),
			kraftwert.getGewicht(),
			kraftwert.getKoerpergewicht(),
			kraftwert.getDatum().format(DATUMSFORMAT),
			kraftwert.getWiederholungen().getWert());
	}
}
