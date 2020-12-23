package de.justinharder.trainharder.model.services;

import de.justinharder.trainharder.model.domain.Uebung;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.model.domain.enums.Uebungsart;
import de.justinharder.trainharder.model.domain.enums.Uebungskategorie;
import de.justinharder.trainharder.model.domain.exceptions.BelastungsfaktorNichtGefundenException;
import de.justinharder.trainharder.model.domain.exceptions.UebungNichtGefundenException;
import de.justinharder.trainharder.model.repository.BelastungsfaktorRepository;
import de.justinharder.trainharder.model.repository.UebungRepository;
import de.justinharder.trainharder.model.services.mapper.UebungDtoMapper;
import de.justinharder.trainharder.view.dto.UebungDto;
import lombok.NonNull;

import javax.inject.Inject;
import java.util.List;

public class UebungService
{
	private final UebungRepository uebungRepository;
	private final BelastungsfaktorRepository belastungsfaktorRepository;
	private final UebungDtoMapper uebungDtoMapper;

	@Inject
	public UebungService(
		UebungRepository uebungRepository,
		BelastungsfaktorRepository belastungsfaktorRepository,
		UebungDtoMapper uebungDtoMapper)
	{
		this.uebungRepository = uebungRepository;
		this.belastungsfaktorRepository = belastungsfaktorRepository;
		this.uebungDtoMapper = uebungDtoMapper;
	}

	public List<UebungDto> ermittleAlle()
	{
		return uebungDtoMapper.mappeAlle(uebungRepository.ermittleAlle());
	}

	public List<UebungDto> ermittleZuUebungsart(@NonNull String uebungsart)
	{
		return uebungDtoMapper.mappeAlle(uebungRepository.ermittleAlleZuUebungsart(Uebungsart.zuWert(uebungsart)));
	}

	public List<UebungDto> ermittleZuUebungskategorie(@NonNull String uebungskategorie)
	{
		return uebungDtoMapper
			.mappeAlle(uebungRepository.ermittleAlleZuUebungskategorie(Uebungskategorie.zuWert(uebungskategorie)));
	}

	public UebungDto ermittleZuId(@NonNull String id) throws UebungNichtGefundenException
	{
		return uebungRepository.ermittleZuId(new Primaerschluessel(id))
			.map(uebungDtoMapper::mappe)
			.orElseThrow(FehlermeldungService.wirfUebungNichtGefundenException("der ID", id));
	}

	public UebungDto speichereUebung(@NonNull UebungDto uebungDto, @NonNull String belastungsfaktorId)
		throws BelastungsfaktorNichtGefundenException
	{
		var belastungsfaktor = belastungsfaktorRepository
			.ermittleZuId(new Primaerschluessel(belastungsfaktorId))
			.orElseThrow(FehlermeldungService.wirfBelastungsfaktorNichtGefundenException("der ID", belastungsfaktorId));

		return uebungDtoMapper.mappe(uebungRepository.speichereUebung(new Uebung(
			new Primaerschluessel(),
			uebungDto.getName(),
			Uebungsart.zuWert(uebungDto.getUebungsart()),
			Uebungskategorie.zuWert(uebungDto.getUebungskategorie()),
			belastungsfaktor)));
	}
}
