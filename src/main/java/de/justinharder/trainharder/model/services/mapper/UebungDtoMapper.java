package de.justinharder.trainharder.model.services.mapper;

import de.justinharder.trainharder.model.domain.Uebung;
import de.justinharder.trainharder.view.dto.UebungDto;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class UebungDtoMapper
{
	private final BelastungsfaktorDtoMapper belastungsfaktorDtoMapper;

	@Inject
	public UebungDtoMapper(BelastungsfaktorDtoMapper belastungsfaktorDtoMapper)
	{
		this.belastungsfaktorDtoMapper = belastungsfaktorDtoMapper;
	}

	public List<UebungDto> mappeAlle(List<Uebung> uebungen)
	{
		return uebungen
			.stream()
			.map(this::mappe)
			.collect(Collectors.toList());
	}

	public UebungDto mappe(Uebung uebung)
	{
		return new UebungDto(
			uebung.getPrimaerschluessel().getId().toString(),
			uebung.getName(),
			uebung.getUebungsart().name(),
			uebung.getUebungskategorie().name(),
			belastungsfaktorDtoMapper.mappe(uebung.getBelastungsfaktor()));
	}
}
