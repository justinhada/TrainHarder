package de.justinharder.trainharder.model.services;

import de.justinharder.trainharder.model.domain.Belastungsfaktor;
import de.justinharder.trainharder.model.domain.embeddables.GrunduebungBelastung;
import de.justinharder.trainharder.model.domain.embeddables.OberkoerperBelastung;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.model.domain.embeddables.UnterkoerperBelastung;
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
				new GrunduebungBelastung(
					Double.parseDouble(belastungsfaktorDto.getGrunduebungBelastung().getSquat()),
					Double.parseDouble(belastungsfaktorDto.getGrunduebungBelastung().getBenchpress()),
					Double.parseDouble(belastungsfaktorDto.getGrunduebungBelastung().getDeadlift())),
				new OberkoerperBelastung(
					Double.parseDouble(belastungsfaktorDto.getOberkoerperBelastung().getTriceps()),
					Double.parseDouble(belastungsfaktorDto.getOberkoerperBelastung().getChest()),
					Double.parseDouble(belastungsfaktorDto.getOberkoerperBelastung().getCore()),
					Double.parseDouble(belastungsfaktorDto.getOberkoerperBelastung().getBack()),
					Double.parseDouble(belastungsfaktorDto.getOberkoerperBelastung().getBiceps()),
					Double.parseDouble(belastungsfaktorDto.getOberkoerperBelastung().getShoulder())),
				new UnterkoerperBelastung(
					Double.parseDouble(belastungsfaktorDto.getUnterkoerperBelastung().getGlutes()),
					Double.parseDouble(belastungsfaktorDto.getUnterkoerperBelastung().getQuads()),
					Double.parseDouble(belastungsfaktorDto.getUnterkoerperBelastung().getHamstrings())))));
	}
}
