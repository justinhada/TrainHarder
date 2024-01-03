package de.justinharder.trainharder.domain.services.mapper;

import de.justinharder.trainharder.domain.model.Uebung;
import de.justinharder.trainharder.domain.services.dto.UebungDto;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import lombok.NonNull;

@Dependent
public class UebungDtoMapper implements DtoMapper<Uebung,UebungDto>
{
	private final BelastungDtoMapper belastungDtoMapper;

	@Inject
	public UebungDtoMapper(BelastungDtoMapper belastungDtoMapper)
	{
		this.belastungDtoMapper = belastungDtoMapper;
	}

	@Override
	public UebungDto mappe(@NonNull Uebung uebung)
	{
		return new UebungDto(
			uebung.getId().getWert().toString(),
			uebung.getBezeichnung(),
			uebung.getUebungsart().name(),
			uebung.getUebungskategorie().name(),
			belastungDtoMapper.mappe(uebung.getBelastung()));
	}
}
