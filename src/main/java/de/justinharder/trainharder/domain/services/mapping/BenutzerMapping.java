package de.justinharder.trainharder.domain.services.mapping;

import de.justinharder.base.domain.services.mapping.Mapping;
import de.justinharder.trainharder.domain.model.Benutzer;
import de.justinharder.trainharder.domain.services.dto.benutzer.GespeicherterBenutzer;
import jakarta.enterprise.context.Dependent;
import lombok.NonNull;

@Dependent
public class BenutzerMapping implements Mapping<Benutzer, GespeicherterBenutzer>
{
	@Override
	public GespeicherterBenutzer mappe(@NonNull Benutzer benutzer)
	{
		return new GespeicherterBenutzer(
			benutzer.getId().toString(),
			benutzer.getVorname().getWert(),
			benutzer.getNachname().getWert());
	}
}
