package de.justinharder.old.domain.services.mapper;

import de.justinharder.old.domain.model.Belastung;
import de.justinharder.old.domain.services.dto.BelastungDto;
import de.justinharder.old.domain.services.dto.GrunduebungBelastungDto;
import de.justinharder.old.domain.services.dto.OberkoerperBelastungDto;
import de.justinharder.old.domain.services.dto.UnterkoerperBelastungDto;
import jakarta.enterprise.context.Dependent;
import lombok.NonNull;

@Dependent
public class BelastungDtoMapper implements DtoMapper<Belastung, BelastungDto>
{
	@Override
	public BelastungDto mappe(@NonNull Belastung belastung)
	{
		return new BelastungDto(
			belastung.getId().getWert().toString(),
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
