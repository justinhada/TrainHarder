package de.justinharder.old.domain.services.mapper;

import de.justinharder.old.domain.model.attribute.Koerpermasse;
import de.justinharder.old.domain.services.dto.KoerpermasseDto;
import jakarta.enterprise.context.Dependent;
import lombok.NonNull;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Dependent
public class KoerpermasseDtoMapper
{
	public KoerpermasseDto mappe(@NonNull Koerpermasse koerpermasse)
	{
		return new KoerpermasseDto(
			koerpermasse.getKoerpergroesse().toString(),
			praezisionZwei(koerpermasse.getKoerpergewicht()),
			praezisionEins(koerpermasse.getKoerperfettAnteil()),
			praezisionZwei(koerpermasse.getFettfreiesKoerpergewicht()),
			praezisionEins(koerpermasse.getBodyMassIndex()),
			praezisionEins(koerpermasse.getFatFreeMassIndex()));
	}

	private static String praezisionEins(BigDecimal wert)
	{
		return wert.setScale(1, RoundingMode.HALF_UP).toString();
	}

	private static String praezisionZwei(BigDecimal wert)
	{
		return wert.setScale(2, RoundingMode.HALF_UP).toString();
	}
}
