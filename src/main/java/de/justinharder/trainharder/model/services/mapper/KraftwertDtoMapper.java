package de.justinharder.trainharder.model.services.mapper;

import de.justinharder.trainharder.model.domain.Kraftwert;
import de.justinharder.trainharder.view.dto.KraftwertDto;
import lombok.NonNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.format.DateTimeFormatter;

public class KraftwertDtoMapper implements DtoMapper<Kraftwert, KraftwertDto>
{
	private static final DateTimeFormatter DATUMFORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

	@Override
	public KraftwertDto mappe(@NonNull Kraftwert kraftwert)
	{
		return new KraftwertDto(
			kraftwert.getPrimaerschluessel().getId().toString(),
			praezision(kraftwert.getGewicht()),
			praezision(kraftwert.getKoerpergewicht()),
			kraftwert.getDatum().format(DATUMFORMAT),
			kraftwert.getWiederholungen().getWert());
	}

	private static String praezision(BigDecimal wert)
	{
		return wert.setScale(2, RoundingMode.HALF_UP).toString();
	}
}