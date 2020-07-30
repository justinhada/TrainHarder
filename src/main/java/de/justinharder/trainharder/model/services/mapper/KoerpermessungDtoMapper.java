package de.justinharder.trainharder.model.services.mapper;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import de.justinharder.trainharder.model.domain.Koerpermessung;
import de.justinharder.trainharder.view.dto.KoerpermessungDto;

public class KoerpermessungDtoMapper
{
	public List<KoerpermessungDto> konvertiereAlle(final List<Koerpermessung> koerpermessungen)
	{
		return koerpermessungen
			.stream()
			.map(this::konvertiere)
			.collect(Collectors.toList());
	}

	public KoerpermessungDto konvertiere(final Koerpermessung koerpermessung)
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
