package de.justinharder.base.domain.services.mapping;

import de.justinharder.base.domain.model.Entitaet;
import de.justinharder.base.domain.services.dto.GespeichertesDTO;
import lombok.NonNull;

public interface Mapping<T extends Entitaet, U extends GespeichertesDTO<?>>
{
	U mappe(@NonNull T entitaet);
}
