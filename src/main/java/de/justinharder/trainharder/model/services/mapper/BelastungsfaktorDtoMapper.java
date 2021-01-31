package de.justinharder.trainharder.model.services.mapper;

import de.justinharder.trainharder.model.domain.Belastung;
import de.justinharder.trainharder.view.dto.BelastungDto;
import de.justinharder.trainharder.view.dto.GrunduebungBelastungDto;
import de.justinharder.trainharder.view.dto.OberkoerperBelastungDto;
import de.justinharder.trainharder.view.dto.UnterkoerperBelastungDto;
import lombok.NonNull;

public class BelastungsfaktorDtoMapper implements DtoMapper<Belastung, BelastungDto>
{
	@Override
	public BelastungDto mappe(@NonNull Belastung belastung)
	{
		return new BelastungDto(
			belastung.getPrimaerschluessel().getId().toString(),
			new GrunduebungBelastungDto(
				String.valueOf(belastung.getGrunduebungBelastung().getSquat()),
				String.valueOf(belastung.getGrunduebungBelastung().getBenchpress()),
				String.valueOf(belastung.getGrunduebungBelastung().getDeadlift())),
			new OberkoerperBelastungDto(
				String.valueOf(belastung.getOberkoerperBelastung().getTriceps()),
				String.valueOf(belastung.getOberkoerperBelastung().getChest()),
				String.valueOf(belastung.getOberkoerperBelastung().getCore()),
				String.valueOf(belastung.getOberkoerperBelastung().getBack()),
				String.valueOf(belastung.getOberkoerperBelastung().getBiceps()),
				String.valueOf(belastung.getOberkoerperBelastung().getShoulder())),
			new UnterkoerperBelastungDto(
				String.valueOf(belastung.getUnterkoerperBelastung().getGlutes()),
				String.valueOf(belastung.getUnterkoerperBelastung().getQuads()),
				String.valueOf(belastung.getUnterkoerperBelastung().getHamstrings())));
	}
}