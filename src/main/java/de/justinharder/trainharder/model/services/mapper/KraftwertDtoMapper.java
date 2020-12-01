package de.justinharder.trainharder.model.services.mapper;

import de.justinharder.trainharder.model.domain.Kraftwert;
import de.justinharder.trainharder.view.dto.KraftwertDto;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class KraftwertDtoMapper
{
	private static final DateTimeFormatter DATUMSFORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

	public List<KraftwertDto> mappeAlle(List<Kraftwert> kraftwerte)
	{
		return kraftwerte
			.stream()
			.map(this::mappe)
			.collect(Collectors.toList());
	}

	public KraftwertDto mappe(Kraftwert kraftwert)
	{
		return new KraftwertDto(
			kraftwert.getPrimaerschluessel().getId().toString(),
			kraftwert.getGewicht(),
			kraftwert.getKoerpergewicht(),
			kraftwert.getDatum().format(DATUMSFORMAT),
			kraftwert.getWiederholungen().getWert());
	}
}
