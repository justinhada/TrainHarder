package de.justinharder.trainharder.model.services.mapper;

import de.justinharder.trainharder.model.domain.Belastungsfaktor;
import de.justinharder.trainharder.view.dto.BelastungsfaktorDto;
import lombok.NonNull;

public class BelastungsfaktorDtoMapper implements DtoMapper<Belastungsfaktor, BelastungsfaktorDto>
{
	@Override
	public BelastungsfaktorDto mappe(@NonNull Belastungsfaktor belastungsfaktor)
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
