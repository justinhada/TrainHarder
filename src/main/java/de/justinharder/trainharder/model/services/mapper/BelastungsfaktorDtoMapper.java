package de.justinharder.trainharder.model.services.mapper;

import de.justinharder.trainharder.model.domain.Belastungsfaktor;
import de.justinharder.trainharder.view.dto.BelastungsfaktorDto;

import java.util.List;
import java.util.stream.Collectors;

public class BelastungsfaktorDtoMapper
{
	public List<BelastungsfaktorDto> konvertiereAlle(final List<Belastungsfaktor> belastungsfaktoren)
	{
		return belastungsfaktoren
			.stream()
			.map(this::konvertiere)
			.collect(Collectors.toList());
	}

	public BelastungsfaktorDto konvertiere(final Belastungsfaktor belastungsfaktor)
	{
		return new BelastungsfaktorDto(
			belastungsfaktor.getPrimaerschluessel().getId().toString(),
			belastungsfaktor.getSquat(),
			belastungsfaktor.getBenchpress(),
			belastungsfaktor.getDeadlift(),
			belastungsfaktor.getTriceps(),
			belastungsfaktor.getChest(),
			belastungsfaktor.getCore(),
			belastungsfaktor.getBack(),
			belastungsfaktor.getBiceps(),
			belastungsfaktor.getGlutes(),
			belastungsfaktor.getQuads(),
			belastungsfaktor.getHamstrings(),
			belastungsfaktor.getShoulder());
	}
}
