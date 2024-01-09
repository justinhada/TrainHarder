package de.justinharder.old.domain.services.mapper;

import de.justinharder.old.domain.model.Kraftwert;
import de.justinharder.old.domain.services.dto.KraftwertDto;
import jakarta.enterprise.context.Dependent;
import lombok.NonNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.format.DateTimeFormatter;

@Dependent
public class KraftwertDtoMapper implements DtoMapper<Kraftwert, KraftwertDto>
{
	@Override
	public KraftwertDto mappe(@NonNull Kraftwert kraftwert)
	{
		return new KraftwertDto(
			kraftwert.getId().getWert().toString(),
			kraftwert.getDatum().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
			praezision(kraftwert.getGewicht()),
			kraftwert.getWiederholungen().getWert());
	}

	private static String praezision(BigDecimal wert)
	{
		return wert.setScale(2, RoundingMode.HALF_UP).toString();
	}
}
