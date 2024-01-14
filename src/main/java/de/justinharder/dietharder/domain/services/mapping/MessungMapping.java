package de.justinharder.dietharder.domain.services.mapping;

import de.justinharder.base.domain.services.mapping.Mapping;
import de.justinharder.dietharder.domain.model.Messung;
import de.justinharder.dietharder.domain.services.dto.messung.GespeicherteMessung;
import jakarta.enterprise.context.Dependent;
import lombok.NonNull;

@Dependent
public class MessungMapping implements Mapping<Messung, GespeicherteMessung>
{
	@Override
	public GespeicherteMessung mappe(@NonNull Messung messung)
	{
		return new GespeicherteMessung(
			messung.getId().toString(),
			messung.getDatum().toString(),
			messung.getKoerpergewicht().toString(),
			messung.getKoerperfettAnteil().toString());
	}
}
