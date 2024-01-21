package de.justinharder.base.domain.repository;

import de.justinharder.base.domain.model.Entitaet;
import de.justinharder.base.domain.model.attribute.ID;
import lombok.NonNull;

import java.util.List;
import java.util.Optional;

public interface Repository<T extends Entitaet>
{
	List<T> findeAlle();

	List<T> findeAlle(@NonNull Long page, @NonNull Long pageSize);

	Optional<T> finde(@NonNull ID id);

	void speichere(@NonNull T entitaet);

	void loesche(@NonNull T entitaet);
}
