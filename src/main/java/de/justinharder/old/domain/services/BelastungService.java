package de.justinharder.old.domain.services;

import de.justinharder.old.domain.model.Belastung;
import de.justinharder.old.domain.model.attribute.GrunduebungBelastung;
import de.justinharder.base.domain.model.attribute.ID;
import de.justinharder.old.domain.model.attribute.OberkoerperBelastung;
import de.justinharder.old.domain.model.attribute.UnterkoerperBelastung;
import de.justinharder.old.domain.model.exceptions.BelastungException;
import de.justinharder.old.domain.repository.BelastungRepository;
import de.justinharder.old.domain.services.dto.BelastungDto;
import de.justinharder.old.domain.services.mapper.BelastungDtoMapper;
import jakarta.enterprise.context.Dependent;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Dependent
@RequiredArgsConstructor
public class BelastungService
{
	@NonNull
	private final BelastungRepository belastungRepository;

	@NonNull
	private final BelastungDtoMapper belastungDtoMapper;

	public BelastungDto finde(@NonNull String id) throws BelastungException
	{
		return belastungRepository.finde(new ID(id))
			.map(belastungDtoMapper::mappe)
			.orElseThrow(() -> new BelastungException(
				"Der Belastungsfaktor mit " + "der ID" + " \"" + id + "\" existiert nicht!"));
	}

	public BelastungDto speichere(@NonNull BelastungDto belastungDto)
	{
		var belastung = belastungRepository.finde(new ID(belastungDto.getId()))
			.map(b -> b
				.setGrunduebungBelastung(new GrunduebungBelastung(
					Double.parseDouble(belastungDto.getGrunduebungBelastung().getSquat()),
					Double.parseDouble(belastungDto.getGrunduebungBelastung().getBenchpress()),
					Double.parseDouble(belastungDto.getGrunduebungBelastung().getDeadlift())))
				.setOberkoerperBelastung(new OberkoerperBelastung(
					Double.parseDouble(belastungDto.getOberkoerperBelastung().getTriceps()),
					Double.parseDouble(belastungDto.getOberkoerperBelastung().getChest()),
					Double.parseDouble(belastungDto.getOberkoerperBelastung().getCore()),
					Double.parseDouble(belastungDto.getOberkoerperBelastung().getBack()),
					Double.parseDouble(belastungDto.getOberkoerperBelastung().getBiceps()),
					Double.parseDouble(belastungDto.getOberkoerperBelastung().getShoulder())))
				.setUnterkoerperBelastung(new UnterkoerperBelastung(
					Double.parseDouble(belastungDto.getUnterkoerperBelastung().getGlutes()),
					Double.parseDouble(belastungDto.getUnterkoerperBelastung().getQuads()),
					Double.parseDouble(belastungDto.getUnterkoerperBelastung().getHamstrings()))))
			.orElse(new Belastung(
				new ID(),
				new GrunduebungBelastung(
					Double.parseDouble(belastungDto.getGrunduebungBelastung().getSquat()),
					Double.parseDouble(belastungDto.getGrunduebungBelastung().getBenchpress()),
					Double.parseDouble(belastungDto.getGrunduebungBelastung().getDeadlift())),
				new OberkoerperBelastung(
					Double.parseDouble(belastungDto.getOberkoerperBelastung().getTriceps()),
					Double.parseDouble(belastungDto.getOberkoerperBelastung().getChest()),
					Double.parseDouble(belastungDto.getOberkoerperBelastung().getCore()),
					Double.parseDouble(belastungDto.getOberkoerperBelastung().getBack()),
					Double.parseDouble(belastungDto.getOberkoerperBelastung().getBiceps()),
					Double.parseDouble(belastungDto.getOberkoerperBelastung().getShoulder())),
				new UnterkoerperBelastung(
					Double.parseDouble(belastungDto.getUnterkoerperBelastung().getGlutes()),
					Double.parseDouble(belastungDto.getUnterkoerperBelastung().getQuads()),
					Double.parseDouble(belastungDto.getUnterkoerperBelastung().getHamstrings()))));

		belastungRepository.speichere(belastung);

		return belastungDtoMapper.mappe(belastung);
	}
}
