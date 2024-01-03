package de.justinharder.trainharder.domain.services;

import de.justinharder.trainharder.domain.model.Belastung;
import de.justinharder.trainharder.domain.model.embeddables.GrunduebungBelastung;
import de.justinharder.trainharder.domain.model.embeddables.OberkoerperBelastung;
import de.justinharder.trainharder.domain.model.embeddables.ID;
import de.justinharder.trainharder.domain.model.embeddables.UnterkoerperBelastung;
import de.justinharder.trainharder.domain.model.exceptions.BelastungsfaktorNichtGefundenException;
import de.justinharder.trainharder.domain.repository.BelastungRepository;
import de.justinharder.trainharder.domain.services.mapper.BelastungsfaktorDtoMapper;
import de.justinharder.trainharder.domain.services.dto.BelastungDto;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import lombok.NonNull;

@Dependent
public class BelastungsfaktorService
{
	private final BelastungRepository belastungRepository;
	private final BelastungsfaktorDtoMapper belastungsfaktorDtoMapper;

	@Inject
	public BelastungsfaktorService(BelastungRepository belastungRepository, BelastungsfaktorDtoMapper belastungsfaktorDtoMapper)
	{
		this.belastungRepository = belastungRepository;
		this.belastungsfaktorDtoMapper = belastungsfaktorDtoMapper;
	}

	public BelastungDto finde(@NonNull String id) throws BelastungsfaktorNichtGefundenException
	{
		return belastungRepository.finde(new ID(id))
			.map(belastungsfaktorDtoMapper::mappe)
			.orElseThrow(FehlermeldungService.wirfBelastungsfaktorNichtGefundenException("der ID", id));
	}

	public BelastungDto speichere(@NonNull BelastungDto belastungDto)
	{
		return belastungsfaktorDtoMapper.mappe(belastungRepository.speichere(new Belastung(
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
				Double.parseDouble(belastungDto.getUnterkoerperBelastung().getHamstrings())))));
	}
}
