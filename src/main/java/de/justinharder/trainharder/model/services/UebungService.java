package de.justinharder.trainharder.model.services;

import com.google.common.base.Preconditions;
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

	public List<UebungDto> ermittleZuUebungsart(String uebungsart)
	{
		Preconditions.checkNotNull(uebungsart, "Die Ermittlung der Uebungen benötigt eine gültige Uebungsart!");

		return uebungDtoMapper.mappeAlle(
			uebungRepository.ermittleAlleZuUebungsart(Uebungsart.zuWert(uebungsart)));
	}

	public List<UebungDto> ermittleZuUebungskategorie(String uebungskategorie)
	{
		Preconditions.checkNotNull(uebungskategorie,
			"Die Ermittlung der Uebungen benötigt eine gültige Uebungskategorie!");

		return uebungDtoMapper.mappeAlle(uebungRepository
			.ermittleAlleZuUebungskategorie(Uebungskategorie.zuWert(uebungskategorie)));
	}

	public UebungDto ermittleZuId(String id) throws UebungNichtGefundenException
	{
		Preconditions.checkNotNull(id, "Die Ermittlung der Uebung benötigt eine gültige UebungID!");

		return uebungRepository.ermittleZuId(new Primaerschluessel(id))
			.map(uebungDtoMapper::mappe)
			.orElseThrow(FehlermeldungService.wirfUebungNichtGefundenException("der ID", id));
	}

	public UebungDto speichereUebung(UebungDto uebungDto, String belastungsfaktorId)
		throws BelastungsfaktorNichtGefundenException
	{
		Preconditions.checkNotNull(uebungDto, "Zur Erstellung der Uebung wird ein gültiges UebungDto benötigt!");
		Preconditions.checkNotNull(belastungsfaktorId,
			"Zur Erstellung der Uebung wird eine gültige BelastungsfaktorID benötigt!");

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
