package de.justinharder.trainharder.model.services.mapper;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import de.justinharder.trainharder.model.domain.Uebung;
import de.justinharder.trainharder.view.dto.UebungDto;

public class UebungDtoMapper
{
	private final BelastungsfaktorDtoMapper belastungsfaktorDtoMapper;

	@Inject
	public UebungDtoMapper(final BelastungsfaktorDtoMapper belastungsfaktorDtoMapper)
	{
		this.belastungsfaktorDtoMapper = belastungsfaktorDtoMapper;
	}

	public List<UebungDto> konvertiereAlle(final List<Uebung> uebungen)
	{
		return uebungen
			.stream()
			.map(this::konvertiere)
			.collect(Collectors.toList());
	}

	public UebungDto konvertiere(final Uebung uebung)
	{
		return new UebungDto(
			uebung.getPrimaerschluessel().getId().toString(),
			uebung.getName(),
			uebung.getUebungsart().name(),
			uebung.getUebungskategorie().name(),
			belastungsfaktorDtoMapper.konvertiere(uebung.getBelastungsfaktor()));
	}
}
