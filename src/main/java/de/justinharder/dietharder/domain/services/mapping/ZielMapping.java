package de.justinharder.dietharder.domain.services.mapping;

import de.justinharder.base.domain.services.mapping.Mapping;
import de.justinharder.dietharder.domain.model.Ziel;
import de.justinharder.dietharder.domain.services.dto.ziel.GespeichertesZiel;
import jakarta.enterprise.context.Dependent;
import lombok.NonNull;

@Dependent
public class ZielMapping implements Mapping<Ziel, GespeichertesZiel>
{
	@Override
	public GespeichertesZiel mappe(@NonNull Ziel ziel)
	{
		return new GespeichertesZiel(
			ziel.getId().toString(),
			ziel.getDatum().toString(),
			ziel.getKoerpergewicht().toString(),
			ziel.getKoerperfettAnteil().toString());
	}
}
