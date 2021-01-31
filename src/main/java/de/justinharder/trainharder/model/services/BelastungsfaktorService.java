package de.justinharder.trainharder.model.services;

import de.justinharder.trainharder.model.domain.Belastung;
import de.justinharder.trainharder.model.domain.embeddables.GrunduebungBelastung;
import de.justinharder.trainharder.model.domain.embeddables.OberkoerperBelastung;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.model.domain.embeddables.UnterkoerperBelastung;
import de.justinharder.trainharder.model.domain.exceptions.BelastungsfaktorNichtGefundenException;
import de.justinharder.trainharder.model.repository.BelastungsfaktorRepository;
import de.justinharder.trainharder.model.services.mapper.BelastungsfaktorDtoMapper;
import de.justinharder.trainharder.view.dto.BelastungDto;
import lombok.NonNull;

import javax.inject.Inject;

public class BelastungsfaktorService
{
	private final BelastungsfaktorRepository belastungsfaktorRepository;
	private final BelastungsfaktorDtoMapper belastungsfaktorDtoMapper;

	@Inject
	public BelastungsfaktorService(BelastungsfaktorRepository belastungsfaktorRepository, BelastungsfaktorDtoMapper belastungsfaktorDtoMapper)
	{
		this.belastungsfaktorRepository = belastungsfaktorRepository;
		this.belastungsfaktorDtoMapper = belastungsfaktorDtoMapper;
	}

	public BelastungDto ermittleZuId(@NonNull String id) throws BelastungsfaktorNichtGefundenException
	{
		return belastungsfaktorRepository.ermittleZuId(new Primaerschluessel(id))
			.map(belastungsfaktorDtoMapper::mappe)
			.orElseThrow(FehlermeldungService.wirfBelastungsfaktorNichtGefundenException("der ID", id));
	}

	public BelastungDto speichereBelastungsfaktor(@NonNull BelastungDto belastungDto)
	{
		return belastungsfaktorDtoMapper.mappe(belastungsfaktorRepository.speichereBelastungsfaktor(new Belastung(
			new Primaerschluessel(),
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