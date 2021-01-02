package de.justinharder.trainharder.model.services.mapper;

import de.justinharder.trainharder.model.domain.Belastungsfaktor;
import de.justinharder.trainharder.view.dto.BelastungsfaktorDto;
import de.justinharder.trainharder.view.dto.GrunduebungBelastungDto;
import de.justinharder.trainharder.view.dto.OberkoerperBelastungDto;
import de.justinharder.trainharder.view.dto.UnterkoerperBelastungDto;
import lombok.NonNull;

public class BelastungsfaktorDtoMapper implements DtoMapper<Belastungsfaktor, BelastungsfaktorDto>
{
	@Override
	public BelastungsfaktorDto mappe(@NonNull Belastungsfaktor belastungsfaktor)
	{
		return new BelastungsfaktorDto(
			belastungsfaktor.getPrimaerschluessel().getId().toString(),
			new GrunduebungBelastungDto(
				String.valueOf(belastungsfaktor.getGrunduebungBelastung().getSquat()),
				String.valueOf(belastungsfaktor.getGrunduebungBelastung().getBenchpress()),
				String.valueOf(belastungsfaktor.getGrunduebungBelastung().getDeadlift())),
			new OberkoerperBelastungDto(
				String.valueOf(belastungsfaktor.getOberkoerperBelastung().getTriceps()),
				String.valueOf(belastungsfaktor.getOberkoerperBelastung().getChest()),
				String.valueOf(belastungsfaktor.getOberkoerperBelastung().getCore()),
				String.valueOf(belastungsfaktor.getOberkoerperBelastung().getBack()),
				String.valueOf(belastungsfaktor.getOberkoerperBelastung().getBiceps()),
				String.valueOf(belastungsfaktor.getOberkoerperBelastung().getShoulder())),
			new UnterkoerperBelastungDto(
				String.valueOf(belastungsfaktor.getUnterkoerperBelastung().getGlutes()),
				String.valueOf(belastungsfaktor.getUnterkoerperBelastung().getQuads()),
				String.valueOf(belastungsfaktor.getUnterkoerperBelastung().getHamstrings())));
	}
}
