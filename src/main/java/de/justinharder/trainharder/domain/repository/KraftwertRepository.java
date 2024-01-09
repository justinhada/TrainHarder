package de.justinharder.trainharder.domain.repository;

import de.justinharder.base.domain.repository.Repository;
import de.justinharder.trainharder.domain.model.Kraftwert;
import de.justinharder.base.domain.model.attribute.ID;
import lombok.NonNull;

import java.util.List;

public interface KraftwertRepository extends Repository<Kraftwert>
{
	List<Kraftwert> findeAlleMitBenutzer(@NonNull ID benutzerId);
}
