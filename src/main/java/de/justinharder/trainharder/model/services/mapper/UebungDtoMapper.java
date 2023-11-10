package de.justinharder.trainharder.model.services.mapper;

import de.justinharder.trainharder.model.domain.Uebung;
import de.justinharder.trainharder.view.dto.UebungDto;
import jakarta.inject.Inject;
import lombok.NonNull;

public class UebungDtoMapper implements DtoMapper<Uebung,UebungDto>
{
	private final BelastungsfaktorDtoMapper belastungsfaktorDtoMapper;

	@Inject
	public UebungDtoMapper(BelastungsfaktorDtoMapper belastungsfaktorDtoMapper)
	{
		this.belastungsfaktorDtoMapper = belastungsfaktorDtoMapper;
	}

	@Override
	public UebungDto mappe(@NonNull Uebung uebung)
	{
		return new UebungDto(
			uebung.getPrimaerschluessel().getId().toString(),
			uebung.getName(),
			uebung.getUebungsart().name(),
			uebung.getUebungskategorie().name(),
			belastungsfaktorDtoMapper.mappe(uebung.getBelastung()));
	}
}
