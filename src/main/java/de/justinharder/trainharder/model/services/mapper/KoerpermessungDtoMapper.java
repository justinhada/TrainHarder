package de.justinharder.trainharder.model.services.mapper;

import de.justinharder.trainharder.model.domain.Koerpermessung;
import de.justinharder.trainharder.view.dto.KoerpermessungDto;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class KoerpermessungDtoMapper
{
	public List<KoerpermessungDto> mappeAlle(List<Koerpermessung> koerpermessungen)
	{
		return koerpermessungen
			.stream()
			.map(this::mappe)
			.collect(Collectors.toList());
	}

	public KoerpermessungDto mappe(Koerpermessung koerpermessung)
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
