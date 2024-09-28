package de.justinharder.base.domain.repository;

import de.justinharder.base.domain.model.Entitaet;
import de.justinharder.base.domain.model.attribute.ID;
import de.justinharder.base.domain.services.dto.pagination.PaginationRequest;
import lombok.NonNull;

import java.util.List;
import java.util.Optional;

public interface Repository<T extends Entitaet>
{
	List<T> findeAlle(@NonNull PaginationRequest<?> paginationRequest);

	Integer zaehleAlle();

	Optional<T> finde(@NonNull ID id);

	void speichere(@NonNull T entitaet);

	void loesche(@NonNull T entitaet);
}
