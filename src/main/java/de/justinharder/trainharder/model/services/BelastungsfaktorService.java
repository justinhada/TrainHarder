package de.justinharder.trainharder.model.services;

import de.justinharder.trainharder.model.domain.Belastungsfaktor;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.model.domain.exceptions.BelastungsfaktorNichtGefundenException;
import de.justinharder.trainharder.model.repository.BelastungsfaktorRepository;
import de.justinharder.trainharder.model.services.mapper.BelastungsfaktorDtoMapper;
import de.justinharder.trainharder.view.dto.BelastungsfaktorDto;
import lombok.NonNull;

import javax.inject.Inject;

public class BelastungsfaktorService
{
	private final BelastungsfaktorRepository belastungsfaktorRepository;
	private final BelastungsfaktorDtoMapper belastungsfaktorDtoMapper;

	@Inject
	public BelastungsfaktorService(
		BelastungsfaktorRepository belastungsfaktorRepository,
		BelastungsfaktorDtoMapper belastungsfaktorDtoMapper)
	{
		this.belastungsfaktorRepository = belastungsfaktorRepository;
		this.belastungsfaktorDtoMapper = belastungsfaktorDtoMapper;
	}

	public BelastungsfaktorDto ermittleZuId(@NonNull String id) throws BelastungsfaktorNichtGefundenException
	{
		return belastungsfaktorRepository.ermittleZuId(new Primaerschluessel(id))
			.map(belastungsfaktorDtoMapper::mappe)
			.orElseThrow(FehlermeldungService.wirfBelastungsfaktorNichtGefundenException("der ID", id));
	}

	public BelastungsfaktorDto speichereBelastungsfaktor(@NonNull BelastungsfaktorDto belastungsfaktorDto)
	{
		return belastungsfaktorDtoMapper.mappe(belastungsfaktorRepository
			.speichereBelastungsfaktor(new Belastungsfaktor(
				new Primaerschluessel(),
				belastungsfaktorDto.getSquat(),
				belastungsfaktorDto.getBenchpress(),
				belastungsfaktorDto.getDeadlift(),
				belastungsfaktorDto.getTriceps(),
				belastungsfaktorDto.getChest(),
				belastungsfaktorDto.getCore(),
				belastungsfaktorDto.getBack(),
				belastungsfaktorDto.getBiceps(),
				belastungsfaktorDto.getGlutes(),
				belastungsfaktorDto.getQuads(),
				belastungsfaktorDto.getHamstrings(),
				belastungsfaktorDto.getShoulder())));
	}
}
