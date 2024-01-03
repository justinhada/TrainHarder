package de.justinharder.trainharder.domain.services.mapper;

import de.justinharder.trainharder.domain.model.Uebung;
import de.justinharder.trainharder.domain.services.dto.UebungDto;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import lombok.NonNull;

@Dependent
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
