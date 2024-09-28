package de.justinharder.base.domain.services.mapping;

import de.justinharder.base.domain.model.Entitaet;
import de.justinharder.base.domain.services.dto.GespeichertesDTO;
import de.justinharder.base.domain.services.dto.pagination.PaginationRequest;
import de.justinharder.base.domain.services.dto.pagination.PaginationResponse;
import lombok.NonNull;

import java.util.List;

public interface Mapping<T extends Entitaet, U extends GespeichertesDTO<U>>
{
	U mappe(@NonNull T entitaet);

	V mappe(@NonNull List<T> entitaeten);
}
